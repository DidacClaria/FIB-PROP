package domain;

public class CtrlUser {

    //ATTRIBUTES
    private CtrlDomain ctrlDomain;

    private User user;

    //CONSTRUCTORS
    public CtrlUser() {
    }

    public CtrlUser(CtrlDomain ctrlDomain, User user) {
        this.ctrlDomain = ctrlDomain;
        this.user = user;
    }

    //GETTERS & SETTERS
    public CtrlDomain getCtrlDomain() {
        return ctrlDomain;
    }

    public void setCtrlDomain(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //CLASS METHODS
    public void createUser(String name){

    }

    public void loadUser(String name){

    }

    public void existUser(String name){

    }

}
