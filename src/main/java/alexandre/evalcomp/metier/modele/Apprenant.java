package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Apprenant implements Serializable
{        
    @Id
    protected String id;
    
    private String nom;
    private String fonction;
    private String entreprise;
    
    @ManyToOne
    private Formation formation;
    
    @OneToOne
    private Personne compte;
    
    public Apprenant() {}
    
    public Apprenant(String id, String nom, String fonction, String entreprise, Personne compte)
    {
        this.id = id;
        this.nom = nom;
        this.fonction = fonction;
        this.entreprise = entreprise;
        this.compte = compte;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    
    public String getFonction() {
        return fonction;
    }

    public Formation getFormation() {
        return formation;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public Personne getCompte() {
        return compte;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setCompte(Personne compte) {
        this.compte = compte;
    }
    
    @Override
    public String toString()
    {
        return "Apprenant{" + "id=" + id + ", nom=" + nom + ", niveau=" + fonction + ", formation=" + formation + '}';
    }

    public Boolean equals(Apprenant a) {
        return this.id.equals(a.getId());
    }
}
