/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Reclamation;
import com.teknikindustries.bulksms.SMS;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import Entity.reclamation.Reclamation;
import GUI.Reclamation.Areclamation;
import GUI.Reclamation.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Amal
 */
public class ReclamationService {
        
    private ConnectionRequest connectionRequest;
    public static Form listRec;
    public void newrec(Reclamation reclamation){

        connectionRequest=new ConnectionRequest(){
            
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("Add to my reclamation ");
            TextArea popupBody = new TextArea("reclamation successfully added");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
           // d.showDialog();
            Dialog.show("Succès", "Reclamation ajoutée avec succès", "Ok", null);
            }
        };
      connectionRequest.setUrl("http://localhost/ReclamationV5/web/app_dev.php/newjson/" + reclamation.getEmail() + "/" + reclamation.getSujet()+"/"+reclamation.getContenu()+"/"+reclamation.getCategorie());
       NetworkManager.getInstance().addToQueue(connectionRequest);
       SMS smsText = new SMS();
      smsText.SendSMS("feryel", "hajjiferyel", "l'utilisateur a envoyé une reclamation " , "+21627493146", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
    }
    public void removerec(Reclamation b){   // remove reclamation by title
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("Remove reclamation from database");
            TextArea popupBody = new TextArea("Book successfully removed");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);                                 
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.showDialog();
            }           
        };
        connectionRequest.setUrl("http://localhost/ReclamationV5/web/app_dev.php/deletejson/" + b.getSujet());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void findAll(String email){
        connectionRequest = new ConnectionRequest() {
        ArrayList<Reclamation> claim = new ArrayList();
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    java.util.List<Map<String, Object>> content = (java.util.List<Map<String, Object>>) data.get("root");
                    claim.clear();
                  
                    for (Map<String, Object> obj : content) {
                        claim.add(new Reclamation((String) obj.get("email"),(String) obj.get("sujet"),(String) obj.get("contenu"),(String) obj.get("categorie"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                 listRec = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Reclamation l :claim){
                    libsNoms.add(l.getSujet());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Reclamation currentBook = claim.get(uiLibsList.getCurrentSelected());
                        new Areclamation(currentBook.getEmail(),currentBook.getSujet(),currentBook.getContenu(),currentBook.getCategorie()).show();
                    }
                });
                listRec.setLayout(new BorderLayout());
                listRec.add(BorderLayout.NORTH,uiLibsList);
                listRec.add(BorderLayout.SOUTH,Statics.createBackBtn());
                listRec.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/ReclamationV5/web/app_dev.php/all/"+email);
        NetworkManager.getInstance().addToQueue(connectionRequest);
        
        
    }
    public void updaterec(Reclamation b){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
                Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("Book updated");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.show();
            }
        };
        connectionRequest.setUrl("http://localhost/ReclamationV5/web/app_dev.php/updatejson"+b.getSujet()+"/"+b.getCategorie()+"/"+b.getContenu()+"/"+b.getEmail());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
}
