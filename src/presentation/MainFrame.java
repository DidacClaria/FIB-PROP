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
    private StartedGameView startedGameView;

    public MainFrame(CtrlPresentation CP) {

        ctrlPresentation = CP;

        this.logInView = new LogInView(ctrlPresentation);
        this.createKakuroView = new CreateKakuroView(ctrlPresentation);
        this.selectGameView = new SelectGameView(ctrlPresentation);
        this.playGameView = new PlayGameView(ctrlPresentation);

        mainFrameInit();

    }

    public void iniUserMenu () {
        this.userMenuView = new UserMenuView(ctrlPresentation);
    }

    public void iniGame (int idKakuro) {this.startedGameView = new StartedGameView(ctrlPresentation, idKakuro);}

    public void iniranKing (String [][] data) {this.rankingView = new RankingView(ctrlPresentation, data);}

    public void mainFrameInit(){
        setTitle("KAKURO");
        setMinimumSize(new Dimension(1200,700));
        setPreferredSize(getMinimumSize());
        setLocationRelativeTo(null);
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

    public void makeRankingViewVisible(boolean globalRank){
        rankingView.setVisible(true);
        if (globalRank) rankingView.setTitle("GLOBAL RANKING");
        else rankingView.setTitle("MY PERSONAL STATS");
        setContentPane(rankingView.getRankingPanel());
    }

    public void makeStartedGameViewVisible() {
        startedGameView.setVisible(true);
        setContentPane(startedGameView.getStartedGamePanel());
    }

}