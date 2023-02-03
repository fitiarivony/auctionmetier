/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.produit;

import com.example.enchere.sary.Sary_Produit;
import dao.GenericDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */

public class Produit_insert {
    String nomproduit;
    String idutilisateur;
    String[]categorie;
    String[]sary;
    String idunite;
    Double nombre;
    String descri;

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
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

    public String[] getCategorie() {
        return categorie;
    }

    public void setCategorie(String[] categorie) {
        this.categorie = categorie;
    }

    public String[] getSary() {
        return sary;
    }

    public void setSary(String[] sary) {
        this.sary = sary;
    }
    
    public boolean insert(Connect conn) throws Exception {
        conn.setuses(true);
        try {
            conn.setAutoCommit(false);
            Produit p=new Produit();
            p.setNomproduit(this.getNomproduit());
            p.setIdunite(this.getIdunite());
            p.setIdutilisateur(this.getIdutilisateur());
            p.setNombre(this.getNombre());
            p.setDescri(this.getDescri());
            if(p.insert(conn)){
                String lastid=GenericDAO.getLastIdString(p, conn);
               System.out.println(lastid);
                for(String cat:this.getCategorie()){
                    Categorie_produit catprod=new Categorie_produit();
                    catprod.setIdcategorie(cat);
                    catprod.setIdproduit(lastid);
                    if(!catprod.insert(conn)){
                        conn.rollback();
                        throw new Exception("une categorie "+cat+"  non assignée");
                    }
                }
                 for(String pics:this.getSary()){
                  Sary_Produit sary=new Sary_Produit();
                  sary.setIdproduit(lastid);
                  sary.ajouterPhoto(pics, conn);
                }
            }
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw ex;
        }
        conn.rollback();
        return false;
    }

    public String getIdunite() {
        return idunite;
    }

    public void setIdunite(String idunite) {
        this.idunite = idunite;
    }

    public Double getNombre() {
        return nombre;
    }

    public void setNombre(Double nombre) {
        this.nombre = nombre;
    }
    
    
}
