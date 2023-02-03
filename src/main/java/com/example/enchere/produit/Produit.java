/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.produit;

import com.example.enchere.sary.Sary_Produit;
import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="produit")
public class Produit extends BddObject{
    @Attribut(attr="idproduit",primary_key=true)
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
    public Object[] getProduitDispo(Connect conn) throws Exception{
       return GenericDAO.executeQuery("SELECT * from produit where idproduit not in(select idproduit from renchere_enchere) and idutilisateur='"+this.getIdutilisateur()+"'", conn,new Produit()).toArray();
    }
    
    
}
