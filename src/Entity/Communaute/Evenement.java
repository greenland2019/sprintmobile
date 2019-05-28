/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Communaute;

/**
 *
 * @author HP
 */
public class Evenement {
      private int id;
     private String date_event;
    private String type;
    private Double nb_participants;
    private String description;
    private String affiche;
    private String lieu;

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    

    public Evenement() {
    }

    public Evenement(String date_event, String type, Double nb_participants,String lieu, String description, String affiche) {
        this.date_event = date_event;
        this.type = type;
        this.nb_participants = nb_participants;
        this.description = description;
        this.affiche = affiche;
       this.lieu = lieu;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getNb_participants() {
        return nb_participants;
    }

    
    public void setNb_participants(Double nb_participants) {
        this.nb_participants = nb_participants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
    
}
