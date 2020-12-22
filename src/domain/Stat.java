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

    /**
     * Alternative constructor with parameters
     * @param time Indicates the time passed.
     * @param hints Indicates the number of hints.
     */
    public Stat(int time, int hints){
        this.time = time;
        this.numHints = hints;

        int scores = (72000 - time);
        if (scores - (7200 * numHints) > 0) scores -= (7200 * hints);
        else scores = 0;

        this.score = scores;

        this.completed = false;
    }

    /**
     * Alternative constructor for completed games.
     * @param time Indicates the finishing time.
     * @param hints Indicates the final number of hints asked.
     * @param scores Indicates the final score obtained.
     */
    public Stat (int time, int hints, int scores) {
        if (scores == -1) updateStats(time, hints);
        else {
            this.time = time;
            this.numHints = hints;
            this.score = scores;
        }
        this.completed = true;
    }

    /**
     * Getter of time
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * Getter of numHints
     * @return numHints
     */
    public int getNumHints() {
        return numHints;
    }

    /**
     * Getter of score
     * @return score
     */
    public int getScores() {
        return score;
    }

    /**
     * Getter of completed
     * @return completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * This method it calculates the current score of a game and sets the new time and hints.
     */
    public void updateStats (int time, int hints) {
        this.time = time;
        this.numHints = hints;

        int scores = (72000 - time);
        if (scores - (7200 * numHints) > 0) scores -= (7200 * hints);
        else scores = 0;

        this.score = scores;
    }
}