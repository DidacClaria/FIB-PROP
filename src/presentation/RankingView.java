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
    private JTable list;

    private final String [][] data_AUX;

    public RankingView(CtrlPresentation ctrlPresentation, String [][] data) {
        this.ctrlPresentation = ctrlPresentation;
        this.data_AUX = data;
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
        createRankingList();
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

    private void createRankingList () {
        String [] components = {"KAKURO ID", "USERNAME", "TIME", "ASK_HINTS", "SCORES"};
        if (data_AUX != null) list = new JTable(data_AUX, components);
        else {
            String[][] nullMatrix = new String[1][5];
            nullMatrix[0] = new String[]{"-", "-", "-", "-", "-"};
            list = new JTable(nullMatrix, components);
        }
        rankingList = new JScrollPane(list);
    }

    public void setTitle (String name) {
        title.setText(name);
    }
}
