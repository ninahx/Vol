<%@ include file="header.jsp" %>
<!-- Contenu Principal -->
        <div class="main-content">
            <h1 class="page-title">Gestion des Vols</h1>

<!--             Formulaire d'ajout/modification 
            <div class="card">
                <h2>Ajouter un nouveau vol</h2>
                <form class="form-container">
                    <div class="form-group">
                        <label>Num�ro de vol</label>
                        <input type="text" class="form-control" placeholder="Ex: AF123" required>
                    </div>

                    <div class="form-group">
                        <label>Destination</label>
                        <select class="form-control" required>
                            <option value="">S�lectionnez une destination</option>
                            <option value="paris">Paris</option>
                            <option value="london">Londres</option>
                            <option value="newyork">New York</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Date et heure de d�part</label>
                        <input type="datetime-local" class="form-control" required>
                    </div>

                    <div class="form-group">
                        <label>Prix</label>
                        <input type="number" class="form-control" placeholder="Prix en euros" required>
                    </div>

                    <div class="form-group">
                        <label>Statut</label>
                        <div class="custom-radio">
                            <input type="radio" name="status" id="status-active" checked>
                            <label for="status-active">Actif</label>
                        </div>
                        <div class="custom-radio">
                            <input type="radio" name="status" id="status-inactive">
                            <label for="status-inactive">Inactif</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Options</label>
                        <div class="custom-checkbox">
                            <input type="checkbox" id="option-repas">
                            <label for="option-repas">Repas inclus</label>
                        </div>
                        <div class="custom-checkbox">
                            <input type="checkbox" id="option-bagages">
                            <label for="option-bagages">Bagages suppl�mentaires</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label>Notes additionnelles</label>
                        <textarea class="form-control" placeholder="Ajoutez des notes suppl�mentaires ici..."></textarea>
                    </div>

                    <div class="btn-group">
                        <button type="submit" class="btn btn-accept">Enregistrer</button>
                        <button type="reset" class="btn btn-cancel">Annuler</button>
                    </div>
                </form>
            </div>

             Tableau des vols 
            <div class="card">
                <h2>Liste des vols</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Num�ro de vol</th>
                                <th>Destination</th>
                                <th>Date de d�part</th>
                                <th>Prix</th>
                                <th>Statut</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>AF123</td>
                                <td>Paris</td>
                                <td>2025-02-14 10:00</td>
                                <td>350 ?</td>
                                <td>Actif</td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-primary"><i class="fas fa-edit"></i></button>
                                        <button class="btn btn-cancel"><i class="fas fa-trash"></i></button>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>AF124</td>
                                <td>Londres</td>
                                <td>2025-02-14 14:30</td>
                                <td>280 ?</td>
                                <td>Actif</td>
                                <td>
                                    <div class="btn-group">
                                        <button class="btn btn-primary"><i class="fas fa-edit"></i></button>
                                        <button class="btn btn-cancel"><i class="fas fa-trash"></i></button>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>-->
        </div>

<!--<div class="message success">
    <span class="message-icon"><i class="fas fa-check-circle"></i></span>
    <span class="message-text">Op�ration r�ussie !</span>
    <button class="message-close"><i class="fas fa-times"></i></button>
</div>

<div class="message error">
    <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
    <span class="message-text">Une erreur est survenue.</span>
    <button class="message-close"><i class="fas fa-times"></i></button>
</div>

<div class="message warning">
    <span class="message-icon"><i class="fas fa-exclamation-triangle"></i></span>
    <span class="message-text">Avertissement : Veuillez v�rifier les informations.</span>
    <button class="message-close"><i class="fas fa-times"></i></button>
</div>

<div class="message info">
    <span class="message-icon"><i class="fas fa-info-circle"></i></span>
    <span class="message-text">Nouveaux messages dans votre bo�te.</span>
    <button class="message-close"><i class="fas fa-times"></i></button>
</div>-->
        <!-- Include du Footer -->
        <%@ include file="footer.jsp" %>