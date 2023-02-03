/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.mouvement;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;
import java.util.Date;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="mouvement")
public class Mouvement extends BddObject{
    @Attribut(attr="idmouvement",primary_key=true)
    String idmouvement;
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="montant")
    Double montant;
    @Attribut(attr="dateinsertion")
    Date dateinsertion;
    @Attribut(attr="valide")
    Integer valide;
    @Attribut(attr="motif")
    String motif;

    public Integer getValide() {
        return valide;
    }

    public void setValide(Integer valide) {
        this.valide = valide;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
    

    public String getIdmouvement() {
        return idmouvement;
    }

    public void setIdmouvement(String idmouvement) {
        this.idmouvement = idmouvement;
    }

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateut) {
        this.idutilisateur = idutilisateut;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDateinsertion() {
        return dateinsertion;
    }

    public void setDateinsertion(Date dateinsertion) {
        this.dateinsertion = dateinsertion;
    }
    
}
