package views;

import Control.Controller;
import Control.DataAccessLayer;
import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.list.GenericListCellRenderer;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import models.Article;
import models.Var;

public class FormModels implements ActionListener, CommandListener{

    
   // private String title = "Thông tin";
    Command back= new Command(Var.back);
    List list;
    
    Vector items;
    String tittle;
    String filePath;
    javax.microedition.lcdui.List lis;
    javax.microedition.lcdui.Command cmdBack = new javax.microedition.lcdui.Command(Var.back, javax.microedition.lcdui.Command.BACK, 0);
    
    public FormModels(String tittle, String filePath) {
        
        Var.nameForm= tittle;
        Var.stringPath=filePath;
        this.tittle = tittle;
        this.filePath = filePath;
        DataAccessLayer dal = new DataAccessLayer(Var.dataXmlPath);
        items = dal.getTitlesByPath(filePath);
    }

    public void  showFormModels2(){
        
        
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        
//        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.header_bar", Boolean.TRUE);
        
        lis= new javax.microedition.lcdui.List(Var.nameForm,javax.microedition.lcdui.List.IMPLICIT);
        for (int i =0 ; i< items.size(); i++ ){
            lis.append(((Article)items.elementAt(i)).getTitle(), null);
        }
        lis.setFitPolicy(javax.microedition.lcdui.List.TEXT_WRAP_OFF);
        lis.addCommand(cmdBack);
        lis.setCommandListener(this);
        
        javax.microedition.lcdui.Display.getDisplay(Controller.main).setCurrent(lis);
        
        
    }
    
     private Container createGenericRendererContainer() {
        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);

        Container f = new Container();
        
        Label bank = new Label();
        bank.setName("data");
        bank.setFocusable(true);
        bank.getStyle().setBgTransparency(0);
        bank.getStyle().setFgColor(0x000000);
        bank.setEndsWith3Points(true);

        //f.addComponent(BorderLayout.WEST,labImg);
        f.addComponent(bank);

        return f;
    }

    private Hashtable[] createGenericListCellRendererModelData() {
        Hashtable[] data = new Hashtable[items.size()];
        String nd;
        for (int i = 0; i < items.size(); i++) {
            data[i] = new Hashtable();
            nd=((Article) items.elementAt(i)).getTitle();
            if (nd.charAt(0)!=' ') data[i].put("data"," "+nd);
            else data[i].put("data",nd);
        }

        return data;
    }
    
    public void showFormModels(){
        
        
        
        //Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        HeaderBar header = null;
        try {
            header = new HeaderBar(tittle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        header.setHeaderTitleColor(Var.colorWhite);
        header.getStyle().setBgColor(Var.colorHearder);
        header.setScrollable(false);
        
        /*
        list= new List();
        for (int i =0 ; i < items.size(); i ++){
            list.addItem(((Article)items.elementAt(i)).getTitle());
        }
        
        list.setScrollAnimationSpeed(100);
        list.setScrollOpacityChangeSpeed(200);
        */
        
        list = new List(createGenericListCellRendererModelData());
            list.setRenderer(new GenericListCellRenderer(createGenericRendererContainer(),
                    createGenericRendererContainer()));
        
        list.addActionListener(this);
        
        
        
        Form form= new Form();
        form.addComponent(header);
//        form.setTitle(tittle);
        Container con= new Container();
        con.setScrollableY(true);
        con.addComponent(list);
        form.setScrollable(false);
        form.addComponent(con);
        form.setBackCommand(back);
        form.addCommand(back);
        form.addCommandListener(this);
        form.setScrollAnimationSpeed(100);
        form.setScrollOpacityChangeSpeed(100);
        form.show();
    }
    

    public void actionPerformed(ActionEvent ae) {
        if (ae.getCommand()==back){
            Controller.getInstance().showMenu();
        }
        else if (ae.getSource()==list){
            Var.backForm=1;
            Article a = (Article) items.elementAt(list.getSelectedIndex());
            Controller.getInstance().showResuit(a.getTitlePath(),a.getId());
        }
    }

    public void commandAction(javax.microedition.lcdui.Command c, Displayable d) {
        if (c==cmdBack){
            Controller.getInstance().showMenu();
        }
        else if (d==lis){
            System.out.println("list");
            Var.backForm=1;
            Article a = (Article) items.elementAt(lis.getSelectedIndex());
            Controller.getInstance().showResuit(a.getTitlePath(),a.getId());
        }
    }
}
