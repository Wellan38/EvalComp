/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class MiseEnSituation implements Serializable
{
    @Id
    private String id;
    
    private String contexte;
    private String ressources;
    private String action;

    public MiseEnSituation() {
    }
    
    public MiseEnSituation(String id, String contexte, String ressources, String action)
    {
        this.id = id;
        this.contexte = contexte;
        this.ressources = ressources;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public String getContexte() {
        return contexte;
    }

    public String getRessources() {
        return ressources;
    }

    public String getAction() {
        return action;
    }

    public void setContexte(String contexte) {
        this.contexte = contexte;
    }

    public void setRessources(String ressources) {
        this.ressources = ressources;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MiseEnSituation other = (MiseEnSituation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MiseEnSituation{" + "id=" + id + ", contexte=" + contexte + ", ressources=" + ressources + ", action=" + action + '}';
    }
}
