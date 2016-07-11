/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.MiseEnSituation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alex-Laptop
 */
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
    
    public MiseEnSituation findById(Integer id) throws Throwable {
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
        List<MiseEnSituation> misesEnSituations = null;
        try {
            Query q = em.createQuery("SELECT a FROM MiseEnSituation a");
            misesEnSituations = (List<MiseEnSituation>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return misesEnSituations;
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