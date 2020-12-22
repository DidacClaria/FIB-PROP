package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a custom component that extends the class JPanel and it is used in the SelectGameView to represent one entry of Game.
 */
public class RowSelectGame extends JPanel {

    private int idGame;

    private JLabel kakuro;
    private JButton resume;
    private JButton delete;
    private StartedGameView gameView;

    /**
     * Default creator of a RowSelectGame.
     * @param gv Reference of the parent view.
     * @param id Identifier of the game that is displayed.
     */
    public RowSelectGame (StartedGameView gv, int id) {
        this.idGame = id;

        kakuro = new JLabel();
        resume = new JButton();
        delete = new JButton();

        kakuro.setForeground(Color.black);

        resume.setBackground(Color.decode("#303030"));
        resume.setForeground(Color.white);

        delete.setBackground(Color.decode("#B10000"));
        delete.setForeground(Color.white);

        kakuro.setText("GAME " + id);
        resume.setText("RESUME");
        delete.setText("DELETE GAME");

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
        auxR.add(resume);
        auxR.add(delete);
        auxR.setLayout(new GridLayout(2,0));
        auxR.setBorder(BorderFactory.createLineBorder(Color.black));

        auxL.setLayout(new GridBagLayout());
        auxL.add(kakuro);
        auxL.setBorder(BorderFactory.createLineBorder(Color.black));
        auxL.setBackground(Color.decode("#C493FF"));

        add(auxL);
        add(auxR);
        setLayout(new GridLayout(1,2));
    }

    /**
     * This method implements the behaviour of resume and delete buttons.
     */
    private void createActionListener () {
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameView.setVisible(false);
                gameView.setGameVisible(gameView.resumeGame(idGame));
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(gameView.getStartedGamePanel(),"Are you sure?");
                if (option == JOptionPane.YES_OPTION) {
                    gameView.deleteGame(idGame);
                    gameView.setVisible(false);
                    gameView.setSelectGameVisible();
                }
            }
        });
    }

}
