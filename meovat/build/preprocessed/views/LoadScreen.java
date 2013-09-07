package views;

import Control.Main;
import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * @TRI TUE VIET
 */

// form show màn hình load


public class LoadScreen extends GameCanvas{
    private Graphics g;
    Main main;
    
    
    public LoadScreen(Main main) {
        super(false);
        
        this.g = this.getGraphics();
        this.main= main;
        //setFullScreenMode(true);
    }

    public void paint() {
        g.setColor(0xffffff);
        g.fillRect(0, 0, getWidth(), getHeight());
        try {
            g.drawImage(Image.createImage("/images/conga.png"), getWidth() / 2, getHeight() / 2, Graphics.VCENTER | Graphics.HCENTER);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.flushGraphics();

    }

    public void start() {
       
            paint();
            
    }
}
