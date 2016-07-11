/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.CompetenceG;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
public class CompetenceGDao {
    
    public void create(CompetenceG competence) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(competence);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public CompetenceG update(CompetenceG competence) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            competence = em.merge(competence);
        }
        catch(Exception e){
            throw e;
        }
        return competence;
    }
    
    public CompetenceG findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        CompetenceG competence = null;
        try {
            competence = em.find(CompetenceG.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return competence;
    }
    
    public List<CompetenceG> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<CompetenceG> competences = null;
        try {
            Query q = em.createQuery("SELECT a FROM CompetenceG a");
            competences = (List<CompetenceG>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return competences;
    }
    
    public void remove(CompetenceG c) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.remove(em.find(c.getClass(), c.getId()));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
