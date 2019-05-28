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
public class Participation {
    
    private int id;
    private Double participant_id;
    private Double evenement_id;

    public Participation(int id, Double participant_id, Double evenement_id) {
        this.id = id;
        this.participant_id = participant_id;
        this.evenement_id = evenement_id;
    }

    public Participation(Double participant_id, Double evenement_id) {
        this.participant_id = participant_id;
        this.evenement_id = evenement_id;
    }

    public Participation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(Double participant_id) {
        this.participant_id = participant_id;
    }

    public Double getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(Double evenement_id) {
        this.evenement_id = evenement_id;
    }
    
    
    
}
