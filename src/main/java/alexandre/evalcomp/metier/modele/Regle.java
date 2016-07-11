/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class Regle implements Serializable
{
    //---------------------------- Attributs -----------------------------------
    
    @Id
    protected String id;
    
    private String libelle;
    
    private List<String> texte;
    
    //------------------------ Méthodes publiques ------------------------------

    // Constructeurs

    public Regle() {}
    
    public Regle(String id, String libelle, List<String> texte) {
        this.id = id;
        this.libelle = libelle;
        this.texte = texte;
    }
    
    
    // Getters
    
    public String getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public List<String> getTexte() {
        return texte;
    }
    
    // Setters
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setTexte(List<String> texte) {
        this.texte = texte;
    }
        
    // Autres méthodes publiques
    
    @Override
    public String toString()
    {
        return "Regle{" + "id=" + id + ", libelle=" + libelle + ", texte=" + texte + '}';
    }
    
    public Boolean equals(Regle r)
    {
        return this.id.equals(r.getId());
    }
}
