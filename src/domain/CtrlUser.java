package domain;

import java.util.ArrayList;

/**
 * User controller class. Used to collect and manage the use cases of a User.
 */
public class CtrlUser {

    //ATTRIBUTES

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
     */
    public CtrlUser(ArrayList<String> users) {
        this.users = new ArrayList<>();
        for (String u: users) this.users.add(new User(u));
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
     * This method returns the username of the active user.
     * @return Username of the active user.
     */
    public String getActiveUser () {
        return activeUser.getNom();
    }

    /**
     * It will set the activeUser
     * @param n It indicates the name of the activeUser.
     */
    public void setActiveUser (String n) {
        boolean found = false;
        for (User i : users) {
            if (!found && i.getNom().equals(n)) {
                activeUser = i;
                found = true;
            }
        }
        if (!found) createUser(n);
    }

    /**
     * This method deletes a user from domain layer, erasing it from the collection of users.
     * @param user Username to delete.
     */
    public void deleteUser(String user){
        for (User u : users){
            if (u.getNom().equals(user)) {
                users.remove(u);
                break;
            }
        }
    }

}
