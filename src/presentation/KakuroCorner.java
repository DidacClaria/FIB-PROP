package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KakuroCorner extends JButton{

    private JLabel myButton1_Label_E;
    private JLabel myButton1_Label_S;

    public KakuroCorner() {
        myButton1_Label_E = new JLabel("3");
        myButton1_Label_E.setHorizontalAlignment(JLabel.CENTER);
        myButton1_Label_E.setForeground(Color.white);

        myButton1_Label_S = new JLabel("45");
        myButton1_Label_S.setHorizontalAlignment(JLabel.CENTER);
        myButton1_Label_S.setForeground(Color.white);

        setBackground(Color.black);
        setLayout(new BorderLayout());
        add(myButton1_Label_E, BorderLayout.EAST);
        add(myButton1_Label_S, BorderLayout.SOUTH);
        setEnabled(false);
    }

}