/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Communaute;

import Entity.Communaute.Promotion;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class PromotionService {
      public ArrayList<Promotion> parseListTaskJson(String json) {

        ArrayList<Promotion> promotions = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
          
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Promotion e = new Promotion();

                Double id = Double.valueOf(""+obj.get("id"));

                e.setId( id);
                //e.setDate_fin(""+obj.get("date_fin"));
                //e.setDate_promo(""+obj.get("date_promo"));
                //e.setDescription(""+obj.get("description"));
                e.setReduction(Double.valueOf(""+obj.get("reduction")));
               // e.setType(""+obj.get("type"));
                e.setProduit_id(Double.valueOf(""+obj.get("produit_id")));
                System.out.println(e);
                
                promotions.add(e);

            }

        } catch (IOException ex) {
        }
    
        System.out.println(promotions);
        return promotions;
}
      
      ArrayList<Promotion> promo = new ArrayList<>();
      public Promotion findpromo(Double prodid){
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/findpromo/"+prodid);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PromotionService ser = new PromotionService();
                promo = ser.parseListTaskJson(new String(con.getResponseData()));
               
            }
        });
                       
        NetworkManager.getInstance().addToQueueAndWait(con);
        
          
       return promo.get(0);
        
     }
      
      public void promouvoir(Double prodid,String datefin,Double valeur){
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/ajoutpromo?idprod="+prodid+"&datefin="+datefin+"&valeur="+valeur);  
          
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        
     }
      
       public void cancelpromo(Double prodid){
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/deletepromo/"+prodid);  
          
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        
     }
}