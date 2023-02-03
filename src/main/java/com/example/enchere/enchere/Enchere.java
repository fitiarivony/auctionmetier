package com.example.enchere.enchere;

import com.example.enchere.comission.Comission;
import com.example.enchere.mouvement.Mouvement;
import com.example.enchere.produit.Produit;
import com.example.enchere.renchere.RenchereMaxPrix;
import com.example.enchere.renchere_utilisateur.Tri_mise;
import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.Connection;
import utils.Connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

@AnnotMap(nomTable="enchere")
public class Enchere extends BddObject{
    @Attribut(attr="idenchere",primary_key=true)
    String idenchere;
    @Attribut(attr="idproduit")
    String idproduit;
    @Attribut(attr="dateenchere")
    Timestamp dateenchere;
    @Attribut(attr="prixenchere")
    Double prixenchere;
    @Attribut(attr="dureeenchere")
    Double dureeenchere;
    @Attribut(attr="etat")
    Integer etat;
    @Attribut(attr="idcommission")
    String idcommission;

    public String getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(String idenchere) {
        this.idenchere = idenchere;
    }

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
    }

    public Timestamp getDateenchere() {
        return dateenchere;
    }

    public void setDateenchere(Timestamp dateenchere) {
        this.dateenchere = dateenchere;
    }

    public Double getPrixenchere() {
        return prixenchere;
    }

    public void setPrixenchere(Double prixenchere) {
        this.prixenchere = prixenchere;
    }

    public Double getDureeenchere() {
        return dureeenchere;
    }

    public void setDureeenchere(Double dureeenchere) {
        this.dureeenchere = dureeenchere;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public String getIdcommission() {
        return idcommission;
    }

    public void setIdcommission(String idcommission) {
        this.idcommission = idcommission;
    }

    @Override
    public String toString() {
        return "Enchere{" + "idenchere=" + idenchere + ", idproduit=" + idproduit + ", dateenchere=" + dateenchere + ", prixenchere=" + prixenchere + ", dureeenchere=" + dureeenchere + ", etat=" + etat + ", idcommission=" + idcommission + '}';
    }
   
 
    @Override
    public boolean insert(Connect conn) throws SQLException, Exception {
//        ctrl
        Produit pro=new Produit();
        pro.setIdproduit(this.getIdproduit());
        pro=(Produit) pro.getById(conn).get(0);
        Mouvement mouvement=new Mouvement();
        mouvement.setIdutilisateur(pro.getIdutilisateur());
        mouvement.setValide(1);
        Object[] listmouv=mouvement.getByIds(conn);
        double montant=0;
        for(Object ob:listmouv){
            Mouvement mouv=(Mouvement)ob;
            montant+=mouv.getMontant();
        }
//        - enchere en cours
        Detail_enchere de=new Detail_enchere();
        de.setIdutilisateur(pro.getIdutilisateur());
        de.setEtat(0);
        Object[] listdetail=de.getByIds(conn);
        for(Object ob:listdetail){
            Detail_enchere detail=(Detail_enchere) ob;
            montant-=detail.getPrixenchere();
        }

//        - renchere en cours
        RenchereMaxPrix rmp=new RenchereMaxPrix();
        rmp.setIdutilisateur(pro.getIdutilisateur());
        ArrayList listrenchere=rmp.listNotFinishedRenchere(conn);
        if(listrenchere.size()!=0){
            for(Object ob:listrenchere){
                RenchereMaxPrix max=(RenchereMaxPrix) ob;
                montant-=max.getPrix();
            }
        }

        if(montant<this.getPrixenchere()){
            throw new Exception("votre solde ne vous permet pas cette transaction");
        }
        return super.insert(conn);
    }
    
    

//    sort an ArrayList of enchere
    public static void sort(ArrayList<Enchere> list){
        Collections.sort(list, Collections.reverseOrder(new Comparator<Enchere>() {
            @Override
            public int compare(Enchere o1, Enchere o2) {
                return o1.getDateenchere().compareTo(o2.getDateenchere());
            }
        }));
    }

}
