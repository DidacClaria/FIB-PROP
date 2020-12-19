package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public UserMenuView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {

        String username = ctrlPresentation.getActiveUser();
        usernameLabel.setText("WELCOME, $" + username);

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
                ctrlPresentation.makeRankingViewVisible(false, username);
            }
        });
        GLOBALRANKINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userMenuPanel.setVisible(false);
                ctrlPresentation.makeRankingViewVisible(true, username);
            }
        });
        DELETEUSERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(userMenuPanel,"Are you sure?");
                if (option == JOptionPane.YES_OPTION) {
                    ctrlPresentation.deleteUser(username);
                    userMenuPanel.setVisible(false);
                    ctrlPresentation.makeLogInViewVisible();
                }
            }
        });
    }

    public JPanel getUserMenuPanel() {
        return userMenuPanel;
    }

    public void setVisible(boolean b) {
        userMenuPanel.setVisible(b);
    }

    public void setUsername(String username){
        usernameLabel.setText(username);
    }


    private void createUIComponents() {

        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/logoutLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            LOGOUTButton = new JButton(icon);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }
}
