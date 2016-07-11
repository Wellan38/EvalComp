/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.vue;

import alexandre.evalcomp.metier.modele.Formation;
import alexandre.evalcomp.metier.service.ServiceMetier;
import java.util.List;

/**
 *
 * @author Alex-Laptop
 */
public class MainTest {
    
    public static void main(String[] args) throws Throwable
    {
        ServiceMetier serv = new ServiceMetier();
        
        List<Formation> formations = serv.listerFormationsParPersonne(serv.trouverPersonneParNom("Personne1").get(0));
        
        for (Formation f : formations)
        {
            System.out.println(f);
        }
        
        serv.supprimerFormation(serv.trouverFormationParLibelle("form1").get(0));
        
        formations = serv.listerFormationsParPersonne(serv.trouverPersonneParNom("Personne1").get(0));
        
        for (Formation f : formations)
        {
            System.out.println(f);
        }
        
    }
}