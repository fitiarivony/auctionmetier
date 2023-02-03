/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.utilisateur;

import com.example.enchere.enchere.Detail_enchere;
import com.example.enchere.enchere.Enchere;
import dao.BddObject;
import dao.GenericDAO;
import exception.OutOfConnectionException;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connect;
import utils.SHA1;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="utilisateur")
public class Utilisateur extends BddObject{
    @Attribut(attr="idutilisateur",primary_key=true)
    String idutilisateur;
    @Attribut(attr="nom")
    String nom;
    @Attribut(attr="prenom")
    String prenom;
    @Attribut(attr="identifiant")
    String identifiant;
    @Attribut(attr="mdp")
    String mdp;
    @Attribut(attr="datenaissance")
    Date datenaissance;
    @Attribut(attr="token")
    String token;
    @Attribut(attr="dateexpiration")
    Timestamp dateexpiration;
    @Attribut(attr="datedeconnect")
    Timestamp datedeconnect;
   

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        
        this.mdp = mdp;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

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
    public Timestamp getDatedeconnect() {
        return datedeconnect;
    }

    public void setDatedeconnect(Timestamp datedeconnect) {
        this.datedeconnect = datedeconnect;
    }
      public Utilisateur login(Connect conn) throws Exception {
      
        try {
            if(this.getByIds(conn).length==0)throw new Exception("Vous n'etes pas un utilisateur");
           Utilisateur util=(Utilisateur)this.getByIds(conn)[0];
           this.setIdutilisateur(util.getIdutilisateur());
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
        String sql = "update utilisateur set token='' where idutilisateur=?";

        try{
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, this.getIdutilisateur());
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
            String sql="update utilisateur set dateexpiration=current_timestamp+'01:00:00' where idutilisateur='"+this.getIdutilisateur()+"'";
            System.out.println(sql);
            stat=conn.createStatement();
            stat.executeUpdate(sql);
            
            Utilisateur obj=(Utilisateur)this.getById(conn).get(0);
            
            
            String currentexpiration=obj.getDateexpiration().toString();
            
            String newtoken=new String();
            SHA1 sha=new SHA1(this.getIdentifiant()+currentexpiration);
            newtoken=sha.getSha1();
            this.setToken(newtoken);
            this.setDateexpiration(obj.getDateexpiration());
            String sql1="update utilisateur set token='"+newtoken+"' where idutilisateur='"+this.getIdutilisateur()+"'";
            
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
      public Utilisateur checkConnex(Connect conn)throws Exception{
    	String sql="SELECT * from utilisateur where  token=? and dateexpiration>current_timestamp";
    	PreparedStatement stat=null;
    	try {
    		
    		conn.getConnection();
    		stat=conn.prepareStatement(sql);
                 this.setToken(this.getToken().replace("Bearer ", ""));
    		stat.setString(1,this.getToken());
    		System.out.println(stat);
    		ArrayList<Object>liste=GenericDAO.executeQuery(stat.toString(), conn, new Utilisateur());
    		if(liste.isEmpty())throw new OutOfConnectionException("Connexion expiree",230);
    		Utilisateur util=(Utilisateur)liste.get(0);
    		return util;
    	}finally {
    		if(stat!=null)stat.close();
    		conn.close();
    	}
    	
    }
    
      
     //    get efa tapitra where etat not updated yet admin
    public ArrayList<Detail_enchere> encheresOverNotUpdated(Connect conn)throws Exception{
        ArrayList<Detail_enchere> retour=new ArrayList<>();
        Detail_enchere de=new Detail_enchere();
        //de.setIdutilisateur(this.getIdutilisateur());
        de.setEtat(0);
        Object[] list=de.getByIds(conn);
        for(Object ob:list){
            Detail_enchere detail=(Detail_enchere)ob;
            Calendar cal = Calendar.getInstance();
            System.out.println(detail.getDateenchere());
            cal.setTime(detail.getDateenchere());
            System.out.println("Calendar: " + cal.getTime());

            // if dureenchere minute
            cal.add(Calendar.MINUTE, detail.getDureeenchere().intValue());
            // if dureenchere hour
//            double d=0d;
//            cal.add(Calendar.HOUR, (int) d);
//            cal.add(Calendar.MINUTE, (int) ((d - (int) d) * 60));
            Date daty = cal.getTime();
            System.out.println(daty);

            Date currentDate = new Date();
            // check if tapitra enchere
            if(daty.compareTo(currentDate)<0){
                Enchere e=new Enchere();
                e.setIdenchere(detail.getIdenchere());
                retour.add(detail);
            }
        }
        return retour;
    }
    // by idutilisateur
    public ArrayList<Detail_enchere> encheresOverClient(Connect conn)throws Exception{
        ArrayList<Detail_enchere> retour=new ArrayList<>();
        Detail_enchere de=new Detail_enchere();
        de.setIdutilisateur(this.getIdutilisateur());
        de.setEtat(0);
        Object[] list=de.getByIds(conn);
        for(Object ob:list){
            Detail_enchere detail=(Detail_enchere)ob;
            Calendar cal = Calendar.getInstance();
            cal.setTime(detail.getDateenchere());

            // if dureenchere minute
            cal.add(Calendar.MINUTE, detail.getDureeenchere().intValue());
            Date daty = cal.getTime();

            Date currentDate = new Date();
            // check if tapitra enchere
            if(daty.compareTo(currentDate)<0){
                Enchere e=new Enchere();
                e.setIdenchere(detail.getIdenchere());
                retour.add(detail);
            }
        }
        return retour;
    }
    public void updateEtatEnchereOverClient(Connect conn)throws Exception{
        ArrayList<Detail_enchere> list=encheresOverClient(conn);
        for(Detail_enchere de:list){
            Enchere e=new Enchere();
            e.setIdenchere(de.getIdenchere());
            e=(Enchere) GenericDAO.getById(e,conn).get(0);
            e.setEtat(1);
            de.insertTri(conn);
            e.update(conn);
        }
    }

    //    update
    public void updateEtatEnchereOver(Connect conn)throws Exception{
        ArrayList<Detail_enchere> list=encheresOverNotUpdated(conn);
        for(Detail_enchere de:list){
            Enchere e=new Enchere();
            e.setIdenchere(de.getIdenchere());
            e=(Enchere) GenericDAO.getById(e,conn).get(0);
            e.setEtat(1);
            de.insertTri(conn);
            e.update(conn);
        }
    }

    //    notif 10 farany
    public ArrayList<Detail_enchere> last10EnchereTapitra(Connect conn)throws Exception{
        Detail_enchere enchere=new Detail_enchere();
        enchere.setIdutilisateur(this.getIdutilisateur());
        this.updateEtatEnchereOver(conn);
        enchere.setEtat(1);
        
        Object[] list=enchere.getByIds(conn);
        ArrayList<Detail_enchere> listEnchere=new ArrayList<>();
        for(Object o:list){listEnchere.add((Detail_enchere) o);}
        Detail_enchere.sort(listEnchere);
        
        if (listEnchere.size() > 10) {
            listEnchere.subList(10, listEnchere.size()).clear();
        }
        return  listEnchere;
    }

    // list enchere efa tapitra fa mbola tsy updated entre last deconnect & now
    public ArrayList<Detail_enchere> listEnchereLastDeconnect(Connect conn)throws Exception{
        String datefinenchere="dateenchere + interval '1 minute'* dureeenchere";
        String sql="select * from detail_enchere where "+datefinenchere+">=datedeconnect and "+datefinenchere+"<=current_timestamp";
        ArrayList list = GenericDAO.executeQuery(sql,conn,new Detail_enchere());
        ArrayList<Detail_enchere> listEnchere=new ArrayList<>();
        for(Object obj:list)listEnchere.add((Detail_enchere) obj);
        return  listEnchere;
    }
    public String toStringListDetail_enchere(ArrayList<Detail_enchere> list)throws Exception{
        String retour="";
        for(Detail_enchere de:list){
            Calendar cal = Calendar.getInstance();
            cal.setTime(de.getDateenchere());
            cal.add(Calendar.MINUTE, de.getDureeenchere().intValue());
            Date datefin = cal.getTime();
            String info=de.getIdenchere()+" produit "+de.getNomproduit()+" finished on "+datefin+"\n";
            retour+=info;
        }
        return retour;
    }
    public void updateEtatEnchereLastDeconnect(Connect conn)throws Exception{
        ArrayList<Detail_enchere> list=listEnchereLastDeconnect(conn);
        for(Detail_enchere de:list){
            Enchere e=new Enchere();
            e.setIdenchere(de.getIdenchere());
            e=(Enchere) GenericDAO.getById(e,conn).get(0);
            e.setEtat(1);
            de.insertTri(conn);
            e.update(conn);
        }
    }


      
    
}
