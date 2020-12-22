package domain;

/**
 * Generic class used to created Users.
 */
public class User {

    //ATTRIBUTES

    /**
     * Name of the User. It identifies it.
     */
    private String name;

    private int gamesStarted;

    //CONSTRUCTORS

    /**
     * Default empty User constructor.
     */
    public User() {
        this.gamesStarted = 0;
    }

    /**
     * Default User creator
     * @param n Username
     */
    public User(String n) {
        this.name = n;
        this.gamesStarted = 0;
    }

    /**
     * Getter of name
     * @return name
     */
    public String getNom () {
        return this.name;
    }

    /**
     * Getter of gamesStarted
     * @return gamesStarted
     */
    public int getGSuser () {
        return gamesStarted;
    }

    /**
     * Setter of gamesStarted
     * @param x gamesStarted
     */
    public void setGSuser (int x) {
        this.gamesStarted += x;
    }

}
