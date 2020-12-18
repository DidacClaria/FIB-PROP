package presentation;

import domain.CtrlDomain;
import domain.Game;
import domain.Main;
import java.io.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Presentation Controller Class.
 */
public class CtrlPresentation {
    //ATTRIBUTES
    /**
     * Domain Controller.
     */
    private final CtrlDomain ctrlDomain;

    private final MainFrame mainFrame;


    //CONSTRUCTOR
    /**
     * Default empty Presentation Controller constructor.
     */
    public CtrlPresentation() {
        this.ctrlDomain = new CtrlDomain (this);
        this.mainFrame = new MainFrame(this);
    }


    public void initPresentation() {
        mainFrame.makeVisible();
    }

    public void iniUserMenu () {
        mainFrame.iniUserMenu();
    }

    public void iniGame (int id_kakuro) {
        mainFrame.iniGame(id_kakuro);
    }

    public void logInUser(String username) {
        ctrlDomain.logInUser(username);
    }

    public String getActiveUser() {
        return ctrlDomain.getActiveUser();
    }

    public void eliminateUser(String username) {
        ctrlDomain.deleteUser(username);
    }

    public void eliminateGame(String username, int idKakuro, int idGame){
        ctrlDomain.deleteGame(username, idKakuro, idGame);
    }

    public String[][] generateKakuro(int width, int height, int difficulty, int numFilledCells) {
//        String[][] kakuro = ctrlDomain.generateKakuro(width,height);
        return null;
    }

    public String[][] validateKakuro(int width, int height, String[][] field){
        return null;
    }

    public String[] getKakurosGlobals() {
        return ctrlDomain.getKakurosGlobals();
    }

    public String[] getGames(String user, int id_kakuro) {
        return ctrlDomain.getGames(user, id_kakuro);
    }

    public String listGlobalRanking () {
        return ctrlDomain.listGlobalRanking();
    }

    public String listPersonalStats(String user){
        return ctrlDomain.listPersonalStats(user);
    }

    public Set<Integer> getListGames(String username){
        return null;
    }

    public String askHint(String[][] field){
        return null;
    }

    public String addValueToCell(int idGame, int posX, int posY, int value){
        return null;
    }

    public void saveGame(){

    }

    public void exitGame(){

    }

    public void makeUserMenuViewVisible() {
        mainFrame.makeUserMenuViewVisible();
    }

    public void makeLogInViewVisible() {
        mainFrame.makeLogInViewVisible();
    }

    public void makeCreateKakuroViewVisible() {
        mainFrame.makeCreateKakuroViewVisible();
    }

    public void makePlayGameViewVisible() { mainFrame.makePlayGameViewVisible(); }

    public void makeRankingViewVisible(boolean globalRank, String username) { mainFrame.makeRankingViewVisible(globalRank); }

    public void makeSelectGameViewVisible() {
        mainFrame.makeSelectGameViewVisible();
    }

    public void makeStartedGameViewVisible() {
        mainFrame.makeStartedGameViewVisible();
    }

}
