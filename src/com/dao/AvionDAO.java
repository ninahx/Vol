package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Avion;
import com.model.Modele;
import com.util.DBConnection;

public class AvionDAO {
//     public Avion getById(long id) throws SQLException {
//         String query = "SELECT * FROM avion WHERE id_avion = ?";
//         try (Connection conn = DBConnection.getPostgesConnection();
//              PreparedStatement stmt = conn.prepareStatement(query)) {
//             stmt.setLong(1, id);
//             ResultSet rs = stmt.executeQuery();
//             if (rs.next()) {
//                 ModeleDAO modeleDAO = new ModeleDAO();
//                 Modele modele = modeleDAO.getById(rs.getLong("id_modele"));
//                 return new Avion(rs.getLong("id_avion"), rs.getString("nom_avion"), modele, rs.getDate("date_fabrication").toLocalDate());
//             }
//         }
//         return null;
//     }

//     public List<Avion> getAll() throws SQLException {
//         List<Avion> avions = new ArrayList<>();
//         String query = "SELECT * FROM avion";
//         try (Connection conn = DBConnection.getPostgesConnection();
//              Statement stmt = conn.createStatement();
//              ResultSet rs = stmt.executeQuery(query)) {
//             ModeleDAO modeleDAO = new ModeleDAO();
//             while (rs.next()) {
//                 Modele modele = modeleDAO.getById(rs.getLong("id_modele"));
//                 avions.add(new Avion(rs.getLong("id_avion"), rs.getString("nom_avion"), modele, rs.getDate("date_fabrication").toLocalDate()));
//             }
//         }
//         return avions;
//     }
// }
//  j {
    
}
