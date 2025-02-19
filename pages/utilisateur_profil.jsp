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
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
            if (utilisateur != null) {
        %>
        <p>Your name: <%= utilisateur.getNomUtilisateur() %></p>
        <p>Your address: <%= utilisateur.getAdresse() %></p>
        <p>Your actual age: <%= utilisateur.getAge() %></p>
        <p>Your IT option: <%= utilisateur.getChoix() %></p>
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
