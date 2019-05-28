/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Personne;

import Entity.Communaute.Evenement;
import Entity.User.Personne;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class PersonneService {
     public ArrayList<Personne> parseListTaskJson(String json) {

        ArrayList<Personne> Personnes = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                 Personne e = new Personne();

                Double id = Double.parseDouble(""+obj.get("id"));

                e.setId(id);
                e.setEmail(""+obj.get("email"));
              e.setNom(""+obj.get("nom"));
                e.setPrenom(""+obj.get("prenom"));
                e.setRole(""+obj.get("role"));
                e.setAdresse(""+obj.get("addresse"));
//                e.setNumtel(Double.valueOf(""+obj.get("numtel")));
                e.setZone(""+obj.get("zone"));
                e.setRole(""+obj.get("role"));
                System.out.println(e);
                
                Personnes.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(Personnes);
        return Personnes;

    }
     
     
     ArrayList<Personne> found = new ArrayList<>();
     public Personne FindPersonne(Double id){
      
    
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/findperson/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PersonneService ser = new PersonneService();
                found = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       return found.get(0);
        
     }
     
      public Personne connect(String email,String password){
      
    
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/login?email="+email+"&password="+password);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PersonneService ser = new PersonneService();
                found = ser.parseListTaskJson(new String(con.getResponseData()));
                
            }
        });
        
        
        NetworkManager.getInstance().addToQueueAndWait(con);
                        try{

       return found.get(0);
         }
                catch(Exception e){
                    System.out.println("Utilisateur inexistant");
                }
                        return new Personne();
     }

}
