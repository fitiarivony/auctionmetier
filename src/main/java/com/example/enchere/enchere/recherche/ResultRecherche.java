/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.enchere.recherche;

import com.example.enchere.categorie.Categorie;
import com.example.enchere.enchere.Enchere;
import com.example.enchere.produit.Produit;
import frame.Attribut;
import java.util.ArrayList;

/**
 *
 * @author FITIA ARIVONY
 */
public class ResultRecherche {
    Enchere enchere;
    ArrayList<Categorie> categorie;
    Produit prod;

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public  ArrayList<Categorie> getCategorie() {
        return categorie;
    }

    public void setCategorie( ArrayList<Categorie> categorie) {
        this.categorie = categorie;
    }

    public Produit getProd() {
        return prod;
    }

    public void setProd(Produit prod) {
        this.prod = prod;
    }
    public Integer alreadyThere(ArrayList<ResultRecherche>obj){
        for(int i=0;i<obj.size();i++){
              if(obj.get(i).getEnchere().getIdenchere().equalsIgnoreCase(this.getEnchere().getIdenchere()))return i;
        }
      
        return null;
    }
}
