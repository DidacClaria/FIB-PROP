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
 * This class represents the RankingView and with all the components added into the rankingView it is represented. It also communicates with the Presentation Controller.
 */
public class RankingView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel rankingPanel;
    private JButton gobackButton;
    private JScrollPane rankingList;
    private JPanel headerContainer;
    private JLabel title;
    private JTable list;

    private final String [][] dataAUX;

    /**
     * Default RankingView constructor.
     * @param ctrlPresentation Reference of the presentation controller.
     * @param data This variable contains the information to be represented in the view.
     */
    public RankingView(CtrlPresentation ctrlPresentation, String [][] data) {
        this.ctrlPresentation = ctrlPresentation;
        this.dataAUX = data;
        initComponents();
    }

    /**
     * This method initialize all the different buttons and their specific behaviour.
     */
    private void initComponents() {
        gobackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rankingPanel.setVisible(false);
                ctrlPresentation.makeUserMenuViewVisible();
            }
        });
    }

    /**
     * This method sets the panel of the view as visible or not depending on the bool of the parameter.
     * @param b Indicates whether the view must show or not.
     */
    public void setVisible(boolean b) {
        rankingPanel.setVisible(b);
    }

    /**
     * Getter method of the rankingPanel.
     * @return It returns the instance of the rankingPanel.
     */
    public JPanel getRankingPanel() {
        return rankingPanel;
    }

    /**
     * This method initialize the custom components of the view.
     */
    private void createUIComponents() {
        createBackImage();
        createRankingList();
    }

    /**
     * This method configure the BACKButton so that it has the desired appearance.
     */
    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            gobackButton = new JButton(icon);
            gobackButton.setText("");
            gobackButton.setBorderPainted(false);
            gobackButton.setFocusPainted(false);
            gobackButton.setContentAreaFilled(false);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }

    private void createRankingList () {
        String [] components = {"KAKURO ID", "USERNAME", "TIME", "ASK_HINTS", "SCORES"};
        if (dataAUX != null) list = new JTable(dataAUX, components);
        else {
            String[][] nullMatrix = new String[1][5];
            nullMatrix[0] = new String[]{"-", "-", "-", "-", "-"};
            list = new JTable(nullMatrix, components);
        }
        rankingList = new JScrollPane(list);
    }

    /**
     * Setter of the title of the view.
     * @param name Indicates whether it is "GLOBAL RANKING" or "PERSONAL STATS".
     */
    public void setTitle (String name) {
        title.setText(name);
    }
}
