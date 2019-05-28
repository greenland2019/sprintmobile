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
public class Promotion {
     private Double id;
    private Double produit_id;
    private String date_promo;
    private String type;
    private Double reduction;
    private String description;
    private String date_fin;

    public Promotion() {
    }
    
      public Promotion(Double id, Double produit_id,Double reduction) {
        this.id = id;
        this.produit_id = produit_id;
        this.reduction = reduction;
      
    }


    public Promotion(Double id, Double produit_id, String date_promo, String type, Double reduction, String description, String date_fin) {
        this.id = id;
        this.produit_id = produit_id;
        this.date_promo = date_promo;
        this.type = type;
        this.reduction = reduction;
        this.description = description;
        this.date_fin = date_fin;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(Double produit_id) {
        this.produit_id = produit_id;
    }

    public String getDate_promo() {
        return date_promo;
    }

    public void setDate_promo(String date_promo) {
        this.date_promo = date_promo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }
    
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionProp() {
        return description;
    }

    public void setDescriptionProp(String description) {
        this.description = description;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }
    
    
    
}
