package presentation;

import javax.swing.*;
import java.awt.*;

public class RowSelectKakuro extends JPanel {

    JLabel kakuro;
    JButton new_game;
    JButton see_game;

    public RowSelectKakuro (int id) {
        kakuro = new JLabel();
        new_game = new JButton();
        see_game = new JButton();

        kakuro.setForeground(Color.black);
        new_game.setForeground(Color.black);
        see_game.setForeground(Color.black);

        kakuro.setText("KAKURO " + id);
        new_game.setText("NEW GAME");
        see_game.setText("SEE GAME");

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
}
