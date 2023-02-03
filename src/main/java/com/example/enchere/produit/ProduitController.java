/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.produit;

import com.example.enchere.unite.Unite;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.Connect;
import utils.ErrorResponse;
import utils.Response;
import utils.SuccessResponse;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="produit")
public class ProduitController {
    @GetMapping
    public Response listProduit() {
    
        Object[]produit=new Object[0];
        try {
            produit=(new Produit()).selectAll(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(produit);
    }
    
     @GetMapping("dispo/{idutilisateur}")
    public Response getProduitDispo(@PathVariable String idutilisateur) {
    
        Object[]produits=new Object[0];
        Connect conn=null;
        try {
            conn=new Connect();
            Produit produit=new Produit();
           produit.setIdutilisateur(idutilisateur);
            produits=produit.getProduitDispo(conn);
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(produits);
    }

    @PostMapping
    public Response insertProduit(@RequestBody Produit produit){
        try{
           // System.out.println(enchere);
            produit.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(produit);
    }
    
     @PostMapping("insert")
    public Response insertProduct(@RequestBody Produit_insert produit){
      Connect conn=null;
        try{
            conn=new Connect();
           // System.out.println(enchere);
            produit.insert(conn);
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally{
          try {
              conn.forceClose();
          } catch (SQLException ex) {
              return new ErrorResponse("201",ex.getMessage());
          }
        }
        return new SuccessResponse(produit);
    }

    @DeleteMapping("{idproduit}")
    public Response deleteProduit(@PathVariable("idproduit") String id){
        Produit produit=new Produit();
        produit.setIdproduit(id);
        try {
           produit.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(produit);
    }

    @PutMapping("{idproduit}")
    public Response updateProduit(@PathVariable("idproduit") String id,@RequestBody Produit produit){
        produit.setIdproduit(id);
        try{
           produit.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(produit);
    }
    
}
