package com.connection;


import dao.GenericDAO;
import dao.UtildDb;

public class Connexion {
    public static GenericDAO getGenericDAO(){
        String driverName = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/";
        String database = "vol";
        String user = "postgres";
        String password = "2904";
        UtildDb utilDB = new UtildDb(driverName, url, database, user, password);
        // Création de l'objet GenericDAO
        GenericDAO dao = new GenericDAO();
        dao.setUtildDb(utilDB);
        return dao;
    }
}
