package presentation;

import javax.swing.*;
import java.awt.event.*;

public class LogInView {

    private final CtrlPresentation ctrlPresentation;
    private JButton enterButton;
    private JTextField usernameTextField;
    private JPanel logInPanel;
    private Logo kakuroLogo;
    private JPanel kakuroContainer;

    public LogInView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkUsername();
            }
        });
        usernameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar()==KeyEvent.VK_ENTER){
                    checkUsername();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void checkUsername() {
        if (usernameTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(logInPanel,"Wrong Format of User!","Alert",JOptionPane.WARNING_MESSAGE);
        }
        else {
            ctrlPresentation.logInUser(usernameTextField.getText());
            ctrlPresentation.iniUserMenu();
            usernameTextField.setText("");
            logInPanel.setVisible(false);
            ctrlPresentation.makeUserMenuViewVisible();
        }
    }

    public JPanel getLogInPanel() {
        return logInPanel;
    }

    public void setVisible(boolean b) {
        logInPanel.setVisible(b);
    }

    private void createUIComponents() {
        kakuroLogo= new Logo(250,250);
    }
}
