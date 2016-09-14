package alexandre.evalcomp.dao;

import alexandre.evalcomp.dao.JpaUtil;
import alexandre.evalcomp.metier.modele.CompetenceS;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CompetenceSDao {
    
    public void create(CompetenceS competence) throws Throwable
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
    
    public CompetenceS update(CompetenceS competence) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            competence = em.merge(competence);
        }
        catch(Exception e){
            throw e;
        }
        return competence;
    }
    
    public CompetenceS findById(String id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        CompetenceS competence = null;
        try {
            competence = em.find(CompetenceS.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return competence;
    }
    
    public List<CompetenceS> findAll() throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        List<CompetenceS> competences = null;
        try {
            Query q = em.createQuery("SELECT a FROM CompetenceS a");
            competences = (List<CompetenceS>) q.getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        
        return competences;
    }
    
    public void remove(CompetenceS c) throws Throwable
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
