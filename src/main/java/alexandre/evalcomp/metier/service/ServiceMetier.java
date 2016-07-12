/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.service; 

import alexandre.evalcomp.dao.*;
import alexandre.evalcomp.metier.modele.*;
import alexandre.evalcomp.metier.modele.Personne.TypePersonne;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.OptimisticLockException;

/**
 *
 * @author Alex-Laptop
 */
public class ServiceMetier
{
    // ----------------------- Méthodes de création ----------------------------
    
    /**
     * Cette méthode permet de créer une nouvelle formation.
     * @param libelle nom de la formation
     * @param domaine domaine de la formation
     * @param url URL de la formation
     * @param duree durée (en heures) de la formation
     * @param date date du début de la formation
     * @return Renvoie la formation nouvellement créée, si celle-ci n'existait pas auparavant, renvoie null sinon.
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de créer une compétence générale. Elle devra être ajoutée par la suite à une formation.
     * Il est possible de créer plusieurs compétences portant le même nom.
     * @param libelle nom de la compétence
     * @param type type de la compétence
     * @param propriete propriété de la compétence
     * @param seuilMin seuil en-dessous duquel une compétence ne peut être acquise
     * @param seuilMax seuil au-delà duquel une compétence est acquise
     * @return Renvoie la compétence nouvellement créée.
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de créer une compétence spécifique. Elle devra être ajoutée par la suite à une compétence générale.
     * Il est possible de créer plusieurs compétences spécifiques portant le même nom.
     * @param libelle nom de la compétence
     * @param type type de la compétence
     * @param propriete propriété de la compétence
     * @param ponderation coefficient de la compétence spécifique dans sa compétence générale
     * @param regle règle à appliquer à la compétence spécifique
     * @return Renvoie la compétence nouvellement créée.
     * @throws java.lang.Throwable
     */
    
    public CompetenceS creerCompetenceS(String id, String libelle, String type, Double ponderation, Regle regle, String miseEnSituation) throws Throwable
    {
        if (trouverCompetenceSParId(id) == null)
        {
            CompetenceS c = new CompetenceS(id, libelle, type, ponderation, regle, miseEnSituation);

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
    
    /**
     * Cette méthode permet de créer une nouvelle personne.
     * @param nom nom de la personne
     * @param niveau niveau de la personne
     * @param type type de la personne (Formateur/Coordonateur)
     * @return Renvoie la personne nouvellement créée, si celle-ci n'existait pas aupravant, renvoie null sinon.
     * @throws java.lang.Throwable
     */
    
    public Personne creerPersonne(String id, String nom, String niveau, TypePersonne type) throws Throwable
    {
        Personne p = new Personne(id, nom, niveau, type);
        
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
    
    /**
     * Cette méthode permet de créer un apprenant.
     * @param nom nom de l'apprenant
     * @param niveau niveau de l'apprenant
     * @return Renvoie l'apprenant nouvellement créé.
     * @throws java.lang.Throwable
     */
    
    public Apprenant creerApprenant(String id, String nom, String fonction, String entreprise) throws Throwable
    {
        if (trouverApprenantParId(id) == null)
        {
            Apprenant a = new Apprenant(id, nom, fonction, entreprise);

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
    
    /**
     * Cette méthode permet de créer une nouvelle règle de calcul.
     * Il est possible de créer plusieurs règles portant le même nom.
     * @param libelle nom de la règle
     * @param calcul formule sur laquelle la règle se base
     * @return Renvoie la règle nouvellement créée si celle-ci n'existe pas déjà, renvoie null sinon.
     * @throws java.lang.Throwable
     */
    
    public Regle creerRegle(String id, String libelle, List<String> texte) throws Throwable
    {
        if (trouverRegleParId(id) == null)
        {
            Regle r = new Regle(id, libelle, texte);

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
    
    /**
     * Cette méthode permet de supprimer une formation, ainsi que tous les liens qu'elle possède avec d'autres objets.
     * Deux formations ne peuvent porter le même nom.
     * @param f la formation à supprimer
     * @return Renvoie vrai si la formation a effectivement été supprimée, renvoie faux sinon.
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de supprimer une personne.
     * @param p la personne à supprimer
     * @return Renvoie vrai si la personne a effectivement été supprimée, renvoie faux sinon.
     * @throws Throwable 
     */
    
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
    
    /**
     * Cette méthode permet de supprimer le score d'un apprenant dans une compétence spécifique.
     * @param a l'apprenant concerné
     * @param c la compétence spécifique concernée
     * @return Renvoie vrai si le score a effectivement été supprimé, renvoie faux sinon.
     * @throws Throwable 
     */
    
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
    
    /**
     * Cette méthode permet de supprimer une règle.
     * @param r la règle à supprimer
     * @return Renvoie vrai si la règle a effectivement été supprimée, renvoie faux sinon.
     * @throws Throwable 
     */
    
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
    
    public Regle trouverRegleParId(String id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        RegleDao dao = new RegleDao();
        
        Regle r = dao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return r;
    }
    
    // -------------- Méthodes de recherche par nom/libellé --------------------
    
    /**
     * Cette méthode permet de trouver un apprenant par son nom..
     * @param nom nom de l'apprenant recherché
     * @return Renvoie la liste des apprenant correspondant au nom fourni.
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de rechercher une formation par son libellé.
     * @param libelle le libellé de la formation recherchée
     * @return Renvoie la formation correspondant au libellé, si elle a été trouvée, renvoie null sinon.
     * @throws Throwable 
     */
    
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
    
    /**
     * Cette méthode permet de trouver une personne par son nom.
     * @param nom le nom de la personne recherchée
     * @return Renvoie la liste des personnes correspondant au nom fourni.
     * @throws Throwable 
     */
    
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
    
    /**
     * Cette méthode permet de trouver une compétence générale par son libellé.
     * @param libelle nom de la compétence recherchée
     * @return Renvoie la liste des compétences générales portant le nom fourni.
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de trouver une compétence spécifique par son libellé.
     * @param libelle nom de la compétence recherchée
     * @return Renvoie la liste des compétences portant le nom fourni.
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de trouver une règle de calcul par son libellé.
     * @param libelle nom de la règle recherchée
     * @return Renvoie la règle correspondant au libellé, si celle-ci existe, renvoie null sinon.
     * @throws java.lang.Throwable
     */
    
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
    
    // --------------------- Méthodes de listing -------------------------------
    
    public List<Personne> listerPersonnes() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        PersonneDao dao = new PersonneDao();
        
        List<Personne> personnes = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return personnes;
    }
    
    /**
     * Cette méthode permet de lister toutes les formations existantes.
     * @return Renvoie la liste des formations.
     * @throws java.lang.Throwable
     */
    
    public List<Formation> listerFormations() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        FormationDao dao = new FormationDao();
        
        List<Formation> formations = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return formations;
    }
    
    /**
     * Cette méthode permet de lister toutes les compétences générales existantes.
     * @return Renvoie la liste des compétences générales.
     * @throws java.lang.Throwable
     */
    
    public List<CompetenceG> listerCompetenceG() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        CompetenceGDao dao = new CompetenceGDao();
        
        List<CompetenceG> competences = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return competences;
    }
    
    /**
     * Cette méthode permet de lister toutes les compétences spécifiques existantes.
     * @return Renvoie la liste des compétences spécifiques.
     * @throws java.lang.Throwable
     */
    
    public List<CompetenceS> listerCompetenceS() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        CompetenceSDao dao = new CompetenceSDao();
        
        List<CompetenceS> competences = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return competences;
    }
    
    /**
     * Cette méthode permet de lister tous les apprenants.
     * @return Renvoie la liste des apprenants.
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de lister toutes les règles existantes.
     * @return Renvoie la liste des règles.
     * @throws java.lang.Throwable
     */
    
    public List<Regle> listerRegles() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        RegleDao dao = new RegleDao();
        
        List<Regle> regles = dao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return regles;
    }
    
    /**
     * Cette méthode permet de lister tous les grades obtenus par un apprenant dans sa formation.
     * @param a l'apprenant concerné
     * @return Renvoie la liste des grades (couplés à leur compétence générale respective) si elle a été trouvée, renvoie null sinon.
     */
    
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
    
    /**
     * Cette méthode permet de lister tous les scores d'un apprenant.
     * @param a l'apprenant concerné
     * @return Renvoie la liste des scores (couplés à leur compétence spécifique respective) si elle a été trouvée, renvoie null sinon.
     */
    
    public List<Score> listerScoresParApprenant(Apprenant a) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<Score> scores = listerScores();
            List<Score> res = new ArrayList();
            
            for (Score g : scores)
            {
                if (g.getApprenant().equals(a))
                {
                    res.add(g);
                }
            }
            
            return res;
        }
    }
    
    /**
     * Cette méthode permet de lister les scores d'un apprenant dans une compétence générale particulière.
     * @param a l'apprenant concerné
     * @param c la compétence générale voulue
     * @return Renvoie la liste des scores (couplés à leur compétence spécifique respective) obtenus par un apprenant dans une compétence générale, si cette liste a été trouvée, renvoie null sinon.
     */
    
    public List<Score> listerScoresParCompetenceG(Apprenant a, CompetenceG c) throws Throwable
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            List<Score> scores = listerScoresParApprenant(a);
            List<Score> res = new ArrayList();
            
            for( Score p : scores)
            {
                if (p.getCompetence().getCompG().equals(c))
                {
                    res.add(p);
                }
            }
            
            return res;
        }
    }
    
    public List<Formation> listerFormationsParPersonne(Personne p) throws Throwable
    {
        if (p == null || !p.getType().equals(TypePersonne.Coordonateur))
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
    
    /**
     * Cette méthode permet d'ajouter une compétence spécifique à une compétence générale.
     * @param cg la compétence générale concernée
     * @param cs la compétence spécifique à ajouter
     * @return Renvoie vrai si la compétence spécifique a bien été ajoutée, renvoie faux sinon (par exemple si elle était déjà présente dans la compétence générale).
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet d'ajouter une compétence générale à une formation
     * @param f la formation concernée
     * @param c la compétence générale à ajouter
     * @return Renvoie vrai si la compétence a bien été ajoutée à la formation, renvoie faux sinon (par exemple si elle était déjà présente dans la formation).
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de retirer une compétence spécifique à une compétence générale
     * @param cg la compétence générale concernée
     * @param cs la compétence spécifique à retirer
     * @return Renvoie vrai si la compétence spécifique a bien été retirée de la compétence générale, renvoie faux sinon (par exemple si elle ne se trouvait pas dans la liste des compétences spécifiques dans la compétence générale).
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de retirer une compétence générale à une formation
     * @param f la formation concernée
     * @param c la compétence générale à retirer
     * @return Renvoie vrai si la compétence générale a bien été retirée de la formation, renvoie faux sinon (par exemple si elle ne se trouvait pas dans la liste des compétences spécifiques dans la formation).
     * @throws java.lang.Throwable
     */
    
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
    
    /**
     * Cette méthode permet de calculer la note d'un apprenant dans une compétence spécifique.
     * Si cette note existait déjà, elle sera remplacée.
     * Le grade de l'apprenant dans la compétence générale concernée sera également mis à jour, si nécessaire.
     * @param a l'apprenant concerné
     * @param cs la compétence spécifique pour laquelle il faut calculer la note
     * @param r la règle à appliquer
     * @param param la liste des paramètres pris en compte par la règle
     * @return Renvoie vrai si la note a effectivement été calculée, renvoie faux sinon.
     * @throws Throwable 
     */
    
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

                    Score p;
                    Boolean contientScore = false;

                    for (Score ps : scores)
                    {
                        if (ps.getCompetence().equals(cs))
                        {
                            ps.setScore(note);

                            majObjet(ps);

                            contientScore = true;

                            break;
                        }
                    }

                    if (!contientScore)
                    {
                        p = new Score(cs, a, note);

                        if (creerObjet(p))
                        {
                            scores.add(p);
                        }
                    }

                    majObjet(a);

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
    
    /**
     * Cette méthode permet de calculer le grade d'un apprenant dans une compétence générale.
     * Il ne sera caculé que si toutes les compétences spécifiques découlant de la compétence générale ont un score.
     * Le grade sera mis à jour automatiquement.
     * @param a l'apprenant concerné
     * @param cg la compétence générale pour laquelle le grade sera calculé
     * @return Renvoie le grade s'il a effectivement été calculé, renvoie null sinon.
     * @throws Throwable 
     */
    
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
                        note += p.getScore() * p.getCompetence().getPonderation();
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
    
    /**
     * Cette méthode permet de calculer la moyenne d'un apprenant dans une compétence générale.
     * Elle sera calculée même si toutes les compétences spécifiques découlant de la compétence générale n'ont pas de score.
     * La formule utilisée dqns tous les cas est : moyenne = somme(score * pondération).
     * @param a l'apprenant concerné
     * @param c la compétence générale pour laquelle on veut calculer la moyenne
     * @return Renvoie la moyenne si elle a effectivement été calculée, renvoie null sinon.
     */
    
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
    
    /**
     * Cette méthode permet de donner une formation à un apprenant.
     * @param a l'apprenant concerné
     * @param f la formation à assigner
     * @return Renvoie vrai si la formation a effectivement été assignée, renvoie faux sinon.
     * @throws Throwable 
     */
    
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
        if (p == null || f == null || !p.getType().equals(TypePersonne.Coordonateur))
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
            
            return majObjet(p);
        }
    }
}
