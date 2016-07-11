/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.vue;

import alexandre.evalcomp.metier.modele.Apprenant;
import alexandre.evalcomp.metier.modele.CompetenceG;
import alexandre.evalcomp.metier.modele.CompetenceS;
import alexandre.evalcomp.metier.modele.Formation;
import alexandre.evalcomp.metier.modele.MiseEnSituation;
import alexandre.evalcomp.metier.modele.Personne;
import alexandre.evalcomp.metier.service.ServiceMetier;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Alex-Laptop
 */
public class Parseur {
    
    final static String COORDONATEUR = "Coordonateur";
    final static String FORMATEUR = "Formateur";
    final static String FORMATDATE = "dd/MM/yyyy";
    final static String SEP = ",";
    static String chemin;
    
    public static ServiceMetier serv = new ServiceMetier();
    
    public enum TypeParse {
        Apprenant,
        CompetenceG,
        CompetenceS,
        Formation,
        Formation_CompetenceG,
        MiseEnSituation,
        Personne,
        Score,
        Apprenant_Formation,
        Personne_Formation,
        Regle
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, Throwable
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Entrez le chemin des fichiers CSV : ");
        chemin = sc.nextLine();
        
        Boolean correct = false;
        int choix = 0;
        
        while (!correct)
        {
            System.out.println("Quelle table voulez-vous remplir ?");
            System.out.println("1. Apprenant");
            System.out.println("2. CompetenceG");
            System.out.println("3. CompetenceS");
            System.out.println("4. Formation");
            System.out.println("5. Formation_CompetenceG");
            System.out.println("6. MiseEnSituation");
            System.out.println("7. Personne");
            System.out.println("8. Score");
            System.out.println("9. Apprenant_Formation");
            System.out.println("10. Personne_Formation");
            System.out.println("11. Regle");
            System.out.println("12. Tout");

            choix = sc.nextInt();
            sc.nextLine();
            
            if (choix >= 1 && choix <= 12)
            {
                correct = true;
            }
            else
            {
                System.out.println("Choix incorrect !");
            }
        }
        
        switch(choix)
        {
            case 1:
                parser(TypeParse.Apprenant);
                
                break;
                
            case 2:
                parser(TypeParse.CompetenceG);
                
                break;
                
            case 3:
                parser(TypeParse.CompetenceS);
                
                break;
                
            case 4:
                parser(TypeParse.Formation);
                
                break;
                
            case 5:
                parser(TypeParse.Formation_CompetenceG);
                
                break;
                
            case 6:
                parser(TypeParse.MiseEnSituation);
                
                break;
                
            case 7:
                parser(TypeParse.Personne);
                
                break;
                
            case 8:
                parser(TypeParse.Score);
                
                break;
                
            case 9:
                parser(TypeParse.Apprenant_Formation);
                
                break;
                
            case 10:
                parser(TypeParse.Personne_Formation);
                
                break;
            case 11:
                parser(TypeParse.Regle);
                
                break;
                
            case 12:
                parser(TypeParse.Apprenant);
                parser(TypeParse.Formation);
                parser(TypeParse.CompetenceG);
                parser(TypeParse.Apprenant_Formation);
                parser(TypeParse.Formation_CompetenceG);
                parser(TypeParse.Regle);
                parser(TypeParse.CompetenceS);
                parser(TypeParse.MiseEnSituation);
//                parser(TypeParse.Personne);
//                parser(TypeParse.Personne_Formation);
//                parser(TypeParse.Score);
                
                break;
                
            
        }
        
        sc.close();
    }
    
    public static void parser(TypeParse t) throws Throwable
    {
        BufferedReader br = null;
        
        String nomFichier = chemin + "/";
        
        switch(t)
        {
            case Apprenant:
                nomFichier += "Apprenant.csv";
                break;
                
            case CompetenceG:
                nomFichier += "CompetenceG.csv";
                break;
                
            case CompetenceS:
                nomFichier += "CompetenceS.csv";
                break;
                
            case Formation:
                nomFichier += "Formation.csv";
                break;
                
            case Formation_CompetenceG:
                nomFichier += "Formation_CompetenceG.csv";
                break;
                
            case MiseEnSituation:
                nomFichier += "MiseEnSituation.csv";
                break;
                
            case Personne:
                nomFichier += "Personne.csv";
                break;
                
            case Score:
                nomFichier += "Score.csv";
                break;
                
            case Apprenant_Formation:
                nomFichier += "Apprenant_Formation.csv";
                
                break;
                
            case Personne_Formation:
                nomFichier += "Personne_Formation.csv";
                
                break;
                
            case Regle:
                nomFichier += "Regle.csv";
                
                break;
                
            default:
                break;
        }
        
        try
        {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(nomFichier), "UTF8"));
            
            String ligne = br.readLine();
            
            String[] elements;
            
            Formation f;
            CompetenceG cg;
            Apprenant a;
            Personne p;
            
            while ((ligne = br.readLine()) != null)
            {
                elements = ligne.split(SEP);
                System.out.println("Elements : " + elements[0]);
                
                switch(t)
                {
                    case Apprenant:
                        // Apprenant
                        serv.creerApprenant(elements[0], elements[1], elements[2], elements[3]);

                        break;

                    case CompetenceG:
                        // CompetenceG
                        serv.creerCompetenceG(elements[0], elements[1], elements[2], Double.parseDouble(elements[3]), Double.parseDouble(elements[4]), new ArrayList());

                        break;
                        
                    case CompetenceS:
                        //CompetenceS
                        System.out.println(elements[5]);
                        CompetenceS cs = serv.creerCompetenceS(elements[0], elements[1], elements[2], Double.parseDouble(elements[3]), serv.trouverRegleParId(elements[5]));
                        cg = serv.trouverCompetenceGParId(elements[5]);
                        serv.ajouterCompetenceS(cg, cs);
                        
                        break;
                        
                    case Formation:
                        // Formation
                        DateFormat format = new SimpleDateFormat(FORMATDATE, Locale.FRANCE);
                        serv.creerFormation(elements[0], elements[1], elements[2], elements[3], Integer.parseInt(elements[4]), format.parse(elements[5]));
                        
                        break;
                        
                    case Formation_CompetenceG:
                        // Formation_CompetenceG
                        f = serv.trouverFormationParId(elements[0]);
                        cg = serv.trouverCompetenceGParId(elements[1]);
                        serv.ajouterCompetenceG(f, cg);
                        
                        break;
                        
                    case MiseEnSituation:
                        // MiseEnSituation
                        
                        List<String> description = new ArrayList();
                        
                        for (int i = 1; i < elements.length; i++)
                        {
                            description.add(elements[i]);
                        }
                        
                        MiseEnSituation m = serv.creerMiseEnSituation(description);
                        
                        cs = serv.trouverCompetenceSParId(elements[0]);
                        serv.assignerMiseEnSituation(cs, m);
                        
                        break;
                    
//                    case Personne:
//                        // Personne
//                        TypePersonne type = TypePersonne.Coordonateur;
//                        
//                        if (elements[2].equals(FORMATEUR))
//                        {
//                            type = TypePersonne.Formateur;
//                        }
//                        else if (elements[2].equals(COORDONATEUR))
//                        {
//                            type = TypePersonne.Coordonateur;
//                        }
//                        
//                        serv.creerPersonne(elements[0], elements[1], type);
//                        
//                        break;
//                        
//                    case Score:
//                        // Score
//                        a = serv.trouverApprenantParNom(elements[0]).get(0);
//                        cs = serv.trouverCompetenceSParLibelle(elements[1]).get(0);
//                        
//                        serv.ajouterNote(a, cs, Double.parseDouble(elements[2]));
//                        
//                        break;
                        
                    case Apprenant_Formation:
                        // Apprenant_Formation
                        a = serv.trouverApprenantParId(elements[0]);
                        f = serv.trouverFormationParId(elements[1]);
                        
                        serv.assignerFormation(a, f);
                        
                        break;
                        
//                    case Personne_Formation:
//                        // Personne_Formation
//                        p = serv.trouverPersonneParNom(elements[0]).get(0);
//                        f = serv.trouverFormationParLibelle(elements[1]).get(0);
//                        
//                        serv.assignerFormation(p, f);
//                        
//                        break;
                        
                    case Regle :
                        // Regle
                        
                        List<String> texte = new ArrayList();
                        
                        for (int i = 2; i < elements.length; i++)
                        {
                            texte.add(elements[i]);
                        }
                        
                        serv.creerRegle(elements[0], elements[1], texte);
                }
            }
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}
