package com.example.enchere.renchere;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;
import utils.Connect;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@AnnotMap(nomTable="renchere_enchere")
public class Renchere_enchere extends BddObject {
    @Attribut(attr="idrenchere")
    String idrenchere;
    @Attribut(attr="idenchere")
    String idenchere;
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="daterenchere")
    Timestamp daterenchere;
    @Attribut(attr="prix")
    Double prix;
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



}
