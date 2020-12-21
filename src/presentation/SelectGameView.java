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
 * This class represents the SelectGameView and with all the components added into the selectGamePanel it is represented. It also communicates with the Presentation Controller.
 */
public class SelectGameView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel selectGamePanel;
    private JPanel headerContainer;
    private JButton GOBACKButton;
    private JLabel titleLabel;
    private JScrollPane listGamesContainer;

    /**
     * Default SelectGameView constructor.
     * @param ctrlPresentation Reference of the presentation controller.
     */
    public SelectGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    /**
     * This method initialize all the different buttons and their specific behaviour.
     */
    private void initComponents() {
        GOBACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectGamePanel.setVisible(false);
                ctrlPresentation.makeUserMenuViewVisible();
            }
        });
    }

    /**
     * This method sets the panel of the view as visible or not depending on the bool of the parameter.
     * @param b Indicates whether the view must show or not.
     */
    public void setVisible(boolean b) {
        selectGamePanel.setVisible(b);
    }

    /**
     * This method indicates to the presentation controller to set the specified game view visible.
     * @param idGame Indicates whether the view must show or not.
     */
    public void setGameVisible(int idGame) {ctrlPresentation.makePlayGameViewVisible(idGame);}

    /**
     * This method initializes the StartedGamesView.
     * @param idKakuro Indicates the identifier of the kakuros to play.
     */
    public void setGamesStarted(int idKakuro) {
        ctrlPresentation.iniGame(idKakuro);
        ctrlPresentation.makeStartedGameViewVisible();
    }

    /**
     * Getter method of the selectGamePanel.
     * @return It returns the instance of the selectGamePanel.
     */
    public JPanel getSelectGamePanel() {
        return selectGamePanel;
    }

    /**
     * This method initialize the custom components of the view.
     */
    private void createUIComponents() {
        createBackImage();
        listGamesContainer = new JScrollPane(new JPanel());
//        createListKakuros();
    }

    /**
     * This method configure the GOBACKbutton so that it has the desired appearance.
     */
    private void createBackImage () {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("DOCS/gobackLogo.png"));
            Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon (newImage);
            GOBACKButton = new JButton(icon);
            GOBACKButton.setText("");
            GOBACKButton.setBorderPainted(false);
            GOBACKButton.setFocusPainted(false);
            GOBACKButton.setContentAreaFilled(false);
        } catch (IOException ex) {
//            System.out.println("The file does not exists");
        }
    }

    /**
     * This method configures the list of kakuros available for the user.
     */
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

    /**
     * This method sends the signal to the presentation control that a new game for the active user and the idKakuro specified needs to be created.
     * @param idKakuro Identifies the kakuro to be played.
     * @return It returns the identifier of the new Game created.
     */
    public int createNewGame(int idKakuro) {
        return ctrlPresentation.createNewGame(idKakuro);
    }
}
