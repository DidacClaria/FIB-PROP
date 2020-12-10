package presentation;

import domain.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CtrlPresentation ctrlPresentation;

    private JPanel MainFrame;
    private LogInView logInView;
    private UserMenuView userMenuView;
    private CreateKakuroView createKakuroView;
    private SelectGameView selectGameView;
    private PlayGameView playGameView;
    private RankingView rankingView;

    public MainFrame(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;

        this.logInView = new LogInView();
        this.userMenuView = new UserMenuView();
        this.createKakuroView = new CreateKakuroView();
        this.selectGameView = new SelectGameView();
        this.playGameView = new PlayGameView();
        this.rankingView = new RankingView();

        mainFrameInit();

    }

    public void mainFrameInit(){
        setTitle("KAKURO");
        setMinimumSize(new Dimension(900,700));
        setPreferredSize(getMinimumSize());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(logInView);
    }

    public void makeVisible() {
        pack();
        setVisible(true);
    }
}