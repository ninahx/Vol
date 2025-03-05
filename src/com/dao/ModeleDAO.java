package com.dao;

import com.model.*;
import com.util.DBConnection;
import java.sql.*;
import java.util.*;

// DAO pour la table Modele
public class ModeleDAO {
    // public Modele getById(long id) throws SQLException {
    //     String query = "SELECT * FROM modele WHERE id_modele = ?";
    //     try (Connection conn = DBConnection.getPostgesConnection();
    //          PreparedStatement stmt = conn.prepareStatement(query)) {
    //         stmt.setLong(1, id);
    //         ResultSet rs = stmt.executeQuery();
    //         if (rs.next()) {
    //             return new Modele(rs.getLong("id_modele"), rs.getString("nom_modele"), rs.getString("type"));
    //         }
    //     }
    //     return null;
    // }

    // public List<Modele> getAll() throws SQLException {
    //     List<Modele> modeles = new ArrayList<>();
    //     String query = "SELECT * FROM modele";
    //     try (Connection conn = DBConnection.getPostgesConnection();
    //          Statement stmt = conn.createStatement();
    //          ResultSet rs = stmt.executeQuery(query)) {
    //         while (rs.next()) {
    //             modeles.add(new Modele(rs.getLong("id_modele"), rs.getString("nom_modele"), rs.getString("type")));
    //         }
    //     }
    //     return modeles;
    // }
}


