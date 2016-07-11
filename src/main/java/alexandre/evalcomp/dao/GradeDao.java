/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.Grade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
public class GradeDao {
    
    public void create(Grade g) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(g);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public Grade update(Grade g) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            g = em.merge(g);
        }
        catch(Exception e){
            throw e;
        }
        return g;
    }
    
    public Grade findById(Integer id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Grade g = null;
        try {
            g = em.find(Grade.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return g;
    }
    
    public List<Grade> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Grade> g = null;
        try {
            Query q = em.createQuery("SELECT a FROM Grade a");
            g = (List<Grade>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return g;
    }
    
    public void remove(Grade p) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.remove(em.find(p.getClass(), p.getId()));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
