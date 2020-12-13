package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logo extends JPanel {

    private BufferedImage image;
    private Image newImage;

    public Logo(int width, int height) {
        try {
            image = ImageIO.read(new File("DOCS/kakuroLogo.jpg"));
            newImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newImage, 0, 0, this); // see javadoc for more info on the parameters
    }

}