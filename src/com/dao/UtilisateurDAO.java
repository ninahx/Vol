package com.dao;

import com.model.Utilisateur;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getPostgesConnection();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                    rs.getInt("id_utilisateur"),
                    rs.getString("nom_utilisateur"),
                    rs.getString("adresse"),
                    rs.getInt("age"),
                    rs.getString("choix"),
                    rs.getString("mot_de_passe")
                );
                utilisateurs.add(utilisateur);
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Capture et relance de l'exception
            e.printStackTrace();
            throw e;
        } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
           
        }

        return utilisateurs;
    }

    // Récupérer un utilisateur par son email
    public Utilisateur getUtilisateurByEmail(String email) throws Exception {
        String query = "SELECT * FROM utilisateur WHERE nom_utilisateur = ?";
        Utilisateur utilisateur = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getPostgesConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);  // Remplacement de l'ID par l'email
            rs = stmt.executeQuery();

            if (rs.next()) {
                utilisateur = new Utilisateur(
                    rs.getInt("id_utilisateur"),
                    rs.getString("nom_utilisateur"),
                    rs.getString("adresse"),
                    rs.getInt("age"),
                    rs.getString("choix"),
                    rs.getString("mot_de_passe")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Capture et relance de l'exception
            e.printStackTrace();
            throw e;
        } finally {
            
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
          
        }

        return utilisateur;
    }

    // Récupérer un utilisateur par son ID
    public Utilisateur getUtilisateurById(int id) throws Exception {
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        Utilisateur utilisateur = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getPostgesConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                utilisateur = new Utilisateur(
                    rs.getInt("id_utilisateur"),
                    rs.getString("nom_utilisateur"),
                    rs.getString("adresse"),
                    rs.getInt("age"),
                    rs.getString("choix"),
                    rs.getString("mot_de_passe")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Capture et relance de l'exception
            e.printStackTrace();
            throw e;
        } finally {
            
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
           
        }

        return utilisateur;
    }
}
