<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.model.VilleDeservie"%>
<%@page import="java.util.List"%>
<%@ include file="../header.jsp" %>
<%@ page import="com.model.Vol" %>

<%
    List<VilleDeservie> listVille = (List<VilleDeservie>) request.getAttribute("listVille");
    Vol vol = (Vol) request.getAttribute("vol");
    String errorMessage = (String) request.getAttribute("error");
    // Formater les dates
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateDepartFormatted = dateFormat.format(vol.getDateDepart());
    String dateArriveeFormatted = dateFormat.format(vol.getDateArrivee());
%>

<div class="main-content">
    <div class="card">
        <% if (errorMessage != null) { %>
            <div class="message error">
                <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
                <span class="message-text"><%= errorMessage %></span>
                <button class="message-close"><i class="fas fa-times"></i></button>
            </div>
        <% } %>

        <h2>Modifier le vol</h2>
        <form class="form-container" action="<app:url value='/vol/update' />" method="post">
            <!-- Champ cach� pour l'ID du vol -->
            <input type="hidden" name="id" value="<%= vol.getId() %>" />

            <div class="form-group">
                <label for="villeDepartId">Ville de d�part</label>
                <select name="villeDepartId" id="villeDepartId" class="form-control" required>
                    <% if (listVille != null) {
                        for (VilleDeservie ville : listVille) { 
                            String selected = (ville.getId().equals(vol.getVilleDepartId())) ? "selected" : ""; %>
                            <option value="<%= ville.getId() %>" <%= selected %>>
                                <%= ville.getNomVille() %> (<%= ville.getCodeVille() %>)
                            </option>
                    <%  }
                    } %>
                </select>
            </div>

            <div class="form-group">
                <label for="villeArriveeId">Ville d'arriv�e</label>
                <select name="villeArriveeId" id="villeArriveeId" class="form-control" required>
                    <% if (listVille != null) {
                        for (VilleDeservie ville : listVille) { 
                            String selected = (ville.getId().equals(vol.getVilleArriveeId())) ? "selected" : ""; %>
                            <option value="<%= ville.getId() %>" <%= selected %>>
                                <%= ville.getNomVille() %> (<%= ville.getCodeVille() %>)
                            </option>
                    <%  }
                    } %>
                </select>
            </div>

            <div class="form-group">
                <label for="dateDepart">Date de d�part</label>
                <input type="date" id="dateDepart" name="dateDepart" class="form-control" value="<%= dateDepartFormatted %>" required />
            </div>

            <div class="form-group">
                <label for="dateArrivee">Date d'arriv�e</label>
                <input type="date" id="dateArrivee" name="dateArrivee" class="form-control" value="<%= dateArriveeFormatted %>" required />
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-accept">Mettre � jour</button>
                <button type="reset" class="btn btn-cancel">Annuler</button>
            </div>
        </form>
    </div>
</div>


<%@ include file="../footer.jsp" %>
