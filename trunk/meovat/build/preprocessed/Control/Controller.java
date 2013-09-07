/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import com.nokia.mid.ui.CategoryBar;
import com.nokia.mid.ui.ElementListener;
import com.nokia.mid.ui.IconCommand;
import com.sun.lwuit.Form;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import event.Event;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Image;
import models.Var;
import views.FormAbout;
import views.FormHelp;
import views.FormMenu;
import views.FormModels;
import views.FormModelsSearch;
import views.FormSearch;
import views.FormViewResut;
import views.LoadScreen;

/**
 *
 * @author TRITUEVIET
 */
public class Controller {

    private static Controller instance = null;
    private static Resources theme;
    public static Main main;
    private FormModels formModel = null;
    private FormSearch formSeach = null;
    private FormMenu formMenu = null;
    private FormHelp formHelp = null;
    private FormAbout formAbout = null;
    private FormModelsSearch formModelSearch;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void chay(Main main) {
        this.main = main;


        //  màn hình load nếu cần có thể không càn bỏ  thì k cho code chạy
        if (1 == 0) {
            LoadScreen load = new LoadScreen(main);
            javax.microedition.lcdui.Display.getDisplay(main).setCurrent(load);
            load.start();
            load = null;

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        //categoryBar.setVisibility(true);
        showMenu();

    }

    public void showMenu() {
//        Form a = new Form();
//        a.show();
        if (formMenu == null) {
            formMenu = new FormMenu();
        }
        formMenu.showFormMenu();
    }

    public void showResuit(String path, String idArticle) {
        FormViewResut formViewResut = new FormViewResut(path, idArticle);
        formViewResut.showFormModels();
    }

    public void showSearchh() {
        if (formSeach == null) {
            formSeach = new FormSearch();
        }
        formSeach.show();

    }

    public void showResuitSearch(Vector v) {
        formModelSearch = new FormModelsSearch(v);
        formModelSearch.showFormModels();
    }

    public void showModels(String title, String path) {
        try {
            formModel = new FormModels(title, path);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        formModel.showFormModels();
    }

    public void showAbout() {
        if (formAbout == null) {
            formAbout = new FormAbout();
        }
        formAbout.show();

    }

    public void showHelp() {
        if (formHelp == null) {
            formHelp = new FormHelp();
        }
        formHelp.show();
    }

    public void handleEvent(int eventType, Event evt) {
        switch (eventType) {

        }
    }

    //  hàm load theme  nhưng do chỉ chạy trên 501 nên k cần thiết load theme vì nó sẽ lấy sẵn ở thư viện
    public static void loadTheme() {
        try {
            if (theme == null) {
                theme = Resources.open("/themes/asha2013_theme.res");
            }
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (Throwable ex) {
        }
    }

    public void exit() {
        //   saveConfig();
        main.destroyApp(true);
    }
}