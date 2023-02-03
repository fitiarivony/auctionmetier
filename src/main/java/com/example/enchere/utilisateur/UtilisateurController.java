/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.utilisateur;

import com.example.enchere.administrateur.Administrateur;
import com.example.enchere.categorie.Categorie;
import com.example.enchere.enchere.Detail_enchere;
import com.example.enchere.enchere.Enchere;
import java.sql.SQLException;
import java.util.ArrayList;
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
import utils.HandleNotif;
import utils.Response;
import utils.SHA1;
import utils.SuccessResponse;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="utilisateur")
public class UtilisateurController {
     @GetMapping
    public Response listUtilisateurs() {
    
        Object[]utilisateur=new Object[0];
        try {
            utilisateur=(new Utilisateur()).selectAll(new Connect());
            
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(utilisateur);
    }

    @PostMapping
    public Response insertUtilisateur(@RequestBody Utilisateur utilisateur){
        try{
           // System.out.println(enchere);
           utilisateur.setMdp(new SHA1(utilisateur.getMdp()).getSha1());
          utilisateur.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(utilisateur);
    }
    

    @DeleteMapping("{idutilisateur}")
    public Response deleteutilisateur(@PathVariable("idutilisateur") String id){
        Utilisateur util=new Utilisateur();
        util.setIdutilisateur(id);
        try {
           util.delete(new Connect());
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(util);
    }

    @PutMapping("{idutilisateur}")
    public Response updateUtilisateur(@PathVariable("idutilisateur") String id,@RequestBody Utilisateur utilisateur){
        utilisateur.setIdutilisateur(id);
        try{
           utilisateur.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(utilisateur);
    }
    
    
      @PostMapping("/login")
    public Response loginUser(@RequestBody Utilisateur utilisateur){
        Connect conn=null;
        try{
           conn=new Connect();
           utilisateur.setMdp(new SHA1(utilisateur.getMdp()).getSha1());
          utilisateur.login(conn);
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
        return new SuccessResponse(utilisateur);
    }
      @PostMapping("/logout/{idutilisateur}")
    public Response logout(@PathVariable String idutilisateur){
        Connect conn=null;
        Utilisateur util=new Utilisateur();
        try{
           conn=new Connect();
          
          util.setIdutilisateur(idutilisateur);
          util.logout();
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
        return new SuccessResponse(util);
    }
    
    
    @GetMapping("/notifonconnect/{idutilisateur}")
    public Response notifonconnect(@PathVariable String idutilisateur) {
        Connect conn = null;
        Utilisateur util = new Utilisateur();
        ArrayList<Detail_enchere> retour=null;
        try {
            conn = new Connect();
            conn.setuses(true);
            // call listEnchereLastDeconnect
            util.setIdutilisateur(idutilisateur);
            retour = util.listEnchereLastDeconnect(conn);

            // send notification of toStringEnchereLastDeconnect
            String bodynotif=util.toStringListDetail_enchere(retour);
            HandleNotif.sendNotification(bodynotif,idutilisateur);

            // call updateEtatEnchereLastDeconnect
            util.updateEtatEnchereLastDeconnect(conn);

        } catch (Exception e) {
            return new ErrorResponse("201", e.getMessage());
        } finally {
            try {
                conn.forceClose();
            } catch (SQLException e) {
                e.printStackTrace();
                return new ErrorResponse("201", e.getMessage());
            }
        }
        return new SuccessResponse("cool");
    }
    
    
     @GetMapping("/notification/{idutilisateur}")
    public Response listnotification(@PathVariable("idutilisateur") String idutilisateur) {
    
       Connect conn=null;
        Utilisateur util=new Utilisateur();
        ArrayList<Detail_enchere> retour=null;
        try{
           conn=new Connect();
                     Utilisateur user=new Utilisateur();
             conn=new Connect();
                conn.setuses(true);
            // call listEnchereLastDeconnect
            util.setIdutilisateur(idutilisateur);
            retour = util.encheresOverClient(conn);

            // send notification of toStringEnchereLastDeconnect
            String bodynotif=util.toStringListDetail_enchere(retour);
            HandleNotif.sendNotification(bodynotif,idutilisateur);

            // call updateEtatEnchereLastDeconnect
            util.updateEtatEnchereOverClient(conn);
          
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally {
    		try {
				conn.forceClose();
			} catch (SQLException e) {
				e.printStackTrace();
				return new ErrorResponse("201",e.getMessage());
			}
    	}
        return new SuccessResponse(retour);
    }
    
     @GetMapping("preparelogout")
    public Response prepareLogout(@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        Connect conn=null;
         Utilisateur util=new Utilisateur();
        try{
            
             conn=new Connect();
                conn.setuses(true);
                util.setToken(token);
                util=util.checkConnex(conn);
           // System.out.println(enchere);
          
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(util);
    }
    
    @GetMapping("getvariation/{idutilisateur}")
    public Response getVariation(@RequestHeader(HttpHeaders.AUTHORIZATION)String token,@PathVariable String idutilisateur){
        Connect conn=null;
         Utilisateur util=new Utilisateur();
         Variation_money[] money=new Variation_money[0];
        try{
            
             conn=new Connect();
                conn.setuses(true);
                util.setToken(token);
                util=util.checkConnex(conn);
                Variation_money vola=new Variation_money();
                vola.setIdutilisateur(idutilisateur);
                return new SuccessResponse(vola.getByIds(conn));
           // System.out.println(enchere);
          
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally {
    		try {
				conn.forceClose();
			} catch (SQLException e) {
				e.printStackTrace();
				return new ErrorResponse("201",e.getMessage());
			}
    	}
     
}
    
}
