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
public class Regle implements Serializable
{
    //---------------------------- Attributs -----------------------------------
    
    @Id
    protected String id;
    
    private String libelle;
    
    private RulePattern pattern;
    
    private Boolean pourcentages;
    
    private List<Pair<String, Integer>> cas;
    
    //------------------------ Méthodes publiques ------------------------------

    // Constructeurs
    
    public Regle() {
    }

    public Regle(String id, String libelle, RulePattern pattern, Boolean pourcentages, List<Pair<String, Integer>> cas) {
        this.id = id;
        this.libelle = libelle;
        this.pattern = pattern;
        this.pourcentages = pourcentages;
        this.cas = cas;
    }
    public String getId() {    
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public RulePattern getPattern() {
        return pattern;
    }

    public Boolean getPourcentages() {
        return pourcentages;
    }

    // Getters
    public List<Pair<String, Integer>> getCas() {    
        return cas;
    }

    // Setters
    public void setPattern(RulePattern pattern) {
        this.pattern = pattern;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPourcentages(Boolean pourcentages) {
        this.pourcentages = pourcentages;
    }

    public void setCas(List<Pair<String, Integer>> cas) {
        this.cas = cas;
    }
    
    // Autres méthodes publiques

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Regle other = (Regle) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Regle{" + "id=" + id + ", libelle=" + libelle + ", pattern=" + pattern + ", cas=" + cas + '}';
    }
}
