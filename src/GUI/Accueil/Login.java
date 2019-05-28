/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accueil;

import Entity.User.Personne;
import Entity.User.UserSession;
import Service.Personne.PersonneService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author HP
 */
public class Login {
     Form f;
   

    public Login() {
        
         Resources theme = UIManager.initFirstTheme("/theme_1");
       
        PersonneService ser= new PersonneService();
        f = new Form("Connexion",BoxLayout.y());
      
        TextField emailtxt = new  TextField("","Email",20,20);
        TextField passtxt = new  TextField("","Mot de passe",20,TextArea.PASSWORD);
        Button con = new Button("Se connecter");
        Button ins = new Button("Inscription");
        
        Container c1 = new Container(BoxLayout.y());
        Container c2 = new Container(BoxLayout.y());
        
        c1.add(emailtxt);
        c1.add(passtxt);
        c1.add(con);
        c1.add(ins);
        
        f.add(c1);
        
        con.addActionListener((evt) -> {
            if(emailtxt.getText().equalsIgnoreCase("") || passtxt.getText().equalsIgnoreCase("")){
                Dialog.show("Warning", "Veuillez remplir tous les champs", "OK", null);
            }
            else{
                PersonneService ps = new PersonneService();
                
                Personne p = ps.connect(emailtxt.getText(), passtxt.getText());
               if(p.getEmail()!=null){
                   UserSession.getInstace(p.getEmail(), p.getRole());
                   UserSession.getInstace("","").setId(p.getId());
                   Accueil s = new Accueil();
                   s.getF().show();
               }
               else
                Dialog.show("Erreur", "Informations erronées", "OK", null);
            }

        });
        
        ins.addActionListener((evt) -> {
            Signin s = new Signin();
        s.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
