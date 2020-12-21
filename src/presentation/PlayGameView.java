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
 * This class represents the PlayGameView and with all the components added into the playGamePanel it is represented. It also communicates with the Presentation Controller.
 */
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

    /**
     * Default PlayGameView constructor.
     * @param ctrlPresentation Reference of the presentation controller.
     */
    public PlayGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    /**
     * This method initialize all the different buttons and their specific behaviour.
     */
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

    /**
     * Getter method of the logInPanel.
     * @return It returns the instance of the logInPanel.
     */
    public JPanel getPlayGamePanel() {
        return playGamePanel;
    }

    /**
     * This method sets the panel of the view as visible or not depending on the bool of the parameter. It also sets the timer to the correct value, and the current status of the field.
     * @param b Indicates whether the view must show or not.
     * @param idGame Indicates which Game to load.
     */
    public void setVisible(Boolean b, int idGame){
//        String sizeAndField = ctrlPresentation.getGameScenario();
//        stringToKakuroGrid(sizeAndField);
//        String stats = ctrlPresentation.getStats();
//        stringToStats(stats);
        /*
        String[][] field = new String[9][9];
        String f= "*,*,C19,C12,*,*,*,C7,C10,*,F14,?,?,C4,C11,C17F4,?,?,*,C7F36,?,?,?,?,?,?,?,F12,?,?,F10,?,?,?,C25,C14,F3,?,?,C20,C11F20,?,?,?,?,F17,?,?,?,?,C8,F6,?,?,*,C11,C7F13,?,?,?,C4F10,?,?,F28,?,?,?,?,?,?,?,*,F6,?,?,*,*,F8,?,?,*";
        String[] l= f.split(",");
        for (int i=0; i<9;++i){
            for (int j=0; j<9; ++j){
                field[i][j] = l[i*9+j];
            }
        }
        gamesScenario = new KakuroGrid(9,9,field,true);
        kakuroContainer.removeAll();
        kakuroContainer.repaint();
        kakuroContainer.revalidate();
        kakuroContainer.add(gamesScenario);
        kakuroContainer.repaint();
        kakuroContainer.revalidate();
        */
        errorMessage.setText("");
        timerCount = 0;
        timerValue.setText(String.valueOf(timerCount));
        timer.restart();
        playGamePanel.setVisible(b);
    }

    /**
     * This operation interprets the formatted String as the different stats values.
     * @param stats It has the current time, score, and numHints divided by ":".
     */
    private void stringToStats(String stats) {
        String[] parts = stats.split(":");
        timerCount = Integer.parseInt(parts[0]);
        numHints = Integer.parseInt(parts[1]);
    }

    /**
     * This operation interprets the formatted String as a KakuroGrid. It also repaints the custom component.
     * @param sizeAndField It contains the information of a KakuroGrid divided by ":".
     */
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

    /**
     * This method initialize the custom components of the view.
     */
    private void createUIComponents() {
        createBackImage();
        kakuroContainer = new JPanel(new GridLayout());
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
            BACKButton = new JButton(icon);
            BACKButton.setText("");
            BACKButton.setBorderPainted(false);
            BACKButton.setFocusPainted(false);
            BACKButton.setContentAreaFilled(false);
        } catch (IOException ex) {}
    }

    /**
     * This method returns the current status of the field from one game played.
     * @return The field in a String matrix.
     */
    private String[][] getFieldStatus(){
        return gamesScenario.getFieldStatus();
    }
}
