package presentation;

import java.awt.*;

public class Logo extends Canvas {
    public void paint(Graphics g) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("");
        g.drawImage(i, 120,100,this);
    }
}
