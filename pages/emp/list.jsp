<% String nom = (String) request.getAttribute("nom"); %>
<% String prenom = (String) request.getAttribute("prenom"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View</title>
</head>
<body>
    <p>Nom: <%= nom %></p>
    <p>Prenom: <%= prenom %></p>
</body>
</html>
