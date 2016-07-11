/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class CompetenceS implements Serializable
{
    //---------------------------- Attributs -----------------------------------
    
    @Id
    protected String id;
    
    private String libelle;
    private String type;
    private Double ponderation;
    
    @ManyToOne
    CompetenceG compG;
    
    @OneToOne
    private MiseEnSituation miseEnSituation;
    
    @ManyToOne
    private Regle regle;
    
    //------------------------ Méthodes publiques ------------------------------
    
    // Constructeurs
    
    public CompetenceS() {}

    public CompetenceS(String id, String libelle,  String type, Double ponderation, Regle regle) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.ponderation = ponderation;
        this.regle = regle;
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
    
    // Setters

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
    
    // Autres méthodes publiques

    @Override
    public String toString() {
        return "CompetenceS{" + "id=" + id + ", libelle=" + libelle + ", type=" + type + ", ponderation=" + ponderation + ", miseEnSituation=" + miseEnSituation + '}';
    }
    
    public Boolean equals(CompetenceS c)
    {
        return this.id.equals(c.getId());
    }
}
