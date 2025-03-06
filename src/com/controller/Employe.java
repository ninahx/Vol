package com.controller;

import java.io.IOException;
import main.modelView.ModelView;
import main.controller.Controller;
import main.controller.POST;
import main.controller.Param;
import main.controller.URLS;
import main.controller.*;
@Controller
public class Employe {
    @Attribute("name")
    @NotNull(message = "Le nom ne peut pas être nul.")
    String nom;
    @Attribute("firstName")
    @MinLength(value = 3, message = "La taille du prenom ne peut pas etre inferieur a 3")
    String prenom;
    @Positive(message = "L'âge doit être un nombre positif.")
    @Attribute("age")
    int age;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @GET
    @URLS("/emp/test")
    public String test() {
        return "Liste employee get";
    }

    @POST
    @URLS("/emp/test")
    public String testPost() {
        return "Liste employee post";
    }

    @GET
    @URLS("/emp/list")
    @Restapi
    public ModelView getListEmp() {
        ModelView modelView = new ModelView();
        modelView.add("nom", "Mickael");
        modelView.add("prenom", "Fandresena");
        return modelView;
    }
    
    @GET
    @URLS("/emp/mety")
    public ModelView ety() {
        ModelView modelView = new ModelView();
        modelView.setUrl("/emp/mety.jsp");
        return modelView;
    }
    
    @GET
    @URLS("/emp/file")
    public ModelView file() {
        ModelView modelView = new ModelView();
        modelView.setUrl("/emp/fileUpload.jsp");
        return modelView;
    }
    
    @GET
    @URLS("/emp/tsyMety")
    public ModelView tsyMety() {
        ModelView modelView = new ModelView();
        modelView.setUrl("/emp/tsyMety.jsp");
        return modelView;
    }

    @GET
    @URLS("/emp/save")
    public ModelView saveEmp(String nom, @Param("firstName") String prenom, @Param("age") int age, @Param("photo") MultipartFile file) {
        System.out.println("Nom du fichier uploadé : " + file.getOriginalFileName());
        ModelView modelView = new ModelView();
        modelView.setUrl("/emp/save.jsp");
        modelView.add("employer", this);
        return modelView;
    }
    
    @POST
    @URLS("/emp/saveFile")
    public ModelView saveFIle(@Param("photo") MultipartFile photo) {
        ModelView modelView = new ModelView();
        modelView.setUrl("/emp/file.jsp");
         if (photo != null && !photo.isEmpty()) {
            String destinationPath = "D:/Fianarana/Semestre4\\Mr Vahatriniaina\\Framework_ITU_Test\\" + photo.getOriginalFileName();
            System.out.println("Nom fichier: " + photo.getOriginalFileName());
            modelView.add("photo", photo.getOriginalFileName());
            try {
                photo.saveToFile(destinationPath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Aucune photo reçue! Contenu: " + photo);
        }
         
        return modelView;
    }
    
    @POST
    @URLS("/emp/saveEmp")
    @OnValidationError("/emp/mety")
    public ModelView saveEmps(@ParamObject Employe employe) {
        ModelView modelView = new ModelView();
        modelView.setUrl("/emp/save.jsp");
        modelView.add("employer", employe);
        return modelView;
    }
}
