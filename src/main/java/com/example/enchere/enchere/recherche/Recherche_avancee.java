/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.enchere.recherche;

import com.example.enchere.categorie.Categorie;
import com.example.enchere.enchere.Enchere;
import com.example.enchere.enchere.recherche.Recherche;
import com.example.enchere.produit.Produit;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="recherche_avancee")
public class Recherche_avancee {
    @Attribut(attr="idenchere")
    String idenchere;
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

    public String getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(String idenchere) {
        this.idenchere = idenchere;
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
    public Object[] recherche_avancee(Recherche rec,Connect conn) throws Exception{
        ArrayList<ResultRecherche>liste=new ArrayList<>();
        Object[]obj=GenericDAO.executeQuery( rec.generateSQL(), conn, new Recherche_avancee()).toArray();
        for(int i=0;i<obj.length;i++){
            ResultRecherche result=new ResultRecherche();
            Enchere enchere=new Enchere();
            enchere.setDateenchere(((Recherche_avancee)obj[i]).getDateenchere());
             enchere.setDureeenchere(((Recherche_avancee)obj[i]).getDureeenchere());
             enchere.setPrixenchere(((Recherche_avancee)obj[i]).getPrixenchere());
             enchere.setEtat(((Recherche_avancee)obj[i]).getEtat());
             enchere.setIdcommission(((Recherche_avancee)obj[i]).getIdcommission());
             enchere.setIdenchere(((Recherche_avancee)obj[i]).getIdenchere());
              enchere.setIdproduit(((Recherche_avancee)obj[i]).getIdproduit());
            result.setEnchere(enchere);
            
             Categorie cat=new Categorie();
            cat.setIdcategorie(((Recherche_avancee)obj[i]).getIdcategorie());
             cat.setNomcategorie(((Recherche_avancee)obj[i]).getNomcategorie());
            Produit prod=new Produit();
            prod.setIdproduit(((Recherche_avancee)obj[i]).getIdproduit());
             prod.setNomproduit(((Recherche_avancee)obj[i]).getNomproduit());
             prod.setIdunite(((Recherche_avancee)obj[i]).getIdunite());
             prod.setIdutilisateur(((Recherche_avancee)obj[i]).getIdutilisateur());
             prod.setNombre(((Recherche_avancee)obj[i]).getNombre()); 
             prod.setDescri(((Recherche_avancee)obj[i]).getDescri());
             
             
             result.setProd(prod);
            Integer all=result.alreadyThere(liste);
             if(all!=null){
                 liste.get(all).getCategorie().add(cat);
             }else{
                 ArrayList<Categorie>categories=new ArrayList<>();
                 categories.add(cat);
                 result.setCategorie(categories);
                 liste.add(result);
             }
        }
        return liste.toArray();
    }
}
