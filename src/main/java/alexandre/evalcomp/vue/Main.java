///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package alexandre.evalcomp.vue;
//
//import alexandre.evalcomp.metier.modele.*;
//import alexandre.evalcomp.metier.service.ServiceMetier;
//import alexandre.evalcomp.metier.modele.Calcul;
//import alexandre.evalcomp.metier.modele.Grade;
//import alexandre.evalcomp.metier.modele.Score;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//
///**
// *
// * @author Alex-Laptop
// */
//public class Main {
// 
//    public static void main(String[] args) throws Throwable
//    {        
//        ServiceMetier servM = new ServiceMetier();
//        
//        Scanner sc = new Scanner(System.in);
//        
//        Boolean quitter = false;
//        
//        final int nbChoix = 33;
//        
//        while(!quitter)
//        {
//            System.out.println("Que voulez-vous faire ?");
//            System.out.println("1. Créer un apprenant");
//            System.out.println("2. Créer une formation");
//            System.out.println("3. Créer une compétence générale");
//            System.out.println("4. Créer une compétence spécifique");
//            System.out.println("5. Créer une personne");
//            System.out.println("6. Créer une règle");
//            System.out.println("7. Supprimer une formation");
//            System.out.println("8. Trouver un apprenant par son nom");
//            System.out.println("9. Trouver une personne par son nom");
//            System.out.println("10. Trouver une formation par son libellé");
//            System.out.println("11. Trouver une compétence générale par son libellé");
//            System.out.println("12. Trouver une compétence spécifique par son libellé");
//            System.out.println("13. Trouver une règle par son libellé");
//            System.out.println("14. Afficher la liste des apprenants");
//            System.out.println("15. Afficher la liste des formations");
//            System.out.println("16. Afficher la liste des compétences générales");
//            System.out.println("17. Afficher la liste des compétences spécifiques");
//            System.out.println("18. Afficher la liste des règles");
//            System.out.println("19. Afficher la liste des compétences générales d'une formation");
//            System.out.println("20. Afficher la liste des compétences spécifiques d'une compétence générale");
//            System.out.println("21. Ajouter une compétence générale à une formation");
//            System.out.println("22. Ajouter une compétence spécifique à une compétence générale");
//            System.out.println("23. Retirer une compétence générale d'une formation");
//            System.out.println("24. Retirer une compétence spécifique d'une compétence générale");
//            System.out.println("25. Assigner une formation à un apprenant");
//            System.out.println("26. Donner une note à un apprenant");
//            System.out.println("27. Calculer la moyenne d'un apprenant dans une compétence générale");
//            System.out.println("28. Supprimer un score d'un apprenant");
//            System.out.println("29. Supprimer une règle");
//            System.out.println("30. Lister les grades d'un apprenant");
//            System.out.println("31. Lister les scores d'un apprenant");
//            System.out.println("32. Lister les scores d'un apprenant par compétence générale");
//            System.out.println(nbChoix + ". Quitter");
//            
//            int choix = sc.nextInt();
//            sc.nextLine();
//            
//            String nom, niveau, libelle, domaine, url, type, propriete;
//            Long id;
//            Integer duree, typePersonne;
//            Double ponderation, seuilMin, seuilMax;
//            Date date;
//            Boolean resultat;
//            
//            Apprenant a;
//            Formation f;
//            CompetenceG cg;
//            CompetenceS cs;
//            Personne p;
//            Regle r;
//            
//            List<Apprenant> apprenants;
//            List<Formation> formations;
//            List<CompetenceG> compG;
//            List<CompetenceS> compS;
//            List<Regle> regles;
//            List<Grade> grades;
//            List<Score> scores;
//            List<Personne> personnes;
//            
//            if (choix >= 1 && choix <= nbChoix)
//            {
//                switch(choix)
//                {
//                    case 1:
//                        // Créer un apprenant
//                        System.out.print("Entrez le nom de l'apprenant : ");
//                        nom = sc.nextLine();
//                        System.out.print("Entrez le niveau de l'apprenant : ");
//                        niveau = sc.nextLine();
//                        
//                        System.out.println(servM.creerApprenant(nom, niveau));
//                        
//                        break;
//                        
//                    case 2:
//                        //Créer une formation
//                        System.out.print("Entrez le libellé de la formation : ");
//                        libelle = sc.nextLine();
//                        System.out.print("Entrez le domaine de la formation : ");
//                        domaine = sc.nextLine();
//                        System.out.print("Entrez l'URL de la formation : ");
//                        url = sc.nextLine();
//                        System.out.print("Entrez la durée de la formation : ");
//                        duree = sc.nextInt();
//                        sc.nextLine();
//                        date = new Date();
//                        
//                        f = servM.creerFormation(libelle, domaine, url, duree, date);
//                        
//                        if (f != null)
//                        {
//                            System.out.println(f);
//                        }
//                        else
//                        {
//                            System.out.println("Une formation du même nom existe déjà !");
//                        }
//                        
//                        break;
//                        
//                    case 3:
//                        // Créer une compétence générale
//                        System.out.print("Entrez le libellé de la compétence : ");
//                        libelle = sc.nextLine();
//                        
//                        System.out.print("Entrez le type de la compétence : ");
//                        type = sc.nextLine();
//                        
//                        System.out.print("Entrez la propriété de la compétence : ");
//                        propriete = sc.nextLine();
//                        
//                        System.out.print("Entrez le seuil minimal de la compétence : ");
//                        seuilMin = sc.nextDouble();
//                        
//                        System.out.print("Entrez le seuil maximal de la compétence : ");
//                        seuilMax = sc.nextDouble();
//                        
//                        cg = servM.creerCompetenceG(libelle, type, propriete, seuilMin, seuilMax, new ArrayList());
//                        
//                        if (cg != null)
//                        {
//                            System.out.println(cg);
//                        }
//                        else
//                        {
//                            System.out.println("Une compétence générale du même nom existe déjà !");
//                        }
//                        
//                        break;
//                    
//                    case 4:
//                        // Créer une compétence spécifique
//                        System.out.print("Entrez le libellé de la compétence : ");
//                        libelle = sc.nextLine();
//                        System.out.print("Entrez le type de la compétence : ");
//                        type = sc.nextLine();
//                        System.out.print("Entrez la propriété de la compétence : ");
//                        propriete = sc.nextLine();
//                        System.out.print("Entrez la ponderation de la compétence : ");
//                        ponderation = sc.nextDouble();
//                        sc.nextLine();
//                        System.out.print("Entrez le libellé de la règle à appliquer : ");
//                        String libRegle = sc.nextLine();
//                        
//                        r = servM.trouverRegleParLibelle(libRegle).get(0);
//                        
//                        cs = servM.creerCompetenceS(libelle, type, propriete, ponderation, r);
//                        
//                        if (cs != null)
//                        {
//                            System.out.println(cs);
//                        }
//                        else
//                        {
//                            System.out.println("Une compétence spécifique du même nom existe déjà !");
//                        }
//                        
//                        break;
//                        
//                    case 5:
//                        //Créer une personne
//                        System.out.print("Entrez le nom de la personne : ");
//                        nom = sc.nextLine();
//                        System.out.print("Entrez le niveau de la personne : ");
//                        niveau = sc.nextLine();
//                        System.out.print("Entrez le type de la personne(1 : Formateur, 2 : Coordonateur) : ");
//                        typePersonne = sc.nextInt();
//                        sc.nextLine();
//                        
//                        if (typePersonne == 1)
//                        {
//                            p = servM.creerPersonne(nom, niveau, Personne.TypePersonne.Formateur);
//                        }
//                        else
//                        {
//                            p = servM.creerPersonne(nom, niveau, Personne.TypePersonne.Coordonateur);
//                        }
//                        
//                        System.out.println(p);
//                        
//                        break;
//                        
//                    case 6:
//                        // Créer une règle
//                        System.out.print("Entrez le libellé de la règle : ");
//                        libelle = sc.nextLine();
//                        
//                        Calcul test = (Object l) ->
//                        {
//                            List<Boolean> li = (List<Boolean>) l;
//                            
//                            Double res = 10.;
//
//                            for (Boolean b : li)
//                            {
//                                if (res == 0.)
//                                {
//                                    break;
//                                }
//                                else if (!b)
//                                {
//                                    res--;
//                                }
//                            }
//
//                            return res;
//                        };
//                        
//                        r = servM.creerRegle(libelle, test);
//                        
//                        if (r != null)
//                        {
//                            System.out.println(r);
//                        }
//                        else
//                        {
//                            System.out.println("La règle n'a pas été créée !");
//                        }
//                        
//                        break;
//                        
//                    case 7:
//                        //Supprimer une formation
//                        System.out.print("Entrez le libellé de la formation : ");
//                        libelle = sc.nextLine();
//                        
//                        f = servM.trouverFormationParLibelle(libelle).get(0);
//                        
//                        resultat = servM.supprimerFormation(f);
//                        
//                        if (resultat)
//                        {
//                            System.out.println("La formation a été supprimée !");
//                        }
//                        else
//                        {
//                            System.out.println("Cette formation n'existe pas !");
//                        }
//                        
//                        break;
//                        
//                    case 8:
//                        //Trouver un apprenant par son nom
//                        System.out.print("Entrez le nom de l'apprenant : ");
//                        nom = sc.nextLine();
//                        
//                        apprenants = servM.trouverApprenantParNom(nom);
//                        
//                        if (!apprenants.isEmpty())
//                        {
//                            for (Apprenant app : apprenants)
//                            {
//                                System.out.println(app);
//                            }
//                        }
//                        else
//                        {
//                            System.out.println("Aucun apprenant ne porte ce nom !");
//                        }
//                        
//                        break;
//                        
//                    case 9:
//                        // Trouver une personne par son nom
//                        System.out.print("Entrez le nom de la personne : ");
//                        nom = sc.nextLine();
//                        
//                        personnes = servM.trouverPersonneParNom(nom);
//                        
//                        if (!personnes.isEmpty())
//                        {
//                            for (Personne pers : personnes)
//                            {
//                                System.out.println(pers);
//                            }
//                        }
//                        else
//                        {
//                            System.out.println("Aucune personne ne porte ce nom !");
//                        }
//                        
//                        break;
//                        
//                    case 10:
//                        //Trouver une formation par son libellé
//                        System.out.print("Entrez le libellé de la formation : ");
//                        libelle = sc.nextLine();
//                        
//                        f = servM.trouverFormationParLibelle(libelle).get(0);
//                        
//                        if (f != null)
//                        {
//                            System.out.println(f);
//                        }
//                        else
//                        {
//                            System.out.println("Cette formation n'existe pas !");
//                        }
//                        
//                        break;
//                        
//                    case 11:
//                        // Trouver une compétence générale par son libellé
//                        System.out.print("Entrez le libellé de la compétence générale : ");
//                        libelle = sc.nextLine();
//                        
//                        compG = servM.trouverCompetenceGParLibelle(libelle);
//                        
//                        if (!compG.isEmpty())
//                        {
//                            for (CompetenceG c : compG)
//                            {
//                                System.out.println(c);
//                            }
//                        }
//                        else
//                        {
//                            System.out.println("Auncune compétence générale ne porte ce nom !");
//                        }
//                        
//                        break;
//                        
//                    case 12:
//                        // Trouver une compétence spécifique par son libellé
//                        System.out.print("Entrez le libellé de la compétence spécifique : ");
//                        libelle = sc.nextLine();
//                        
//                        compS = servM.trouverCompetenceSParLibelle(libelle);
//                        
//                        if (!compS.isEmpty())
//                        {
//                            for (CompetenceS c : compS)
//                            {
//                                System.out.println(c);
//                            }
//                        }
//                        else
//                        {
//                            System.out.println("Aucune compétence spécifique ne porte ce nom !");
//                        }
//                        
//                        break;
//                        
//                    case 13:
//                        // Trouver une règle par son libellé
//                        System.out.print("Entrez le libellé de la règle : ");
//                        libelle = sc.nextLine();
//                        
//                        regles = servM.trouverRegleParLibelle(libelle);
//                        
//                        if (!regles.isEmpty())
//                        {
//                            for (Regle re : regles)
//                            {
//                                System.out.println(re);
//                            }
//                        }
//                        else
//                        {
//                            System.out.println("Aucune règle ne porte ce nom !");
//                        }
//                        
//                        break;
//                        
//                    case 14:
//                        // Afficher la liste des apprenants
//                        apprenants = servM.listerApprenants();
//                        
//                        for (Apprenant ap : apprenants)
//                        {
//                            System.out.println(ap);
//                        }
//                        
//                        break;
//                        
//                    case 15:
//                        // Afficher la liste des formations
//                        formations = servM.listerFormations();
//                        
//                        for (Formation fo : formations)
//                        {
//                            System.out.println(fo);
//                        }
//                        
//                        break;
//                        
//                    case 16:
//                        // Afficher la liste des compétences générales
//                        compG = servM.listerCompetenceG();
//                        
//                        for (CompetenceG co : compG)
//                        {
//                            System.out.println(co);
//                        }
//                        
//                        break;
//                        
//                    case 17:
//                        // Afficher la liste des compétences spécifiques
//                        compS = servM.listerCompetenceS();
//                        
//                        for (CompetenceS co : compS)
//                        {
//                            System.out.println(co);
//                        }
//                        
//                        break;
//                        
//                    case 18:
//                        // Afficher la liste des règles
//                        regles = servM.listerRegles();
//                        
//                        for (Regle re : regles)
//                        {
//                            System.out.println(re);
//                        }
//                        
//                        break;
//                        
//                    case 19:
//                        //Afficher la liste des compétences générales d'une formation
//                        System.out.print("Entrez le libellé de la formation : ");
//                        libelle = sc.nextLine();
//                        
//                        f = servM.trouverFormationParLibelle(libelle).get(0);
//                        
//                        if (f != null)
//                        {
//                            compG = f.getCompetences();
//
//                            for (CompetenceG co : compG)
//                            {
//                                System.out.println(co);
//                            }
//                        }
//                                                
//                        
//                        break;
//                        
//                    case 20:
//                        //Afficher la liste des compétences spécifiques d'une compétence générale
//                        System.out.print("Entrez l'id de la compétence générale : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cg = servM.trouverCompetenceGParId(id);
//                        
//                        if (cg != null)
//                        {
//                            compS = cg.getCompSpec();
//
//                            for (CompetenceS co : compS)
//                            {
//                                System.out.println(co);
//                            }
//                        }
//                        else
//                        {
//                            System.out.println("Cette compétence générale n'existe pas !");
//                        }
//                        
//                        
//                        break;
//                        
//                    case 21:
//                        // Ajouter une compétence générale à une formation
//                        System.out.print("Entrez le libellé de la formation : ");
//                        libelle = sc.nextLine();
//                        
//                        f = servM.trouverFormationParLibelle(libelle).get(0);
//                        
//                        System.out.print("Entrez l'id de la compétence générale : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cg = servM.trouverCompetenceGParId(id);
//                        
//                        if (servM.ajouterCompetenceG(f, cg))
//                        {
//                            System.out.println("La compétence générale a bien été ajoutée à la formation !");
//                        }
//                        else
//                        {
//                            System.out.println("L'ajout n'a pas été effectué !");
//                        }
//                        
//                        break;
//                        
//                    case 22:
//                        // Ajouter une compétence spécifique à une compétence générale
//                        System.out.print("Entrez l'id de la compétence générale : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cg = servM.trouverCompetenceGParId(id);
//                        
//                        System.out.print("Entrez l'id de la compétence spécifique : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cs = servM.trouverCompetenceSParId(id);
//                        
//                        if (servM.ajouterCompetenceS(cg, cs))
//                        {
//                            System.out.println("La compétence spécifique a bien été ajoutée à la compétence générale !");
//                        }
//                        else
//                        {
//                            System.out.println("L'ajout n'a pas été effectué !");
//                        }
//                        
//                        break;
//                        
//                    case 23:
//                        //Retirer une compétence générale à une formation
//                        System.out.print("Entrez le libellé de la formation : ");
//                        libelle = sc.nextLine();
//                        
//                        f = servM.trouverFormationParLibelle(libelle).get(0);
//                        
//                        System.out.print("Entrez l'id de la compétence générale : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cg = servM.trouverCompetenceGParId(id);
//                        
//                        if (servM.retirerCompetenceG(f, cg))
//                        {
//                            System.out.println("La compétence générale a bien été retirée de la formation !");
//                        }
//                        else
//                        {
//                            System.out.println("Le retrait n'a pas été effectué !");
//                        }
//                        
//                        break;
//                        
//                    case 24:
//                        //Retirer une compétence spécifique à une compétence générale
//                        System.out.print("Entrez l'id de la compétence générale : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cg = servM.trouverCompetenceGParId(id);
//                        
//                        System.out.print("Entrez l'id de la compétence spécifique : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cs = servM.trouverCompetenceSParId(id);
//                        
//                        if (servM.retirerCompetenceS(cg, cs))
//                        {
//                            System.out.println("La compétence spécifique a bien été retirée de la compétence générale !");
//                        }
//                        else
//                        {
//                            System.out.println("Le retrait n'a pas été effectué !");
//                        }
//                        
//                        break;
//                        
//                    case 25:
//                        // Assigner une formation à un apprenant
//                        System.out.print("Entrez l'id de l'apprenant : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        System.out.print("Entrez le libellé de la formation : ");
//                        libelle = sc.nextLine();
//                        
//                        a = servM.trouverApprenantParId(id);
//                        f = servM.trouverFormationParLibelle(libelle).get(0);
//                        
//                        if (servM.assignerFormation(a, f))
//                        {
//                            System.out.println("La formation a bien été assignée à l'apprenant !");
//                        }
//                        else
//                        {
//                            System.out.println("La formation n'a pas été assignée !");
//                        }
//                        
//                        break;
//                        
//                    case 26:
//                        // Donner une note à un apprenant
//                        System.out.print("Entrez l'id de l'apprenant : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        a = servM.trouverApprenantParId(id);
//                        
//                        System.out.print("Entrez l'id de la compétence spécifique : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cs = servM.trouverCompetenceSParId(id);
//                        
//                        System.out.print("Entrez l'id de la règle : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        r = servM.trouverRegleParId(id);
//                        
//                        System.out.print("Entrez le nombre de paramètres de calcul : ");
//                        int nb = sc.nextInt();
//                        
//                        List<Boolean> param = new ArrayList();
//                        
//                        for (int i = 1 ; i <= nb; i++)
//                        {
//                            System.out.print("Entrez le paramètre N°" + i + " (1 : vrai, autre : faux) : ");
//                            int val = sc.nextInt();
//                            
//                            if (val == 1)
//                            {
//                                param.add(Boolean.TRUE);
//                            }
//                            else
//                            {
//                                param.add(Boolean.FALSE);
//                            }
//                        }
//                        
//                        if (servM.ajouterNote(a, cs, r, param))
//                        {
//                            System.out.println(a);
//                        }
//                        else
//                        {
//                            System.out.println("La note n'a pas été ajoutée !");
//                        }
//                        
//                        break;
//                        
//                    case 27:
//                        // Calculer la moyenne d'un apprenant dans une compétence générale
//                        
//                        System.out.print("Entrez l'id de l'apprenant : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        a = servM.trouverApprenantParId(id);
//                        
//                        System.out.print("entrez l'id de la compétence générale : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cg = servM.trouverCompetenceGParId(id);
//                        
//                        Double moyenne = servM.calculerMoyenneCompetenceG(a, cg);
//                        
//                        if (moyenne != null)
//                        {
//                            System.out.println("La moyenne vaut " + moyenne);
//                        }
//                        else
//                        {
//                            System.out.println("La moyenne n'a pas été calculée !");
//                        }
//                        
//                        break;
//                        
//                    case 28:
//                        // Supprimer un score d'un apprenant
//                        System.out.print("Entrez l'id de l'apprenant : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        a = servM.trouverApprenantParId(id);
//                        
//                        System.out.print("Entrez l'id de la compétence spécifique : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        cs = servM.trouverCompetenceSParId(id);
//                        
//                        if (servM.supprimerScore(a, cs))
//                        {
//                            System.out.println("Le score a bien été supprimé !");
//                        }
//                        else
//                        {
//                            System.out.println("Le score n'a pas été supprimé !");
//                        }
//                        
//                        break;
//                        
//                    case 29:
//                        // Supprimer une règle
//                        System.out.print("Entrez l'id de la règle : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        r = servM.trouverRegleParId(id);
//                        
//                        if (servM.supprimerRegle(r))
//                        {
//                            System.out.println("La règle a été supprimée !");
//                        }
//                        else
//                        {
//                            System.out.println("La règle n'a pas été supprimée !");
//                        }
//                        
//                        break;
//                        
//                    case 30:
//                        // Lister les grades d'un apprenant
//                        System.out.print("Entrez l'id de l'apprenant : ");
//                        id = sc.nextLong();
//                        sc.nextLine();
//                        
//                        a = servM.trouverApprenantParId(id);
//                        
//                        grades = servM.listerGradesParApprenant(a);
//                        
//                        if (grades != null)
//                        {
//                            for (Grade pg : grades)
//                            {
//                                System.out.println(pg);
//                            }
//                        }
//                        
//                        break;
//                        
//                    case 31:
//                        // Lister les scores d'un apprenant
//                        
//                        break;
//                        
//                    case nbChoix:
//                        quitter = true;
//                        break;
//                        
//                    default:
//                        quitter = true;
//                        break;
//                }
//            }
//            else
//            {
//                System.out.println("Choix incorrect !");
//                break;
//            }
//        }
//        
//        sc.close();
//    }
//}
