package presentation;

import domain.CtrlDomain;
import java.util.Set;

/**
 * Presentation Controller Class. It communicates with the mainFrame where the different views will be loaded, and with the Domain Controller.
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
     * This method sets the mainFrame as visible.
     */
    public void initPresentation() {
        mainFrame.makeVisible();
    }

    /**
     * This method instantiates a new UserMenuView.
     */
    public void iniUserMenu () {
        mainFrame.iniUserMenu();
    }

    /**
     * This method instantiates a new StartedGameView for an specific idKakuro.
     * @param idKakuro Indicates the identifier of the kakuro referenced.
     */
    public void iniGame (int idKakuro) {
        mainFrame.iniGame(idKakuro);
    }

    /**
     * This method instantiates a new StartedGameView for an specific idKakuro.
     * @param data This variable contains the info that the rankingView will load.
     */
    public void iniRanking (String[][] data) {
        mainFrame.iniRanking(data);
    }

    /**
     * This method sends the information collected in the LogInView to be processed by the domain
     * @param username This attribute indicates the username the new user logged in.
     */
    public void logInUser(String username) {
        ctrlDomain.logInUser(username);
    }

    /**
     * This method sends a request for the username of the Active User in the system.
     * @return The string contains the username of the Active User.
     */
    public String getActiveUser() {
        return ctrlDomain.getActiveUser();
    }

    /**
     * This method sends a request to the domain controller in order to delete the Active User.
     */
    public void deleteUser() {
        ctrlDomain.deleteUser();
    }

    /**
     * This method sends a request to domain that deletes a game of a Kakuro from the Active User.
     * @param idKakuro Indicates the Kakuro that has a game.
     * @param idGame Indicate the exact game to delete.
     */
    public void deleteGame(int idKakuro, int idGame){
        ctrlDomain.deleteGame(idKakuro, idGame);
    }

    /**
     * This method sends the information collected from the CreateKakuroView in order that the domain can generate a new Kakuro.
     * @param rows Indicates the rows of the new Kakuro to create.
     * @param cols Indicates the cols of the new Kakuro to create.
     * @param difficulty Indicates the difficulty of the new Kakuro to create.
     * @param numFilledCells Indicates the number of filled cells that the new Kakuro to create will contain.
     * @return It returns the id of the new Kakuro created or -1 if it could'nt be created.
     */
    public int generateKakuro(int rows, int cols, int difficulty, int numFilledCells) {
//        return ctrlDomain.generateKakuro(rows,cols);
        return -1;
    }

    /**
     * This method sends the information readed by file to the domain in order to check if the proposed kakuro it has a solution.
     * @param rows Indicates the rows of the new Kakuro to create.
     * @param cols Indicates the cols of the new Kakuro to create.
     * @param field It contains the information of each individual cell from a Kakuro.
     * @return It returns the id of the new Kakuro created or -1 if it could'nt be created.
     */
    public int proposeKakuro(int rows, int cols, String[][] field) {
        return ctrlDomain.proposeKakuro(rows,cols,field);
    }

    /**
     * This method validates if a kakuro has a valid format either way it indicates the source problem through the return value.
     * @param numColumns Indicates the columns of the new Kakuro to create.
     * @param numRows Indicates the rows of the new Kakuro to create.
     * @param field It contains the information of each individual cell from a Kakuro.
     * @return It returns the String "OK" if it is a valid Kakuro, and if it isn't it indicates the error message.
     */
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

    /**
     * This method asks for all the kakuros in the system.
     * @return It returns a list with the information of all the existing kakuros.
     */
    public String[] getKakurosGlobals() {
        return ctrlDomain.getKakurosGlobals();
    }

    /**
     * This method asks for all the games of the Active User for a specific Kakuro.
     * @param idKakuro It has the identifier of the kakuro to search games of.
     * @return It returns a list with the information of all the games for a specific Kakuro.
     */
    public String[] getGames(int idKakuro) {
        return ctrlDomain.getGames(idKakuro);
    }

    /**
     * This method asks for the information of all the completed Games in the system with their score.
     * @return An ordered list depending on the score of a completed Game from all the Games in the system.
     */
    public String[][] listGlobalRanking () {
        return ctrlDomain.listGlobalRanking();
    }

    /**
     * This method asks for the information of all the completed Games from the Active User.
     * @return An ordered list depending on the score of a completed Game from all the games of the Active User.
     */
    public String[][] listPersonalStats(){
        return ctrlDomain.listPersonalStats();
    }

    /**
     * This method asks to the Domain Controller for a hint in the game scenario with its current state.
     * @param field Current state of the game scenario.
     * @return It will contain in a formatted String the information of "posX:posY:value" if there are no more hints available it will return an error message.
     */
    public String askHint(String[][] field){
        return null;
    }

    /**
     * This method will send the information of the current state of the game scenario being played in order to check if the solution is correct.
     * @param field It will contain the current state of the game scenario.
     * @return If the field is correct the boolean will be true either way will be false.
     */
    public boolean validateSolution(String[][] field){return false;}

    /**
     * This method will send a signal to the domain controller in order to update the state of the current game scenario for the current one.
     * @param field It will contain the current state of the game scenario.
     */
    public void saveGame(String[][] field){

    }

    /**
     * This method will send a signal to the domain controller in order to decline the new changes to the state of the game scenario played.
     */
    public void exitGame(){

    }

    /**
     * This method will make the UserMenuView visible.
     */
    public void makeUserMenuViewVisible() {
        mainFrame.makeUserMenuViewVisible();
    }

    /**
     * This method will make the LogInView visible.
     */
    public void makeLogInViewVisible() {
        mainFrame.makeLogInViewVisible();
    }

    /**
     * This method will make the CreateKakuroView visible
     */
    public void makeCreateKakuroViewVisible() {
        mainFrame.makeCreateKakuroViewVisible();
    }

    /**
     * This method will make the PlayGameView visible.
     * @param idGame It indicates which game has to be loaded.
     */
    public void makePlayGameViewVisible(int idGame) { mainFrame.makePlayGameViewVisible(idGame); }

    /**
     * This method will make the RankingView visible.
     */
    public void makeRankingViewVisible(boolean globalRank) { mainFrame.makeRankingViewVisible(globalRank); }

    /**
     * This method will make the SelectGameView visible.
     */
    public void makeSelectGameViewVisible() {
        mainFrame.makeSelectGameViewVisible();
    }

    /**
     * This method will make the StartedGameView visible.
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
     * This method creates a new Game for a specific idKakuro
     * @param idKakuro Indicates the id of the kakuro to play
     * @return It returns the id of the new created game if exists -1 if not.
     */
    public int createNewGame(int idKakuro) {
        return ctrlDomain.createNewGame(idKakuro);
    }

    /**
     * This method will send the information to the domain controller about the game that will be played.
     * @param idKakuro Indicates the gameScenario of the Game.
     * @param idGame Identifies the Game that will be resumed.
     * @return It returns the stats of the game and the current state of the field in a formatted string. All different attributes are divided by ":".
     */
    public String playStartedGame(int idKakuro, int idGame){
//        return ctrlDomain.playStartedGame();
        return "";
    }
}
