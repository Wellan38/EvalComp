package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.Formation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;

public class FormationDao {
    
    public void create(Formation formation) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(formation);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public Formation update(Formation formation) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            formation = em.merge(formation);
        }
        catch(Exception e){
            throw e;
        }
        return formation;
    }
    
    public Formation findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Formation formation = null;
        
        try {
            formation = em.find(Formation.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return formation;
    }
    
    
    public Formation findByUrl (String url) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Formation formation = null;
        try {
            formation = em.find(Formation.class, url);
        }
        catch(Exception e) {
            throw e;
        }
        return formation;
    }

    
    public List<Formation> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Formation> formations = null;
        try {
            Query q = em.createQuery("SELECT a FROM Formation a");
            formations = (List<Formation>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return formations;
    }
    
    public void remove(Formation f) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.remove(em.find(f.getClass(), f.getId()));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
