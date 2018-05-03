/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kobedi.ntierapp.username.business.service;

import com.kobedi.ntierapp.username.doa.UsernameDAO;
import com.kobedi.ntierapp.username.entity.User;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;

/**
 *
 * @author Kobedi.Makgabutlane
 */
@ManagedBean
@ApplicationScoped
public class PersistenceService {
    
    
    private String persistenceUnitName;
    private static EntityManager em;
    private UsernameDAO userDao;
    
    
    
    @PostConstruct
    public void init()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        this.persistenceUnitName = sc.getInitParameter("PersistenceUnitName"); 
        this.userDao = new UsernameDAO();
        //create Entity Manager for persistence
        EntityManager emanager = UsernameDAO.createEM(persistenceUnitName);
        this.em = emanager;
        //If you must, Store dao in context for further use.
        sc.setAttribute("userNameDoa", userDao);
    }
    
    public int saveUserName(User user)
    {
        return userDao.saveUserName(user);
    }  
    
    public User getUserName(int userId)
    {
        return userDao.getUserName(userId);
    }        
         
    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public static EntityManager getEm() {
        return em;
    }
    
    
    
    

  
    
    
    
}
