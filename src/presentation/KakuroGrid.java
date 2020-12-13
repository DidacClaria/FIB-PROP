package presentation;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class KakuroGrid extends JComponent {

    public void paint(Graphics g) {
        int width=30;
        int height=30;
        for(int x=0;x<10;x++)
        {
            for(int y=0 ;y < 10;y++)
            {
                g.drawRect(x*width,y*height,width,height);

                g.drawString("2", x*width,y*height);
            }
        }
    }
}