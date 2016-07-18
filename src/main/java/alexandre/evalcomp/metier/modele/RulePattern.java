/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javafx.util.Pair;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class RulePattern implements Serializable
{
    @Id
    private String id;
    
    private String libelle;
    
    private List<Pair<String, Integer>> cas;

    public RulePattern() {
    }

    public RulePattern(String id, String libelle, List<Pair<String, Integer>> cas) {
        this.id = id;
        this.libelle = libelle;
        this.cas = cas;
    }

    public String getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public List<Pair<String, Integer>> getCas() {
        return cas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "RulePattern{" + "id=" + id + ", libelle=" + libelle + ", cas=" + cas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
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
        final RulePattern other = (RulePattern) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
