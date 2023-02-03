/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.utilisateur;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="variation_money")
public class Variation_money extends BddObject{
    @Attribut(attr="sum")
    Double prix;
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="mois")
    Double mois;
    @Attribut(attr="annee")
    Double annee;

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Double getMois() {
        return mois;
    }

    public void setMois(Double mois) {
        this.mois = mois;
    }

    public Double getAnnee() {
        return annee;
    }

    public void setAnnee(Double annee) {
        this.annee = annee;
    }
    
}
