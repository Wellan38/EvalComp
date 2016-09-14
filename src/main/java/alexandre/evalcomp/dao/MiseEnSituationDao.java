package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.MiseEnSituation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MiseEnSituationDao {
    
    public void create(MiseEnSituation miseEnSituation) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.persist(miseEnSituation);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public MiseEnSituation update(MiseEnSituation miseEnSituation) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            miseEnSituation = em.merge(miseEnSituation);
        }
        catch(Exception e){
            throw e;
        }
        return miseEnSituation;
    }
    
    public MiseEnSituation findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        MiseEnSituation miseEnSituation = null;
        try {
            miseEnSituation = em.find(MiseEnSituation.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return miseEnSituation;
    }
    
    public List<MiseEnSituation> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<MiseEnSituation> miseEnSituations = null;
        try {
            Query q = em.createQuery("SELECT a FROM MiseEnSituation a");
            miseEnSituations = (List<MiseEnSituation>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return miseEnSituations;
    }
    
    public void remove(MiseEnSituation m) throws Throwable
    {
        EntityManager em = JpaUtil.obtenirEntityManager();
        
        try
        {
            em.remove(em.find(m.getClass(), m.getId()));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
