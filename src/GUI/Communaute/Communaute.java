/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Communaute;

import GUI.Accueil.Accueil;
import Entity.Communaute.Evenement;
import Entity.Communaute.Participation;
import Entity.User.UserSession;
import Service.Communaute.EvenementService;
import Service.Personne.PersonneService;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Communaute {
            EvenementService es = new EvenementService();
            PersonneService ps = new PersonneService();
            UserSession session = UserSession.getInstace("","");
     private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(10);
        renderer.setLegendTextSize(10);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, List<Evenement> values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (Evenement value : values) {
            series.add(value.getDescription(), value.getNb_participants());
        }

        return series;
    }
    
      Form f;
   

    public Communaute() {
        
        f = new Form("Evenements",BoxLayout.y());
        Accueil a = new Accueil();
         f.getToolbar().addCommandToLeftBar("Back", null,d->{
                                 a.getF().showBack();});
        /* f.getToolbar().addCommandToOverflowMenu("Ajouter",null, ev1->{
             
             Form  ajout = new Form("Ajout");
                    Container ca = new Container(BoxLayout.y());
                     ajout.getToolbar().addCommandToLeftBar("Back", null,d->{
                                 f.showBack();
                            });
                 
                    TextArea adesc= new TextArea();
                    adesc.setHint("Description");
                    Picker adate= new Picker();
                    adate.setType(Display.PICKER_TYPE_DATE_AND_TIME);
                    TextArea alieu= new TextArea();
                    alieu.setHint("Lieu");
                    ComboBox atype= new ComboBox("Apprentissage","Exposition","Rencontre");
                    
                    Button ajouter = new Button("Ajouter");
                    
                    ca.add(adesc);
                    ca.add(atype);
                    ca.add(adate);
                    ca.add(alieu);
                    ca.add(ajouter);
                    ajout.add(ca);
                    ajout.show();
                    
                    ajouter.addActionListener((evt) -> {
                        
                    });
         });*/
        f.getToolbar().addCommandToOverflowMenu("Statistiques",null, ev1->{
                            List<Evenement> values = es.listeventparts();
                            
          //  System.out.println(es.listeventparts().get(0).getNb_participants());
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form stat= new Form("Popularité");
        stat.setLayout(new BorderLayout());
        stat.addComponent(BorderLayout.CENTER, c);
        stat.show();
        
        stat.getToolbar().addCommandToLeftBar("Back", null,d->{
                                 f.showBack();
                            });
                        });
        
        List<Evenement> list = es.listevents();
        Container hi = new Container( new GridLayout(2));
        
        for(Evenement e :list){
            
            Image affiche;
            try {
                affiche = Image.createImage("/"+e.getAffiche());
                 affiche.scaled(50,50);
                 ImageViewer iv = new ImageViewer(affiche);
               
                  hi.add(iv);
                  
                  iv.addPointerReleasedListener((evt) -> {
                    Form  details = new Form("Details");
                    Container cd = new Container(BoxLayout.y());
                     details.getToolbar().addCommandToLeftBar("Back", null,d->{
                                 f.showBack();
                            });
                 //affiche.scaled(50,50);
                 ImageViewer iv1 = new ImageViewer(affiche);
                    TextArea l1= new TextArea(e.getDescription());
                    Label ldesc= new Label("Description:");
                    TextArea l2= new TextArea(e.getType());
                    Label ltype= new Label("Type:");
                    TextArea l3= new TextArea(e.getDate_event());
                    Label ldate= new Label("Date:");
                    TextArea l4= new TextArea(e.getLieu());
                    Label lieu= new Label("Lieu:");
                    
                    Button modif = new Button("Modifier");
                    Button partbut = new Button("Participer");
                    Button renoncer = new Button("Renoncer");
                    //cd.add(iv1);
                    cd.add(ldesc);
                    cd.add(l1);
                    cd.add(ltype);
                    cd.add(l2);
                    cd.add(ldate);
                    cd.add(l3);
                    cd.add(lieu);
                    cd.add(l4);
                    cd.add(modif);
                     if(session.getRole().equalsIgnoreCase("client")){
                    if(es.checkPart(session.getUserName(),e.getId()).equalsIgnoreCase("yes")){
                       cd.add(renoncer);
                        renoncer.addActionListener((evt3) -> {
                                es.annulpart(session.getUserName(),e.getId());
                              
                              details.show();
                              details.refreshTheme();
                            });
                        }
                    if(es.checkPart(session.getUserName(),e.getId()).equalsIgnoreCase("no")){
                    cd.add(partbut);
                     partbut.addActionListener((evt3) -> {
                                es.participer(session.getUserName(),e.getId());
                               
                              details.show();
                              details.refreshTheme();
                              
                            });
                        }
                     }
                    details.add(cd);
                    details.show();
                    
                    if(session.getRole().equalsIgnoreCase("admin")){
                        details.getToolbar().addCommandToOverflowMenu("Annuler",null, ev1->{
                            es.deleteEvent(e.getId());
                             Dialog.show("Confirmation", "Evenement supprimé", "OK", null);
                            Communaute c = new Communaute();
                              c.getF().show();
                        });
                        
                        details.getToolbar().addCommandToOverflowMenu("Participants",null, ev1->{
                             Form parts = new Form("Participants",BoxLayout.y());
                            parts.getToolbar().addCommandToLeftBar("Back", null,d->{
                                 details.showBack();
                            });
                            List<Participation> participations = es.participants(e.getId());
                           int k=0;
                            for(Participation p : participations){
                            Label nomp = new Label((++k)+" - "+ ps.FindPersonne(p.getParticipant_id()).getNom()+" "
                                    +ps.FindPersonne(p.getParticipant_id()).getPrenom());
                            parts.add(nomp);
                            }
                            Button pdf = new  Button("PDF");
                            parts.show();
                            
                           
                           
                        });
                        }
                        if(session.getRole().equalsIgnoreCase("client")){
                        modif.remove();
                        l1.setEnabled(false);
                        l2.setEnabled(false);
                        l3.setEnabled(false);
                        l4.setEnabled(false);
                       
                        
                         
                        }
                   modif.addActionListener((evt2) -> {
                       
                   });
                  });
            } catch (IOException ex) {
            }
           
           
            hi.add(e.getDescription());
        
        }

        f.add(hi);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
