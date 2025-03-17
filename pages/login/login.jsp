<%@ include file="../header.jsp" %>

<div class="main-content">
    <!-- Formulaire d'ajout/modification -->
            <div class="card">
                <h2>Se conncter</h2>
                <form class="form-container" action="do-login" method="post">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" name="username" class="form-control" placeholder="EX: jean@gmail.com" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Mot de passe</label>
                        <input type="password" name="password" class="form-control" placeholder="*****" required>
                    </div>
                    <% if (request.getAttribute("error") != null) { %>
                        <p style="color: red;">${error}</p>
                    <% } %>
                    <div class="btn-group">
                        <button type="submit" class="btn btn-accept">Connecter</button>
                        <button type="reset" class="btn btn-cancel">Annuler</button>
                    </div>
                </form>
            </div>
</div>
<%@ include file="../footer.jsp" %>