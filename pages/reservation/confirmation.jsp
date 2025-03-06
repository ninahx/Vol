<%@ include file="../header.jsp" %>
<%@ page import="com.model.Reservation" %>
<%@ page import="com.model.Vol" %>
<%@ page import="com.model.TypeSiege" %>
<%@ page import="com.model.VilleDeservie" %>

<%
    Reservation reservation = (Reservation) request.getAttribute("reservation");
    String message = (String) request.getAttribute("success");
    String error = (String) request.getAttribute("error");
%>

<div class="main-content">
    <div class="card">
        <% if (message != null) { %>
            <div class="message success">
                <span class="message-icon"><i class="fas fa-check-circle"></i></span>
                <span class="message-text"><%= message %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>
        <% if (error != null) { %>
            <div class="message error">
                <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
                <span class="message-text"><%= error %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>
        
        <h2>Confirmation de r�servation</h2>
        
        <% if (reservation != null) { 
            Vol vol = Vol.findById(reservation.getVolId());
            TypeSiege typeSiege = TypeSiege.findById(reservation.getTypeSiegeId());
        %>
            <div class="confirmation-details">
                <h3>Votre r�servation a �t� enregistr�e avec succ�s!</h3>
                <p>Num�ro de r�servation: <strong><%= reservation.getId() %></strong></p>
                
                <div class="detail-group">
                    <h4>D�tails du vol</h4>
                    <p>Num�ro de vol: <strong><%= vol.getId() %></strong></p>
                    <p>De: <strong><%= VilleDeservie.findById(vol.getVilleDepartId()).getNomVille() %></strong></p>
                    <p>�: <strong><%= VilleDeservie.findById(vol.getVilleArriveeId()).getNomVille() %></strong></p>
                    <p>Date de d�part: <strong><%= vol.getDateDepart() %></strong></p>
                    <p>Date d'arriv�e: <strong><%= vol.getDateArrivee() %></strong></p>
                </div>
                
                <div class="detail-group">
                    <h4>D�tails du passager</h4>
                    <p>Nom: <strong><%= reservation.getClientNom() %></strong></p>
                    <p>Pr�nom: <strong><%= reservation.getClientPrenom() %></strong></p>
                    <p>Email: <strong><%= reservation.getClientEmail() %></strong></p>
                    <p>Type de si�ge: <strong><%= typeSiege.getType() %></strong></p>
                    <p>Date de r�servation: <strong><%= reservation.getDateReservation() %></strong></p>
                </div>
                
                <% if (vol.getHeureAnnulationAvantVol() != null) { %>
                    <div class="detail-group">
                        <h4>Politique d'annulation</h4>
                        <p>Vous pouvez annuler cette r�servation jusqu'� <%= vol.getHeureAnnulationAvantVol() %> heures avant le d�part.</p>
                    </div>
                <% } %>
            </div>
            
            <div class="actions">
                <a href="<app:url value='/reservations' />" class="btn btn-primary">Voir toutes mes r�servations</a>
                <a href="<app:url value='/' />" class="btn btn-secondary">Retour � l'accueil</a>
            </div>
        <% } else { %>
            <div class="error-container">
                <p>Aucune information de r�servation disponible.</p>
                <a href="<app:url value='/reservation/form' />" class="btn btn-primary">Faire une nouvelle r�servation</a>
            </div>
        <% } %>
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