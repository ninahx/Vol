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

        <h2>Gestion des si�ges promotionnels</h2>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Num�ro de vol</th>
                        <th>Destination</th>
                        <th>Date de d�part</th>
                        <th>Date d'arriv�e</th>
                        <th>Si�ges Business Promo</th>
                        <th>Si�ges �co Promo</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (listVols != null) {
                        for (Vol vol : listVols) { %>
                            <tr>
                                <td><%= vol.getId() %></td>
                                <td><%= VilleDeservie.findById(vol.getVilleDepartId()).getNomVille() %> - <%= VilleDeservie.findById(vol.getVilleArriveeId()).getNomVille() %></td>
                                <td><%= dateFormat.format(vol.getDateDepart()) %></td>
                                <td><%= dateFormat.format(vol.getDateArrivee()) %></td>
                                <td><%= vol.getNombreSiegesBusinessPromo() != null ? vol.getNombreSiegesBusinessPromo() : "0" %></td>
                                <td><%= vol.getNombreSiegesEcoPromo() != null ? vol.getNombreSiegesEcoPromo() : "0" %></td>
                                <td>
                                    <button class="btn btn-primary" onclick="openPromoModal('<%= vol.getId() %>', '<%= vol.getNombreSiegesBusinessPromo() %>', '<%= vol.getNombreSiegesEcoPromo() %>')">
                                        <i class="fas fa-edit"></i> Configurer les si�ges
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

<!-- Modal pour la gestion des si�ges promotionnels -->
<div id="promoModal" class="modal">
    <div class="modal-content">
        <h3>Configurer les si�ges promotionnels</h3>
        <form class="form-container" action="<app:url value='/vol/updatePromotion' />" method="post">
            <input type="hidden" id="volId" name="id">
            
            <div class="form-group">
                <label for="nombreSiegesBusinessPromo">Nombre de si�ges Business en promotion</label>
                <input type="number" id="nombreSiegesBusinessPromo" name="nombreSiegesBusinessPromo" 
                       class="form-control" min="0" required>
            </div>

            <div class="form-group">
                <label for="nombreSiegesEcoPromo">Nombre de si�ges �conomique en promotion</label>
                <input type="number" id="nombreSiegesEcoPromo" name="nombreSiegesEcoPromo" 
                       class="form-control" min="0" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-accept">Enregistrer</button>
                <button type="button" class="btn btn-cancel" onclick="closePromoModal()">Annuler</button>
            </div>
        </form>
    </div>
</div>

<script>
function openPromoModal(volId, businessPromo, ecoPromo) {
    document.getElementById('volId').value = volId;
    document.getElementById('nombreSiegesBusinessPromo').value = businessPromo || 0;
    document.getElementById('nombreSiegesEcoPromo').value = ecoPromo || 0;
    document.getElementById('promoModal').style.display = 'block';
}

function closePromoModal() {
    document.getElementById('promoModal').style.display = 'none';
}

// Fermer le modal si l'utilisateur clique en dehors
window.onclick = function(event) {
    var modal = document.getElementById('promoModal');
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