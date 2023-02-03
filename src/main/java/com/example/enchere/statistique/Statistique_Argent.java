package com.example.enchere.statistique;
import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;


@AnnotMap(nomTable="statistique_argent_gagne")
public class Statistique_Argent extends BddObject {

    @Attribut(attr="idproduit")
    private String idProduit;

    @Attribut(attr="total_ench")
    private Double total_ench;

    @Attribut(attr="max_ench")
    private Double max_ench;

    @Attribut(attr="mois")
    private Double mois;

    @Attribut(attr="annee")
    private Double annee;

    public String getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
    public Double getTotal_ench() {
        return total_ench;
    }
    public void setTotal_ench(Double total_ench) {
        this.total_ench = total_ench;
    }
    public Double getMax_ench() {
        return max_ench;
    }
    public void setMax_ench(Double max_ench) {
        this.max_ench = max_ench;
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