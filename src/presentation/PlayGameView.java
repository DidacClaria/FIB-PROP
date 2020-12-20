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
    private static int cnt=0, timerCount, numHints;
    Timer  timer;

    public PlayGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        errorMessage.setText("");
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGamePanel.setVisible(false);
                ctrlPresentation.makeSelectGameViewVisible();
                timer.stop();
            }
        });
        VALIDATESOLUTIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ctrlPresentation.validateSolution(gamesScenario.getField());
            }
        });
        ASKHINTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++numHints;
//                String newValue = ctrlPresentation.askHint(numHints);
//
            }
        });
        SAVEANDEXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ctrlPresentation.updateStats;
//                ctrlPresentation.updateFieldStatus;
                playGamePanel.setVisible(false);
                ctrlPresentation.makeSelectGameViewVisible();
                timer.stop();
            }
        });
        timer =new Timer(1000,new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                ++timerCount;
                timerValue.setText(String.valueOf(timerCount));
            }
        });
    }

    public JPanel getPlayGamePanel() {
        return playGamePanel;
    }

    public void setVisible(Boolean b, int idGame){
//        String sizeAndField = ctrlPresentation.getGameScenario();
//        stringToKakuroGrid(sizeAndField);
//        String stats = ctrlPresentation.getStats();
//        stringToStats(stats);
        timerCount = 0;
        timerValue.setText(String.valueOf(timerCount));
        timer.restart();
        playGamePanel.setVisible(b);
    }

    private void stringToStats(String stats) {
        String[] parts = stats.split(":");
        timerCount = Integer.parseInt(parts[0]);
        numHints = Integer.parseInt(parts[1]);
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
        kakuroContainer.removeAll();
        kakuroContainer.repaint();
        kakuroContainer.revalidate();

        gamesScenario = new KakuroGrid(numRows,numCols,kakuroField,true);
        kakuroContainer.add(gamesScenario);
        kakuroContainer.repaint();
        kakuroContainer.revalidate();
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

    private String[][] getFieldStatus(){
        return gamesScenario.getFieldStatus();
    }
}
