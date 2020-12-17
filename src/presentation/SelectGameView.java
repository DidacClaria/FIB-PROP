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

    public JPanel getSelectGamePanel() {
        return selectGamePanel;
    }

    private void createUIComponents() {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            GOBACKButton = new JButton(icon);
        } catch (IOException ex) {
            System.out.println("The file does not exists");
        }

        JPanel aux = new JPanel();

        for (int i = 0; i < 50; ++i) {
            JPanel aux2 = new RowSelectKakuro(i);
            aux2.setBorder(BorderFactory.createLineBorder(Color.black));
            aux.add(aux2);
        }
        aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
        listGamesContainer = new JScrollPane(aux);
    }
}
