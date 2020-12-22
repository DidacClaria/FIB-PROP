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

    //CONSTRUCTORS

    /**
     * Default empty User constructor.
     */
    public User() {
    }

    public User(String n) {
        this.name = n;
    }

    public String getNom () {
        return this.name;
    }


}
