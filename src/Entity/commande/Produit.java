/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.commande;

/**
 *
 * @author ghada
 */
public class Produit {
   private Double id;
   private String nom;
   private String img ;
   private String description;
   private Double prix;
   private String categorie;
   private Double stock;

    public Produit() {
    }

    public Produit(Double id,String nom, String img, String description, Double prix, String categorie, Double stock) {
        this.id = id;
        this.nom = nom;
        this.img = img;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.stock = stock;
    }
    
    public Produit(String nom, String img, String description, Double prix, String categorie, Double stock) {
        this.nom = nom;
        this.img = img;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this.stock = stock;
    }

    public Produit(Double id, String nom, String img, String description, Double prix, Double stock) {
        this.id = id;
        this.nom = nom;
        this.img = img;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", img=" + img + ", description=" + description + ", prix=" + prix + ", categorie=" + categorie + ", stock=" + stock + '}';
    }
   
  



 

    
}
