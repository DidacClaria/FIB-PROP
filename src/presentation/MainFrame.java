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

    /**
     * Default MainFrame constructor
     * @param CP Is the reference of the Presentation Controller
     */
    public MainFrame(CtrlPresentation CP) {

        ctrlPresentation = CP;

        this.logInView = new LogInView(ctrlPresentation);
        this.createKakuroView = new CreateKakuroView(ctrlPresentation);
        this.selectGameView = new SelectGameView(ctrlPresentation);
        this.playGameView = new PlayGameView(ctrlPresentation);
        this.rankingView = new RankingView(ctrlPresentation);

        mainFrameInit();

    }

    public void iniUserMenu () {
        this.userMenuView = new UserMenuView(ctrlPresentation);
    }


    public void iniGame (int id_kakuro) {this.startedGameView = new StartedGameView(ctrlPresentation, id_kakuro);}

    /**
     * This method initialize the properties of the frame created
     */
    public void mainFrameInit(){
        setTitle("KAKURO");
        setMinimumSize(new Dimension(1200,700));
        setPreferredSize(getMinimumSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(logInView.getLogInPanel());
    }

    /**
     * This method makes the main frame visible
     */
    public void makeVisible() {
        pack();
        setVisible(true);
    }

    /**
     * This method sets the userMenuView as visible
     */
    public void makeUserMenuViewVisible() {
        userMenuView.setVisible(true);
        setContentPane(userMenuView.getUserMenuPanel());
    }

    /**
     * This method sets the logInView as visible
     */
    public void makeLogInViewVisible() {
        logInView.setVisible(true);
        setContentPane(logInView.getLogInPanel());
    }

    /**
     * This method sets the createKakuroView as visible
     */
    public void makeCreateKakuroViewVisible() {
        createKakuroView.setVisible(true);
        setContentPane(createKakuroView.getCreateKakuroPanel());
    }

    /**
     * This method sets the playGameView for an specific idGame to visible
     * @param idGame Indicates the identifier of the game to play
     */
    public void makePlayGameViewVisible(int idGame){
        playGameView.setVisible(true, idGame);
        setContentPane(playGameView.getPlayGamePanel());
    }

    /**
     * This method sets the SelectGameView as visible
     */
    public void makeSelectGameViewVisible(){
        selectGameView.setVisible(true);
        setContentPane(selectGameView.getSelectGamePanel());
    }

    /**
     * This method sets either the globalRankingView or the personalStatsView as visible
     * @param globalRank Indicates if the panel to show is the globalRankingView
     */
    public void makeRankingViewVisible(boolean globalRank){
        rankingView.setVisible(true);
        if (globalRank) {
            rankingView.setTitle("GLOBAL RANKING");
            rankingView.createRGText();;
        }
        else {
            rankingView.setTitle("MY PERSONAL STATS");
            rankingView.createPSText();
        }
        setContentPane(rankingView.getRankingPanel());
    }

    /**
     * This method sets the startedGamesView visible.
     */
    public void makeStartedGameViewVisible() {
        startedGameView.setVisible(true);
        setContentPane(startedGameView.getStartedGamePanel());
    }

}