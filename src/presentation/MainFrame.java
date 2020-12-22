package presentation;

import domain.Main;

import javax.swing.*;
import java.awt.*;

/**
 * This class it contains the mainFrame where all the panels will be painted and it also communicates with the presentation controller.
 */
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
     * Default MainFrame constructor.
     * @param CP Is the reference of the Presentation Controller.
     */
    public MainFrame(CtrlPresentation CP) {

        ctrlPresentation = CP;

        this.logInView = new LogInView(ctrlPresentation);
        this.createKakuroView = new CreateKakuroView(ctrlPresentation);
        this.playGameView = new PlayGameView(ctrlPresentation);

        mainFrameInit();

    }

    /**
     * This method initializes a new instance of the UserMenuView class.
     */
    public void iniUserMenu () {
        this.userMenuView = new UserMenuView(ctrlPresentation);
    }

    /**
     * This method initializes a new instance of the StartedGameView class.
     * @param idKakuro It identifies the kakuro of the games.
     */
    public void iniGame (int idKakuro) {this.startedGameView = new StartedGameView(ctrlPresentation, idKakuro);}

    /**
     * This method initializes a new instance of the RankingView class.
     * @param data This variable contains the info that the rankingView will load.
     */
    public void iniRanking (String [][] data) {this.rankingView = new RankingView(ctrlPresentation, data);}

    /**
     * This method initializes a new instance of the SelectGameView class.
     * @param gamesId This variable contains all the identifiers of kakuros of the global repository.
     */
    public void iniSelectGameList (int [] gamesId) {this.selectGameView = new SelectGameView(ctrlPresentation, gamesId);}

    /**
     * This method initialize the properties of the frame created.
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
     * This method makes the main frame visible.
     */
    public void makeVisible() {
        pack();
        setVisible(true);
    }

    /**
     * This method sets the userMenuView as visible.
     */
    public void makeUserMenuViewVisible() {
        userMenuView.setVisible(true);
        setContentPane(userMenuView.getUserMenuPanel());
    }

    /**
     * This method sets the logInView as visible.
     */
    public void makeLogInViewVisible() {
        logInView.setVisible(true);
        setContentPane(logInView.getLogInPanel());
    }

    /**
     * This method sets the createKakuroView as visible.
     */
    public void makeCreateKakuroViewVisible() {
        createKakuroView.setVisible(true);
        setContentPane(createKakuroView.getCreateKakuroPanel());
    }

    /**
     * This method sets the playGameView for an specific idGame to visible.
     * @param idGame Indicates the identifier of the game to play.
     */
    public void makePlayGameViewVisible(String game){
        playGameView.setVisible(true, game);
        setContentPane(playGameView.getPlayGamePanel());
    }

    /**
     * This method sets the SelectGameView as visible.
     */
    public void makeSelectGameViewVisible(){
        selectGameView.setVisible(true);
        setContentPane(selectGameView.getSelectGamePanel());
    }

    /**
     * This method sets either the globalRankingView or the personalStatsView as visible.
     * @param globalRank Indicates if the panel to show is the globalRankingView.
     */
    public void makeRankingViewVisible(boolean globalRank){
        rankingView.setVisible(true);
        if (globalRank) rankingView.setTitle("GLOBAL RANKING");
        else rankingView.setTitle("MY PERSONAL STATS");
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