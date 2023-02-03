/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.statistique;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.Timestamp;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="enchere_plus_rencheri")
public class Enchere_plus_rencheri extends BddObject{
    @Attribut(attr="idenchere")
    String idenchere;
    @Attribut(attr="idproduit")
    String idproduit;
    @Attribut(attr="nomproduit")
    String nomproduit;
    @Attribut(attr="dureeenchere")
    Double duree;
    @Attribut(attr="dateenchere")
    Timestamp dateenchere;
    @Attribut(attr="count")
    Integer nombre;

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

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

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public Timestamp getDateenchere() {
        return dateenchere;
    }

    public void setDateenchere(Timestamp dateenchere) {
        this.dateenchere = dateenchere;
    }
    
    
}
