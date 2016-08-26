/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Alexandre
 */

@Entity
public class AutoEvaluation
{
    @Id
    private String id;
    
    @ManyToOne
    private Apprenant apprenant;
    
    @ManyToOne
    private CompetenceS competence;
    
    private int valeur;

    public AutoEvaluation(String id, Apprenant apprenant, CompetenceS competence, int valeur) {
        this.id = id;
        this.apprenant = apprenant;
        this.competence = competence;
        this.valeur = valeur;
    }

    public AutoEvaluation() {
    }
    
    

    public String getId() {
        return id;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public CompetenceS getCompetence() {
        return competence;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "AutoEvaluation{" + "id=" + id + ", apprenant=" + apprenant + ", competence=" + competence + ", valeur=" + valeur + '}';
    }

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
        final AutoEvaluation other = (AutoEvaluation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
