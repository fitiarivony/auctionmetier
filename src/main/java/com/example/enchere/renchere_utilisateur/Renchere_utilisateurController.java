/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.renchere_utilisateur;

import com.example.enchere.administrateur.Administrateur;
import com.example.enchere.enchere.Detail_enchere;
import com.example.enchere.produit.Produit;
import com.example.enchere.utilisateur.Utilisateur;
import java.sql.SQLException;
import java.util.Date;
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
@RequestMapping(path="renchere_utilisateur")
public class Renchere_utilisateurController {
     @GetMapping
    public Response listRenchere() {
    
        Object[]renchere=new Object[0];
        try {
            renchere=(new Renchere_utilisateur()).selectAll(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(renchere);
    }

    @PostMapping
    public Response insertRenchere(@RequestBody Renchere_utilisateur renchere
           ,@RequestHeader(HttpHeaders.AUTHORIZATION)String token
    ){
      Connect conn=null;
        try{
             Utilisateur user=new Utilisateur();
             conn=new Connect();
             conn.setuses(true);
               user.setToken(token);
               user=user.checkConnex(conn);
               
           // System.out.println(enchere);
            renchere.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(renchere);
    }

    @DeleteMapping("{idrenchere}")
    public Response deleteRenchere(@PathVariable("idrenchere") String id){
        Renchere_utilisateur rechere=new Renchere_utilisateur();
        rechere.setIdenchere(id);
        try {
           rechere.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(rechere);
    }

    @PutMapping("{idrenchere}")
    public Response updateRenchere(@PathVariable("idrenchere") String id,@RequestBody Renchere_utilisateur renchere){
        renchere.setIdrenchere(id);
        try{
           renchere.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(renchere);
    }
     @GetMapping("/historique/{idenchere}")
    public Response getRenchere(@PathVariable("idenchere") String id){
      Connect conn=null;
    	try {
    		conn=new Connect();
    		Detail_renchere enc=new Detail_renchere();
                enc.setIdenchere(id);
    		return new SuccessResponse(enc.getHistoriqueRenchere(conn));	
    	}catch(Exception e) {
    		e.printStackTrace();
   		 return new ErrorResponse("201",e.getMessage());
    	}finally {
    		try {
				conn.forceClose();
			} catch (SQLException e) {
				//e.printStackTrace();
				return new ErrorResponse("201",e.getMessage());
			}
    	}
    }
    @GetMapping("gagnant/{idenchere}")
    public Response getRenchere_max(@PathVariable String idenchere){
        Connect conn=null;
        try{
            conn=new Connect();
            Renchere_utilisateur renchere=new Renchere_utilisateur();
            renchere.setIdenchere(idenchere);
            return new SuccessResponse(renchere.getMise_max(conn));
        }catch(Exception e) {
    		e.printStackTrace();
   		 return new ErrorResponse("201",e.getMessage());
    	}finally {
    		try {
				conn.forceClose();
			} catch (SQLException e) {
				//e.printStackTrace();
				return new ErrorResponse("201",e.getMessage());
			}
    	}
    }
}
