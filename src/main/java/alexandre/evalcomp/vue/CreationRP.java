/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.vue;

import alexandre.evalcomp.metier.service.ServiceMetier;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Alex-Laptop
 */
public class CreationRP {
    
    public static void main (String[] args) throws Throwable
    {
        ServiceMetier serv = new ServiceMetier();
        
        List<Pair<String, Integer>> cas = new ArrayList();
        
        cas.add(new Pair("si plus de &number des &type ont été &verbe, alors : score = ", 10));
        cas.add(new Pair("sinon : score = ", 0));
        
        serv.creerRulePattern("RP-EXCLUSIF", "Exclusif", cas);
    }
}
