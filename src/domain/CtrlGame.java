package domain;

import java.util.ArrayList;

/**
 * Game controller class. Used to collect and manage the use cases of a Game.
 */
public class CtrlGame {
    //ATTRIBUTES
    /**
     * Domain controller. Used to communicate back the result of the class methods.
     */
    private final CtrlDomain ctrlDomain;

    /**
     * Collection of games in the system.
     */
    private final ArrayList<Game> games;

    /**
     * Game class. Is the instance of the game currently running in the system.
     */
    private Game activeGame;

    /**
     * Number of games in the system.
     */
    private int numGames;

    //CONSTRUCTOR

    /**
     * Default empty Game Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlGame(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
        this.games = new ArrayList<Game>();
    }

    //WIP CLASS METHODS

    /**
     * The execution of a Game is on.
     * @param idGame is the identification of the current game.
     */
    public void startKakuro(String user, int idKakuro){
        ++this.numGames;
        activeGame = new Game(user, idKakuro);
        this.games.add(activeGame);
    }

    /*
     * The execution of a Game it stops and the current state is saved.
     * @param idGame is the identification of the current game.
     */
    public void saveGame(String user, int idKakuro){

    }

    /**
     * It returns the global ranking of punctuations from the system.
     */
    public void listRanking() {
        throw new ArithmeticException("Not implemented yet");
    }

    /**
     * It returns all the punctuations of the current User in all their Games.
     */
    public void listPersonalStats() {
        throw new ArithmeticException("Not implemented yet");
    }
}
