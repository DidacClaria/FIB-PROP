package presentation;

import domain.CtrlDomain;
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

    /**
     * This will be the frame where all the different panels will be painted.
     */
    private final MainFrame mainFrame;

    //CONSTRUCTOR
    /**
     * Default empty Presentation Controller constructor.
     */
    public CtrlPresentation() {
        this.ctrlDomain = new CtrlDomain (this);
        this.mainFrame = new MainFrame(this);
    }

    /**
     * This method sets the mainFrame as visible
     */
    public void initPresentation() {
        mainFrame.makeVisible();
    }

    public void iniUserMenu () {
        mainFrame.iniUserMenu();
    }

    public void iniGame (int idKakuro) {
        mainFrame.iniGame(idKakuro);
    }

    public void logInUser(String username) {
        ctrlDomain.logInUser(username);
    }

    public String getActiveUser() {
        return ctrlDomain.getActiveUser();
    }

    public void deleteUser() {
        ctrlDomain.deleteUser();
    }

    public void deleteGame(int idKakuro, int idGame){
        ctrlDomain.deleteGame(idKakuro, idGame);
    }

    public String[][] generateKakuro(int width, int height, int difficulty, int numFilledCells) {
//        String[][] kakuro = ctrlDomain.generateKakuro(width,height);
        return null;
    }

    public int proposeKakuro(int rows, int cols, String[][] field) {
        return ctrlDomain.proposeKakuro(rows,cols,field);
    }

    public String validateKakuro(int numColumns, int numRows, String[][] field){
        if (numColumns< 3 && numRows<3) return "The size of the Kakuro is too small";
        else if (numColumns>10 && numRows>10) return "The size of the Kakuro is too big";
        boolean validField = false;
        for (String[] row:field){
            for (String cell: row){
                if (!(cell.equals("*") || cell.equals("?"))){
                    validField=false;
                    String[] parts = cell.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
                    try{
                        if (parts[0].equals("F") && parts[1].length()<3 && parts[2]==null) validField =true;
                        else if (parts[0].equals("C") && parts[1].length()<3 && parts[2]==null) validField =true;
                        else if (parts[0].equals("C") && parts[2].equals("F") && (parts[1].length()<3 || parts[3].length()<3)) validField=true;

                    } catch (ArrayIndexOutOfBoundsException ignored){
                        if (parts[0].equals("F") && parts[1].length()<3) validField = true;
                        else validField = parts[0].equals("C") && parts[1].length() <= 3;
                    }
                }
                else validField=true;
                if (!validField) break;
            }
        }
        if (!validField) return "The proposed field is not valid";
        else return "OK";
    }

    public String[] getKakurosGlobals() {
        return ctrlDomain.getKakurosGlobals();
    }

    public String[] getGames(String user, int idKakuro) {
        return ctrlDomain.getGames(user, idKakuro);
    }

    public String listGlobalRanking () {
        return ctrlDomain.listGlobalRanking();
    }

    public String listPersonalStats(){
        return ctrlDomain.listPersonalStats();
    }

    public Set<Integer> getListGames(String username){
        return null;
    }

    public String askHint(String[][] field){
        return null;
    }

    public boolean validateSolution(String[][] field){return false;}

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

    public void makePlayGameViewVisible(int idGame) { mainFrame.makePlayGameViewVisible(idGame); }

    public void makeRankingViewVisible(boolean globalRank, String username) { mainFrame.makeRankingViewVisible(globalRank); }

    /**
     * This method sets the select game view
     */
    public void makeSelectGameViewVisible() {
        mainFrame.makeSelectGameViewVisible();
    }

    /**
     * This method sets to the mainFrame the StartedGameView as visible
     */
    public void makeStartedGameViewVisible() { mainFrame.makeStartedGameViewVisible(); }

    /**
     * This method asks to domain the gameScenario of a specific idGame
     * @param idGame This parameter specifies which gameScenario is needed
     * @return It returns the information of the size and cell values in a formatted string
     */
    public String getGameScenario(int idGame) {
        return ctrlDomain.getGameScenario(idGame);
    }

    /**
     * This operation creates a new Game for a specific idKakuro
     * @param idKakuro Indicates the id of the kakuro to play
     * @return It returns the id of the new created game if exists -1 if not.
     */
    public int createNewGame(int idKakuro) {
        return ctrlDomain.createNewGame(idKakuro);
    }
}
