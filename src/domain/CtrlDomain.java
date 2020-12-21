package domain;

import persistence.CtrlPersistence;
import presentation.CtrlPresentation;

/**
 * Domain Controller Class. It is in charge of communicating with the layers of Presentation and Persistence, through their correspondent Controllers (CtrlPresentation and CtrlPersistence respectively) and to delegate use cases to their responsible.
 */
public class CtrlDomain {
    //ATTRIBUTES

    /**
     * Persistence Controller.
     */
    private final CtrlPersistence ctrlPersistence;

    /**
     * Presentation Controller.
     */
    private final CtrlPresentation ctrlPresentation;

    /**
     * User Controller. Used to manage and collect all the User use cases.
     */
    private final CtrlUser ctrlUser;

    /**
     * Game Controller. Used to manage and collect all the Game use cases.
     */
    private final CtrlGame ctrlGame;

    /**
     * Kakuro Controller. Used to manage and collect all the Kakuro use cases.
     */
    private final CtrlKakuro ctrlKakuro;

    //CONSTRUCTOR

    /**
     * Default Domain Controller constructor.
     */
    public CtrlDomain(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        ctrlPersistence = new CtrlPersistence(this);
        ctrlGame = new CtrlGame(this);
        ctrlKakuro = new CtrlKakuro(this);
        ctrlUser = new CtrlUser(this);
    }

    //CLASS METHODS

    /**
     * If the proposed Kakuro by the User at the Presentation Layer is valid, it is saved at the Persistence Layer and it's added to the collection of Kakuros that a User has.
     * @param numRows indicates the number of rows that the field has
     * @param numColumns indicates the number of columns that the field has
     * @param field it has the value of each cell divided by ",".
     * @return It returns either the id of the new proposed Kakuro or -1 if it is has no solution.
     */
    public int proposeKakuro(int numRows, int numColumns, String[][] field){
        int idKakuro = ctrlKakuro.proposeKakuro(numRows,numColumns,field);
        if (idKakuro!=-1) {
            ctrlPersistence.newKakuro(ctrlKakuro.listIdKakuro(), ctrlKakuro.listKakuro());
            return idKakuro;
        }
        return -1;
    }

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     */
    public void generateKakuro(int numRows, int numColumns){
        ctrlKakuro.generateKakuro(numRows, numColumns);
        ctrlPersistence.newKakuro(ctrlKakuro.listIdKakuro(), ctrlKakuro.listKakuro());
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] listKakuro () {
        return ctrlKakuro.listKakuro();
    }

    /**
     * Function used to log in, if the user is not existed, the system will create by itself a new user with name introduced
     * @param name indicates the name of the new user
     */
    public void logInUser(String name){
        if (ctrlPersistence.createUser(name)) ctrlUser.createUser(name);
        else ctrlUser.setActiveUser(name);
    }

    /**
     * This method is a getter of the active user in the system.
     * @return username of the active user.
     */
    public String getActiveUser() {
        return ctrlUser.getActiveUser();
    }

    /**
     * This method is a getter of all the kakuros in the system.
     * @return A list of the kakuros existing.
     */
    public String[] getKakurosGlobals() {
//        return ctrlPersistence.getKakurosGlobals();
//        AFEGIR CONSULTA A CTRLKAKURO ENLLOC DE PERSISTENCIA
        return null;
    }

    /**
     * This method is a getter of all the existing games of a specific Kakuro from the active user.
     * @param idKakuro Indicates the kakuro that is the gameScenario of all the games.
     * @return A list of game identifiers.
     */
    public String[] getGames(int idKakuro) {
        String user=getActiveUser();
        return ctrlPersistence.getGames(user, idKakuro);
    }

    /**
     * Function used to save the current state of a game.
     * @param idGame indicates the identifier of the kakuro to save
     */
    public void saveGame(String user, int idKakuro, int idGame, int time, int hints, String [][] newState){
        ctrlPersistence.saveGame(user, idKakuro, idGame, time, hints, newState);
    }

    /**
     * This method checks if a specific game solution is correct. If it is the score is calculated.
     * @param idKakuro Identifies the kakuro that is the gameScenario to check.
     * @param idGame Identifies the game that is being checked.
     * @param time Indicates the value of the time passed playing the game.
     * @param hints Indicates the number of hints asked while playing.
     * @param kakuro It contains the solution provided by the user.
     */
    public void validateGame (int idKakuro, int idGame, int time, int hints, String [][] kakuro) {
        // CANVIAR VALIDATE CORRECTNESS PERQUE ES FACI DESDE DOMINI AMB SOLUTION DE KAKURO
        String user = getActiveUser();
        if (ctrlPersistence.validateCorrectnessGame(user, idKakuro, idGame, kakuro)) {
            int scores = (72000 - time);
            if (scores - (7200 * hints) > 0) scores -= (7200 * hints);
            else scores = 0;
            ctrlPersistence.updateStats(user, idKakuro, time, hints, scores, true);
            ctrlPersistence.updateStats(user, idKakuro, time, hints, scores, false);
        }
    }

    /**
     * This method deletes the user from the system.
     */
    public void deleteUser () {
        // FALTA ESBORRAR DEL CONJUNT DE USERS QUE TE CTRL USER I ESBORRAR TOTS ELS GAMES QUE TINGUI A CTRL GAME
        String user = getActiveUser();
        ctrlPersistence.deleteUser(user);
    }

    /**
     * This method deletes a specified game from the active user.
     * @param idKakuro Indicates the gameScenario of the game to delete.
     * @param idGame Identifies the game to delete.
     */
    public void deleteGame (int idKakuro, int idGame) {
        //FALTA ESBORRAR DESDE CTRL GAMES
        String user = getActiveUser();
        ctrlPersistence.deleteGame(user, idKakuro, idGame);
    }

    /**
     * Consultant function of the ranking of punctuations that all the different users made in their games.
     */
    public String[][] listGlobalRanking(){
        //HAURIA DE CONSULTAR EL RANKING A CTRL GAME
//        return ctrlPersistence.listRankingOrStats(null, true);
        return null;
    }

    /**
     * Consultant function of the personal ranking of punctuations for one user from all his games.
     */
    public String[][] listPersonalStats(){
        String user = getActiveUser();
        //HAURIA DE CONSULTAR EL RANKING A CTRL GAME
//        return ctrlPersistence.listRankingOrStats(user, false);
        return null;
    }

    /**
     * This method is used to continue playing a started game.
     * @param idGame Identifies the game to resume.
     * @return It returns the field in a formatted String.
     */
    public String getGameScenario(int idGame) {
        return "";
    }

    /**
     * Method used to create a new Game in the persistence layer and in the domain layer.
     * @param idKakuro Indicates the id of the Kakuro gameScenario.
     * @return It returns either the id of the kakuro or -1 if it failed.
     */
    public int createNewGame(int idKakuro) {
        String username = getActiveUser();
        if(ctrlPersistence.newGame(username,idKakuro)) return ctrlGame.createNewGame(username,idKakuro);
        else return -1;
    }
}
