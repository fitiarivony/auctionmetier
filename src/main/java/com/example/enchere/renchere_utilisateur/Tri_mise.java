/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.renchere_utilisateur;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;
import java.util.Date;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="tri_mise")
public class Tri_mise extends BddObject{
    @Attribut(attr="idutilisateur")
    String idutilisateur;
     @Attribut(attr="nom")
    String nom;
    @Attribut(attr="identifiant")
    String identifiant;
    @Attribut(attr="mdp")
    String mdp;
    @Attribut(attr="datenaissance")
    Date datenaissance;
    @Attribut(attr="token")
    String token;
    @Attribut(attr="dateexpiration")
    Date dateexpiration;
    @Attribut(attr="idenchere")
    String idenchere;
    @Attribut(attr="idrenchere")
    String idrenchere;
    @Attribut(attr="prix")
    Double prix;
    @Attribut(attr="daterenchere")
    Date daterenchere;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Date dateexpiration) {
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

    public Date getDaterenchere() {
        return daterenchere;
    }

    public void setDaterenchere(Date daterenchere) {
        this.daterenchere = daterenchere;
    }
    
    
}
