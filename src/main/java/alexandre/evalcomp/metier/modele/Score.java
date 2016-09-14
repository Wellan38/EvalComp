package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Score implements Serializable, Comparable<Score>
{
    @Id
    protected String id;
    
    @ManyToOne
    private CompetenceS competence;
    
    @ManyToOne
    private Apprenant apprenant;
    
    private Double score;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    public Score() {
    }

    public Score(String id, CompetenceS competence, Apprenant apprenant, Double score, Date date) {
        this.id = id;
        this.competence = competence;
        this.apprenant = apprenant;
        this.score = score;
        this.date = date;
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

    public Date getDate() {
        return date;
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

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PaireSpecifique{" + "id=" + id + ", competence=" + competence + ", score=" + score + '}';
    }
    
    public Boolean equals(Score s)
    {
        return this.id.equals(s.getId());
    }
    
    public int compareTo(Score s)
    {
        return date.compareTo(s.getDate());
    }
}
