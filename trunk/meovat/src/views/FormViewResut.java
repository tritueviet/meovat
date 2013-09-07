package views;

import Control.Controller;
import Control.DataAccessLayer;
import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.List;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import models.Article;
import models.Var;

public class FormViewResut implements ActionListener, CommandListener {

    // private String title = "Thông tin";
    Command back = new Command(Var.back);
    String idArticle;
    String path;
    List list;
    private Image img;
    javax.microedition.lcdui.Command cmdBack = new javax.microedition.lcdui.Command(Var.back, javax.microedition.lcdui.Command.BACK, 0);

    public FormViewResut(String path, String idArticle) {
        this.path = path;
        this.idArticle = idArticle;
    }

    public void showFormModels2() {
        DataAccessLayer dal = new DataAccessLayer(Var.dataXmlPath);
        Article a = dal.getArticleById(path, Integer.parseInt(idArticle));
        
        javax.microedition.lcdui.Form form = new javax.microedition.lcdui.Form(a.getTitle());
        //javax.microedition.lcdui.TextField txtf= new TextField(path, path, maxSize, constraints);
        
        javax.microedition.lcdui.Font f1 = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM, javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_LARGE);  
        
        StringItem messageItem = new StringItem("", a.getTitle());
        messageItem.setFont(f1);
        
        form.append(messageItem);
        try {
            form.append(javax.microedition.lcdui.Image.createImage("/images/line.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        form.append(a.getContent());
        form.addCommand(cmdBack);
        form.setCommandListener(this);
        
        javax.microedition.lcdui.Display.getDisplay(Controller.main).setCurrent(form);
        
    }

    public void showFormModels() {
        DataAccessLayer dal = new DataAccessLayer(Var.dataXmlPath);
        Article a = dal.getArticleById(path, Integer.parseInt(idArticle));

        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        HeaderBar header = null;
        try {
            header = new HeaderBar(a.getTitle());
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        header.setHeaderTitleColor(Var.colorWhite);
        header.getStyle().setBgColor(Var.colorHearder);
        //header.set

        header.setScrollable(false);

        Form form = new Form();
        form.addComponent(header);
//        form.setTitle(a.getTitle());
        form.setScrollable(false);

        Container con = new Container();

        TextArea textare = new TextArea(a.getTitle(), 2, 10);
        textare.setEditable(false);
        textare.getStyle().setFgColor(0x000000);
        textare.getSelectedStyle().setFgColor(0x000000);
        textare.getUnselectedStyle().setFgColor(0x000000);
        textare.getStyle().setFont(font);
        textare.getSelectedStyle().setFont(font);


        TextArea textare2 = new TextArea(a.getContent(), 2, 10);

        textare2.getStyle().setFgColor(0x000000);
        textare2.getSelectedStyle().setFgColor(0x000000);
        textare2.getUnselectedStyle().setFgColor(0x000000);
        textare2.setEditable(false);
        textare2.setGrowByContent(true);
        textare2.setUIID("Form");

        con.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        con.addComponent(textare);
        con.addComponent(textare2);
        con.setScrollableY(true);
        form.addComponent(con);

        form.setBackCommand(back);
        form.addCommand(back);
        form.addCommandListener(this);
        form.show();


    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getCommand() == back) {
            if (Var.backForm == 1) {
                Controller.getInstance().showModels(Var.nameForm, Var.stringPath);
            } else if (Var.backForm == 2) {
                FormModelsSearch formModelSearch = new FormModelsSearch(Var.itemSave);
                formModelSearch.showFormModels();
            }
        }

    }

    public void commandAction(javax.microedition.lcdui.Command c, Displayable d) {
        if (c == cmdBack) {
            if (Var.backForm == 1) {
                Controller.getInstance().showModels(Var.nameForm, Var.stringPath);
            } else if (Var.backForm == 2) {
                FormModelsSearch formModelSearch = new FormModelsSearch(Var.itemSave);
                formModelSearch.showFormModels();
            }
        }
    }
}
