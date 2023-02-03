/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.enchere;

import com.example.enchere.comission.Comission;
import com.example.enchere.mouvement.Mouvement;
import com.example.enchere.renchere_utilisateur.Tri_mise;
import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="detail_enchere")
public class Detail_enchere extends BddObject{
    @Attribut(attr="idenchere")
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
    @Attribut(attr="nomproduit")
    String nomproduit;
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="valeurcommission")
    Double valeurcommission;

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

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Double getValeurcommission() {
        return valeurcommission;
    }

    public void setValeurcommission(Double valeurcommission) {
        this.valeurcommission = valeurcommission;
    }
    
    public Tri_mise getTrimise(Connect conn)throws Exception{
        Object[] list=new Tri_mise().getByIds(conn);
        if(list.length!=0){
            return (Tri_mise) list[0];
        }
        return null;
    }
    
    public void insertTri(Connect conn)throws Exception{
        Tri_mise tri=getTrimise(conn);
        if(tri!=null){
            Comission com=new Comission();
            com.setIdcomission(this.getIdcommission());
            com=(Comission) com.getById(conn).get(0);
            double volamivoka= tri.getPrix();
            double pourc=100.0-com.getValeurcomission();
            //nanao enchere
            double volamiditra=volamivoka*(pourc/100.0);
            conn.setAutoCommit(false);
            //nandresy
            Mouvement nandresy=new Mouvement();
            nandresy.setIdutilisateur(tri.getIdutilisateur());
            nandresy.setMontant(volamivoka*-1);
            nandresy.setMotif("nandresy");
            nandresy.setValide(1);
            nandresy.insert(conn);
            //enchere
            Mouvement enchere=new Mouvement();
            enchere.setIdutilisateur(tri.getIdutilisateur());
            enchere.setMontant(volamiditra);
            enchere.setMotif(this.getIdenchere());
            enchere.setValide(1);
            enchere.insert(conn);
            conn.commit();
        }
    }
    
    public ArrayList<Object> getHistoriqueEnchere(Connect conn) throws Exception{
        
        String sql="SELECT * from detail_enchere where idutilisateur='"+this.getIdutilisateur()+"' order by dateenchere desc";
      ArrayList<Object>obj= GenericDAO.executeQuery(sql, new Connect(), new Detail_enchere());
     return obj; 
      
    }
    
    public static void sort(ArrayList<Detail_enchere> list){
        Collections.sort(list, Collections.reverseOrder(new Comparator<Detail_enchere>() {
            @Override
            public int compare(Detail_enchere o1, Detail_enchere o2) {
                return o1.getDateenchere().compareTo(o2.getDateenchere());
            }
        }));
    }
    
}
