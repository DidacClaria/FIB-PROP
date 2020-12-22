package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is a custom component that extends the JPanel class, and it is used to display the Logo image of the program showed in the Log In View.
 */
public class Logo extends JPanel {

    private BufferedImage image;
    private Image newImage;

    /**
     * Default Logo constructor
     * @param width Indicates the width for the size of the image.
     * @param height Indicates the width for the size of the image.
     */
    public Logo(int width, int height) {
        try {
            image = ImageIO.read(new File("DOCS/kakuroLogo.jpg"));
            newImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
//            System.out.println("The file does not exists");
        }
    }

    /**
     * Paints each of the components in this container.
     *
     * @param g the graphics context.
     * @see Component#paint
     * @see Component#paintAll
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(newImage, 0, 0, this);
    }

}