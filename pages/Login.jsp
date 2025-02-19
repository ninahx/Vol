<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page de login</title>
</head>
<body>
    <h1>Login page</h1>
    <div class="container">
        <form action="<%= request.getContextPath() %>/submitLogDB">
            <label for="">Enter your name</label>
            <input type="text" name="nom">
            <label for="">Enter your password</label>
            <input type="password" name="mdp">
            <input type="submit" value="Valider">
        </form>
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <p style="color: red;"><%= error %></p>
        <% } %>
    </div>
</body>
</html>
