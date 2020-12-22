package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * This class is a CustomComponent that extends a JFormattedField and it is used to represent a Cell from a KakuroGrid in the GUI.
 */
public class KakuroCell extends JFormattedTextField implements KeyListener {

    private JLabel numColLabel;
    private JLabel numRowLabel;
    private KakuroGrid parent;
    private int posX, posY, value;

    /**
     * Constructor of a BlackCell
     * @param numCol Indicates the value of the sum in the columns if it has, either way it will contain a -1.
     * @param numRow Indicates the value of the sum in the rows if it has, either way it will contain a -1.
     */
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

    /**
     * Constructor of a WhiteCell
     * @param value Indicates the value of the cell if it has -1 either way.
     * @param posX Indicates the X position of the cell in the grid.
     * @param posY Indicates the Y position of the cell in the grid.
     * @param enabled Indicates if the cell can be editable or not.
     * @param parent It is the reference of the kakuro Grid that contains it.
     */
    public KakuroCell(int value, int posX, int posY, boolean enabled, KakuroGrid parent){
        this.parent = parent;
        this.posX=posX;
        this.posY=posY;
        this.value = value;
        setFont (getFont().deriveFont(12f));
        setHorizontalAlignment(JLabel.CENTER);
        setForeground(Color.black);
        setBackground(Color.white);
        if (value>=1 && value<=9) setText(String.valueOf(value));
        else setText("");
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
        if (!(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)){
            e.consume();
        }
        else {
            try {
                if (getText().length() == 1 || c=='0') e.consume();
                else {
                    if ((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)) {
                        parent.setValueField(posX,posY,0);
                    }
                    else {
                        parent.setValueField(posX,posY,c-'0');
                    }
                }
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