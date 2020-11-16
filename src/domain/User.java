package domain;

/**
 * Generic class used to created Users.
 */
public class User {

    //ATTRIBUTES
    /**
     * User Controller. Used to send feedback to the Domain Controller.
     */
    private CtrlUser ctrlUser;

    /**
     * This attribute has the record of all the Kakuros created by this user.
     */
    private Kakuro[] createdKakuros;

    /**
     * This attribute has the record of all the Games that a User started playing.
     */
    private Game[] gamesPlayed;

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

}
