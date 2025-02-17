<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.need.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page session</title>
</head>
<body>
    <h1>Page Utilisateur</h1>
    <div class="container">
        <%
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
        %>
        <p>Your name: <%= user.getName() %></p>
        <p>Your address: <%= user.getAddress() %></p>
        <p>Your actual age:<%= user.getAge() %></p>
        <p>Your IT option: <%= user.getOption() %></p>
        <% } else { %>
        <p>You are logged out</p>
        <% } %>
    </div>
</body>
</html>
