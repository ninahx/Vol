<%@ include file="../header.jsp" %>
<%@ page import="java.util.List, java.util.Base64" %>
<%@ page import="com.model.Reservation, com.model.Vol, com.model.TypeSiege, com.model.VilleDeservie" %>

<%
    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
    String successMessage = (String) request.getAttribute("success");
    String errorMessage = (String) request.getAttribute("error");
%>

<div class="main-content">
    <div class="card">
        <% if (successMessage != null) { %>
            <div class="message success">
                <span class="message-icon"><i class="fas fa-check-circle"></i></span>
                <span class="message-text"><%= successMessage %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>

        <% if (errorMessage != null) { %>
            <div class="message error">
                <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
                <span class="message-text"><%= errorMessage %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>

        <h2>Mes r�servations</h2>
        
        <div class="actions-top">
            <a href="<app:url value='/reservation/form' />" class="btn btn-primary">Nouvelle r�servation</a>
        </div>
        
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>N� R�servation</th>
                        <th>N� Vol</th>
                        <th>Trajet</th>
                        <th>Date de d�part</th>
                        <th>Nom/Pr�nom</th>
                        <th>Email</th>
                        <th>Type si�ge</th>
                        <th>Date r�servation</th>
                        <th>Photo</th> <!-- Nouvelle colonne -->
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (reservations != null && !reservations.isEmpty()) {
                        for (Reservation reservation : reservations) { 
                            Vol vol = Vol.findById(reservation.getVolId());
                            TypeSiege typeSiege = TypeSiege.findById(reservation.getTypeSiegeId());
                            String deleteUrl = "/reservation/delete?id=" + reservation.getId();
                            byte[] photoBytes = reservation.getFichier(); // R�cup�rer l'image de la BD
                            String photoBase64 = (photoBytes != null) ? Base64.getEncoder().encodeToString(photoBytes) : null;
                    %>
                            <tr>
                                <td><%= reservation.getId() %></td>
                                <td><%= reservation.getVolId() %></td>
                                <td>
                                    <% if (vol != null) { %>
                                        <%= VilleDeservie.findById(vol.getVilleDepartId()).getNomVille() %> - 
                                        <%= VilleDeservie.findById(vol.getVilleArriveeId()).getNomVille() %>
                                    <% } else { %>
                                        Vol introuvable
                                    <% } %>
                                </td>
                                <td><%= vol != null ? vol.getDateDepart() : "N/A" %></td>
                                <td><%= reservation.getClientNom() %> <%= reservation.getClientPrenom() %></td>
                                <td><%= reservation.getClientEmail() %></td>
                                <td><%= typeSiege != null ? typeSiege.getType() : "N/A" %></td>
                                <td><%= reservation.getDateReservation() %></td>
                                <td>
                                    <% if (photoBase64 != null) { %>
                                        <img src="data:image/jpeg;base64,<%= photoBase64 %>" alt="Photo de r�servation" width="80" height="80">
                                    <% } else { %>
                                        <span>Pas de photo</span>
                                    <% } %>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <a href="<app:url value='<%= deleteUrl %>' />" class="btn btn-cancel" onclick="return confirm('�tes-vous s�r de vouloir annuler cette r�servation?');">
                                            <i class="fas fa-times"></i> Annuler
                                        </a>
                                    </div>
                                </td>
                            </tr>
                    <%  
                        }
                    } else { %>
                        <tr>
                            <td colspan="10" class="no-data">Aucune r�servation trouv�e</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
// Fermer les messages
document.addEventListener('DOMContentLoaded', function() {
    let closeButtons = document.querySelectorAll('.message-close');
    closeButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            let messageDiv = this.parentElement;
            messageDiv.style.display = 'none';
        });
    });
});
</script>

<%@ include file="../footer.jsp" %>
