/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.sary;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="detail_saryprod")
public class Detail_saryprod extends BddObject{
    @Attribut(attr="idproduit")
    String idproduit;
    @Attribut(attr="nomproduit")
    String nomproduit;
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="nombre")
    Double nombre;
    @Attribut(attr="idunite")
    String idunite;
    @Attribut(attr="descri")
    String descri;
    @Attribut(attr="idsary")
    String idsary;
    @Attribut(attr="basesary")
    String basesary;

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
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

    public Double getNombre() {
        return nombre;
    }

    public void setNombre(Double nombre) {
        this.nombre = nombre;
    }

    public String getIdunite() {
        return idunite;
    }

    public void setIdunite(String idunite) {
        this.idunite = idunite;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getIdsary() {
        return idsary;
    }

    public void setIdsary(String idsary) {
        this.idsary = idsary;
    }

    public String getBasesary() {
        return basesary;
    }

    public void setBasesary(String basesary) {
        this.basesary = basesary;
    }
    
    
}
