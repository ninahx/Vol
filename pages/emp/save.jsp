<%@ page import="com.controller.Employe" %>
<% Employe emp = (Employe) request.getAttribute("employer"); %>
<html>
<head>
    <title>Sauvegarde de l'Employé</title>
</head>
<body>
    <h1>Employé Sauvegardé</h1>
    <p>Nom: <%= emp.getNom() %></p>
    <p>Prenom: <%= emp.getPrenom() %></p>
    <p>Age: <%= emp.getAge() %></p>
</body>
</html>
