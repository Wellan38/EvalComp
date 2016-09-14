package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CompetenceG implements Serializable
{    
    @Id
    protected String id;
    
    private String libelle;
    private String type;
    private Double seuilMin;
    private Double seuilMax;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<CompetenceS> compSpec;

    public CompetenceG() {}

    public CompetenceG(String id, String libelle, String type, double seuilMin, double seuilMax, List<CompetenceS> compSpec) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.seuilMin = seuilMin;
        this.seuilMax = seuilMax;
        this.compSpec = compSpec;
    }
    
    public String getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getType() {
        return type;
    }

    public Double getSeuilMin() {
        return seuilMin;
    }

    public Double getSeuilMax() {
        return seuilMax;
    }

    public List<CompetenceS> getCompSpec() {
        return compSpec;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSeuilMin(Double seuilMin) {
        this.seuilMin = seuilMin;
    }

    public void setSeuilMax(Double seuilMax) {
        this.seuilMax = seuilMax;
    }

    @Override
    public String toString() {
        return "CompetenceG{" + "id=" + id + ", libelle=" + libelle + ", type=" + type + ", seuilMin=" + seuilMin + ", seuilMax=" + seuilMax + ", compSpec=" + compSpec + '}';
    }
    
    public Boolean equals(CompetenceG c)
    {
        return this.id.equals(c.getId());
    }
}
