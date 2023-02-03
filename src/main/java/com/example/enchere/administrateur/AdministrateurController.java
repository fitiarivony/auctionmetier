/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.administrateur;

import com.example.enchere.categorie.Categorie;
import com.example.enchere.utilisateur.Utilisateur;
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
import utils.SHA1;
import utils.SuccessResponse;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="admin")
public class AdministrateurController {
      @GetMapping
    public Response listAdmin() {
    
        Object[]admin=new Object[0];
        try {
            admin=(new Administrateur()).selectAll(new Connect());
            
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(admin);
    }

    @PostMapping
    public Response insertAdmin(@RequestBody Administrateur admin){
        try{
           // System.out.println(enchere);
           admin.setMdp(new SHA1(admin.getMdp()).getSha1());
          admin.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(admin);
    }
    

    @DeleteMapping("{idadministrateur}")
    public Response deleteutilisateur(@PathVariable("idadministrateur") String id){
        Administrateur admin=new Administrateur();
        admin.setIdadministrateur(id);
        try {
           admin.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(admin);
    }

    @PutMapping("{idadministrateur}")
    public Response updateAdministrateur(@PathVariable("idadministrateur") String id,@RequestBody Administrateur admin){
        admin.setIdadministrateur(id);
        try{
           admin.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(admin);
    }
    
    
      @PostMapping("/login")
    public Response loginUser(@RequestBody Administrateur admin){
        Connect conn=null;
        try{
           conn=new Connect();
          
           admin.setMdp(new SHA1(admin.getMdp()).getSha1());
          admin.login(conn);
        }catch (Exception e){
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
        return new SuccessResponse(admin);
    }
    
   @GetMapping("preparelogout")
    public Response prepareLogout(@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        Connect conn=null;
         Administrateur admin=new Administrateur();
        try{
            
             conn=new Connect();
                conn.setuses(true);
                admin.setToken(token);
                admin=admin.checkConnex(conn);
           // System.out.println(enchere);
          
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(admin);
    }
    
    
    
      @GetMapping("/logout/{idadministrateur}")
    public Response logout(@PathVariable String idadministrateur){
        Connect conn=null;
        Administrateur admin=new Administrateur();
        try{
           conn=new Connect();
          
          admin.setIdadministrateur(idadministrateur);
          admin.logout();
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally {
    		try {
				conn.forceClose();
			} catch (SQLException e) {
				//e.printStackTrace();
				return new ErrorResponse("201",e.getMessage());
			}
    	}
        return new SuccessResponse(admin);
    }
}
