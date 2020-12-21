package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the UserMenuView and with all the components added into the userMenuPanel it is represented. It also communicates with the Presentation Controller.
 */
public class UserMenuView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel userMenuPanel;
    private JButton GLOBALRANKINGButton;
    private JButton PERSONALSTATSButton;
    private JButton PLAYGAMEButton;
    private JButton CREATEKAKUROButton;
    private JButton LOGOUTButton;
    private JLabel usernameLabel;
    private JButton DELETEUSERButton;

    /**
     * Default UserMenuView constructor.
     * @param ctrlPresentation Reference of the presentation controller.
     */
    public UserMenuView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    /**
     * This method initialize all the different buttons and their specific behaviour.
     */
    private void initComponents() {

        String username = ctrlPresentation.getActiveUser();
        usernameLabel.setText("WELCOME, " + username.toUpperCase());

        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int option = JOptionPane.showConfirmDialog(userMenuPanel,"Are you sure?");
                if (option == JOptionPane.YES_OPTION) {
                    userMenuPanel.setVisible(false);
                    ctrlPresentation.makeLogInViewVisible();
                }
            }
        });
        CREATEKAKUROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                userMenuPanel.setVisible(false);
                ctrlPresentation.makeCreateKakuroViewVisible();
            }
        });
        PLAYGAMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userMenuPanel.setVisible(false);
                ctrlPresentation.makeSelectGameViewVisible();
            }
        });
        PERSONALSTATSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userMenuPanel.setVisible(false);
                ctrlPresentation.iniRanking(ctrlPresentation.listPersonalStats());
                ctrlPresentation.makeRankingViewVisible(false);
            }
        });
        GLOBALRANKINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userMenuPanel.setVisible(false);
                ctrlPresentation.iniRanking(ctrlPresentation.listGlobalRanking());
                ctrlPresentation.makeRankingViewVisible(true);
            }
        });
        DELETEUSERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(userMenuPanel,"Are you sure?");
                if (option == JOptionPane.YES_OPTION) {
                    ctrlPresentation.deleteUser();
                    userMenuPanel.setVisible(false);
                    ctrlPresentation.makeLogInViewVisible();
                }
            }
        });
    }

    /**
     * Getter method of the userMenuPanel.
     * @return It returns the instance of the userMenuPanel.
     */
    public JPanel getUserMenuPanel() {
        return userMenuPanel;
    }

    /**
     * This method sets the panel of the view as visible or not depending on the bool of the parameter.
     * @param b Indicates whether the view must show or not.
     */
    public void setVisible(boolean b) {
        userMenuPanel.setVisible(b);
    }

    /**
     * This operations sets the JLabel text depending on the username in the parameter.
     * @param username Indicates the username of the active user.
     */
    public void setUsername(String username){
        usernameLabel.setText(username);
    }


    /**
     * This method initialize the custom components of the view.
     */
    private void createUIComponents() {

        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/logoutLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            LOGOUTButton = new JButton(icon);
            LOGOUTButton.setText("");
            LOGOUTButton.setBorderPainted(false);
            LOGOUTButton.setFocusPainted(false);
            LOGOUTButton.setContentAreaFilled(false);
        } catch (IOException ex) {
//            System.out.println("The file does not exists");
        }
    }
}
