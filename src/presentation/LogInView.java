package presentation;

import javax.swing.*;
import java.awt.event.*;

/**
 * This class represents the LogInView and with all the components added into the logInPanel it is represented. It communicates with the Presentation Controller.
 */
public class LogInView {

    private final CtrlPresentation ctrlPresentation;
    private JButton enterButton;
    private JTextField usernameTextField;
    private JPanel logInPanel;
    private Logo kakuroLogo;
    private JPanel kakuroContainer;

    /**
     * Default LogInView creator.
     * @param ctrlPresentation Reference of the presentation controller.
     */
    public LogInView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    /**
     * This method initialize all the different buttons and their specific behaviour.
     */
    private void initComponents() {
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkUsername();
            }
        });
        usernameTextField.addKeyListener(new KeyListener() {
            /**
             * Invoked when a key has been typed.
             * See the class description for {@link KeyEvent} for a definition of
             * a key typed event.
             *
             * @param e the event to be processed
             */
            @Override
            public void keyTyped(KeyEvent e) {

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
                if (e.getKeyChar()==KeyEvent.VK_ENTER){
                    checkUsername();
                }
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
        });
    }

    /**
     * This method asks to the presentation controller if the username added is valid and if it is, the user is logged into the system.
     */
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

    /**
     * Getter method of the logInPanel.
     * @return It returns the instance of the logInPanel.
     */
    public JPanel getLogInPanel() {
        return logInPanel;
    }

    /**
     * This method sets the panel of the view as visible or not depending on the bool of the parameter.
     * @param b Indicates whether the view must show or not.
     */
    public void setVisible(boolean b) {
        logInPanel.setVisible(b);
    }

    /**
     * This method initialize the custom components of the view.
     */
    private void createUIComponents() {
        kakuroLogo= new Logo(250,250);
    }
}
