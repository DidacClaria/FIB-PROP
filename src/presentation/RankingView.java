package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RankingView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel rankingPanel;
    private JButton gobackButton;
    private JScrollPane rankingList;
    private JPanel headerContainer;
    private JLabel title;

    private JTextArea list;

    public RankingView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        gobackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rankingPanel.setVisible(false);
                ctrlPresentation.makeUserMenuViewVisible();
            }
        });
    }

    public void setVisible(boolean b) {
        rankingPanel.setVisible(b);
    }

    public JPanel getRankingPanel() {
        return rankingPanel;
    }

    private void createUIComponents() {
        createBackImage();
        list = new JTextArea();
        rankingList = new JScrollPane(list);
    }

    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            gobackButton = new JButton(icon);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }

    public void createRGText () {
        String text = ctrlPresentation.listGlobalRanking();
        list.setText(text);
        list.setEditable(false);
    }

    public void createPSText () {
        String text = ctrlPresentation.listPersonalStats(ctrlPresentation.getActiveUser());
        list.setText(text);
        list.setEditable(false);
    }

    public void setTitle (String name) {
        title.setText(name);
    }
}
