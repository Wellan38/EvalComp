/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.AutoEvaluation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
public class AutoEvaluationDao {
    
    public void create(AutoEvaluation a) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(a);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public AutoEvaluation update(AutoEvaluation a) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            a = em.merge(a);
        }
        catch(Exception e){
            throw e;
        }
        return a;
    }
    
    public AutoEvaluation findById(Integer id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        AutoEvaluation a = null;
        try {
            a = em.find(AutoEvaluation.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return a;
    }
    
    public List<AutoEvaluation> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<AutoEvaluation> a = null;
        try {
            Query q = em.createQuery("SELECT a FROM AutoEvaluation a");
            a = (List<AutoEvaluation>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return a;
    }
    
    public void remove(AutoEvaluation a) throws Throwable
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
