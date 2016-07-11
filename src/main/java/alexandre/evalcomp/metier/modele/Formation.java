/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.evalcomp.metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Alex-Laptop
 */

@Entity
public class Formation implements Serializable
{
    //---------------------------- Attributs -----------------------------------
    
    @Id
    protected String id;
    
    private String libelle;
    private String domaine;
    private String url;
    private Integer duree;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CompetenceG> competences;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Personne> responsables;
    
    //------------------------ Méthodes publiques ------------------------------

    // Constructeurs
    
    public Formation() {}

    public Formation(String id, String libelle, String domaine, String url, int duree, Date date) {
        this.id = id;
        this.libelle = libelle;
        this.domaine = domaine;
        this.url = url;
        this.duree = duree;
        this.date = date;
        
        competences = new ArrayList();
        responsables = new ArrayList();
    }
    
    public Formation(String id, String libelle, String domaine, int duree, Date date) {
        this.id = id;
        this.libelle = libelle;
        this.domaine = domaine;
        this.url = null;
        this.duree = duree;
        this.date = date;
        
        competences = new ArrayList();
        responsables = new ArrayList();
    }
    
    // Getters
    
    public String getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getUrl() {
        return url;
    }

    public Integer getDuree() {
        return duree;
    }

    public Date getDate() {
        return date;
    }

    public List<CompetenceG> getCompetences() {
        return competences;
    }

    public List<Personne> getResponsables() {
        return responsables;
    }
    
    // Setters

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    // Autres méthodes

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", libelle=" + libelle + ", domaine=" + domaine + ", url=" + url + ", duree=" + duree + ", date=" + date + ", competences=" + competences + '}';
    }
    
    public Boolean equals(Formation f)
    {
        return this.id.equals(f.getId());
    }
}
