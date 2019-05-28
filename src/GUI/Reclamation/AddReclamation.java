package GUI.Reclamation;

import Entity.User.UserSession;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import Service.Reclamation.ReclamationService;
import Entity.reclamation.Reclamation;
import GUI.Accueil.Accueil;
import GUI.Reclamation.Statics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author feryel
 */
public class AddReclamation extends Form {
        
    private final Label l1,l2,l3,l4,l5;
    private final TextField emailTf,sujetTf,categorieTf,contenuTf;
    private final Container mainContainer;
    private final Button addBtn,backBtn,affichBtn;
    
   public AddReclamation(){
        Accueil a = new Accueil();
         this.getToolbar().addCommandToLeftBar("Back", null,d->{
                                 a.getF().showBack();});
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Add a new reclamation");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("email:");
        emailTf = new TextField(""); 
        l3 = new Label("categorie:");
        categorieTf = new TextField("");
        l4 = new Label("sujet:");
        sujetTf= new TextField("");
        l5 = new Label("contenu:");
        contenuTf= new TextField("");
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        affichBtn=new Button("affich");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        /*mainContainer.add(l2);
        Statics.setLabelStyle(l3);*/
        mainContainer.add(l3);
        //mainContainer.add(emailTf);
        mainContainer.add(categorieTf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        mainContainer.add(sujetTf);
        mainContainer.add(contenuTf);
        mainContainer.add(addBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        mainContainer.add(affichBtn);
        
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a book
            Reclamation rec = new Reclamation(UserSession.getInstace("", "").getUserName(),categorieTf.getText(),sujetTf.getText(),contenuTf.getText());
            new  ReclamationService().newrec(rec);
            });
        this.add(BorderLayout.NORTH, mainContainer);
   
        affichBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a book
            Reclamation rec = new Reclamation(UserSession.getInstace("", "").getUserName(),categorieTf.getText(),sujetTf.getText(),contenuTf.getText());
            new  ReclamationService().findAll(UserSession.getInstace("", "").getUserName());
            });
       // this.add(BorderLayout.NORTH, mainContainer);
   }
   

}
