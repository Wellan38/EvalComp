package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CompetenceS implements Serializable
{   
    @Id
    protected String id;
    
    private String libelle;
    private Boolean feminin;
    private Boolean pluriel;
    private String type;
    private Double ponderation;
    private MiseEnSituation miseEnSituation;
    
    @ManyToOne
    CompetenceG compG;
    
    @ManyToOne
    private Regle regle;
    
    public CompetenceS() {}

    public CompetenceS(String id, String libelle, Boolean feminin, Boolean pluriel,  String type, Double ponderation, Regle regle, MiseEnSituation miseEnSituation) {
        this.id = id;
        this.libelle = libelle;
        this.feminin = feminin;
        this.pluriel = pluriel;
        this.type = type;
        this.ponderation = ponderation;
        this.regle = regle;
        this.miseEnSituation = miseEnSituation;
    }
    
    public String getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Boolean getFeminin() {
        return feminin;
    }

    public Boolean getPluriel() {
        return pluriel;
    }

    public String getType() {
        return type;
    }
    
    public Double getPonderation() {
        return ponderation;
    }

    public CompetenceG getCompG() {
        return compG;
    }

    public MiseEnSituation getMiseEnSituation() {
        return miseEnSituation;
    }

    public Regle getRegle() {
        return regle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setFeminin(Boolean feminin) {
        this.feminin = feminin;
    }

    public void setPluriel(Boolean pluriel) {
        this.pluriel = pluriel;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setPonderation(Double ponderation) {
        this.ponderation = ponderation;
    }

    public void setCompG(CompetenceG compG) {
        this.compG = compG;
    }

    public void setMiseEnSituation(MiseEnSituation miseEnSituation) {
        this.miseEnSituation = miseEnSituation;
    }

    public void setRegle(Regle regle) {
        this.regle = regle;
    }

    @Override
    public String toString() {
        return "CompetenceS{" + "id=" + id + ", libelle=" + libelle + ", type=" + type + ", ponderation=" + ponderation + ", miseEnSituation=" + miseEnSituation + '}';
    }
    
    public Boolean equals(CompetenceS c)
    {
        return this.id.equals(c.getId());
    }
}
