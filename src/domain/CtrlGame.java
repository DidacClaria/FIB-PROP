package domain;

/**
 * Game controller class. Used to collect and manage the use cases of a Game.
 */
public class CtrlGame {
    //ATTRIBUTES
    /**
     * Domain controller. Used to communicate back the result of the class methods.
     */
    private CtrlDomain ctrlDomain;

    /**
     * Game class. Is the instance of the game currently running in the system.
     */
    private Game game;

    //CONSTRUCTOR

    /**
     * Default empty Game Controller constructor.
     */
    public CtrlGame() {
    }

    //CLASS METHODS

    /**
     * It returns the global ranking of punctuations from the system.
     */
    public void consultRanking(){

    }

    /**
     * It returns all the punctuations of the current User in all their Games.
     */
    public void consultPersonalStats(){

    }

    /**
     * The execution of a Game is on.
     * @param idGame is the identification of the current game.
     */
    public void playKakuro(int idGame){

    }

    /**
     * The execution of a Game it stops and the current state is saved.
     * @param idGame is the identification of the current game.
     */
    public void saveGame(int idGame){

    }

}
