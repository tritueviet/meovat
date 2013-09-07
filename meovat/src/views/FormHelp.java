package views;

import Control.Controller;
import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.html.HTMLComponent;
import com.sun.lwuit.layouts.BoxLayout;
import models.Var;

public class FormHelp extends Form implements ActionListener {

    //private String title = "Giúp đỡ";
    Command back = new Command(Var.back);

    public FormHelp() {
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        HeaderBar header = null;
        try {
            header = new HeaderBar(Var.help);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        header.setHeaderTitleColor(Var.colorWhite);
        header.getStyle().setBgColor(Var.colorHearder);

        header.setVisible(true);
        header.setScrollable(false);

        addComponent(header);
//        setTitle(Var.help);

        Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        HTMLComponent html = new HTMLComponent();
        html.setBodyText("Ứng dụng giúp cung cấp thông tin cho bạn về các mẹo vặt cuộc sống.","UTF-8");
        TextArea ta = new TextArea("Ứng dụng giúp cung cấp thông tin cho bạn về các mẹo vặt cuộc sống.", 10, 10);
        setScrollable(false);
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
        if (ae.getCommand() == back) {
            Controller.getInstance().showMenu();
        }
    }
}
