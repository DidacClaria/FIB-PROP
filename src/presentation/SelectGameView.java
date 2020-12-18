package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SelectGameView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel selectGamePanel;
    private JPanel headerContainer;
    private JButton GOBACKButton;
    private JLabel titleLabel;
    private JScrollPane listGamesContainer;


    public SelectGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        GOBACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectGamePanel.setVisible(false);
                ctrlPresentation.makeUserMenuViewVisible();
            }
        });
    }

    public void setVisible(boolean b) {
        selectGamePanel.setVisible(b);
    }
    public void setGameVisible() {ctrlPresentation.makePlayGameViewVisible();}
    public void setGamesStarted(int id_kakuro) {
        ctrlPresentation.iniGame(id_kakuro);
        ctrlPresentation.makeStartedGameViewVisible();
    }

    public JPanel getSelectGamePanel() {
        return selectGamePanel;
    }

    private void createUIComponents() {
        createBackImage();
        createListKakuros();
    }

    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            GOBACKButton = new JButton(icon);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }
    }

    private void createListKakuros () {

        String [] files = ctrlPresentation.getKakurosGlobals();

        JPanel aux = new JPanel();

        for (int i = 0; i < files.length; ++i) {
            if (i % 2 == 0) {
                int id = files[i].charAt(6) - '0';
                JPanel aux2 = new RowSelectKakuro(this, id);
                aux2.setBorder(BorderFactory.createLineBorder(Color.black));
                aux.add(aux2);
            }
        }
        aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
        listGamesContainer = new JScrollPane(aux);
    }
}
