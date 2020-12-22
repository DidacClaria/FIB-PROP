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

    //CONSTRUCTORS

    /**
     * Default User creator
      * @param n
     */
    public User(String n) {
        this.name = n;
    }

    /**
     * Getter of name
     * @return name
     */
    public String getNom () {
        return this.name;
    }


}
