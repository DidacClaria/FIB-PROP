package domain;

import java.util.ArrayList;

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

    public User(String n) {
        this.name = n;
        this.gamesStarted = 0;
    }

    public String getNom () {
        return this.name;
    }

    public int getGSuser () {
        return gamesStarted;
    }

    public void setGSuser (int x) {
        this.gamesStarted += x;
    }

}
