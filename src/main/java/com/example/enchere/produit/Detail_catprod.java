/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.produit;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="detail_catprod")
public class Detail_catprod extends BddObject{
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
    @Attribut(attr="idcategorie")
    String idcategorie;
    @Attribut(attr="nomcategorie")
    String nomcategorie;

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

    public String getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(String idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
    
    
}
