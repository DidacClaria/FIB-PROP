package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class KakuroCell extends JFormattedTextField implements ActionListener{

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}