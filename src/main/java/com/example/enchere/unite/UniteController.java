/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.unite;

import com.example.enchere.utilisateur.Utilisateur;
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
@RequestMapping(path="unite")
public class UniteController {
     @GetMapping
    public Response listUnite() {
    
        Object[]unite=new Object[0];
        try {
            unite=(new Unite()).selectAll(new Connect());
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(unite);
    }

    @PostMapping
    public Response insertUnite(@RequestBody Unite unite){
        try{
           // System.out.println(enchere);
            unite.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(unite);
    }

    @DeleteMapping("{idunite}")
    public Response deleteUnite(@PathVariable("idunite") String id){
        Unite unite=new Unite();
        unite.setIdunite(id);
        try {
           unite.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(unite);
    }

    @PutMapping("{idunite}")
    public Response updateUnite(@PathVariable("idunite") String id,@RequestBody Unite unite){
        unite.setIdunite(id);
        try{
           unite.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(unite);
    }
    
}
