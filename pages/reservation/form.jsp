<%@ include file="../header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Vol" %>
<%@ page import="com.model.TypeSiege" %>
<%@ page import="com.model.VilleDeservie" %>

<%
    List<Vol> vols = (List<Vol>) request.getAttribute("vols");
    List<TypeSiege> typesSiege = (List<TypeSiege>) request.getAttribute("typesSiege");
    String message = (String) request.getAttribute("success");
    String error = (String) request.getAttribute("error");
%>

<div class="main-content">
    <div class="card">
        <% if (message != null) { %>
            <div class="message success">
                <span class="message-icon"><i class="fas fa-check-circle"></i></span>
                <span class="message-text"><%= message %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>
        <% if (error != null) { %>
            <div class="message error">
                <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
                <span class="message-text"><%= error %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>
        
        <h2>R�servation de vol</h2>
        <form class="form-container" action="<app:url value='/reservation/add' />" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
            <div class="form-group">
                <label>S�lectionnez un vol</label>
                <select name="volId" id="volId" class="form-control" required>
                    <option value="">Choisir un vol</option>
                    <% if (vols != null) {
                        for (Vol vol : vols) { 
                    %>
                        <option value="<%= vol.getId() %>">
                            <%= vol.getId() %> - 
                            <%= VilleDeservie.findById(vol.getVilleDepartId()).getNomVille() %> 
                            vers 
                            <%= VilleDeservie.findById(vol.getVilleArriveeId()).getNomVille() %> 
                            - D�part: <%= vol.getDateDepart() %>
                        </option>
                    <%  }
                    } %>
                </select>
            </div>
            
            <div class="form-group">
                <label>Nom</label>
                <input type="text" name="clientNom" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Pr�nom</label>
                <input type="text" name="clientPrenom" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="clientEmail" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Type de si�ge</label>
                <select name="typeSiegeId" id="typeSiegeId" class="form-control" required>
                    <option value="">Choisir un type de si�ge</option>
                    <% if (typesSiege != null) {
                        for (TypeSiege type : typesSiege) { 
                    %>
                        <option value="<%= type.getId() %>"><%= type.getType() %></option>
                    <%  }
                    } %>
                </select>
            </div>
                
            <div class="form-group">
                <label>Nombre de si�ge</label>
                <input type="number" name="nombreSiege" class="form-control" min="1" required>
            </div>
            
            <div class="form-group">
                <label>Date de r�servation</label>
                <input type="date" name="dateReservation" class="form-control" required>
            </div>
                
            <div class="form-group">
                <label>Photo</label>
                <input type="file" name="photo" class="form-control" required>
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-accept">R�server</button>
                <button type="reset" class="btn btn-cancel">Annuler</button>
            </div>
        </form>
    </div>
</div>

<script>
function validateForm() {
    // Validation simple c�t� client
    let volId = document.getElementById('volId').value;
    let typeSiegeId = document.getElementById('typeSiegeId').value;
    
    if (volId === '') {
        alert('Veuillez s�lectionner un vol');
        return false;
    }
    
    if (typeSiegeId === '') {
        alert('Veuillez s�lectionner un type de si�ge');
        return false;
    }
    
    return true;
}

// Fermer les messages
document.addEventListener('DOMContentLoaded', function() {
    let closeButtons = document.querySelectorAll('.message-close');
    closeButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            let messageDiv = this.parentElement;
            messageDiv.style.display = 'none';
        });
    });
});
</script>

<%@ include file="../footer.jsp" %>