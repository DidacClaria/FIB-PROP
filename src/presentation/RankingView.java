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

    private JTextArea list;

    /**
     * Default RankingView constructor.
     * @param ctrlPresentation Reference of the presentation controller.
     */
    public RankingView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
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
        list = new JTextArea();
        rankingList = new JScrollPane(list);
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
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }

    /**
     * This method fills the table with the entries of the globalRanking.
     */
    public void createRGText () {
        String text = ctrlPresentation.listGlobalRanking();
        list.setText(text);
        list.setEditable(false);
    }

    /**
     * This method sets the talbe with the entries of the personalRanking.
     */
    public void createPSText () {
        String text = ctrlPresentation.listPersonalStats();
        list.setText(text);
        list.setEditable(false);
    }

    /**
     * Setter of the title of the view.
     * @param name Indicates whether it is "GLOBAL RANKING" or "PERSONAL STATS".
     */
    public void setTitle (String name) {
        title.setText(name);
    }
}
