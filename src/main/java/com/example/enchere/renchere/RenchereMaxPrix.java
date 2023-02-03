package com.example.enchere.renchere;

import dao.BddObject;
import dao.GenericDAO;
import frame.AnnotMap;
import frame.Attribut;
import utils.Connect;

import java.util.ArrayList;

@AnnotMap(nomTable="renchere_enchere")
public class RenchereMaxPrix extends BddObject {
    @Attribut(attr="idenchere")
    String idenchere;
    @Attribut(attr="idutilisateur")
    String idutilisateur;
    @Attribut(attr="prix")
    Double prix;


    public String getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(String idenchere) {
        this.idenchere = idenchere;
    }

    public String getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
    public ArrayList listNotFinishedRenchere(Connect conn)throws Exception{
        ArrayList l = GenericDAO.executeQuery(" select idenchere,idutilisateur," +
                "max(prix)as prix from renchere_enchere where idutilisateur='"+this.getIdutilisateur()+"'" +
                " and idenchere in(select idenchere from enchere where etat=0)" +
                " group by idenchere,idutilisateur", conn, this);
        return l;
    }

}
