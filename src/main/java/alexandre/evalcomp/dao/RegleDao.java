package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.Regle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RegleDao {
    
    public void create(Regle regle) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(regle);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public Regle update(Regle regle) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            regle = em.merge(regle);
        }
        catch(Exception e){
            throw e;
        }
        return regle;
    }
    
    public Regle findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Regle regle = null;
        try {
            regle = em.find(Regle.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return regle;
    }
    
    public List<Regle> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<Regle> regles = null;
        try {
            Query q = em.createQuery("SELECT a FROM Regle a");
            regles = (List<Regle>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return regles;
    }
    
    public void remove(Regle r) throws Throwable
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
