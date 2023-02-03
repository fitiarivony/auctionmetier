/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.administrateur;

import com.example.enchere.utilisateur.Utilisateur;
import dao.BddObject;
import dao.GenericDAO;
import exception.OutOfConnectionException;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.Connect;
import utils.SHA1;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="administrateur")
public class Administrateur extends BddObject{
    @Attribut(attr="idadministrateur",primary_key=true)
    String idadministrateur;
    @Attribut(attr="mdp")
    String mdp;
    @Attribut(attr="identifiant")
    String identifiant;
    @Attribut(attr="token")
    String token;
    @Attribut(attr="dateexpiration")
    Timestamp dateexpiration;

    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Timestamp dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    
    public String getIdadministrateur() {
        return idadministrateur;
    }

    public void setIdadministrateur(String idadministrateur) {
        this.idadministrateur = idadministrateur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }
    public Administrateur login(Connect conn) throws Exception {
      
        try {
            Object[]obj=this.getByIds(conn);
            
            if(obj.length==0)throw new Exception("Vous n'etes pas un administrateur");
           Administrateur admin=(Administrateur)obj[0];
           this.setIdadministrateur(admin.getIdadministrateur());
           this.setToken();
           return this;
        }finally {
            conn.close();
        }
        
    }
    public void logout() throws Exception{
        Connect connection = new Connect();
        connection.getConnection();
        PreparedStatement pStatement = null;
        String sql = "update administrateur set token='' where idadministrateur=?";

        try{
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, this.getIdadministrateur());
            int ta=pStatement.executeUpdate();
            if(ta==0){throw new Exception();}
        }catch (Exception e){
            throw e;
        }finally{
            if(pStatement != null)pStatement.close();
            connection.close();
        }
    }
    public void setToken() throws Exception{
        Connect conn=null;
        Statement stat=null;
        try {
            conn=new Connect();
            conn.setuses(true);
            String sql="update administrateur set dateexpiration=current_timestamp+'01:00:00' where idadministrateur='"+this.getIdadministrateur()+"'";
            System.out.println(sql);
            stat=conn.createStatement();
            stat.executeUpdate(sql);
            
            Administrateur obj=(Administrateur)this.getById(conn).get(0);
            
            
            String currentexpiration=obj.getDateexpiration().toString();
            
            String newtoken=new String();
            SHA1 sha=new SHA1(this.getIdentifiant()+currentexpiration);
            newtoken=sha.getSha1();
            this.setToken(newtoken);
            this.setDateexpiration(obj.getDateexpiration());
            String sql1="update administrateur set token='"+newtoken+"' where idadministrateur='"+this.getIdadministrateur()+"'";
            
            stat=conn.createStatement();
            System.out.println(sql1);
            stat.executeUpdate(sql1);
           
            
        } catch (Exception ex) {
            throw ex;
        }finally{
            if(stat!=null)stat.close();
            conn.close();
        }
       

    }
      public Administrateur checkConnex(Connect conn)throws Exception{
    	String sql="SELECT * from administrateur where  token=? and dateexpiration>current_timestamp";
    	PreparedStatement stat=null;
    	try {
    		
    		conn.getConnection();
    		stat=conn.prepareStatement(sql);
                this.setToken(this.getToken().replace("Bearer ", ""));
    		stat.setString(1,this.getToken());
    		System.out.println(stat);
    		ArrayList<Object>liste=GenericDAO.executeQuery(stat.toString(), conn, new Administrateur());
    		if(liste.isEmpty())throw new OutOfConnectionException("Connexion expiree",230);
    		Administrateur util=(Administrateur)liste.get(0);
    		return util;
    	}finally {
    		if(stat!=null)stat.close();
    		conn.close();
    	}
    	
    }
    
}
