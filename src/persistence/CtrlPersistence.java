package persistence;

import domain.CtrlDomain;

/**
 * Persistence Controller Class.
 */
public class CtrlPersistence {
    //ATTRIBUTES

    /**
     * Domain Controller.
     */
    private final CtrlDomain ctrlDomain;

    //CONSTRUCTOR

    /**
     * Default empty Persistence Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlPersistence(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
    }

}
