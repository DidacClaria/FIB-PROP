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
        ctrlKakuro.proposeKakuro(numRows,numColumns,field);
    }

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     */
    public void generateKakuro(int numRows, int numColumns){
        ctrlKakuro.generateKakuro(numRows, numColumns);
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
     * Function used to create new Users into the system
     * @param name indicates the name of the new user
     * @param password indicates the password of the user
     */
    public void createUser(String name, String password){
        ctrlUser.createUser(name);
    }

    /**
     * Function used to swap the current User with the new one indicated
     * @param name indicates the name of the user to load
     */
    public void loadUser(String name){
        ctrlUser.loadUser(name);
    }

    /**
     * Function used to simulate a game for a kakuro
     * @param idGame indicates the identifier of the kakuro to load and play
     */
    public void playKakuro(int idGame){
        ctrlGame.playKakuro(idGame);
    }

    /**
     * Function used to save the current state of a game.
     * @param idGame indicates the identifier of the kakuro to save
     */
    public void saveGame(int idGame){
        ctrlGame.saveGame(idGame);
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
