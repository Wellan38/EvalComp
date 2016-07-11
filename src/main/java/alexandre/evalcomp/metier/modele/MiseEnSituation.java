/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class MiseEnSituation implements Serializable
{
    //---------------------------- Attributs -----------------------------------
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected String id;
    
    private List<String> actions;
    
    //------------------------ Méthodes publiques ------------------------------

    // Constructeurs

    public MiseEnSituation() {
    }

    public MiseEnSituation(List<String> actions) {
        this.actions = actions;
    }
    
    // Getters
    
    public String getId() {
        return id;
    }

    public List<String> getActions() {
        return actions;
    }
    
    // Setters

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
    
    // Autres méthodes publiques

    @Override
    public String toString() {
        return "MiseEnSituation{" + "id=" + id + ", actions=" + actions + '}';
    }
    
    public Boolean equals(MiseEnSituation m)
    {
        return this.id.equals(m.getId());
    }
}
