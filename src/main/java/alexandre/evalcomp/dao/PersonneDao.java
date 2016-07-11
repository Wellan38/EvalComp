/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.Personne;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
public class PersonneDao {
    
    public void create(Personne personne) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(personne);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public Personne update(Personne personne) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            personne = em.merge(personne);
        }
        catch(Exception e){
            throw e;
        }
        return personne;
    }
    
    public Personne findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Personne personne = null;
        try {
            personne = em.find(Personne.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return personne;
    }
    
    public List<Personne> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Personne> personnes = null;
        try {
            Query q = em.createQuery("SELECT a FROM Personne a");
            personnes = (List<Personne>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return personnes;
    }
    
    public void remove(Personne p) throws Throwable
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
