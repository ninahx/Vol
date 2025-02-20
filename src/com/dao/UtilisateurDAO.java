package com.dao;

import com.model.Utilisateur;
import com.model.Role;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    private RoleDAO roleDAO = new RoleDAO();  // Create an instance of RoleDAO

    // Récupérer tous les utilisateurs avec leur rôle
    public List<Utilisateur> getAllUtilisateurs() throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";

        try (Connection conn = DBConnection.getPostgesConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                Role role = roleDAO.getRoleById(roleId); // Fetch role using RoleDAO
                Utilisateur utilisateur = new Utilisateur(
                    rs.getInt("id_utilisateur"),
                    rs.getString("email"),
                    rs.getString("mot_de_passe"),
                    role
                );
                utilisateurs.add(utilisateur);
            }
        }
        return utilisateurs;
    }

    // Récupérer un utilisateur par son email
    public Utilisateur getUtilisateurByEmail(String email) throws Exception {
        String query = "SELECT * FROM utilisateur WHERE email = ?";
        Utilisateur utilisateur = null;

        try (Connection conn = DBConnection.getPostgesConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int roleId = rs.getInt("role_id");
                    Role role = roleDAO.getRoleById(roleId);  // Fetch role using RoleDAO
                    utilisateur = new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        role
                    );
                }
            }
        }
        return utilisateur;
    }

    // Récupérer un utilisateur par son ID
    public Utilisateur getUtilisateurById(int id) throws Exception {
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        Utilisateur utilisateur = null;

        try (Connection conn = DBConnection.getPostgesConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int roleId = rs.getInt("role_id");
                    Role role = roleDAO.getRoleById(roleId);  // Fetch role using RoleDAO
                    utilisateur = new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        role
                    );
                }
            }
        }
        
        return utilisateur;
    }
}
