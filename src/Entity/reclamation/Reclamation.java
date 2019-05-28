/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.reclamation;

import java.util.Date;

/**
 *
 * @author Amal
 */
public class Reclamation {
    
    private String email;
    private String sujet;
    private String categorie;
    private String contenu;
    private boolean treated;

    public boolean isTreated() {
        return treated;
    }

    public void setTreated(boolean treated) {
        this.treated = treated;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    private Date date;

    
    public Reclamation(String email,String sujet,String contenu ,String categorie){
        this.email=email;
        this.sujet=sujet;
        this.contenu=contenu;
        this.categorie=categorie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    
}
