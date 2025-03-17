<!DOCTYPE html>
<html lang="en">
<head>
    <title>Formulaire d'Employé</title>
</head>
<body>
    <h1>Formulaire d'Employé</h1>
    <form action="/Framework/emp/save" method="GET">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="name"><br>

        <label for="prenom">Prenom:</label>
        <input type="text" id="prenom" name="firstName"><br>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age"><br>

        <input type="submit" value="Sauvegarder">
    </form>
</body>
</html>
