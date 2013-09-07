/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Control.Controller;
import Control.DataAccessLayer;
import com.sun.lwuit.Button;
import com.sun.lwuit.ComboBox;
import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.List;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.list.DefaultListModel;
import com.sun.lwuit.list.ListModel;
import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import models.Category;
import models.Var;

/**
 *
 * @author TRITUEVIET
 */
public class FormMenu implements ActionListener, CommandListener {

    ListModel model;
    List list;
    Command commanAbout = new Command(Var.about);
    Button btnSearch = new Button(Var.search);
    Command commanHelp = new Command(Var.help);
    Vector cats;
    javax.microedition.lcdui.Command commandSearch = new javax.microedition.lcdui.Command(Var.search, javax.microedition.lcdui.Command.ITEM, 0);
    javax.microedition.lcdui.Command commandAbout = new javax.microedition.lcdui.Command(Var.about, javax.microedition.lcdui.Command.ITEM, 1);
    javax.microedition.lcdui.Command commandHelp = new javax.microedition.lcdui.Command(Var.help, javax.microedition.lcdui.Command.ITEM, 2);
    javax.microedition.lcdui.List lis;

    public void showFormMenu() {
        DataAccessLayer dal = new DataAccessLayer(Var.dataXmlPath);
        cats = dal.getVectorCategories();


        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);

        lis = new javax.microedition.lcdui.List(null, javax.microedition.lcdui.List.IMPLICIT);
        for (int i = 0; i < cats.size(); i++) {
            Category c = (Category) cats.elementAt(i);

            try {
                lis.append("  " + c.getCategoryName(), javax.microedition.lcdui.Image.createImage(c.getImagePath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        lis.addCommand(commandSearch);
        lis.addCommand(commandAbout);
        lis.addCommand(commandHelp);

        lis.setCommandListener(this);
        javax.microedition.lcdui.Display.getDisplay(Controller.main).setCurrent(lis);


    }

    public void showFormMenu2() {
        DataAccessLayer dal = new DataAccessLayer(Var.dataXmlPath);
        cats = dal.getVectorCategories();


        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);

        Form form = new Form();
        form.setScrollable(false);

        model = new DefaultListModel();


        for (int i = 0; i < cats.size(); i++) {
            Category c = (Category) cats.elementAt(i);
            // Command item = new Command("  "+Var.menu[i], image[i]);

            Command item = null;
            try {
                item = new Command("  " + c.getCategoryName(), Image.createImage(c.getImagePath()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (null != item) {
                model.addItem(item);
            }
        }
        list = new List(model);

        form.addComponent(list);
        list.addActionListener(this);
        form.addCommand(commanHelp);
        form.addCommand(commanAbout);
        form.addComponent(btnSearch);
        btnSearch.addActionListener(this);

        form.addCommandListener(this);
        form.show();
        //System.out.println("fin...");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getCommand() == commanAbout) {
            FormAbout formAbout = new FormAbout();
            formAbout.show();
        } else if (ae.getCommand() == commanHelp) {
            FormHelp formHelp = new FormHelp();
            formHelp.show();
        } else if (ae.getSource() == btnSearch) {
            Controller.getInstance().showSearchh();
        } else if (ae.getSource() == list) {

            // Bắt sự kiện click vào list trên HomePage

            //System.out.println(""+list.getSelectedIndex());
            // FormModels formModel = null;
            //  đoc dũ ;liệu list đầu 

//            Var.nameForm = list.getSelectedItem().toString();
//            Var.chon = list.getSelectedIndex();

            Category c = (Category) cats.elementAt(list.getSelectedIndex());
//System.out.println("c.getCategoryName(): "+c.getCategoryName()+"c.getCategoryPath():"+c.getCategoryPath());
            Controller.getInstance().showModels(c.getCategoryName(), c.getCategoryPath());

        }
    }

    public void commandAction(javax.microedition.lcdui.Command c, Displayable d) {
        if (c == commandAbout) {
            Controller.getInstance().showAbout();
        } else if (c == commandHelp) {
            Controller.getInstance().showHelp();
        } else if (c == commandSearch) {
            Controller.getInstance().showSearchh();
        } else if (d == lis) {
            // Bắt sự kiện click vào list trên HomePage

            Category cate = (Category) cats.elementAt(lis.getSelectedIndex());
//System.out.println("c.getCategoryName(): "+c.getCategoryName()+"c.getCategoryPath():"+c.getCategoryPath());
            Controller.getInstance().showModels(cate.getCategoryName(), cate.getCategoryPath());
        }
    }
}
