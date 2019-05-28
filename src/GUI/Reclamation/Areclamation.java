/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

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
import Entity.reclamation.Reclamation;
import GUI.Accueil.Accueil;
import Service.Reclamation.ReclamationService;
import GUI.Reclamation.Statics;

/**
 *
 * @author Amal
 */
public class Areclamation extends Form {
    
    private final Label l1,l2,l3,l4,l5;
    private final TextField EmailTf,SujetTf,CategorieTf,ContenuTf;
    private final Container mainContainer;
    private final Button editBtn,removeBtn,backBtn;
    private Reclamation claim;
    
    

    public Areclamation(String email,String sujet,String contenu,String categorie){
         Accueil a = new Accueil();
         this.getToolbar().addCommandToLeftBar("Back", null,d->{
                                 a.getF().showBack();});
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Reclamation "+categorie);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("email:");
        EmailTf = new TextField(email); 
        l3 = new Label("sujet:");
        SujetTf = new TextField(sujet);
        l4 = new Label("contenu:");
        ContenuTf= new TextField(contenu);
        l5 = new Label("categorie:");
        CategorieTf= new TextField(categorie);
        editBtn= new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(EmailTf);
        mainContainer.add(SujetTf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        mainContainer.add(CategorieTf);
        mainContainer.add(ContenuTf);
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        claim = new Reclamation( email, sujet, contenu, categorie);
        editBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           new ReclamationService().updaterec(claim);
            });
        removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new ReclamationService().removerec(claim);
            }
        });
        this.add(BorderLayout.NORTH, mainContainer);
    }

   

    
}
