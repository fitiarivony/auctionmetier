/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.categorie;

import com.example.enchere.administrateur.Administrateur;
import com.example.enchere.enchere.Enchere;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
@RequestMapping(path="categorie")
public class CategorieController {
     @GetMapping
    public Response listCategories() {
    
        Object[]categorie=new Object[0];
        try {
            categorie=(new Categorie()).selectAll(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(categorie);
    }

    @PostMapping
    public Response insertCategorie(@RequestBody Categorie categorie,@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        Connect conn=null;
        try{
             Administrateur admin=new Administrateur();
             conn=new Connect();
                conn.setuses(true);
                admin.setToken(token);
                admin=admin.checkConnex(conn);
           // System.out.println(enchere);
          categorie.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
               return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(categorie);
    }

    @DeleteMapping("{idcategorie}")
    public Response deleteCategorie(@PathVariable("idcategorie") String id){
        Categorie cat=new Categorie();
        cat.setIdcategorie(id);
        try {
           cat.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(cat);
    }

    @PutMapping("{idcategorie}")
    public Response udpatecategorie(@PathVariable("idcategorie") String id,@RequestBody Categorie categorie,@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        categorie.setIdcategorie(id);
        Connect conn=null;
        try{
              conn=new Connect();
              Administrateur admin=new Administrateur();
             conn=new Connect();
                conn.setuses(true);
                admin.setToken(token);
                admin=admin.checkConnex(conn);
           categorie.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(categorie);
    }
    
}
