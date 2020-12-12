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
    private User activeUser;

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
     * @param name It indicates the name of the activeUser.
     */
    public void createUser(String name){
        activeUser = new User(name);
        users.add (activeUser);
    }

    /**
     * It will set the activeUser
     * @param n It indicates the name of the activeUser.
     */
    public void setActiveUser (String n) {
        User aux = new User(n);
        activeUser = users.get (users.indexOf(aux));
    }
}
