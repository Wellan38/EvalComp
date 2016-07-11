/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.Apprenant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
public class ApprenantDao {
    
    public void create(Apprenant apprenant) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(apprenant);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public Apprenant update(Apprenant apprenant) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            apprenant = em.merge(apprenant);
        }
        catch(Exception e){
            throw e;
        }
        return apprenant;
    }
    
    public Apprenant findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Apprenant apprenant = null;
        try {
            apprenant = em.find(Apprenant.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return apprenant;
    }
    
    public List<Apprenant> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Apprenant> apprenants = null;
        try {
            Query q = em.createQuery("SELECT a FROM Apprenant a");
            apprenants = (List<Apprenant>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return apprenants;
    }
    
    public void remove(Apprenant a) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.remove(em.find(a.getClass(), a.getId()));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
