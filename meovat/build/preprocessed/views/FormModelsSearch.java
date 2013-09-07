package views;

import Control.Controller;
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

public class FormModelsSearch implements ActionListener, CommandListener {

    // private String title = "Thông tin";
    Command back = new Command(Var.back);
    List list;
    Vector items;
    javax.microedition.lcdui.List lis;
    javax.microedition.lcdui.Command cmdBack = new javax.microedition.lcdui.Command(Var.back, javax.microedition.lcdui.Command.BACK, 0);

    public FormModelsSearch(Vector items) {
        this.items = Var.itemSave = items;

    }

    public void showFormModels2() {
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);

//        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.header_bar", Boolean.TRUE);

        lis = new javax.microedition.lcdui.List(Var.resuit, javax.microedition.lcdui.List.IMPLICIT);
        for (int i = 0; i < items.size(); i++) {
            lis.append(((Article) items.elementAt(i)).getTitle(), null);
        }
        lis.addCommand(cmdBack);
        lis.setCommandListener(this);

        javax.microedition.lcdui.Display.getDisplay(Controller.main).setCurrent(lis);

    }

    public void showFormModels() {
        //Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        HeaderBar header = null;
        try {
            header = new HeaderBar(Var.resuit);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        header.setHeaderTitleColor(Var.colorWhite);
        header.getStyle().setBgColor(Var.colorHearder);
        header.setScrollable(false);

        Form form = new Form();
        form.refreshTheme();
        form.addComponent(header);
        if (items.size() == 0) {
            Label nullResuilts = new Label("không tìm thấy kết quả.");
            nullResuilts.getStyle().setMargin(10, 10, 10, 10);
            nullResuilts.getStyle().setBgTransparency(0);
            nullResuilts.getStyle().setFgColor(Var.colorBlack);

            Container containn = new Container();
            containn.addComponent(nullResuilts);
            containn.setScrollableY(true);
            
            form.addComponent(containn);
            form.setBackCommand(back);
            form.addCommand(back);
            form.addCommandListener(this);
            form.show();
            
        } else {
            list = new List(createGenericListCellRendererModelData());
            list.setRenderer(new GenericListCellRenderer(createGenericRendererContainer(),
                    createGenericRendererContainer()));

            list.addActionListener(this);

            form.addComponent(list);
            form.setBackCommand(back);
            form.addCommand(back);
            form.addCommandListener(this);
            form.setScrollable(false);
            form.show();
        }
        /*list= new List();
        for (int i =0 ; i < items.size(); i ++){
        list.addItem(((Article)items.elementAt(i)).getTitle());
        }
         */


    }

    private Container createGenericRendererContainer() {
        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);

        Container f = new Container(new BorderLayout());
        Label bank = new Label();
        bank.setName("data");
        bank.getStyle().setAlignment(Label.LEFT);
        bank.setFocusable(true);
        bank.getStyle().setBgTransparency(0);
        bank.getStyle().setFgColor(0x000000);
        bank.setEndsWith3Points(true);

        //f.addComponent(BorderLayout.WEST,labImg);
        f.addComponent(BorderLayout.CENTER,bank);

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
            //System.out.println(""+((Article) items.elementAt(i)).getTitle());
        }

        return data;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getCommand() == back) {
//            items=null;
            System.out.println("back ve search ");
            Controller.getInstance().showSearchh();
//            Runtime.getRuntime().gc();
        } else if (ae.getSource() == list) {
            Var.backForm = 2;
            Article a = (Article) items.elementAt(list.getSelectedIndex());
            Controller.getInstance().showResuit(a.getTitlePath(), a.getId());

        }
    }

    public void commandAction(javax.microedition.lcdui.Command c, Displayable d) {
        if (c == cmdBack) {
            Controller.getInstance().showSearchh();
        } else if (d == lis) {
            Var.backForm = 2;
            Article a = (Article) items.elementAt(lis.getSelectedIndex());
            Controller.getInstance().showResuit(a.getTitlePath(), a.getId());

        }
    }
}
