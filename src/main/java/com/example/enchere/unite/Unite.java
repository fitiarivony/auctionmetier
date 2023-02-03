/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.unite;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="unite")
public class Unite extends BddObject{
    @Attribut(attr="idunite",primary_key=true)
    String idunite;
    @Attribut(attr="nomunite")
    String nomunite;

    public String getIdunite() {
        return idunite;
    }

    public void setIdunite(String idunite) {
        this.idunite = idunite;
    }

    public String getNomunite() {
        return nomunite;
    }

    public void setNomunite(String nomunite) {
        this.nomunite = nomunite;
    }
    
}
