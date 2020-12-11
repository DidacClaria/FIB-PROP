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

    public void logInUser(String username) {
//        ctrlDomain.logInUser(username);
    }

    public void generateKakuro(int width, int height, int difficulty, int numFilledCells) {
//        String[][] kakuro = ctrlDomain.generateKakuro(width,height);
    }

    public void makeUserMenuViewVisible() {
        mainFrame.makeUserMenuViewVisible();
    }

    public void makeLogInViewVisible() {
        mainFrame.makeLogInViewVisible();
    }

    public void makeCreateKakuroViewVisible() {
        mainFrame.makeCreateKakuroViewVisible();
    }

}
