package presentation;

import domain.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel MainFrame;
    private LogInView logInView;
    private UserMenuView userMenuView;
    private CreateKakuroView createKakuroView;
    private SelectGameView selectGameView;
    private PlayGameView playGameView;
    private RankingView rankingView;

    public MainFrame(CtrlPresentation ctrlPresentation) {

        this.logInView = new LogInView(ctrlPresentation);
        this.userMenuView = new UserMenuView(ctrlPresentation);
        this.createKakuroView = new CreateKakuroView(ctrlPresentation);
        this.selectGameView = new SelectGameView(ctrlPresentation);
        this.playGameView = new PlayGameView(ctrlPresentation);
        this.rankingView = new RankingView(ctrlPresentation);

        mainFrameInit();

    }

    public void mainFrameInit(){
        setTitle("KAKURO");
        setMinimumSize(new Dimension(900,700));
        setPreferredSize(getMinimumSize());
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(logInView.getLogInPanel());
    }

    public void makeVisible() {
        pack();
        setVisible(true);
    }

    public void makeUserMenuViewVisible() {
        userMenuView.setVisible(true);
        setContentPane(userMenuView.getUserMenuPanel());
    }

    public void makeLogInViewVisible() {
        logInView.setVisible(true);
        setContentPane(logInView.getLogInPanel());
    }

    public void makeCreateKakuroViewVisible() {
        createKakuroView.setVisible(true);
        setContentPane(createKakuroView.getCreateKakuroPanel());
    }

    public void makePlayGameViewVisible(){
        playGameView.setVisible(true);
        setContentPane(playGameView.getPlayGamePanel());
    }

    public void makeSelectGameViewVisible(){
        selectGameView.setVisible(true);
        setContentPane(selectGameView.getSelectGamePanel());
    }

    public void makeRankingViewVisible(String rankingType){
        rankingView.setVisible(true);
        JPanel rankingAux = rankingView.getRankingPanel();
        if (rankingType == "PERSONAL STATS") {
//            rankingAux.setTitle(rankingType);
        }
        else {
//            rankingAux.setTitle(rankingAux);
        }
        setContentPane(rankingAux);
    }
}