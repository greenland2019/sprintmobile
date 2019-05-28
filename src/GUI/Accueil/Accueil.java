/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accueil;

import Entity.User.Personne;
import Entity.User.UserSession;
import Entity.reclamation.Reclamation;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import GUI.Communaute.Communaute;
import GUI.Commande.AllProduit;
import GUI.Reclamation.Areclamation;
import GUI.Reclamation.reclamation;
import Service.Personne.PersonneService;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;


/**
 *
 * @author HP
 */
public class Accueil {
     Form f;
   

    public Accueil() {
         try {
             PersonneService ps = new PersonneService();
             Personne  p = ps.FindPersonne(UserSession.getInstace("", "").getId());
             Resources theme = UIManager.initFirstTheme("/theme_1");
             f = new Form("Accueil",BoxLayout.y());
             f.getToolbar().addCommandToSideMenu("Communaute", null, (evt) -> {
                 Communaute c  = new Communaute();
                 c.getF().show();
             });
             Image deconnect =  Image.createImage("/icon-power.png");
             deconnect.scale(25, 25);
              f.getToolbar().addCommandToRightBar(p.getPrenom(),null, a1 -> {
                
             });
             f.getToolbar().addCommandToRightBar("",deconnect, a -> {
                 UserSession.getInstace("", "").cleanUserSession();
                 new Login().getF().show();
             });
             f.getToolbar().addCommandToSideMenu("Produits", null, (evt) -> {
                 AllProduit allp  = new AllProduit();
                 allp.show();
             });
             
             if(UserSession.getInstace("", "").getRole().equalsIgnoreCase("client")){
                  f.getToolbar().addCommandToSideMenu("Mes reclamations", null, (evt) -> {
                      (new reclamation()).getMainForm();
             });
             
             }
             
             /* if(UserSession.getInstace("", "").getRole().equalsIgnoreCase("admin")){
                  f.getToolbar().addCommandToSideMenu("Reclamations", null, (evt) -> {
                 AllProduit allp  = new AllProduit();
                 allp.show();
             });
             
             }*/
         } catch (IOException ex) {
            
         }
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
