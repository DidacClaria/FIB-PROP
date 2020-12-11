package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInView {

    private final CtrlPresentation ctrlPresentation;
    private JButton enterButton;
    private JTextField usernameTextField;
    private JPanel logInPanel;

    public LogInView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ctrlPresentation.logInUser(usernameTextField.getText());
                logInPanel.setVisible(false);
                ctrlPresentation.makeUserMenuViewVisible();
            }
        });
    }


    public JPanel getLogInPanel() {
        return logInPanel;
    }

    public void setVisible(boolean b) {
        logInPanel.setVisible(b);
    }
}
