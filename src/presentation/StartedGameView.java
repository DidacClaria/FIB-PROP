package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents the StartedGameView and with all the components added into the startedGamePanel it is represented. It also communicates with the Presentation Controller.
 */
public class StartedGameView {

    private int idKakuro;

    private final CtrlPresentation ctrlPresentation;

    private JPanel startedGamePanel;
    private JPanel headerContainer;
    private JLabel titleLabel;
    private JButton GOBACKButton;
    private JScrollPane mygames;

    /**
     * Default StartedGameView constructor.
     * @param ctrlPresentation Reference of the presentation controller.
     */
    public StartedGameView(CtrlPresentation ctrlPresentation, int id) {
        this.idKakuro = id;
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    /**
     * This method initialize all the different buttons and their specific behaviour.
     */
    private void initComponents() {

        titleLabel.setText("MY GAMES OF KAKURO " + idKakuro);

        GOBACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ctrlPresentation.iniGame(idKakuro);
                ctrlPresentation.makeSelectGameViewVisible();
            }
        });
    }

    /**
     * This method sets the panel of the view as visible or not depending on the bool of the parameter.
     * @param b Indicates whether the view must show or not.
     */
    public void setVisible(boolean b) {
        startedGamePanel.setVisible(b);
    }

    /**
     * This method indicates to the presentation controller to set the specified game view visible.
     * @param idGame Indicates whether the view must show or not.
     */
    public void setGameVisible(int idGame) {ctrlPresentation.makePlayGameViewVisible(idGame);}

    /**
     * This method indicates to the presentation controller to set the SelectGameView visible.
     */
    public void setSelectGameVisible() {ctrlPresentation.makeSelectGameViewVisible();}

    /**
     * This method indicates to the presentation controller the game to erase.
     * @param idGame Identifier of the game to erase.
     */
    public void deleteGame (int idGame) {
        ctrlPresentation.deleteGame(idGame);
    }

    /**
     * Getter method of the startedGamePanel.
     * @return It returns the instance of the startedGamePanel.
     */
    public JPanel getStartedGamePanel() {
        return startedGamePanel;
    }

    /**
     * This method initialize the custom components of the view.
     */
    private void createUIComponents() {
        createBackImage();
        createListGames();
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
            System.out.println("The file does not exists");
        }
    }

    /**
     * This method configures the list of games available for the user.
     */
    private void createListGames () {

//        ArrayList<Integer> files = ctrlPresentation.getGames(idKakuro);
        ArrayList<Integer> files = new ArrayList<Integer>();
        if (files == null) mygames = new JScrollPane();
        else {
            for (int i = 0; i < 20; ++i) files.add(i);
            JPanel aux = new JPanel();

            for (int i = 0; i < files.size(); ++i) {
//                if (i % 2 != 0) {
                    int id = files.get(i);
                    JPanel aux2 = new RowSelectGame(this, id);
                    aux2.setBorder(BorderFactory.createLineBorder(Color.black));
                    aux.add(aux2);
//                }
            }
            aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
            mygames = new JScrollPane(aux);
        }
    }


}
