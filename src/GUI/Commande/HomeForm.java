/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Commande;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author LENOVO
 */
public class HomeForm extends BaseForm {

    Container mother = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    Container welcome = new Container(new FlowLayout(CENTER, CENTER));
    Container Imgw = new Container(new FlowLayout(CENTER, CENTER));
    Container owner = new Container(new FlowLayout(CENTER, CENTER));

   
     public HomeForm( ) {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    

    public HomeForm(com.codename1.ui.util.Resources resourceObjectInstance) {

        installSidemenu(resourceObjectInstance);
        initGuiBuilderComponents( resourceObjectInstance) ;
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        Label lwelcome = new Label("Welcome Hedi  " );
        Label lOwenr = new Label("Created by GreenLand");
        int deviceWidth = Display.getInstance().getDisplayWidth();
        int deviceHeight = Display.getInstance().getDisplayHeight();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        URLImage url = URLImage.createToStorage(encImage, "vv", "http://localhost:8080/template2/web/templates/images/8.jpg");
        ScaleImageLabel sl = new ScaleImageLabel(url);
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        welcome.add(lwelcome);
        Imgw.add(sl);
        owner.add(lOwenr);
        mother.add(welcome);
        mother.add(Imgw);
        mother.add(owner);
        addComponent(mother);
    }

}