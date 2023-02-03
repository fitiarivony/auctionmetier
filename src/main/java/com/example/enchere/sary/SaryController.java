/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.sary;

import com.example.enchere.enchere.Enchere;
import com.example.enchere.unite.Unite;
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
@RequestMapping(path="sary")
public class SaryController {
     @PutMapping("produit/{idproduit}")
    public Response addPhotoProduit(@RequestBody String basesary,@PathVariable String idproduit) {
        Sary_Produit sary=new Sary_Produit();
        Connect conn=null;
        try {
            conn=new Connect();
            conn.setuses(true);
            sary.setIdproduit(idproduit);
            sary.ajouterPhoto(basesary, conn);
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (Exception e) {
                return new ErrorResponse("201",e.getMessage());
            }
        }
        return new SuccessResponse(sary);
    }
     @GetMapping
    public Response listSary() {
    
        Object[]sarys=new Object[0];
        try {
            sarys=(new Sary()).selectAll(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(sarys);
    }

    @PostMapping
    public Response insertSary(@RequestBody Sary sary){
        try{
           // System.out.println(enchere);
            sary.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(sary);
    }

    @DeleteMapping("{idsary}")
    public Response deleteSary(@PathVariable("idsary") String id){
        Sary sary=new Sary();
        sary.setIdsary(id);
        try {
           sary.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(sary);
    }

    @PutMapping("{idsary}")
    public Response updateUnite(@PathVariable("idsary") String id,@RequestBody Sary sary){
        sary.setIdsary(id);
        try{
           sary.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(sary);
    }

}
