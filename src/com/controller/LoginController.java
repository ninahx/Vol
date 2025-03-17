package com.controller;

import com.model.User;

import main.controller.Controller;
import main.controller.POST;
import main.controller.Param;
import main.controller.URLS;
import main.modelView.ModelView;
import main.session.MySession;

@Controller
public class LoginController {
    
    @URLS("/login")
    public ModelView loginPage() {
        ModelView modelView = new ModelView();
        modelView.setUrl("/login/login.jsp");
        return modelView;
    }
    
    @URLS("/do-login")
    @POST
    public ModelView processLogin(
        @Param("username") String username,
        @Param("password") String password,
        MySession session) {
        ModelView mv = new ModelView();
        User user = User.authenticate(username, password);
        
        if (user == null) {
            mv.add("error", "Invalid credentials");
            mv.setUrl("/login/login.jsp");
        } else if (user.getRole().equalsIgnoreCase("ADMIN")) {
            session.add("user", user);        
            session.add("userRole", "ADMIN"); 
            mv.setUrl("/vols");
        } 
        
        return mv;
    }
    
    @URLS("/logout")
    public ModelView logout(MySession session) {
        ModelView mv = new ModelView();
        session.delete("user");
        session.delete("userRole");
        mv.setUrl("/index.jsp");
        return mv;
    }
}