package domain;

import java.util.ArrayList;

/**
 * Generic class used to created Users.
 */
public class User {

    //ATTRIBUTES
    /**
     * Collection of all the Games that a User started playing.
     */
    private ArrayList<Game> gamesPlayed;

    /**
     * Name of the User. It identifies it.
     */
    private String name;

    //CONSTRUCTORS

    /**
     * Default empty User constructor.
     */
    public User() {
        this.gamesPlayed = new ArrayList<Game>();
    }

    public User(String n) {
        this.name = n;
        this.gamesPlayed = new ArrayList<Game>();
    }

    public String get_Nom () {
        return this.name;
    }


}
