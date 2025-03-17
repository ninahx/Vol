<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/Vol" prefix="app" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion des Vols - VOL</title>
        <link rel="stylesheet" href="<app:url value='/assets/css/header.css' />"/>
        <link rel="stylesheet" href="<app:url value='/assets/css/main.css' />"/>
        <link rel="stylesheet" href="<app:url value='/assets/css/footer.css' />"/>
        <link rel="stylesheet" href="<app:url value='/assets/css/all.min.css' />"/>
    </head>
    <body>
        <div class="sidebar">
            <div class="logo">VOL</div>
            <div class="toggle-btn" onclick="toggleSidebar()">☰</div>
            <ul>
                <li class="has-submenu">
                    <a href="#" onclick="toggleSubmenu(event)"><i class="fas fa-plane"></i><span>Vol</span></a>
                    <ul>
                        <li><a href="<app:url value='/addVol' />">Ajout vol</a></li>
                        <li><a href="<app:url value='/vols' />">Liste vol</a></li>
                        <li><a href="<app:url value='/vol/promotions' />">Promotion vol</a></li>
                        <li><a href="<app:url value='/vol/configReservation' />">Configuration reservation vol</a></li>
                        <li><a href="<app:url value='/vol/configAnnulation' />">Configuration annulation vol</a></li>
                    </ul>
                </li>
                <li class="has-submenu">
                    <a href="#" onclick="toggleSubmenu(event)"><i class="fas fa-plane"></i><span>Reservation</span></a>
                    <ul>
                        <li><a href="<app:url value='/reservations' />">List reservation</a></li>
                        <li><a href="<app:url value='/reservation/form' />">Ajouter une reservation</a></li>
                    </ul>
                </li>
                <li><a href="<app:url value='/login' />"><i class="fas fa-sign-in-alt"></i><span>Connexion</span></a></li>
                <li><a href="<app:url value='/logout' />"><i class="fas fa-sign-out-alt"></i><span>Déconnexion</span></a></li>
            </ul>
        </div>

        <script>
            // Toggle Sidebar
            function toggleSidebar() {
                document.querySelector(".sidebar").classList.toggle("collapsed");
            }

            // Toggle Sous-Menus
            function toggleSubmenu(event) {
                event.preventDefault();
                let parentLi = event.target.closest("li");
                parentLi.classList.toggle("active");
            }
        </script>
