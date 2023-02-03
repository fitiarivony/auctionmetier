/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Connect;


/**
 *
 * @author FITIA ARIVONY
 */
public class BddObject  implements Serializable{
   
    public Object[] selectAll(Connect conn) throws Exception{
      
        ArrayList<Object> liste=GenericDAO.selectAll(this, conn);
        return liste.toArray();
    }
    public boolean insert(Connect conn) throws SQLException, Exception{
        boolean bool=GenericDAO.save(this, conn);
        conn.close();
        return bool;
    }
    public boolean update(Connect conn) throws Exception{
        return GenericDAO.update(this, conn);
    }
    
    public boolean delete(Connect conn) throws Exception{
        return GenericDAO.delete(this, conn);
       // return this.getTrait().delete(conn, this);
    }
   
    public ArrayList<Object> getById(Connect conn) throws Exception{
        return GenericDAO.getById(this, conn);
    }
    
      public Object[] getByIds(Connect conn) throws  Exception{
        return GenericDAO.getByIds(this, conn);
    }
       
   
  
}
