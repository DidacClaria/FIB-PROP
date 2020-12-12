package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGameView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel selectGamePanel;
    private JPanel headerContainer;
    private JPanel listGamesContainer;
    private JScrollBar scrollBar1;
    private JButton GOBACKButton;
    private JLabel titleLabel;

    public SelectGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
        GOBACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setVisible(boolean b) {
        selectGamePanel.setVisible(b);
    }

    public JPanel getSelectGamePanel() {
        return selectGamePanel;
    }
}
