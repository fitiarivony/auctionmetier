/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.sary;

import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="sary_produit")
public class Sary_Produit extends BddObject{
    @Attribut(attr="idproduit")
    String idproduit;
    @Attribut(attr="idsary")
    String idsary;

    public String getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(String idproduit) {
        this.idproduit = idproduit;
    }

    public String getIdsary() {
        return idsary;
    }

    public void setIdsary(String idsary) {
        this.idsary = idsary;
    }
    public Sary_Produit ajouterPhoto(String basesary,Connect conn) throws Exception {
        try {
            conn.setuses(true);
            conn.setAutoCommit(false);
            Sary sary=new Sary();
            sary.setBasesary(basesary);
            
            if(!sary.insert(conn)){
                conn.rollback();
                throw new Exception("Sary produit non inserer");
                
            }
            this.setIdsary(GenericDAO.getLastIdString(sary, conn));
            System.out.println(this);
            if(this.insert(conn)){
                conn.commit();
                return this;
            }
            conn.rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
           conn.rollback();
        }
        throw new Exception("Sary produit non inserer");
    }

    @Override
    public String toString() {
        return "Sary_Produit{" + "idproduit=" + idproduit + ", idsary=" + idsary + '}';
    }
    
}
