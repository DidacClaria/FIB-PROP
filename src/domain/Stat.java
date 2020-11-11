package domain;

/**
 * Generic class used to created Stats.
 */
public class Stat {

    //ATTRIBUTES
    /**
     * This attribute has the record of the time passed during a Game.
     */
    private int time;

    /**
     * This attributes has the record of the number of hints that the user asked for.
     */
    private int numHints;

    /**
     * This attribute has the value of the final score of a Game.
     */
    private int score;

    /**
     * This attribute indicates if the Game is already completed or not.
     */
    private boolean completed;

    //CONSTRUCTORS

    /**
     * Default empty Stat constructor.
     */
    public Stat() {
    }

}