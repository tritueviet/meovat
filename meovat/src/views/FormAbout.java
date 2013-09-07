package views;

import Control.Controller;
import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.html.HTMLComponent;
import com.sun.lwuit.layouts.BoxLayout;
import models.Var;

public class FormAbout extends Form implements ActionListener{

    
   // private String title = "Thông tin";
    Command back= new Command(Var.back);

    public FormAbout() {
        
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        
        
        HeaderBar header = null;
        try {
           header = new HeaderBar(Var.about);
        } catch (Exception ex) {
            ex.printStackTrace();
       }
        header.setHeaderTitleColor(Var.colorWhite);
        header.getStyle().setBgColor(Var.colorHearder);
        
        header.setVisible(true);
        header.setScrollable(false);
        
        addComponent(header);
//        
//        setTitle(Var.about);
        
        
        
//        getTitleComponent().setVisible(false);
//        getTitleArea().setVisible(false);
        
        
//        Label lbTittle = new Label(Var.about);
//        lbTittle.getStyle().setMargin(0, 0, 0, 0);
//        lbTittle.getStyle().setBgColor(Var.colorHearder);
//        lbTittle.getStyle().setFgColor(Var.colorWhite);
//        Container contai1= new Container();
//        contai1.addComponent(lbTittle);
//        contai1.getStyle().setMargin(0, 0, 0, 0);
//        
//        
//        contai1.setScrollable(false);
//        
//        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//        addComponent(contai1);
        
        
        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    
        //setLayout(new BoxLayout(CENTER));
        TextArea ta = new TextArea("name: Tips for Life"
                + "\nsize: 0.98 MB"
                + "\ncreated: 24-05-2013"
                + "\n\nVersion: 1.0"
                + "\nVendor: Nova Vietnam"
                + "\nCertificate: no",10,10);
        setScrollable(false);
        ta.getUnselectedStyle().setFgColor(0xffffff);
        ta.getPressedStyle().setFgColor(0xffffff);
        
        HTMLComponent html=new HTMLComponent();
        html.setBodyText("<span> name: Tips for Life"
                + "<br/> size: 0.98 MB"
                + "<br/> created: 24-05-2013"
                + "<br> Version: 1.0"
                + "<br/> Vendor: Nova Vietnam"
                +" Certificate: no </span>","UTF-8");
        
        //ta.setGrowByContent(true);
        ta.setEditable(false);
        setBackCommand(back);
        addCommand(back);
        addCommandListener(this);
        Container con= new Container();
        con.getStyle().setMargin(10, 10, 10, 10);
        con.addComponent(html);
        addComponent(con);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getCommand()==back){
            Controller.getInstance().showMenu();
        }
    }
}
