/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.User;

/**
 *
 * @author HP
 */
public class Personne {
     private Double id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Double numtel;
    private String adresse;
    private String role;
    private String zone;

    public Personne() {
    }

    public Personne(String nom, String prenom, String email, String password, Double numtel, String adresse, String role, String zone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numtel = numtel;
        this.adresse = adresse;
        this.role = role;
        this.zone = zone;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getNumtel() {
        return numtel;
    }
     
    public void setNumtel(Double numtel) {
        this.numtel = numtel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", numtel=" + numtel + ", adresse=" + adresse + ", role=" + role + ", zone=" + zone + '}';
    }
    
    
}
