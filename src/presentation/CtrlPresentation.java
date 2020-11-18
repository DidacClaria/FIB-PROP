package presentation;

import domain.CtrlDomain;

/**
 * Presentation Controller Class.
 */
public class CtrlPresentation {
    //ATTRIBUTES
    /**
     * Domain Controller.
     */
    private final CtrlDomain ctrlDomain;

    //CONSTRUCTOR
    /**
     * Default empty Presentation Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlPresentation(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
    }

}
