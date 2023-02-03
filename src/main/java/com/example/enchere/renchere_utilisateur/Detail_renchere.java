/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.renchere_utilisateur;

import com.example.enchere.enchere.Detail_enchere;
import com.example.enchere.enchere.Enchere;
import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="detail_renchere")
public class Detail_renchere extends BddObject{
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="nom")
    String nom;
    @Attribut(attr="prenom")
    String prenom;
    @Attribut(attr="identifiant")
    String identifiant;
    @Attribut(attr="mdp")
    String mdp;
    @Attribut(attr="datenaissance")
    Date datenaissance;
    @Attribut(attr="dateexpiration")
    Timestamp dateexpiration;
    @Attribut(attr="idenchere")
    String idenchere;
    @Attribut(attr="idrenchere")
    String idrenchere;
    @Attribut(attr="prix")
    Double prix;
    @Attribut(attr="daterenchere")
    Timestamp daterenchere;

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public Timestamp getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Timestamp dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(String idenchere) {
        this.idenchere = idenchere;
    }

    public String getIdrenchere() {
        return idrenchere;
    }

    public void setIdrenchere(String idrenchere) {
        this.idrenchere = idrenchere;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Timestamp getDaterenchere() {
        return daterenchere;
    }

    public void setDaterenchere(Timestamp daterenchere) {
        this.daterenchere = daterenchere;
    }
      public ArrayList<Object> getHistoriqueRenchere(Connect conn) throws Exception{
        
        String sql="SELECT * from detail_renchere where idenchere='"+this.getIdenchere()+"' order by daterenchere desc";
      ArrayList<Object>obj= GenericDAO.executeQuery(sql, new Connect(), new Detail_renchere());
     return obj; 
      
    }
    
    
}
