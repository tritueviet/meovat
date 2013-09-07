/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Control.Controller;
import Control.DataAccessLayer;
import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Dialog;
import com.sun.lwuit.Form;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Gauge;
import models.Var;

/**
 *
 * @author TRITUEVIET
 */
public class FormSearch extends Form implements ActionListener, CommandListener {

    TextField texfeild;
    Button btnSearch = new Button(Var.search);
    private Command back = new Command(Var.back);
    public String SPECIAL_CHARACTERS = "àÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđĐèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆìÌỉỈĩĨíÍịỊòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰýÝỳỲỹỸỷỶyỴ";
    public String REPLACEMENTS = "aAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAdDeEeEeEeEeEeEeEeEeEeEeEiIiIiIiIiIoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOuUuUuUuUuUuUuUuUuUuUuUyYyYyYyYyY";
    private javax.microedition.lcdui.Command cmdBack;
    boolean show = true;
    private Dialog dia;

    public FormSearch() {
    
        texfeild = new TextField();
        TextField.setDefaultValign(TextField.ANY);
        addComponent(texfeild);
        addComponent(btnSearch);
        btnSearch.addActionListener(this);
        setBackCommand(back);
        addCommand(back);
        addCommandListener(this);
        
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSearch) {
            show = true;
            //Dialog.show(null, "Wating...", null, null);
            
            javax.microedition.lcdui.Gauge gauge = new Gauge(null, false, Gauge.INDEFINITE,
            Gauge.CONTINUOUS_RUNNING);
            javax.microedition.lcdui.Alert alert = new Alert("chờ đợi...");
            
            alert.setIndicator(gauge);
            cmdBack = new javax.microedition.lcdui.Command("trở về", javax.microedition.lcdui.Command.BACK, 0);
            alert.addCommand(cmdBack);
            alert.setCommandListener(this);
            
            alert.setType(AlertType.INFO);
            alert.setTimeout(Alert.FOREVER);
            
            javax.microedition.lcdui.Display.getDisplay(Controller.main).setCurrent(alert);
            
          

         //   dia = new Dialog("chờ đợi.. .");
//            dia.setAutoDispose(true);

            new Thread(new Runnable() {

                public void run() {

                    DataAccessLayer dal = new DataAccessLayer(Var.dataXmlPath);
                    Vector v = dal.searchByKeyword(texfeild.getText().trim().toLowerCase());
//                    Form a = new Form();
//                    a.show();
                    if (show == true) {
//                        dia.dispose();
//                        dia.showBack();
                        System.out.println("chay");
                        Controller.getInstance().showResuitSearch(v);
                    }
                }
            }).start();
            //dia.showDialog();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //Runtime.getRuntime().gc();
        } else if (ae.getCommand() == back) {
            //show = false;
            Form a = new Form();
            a.show();
            Controller.getInstance().showMenu();
        }
    }

    public void commandAction(javax.microedition.lcdui.Command c, Displayable d) {
        if (c == cmdBack) {
            show = false;
            Controller.getInstance().showSearchh();
//            Runtime.getRuntime().gc();
        }
    }
}
