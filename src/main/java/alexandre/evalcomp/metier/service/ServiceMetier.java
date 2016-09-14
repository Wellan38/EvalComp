package alexandre.evalcomp.metier.service; 

import alexandre.evalcomp.dao.*;
import alexandre.evalcomp.metier.modele.*;
import alexandre.evalcomp.metier.modele.Personne.TypePersonne;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;
import javax.persistence.OptimisticLockException;

public class ServiceMetier
{
    // ----------------------- Méthodes de création ----------------------------

    public Formation creerFormation(String id, String libelle, String domaine, String url, Integer duree, Date date) throws Throwable
    {
        if (trouverFormationParId(id) == null)
        {
            Formation f;
            if (url == null)
            {
                f = new Formation(id, libelle, domaine, duree, date);
            }
            else
            {
                f = new Formation(id, libelle, domaine, url, duree, date);
            }

            if (creerObjet(f))
            {
                return f;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    public CompetenceG creerCompetenceG(String id, String libelle, String type, Double seuilMin, Double seuilMax, List<CompetenceS> compSpec) throws Throwable
    {
        if (trouverCompetenceGParId(id) == null)
        {
            CompetenceG c = new CompetenceG(id, libelle, type, seuilMin, seuilMax, compSpec);

            if (creerObjet(c))
            {
                return c;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    public CompetenceS creerCompetenceS(String id, String libelle, Boolean feminin, Boolean pluriel, String type, Double ponderation, Regle regle, MiseEnSituation miseEnSituation) throws Throwable
    {
        if (trouverCompetenceSParId(id) == null)
        {
            CompetenceS c = new CompetenceS(id, libelle, feminin, pluriel, type, ponderation, regle, miseEnSituation);

            if (creerObjet(c))
            {
                return c;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    public MiseEnSituation creerMiseEnSituation(String id, String contexte, String ressources, String action) throws Throwable
    {
        MiseEnSituation m = new MiseEnSituation(id, contexte, ressources, action);
        
        if (creerObjet(m))
        {
            return m;
        }
        else
        {
            return null;
        }
    }
    
    public Personne creerPersonne(String id, String nom, String niveau, TypePersonne type, String motDePasse) throws Throwable
    {
        Personne p = new Personne(id, nom, niveau, type, motDePasse);
        
        if (trouverPersonneParId(id) == null)
        {
            if (creerObjet(p))
            {
                return p;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    public Apprenant creerApprenant(String id, String nom, String fonction, String entreprise, Personne compte) throws Throwable
    {
        if (trouverApprenantParId(id) == null)
        {
            Apprenant a = new Apprenant(id, nom, fonction, entreprise, compte);

            if (creerObjet(a))
            {
                return a;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    public RulePattern creerRulePattern(String id, String libelle, List<Pair<String, Integer>> cas, Boolean nombre, Boolean ajoutCas) throws Throwable
    {
        if (trouverRulePatternParId(id) == null)
        {
            RulePattern r = new RulePattern(id, libelle, cas, nombre, ajoutCas);

            if (creerObjet(r))
            {
                return r;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    public Regle creerRegle(String id, String libelle, RulePattern pattern, Boolean pourcentages, List<Pair<String, Integer>> cas) throws Throwable
    {
        if (trouverRegleParId(id) == null)
        {
            Regle r = new Regle(id, libelle, pattern, pourcentages, cas);

            if (creerObjet(r))
            {
                return r;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    // ---------------------- Méthodes de suppression --------------------------
    
    public Boolean supprimerApprenant(Apprenant a) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {            
            return supprimerObjet(a);
        }
    }

    public Boolean supprimerFormation(Formation f) throws Throwable
    {
        if (f == null)
        {
            return false;
        }
        
        List<Apprenant> apprenants = listerApprenants();
            
        for (Apprenant a : apprenants)
        {
            if (a.getFormation() != null && a.getFormation().equals(f))
            {
                a.setFormation(null);
                
                majObjet(a);
            }
        }
        
        supprimerObjet(f);

        return true;
    }

    public Boolean supprimerPersonne(Personne p) throws Throwable
    {
        if (p == null)
        {
            return false;
        }
        else
        {
            List<Formation> formations = listerFormations();
            
            for (Formation f : formations)
            {
                List<Personne> responsables = f.getResponsables();
                
                Boolean contient = false;
                for (Personne r : responsables)
                {
                    if (r.equals(p))
                    {
                        responsables.remove(r);
                        contient = true;
                    }
                }
                
                if (contient)
                {
                    majObjet(f);
                }
            }
            
            supprimerObjet(p);
            
            return true;
        }
    }

    public Boolean supprimerScore(Apprenant a, CompetenceS c) throws Throwable
    {
        if (a == null || c == null)
        {
            return false;
        }
        
        List<Score> scores = listerScoresParApprenant(a);
        
        for (Score p : scores)
        {
            if (p.getCompetence().equals(c))
            {
                scores.remove(p);
                
                calculerGrade(a, c.getCompG());
                
                return majObjet(a);
            }
        }
        
        return false;
    }
    
    public Boolean supprimerAutoevaluation(Apprenant a, CompetenceS c) throws Throwable
    {
        if (a == null || c == null)
        {
            return false;
        }
        
        List<AutoEvaluation> evals = listerAutoevaluationsParApprenant(a);
        
        for (AutoEvaluation e : evals)
        {
            if (e.getCompetence().equals(c))
            {
                evals.remove(e);
                
                majObjet(a);
                
                return supprimerObjet(e);
            }
        }
        
        return false;
    }

    public Boolean supprimerRegle(Regle r) throws Throwable
    {
        List<CompetenceS> comps = listerCompetenceS();
        
        for (CompetenceS c : comps)
        {
            if (c.getRegle().equals(r))
            {
                c.setRegle(null);
                majObjet(c);
            }
        }
        
        return supprimerObjet(r);
    }
    
    public Boolean supprimerCompetenceS(CompetenceS c) throws Throwable
    {
        if (c != null)
        {
            Regle r = c.getRegle();
            c.setRegle(null);
            majObjet(c);
            supprimerObjet(r);
            List<Score> scores = listerScores();
            
            for (Iterator<Score> it = scores.listIterator(); it.hasNext();)
            {
                Score s = it.next();
                
                if (s.getCompetence().equals(c))
                {
                    supprimerScore(s.getApprenant(), c);
                }
            }
            
            return supprimerObjet(c);
        }
        else
        {
            return Boolean.FALSE;
        }
    }
    
    public Boolean supprimerCompetenceG(CompetenceG c) throws Throwable
    {
        if (c != null)
        {
            List<Formation> formations = listerFormations();
            
            for (Formation f : formations)
            {
                for (Iterator<CompetenceG> it = f.getCompetences().listIterator(); it.hasNext();)
                {
                    CompetenceG cg = it.next();
                    
                    if (cg.equals(c))
                    {
                        it.remove();
                        
                        majObjet(f);
                    }
                }
            }
            
            for (Iterator<CompetenceS> it = c.getCompSpec().listIterator(); it.hasNext();)
            {
                CompetenceS cs = it.next();
                
                it.remove();
                
                majObjet(c);
                
                supprimerCompetenceS(cs);
            }
            
            return supprimerObjet(c);
        }
        else
        {
            return Boolean.FALSE;
        }
    }
    
    // -------------- Méthodes de recherche par identifiant --------------------
    
    public Apprenant trouverApprenantParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        ApprenantDao dao = new ApprenantDao();
        
        Apprenant a = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return a;
    }
    
    public CompetenceG trouverCompetenceGParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        CompetenceGDao dao = new CompetenceGDao();
        
        CompetenceG c = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return c;
    }
    
    public CompetenceS trouverCompetenceSParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        CompetenceSDao dao = new CompetenceSDao();
        
        CompetenceS c = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return c;
    }
    
    public Formation trouverFormationParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        FormationDao dao = new FormationDao();
        
        Formation f = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return f;
    }
    
    public Personne trouverPersonneParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        PersonneDao dao = new PersonneDao();
        
        Personne p = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return p;
    }
    
    public RulePattern trouverRulePatternParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        RulePatternDao dao = new RulePatternDao();
        
        RulePattern p = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return p;
    }
    
    public Regle trouverRegleParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        RegleDao dao = new RegleDao();
        
        Regle r = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return r;
    }
    
    public MiseEnSituation trouverMiseEnSituationParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        MiseEnSituation m = new MiseEnSituationDao().findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return m;
    }
    
    // -------------- Méthodes de recherche par nom/libellé --------------------

    public List<Apprenant> trouverApprenantParNom(String nom) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        List<Apprenant> res = new ArrayList();
        
        ApprenantDao dao = new ApprenantDao();
        
        List<Apprenant> apprenants = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        for (Apprenant a : apprenants)
        {
            if (a.getNom().contains(nom))
            {
                res.add(a);
            }
        }
        
        return res;
    }
    
    public List<Apprenant> trouverApprenantParFonction(String fonction) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        List<Apprenant> res = new ArrayList();
        
        ApprenantDao dao = new ApprenantDao();
        
        List<Apprenant> apprenants = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        for (Apprenant a : apprenants)
        {
            if (a.getFonction().contains(fonction))
            {
                res.add(a);
            }
        }
        
        return res;
    }

    public List<Formation> trouverFormationParLibelle(String libelle) throws Throwable
    {
        List<Formation> res = new ArrayList();
        
        JpaUtil.creerEntityManager();
        
        FormationDao dao = new FormationDao();
        
        List<Formation> formations = dao.findAll();
            
        JpaUtil.fermerEntityManager();

        for (Formation f : formations)
        {
            if (f.getLibelle().contains(libelle))
            {
                res.add(f);
            }
        }

        return res;
    }

    public List<Personne> trouverPersonneParNom(String nom) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        List<Personne> res = new ArrayList();
        
        PersonneDao dao = new PersonneDao();
        
        List<Personne> personnes = dao.findAll();
        
        for (Personne p : personnes)
        {
            if (p.getNom().contains(nom))
            {
                res.add(p);
            }
        }
        
        return res;
    }

    public List<CompetenceG> trouverCompetenceGParLibelle(String libelle) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        List<CompetenceG> res = new ArrayList();
        
        CompetenceGDao dao = new CompetenceGDao();
        
        List<CompetenceG> competences = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        for (CompetenceG c : competences)
        {
            if (c.getLibelle().contains(libelle))
            {
                res.add(c);
            }
        }
        
        return res;
    }

    public List<CompetenceS> trouverCompetenceSParLibelle(String libelle) throws Throwable
    {
        List<CompetenceS> res = new ArrayList();
        
        List<CompetenceS> competences = listerCompetenceS();
        
        for (CompetenceS c : competences)
        {
            if (c.getLibelle().contains(libelle))
            {
                res.add(c);
            }
        }
        
        return res;
    }

    public List<Regle> trouverRegleParLibelle(String libelle) throws Throwable
    {
        List<Regle> res = new ArrayList();
        
        List<Regle> regles = listerRegles();
        
        for (Regle r : regles)
        {
            if (r.getLibelle().contains(libelle))
            {
                System.out.println("trouvé");
                res.add(r);
            }
        }
        
        return res;
    }
    
    // ------------------ Autres méthodes de recherche -------------------------
    
    public List<Formation> trouverFormationParDomaine(String domaine) throws Throwable
    {
        List<Formation> formations = listerFormations();
        
        List<Formation> res = new ArrayList();
        
        for (Formation f : formations)
        {
            if (f.getDomaine().contains(domaine))
            {
                res.add(f);
            }
        }
        
        return res;
    }
    
    public List<CompetenceG> trouverCompetenceGParType(String type) throws Throwable
    {
        List<CompetenceG> competences = listerCompetenceG();
        
        List<CompetenceG> res = new ArrayList();
        
        for (CompetenceG c : competences)
        {
            if (c.getType().contains(type))
            {
                res.add(c);
            }
        }
        
        return res;
    }
    
    public List<CompetenceS> trouverCompetenceSParType(String type) throws Throwable
    {
        List<CompetenceS> competences = listerCompetenceS();
        
        List<CompetenceS> res = new ArrayList();
        
        for (CompetenceS c : competences)
        {
            if (c.getType().contains(type))
            {
                res.add(c);
            }
        }
        
        return res;
    }
    
    public Apprenant trouverApprenantParCompte(Personne compte) throws Throwable
    {
        List<Apprenant> apprenants = listerApprenants();
        
        for (Apprenant a : apprenants)
        {
            if (compte.equals(a.getCompte()))
            {
                return a;
            }
        }
        
        return null;
    }
    
    // --------------------- Méthodes de listing -------------------------------
    
    public List<Personne> listerPersonnes() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        PersonneDao dao = new PersonneDao();
        
        List<Personne> personnes = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return personnes;
    }

    public List<Formation> listerFormations() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        FormationDao dao = new FormationDao();
        
        List<Formation> formations = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return formations;
    }

    public List<CompetenceG> listerCompetenceG() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        CompetenceGDao dao = new CompetenceGDao();
        
        List<CompetenceG> competences = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return competences;
    }

    public List<CompetenceS> listerCompetenceS() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        CompetenceSDao dao = new CompetenceSDao();
        
        List<CompetenceS> competences = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return competences;
    }

    public List<Apprenant> listerApprenants() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        ApprenantDao dao = new ApprenantDao();
        
        List<Apprenant> apprenants = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return apprenants;
    }
    
    public List<Apprenant> listerApprenantsParFormation(Formation f) throws Throwable
    {
        if (f == null)
        {
            return null;
        }
        else
        {
            List<Apprenant> apprenants = listerApprenants();
            List<Apprenant> res = new ArrayList();
            
            for (Apprenant a : apprenants)
            {
                if (a.getFormation().equals(f))
                {
                    res.add(a);
                }
            }
            
            return res;
        }
    }
    
    public List<RulePattern> listerRulePatterns() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        RulePatternDao dao = new RulePatternDao();
        
        List<RulePattern> patterns = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return patterns;
    }

    public List<Regle> listerRegles() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        RegleDao dao = new RegleDao();
        
        List<Regle> regles = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return regles;
    }

    public List<Grade> listerGradesParApprenant(Apprenant a) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<Grade> grades = listerGrades();
            List<Grade> res = new ArrayList();
            
            for (Grade g : grades)
            {
                if (g.getApprenant().equals(a))
                {
                    res.add(g);
                }
            }
            
            return res;
        }
    }
    
    public List<Grade> listerGrades() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        GradeDao dao = new GradeDao();
        
        List<Grade> res = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return res;
    }
    
    public List<Score> listerScores() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        ScoreDao dao = new ScoreDao();
        
        List<Score> res = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return res;
    }
    
    public List<AutoEvaluation> listerAutoevaluations() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        AutoEvaluationDao dao = new AutoEvaluationDao();
        
        List<AutoEvaluation> res = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return res;
    }

    public List<Score> listerScoresParApprenant(Apprenant a) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<Score> scores = listerScores();
            List<Score> scores_app = new ArrayList();
            
            List<CompetenceS> compS = new ArrayList();
            
            for (Score g : scores)
            {
                if (g.getApprenant().equals(a))
                {
                    scores_app.add(g);
                    
                    if (!compS.contains(g.getCompetence()))
                    {
                        compS.add(g.getCompetence());
                    }
                }
            }
            
            List<Score> res = new ArrayList();
            
            for (CompetenceS cs : compS)
            {
                Score max = null;
                
                for (Score sc : scores_app)
                {
                    if ((max == null || sc.getDate().compareTo(max.getDate()) > 0) && sc.getCompetence().equals(cs))
                    {
                        max = sc;
                    }
                }
                res.add(max);
            }
            
            return res;
        }
    }
    
    public List<AutoEvaluation> listerAutoevaluationsParApprenant(Apprenant a) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<AutoEvaluation> evals = listerAutoevaluations();
            List<AutoEvaluation> evals_app = new ArrayList();
            
            List<CompetenceS> compS = new ArrayList();
            
            for (AutoEvaluation g : evals)
            {
                if (g.getApprenant().equals(a))
                {
                    evals_app.add(g);
                    
                    if (!compS.contains(g.getCompetence()))
                    {
                        compS.add(g.getCompetence());
                    }
                }
            }
            
            List<AutoEvaluation> res = new ArrayList();
            
            for (CompetenceS cs : compS)
            {
                AutoEvaluation max = null;
                
                for (AutoEvaluation sc : evals_app)
                {
                    if ((max == null || sc.getDate().compareTo(max.getDate()) > 0) && sc.getCompetence().equals(cs))
                    {
                        max = sc;
                    }
                }
                res.add(max);
            }
            
            return res;
        }
    }

    public List<Score> listerScoresParCompetenceG(Apprenant a, CompetenceG c) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<Score> scores = listerScoresParApprenant(a);
            
            List<Score> scores_app = new ArrayList();
            List<CompetenceS> compS = new ArrayList();
            
            for (Score s : scores)
            {
                if (s.getCompetence().getCompG().equals(c))
                {
                    scores_app.add(s);
                    
                    if (!compS.contains(s.getCompetence()))
                    {
                        compS.add(s.getCompetence());
                    }
                }
            }
            
            List<Score> res = new ArrayList();
            
            for (CompetenceS cs : compS)
            {
                Score max = null;
                
                for (Score s : scores_app)
                {
                    if ((max == null || s.getDate().compareTo(max.getDate()) > 0) && s.getCompetence().equals(cs))
                    {
                        max = s;
                    }
                }
                
                res.add(max);
            }
            
            return res;
        }
    }
    
    public List<Score> historiqueScoresParCompetenceG(Apprenant a, CompetenceG c) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<Score> scores = listerScores();
            
            List<Score> scores_app = new ArrayList();
            
            for (Score s : scores)
            {
                if (a.equals(s.getApprenant()) && s.getCompetence().getCompG().equals(c))
                {
                    scores_app.add(s);
                }
            }
            
            Collections.sort(scores_app);
            Collections.reverse(scores_app);
            
            return scores_app;
        }
    }
    
    public List<AutoEvaluation> historiqueAutoevaluationsParCompetenceG(Apprenant a, CompetenceG c) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<AutoEvaluation> evals = listerAutoevaluations();
            
            List<AutoEvaluation> evals_app = new ArrayList();
            
            for (AutoEvaluation e : evals)
            {
                if (a.equals(e.getApprenant()) && e.getCompetence().getCompG().equals(c))
                {
                    evals_app.add(e);
                }
            }
            
            Collections.sort(evals_app);
            Collections.reverse(evals_app);
            
            return evals_app;
        }
    }
    
    public List<AutoEvaluation> listerAutoevaluationsParCompetenceG(Apprenant a, CompetenceG c) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<AutoEvaluation> evals = listerAutoevaluationsParApprenant(a);
            List<AutoEvaluation> res = new ArrayList();
            
            for( AutoEvaluation e : evals)
            {
                if (e.getCompetence().getCompG().equals(c))
                {
                    res.add(e);
                }
            }
            
            return res;
        }
    }
    
    public List<Formation> listerFormationsParPersonne(Personne p) throws Throwable
    {
        if (p == null)
        {
            return null;
        }
        else
        {
            List<Formation> formations = listerFormations();
            
            List<Formation> res = new ArrayList();
            
            for (Formation f : formations)
            {
                List<Personne> responsables = f.getResponsables();
                
                for (Personne r : responsables)
                {
                    if (r.equals(p) && !res.contains(f))
                    {
                        res.add(f);
                    }
                }
            }
            
            return res;
        }
    }
    
    public List<Personne> listerResponsablesParFormation(Formation f) throws Throwable
    {
        if (f == null)
        {
            return null;
        }
        else
        {
            return f.getResponsables();
        }
    }
    
    // ------------- Méthodes d'ajout/retrait de compétences -------------------

    public Boolean ajouterCompetenceS(CompetenceG cg, CompetenceS cs) throws Throwable
    {
        if (cg == null || cs == null)
        {
            return false;
        }
        
        List<CompetenceS> compSpecs = cg.getCompSpec();
        
        Boolean contient = false;
        
        for (CompetenceS comps : compSpecs)
        {
            if (comps.equals(cs))
            {
                contient = true;
                break;
            }
        }
        
        if (!contient)
        {
            Double pondTotale = 0.;
            
            for (CompetenceS compS : compSpecs)
            {
                pondTotale += compS.getPonderation();
            }
            
            if (pondTotale + cs.getPonderation() <= 1)
            {
                compSpecs.add(cs);
                
                cs.setCompG(cg);

                try
                {
                    majObjet(cg);
                    majObjet(cs);
                }
                catch(Exception ex)
                 {
                     if (ex.getCause()!=null && ex.getCause() instanceof OptimisticLockException)
                     {
                         return ajouterCompetenceS(cg, cs);
                     }
                 }

                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public Boolean ajouterCompetenceG(Formation f, CompetenceG c) throws Throwable
    {
        if (f == null || c == null)
        {
            return false;
        }
        
        List<CompetenceG> competences = f.getCompetences();
        
        Boolean contient = false;
        
        for (CompetenceG comp : competences)
        {
            if (comp.equals(c))
            {
                contient = true;
                break;
            }
        }
        
        if (!contient)
        {
            competences.add(c);
            
            try
            {
                majObjet(f);
            }
            catch(Exception ex)
             {
                 if (ex.getCause()!=null && ex.getCause() instanceof OptimisticLockException)
                 {
                     return ajouterCompetenceG(f, c);
                 }
             }
            
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean retirerCompetenceS(CompetenceG cg, CompetenceS cs) throws Throwable
    {
        if (cg == null || cs == null)
        {
            return false;
        }
        
        List<CompetenceS> compSpecs = cg.getCompSpec();
        
        Boolean contient = false;
        CompetenceS aRetirer = null;
        
        for (CompetenceS comps : compSpecs)
        {
            if (comps.equals(cs))
            {
                contient = true;
                aRetirer = comps;
                break;
            }
        }
        
        if (contient)
        {
            compSpecs.remove(aRetirer);
            
            try
            {
                majObjet(cg);
            }
            catch(Exception ex)
             {
                 if (ex.getCause()!=null && ex.getCause() instanceof OptimisticLockException)
                 {
                     return retirerCompetenceS(cg, cs);
                 }
             }
            
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean retirerCompetenceG(Formation f, CompetenceG c) throws Throwable
    {
        if (f == null || c == null)
        {
            return false;
        }
        
        List<CompetenceG> competences = f.getCompetences();
        
        Boolean contient = false;
        CompetenceG aRetirer = null;
        
        for (CompetenceG comp : competences)
        {
            if (comp.equals(c))
            {
                contient = true;
                aRetirer = comp;
                break;
            }
        }
        
        if (contient)
        {
            competences.remove(aRetirer);
            
            try
            {
                majObjet(f);
            }
            catch(Exception ex)
             {
                 if (ex.getCause()!=null && ex.getCause() instanceof OptimisticLockException)
                 {
                     return retirerCompetenceG(f, c);
                 }
             }
            
            return true;
        }
        else
        {
            return false;
        }
    }
    
    // ----------------------- Méthodes de calcul ------------------------------

    public Boolean ajouterNote(Apprenant a, CompetenceS cs, Double note) throws Throwable
    {
        if (a == null || a.getFormation() == null || cs == null || note == null)
        {
            return false;
        }
        else
        {
            CompetenceG cg = cs.getCompG();
            
            if (cg != null)
            {
                Boolean contientComp = false;

                List<CompetenceG> competences = a.getFormation().getCompetences();

                for (CompetenceG c : competences)
                {
                    if (c.equals(cg))
                    {
                        contientComp = true;
                        break;
                    }
                }

                if (contientComp)
                {
                    List<Score> scores = listerScoresParApprenant(a);
                    List<Score> scores_spec = new ArrayList();
                    
                    Boolean create = false;
                    
                    for (Score s : scores)
                    {
                        if (s.getCompetence().equals(cs))
                        {
                            scores_spec.add(s);
                        }
                    }
                    
                    Collections.sort(scores_spec);
                    
                    if (!scores_spec.isEmpty())
                    {
                        Score last_score = scores_spec.get(scores_spec.size() - 1);

                        if (!last_score.getScore().equals(note))
                        {
                            create = true;
                        }
                    }
                    else
                    {
                        create = true;
                    }
                    
                    if (create)
                    {
                        Date d = new Date();

                        Score p = new Score("SCORE-" + a.getId() + "-" + cs.getId() + "-" + d.getDay() + "-" + d.getMonth() + "-" + d.getYear() + "-" + d.getHours() + "-" + d.getMinutes() + "-" + d.getSeconds(), cs, a, note, d);

                        creerObjet(p);
                    }
                    
                    calculerGrade(a, cs.getCompG());
                    
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }
    public Boolean ajouterAutoevaluation(Apprenant a, CompetenceS cs, Double valeur) throws Throwable
    {
        if (a == null || a.getFormation() == null || cs == null || valeur == null)
        {
            return false;
        }
        else
        {
            CompetenceG cg = cs.getCompG();
            
            if (cg != null)
            {
                Boolean contientComp = false;

                List<CompetenceG> competences = a.getFormation().getCompetences();

                for (CompetenceG c : competences)
                {
                    if (c.equals(cg))
                    {
                        contientComp = true;
                        break;
                    }
                }

                if (contientComp)
                {
                    List<AutoEvaluation> evals = listerAutoevaluationsParApprenant(a);
                    List<AutoEvaluation> evals_spec = new ArrayList();
                    
                    Boolean create = false;
                    
                    for (AutoEvaluation e : evals)
                    {
                        if (e.getCompetence().equals(cs))
                        {
                            evals_spec.add(e);
                        }
                    }
                    
                    Collections.sort(evals_spec);
                    
                    if (!evals_spec.isEmpty())
                    {
                        AutoEvaluation last_eval = evals_spec.get(evals_spec.size() - 1);

                        if (!last_eval.getValeur().equals(valeur))
                        {
                            create = true;
                        }
                    }
                    else
                    {
                        create = true;
                    }
                    
                    if (create)
                    {
                        Date d = new Date();

                        AutoEvaluation e = new AutoEvaluation("AUTOEVALUATION-" + a.getId() + "-" + cs.getId() + "-" + d.getDay() + "-" + d.getMonth() + "-" + d.getYear() + "-" + d.getHours() + "-" + d.getMinutes() + "-" + d.getSeconds(), a, cs, valeur, d);

                        creerObjet(e);
                    }
                    
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public Grade.ValeurGrade calculerGrade(Apprenant a, CompetenceG cg) throws Throwable
    {
        if (a == null || a.getFormation() == null || cg == null)
        {
            return null;
        }
        else
        {
            List<CompetenceG> compG = a.getFormation().getCompetences();
            
            Boolean contient = false;
            
            for (CompetenceG c : compG)
            {
                if (c.equals(cg))
                {
                    contient = true;
                    break;
                }
            }
            
            if (!contient)
            {
                return null;
            }
            else
            {
                List<CompetenceS> compSpecs = cg.getCompSpec();
                List<Score> scores = listerScoresParApprenant(a);
                
                Boolean complet = true;
        
                for (CompetenceS cs : compSpecs)
                {
                    Boolean contientS = false;
                    
                    for (Score p : scores)
                    {
                        if (p.getCompetence().equals(cs))
                        {
                            contientS = true;
                            break;
                        }
                    }
                    
                    if (!contientS)
                    {
                        complet = false;
                        break;
                    }
                }
                
                Grade.ValeurGrade g;
                
                if (complet)
                {
                    Double note = 0.;
                    
                    for (Score p : scores)
                    {
                        if (p.getCompetence().getCompG().equals(cg))
                        {
                            note += p.getScore() * p.getCompetence().getPonderation();
                        }
                    }
                    
                    if (note >= cg.getSeuilMax())
                    {
                        g = Grade.ValeurGrade.Acquis;
                    }
                    else if (note <= cg.getSeuilMin())
                    {
                        g = Grade.ValeurGrade.NonAcquis;
                    }
                    else
                    {
                        g = Grade.ValeurGrade.EnCours;
                    }  
                }
                else
                {
                    g = null;
                }
                
                List<Grade> grades = listerGradesParApprenant(a);

                Boolean contientG = false;

                for (Grade p : grades)
                {
                    if (p.getCompetence().equals(cg))
                    {
                        contientG = true;
                        
                        if (g != null)
                        {
                            p.setGrade(g);
                        }
                        else
                        {
                            grades.remove(p);
                        }

                        majObjet(p);

                        break;
                    }
                }

                if (!contientG)
                {
                    Grade p = new Grade(cg, a, g);

                    if (creerObjet(p))
                    {
                        grades.add(p);
                        
                        majObjet(a);
                    }
                }
                
                return g;
            }
        } 
    }
    
    // -------------------- Méthodes de statistiques ---------------------------

    public Double calculerMoyenneCompetenceG(Apprenant a, CompetenceG c) throws Throwable
    {
        if (a == null && a.getFormation() == null || c == null)
        {
            return null;
        }
        else
        {
            Boolean contient = false;
            
            List<CompetenceG> comp = a.getFormation().getCompetences();
            
            for (CompetenceG cg : comp)
            {
                if (cg.equals(c))
                {
                    contient = true;
                    break;
                }
            }
            
            if (contient)
            {
                List<Score> scores = listerScoresParApprenant(a);
                
                Double moyenne = 0.;
                
                for (Score p : scores)
                {
                    if (p.getCompetence().getCompG().equals(c))
                    {
                        moyenne += p.getScore() * p.getCompetence().getPonderation();
                    }
                }
                
                return moyenne;
            }
            else
            {
                return null;
            }
        }
    }
    
    // --------------------- Méthodes utilitaires ------------------------------
    
    public Boolean creerObjet(Object o) throws Throwable
    {
        if (o == null)
        {
            return false;
        }
        else
        {
            Boolean classeOk = true;
            
            JpaUtil.creerEntityManager();

            JpaUtil.ouvrirTransaction();

            if (o instanceof Apprenant)
            {
                new ApprenantDao().create((Apprenant)o);
            }
            else if (o instanceof CompetenceG)
            {
                new CompetenceGDao().create((CompetenceG)o);
            }
            else if (o instanceof CompetenceS)
            {
                new CompetenceSDao().create((CompetenceS)o);
            }
            else if (o instanceof Formation)
            {
                new FormationDao().create((Formation)o);
            }
            else if (o instanceof Personne)
            {
                new PersonneDao().create((Personne)o);
            }
            else if (o instanceof RulePattern)
            {
                new RulePatternDao().create((RulePattern)o);
            }
            else if (o instanceof Regle)
            {
                new RegleDao().create((Regle)o);
            }
            else if (o instanceof Grade)
            {
                new GradeDao().create((Grade)o);
            }
            else if (o instanceof Score)
            {
                new ScoreDao().create((Score)o);
            }
            else if (o instanceof AutoEvaluation)
            {
                new AutoEvaluationDao().create((AutoEvaluation)o);
            }
            else if (o instanceof MiseEnSituation)
            {
                new MiseEnSituationDao().create((MiseEnSituation)o);
            }
            else
            {
                classeOk = false;
            }
            
            JpaUtil.validerTransaction();
            
            JpaUtil.fermerEntityManager();
            
            return classeOk;
        }
    }
    
    public Boolean majObjet(Object o) throws Throwable
    {
        if (o == null)
        {
            return false;
        }
        else
        {
            Boolean classeOk = true;
            
            JpaUtil.creerEntityManager();

            JpaUtil.ouvrirTransaction();

            if (o instanceof Apprenant)
            {
                new ApprenantDao().update((Apprenant)o);
            }
            else if (o instanceof CompetenceG)
            {
                new CompetenceGDao().update((CompetenceG)o);
            }
            else if (o instanceof CompetenceS)
            {
                new CompetenceSDao().update((CompetenceS)o);
            }
            else if (o instanceof Formation)
            {
                new FormationDao().update((Formation)o);
            }
            else if (o instanceof Personne)
            {
                new PersonneDao().update((Personne)o);
            }
            else if (o instanceof RulePattern)
            {
                new RulePatternDao().update((RulePattern)o);
            }
            else if (o instanceof Regle)
            {
                new RegleDao().update((Regle)o);
            }
            else if (o instanceof Grade)
            {
                new GradeDao().update((Grade)o);
            }
            else if (o instanceof Score)
            {
                new ScoreDao().update((Score)o);
            }
            else if (o instanceof AutoEvaluation)
            {
                new AutoEvaluationDao().update((AutoEvaluation)o);
            }
            else if (o instanceof MiseEnSituation)
            {
                new MiseEnSituationDao().update((MiseEnSituation)o);
            }
            else
            {
                classeOk = false;
            }
            
            JpaUtil.validerTransaction();
            
            JpaUtil.fermerEntityManager();
            
            return classeOk;
        }
    }
    
    public Boolean supprimerObjet(Object o) throws Throwable
    {
        if (o == null)
        {
            return false;
        }
        else
        {
            Boolean classeOk = true;
            
            JpaUtil.creerEntityManager();

            JpaUtil.ouvrirTransaction();

            if (o instanceof Apprenant)
            {
                new ApprenantDao().remove((Apprenant)o);
            }
            else if (o instanceof CompetenceG)
            {
                new CompetenceGDao().remove((CompetenceG)o);
            }
            else if (o instanceof CompetenceS)
            {
                new CompetenceSDao().remove((CompetenceS)o);
            }
            else if (o instanceof Formation)
            {
                new FormationDao().remove((Formation)o);
            }
            else if (o instanceof Personne)
            {
                new PersonneDao().remove((Personne)o);
            }
            else if (o instanceof RulePattern)
            {
                new RulePatternDao().remove((RulePattern)o);
            }
            else if (o instanceof Regle)
            {
                new RegleDao().remove((Regle)o);
            }
            else if (o instanceof Grade)
            {
                new GradeDao().remove((Grade)o);
            }
            else if (o instanceof Score)
            {
                new ScoreDao().remove((Score)o);
            }
            else if (o instanceof AutoEvaluation)
            {
                new AutoEvaluationDao().remove((AutoEvaluation)o);
            }
            else if (o instanceof MiseEnSituation)
            {
                new MiseEnSituationDao().remove((MiseEnSituation)o);
            }
            else
            {
                classeOk = false;
            }
            
            JpaUtil.validerTransaction();
            
            JpaUtil.fermerEntityManager();
            
            return classeOk;
        }
    }
    
    // ----------------------- Autres méthodes ---------------------------------

    public Boolean assignerFormation (Apprenant a, Formation f) throws Throwable
    {
        if (a == null || f == null)
        {
            return false;
        }
        else
        {
            if (a.getFormation() != null && a.getFormation().equals(f))
            {
                return false;
            }
            else
            {
                a.setFormation(f);
                
                return majObjet(a);
            }
        }
    }
    
    public Boolean assignerFormation(Personne p, Formation f) throws Throwable
    {
        if (p == null || f == null)
        {
            return false;
        }
        else
        {
            List<Personne> responsables = f.getResponsables();
            
            for (Personne r : responsables)
            {
                if (r.equals(p))
                {
                    return false;
                }
            }
            responsables.add(p);
            
            return majObjet(f);
        }
    }
}
