/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.renchere_utilisateur;

import com.example.enchere.enchere.Detail_enchere;
import com.example.enchere.enchere.Enchere;
import com.example.enchere.mouvement.Mouvement;
import com.example.enchere.renchere.RenchereMaxPrix;
import com.example.enchere.utilisateur.Utilisateur;
import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="renchere_utilisateur")
public class Renchere_utilisateur extends BddObject{
    @Attribut(attr="idrenchere",primary_key=true)
    String idrenchere;
    @Attribut(attr="idenchere")
    String idenchere;
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="daterenchere")
    Timestamp daterenchere;
    @Attribut(attr="prix")
    Double prix;

    public String getIdrenchere() {
        return idrenchere;
    }

    public void setIdrenchere(String idrenchere) {
        this.idrenchere = idrenchere;
    }

    public String getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(String idenchere) {
        this.idenchere = idenchere;
    }

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Timestamp getDaterenchere() {
        return daterenchere;
    }

    public void setDaterenchere(Timestamp daterenchere) {
        this.daterenchere = daterenchere;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
  
    public void ismyEnchere(Connect conn) throws Exception{
        Detail_enchere enc=new Detail_enchere();
        enc.setIdenchere(this.getIdenchere());
        enc=(Detail_enchere)enc.getByIds(conn)[0];
        if(enc.getIdutilisateur().equalsIgnoreCase(this.getIdutilisateur()))
            throw new Exception("Vous ne pouvez pas miser sur votre propre enchere");
        if(enc.getEtat()==1) throw new Exception("L enchere est deja termine");
           
        
    }
    @Override
    public boolean insert(Connect conn) throws SQLException, Exception {
//        CTRL
 new Utilisateur().updateEtatEnchereOver(conn);
        this.ismyEnchere(conn);
        Mouvement mouvement=new Mouvement();
        mouvement.setIdutilisateur(getIdutilisateur());
         mouvement.setValide(1);
        Object[] listmouv=mouvement.getByIds(conn);
        double montant=0;
        for(Object ob:listmouv){
            Mouvement mouv=(Mouvement)ob;
            montant+=mouv.getMontant();
        }
//        - enchere en cours
        Detail_enchere de=new Detail_enchere();
        de.setIdutilisateur(getIdutilisateur());
        de.setEtat(0);
        Object[] listdetail=de.getByIds(conn);
        for(Object ob:listdetail){
            Detail_enchere detail=(Detail_enchere) ob;
            montant-=detail.getPrixenchere();
        }

//        - renchere en cours
        RenchereMaxPrix rmp=new RenchereMaxPrix();
        rmp.setIdutilisateur(getIdutilisateur());
        ArrayList listrenchere=rmp.listNotFinishedRenchere(conn);
        if(listrenchere.size()!=0){
            for(Object ob:listrenchere){
                RenchereMaxPrix max=(RenchereMaxPrix) ob;
                montant-=max.getPrix();
            }
        }
        if(montant<this.getPrix()){
            throw new Exception("votre solde ne vous permet pas cette transaction");
        }
//      CTRL2 prixmin enchere
        RenchereMaxPrix renchere=new RenchereMaxPrix();
        ArrayList l = GenericDAO.executeQuery(" select 'e1' as idenchere,'u1' as idutilisateur," +
                "max(prix)as prix from renchere_enchere where " +
                " idenchere='"+this.getIdenchere()+"' ", new Connect(), renchere);
        if(l.size()!=0) {
            renchere = (RenchereMaxPrix) l.get(0);
            if(this.getPrix()<=renchere.getPrix()){
                throw new Exception("votre montant est inférieur ou egale au montant minimum de l'enchère");
            }
        }

        return super.insert(conn);
    }
    
    public Tri_mise getMise_max(Connect conn) throws Exception{
        Tri_mise tri=new Tri_mise();
        tri.setIdenchere(this.getIdenchere());
        return (Tri_mise)tri.getByIds(conn)[0];
    }
    
}
