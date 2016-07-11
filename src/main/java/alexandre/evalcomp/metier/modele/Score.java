/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class Score implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected String id;
    
    @ManyToOne
    private CompetenceS competence;
    
    @ManyToOne
    private Apprenant apprenant;
    
    private Double score;

    public Score() {
    }

    public Score(CompetenceS competence, Apprenant apprenant, Double score) {
        this.competence = competence;
        this.apprenant = apprenant;
        this.score = score;
    }

    public String getId() {
        return id;
    }
    
    public CompetenceS getCompetence() {
        return competence;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }
    
    public Double getScore() {
        return score;
    }

    public void setCompetence(CompetenceS competence) {
        this.competence = competence;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PaireSpecifique{" + "id=" + id + ", competence=" + competence + ", score=" + score + '}';
    }
    
    public Boolean equals(Score s)
    {
        return this.id.equals(s.getId());
    }
}
