package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class AutoEvaluation implements Serializable, Comparable<AutoEvaluation>
{
    @Id
    private String id;
    
    @ManyToOne
    private Apprenant apprenant;
    
    @ManyToOne
    private CompetenceS competence;
    
    private Double valeur;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;

    public AutoEvaluation(String id, Apprenant apprenant, CompetenceS competence, Double valeur, Date date) {
        this.id = id;
        this.apprenant = apprenant;
        this.competence = competence;
        this.valeur = valeur;
        this.date = date;
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

    public Double getValeur() {
        return valeur;
    }

    public Date getDate() {
        return date;
    }

    public void setValeur(Double valeur) {
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
    
    public int compareTo(AutoEvaluation e)
    {
        return date.compareTo(e.getDate());
    }
}
