/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.comission;

import dao.BddObject;
import frame.AnnotMap;
import frame.Attribut;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="comission")
public class Comission extends BddObject{
    @Attribut(attr="idcommission",primary_key=true)
    String idcomission;
    @Attribut(attr="valeurcommission")
    Double valeurcomission;

    public String getIdcomission() {
        return idcomission;
    }

    public void setIdcomission(String idcomission) {
        this.idcomission = idcomission;
    }

    public Double getValeurcomission() {
        return valeurcomission;
    }

    public void setValeurcomission(Double valeurcomission) {
        this.valeurcomission = valeurcomission;
    }
    
}
