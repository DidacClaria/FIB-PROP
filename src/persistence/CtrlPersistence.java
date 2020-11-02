package persistence;

import domain.CtrlDomain;

public class CtrlPersistence {
    //ATTRIBUTES
    private CtrlDomain ctrlDomain;

    //CONSTRUCTOR
    public CtrlPersistence() {
    }

    public CtrlPersistence(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
    }

    //GETTERS & SETTERS
    public CtrlDomain getCtrlDomain() {
        return ctrlDomain;
    }

    public void setCtrlDomain(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
    }
}
