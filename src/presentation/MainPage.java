package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage {
    private static JButton CREATEKAKUROButton;
    private static JButton PLAYGAMEButton;
    private static JButton PERSONALSTATSButton;
    private static JButton GLOBALRANKINGButton;
    private static JButton LOGOUTButton;
    private static JPanel mainPanel;

    public MainPage(){
        CREATEKAKUROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"KAKURO CREATED");
            }
        });
        PLAYGAMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"GAME PLAYED");
            }
        });
        PERSONALSTATSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"1ST PLACE");
            }
        });
        GLOBALRANKINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"1ST PLACE");
            }
        });
        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"LOGGED OUT SUCCESSFULLY");
            }
        });
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Main Page");
        mainFrame.setContentPane(new MainPage().mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        CREATEKAKUROButton.setUI(new MyButton());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
