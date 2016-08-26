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
 * Cette classe rassemble toutes les informations nécessaires à la description d'un apprenant.
 * @author Alexandre BENTO
 */

@Entity
public class Apprenant implements Serializable
{
    /**
     * l'énumération Grade rassemble tous les grades possibles pour chaque compétence.
     */
    
    //---------------------------- Attributs -----------------------------------
    
    @Id
    protected String id;
    
    private String nom;
    private String fonction;
    private String entreprise;
    
    @ManyToOne
    private Formation formation;
    
    @OneToOne
    private Personne compte;
    
    //------------------------ Méthodes publiques ------------------------------
    
    // Constructeurs
    
    public Apprenant() {}

    /**
     * Ce constructeur initialise le nom et le fonction d'un apprenant.
     * @param nom nom de l'apprenant
     * @param niveau fonction de l'apprenant
     */
    
    public Apprenant(String id, String nom, String fonction, String entreprise, Personne compte)
    {
        this.id = id;
        this.nom = nom;
        this.fonction = fonction;
        this.entreprise = entreprise;
        this.compte = compte;
    }
    
    // Getters

    public String getId() {
        return id;
    }

    /**
     * Cette méthode renvoie le nom de l'apprenant.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Cette méthode renvoie le fonction de l'apprenant.
     */
    
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
    
    // Setters

    /**
     * Cette méthode remplace le nom de l'apprenant.
     * @param nom le nouveau nom
     */
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Cette méthode remplace le fonction de l'apprenant.
     * @param fonction le nouveau fonction
     */

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    /**
     * Cette méthode remplace la formation de l'apprenant.
     * @param formation la nouvelle formation
     */

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setCompte(Personne compte) {
        this.compte = compte;
    }
    
    // Autres méthodes publiques

    /**
     * Cette méthode renvoie, sous forme ce chaîne de caractères, toutes les informations de l'apprenant.
     * @return 
     */
    
    @Override
    public String toString()
    {
        return "Apprenant{" + "id=" + id + ", nom=" + nom + ", niveau=" + fonction + ", formation=" + formation + '}';
    }

    public Boolean equals(Apprenant a) {
        return this.id.equals(a.getId());
    }
}
