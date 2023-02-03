/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.statistique;

import dao.GenericDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
public class Statistique {
    Object[] categorie;
    Double moyenne_commission;
    Double moyenne_mise;
    Object[] enchere_rencheri;
    Object[] statistique_argent;
    Object[] nombre_rencherisseur;

    public Object[] getStatistique_argent() {
        return statistique_argent;
    }

    public void setStatistique_argent(Object[] statistique_argent) {
        this.statistique_argent = statistique_argent;
    }

    public Object[] getNombre_rencherisseur() {
        return nombre_rencherisseur;
    }

    public void setNombre_rencherisseur(Object[] nombre_rencherisseur) {
        this.nombre_rencherisseur = nombre_rencherisseur;
    }

    public Object[] getEnchere_rencheri() {
        return enchere_rencheri;
    }

    public void setEnchere_rencheri(Object[] enchere_rencheri) {
        this.enchere_rencheri = enchere_rencheri;
    }
    
    
    public Object[] getCategorie() {
        return categorie;
    }

    public void setCategorie( Object[] categorie) {
        this.categorie = categorie;
    }

    public Double getMoyenne_commission() {
        return moyenne_commission;
    }

    public void setMoyenne_commission(Double moyenne_commission) {
        this.moyenne_commission = moyenne_commission;
    }

    public Double getMoyenne_mise() {
        return moyenne_mise;
    }

    public void setMoyenne_mise(Double moyenne_mise) {
        this.moyenne_mise = moyenne_mise;
    }
    public Statistique() throws Exception {
        Connect conn=null;
        try {
            
            conn=new Connect();
            conn.setuses(true);
            this.setCategorie((new Categorie_rencheri()).selectAll(conn));
            this.setMoyenne_commission((Double)(GenericDAO.executeQuery("select avg from moyenne_commission", conn, "avg")[0]));
            this.setMoyenne_mise((Double)(GenericDAO.executeQuery("select avg from moyenne_mise", conn, "avg")[0]));
            this.setEnchere_rencheri(new Enchere_plus_rencheri().selectAll(conn));
            this.setStatistique_argent(new Statistique_Argent().selectAll(conn));
            this.setNombre_rencherisseur(new Nombre_Rencherisseur().selectAll(conn));
        } catch (Exception ex) {
           throw ex;
        } finally{
            conn.forceClose();
        }
        
    }
    
}
