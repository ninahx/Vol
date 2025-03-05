package com.dao;

import com.model.Vol;
import com.model.Avion;
import com.model.Ville;
import com.model.StatutVol;
import com.util.DBConnection;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VolDAO {

    // Récupérer tous les vols
    public List<Vol> getAllVols() throws Exception {
        List<Vol> vols = new ArrayList<>();
        String query = "SELECT v.id_vol, v.date_depart, v.duree_vol, " +
                       "a.id_avion, a.nom_avion, " +
                       "vd.id_ville AS id_ville_depart, vd.nom_ville AS ville_depart, " +
                       "va.id_ville AS id_ville_arrivee, va.nom_ville AS ville_arrivee, " +
                       "s.id_statut_vol, s.statut " +
                       "FROM vol v " +
                       "JOIN avion a ON v.id_avion = a.id_avion " +
                       "JOIN ville vd ON v.id_ville_depart = vd.id_ville " +
                       "JOIN ville va ON v.id_ville_arrivee = va.id_ville " +
                       "JOIN statut_vol s ON v.id_statut_vol = s.id_statut_vol";

        try (Connection conn = DBConnection.getPostgesConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // while (rs.next()) {
            //     Avion avion = new Avion(rs.getLong("id_avion"), rs.getString("nom_avion"));
            //     Ville villeDepart = new Ville(rs.getLong("id_ville_depart"), rs.getString("ville_depart"));
            //     Ville villeArrivee = new Ville(rs.getLong("id_ville_arrivee"), rs.getString("ville_arrivee"));
            //     StatutVol statutVol = new StatutVol(rs.getLong("id_statut_vol"), rs.getString("statut"));

            //     Vol vol = new Vol(
            //         rs.getLong("id_vol"),
            //         avion,
            //         villeDepart,
            //         villeArrivee,
            //         rs.getTimestamp("date_depart").toLocalDateTime(),
            //         Duration.ofSeconds(rs.getTime("duree_vol").toLocalTime().toSecondOfDay()),
            //         statutVol
            //     );
            //     vols.add(vol);
            // }
        }
        return vols;
    }
}
