/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.mouvement;

import com.example.enchere.enchere.Detail_enchere;
import com.example.enchere.enchere.Enchere;
import com.example.enchere.utilisateur.Utilisateur;
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
@RequestMapping(path="mouvement")
public class MouvementController {
     @GetMapping
    public Response listMouvements() {
    
        Object[]mouvement=new Object[0];
        try {
            mouvement=(new Mouvement()).selectAll(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(mouvement);
    }

    @PostMapping
    public Response insertMouvement(@RequestBody Mouvement mouvement,@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
      Connect conn=null;
        try{
           // System.out.println(enchere);
            Utilisateur user=new Utilisateur();
             conn=new Connect();
                conn.setuses(true);
                user.setToken(token);
                user=user.checkConnex(conn);
          mouvement.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(mouvement);
    }

    @DeleteMapping("{idmouvement}")
    public Response deleteMouvement(@PathVariable("idmouvement") String id){
        Mouvement v=new Mouvement();
        v.setIdmouvement(id);
        try {
           v.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(v);
    }

    @PutMapping("{idmouvement}")
    public Response updateMouvement(@PathVariable("idmouvement") String id,@RequestBody Mouvement mouvement){
        mouvement.setIdmouvement(id);
        try{
           mouvement.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(mouvement);
    }
    @GetMapping("/getNonValider")
    public Response listNonValide() {
    
        Object[]mouvement=new Object[0];
        try {
            Mouvement move=new Mouvement();
            move.setValide(0);
            mouvement=move.getByIds(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(mouvement);
    }
    
}
