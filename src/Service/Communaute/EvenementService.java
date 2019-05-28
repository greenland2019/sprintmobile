/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Communaute;

import Entity.Communaute.Evenement;
import Entity.User.Personne;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Communaute.Participation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class EvenementService {
      public ArrayList<Evenement> parseListTaskJson(String json) {

        ArrayList<Evenement> events = new ArrayList<>();

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
                Evenement e = new Evenement();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setAffiche(""+obj.get("affiche"));
              e.setDate_event(""+obj.get("dateEvent"));
                e.setDescription(""+obj.get("description"));
                e.setLieu(""+obj.get("lieu"));
                e.setType(""+obj.get("type"));
                e.setNb_participants(Double.valueOf(""+obj.get("nbParticipants")));
                System.out.println(e);
                
                events.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(events);
        return events;

    }
      
      public ArrayList<Participation> parseListTaskJsonpart(String json) {

        ArrayList<Participation> events = new ArrayList<>();

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
                Participation e = new Participation();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setEvenement_id(Double.valueOf(""+obj.get("evenement")));
              e.setParticipant_id(Double.valueOf(""+obj.get("participant")));
               
                System.out.println(e);
                
                events.add(e);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(events);
        return events;

    }
      
        ArrayList<Evenement> events1 = new ArrayList<>();
     public ArrayList<Evenement> listevents(){
      
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/events");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                events1 = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       return events1;
        
     }
     
     public ArrayList<Evenement> listeventparts(){
      
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/eventparts");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                events1 = ser.parseListTaskJson(new String(con.getResponseData()));
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       return events1;
        
     }
     
     ArrayList<Participation> parts = new ArrayList<>();
      public ArrayList<Participation> participants(int eventid){
      
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/stats/"+eventid);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                parts = ser.parseListTaskJsonpart(new String(con.getResponseData()));
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       return parts;
        
     }
      
       public void deleteEvent(int eventid){
      
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/delete/"+eventid);  
        /*con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                parts = ser.parseListTaskJsonpart(new String(con.getResponseData()));
            }
        });
        */
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        
     }
       
        public String parseCheckJsonpart(String json) {

        ArrayList<Participation> events = new ArrayList<>();
        String response ="";
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
             
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
              
            for (Map<String, Object> obj : list) {
               
               response = ""+obj.get("part");

            }

        } catch (IOException ex) {
        }
        
 
        System.out.println(response);
        return response;

    }
        String checkpart = "";
        public String checkPart(String email ,int idevent){
      
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/participcheck?email="+email+"&idevent="+idevent);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                checkpart = ser.parseCheckJsonpart(new String(con.getResponseData()));
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       return checkpart;
        
     }
        
          public void participer(String email ,int idevent){
      
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/particip?login="+email+"&idevent="+idevent);  
        /*con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                checkpart = ser.parseCheckJsonpart(new String(con.getResponseData()));
            }
        });
        */
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        
     }
          
           public void annulpart(String email ,int idevent){
      
       ConnectionRequest con = new ConnectionRequest();
       con.setPost(false);
       con.setHttpMethod("GET");
        con.setUrl("http://localhost/eplants-api/web/app_dev.php/annulpart?login="+email+"&idevent="+idevent);  
        /*con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementService ser = new EvenementService();
                checkpart = ser.parseCheckJsonpart(new String(con.getResponseData()));
            }
        });
        */
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        
     }
}
