<%@ page import="java.util.Map" %>
<% 
    // Récupération des erreurs depuis les attributs de la requête
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Formulaire d'Employé</title>
    <style>
        .error {
            color: red;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
    <h1>Formulaire d'Employé</h1>
    <form action="saveEmp" method="POST">
        <!-- Champ Nom -->
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">
        <% if (errors != null && errors.containsKey("nom")) { %>
            <div class="error"><%= errors.get("nom") %></div>
        <% } %>
        <br>

        <!-- Champ Prénom -->
        <label for="prenom">Prenom:</label>
        <input type="text" id="prenom" name="firstName" value="<%= request.getParameter("firstName") != null ? request.getParameter("firstName") : "" %>">
        <% if (errors != null && errors.containsKey("prenom")) { %>
            <div class="error"><%= errors.get("prenom") %></div>
        <% } %>
        <br>

        <!-- Champ Âge -->
        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="<%= request.getParameter("age") != null ? request.getParameter("age") : "" %>">
        <% if (errors != null && errors.containsKey("age")) { %>
            <div class="error"><%= errors.get("age") %></div>
        <% } %>
        <br>

        <input type="submit" value="Sauvegarder">
    </form>
</body>
</html>
