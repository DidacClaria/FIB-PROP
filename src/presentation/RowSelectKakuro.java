package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RowSelectKakuro extends JPanel {

    private int idKakuro;

    private JLabel kakuro;
    private JButton newGame;
    private JButton seeGame;
    private SelectGameView gameView;

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
        auxL.setBackground(Color.CYAN);

        add(auxL);
        add(auxR);
        setLayout(new GridLayout(1,2));
    }

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
