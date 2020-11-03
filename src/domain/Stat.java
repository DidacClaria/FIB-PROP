package domain;

public class Stat {

    //ATTRIBUTES
    private Game game;

    private int time;

    private int numHints;

    private int score;

    private boolean automatic;

    private boolean completed;

    //CONSTRUCTORS
    public Stat() {
    }

    public Stat(Game game, int time, int numHints, int score, boolean automatic, boolean completed) {
        this.game = game;
        this.time = time;
        this.numHints = numHints;
        this.score = score;
        this.automatic = automatic;
        this.completed = completed;
    }

    //GETTERS & SETTERS
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNumHints() {
        return numHints;
    }

    public void setNumHints(int numHints) {
        this.numHints = numHints;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
