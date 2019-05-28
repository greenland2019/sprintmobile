/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Commande;

import Entity.Communaute.Promotion;
import Entity.User.UserSession;
import Entity.commande.Produit;
import Service.Commande.ProduitService;
import Service.Communaute.PromotionService;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author User
 */
public class ProduitDetail extends BaseForm {

    // private final Label l1, l2, l3, l4, l5, l6, l7, l8, l9, titreTf, descriptionTf, lieuTf, nbrplaceTf, typeTf, pointsTf, points_totalTf, coutTf, l10, l11, dateDebutf, dateFinTf;
    //private final Container mainContainer;
    private Produit currentEvent;
    private final Button ret = new Button("retour");
    private int ok = 0;

    private static final String HTML_API_KEY = "AIzaSyAm5SZQCFioAfRkJMiaVMcSxF_CbfDtHZQ";

    public ProduitDetail(Produit e, com.codename1.ui.util.Resources resourceObjectInstance) {
        String v;
        v = Double.toString(e.getPrix());
        ProduitService eventsdao = new ProduitService();
        List<Produit> ev = eventsdao.getAll();
        setTitle(e.getNom());
        setName("EventsForm");
        getToolbar().addCommandToLeftBar("Back", resourceObjectInstance.getImage("back-command.png"), a -> {
            new AllProduit().show();
        });

        Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
        addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        MultiButton gui_LA = new com.codename1.components.MultiButton();
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");

        gui_Multi_Button_1.setPropertyValue("line1", e.getNom());
        gui_Multi_Button_1.setPropertyValue("line2",  e.getPrix()+"dt");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        gui_LA.setPropertyValue("line1", v);
        gui_LA.setPropertyValue("line2", "");
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");

        Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);

        gui_Text_Area_1.setText(e.getDescription());
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");

        Label gui_separator1 = new com.codename1.ui.Label();
        addComponent(gui_separator1);
        gui_separator1.setShowEvenIfBlank(true);

        int deviceWidth = Display.getInstance().getDisplayWidth();
        int deviceHeight = Display.getInstance().getDisplayHeight();
        Image placeholder = Image.createImage(deviceWidth, deviceHeight /3 , 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        System.out.println(e.getImg());
        URLImage url = URLImage.createToStorage(encImage, e.getImg() + "act2", "http://localhost/template2/web/templates/images/" + e.getImg());
        ScaleImageLabel sl = new ScaleImageLabel(url);
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer1.add(BorderLayout.CENTER, sl);

        installSidemenu(resourceObjectInstance);

        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);

        
        
        
        
        
        Label qte = new Label("Qte");
        TextField Tqte = new TextField();
        Button valider = new Button("Ajouter");
        Container gui_Container_4 = new com.codename1.ui.Container();
        addComponent(gui_Container_4);
        if(UserSession.getInstace("", "").getRole().equalsIgnoreCase("client")){
        gui_Container_4.addComponent(qte);
        gui_Container_4.addComponent(Tqte);
        gui_Container_4.addComponent(valider);
        }
        PromotionService promoser = new PromotionService();
       
            
            Promotion p = promoser.findpromo(e.getId());
            System.out.println(p.getId());
               if(UserSession.getInstace("", "").getRole().equalsIgnoreCase("admin")){
                    if(p.getId()==0)
             
             {
                 
                 TextField valeur = new TextField();
                 Label labeldate = new  Label("Date de fin");
                 valeur.setHint("Pourcentage");
                 Picker datefin= new Picker();
                    datefin.setType(Display.PICKER_TYPE_DATE);
             Button promobut = new Button("Promouvoir");
             promobut.setHeight(10);
             Calendar calendar = Calendar.getInstance();
             gui_Container_4.add(valeur);
             gui_Container_4.add(labeldate);
             gui_Container_4.add(datefin);
             gui_Container_4.add(promobut);
             
             promobut.addActionListener((evt) -> {
                 if(valeur.getText().equalsIgnoreCase("") || datefin.getText().equalsIgnoreCase("")){
                 Dialog.show("Warning", "Veuillez remplir tous les champs", "OK", null);
                 }
                   SimpleDateFormat sdf = new SimpleDateFormat("d/m/y");
                 Date date1 = null;
                 Date date2 = null;
                     try {
                          date1 = sdf.parse(datefin.getText());
                          date2 = sdf.parse(sdf.format(new Date()));
                          Calendar calendar1 = Calendar.getInstance();
                          calendar1.setTime(date1);
                          Calendar calendar2= Calendar.getInstance();
                          calendar2.setTime(date2);
                          boolean b = calendar2.get(Calendar.YEAR)*calendar2.get(Calendar.DAY_OF_MONTH)*calendar2.get(Calendar.MONTH)
                                  >calendar1.get(Calendar.YEAR)*calendar1.get(Calendar.DAY_OF_MONTH)*calendar1.get(Calendar.MONTH);
                          System.out.println(b);
                          System.out.println(calendar2.get(Calendar.DAY_OF_MONTH)+"/"+(calendar2.get(Calendar.MONTH)+1)+"/"+calendar2.get(Calendar.YEAR));
                          System.out.println(calendar1.get(Calendar.DAY_OF_MONTH)+"/"+(calendar1.get(Calendar.MONTH)+1)+"/"+calendar1.get(Calendar.YEAR));
                          
                           if(b){
               Dialog.show("Warning", "Veuillez saisir une date ultérieure", "OK", null);

              }
              
             
              else if(!b && !valeur.getText().equalsIgnoreCase("") && !datefin.getText().equalsIgnoreCase("")){
                     try {
                         Date date3 = sdf.parse(datefin.getText());
                         calendar1.setTime(date3);
                         String datetosend = calendar.get(Calendar.DAY_OF_MONTH)+"-"
                                 +calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR);
                         if(Double.valueOf(valeur.getText())<100 || Double.valueOf(valeur.getText())==100){
                 promoser.promouvoir(e.getId(), datetosend, Double.valueOf(valeur.getText()));
                 Dialog.show("Succès", "Promotion en cours", "OK", null);
                  new ProduitDetail(new Produit(e.getId(), e.getNom(), e.getImg(), e.getDescription(),e.getPrix()- e.getPrix()*Double.valueOf(valeur.getText())/100, e.getStock()),resourceObjectInstance).show();
                         }
                         else {
                         Dialog.show("Warning", "Saisir un pourcentage inférieur ou égal à 100", "OK", null);
                         }
                     }
                     catch(NumberFormatException ex){
                      Dialog.show("Warning", "Saisir un bon pourcentage", "OK", null);
                     }
                 
                 }
                     } catch (ParseException ex) {
                         System.out.println(ex.getMessage());
                     }
                 
             
             });
             }
            else
                    {
                Button deletepromo = new Button("Annuler la promotion");
                gui_Container_4.addComponent(deletepromo);
                
                deletepromo.addActionListener((evt) -> {
                    System.out.println(e.getId());
                     promoser.cancelpromo(e.getId());
                    Dialog.show("Succès", "Promotion annulée", "OK", null);
                   
                  new ProduitDetail(new Produit(e.getId(), e.getNom(), e.getImg(), e.getDescription(),e.getPrix()/(1-p.getReduction()/100), e.getStock()),resourceObjectInstance).show();
                });
             }
         }
        valider.addActionListener(a -> {

            eventsdao.ajouterPanier(e.getId(),Tqte.getText(),UserSession.getInstace("","").getId());
            LocalNotification n = new LocalNotification();
            n.setId("demo-notification");
            n.setAlertBody("produit a ete ajouté avec succes");
            n.setAlertTitle("Confirmation");

            Display.getInstance().scheduleLocalNotification(
                    n,
                    System.currentTimeMillis() + 1, // fire date/time
                    LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
            );
            new AllProduit().show();
        });

    }

}
