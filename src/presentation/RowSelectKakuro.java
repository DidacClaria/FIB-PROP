package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RowSelectKakuro extends JPanel {

    private int id_kakuro;

    private JLabel kakuro;
    private JButton new_game;
    private JButton see_game;
    private SelectGameView gameView;

    public RowSelectKakuro (SelectGameView gv, int id) {
        this.id_kakuro = id;

        kakuro = new JLabel();
        new_game = new JButton();
        see_game = new JButton();

        kakuro.setForeground(Color.black);
        new_game.setForeground(Color.black);
        see_game.setForeground(Color.black);

        kakuro.setText("KAKURO " + id);
        new_game.setText("NEW GAME");
        see_game.setText("SEE GAME");

        this.gameView = gv;

        create_Format();

        create_ActionListener();
    }

    private void create_Format () {
        JPanel auxL = new JPanel();
        JPanel auxR = new JPanel();
        auxR.add(new_game);
        auxR.add(see_game);
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

    private void create_ActionListener () {
        new_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameView.setVisible(false);
                gameView.setGameVisible();
            }
        });

        see_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameView.setVisible(false);
                gameView.setGamesStarted(id_kakuro);
            }
        });
    }

}
