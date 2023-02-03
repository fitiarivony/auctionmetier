/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.example.enchere.produit.Detail_catprod;
import com.example.enchere.sary.Detail_saryprod;

/**
 *
 * @author FITIA ARIVONY
 */
public class ObjectHelper {
   public static Detail_saryprod[] convertSary(Object[]sary){
       Detail_saryprod[] pics=new Detail_saryprod[sary.length];
     for(int i=0;i<pics.length;i++)pics[i]=(Detail_saryprod)sary[i];
     return pics;
   }
     public static Detail_catprod[] convertCat(Object[]sary){
       Detail_catprod[] pics=new Detail_catprod[sary.length];
     for(int i=0;i<pics.length;i++)pics[i]=(Detail_catprod)sary[i];
     return pics;
   }
}
