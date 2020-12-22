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
    private static int cnt=0, timerCount=0, numHints=0;
    Timer  timer;

    private int kakuro_id;

    /**
     * Default PlayGameView constructor.
     * @param ctrlPresentation Reference of the presentation controller.
     */
    public PlayGameView(CtrlPresentation ctrlPresentation, int idKakuro) {
        this.ctrlPresentation = ctrlPresentation;
        this.kakuro_id = idKakuro;
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
                ctrlPresentation.validateSolution(kakuro_id, timerCount, numHints, gamesScenario.getFieldStatus());
            }
        });
        ASKHINTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numHints+1 <= 3) {
                    ++numHints;
                    String newValue = ctrlPresentation.askHint(kakuro_id, gamesScenario.getFieldStatus());
                    String[] parts = newValue.split(":");
                    gamesScenario.setValueField(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[0]));

                    String [][] GamesHint = gamesScenario.getFieldStatus();

                    kakuroContainer.removeAll();
                    kakuroContainer.repaint();
                    kakuroContainer.revalidate();

                    gamesScenario = new KakuroGrid(GamesHint.length,GamesHint[0].length,GamesHint,true);
                    kakuroContainer.add(gamesScenario);
                    kakuroContainer.repaint();
                    kakuroContainer.revalidate();
                }
                else {
                    errorMessage.setText("You have already used it 3 times!");
                }
            }
        });
        SAVEANDEXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlPresentation.saveGame (timerCount, numHints, gamesScenario.getFieldStatus());
                playGamePanel.setVisible(false);
                ctrlPresentation.makeSelectGameViewVisible();
                timer.stop();
            }
        });
        timer =new Timer(1000,new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                ++timerCount;
                timerValue.setText(formatTimer(timerCount));
            }
        });
    }

    private String formatTimer (int t) {
        String h = String.valueOf(t/3600);
        String m = String.valueOf((t%3600)/60);
        String s = String.valueOf((t%3600)%60);

        if (h.length() < 2) h = "0"+h;
        if (m.length() < 2) m = "0"+m;
        if (s.length() < 2) s = "0"+s;

        return h+":"+m+":"+s;
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
    public void setVisible(Boolean b, String game){

        String[] parts = game.split(":");
        stringToKakuroGrid(parts[2], parts[3]);
        stringToStats(parts[0],parts[1]);

        timerValue.setText(formatTimer(timerCount));
        timer.restart();
        playGamePanel.setVisible(b);
    }

    /**
     * This operation interprets the formatted String as the different stats values.
     * @param stats It has the current time, score, and numHints divided by ":".
     */
    private void stringToStats(String timerC, String numH) {
        timerCount = Integer.parseInt(timerC);
        numHints = Integer.parseInt(numH);
    }

    /**
     * This operation interprets the formatted String as a KakuroGrid. It also repaints the custom component.
     * @param sizeAndField It contains the information of a KakuroGrid divided by ":".
     */
    private void stringToKakuroGrid(String siz, String f) {
        String[] size = siz.split(",");
        int numRows = Integer.parseInt(size[0]);
        int numCols = Integer.parseInt(size[1]);
        String[][] kakuroField = new String[numRows][numCols];
        String[] field = f.split(",");
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
