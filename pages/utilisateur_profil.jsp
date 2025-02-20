<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.Utilisateur" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page Utilisateur</title>
</head>
<body>
    <h1>Page Utilisateur</h1>
    <div class="container">
        <%
            // Retrieve the "user" object from the session
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");

            if (utilisateur != null) {
        %>
        <!-- Display user details -->
        <p>Your ID: <%= utilisateur.getId_utilisateur() %></p>
        <p>Your email: <%= utilisateur.getEmail() %></p>
        <p>Your role: <%= utilisateur.getRole() != null ? utilisateur.getRole().getNom() : "No role assigned" %></p>
        <% } else { %>
        <p>You are logged out</p>
        <% } %>
    </div>
    <div class="disconnection">
        <form action="<%= request.getContextPath() %>/logout" method="get">
            <button type="submit">Log out</button>
        </form>
    </div>  
</body>
</html>
