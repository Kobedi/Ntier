/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kobedi.ntierapp.username.doa;

import com.kobedi.ntierapp.username.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kobedi.Makgabutlane
 */
public class UsernameDAO {

    private static EntityManager em;

    public UsernameDAO() {
    }
    
    public Integer saveUserName(User user)
    {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user.getUserId();
    } 
    
    public User getUserName(int userId)
    {
       TypedQuery<User> query = em.createQuery("FROM User u where u.userId=:userId", User.class);
        query.setParameter("userId", userId);
        return query.getSingleResult();
    }        

    public static EntityManager createEM(String persistenceUnitName) {

        EntityManager ems = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            ems = emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ems;
    }

}
