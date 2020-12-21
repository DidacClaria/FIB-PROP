package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a custom component that extends the class JPanel and it is used in the SelectKakuroView to represent one entry of a Kakuro.
 */
public class RowSelectKakuro extends JPanel {

    private int idKakuro;

    private JLabel kakuro;
    private JButton newGame;
    private JButton seeGame;
    private SelectGameView gameView;

    /**
     * Default RowSelectKakuro creator.
     * @param gv Reference of the view SelectGameView.
     * @param id It indicates the identifier of the kakuro.
     */
    public RowSelectKakuro (SelectGameView gv, int id) {
        this.idKakuro = id;

        kakuro = new JLabel();
        newGame = new JButton();
        seeGame = new JButton();

        kakuro.setForeground(Color.black);
        newGame.setForeground(Color.black);
        seeGame.setForeground(Color.black);

        kakuro.setText("KAKURO " + id);
        newGame.setText("NEW GAME");
        seeGame.setText("SEE GAME");

        this.gameView = gv;

        createFormat();

        createActionListener();
    }

    /**
     * Initialization method that configures the format of the panel.
     */
    private void createFormat () {
        JPanel auxL = new JPanel();
        JPanel auxR = new JPanel();
        auxR.add(newGame);
        auxR.add(seeGame);
        auxR.setLayout(new GridLayout(2,0));
        auxR.setBorder(BorderFactory.createLineBorder(Color.black));

        auxL.setLayout(new GridBagLayout());
        auxL.add(kakuro);
        auxL.setBorder(BorderFactory.createLineBorder(Color.black));
        auxL.setBackground(Color.decode("#845EC2"));

        add(auxL);
        add(auxR);
        setLayout(new GridLayout(1,2));
    }

    /**
     * This method implements the behaviour of newGame and seeGames buttons.
     */
    private void createActionListener () {
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idGame = gameView.createNewGame(idKakuro);
                gameView.setVisible(false);
                gameView.setGameVisible(idGame);
            }
        });

        seeGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameView.setVisible(false);
                gameView.setGamesStarted(idKakuro);
            }
        });
    }

}
