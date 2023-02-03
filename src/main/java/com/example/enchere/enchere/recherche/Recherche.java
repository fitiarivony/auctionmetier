/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.enchere.recherche;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author FITIA ARIVONY
 */
public class Recherche {
    String motcle;
    String[]categorie;
    Timestamp debut;
    Timestamp fin;
    Integer etat;
    Double prixmax;
    Double prixmin;
    private int counter;

    public int getCounter() {
        return counter;
    }
    

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getMotcle() {
        return motcle;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    public String[] getCategorie() {
        return categorie;
    }

    public void setCategorie(String[] categorie) {
        this.categorie = categorie;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Double getPrixmax() {
        return prixmax;
    }

    public void setPrixmax(Double prixmax) {
        this.prixmax = prixmax;
    }

    public Double getPrixmin() {
        return prixmin;
    }

    public void setPrixmin(Double prixMin) {
        this.prixmin = prixMin;
    }
  public  String getWhere(){
        if(this.getCounter()==0){
            this.setCounter(this.getCounter()+1);
            return " where ";       
        }
        this.setCounter(this.getCounter()+1);
        return " and ";
  }
    public String generateMotCle(){
       
        if(this.getMotcle()!=null && !this.getMotcle().equalsIgnoreCase("")){
             String where=this.getWhere();
            return where+" lower(descri) like '%"+this.getMotcle().toLowerCase()+"%'";
            
        }
        return "";
    }
    public String generateCategorie(){
        
        String sql="";
        if(this.getCategorie()!=null && this.getCategorie().length!=0){
              String where=this.getWhere();
              sql=where+" idcategorie in (";
            for(int i=0;i<this.getCategorie().length;i++){
             sql+="'"+this.getCategorie()[i]+"'";
             if(i==this.getCategorie().length-1){
                 sql+=")";
             }else{
                 sql+=",";
             }
            }
            
        }
        return sql;
    }
    public String generateDebutEnchere(){
        if(this.getDebut()!=null){
           
             String where=this.getWhere();
          SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSXXX",Locale.getDefault());
            return where+"dateenchere>='"+format.format(this.getDebut())+"'::timestamptz";
        }
        return "";
    }
     public String generateFinEnchere(){
        if(this.getDebut()!=null){
              String where=this.getWhere();
          SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSXXX",Locale.getDefault());
            return where+"dateenchere<='"+format.format(this.getFin())+"'::timestamptz";
        }
        return "";
    }
     public String generateStatus(){
         return this.getWhere()+"etat="+this.getEtat();
     }
     public String generateMin(){
         if(this.getPrixmin()!=null){
             return this.getWhere()+"prixenchere>="+this.getPrixmin();
         }
         return "";
     }
       public String generateMax(){
         if(this.getPrixmax()!=null){
             return this.getWhere()+"prixenchere<="+this.getPrixmax();
         }
         return "";
     }
      
     public String generateSQL(){
         this.setCounter(0);
         String sql="SELECT * FROM recherche_avancee"+this.generateCategorie()+this.generateDebutEnchere()
                 +this.generateFinEnchere()+this.generateMax()
                 +this.generateMin()+this.generateMotCle()
                 +this.generateStatus();
         return sql;
     }
    
}
