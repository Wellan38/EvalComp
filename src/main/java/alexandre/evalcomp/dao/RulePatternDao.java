/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.RulePattern;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
public class RulePatternDao {
    
    public void create(RulePattern pattern) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(pattern);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public RulePattern update(RulePattern pattern) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            pattern = em.merge(pattern);
        }
        catch(Exception e){
            throw e;
        }
        return pattern;
    }
    
    public RulePattern findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        RulePattern pattern = null;
        try {
            pattern = em.find(RulePattern.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return pattern;
    }
    
    public List<RulePattern> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<RulePattern> patterns = null;
        try {
            Query q = em.createQuery("SELECT a FROM RulePattern a");
            patterns = (List<RulePattern>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return patterns;
    }
    
    public void remove(RulePattern r) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.remove(em.find(r.getClass(), r.getId()));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
