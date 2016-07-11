/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class CompetenceG implements Serializable
{
    //---------------------------- Attributs -----------------------------------
    
    @Id
    protected String id;
    
    private String libelle;
    private String type;
    private Double seuilMin;
    private Double seuilMax;
    
    @OneToMany(fetch = FetchType.EAGER)
    private List<CompetenceS> compSpec;
    
    //------------------------ Méthodes publiques ------------------------------
    
    // Constructeurs

    public CompetenceG() {}

    public CompetenceG(String id, String libelle, String type, double seuilMin, double seuilMax, List<CompetenceS> compSpec) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.seuilMin = seuilMin;
        this.seuilMax = seuilMax;
        this.compSpec = compSpec;
    }
    
    // Getters
    
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
    
    // Setters

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
    
    // Autres méthodes publiques

    @Override
    public String toString() {
        return "CompetenceG{" + "id=" + id + ", libelle=" + libelle + ", type=" + type + ", seuilMin=" + seuilMin + ", seuilMax=" + seuilMax + ", compSpec=" + compSpec + '}';
    }
    
    public Boolean equals(CompetenceG c)
    {
        return this.id.equals(c.getId());
    }
}
