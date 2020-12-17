package presentation;

import domain.CtrlDomain;
import domain.Game;
import domain.Main;

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

    public void logInUser(String username) {
        ctrlDomain.logInUser(username);
    }

    public String getActiveUser() {
        return ctrlDomain.getActiveUser();
    }

    public void eliminateUser(String username) {
        ctrlDomain.deleteUser(username);
    }

    public String[][] generateKakuro(int width, int height, int difficulty, int numFilledCells) {
//        String[][] kakuro = ctrlDomain.generateKakuro(width,height);
        return null;
    }

    public String[][] validateKakuro(int width, int height, String[][] field){
        return null;
    }

    public Set<Integer> getListKakuros() {
        return null;
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

    public String[][] listGlobalRanking() {
        return null;
    }

    public String[][] listPersonalStats(){
        return null;
    }

    public Boolean deleteUser(String username){
        return null;
    }

    public Boolean deleteGame(String username, int idGame){
        return null;
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

    public void makeRankingViewVisible(String rankingType) { mainFrame.makeRankingViewVisible(rankingType); }

    public void makeSelectGameViewVisible() {
        mainFrame.makeSelectGameViewVisible();
    }

}
