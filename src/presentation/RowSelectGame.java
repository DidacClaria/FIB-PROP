package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RowSelectGame extends JPanel {

    private int id_game;

    private JLabel kakuro;
    private JButton resume;
    private JButton delete;
    private StartedGameView gameView;

    public RowSelectGame (StartedGameView gv, int id) {
        this.id_game = id;

        kakuro = new JLabel();
        resume = new JButton();
        delete = new JButton();

        kakuro.setForeground(Color.black);

        resume.setBackground(Color.blue);
        resume.setForeground(Color.black);

        delete.setBackground(Color.red);
        delete.setForeground(Color.black);

        kakuro.setText("GAME " + id);
        resume.setText("RESUME");
        delete.setText("DELETE GAME");

        this.gameView = gv;

        create_Format();

        create_ActionListener();
    }

    private void create_Format () {
        JPanel auxL = new JPanel();
        JPanel auxR = new JPanel();
        auxR.add(resume);
        auxR.add(delete);
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
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameView.setVisible(false);
                gameView.setGameVisible();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(gameView.getStartedGamePanel(),"Are you sure?");
                if (option == JOptionPane.YES_OPTION) {
                    gameView.eliminateGame(id_game);
                    gameView.setVisible(false);
                    gameView.setSelectGameVisible();
                }
            }
        });
    }

}
