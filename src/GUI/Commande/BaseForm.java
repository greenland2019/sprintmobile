
package GUI.Commande;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 *
 * @author Kolsi Ahmed
 */
public class BaseForm extends Form {

    public void installSidemenu(Resources res) {
        Resources theme = UIManager.initFirstTheme("/theme_1");
         
        Image logo=theme.getImage("round.png");
        Container cn= BorderLayout.west(new Label(logo));
        cn.add(BorderLayout.SOUTH,new Label("GreenLand"));
        getToolbar().addComponentToSideMenu(cn);
        getToolbar().addCommandToSideMenu("Home", null, e -> new HomeForm(res).show());
        getToolbar().addCommandToSideMenu("Produits", null, e -> new AllProduit(res).show());
        getToolbar().addCommandToSideMenu("Panier", null, e -> new PanierDetail(res).show());

      
    }

    protected boolean isCurrentInbox() {
        return false;
    }

    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}