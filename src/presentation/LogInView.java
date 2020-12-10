package presentation;

import javax.swing.*;

public class LogInView extends JPanel{

    private JButton enterButton;
    private JTextField usernameTextField;
    private JPanel panel1;

    public LogInView(){

        add(enterButton);
        add(usernameTextField);
        setVisible(true);
    }

}
