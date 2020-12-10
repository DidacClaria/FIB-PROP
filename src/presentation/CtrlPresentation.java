package presentation;

import domain.CtrlDomain;
import domain.Main;

/**
 * Presentation Controller Class.
 */
public class CtrlPresentation {
    //ATTRIBUTES
    /**
     * Domain Controller.
     */
    private final CtrlDomain ctrlDomain;

    private final MainFrame mainFrame;


    //CONSTRUCTOR
    /**
     * Default empty Presentation Controller constructor.
     */
    public CtrlPresentation() {
        this.ctrlDomain = new CtrlDomain(this);
        this.mainFrame = new MainFrame(this);
    }

    public void initPresentation() {
        mainFrame.makeVisible();
    }
}
