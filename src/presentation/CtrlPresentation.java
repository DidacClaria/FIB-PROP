package presentation;

import domain.CtrlDomain;

public class CtrlPresentation {
    //ATTRIBUTES
    private CtrlDomain ctrlDomain;

    //CONSTRUCTOR
    public CtrlPresentation() {
    }

    public CtrlPresentation(CtrlDomain ctrlDomain) {
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
