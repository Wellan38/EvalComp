/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.Score;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
public class ScoreDao {
    
    public void create(Score s) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(s);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public Score update(Score s) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            s = em.merge(s);
        }
        catch(Exception e){
            throw e;
        }
        return s;
    }
    
    public Score findById(Integer id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Score s = null;
        try {
            s = em.find(Score.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return s;
    }
    
    public List<Score> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Score> s = null;
        try {
            Query q = em.createQuery("SELECT a FROM Score a");
            s = (List<Score>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return s;
    }
    
    public void remove(Score s) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.remove(em.find(s.getClass(), s.getId()));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
