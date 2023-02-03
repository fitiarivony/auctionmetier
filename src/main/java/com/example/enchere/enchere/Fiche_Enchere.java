package com.example.enchere.enchere;
import java.sql.Timestamp;

import com.example.enchere.enchere.Enchere;
import com.example.enchere.produit.Detail_catprod;
import com.example.enchere.sary.Detail_saryprod;
import com.example.enchere.sary.Sary;
import utils.Connect;
import utils.ObjectHelper;

public class Fiche_Enchere{
    private String idenchere;
    private String idProduit;
    private Timestamp dateenchere;
    private Double prixenchere;
    private Double dureeenchere;
    private Integer etat;
    private String descri;
    private Detail_saryprod[] sary;
    private Detail_catprod[] detail_catprods;
    private String idutilisateur;

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public void initFiche(Connect conn) throws Exception{
        conn.setuses(true);
        Detail_enchere enchere=new Detail_enchere();
        enchere.setIdenchere(this.getIdenchere());
        enchere=(Detail_enchere)enchere.getByIds(conn)[0];
        this.setDateenchere(enchere.getDateenchere());
        this.setIdenchere(enchere.getIdenchere());
        this.setDateenchere(enchere.getDateenchere());
        this.setDureeenchere(enchere.getDureeenchere());
        this.setPrixenchere(enchere.getPrixenchere());
        this.setEtat(enchere.getEtat());
        this.setIdProduit(enchere.getIdproduit());
       this.setIdutilisateur(enchere.getIdutilisateur());
        Detail_saryprod sary=new Detail_saryprod();
        sary.setIdproduit(this.getIdProduit());
      this.setSary(ObjectHelper.convertSary(sary.getByIds(conn)));
      Detail_catprod prod=new Detail_catprod();
    
      prod.setIdproduit(this.getIdProduit());
      this.setDetail_catprods(ObjectHelper.convertCat(prod.getByIds(conn)));
        this.setDescri(this.getDetail_catprods()[0].getDescri());
    }
    
    public Detail_saryprod[] getSary() {
        return sary;
    }

    public void setSary(Detail_saryprod[] sary) {
        this.sary = sary;
    }

    public String getDescri() {
        return descri;
    }
    public void setDescri(String descri) {
        this.descri = descri;
    }
    
    public Detail_catprod[] getDetail_catprods() {
        return detail_catprods;
    }
    public void setDetail_catprods(Detail_catprod[] detail_catprods) {
        this.detail_catprods = detail_catprods;
    }

    public String getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(String idenchere) {
        this.idenchere = idenchere;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
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
}