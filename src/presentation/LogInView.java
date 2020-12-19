package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
        });
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
