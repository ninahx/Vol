<%@ include file="../header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Vol" %>
<%@ page import="com.model.VilleDeservie" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    List<Vol> listVols = (List<Vol>) request.getAttribute("vols");
    String successMessage = (String) request.getAttribute("success");
    String errorMessage = (String) request.getAttribute("error");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>

<div class="main-content">
    <div class="card">
        <% if (successMessage != null) { %>
            <div class="message success">
                <span class="message-icon"><i class="fas fa-check-circle"></i></span>
                <span class="message-text"><%= successMessage %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>

        <% if (errorMessage != null) { %>
            <div class="message error">
                <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
                <span class="message-text"><%= errorMessage %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>

        <h2>Configuration des d�lais d'annulation</h2>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Num�ro de vol</th>
                        <th>Destination</th>
                        <th>Date de d�part</th>
                        <th>D�lai d'annulation actuel (heures)</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (listVols != null) {
                        for (Vol vol : listVols) { %>
                            <tr>
                                <td><%= vol.getId() %></td>
                                <td><%= VilleDeservie.findById(vol.getVilleDepartId()).getNomVille() %> - 
                                    <%= VilleDeservie.findById(vol.getVilleArriveeId()).getNomVille() %></td>
                                <td><%= dateFormat.format(vol.getDateDepart()) %></td>
                                <td><%= vol.getHeureAnnulationAvantVol() != null ? vol.getHeureAnnulationAvantVol() + "h" : "Non configur�" %></td>
                                <td>
                                    <button class="btn btn-primary" 
                                            onclick="openConfigModal('<%= vol.getId() %>', '<%= vol.getHeureAnnulationAvantVol() %>')">
                                        <i class="fas fa-clock"></i> Configurer
                                    </button>
                                </td>
                            </tr>
                    <%  }
                    } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="configModal" class="modal">
    <div class="modal-content">
        <h3>Configurer le d�lai d'annulation</h3>
        <form class="form-container" action="<app:url value='/vol/updateHeureAnnulation' />" method="post">
            <input type="hidden" id="volId" name="id">
            
            <div class="form-group">
                <label for="heureAnnulationAvantVol">D�lai d'annulation (en heures)</label>
                <input type="number" id="heureAnnulationAvantVol" name="heureAnnulationAvantVol" 
                       class="form-control" min="0" required>
                <small class="form-text text-muted">Nombre d'heures minimum avant le vol pour pouvoir annuler</small>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-accept">Enregistrer</button>
                <button type="button" class="btn btn-cancel" onclick="closeConfigModal()">Annuler</button>
            </div>
        </form>
    </div>
</div>

<script>
function openConfigModal(volId, heures) {
    document.getElementById('volId').value = volId;
    document.getElementById('heureAnnulationAvantVol').value = heures || 0;
    document.getElementById('configModal').style.display = 'block';
}

function closeConfigModal() {
    document.getElementById('configModal').style.display = 'none';
}

window.onclick = function(event) {
    var modal = document.getElementById('configModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}
</script>

<style>
.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 500px;
    border-radius: 5px;
}
</style>

<%@ include file="../footer.jsp" %>