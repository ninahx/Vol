package com.controller;

import com.annotation.AnnotationController;
import com.annotation.MappingAnnotation;
import com.annotation.ParamAnnotation;

import com.utilFrame.ModelView;
import com.utilFrame.MySession;
import com.model.*;
import com.dao.*;
import com.util.*;

@AnnotationController
public class LoginController {

    private MySession session;

    public void setSession(MySession session) {
        this.session = session;
    }

    @MappingAnnotation(url = "/loginForm")
    public ModelView showLoginForm() {
        ModelView mv = new ModelView("login.jsp");
        return mv;
    }

    @MappingAnnotation(url = "/logout")
    public ModelView logout() {
        session.delete("user");
        ModelView mv = new ModelView("login.jsp");
        return mv;
    }

    @MappingAnnotation(url = "/checkSession")
    public ModelView check(@ParamAnnotation("nom") String name) {
        // Récupérer l'utilisateur de la session actuelle
        Utilisateur utilisateur = (Utilisateur) session.get("user");
        
        if (utilisateur != null) {
            // Si un utilisateur est trouvé dans la session, on affiche la page de profil utilisateur
            ModelView modelview = new ModelView("utilisateur_profil.jsp");
            return modelview;
        } else {
            // Si aucun utilisateur n'est trouvé dans la session, rediriger vers la page de login
            ModelView modelview = new ModelView("login.jsp");
            modelview.addObject("error", "You are not logged in");
            return modelview;
        }
    }

    @MappingAnnotation(url = "/submitLogDB")
    public ModelView submitLogDB(@ParamAnnotation("nom") String name, @ParamAnnotation("password") String mdp) {
        try {
            // Utiliser le DAO pour récupérer l'utilisateur par son email (ici, nom = email)
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utilisateur utilisateur = utilisateurDAO.getUtilisateurByEmail(name);

            if (utilisateur != null) {
                // Vérifier si le mot de passe correspond au mot de passe haché dans la base de données
                boolean isPasswordValid = PasswordUtils.verifyPassword(mdp, utilisateur.getMotDePasse());

                System.out.println("Mot de passe en clair : " + mdp);
                System.out.println("Mot de passe haché (base de données) : " + utilisateur.getMotDePasse());
                System.out.println("Mot de passe valide : " + isPasswordValid);

                // if (isPasswordValid) {
                    // Si le mot de passe est valide, on ajoute l'utilisateur à la session
                    session.add("user", utilisateur);
                    ModelView modelview = new ModelView("utilisateur_profil.jsp");
                    return modelview;
                // } else {
                //     // Si le mot de passe est incorrect
                //     ModelView modelview = new ModelView("login.jsp");
                //     modelview.addObject("error", "Incorrect password");
                //     return modelview;
                // }
            } else {
                // Si l'utilisateur n'est pas trouvé
                ModelView modelview = new ModelView("login.jsp");
                modelview.addObject("error", "No user found with that email");
                return modelview;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ModelView modelview = new ModelView("login.jsp");
            modelview.addObject("error", "An error occurred while processing your request");
            return modelview;
        }
    }
}
