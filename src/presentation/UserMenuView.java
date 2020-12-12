package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                userMenuPanel.setVisible(false);
                ctrlPresentation.makeLogInViewVisible();
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
                ctrlPresentation.makeRankingViewVisible("PERSONAL STATS");
            }
        });
        GLOBALRANKINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userMenuPanel.setVisible(false);
                ctrlPresentation.makeRankingViewVisible("GLOBAL RANKING");
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
}
