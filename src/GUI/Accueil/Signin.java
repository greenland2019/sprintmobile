/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Accueil;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author HP
 */
public class Signin {
      Form f;
   

    public Signin() {
        f = new Form("Inscription",BoxLayout.y());
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
