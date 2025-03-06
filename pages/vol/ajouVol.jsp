<%@page import="java.util.Map"%>
<%@ include file="../header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.VilleDeservie" %>

<%
    List<VilleDeservie> listVille = (List<VilleDeservie>) request.getAttribute("listVille");
    String message = (String) request.getAttribute("success");
    String error = (String) request.getAttribute("error");
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
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
        <h2>Ajouter un nouveau vol</h2>
        <form class="form-container" action="<app:url value='/vol/add' />" method="post">
            <div class="form-group">
                <label>Ville de d�part</label>
                <select name="villeDepartId" class="form-control" required>
                    <option value="">S�lectionnez une ville</option>
                    <% if (listVille != null) {
                        for (VilleDeservie ville : listVille) { %>
                            <option value="<%= ville.getId() %>"><%= ville.getNomVille() %> (<%= ville.getCodeVille() %>)</option>
                    <%  }
                    } %>
                </select>
            </div>

            <div class="form-group">
                <label>Ville d'arriv�e</label>
                <select name="villeArriveeId" class="form-control" required>
                    <option value="">S�lectionnez une ville</option>
                    <% if (listVille != null) {
                        for (VilleDeservie ville : listVille) { %>
                            <option value="<%= ville.getId() %>"><%= ville.getNomVille() %> (<%= ville.getCodeVille() %>)</option>
                    <%  }
                    } %>
                </select>
            </div>

            <div class="form-group">
                <label>Date de d�part</label>
                <input type="date" name="dateDepart" class="form-control">
                <% if (errors != null && errors.containsKey("dateDepart")) { %>
                    <div class="message error">
                        <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
                        <span class="message-text"><%= errors.get("dateDepart") %></span>
                        <button class="message-close"><i class="fas fa-times"></i></button>
                    </div>
                <% } %>
            </div>

            <div class="form-group">
                <label>Date d'arriv�e</label>
                <input type="date" name="dateArrivee" class="form-control">
                <% if (errors != null && errors.containsKey("dateArrivee")) { %>
                    <div class="message error">
                        <span class="message-icon"><i class="fas fa-exclamation-circle"></i></span>
                        <span class="message-text"><%= errors.get("dateArrivee") %></span>
                        <button class="message-close"><i class="fas fa-times"></i></button>
                    </div>
                <% } %>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-accept">Enregistrer</button>
                <button type="reset" class="btn btn-cancel">Annuler</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="../footer.jsp" %>