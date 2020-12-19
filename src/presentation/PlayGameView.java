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
    private JLabel titleLabel;
    private JLabel errorMessage;
    private JPanel buttonsContainer;
    private JPanel footerContainer;
    private JLabel timerValue;
    private JButton VALIDATESOLUTIONButton;

    private KakuroGrid gamesScenario;

    public PlayGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void stringToKakuroGrid(String sizeAndField) {
        String[] parts = sizeAndField.split(":");
        String[] size = parts[0].split(",");
        int numRows = Integer.parseInt(size[0]);
        int numCols = Integer.parseInt(size[1]);
        String[][] kakuroField = new String[numRows][numCols];
        String[] field = parts[1].split(",");
        for (int i = 0; i<numRows; ++i) {
            for (int j=0; j<numCols; ++j){
                kakuroField[i][j] = field[i*numCols+j];
            }
        }
        kakuroContainer.add(new KakuroGrid(numRows,numCols,kakuroField,true));
        kakuroContainer.repaint();
        kakuroContainer.revalidate();
    }

    private void initComponents() {
        errorMessage.setText("");
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kakuroContainer.removeAll();
                kakuroContainer.repaint();
                kakuroContainer.revalidate();
                playGamePanel.setVisible(false);
                ctrlPresentation.makeSelectGameViewVisible();
            }
        });
    }

    public JPanel getPlayGamePanel() {
        return playGamePanel;
    }

    public void setVisible(Boolean b, int gameID){
//        String sizeAndField = ctrlPresentation.getGameScenario(gameID);
//        stringToKakuroGrid(sizeAndField);
        playGamePanel.setVisible(b);
    }

    private void createUIComponents() {
        createBackImage();
        kakuroContainer = new JPanel(new GridLayout());
    }

    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            BACKButton = new JButton(icon);
            BACKButton.setText("");
            BACKButton.setBorderPainted(false);
            BACKButton.setFocusPainted(false);
            BACKButton.setContentAreaFilled(false);
        } catch (IOException ex) {}
    }
}
