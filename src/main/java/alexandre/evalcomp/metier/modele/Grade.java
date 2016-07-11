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
public class Grade implements Serializable
{
    public enum ValeurGrade {
        Acquis,
        EnCours,
        NonAcquis;
    };
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected String id;
    
    @ManyToOne
    private CompetenceG competence;
    
    @ManyToOne
    private Apprenant apprenant;
    
    private ValeurGrade grade;

    public Grade() {
    }

    public Grade(CompetenceG competence, Apprenant apprenant, ValeurGrade grade) {
        this.competence = competence;
        this.apprenant = apprenant;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }
    
    public CompetenceG getCompetence() {
        return competence;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public ValeurGrade getGrade() {
        return grade;
    }

    public void setCompetence(CompetenceG competence) {
        this.competence = competence;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }
    
    public void setGrade(ValeurGrade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "PaireGenerale{" + "id=" + id + ", competence=" + competence + ", grade=" + grade + '}';
    }
    
    public Boolean equals(Grade g)
    {
        return this.id.equals(g.getId());
    }
}
