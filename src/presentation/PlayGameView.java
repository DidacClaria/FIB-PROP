package presentation;

import org.w3c.dom.html.HTMLBodyElement;

import javax.swing.*;

public class PlayGameView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel playGamePanel;
    private JPanel titleContainer;
    private JPanel kakuroContainer;
    private JButton ASKHINTButton;
    private JButton SAVEANDEXITButton;
    private JButton EXITButton;
    private JLabel timerLabel;
    private JLabel titleLabel;
    private JLabel errorMessage;
    private JPanel buttonsContainer;
    private JPanel footerContainer;

    public PlayGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
    }

    public JPanel getPlayGamePanel() {
        return playGamePanel;
    }

    public void setVisible(Boolean b){
        playGamePanel.setVisible(b);
    }
}
