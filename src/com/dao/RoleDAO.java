package com.dao;

import com.model.Role;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    // Récupérer tous les rôles
    public List<Role> getAllRoles() throws Exception {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM role";

        try (Connection conn = DBConnection.getPostgesConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Role role = new Role(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getInt("valeur")
                );
                roles.add(role);
            }
        }
        return roles;
    }

    // Récupérer un rôle par son ID
    public Role getRoleById(int id) throws Exception {
        String query = "SELECT * FROM role WHERE id = ?";
        Role role = null;

        try (Connection conn = DBConnection.getPostgesConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = new Role(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getInt("valeur")
                    );
                }
            }
        }
        return role;
    }
}
