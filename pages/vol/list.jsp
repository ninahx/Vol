<%@page import="com.model.VilleDeservie"%>
<%@ include file="../header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Vol" %>

<%
    List<Vol> listVols = (List<Vol>) request.getAttribute("vols");
    String successMessage = (String) request.getAttribute("success");
    String errorMessage = (String) request.getAttribute("error");
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

        <div class="search-form">
            <h3>Rechercher des vols</h3>
            <form action="<app:url value='/vol/search' />" method="GET">
                <div class="form-group">
                    <label for="villeDepartId">Ville de d�part:</label>
                    <select id="villeDepartId" name="villeDepartId">
                        <option value="">Toutes</option>
                        <% for (VilleDeservie ville : VilleDeservie.getAllVilles()) { %>
                            <option value="<%= ville.getId() %>"><%= ville.getNomVille() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="villeArriveeId">Ville d'arriv�e:</label>
                    <select id="villeArriveeId" name="villeArriveeId">
                        <option value="">Toutes</option>
                        <% for (VilleDeservie ville : VilleDeservie.getAllVilles()) { %>
                            <option value="<%= ville.getId() %>"><%= ville.getNomVille() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="dateDepart">Date de d�part:</label>
                    <input type="date" id="dateDepart" name="dateDepart">
                </div>
                <div class="form-group">
                    <label for="dateArrivee">Date d'arriv�e:</label>
                    <input type="date" id="dateArrivee" name="dateArrivee">
                </div>
                <div class="form-group">
                    <label for="nombreSiegesBusinessPromo">Si�ges Business Promo:</label>
                    <input type="number" id="nombreSiegesBusinessPromo" name="nombreSiegesBusinessPromo" min="0">
                </div>
                <div class="form-group">
                    <label for="nombreSiegesEcoPromo">Si�ges �co Promo:</label>
                    <input type="number" id="nombreSiegesEcoPromo" name="nombreSiegesEcoPromo" min="0">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Rechercher</button>
                </div>
            </form>
        </div>
        <h2>Liste des vols</h2>
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
                        <th>D�lai de r�servation actuel (heures)</th>
                        <th>D�lai d'annulation actuel (heures)</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (listVols != null) {
                        for (Vol vol : listVols) { 
                            String editUrl = "/vol/edit?id=" + vol.getId();
                            String deleteUrl = "/vol/delete?id=" + vol.getId();
                    %>
                            <tr>
                                <td><%= vol.getId() %></td>
                                <td><%= VilleDeservie.findById(vol.getVilleDepartId()).getNomVille() %> - <%= VilleDeservie.findById(vol.getVilleArriveeId()).getNomVille() %></td>
                                <td><%= vol.getDateDepart() %></td>
                                <td><%= vol.getDateArrivee() %></td>
                                <td><%= vol.getNombreSiegesBusinessPromo() != null ? vol.getNombreSiegesBusinessPromo() : "0" %></td>
                                <td><%= vol.getNombreSiegesEcoPromo() != null ? vol.getNombreSiegesEcoPromo() : "0" %></td>
                                <td><%= vol.getHeureReservationAvantVol() != null ? vol.getHeureReservationAvantVol() + "h" : "Non configur�" %></td>
                                <td><%= vol.getHeureAnnulationAvantVol() != null ? vol.getHeureAnnulationAvantVol() + "h" : "Non configur�" %></td>
                                <td>
                                    <div class="btn-group">
                                        <a href="<app:url value='<%= editUrl %>' />" class="btn btn-primary">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <a href="<app:url value='<%= deleteUrl %>' />" class="btn btn-cancel">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                    <%  
                        }
                    } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
