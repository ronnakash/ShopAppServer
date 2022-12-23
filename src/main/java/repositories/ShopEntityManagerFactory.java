/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ronna
 */
public class ShopEntityManagerFactory {
        private static EntityManagerFactory emf = null;
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("com.testProject_ShopAppServer_war_1.0PU");
        return emf;
    }
    
    public static EntityManager getEntityManager() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("com.testProject_ShopAppServer_war_1.0PU");
        return emf.createEntityManager();
    }
}
