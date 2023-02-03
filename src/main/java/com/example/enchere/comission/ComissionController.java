/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.comission;

import com.example.enchere.administrateur.Administrateur;
import com.example.enchere.categorie.Categorie;
import java.sql.SQLException;
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
@RequestMapping(path="comission")
public class ComissionController {
     @GetMapping
    public Response listcomission() {
    
        Object[]categorie=new Object[0];
        try {
            categorie=(new Comission()).selectAll(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(categorie);
    }

    @PostMapping
    public Response insertComission(@RequestBody Comission com,@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        Connect conn=null;
        try{
             Administrateur admin=new Administrateur();
             conn=new Connect();
                conn.setuses(true);
                admin.setToken(token);
                admin=admin.checkConnex(conn);
           // System.out.println(enchere);
          com.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
               return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(com);
    }

    @DeleteMapping("{idcomission}")
    public Response deleteComission(@PathVariable("idcomission") String id){
        Comission com=new Comission();
        com.setIdcomission(id);
        try {
           com.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(com);
    }

    @PutMapping("{idcomission}")
    public Response udpatecomission(@PathVariable("idcategorie") String id,@RequestBody Comission comission,@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        comission.setIdcomission(id);
        Connect conn=null;
        try{
              conn=new Connect();
              Administrateur admin=new Administrateur();
             conn=new Connect();
                conn.setuses(true);
                admin.setToken(token);
                admin=admin.checkConnex(conn);
           comission.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(comission);
    }
}
