/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Commande;

import Entity.User.UserSession;
import Entity.commande.Produit;
import GUI.Accueil.Accueil;
import Service.Commande.ProduitService;
import Service.Communaute.PromotionService;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author User
 */
public class AllProduit extends BaseForm {

    

    public AllProduit() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public AllProduit(com.codename1.ui.util.Resources resourceObjectInstance) {

        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);

    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        if(UserSession.getInstace("", "").getRole().equalsIgnoreCase("client")){
        getToolbar().addCommandToOverflowMenu("Mon panier", null, (evt) -> {
            PanierDetail pd = new PanierDetail();
            pd.show();
        });
        }
        getToolbar().addCommandToLeftBar("Back", resourceObjectInstance.getImage("back-command.png"), a -> {
            new Accueil().getF().show();
        });
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Nos Plantes");
        setName("AcceuilForm");
        ProduitService task = new ProduitService();
        List<Produit> ev = task.getAll();
        for (Produit p : ev) {
            String a;
            a=Double.toString(p.getPrix());
            Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
            MultiButton gui_LA = new com.codename1.components.MultiButton();
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");

            gui_Multi_Button_1.setPropertyValue("line1", p.getNom());
            gui_Multi_Button_1.setPropertyValue("line2", "");
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
            gui_LA.setUIID("Label");
            gui_LA.setName("LA");
            gui_LA.setPropertyValue("line1", "prix:"+a+" DT");
            gui_LA.setPropertyValue("line2", " " );
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
            Button gui_Button_1 = new com.codename1.ui.Button();
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);

            gui_Text_Area_1.setText("");
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");
            gui_Button_1.setText("");
            gui_Button_1.setUIID("Label");
            gui_Button_1.setName("Button_1");
            gui_Button_1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {

                    new ProduitDetail(new Produit(p.getId(), p.getNom(), p.getImg(), p.getDescription(), p.getPrix(), p.getStock()),resourceObjectInstance).show();
                }

            });
            com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1, '');
            gui_Button_1.setPressedIcon(com.codename1.ui.FontImage.createMaterial('', gui_Button_1.getStyle()));

            gui_Container_2.setName("Container_2");
            Label gui_separator1 = new com.codename1.ui.Label();
            addComponent(gui_separator1);
            gui_separator1.setShowEvenIfBlank(true);

            int deviceWidth = Display.getInstance().getDisplayWidth();
            int deviceHeight = Display.getInstance().getDisplayHeight();
            Image placeholder = Image.createImage(deviceWidth, deviceHeight/4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            URLImage url = URLImage.createToStorage(encImage, p.getImg() + "ano", "http://localhost/template2/web/templates/images/" + p.getImg());
            ScaleImageLabel sl = new ScaleImageLabel(url);
            
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            gui_imageContainer1.add(BorderLayout.CENTER, sl);
            PromotionService ps = new PromotionService();
            
            if(ps.findpromo(p.getId()).getId()!=0){
            
            Label promo = new Label("Reduction : "+ps.findpromo(p.getId()).getReduction()+" %");
            gui_imageContainer1.add(BorderLayout.SOUTH,promo);
            }
            
            sl.addPointerReleasedListener((evt) -> {
           new ProduitDetail
        (new Produit(p.getId(), p.getNom(), p.getImg(), p.getDescription(), p.getPrix(), p.getStock()),resourceObjectInstance)
                   .show();
           });
            FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
            gui_LA.setIconPosition(BorderLayout.EAST);
            gui_Text_Area_1.setRows(2);
            gui_Text_Area_1.setColumns(100);
            gui_Text_Area_1.setGrowByContent(false);
            gui_Text_Area_1.setEditable(false);

            gui_separator1.setUIID("Separator");

            gui_separator1.setShowEvenIfBlank(true);


        }

    }
}
