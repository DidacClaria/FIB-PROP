package domain;

import java.util.ArrayList;

/**
 * User controller class. Used to collect and manage the use cases of a User.
 */
public class CtrlUser {

    //ATTRIBUTES

    /**
     * Domain controller. Used to communicate back the result of the class methods.
     */
    private final CtrlDomain ctrlDomain;

    /**
     * Collection of all the users in the system.
     */
    private final ArrayList<User> users;

    /**
     * User class. Is the instance of the user currently running in the system.
     */
    private User user;

    //CONSTRUCTORS
    /**
     * Default empty User Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlUser(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
        this.users = new ArrayList<User>();
    }

    //CLASS METHODS

    /**
     * It will create a new User with the following parameters.
     * @param name It indicates the name of the User.
     */
    public void createUser(String name){
        throw new ArithmeticException("Not implemented yet");
    }

    /**
     * It will load the user with the name in the parameters as the active user.
     * @param name It indicates the name of the user to load.
     */
    public void loadUser(String name) {
        throw new ArithmeticException("Not implemented yet");
    }
}
