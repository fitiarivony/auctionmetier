/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.enchere.sary;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import java.sql.SQLException;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.Connect;
import utils.MongoConnect;

/**
 *
 * @author FITIA ARIVONY
 */
@AnnotMap(nomTable="sary")
public class Sary extends BddObject{
    @Attribut(attr="idsary",primary_key=true)
    String idsary;
    @Attribut(attr="basesary")
    String basesary;

    public String getIdsary() {
        return idsary;
    }

    public void setIdsary(String idsary) {
        this.idsary = idsary;
    }

    public String getBasesary() {
        return basesary;
    }

    public void setBasesary(String basesary) {
        this.basesary = basesary;
    }
    
    
    @Override
    public boolean insert(Connect conn) throws SQLException, Exception {
       conn.setuses(true);
        conn.setAutoCommit(false);
       
        if(!super.insert(conn)){
            conn.rollback();
            throw new Exception("Sary produit non inserer");
        }
        this.setIdsary(GenericDAO.getLastIdString(this, conn));
        
//        Document sary = new Document("_id", new Sary());
	MongoDatabase database = null;
        MongoCursor<Document> cursor = null;	
        ArrayList res = new ArrayList();
        
        try {
            database = new MongoConnect().getConnexionMongodb();
            MongoCollection<Document> collection = database.getCollection("sary");
            Document insert = new Document();
            insert.append("idsary", this.getIdsary())
            .append("basesary", this.getBasesary());
            collection.insertOne(insert);
             conn.commit();
        return true;
        } catch(Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            if(cursor!=null) cursor.close();
            conn.close();
        }
    }
    
    @Override
    public Object[] selectAll(Connect conn) throws Exception{
        MongoDatabase database = null;
        MongoCursor<Document> cursor = null;
        ArrayList res = new ArrayList();

        try {
            database = new MongoConnect().getConnexionMongodb();
            MongoCollection<Document> collection = database.getCollection("sary");
            /*Document filter = new Document();
            filter.append("numappelant", numClient);*/
            FindIterable<Document> iterDoc = collection.find();
            cursor = iterDoc.iterator(); 
            
            while(cursor.hasNext()){
                res.add(cursor.next());
            }
        } catch(Exception ex) {
            throw ex;
        } finally {
            if(cursor!=null) cursor.close();
        }
        
        return res.toArray();
    }
    
    @Override
    public boolean update(Connect conn) throws Exception{
        conn.setuses(true);
        conn.setAutoCommit(false);
       
        if(!super.update(conn)){
            conn.rollback();
            throw new Exception("Sary produit non updater");
        }
        
//        Document sary = new Document("_id", new Sary());
	MongoDatabase database = null;
        MongoCursor<Document> cursor = null;	
        ArrayList res = new ArrayList();
        
        try {
            database = new MongoConnect().getConnexionMongodb();
            MongoCollection<Document> collection = database.getCollection("sary");
            Bson filter = Filters.eq("idsary", this.getIdsary());
            Bson update = Updates.set("basesary", this.getBasesary());

            UpdateResult updateResult = collection.updateMany(filter, update);
             conn.commit();
        return true;
        } catch(Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            if(cursor!=null) cursor.close();
            conn.forceClose();
        }
    }
    @Override
    public boolean delete(Connect conn) throws Exception{
        conn.setuses(true);
        conn.setAutoCommit(false);
       
        if(!super.delete(conn)){
            conn.rollback();
            throw new Exception("Sary produit non deleter");
        }
//        Document sary = new Document("_id", new Sary());
	MongoDatabase database = null;
        MongoCursor<Document> cursor = null;	
        ArrayList res = new ArrayList();
        
        try {
            database = new MongoConnect().getConnexionMongodb();
            MongoCollection<Document> collection = database.getCollection("sary");
            Bson filter = Filters.eq("idsary", this.getIdsary());

            
            DeleteResult deleteResult = collection.deleteMany(filter);
             conn.commit();
        return true;
        } catch(Exception ex) {
            conn.rollback();
            throw ex;
        } finally {
            if(cursor!=null) cursor.close();
            conn.forceClose();
        }
    }
//    
//    public static void main(String[] gygy)throws Exception{
//        Sary s=new Sary();
//        s.setIdsary("SAR7");
//        s.setBasesary("titi");
////        s.insert(new Connect());
//        s.delete(new Connect());
//    }
    
}
