package com.example.enchere.enchere;

import com.example.enchere.enchere.recherche.Recherche;
import com.example.enchere.enchere.Enchere;
import com.example.enchere.enchere.recherche.Recherche_avancee;
import com.example.enchere.mouvement.Mouvement;
import com.example.enchere.utilisateur.Utilisateur;
import dao.GenericDAO;
import exception.OutOfConnectionException;
import org.springframework.web.bind.annotation.*;
import utils.Connect;
import utils.ErrorResponse;
import utils.Response;
import utils.SuccessResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="enchere")
public class EnchereController {
    @GetMapping
    public Response listEncheres() {
    
        Object[]enchere=new Object[0];
        Connect conn=null;
        try {
            conn=new Connect();
            conn.setuses(true);
            Utilisateur util=new Utilisateur();
            util.updateEtatEnchereOver(conn);
            enchere=(new Enchere()).selectAll(conn);
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
               ex.printStackTrace();
            return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(enchere);
    }
    
     
    @PostMapping
    public Response insertEnchere(@RequestBody Enchere enchere,@RequestHeader(HttpHeaders.AUTHORIZATION)String token){
        Connect conn=null;
        try{
           // System.out.println(enchere);
           Utilisateur user=new Utilisateur();
             conn=new Connect();
                conn.setuses(true);
                user.setToken(token);
                user.updateEtatEnchereOver(conn);
                user=user.checkConnex(conn);
          enchere.insert(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
               return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(enchere);
    }

    @DeleteMapping("{idenchere}")
    public Response deleteEnchere(@PathVariable("idenchere") String id){
        Enchere v=new Enchere();
        v.setIdenchere(id);
        Connect conn=null;
        try {
            conn=new Connect();
            conn.setuses(true);
               Utilisateur user=new Utilisateur();
                  user.updateEtatEnchereOver(conn);
           v.delete(conn);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(v);
    }

    @PutMapping("{idenchere}")
    public Response updateEnchere(@PathVariable("idenchere") String id,@RequestBody Enchere enchere){
        enchere.setIdenchere(id);
        Connect conn=null;
        try{
            conn=new Connect();
            conn.setuses(true);
             Utilisateur user=new Utilisateur();
                  user.updateEtatEnchereOver(conn);
           enchere.update(new Connect());
        }catch (Exception e){
            return new ErrorResponse("201",e.getMessage());
        }
        return new SuccessResponse(enchere);
    }
     @GetMapping("/details")
    public Response DetailEnchere() {
    
        Object[]enchere=new Object[0];
        Connect conn=null;
        try {
             conn=new Connect();
            conn.setuses(true);
             Utilisateur user=new Utilisateur();
                  user.updateEtatEnchereOver(conn);
            enchere=(new Detail_enchere()).selectAll(conn);
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                 return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(enchere);
    }
       @GetMapping("{idutilisateur}")
    public Response getHistorique(@PathVariable(value="idutilisateur") String id,@RequestHeader(HttpHeaders.AUTHORIZATION)String token) {
    	Connect conn=null;
    	try {
    		conn=new Connect();
                Utilisateur user=new Utilisateur();
                conn=new Connect();
                conn.setuses(true);
                user.setToken(token);
                user=user.checkConnex(conn);
    		Detail_enchere enc=new Detail_enchere();
                
                  user.updateEtatEnchereOver(conn);
                enc.setIdutilisateur(id);
    		return new SuccessResponse(enc.getHistoriqueEnchere(conn));	
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
    @PostMapping("/recherche")
    public Response recherche(@RequestBody Recherche rec){
        Connect conn=null;
        try {
    	conn=new Connect();
        conn.setuses(true);
        Utilisateur user=new Utilisateur();
         user.updateEtatEnchereOver(conn);
        return new SuccessResponse(new Recherche_avancee().recherche_avancee(rec, conn));
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
    @GetMapping("/fiche/{idenchere}")
     public Response ficheenchere(@PathVariable(value="idenchere") String idenchere){
        Connect conn=null;
        try {
    	conn=new Connect();
        Fiche_Enchere fiche=new Fiche_Enchere();
        fiche.setIdenchere(idenchere);
        conn.setuses(true);
        Utilisateur user=new Utilisateur();
        user.updateEtatEnchereOver(conn);
        fiche.initFiche(conn);
        
        return new SuccessResponse(fiche);
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
     @GetMapping("/encours")
    public Response Encours() {
    
        Object[]enchere=new Object[0];
        Connect conn=null;
        try {
            conn=new Connect();
            conn.setuses(true);
            Detail_enchere detail=new Detail_enchere();
            Utilisateur user=new Utilisateur();
            user.updateEtatEnchereOver(conn);
            detail.setEtat(0);
            enchere=detail.getByIds(conn);
            
        }catch(Exception e) {
            e.printStackTrace();
            return new ErrorResponse("201",e.getMessage());
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                return new ErrorResponse("201",ex.getMessage());
            }
        }
        return new SuccessResponse(enchere);
    }
     
     
}
