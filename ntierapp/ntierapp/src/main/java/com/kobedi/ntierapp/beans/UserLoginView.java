/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kobedi.ntierapp.beans;

import com.kobedi.ntierapp.username.business.service.PersistenceService;
import com.kobedi.ntierapp.username.entity.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 *
 * @author Kobedi.Makgabutlane
 */
@ManagedBean
@SessionScoped
public class UserLoginView {
     
    private String username;
     
    private String password;
    
    @ManagedProperty(value="persistenceService")
    private PersistenceService service;

    public void setService(PersistenceService service) {
        this.service = service;
    }
     
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void login(ActionEvent event) {
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            //creat an instance of User with username
            User user = new User();
            //set value
            user.setUserName(this.username);
            //persist value
            service.saveUserName(user);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);       
    }   
}
