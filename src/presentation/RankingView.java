package presentation;

import javax.swing.*;

public class RankingView {
    private final CtrlPresentation ctrlPresentation;
    private JPanel rankingPanel;

    public RankingView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initComponents();
    }

    private void initComponents() {
    }

    public void setVisible(boolean b) {
        rankingPanel.setVisible(b);
    }

    public JPanel getRankingPanel() {
        return rankingPanel;
    }
}
