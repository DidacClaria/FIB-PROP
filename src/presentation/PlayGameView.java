package presentation;

import org.w3c.dom.html.HTMLBodyElement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayGameView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel playGamePanel;
    private JPanel titleContainer;
    private JPanel kakuroContainer;
    private JButton ASKHINTButton;
    private JButton SAVEANDEXITButton;
    private JButton BACKButton;
    private JLabel timerLabel;
    private JLabel titleLabel;
    private JLabel errorMessage;
    private JPanel buttonsContainer;
    private JPanel footerContainer;

    public PlayGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ctrlPresentation.makeSelectGameViewVisible();
            }
        });
    }

    public JPanel getPlayGamePanel() {
        return playGamePanel;
    }

    public void setVisible(Boolean b){
        playGamePanel.setVisible(b);
    }

    private void createUIComponents() {
        createBackImage();
    }

    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            BACKButton = new JButton(icon);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }
}
