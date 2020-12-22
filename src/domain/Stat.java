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
        this.time = 0;
        this.numHints= 0;
        this.score = 0;
        this.completed = false;
    }

    public Stat(int time, int hints){
        this.time = time;
        this.numHints = hints;

        int scores = (72000 - time);
        if (scores - (7200 * numHints) > 0) scores -= (7200 * hints);
        else scores = 0;

        this.score = scores;

        this.completed = false;
    }

    public Stat (int time, int hints, int scores) {
        this.time = time;
        this.numHints = hints;
        this.score = scores;
        this.completed = true;
    }

    public int getTime() {
        return time;
    }

    public int getNumHints() {
        return numHints;
    }

    public int getScores() {
        return score;
    }

    public void updateStats (int time, int hints) {
        this.time = time;
        this.numHints = hints;

        int scores = (72000 - time);
        if (scores - (7200 * numHints) > 0) scores -= (7200 * hints);
        else scores = 0;

        this.score = scores;
    }
}