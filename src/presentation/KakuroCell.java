package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class KakuroCell extends JFormattedTextField implements KeyListener {

    private JLabel numColLabel;
    private JLabel numRowLabel;

    public KakuroCell(int numCol, int numRow) {
        if (numCol==-1) numColLabel = new JLabel("");
        else numColLabel = new JLabel(""+numCol);
        numColLabel.setFont (numColLabel.getFont().deriveFont(12f));
        numColLabel.setHorizontalAlignment(JLabel.CENTER);
        numColLabel.setForeground(Color.white);

        if (numRow==-1) numRowLabel = new JLabel("");
        else numRowLabel = new JLabel(""+numRow);
        numColLabel.setFont (numColLabel.getFont().deriveFont(12f));
        numRowLabel.setHorizontalAlignment(JLabel.CENTER);
        numRowLabel.setForeground(Color.white);

        setBackground(Color.black);
        setLayout(new BorderLayout());
        add(numColLabel, BorderLayout.SOUTH);
        add(numRowLabel, BorderLayout.EAST);
        setEnabled(false);

    }

    public KakuroCell(int value, boolean enabled){
        setFont (getFont().deriveFont(12f));
        setHorizontalAlignment(JLabel.CENTER);
        setForeground(Color.black);
        setBackground(Color.white);
        if (value == -1) setText("");
        else setText(String.valueOf(value));
        setEnabled(enabled);
        if (enabled) this.addKeyListener(this);
    }


    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)){
            e.consume();
        }
        else {
            try {
                if (getText().length() == 1 || c=='0') e.consume();
            }
            catch (Exception ignored) {
            }
        }
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}