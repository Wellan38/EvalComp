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
        
        cas.add(new Pair("si plus de &nombre % des &type ont été &verbe", 10));
        cas.add(new Pair("sinon", 0));
        
        serv.creerRulePattern("RP-EXCLUSIF-POURCENT", "Exclusif (pourcentages)", cas, Boolean.FALSE);
        
        cas = new ArrayList();
        
        cas.add(new Pair("si plus de &nombre &type ont été &verbe", 10));
        cas.add(new Pair("sinon", 0));
        
        serv.creerRulePattern("RP-EXCLUSIF-NOMBRE", "Exclusif (nombre)", cas, Boolean.FALSE);
        
        cas = new ArrayList();
        
        cas.add(new Pair("si &libre", 10));
        cas.add(new Pair("sinon", 0));
        
        serv.creerRulePattern("RP-EXCLUSIF-LIBRE", "Exclusif (libre)", cas, Boolean.FALSE);
        
        cas = new ArrayList();
        
        cas.add(new Pair("si plus de &nombre &type ont été &verbe", 10));
        cas.add(new Pair("si entre &nombre et &nombre &type ont été &verbe", 5));
        cas.add(new Pair("si moins de &nombre &type ont été &verbe", 0));
        
        serv.creerRulePattern("RP-COMPTAGE", "Par comptage", cas, Boolean.TRUE);
        
        cas = new ArrayList();
        
        cas.add(new Pair("si plus de &nombre % des &type ont été &verbe", 10));
        cas.add(new Pair("si entre &nombre % et &nombre % des &type ont été &verbe", 5));
        cas.add(new Pair("si moins de &nombre % des &type ont été &verbe", 0));
        
        serv.creerRulePattern("RP-PROGRESSIF", "Progressif", cas, Boolean.TRUE);
    }
}
