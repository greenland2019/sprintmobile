/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Commande;

import Entity.commande.Produit;
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
 * @author User
 */
public class ProduitService {

    List<Produit> listTasks = new ArrayList<>();
    String total ="";

    public List<Produit> getListTask(String json) {

        List<Produit> listProduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Produit e = new Produit();
               e.setId (Double.valueOf(""+ obj.get("id")));
                e.setNom((String) obj.get("nom"));
                e.setImg((String) obj.get("img"));
                e.setDescription((String) obj.get("description"));
                e.setPrix (Double.valueOf(""+obj.get("prix")));
                e.setStock (Double.valueOf(""+ obj.get("stock")));
                
                listProduits.add(e);
            }

        } catch (IOException ex) {
        }
        return listProduits;

    }
    public List<Produit> getListTask2(String json) {

        List<Produit> listProduits = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Produit e = new Produit();
                e.setId (Double.valueOf(""+ obj.get("id")));
                e.setNom((String) obj.get("nom"));
                e.setImg((String) obj.get("img"));
                e.setDescription((String) obj.get("description"));
                e.setPrix (Double.valueOf(""+obj.get("prix")));
                e.setStock (Double.valueOf(""+ obj.get("stock")));
                
                listProduits.add(e);
            }

        } catch (IOException ex) {
        }
        return listProduits;

    }
    public String getListTask3(String json) {

        String a="";

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                a = (String) obj.get("result");
                
            }

        } catch (IOException ex) {
        }
        return a;

    }

    public List<Produit> getAll() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/template2/web/app_dev.php/restAllProduit");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
     public void deletepanier(Double idproduit , Double idperson) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/template2/web/app_dev.php/deletepanier/"+idproduit+"/"+idperson);
       
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public List<Produit> ajouter(Double a) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/template2/web/app_dev.php/restConfirmer/"+a);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public List<Produit> getAllUser(Double a) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/template2/web/app_dev.php/restAllProduitUser/"+a);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                listTasks = ser.getListTask2(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public List<Produit> ajouterPanier(Double a,String b,Double c) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/template2/web/app_dev.php/restAjouterP/"+a+"/"+b+"/"+c);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    public String prixPanier(int a) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/template2/web/app_dev.php/restPrix/"+a);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProduitService ser = new ProduitService();
                total = ser.getListTask3(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return total;
    }
  
}
