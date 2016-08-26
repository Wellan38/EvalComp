/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Alex-Laptop
 */


@Entity
public class Personne implements Serializable
{
    public enum TypePersonne
    {
        Formateur,
        Coordonateur,
        Apprenant
    };
    
    //---------------------------- Attributs -----------------------------------
    
    @Id
    protected String id;
    
    private String nom;
    private String niveau;
    private TypePersonne type;
    private String motDePasse;
    
    //------------------------ Méthodes publiques ------------------------------
    
    // Constructeurs
    
    public Personne() {}

    public Personne(String id, String nom, String niveau, TypePersonne type, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.type = type;
        this.motDePasse = motDePasse;
    }
    
    // Getters
    
    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNiveau() {
        return niveau;
    }

    public TypePersonne getType() {
        return type;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
    // Setters

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setType(TypePersonne type) {
        this.type = type;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    // Autres méthodes publiques

    @Override
    public String toString()
    {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", niveau=" + niveau + ", type=" + type + '}';
    }
    
    public Boolean equals(Personne p)
    {
        return this.id.equals(p.getId());
    }
}
