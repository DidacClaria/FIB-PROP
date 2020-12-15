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
     */
    public void proposeKakuro(int numRows, int numColumns, String[][] field){
        if (ctrlKakuro.proposeKakuro(numRows,numColumns,field))
            ctrlPersistence.new_kakuro(ctrlKakuro.list_id_kakuro(), ctrlKakuro.listKakuro());
    }

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     */
    public void generateKakuro(int numRows, int numColumns){
        ctrlKakuro.generateKakuro(numRows, numColumns);
        ctrlPersistence.new_kakuro(ctrlKakuro.list_id_kakuro(), ctrlKakuro.listKakuro());
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] listKakuro () {
        return ctrlKakuro.listKakuro();
    }

    //WIP USE CASES

    /**
     * Function used to log in, if the user is not existed, the system will create by itself a new user with name introduced
     * @param name indicates the name of the new user
     */
    public void logInUser(String name){
        if (ctrlPersistence.create_user(name)) ctrlUser.createUser(name);
        else ctrlUser.setActiveUser (name);
    }

    /**
     *
     * @param user It indicates the user who is playing the game
     * @param id_kakuro It indicates the game scenario
     */
    public void playKakuro (String user, int id_kakuro) {
        if (ctrlPersistence.new_game(user, id_kakuro)) ctrlGame.startKakuro(user, id_kakuro);
    }

    /**
     * Function used to save the current state of a game.
     * @param idGame indicates the identifier of the kakuro to save
     */
    public void saveGame(String user, int idKakuro, int idGame, int time, int hints, String [][] new_state){
        ctrlPersistence.save_game(user, idKakuro, idGame, time, hints, new_state);
    }

    public void validate_game (String user, int id_kakuro, int id_game, int time, int hints, String [][] kakuro) {
        if (ctrlPersistence.validate_correctness_game(user, id_kakuro, id_game, kakuro)) {
            int scores = (72000 - time);
            if (scores - (7200 * hints) > 0) scores -= (7200 * hints);
            else scores = 0;
            ctrlPersistence.update_stats(user, id_kakuro, time, hints, scores, true);
            ctrlPersistence.update_stats(user, id_kakuro, time, hints, scores, false);
        }
    }

    public void remove_user (String user) {
        ctrlPersistence.eliminate_user(user);
    }

    public void remove_kakuros (String user, int id_kakuro) {
        ctrlPersistence.eliminate_kakuro(user, id_kakuro);
    }

    /**
     * Consultant function of the ranking of punctuations that all the different users made in their games.
     */
    public void listRanking(){
        ctrlGame.listRanking();
    }

    /**
     * Consultant function of the personal ranking of punctuations for one user from all his games.
     */
    public void listPersonalStats(){
        ctrlGame.listPersonalStats();
    }

}
