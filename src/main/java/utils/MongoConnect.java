/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author FITIA ARIVONY
 */
public class MongoConnect {
    public MongoDatabase getConnexionMongodb() throws Exception {
        //String url = "mongodb://mongo:c20AUdZgBjj7AkSKNcaz@containers-us-west-190.railway.app:7394";
        String url = "mongodb+srv://operateur:op123456@operateurclusteur.gada3.mongodb.net/operateurcluster?retryWrites=true&w=majority";
        MongoClient mongo = null;
        MongoDatabase database = null;
        try {
        	mongo = MongoClients.create(url);
        	database = mongo.getDatabase("test");
        	//database = mongo.getDatabase("operateurdata");
        	System.out.println("Opened MongoDb database successfully");
        } catch(Exception ex) {
	    	throw ex;
	    }
	    return database;
    }
}
