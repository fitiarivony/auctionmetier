package com.example.enchere.statistique;
import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;

@AnnotMap(nomTable="nombre_rencherisseur")
public class Nombre_Rencherisseur extends BddObject {

    @Attribut(attr="nombre")
    private Integer nbUtilisateur;

    @Attribut(attr="mois")
    private Double mois;

    @Attribut(attr="annee")
    private Double annee;

    public int getNbUtilisateur() {
        return nbUtilisateur;
    }
    public void setNbUtilisateur(int nbUtilisateur) {
        this.nbUtilisateur = nbUtilisateur;
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
