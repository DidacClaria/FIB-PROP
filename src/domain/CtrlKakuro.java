package domain;

/**
 * Kakuro controller class. Used to collect and manage the use cases of a Kakuro.
 */
public class CtrlKakuro {

    //ATTRIBUTES
    /**
     * Domain controller. Used to communicate back the result of the class methods.
     */
    private CtrlDomain ctrlDomain;

    /**
     * Kakuro class. Is the instance of the kakuro currently running in the system.
     */
    private Kakuro kakuro;

    /**
     * It contains the current global number of kakuros created and saved.
     */
    private int numKakuros;

    //CONSTRUCTORS
    /**
     * Default empty Kakuro Controller constructor.
     */
    public CtrlKakuro() {
    }

    //CLASS METHODS

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param dificulty It indicates the level of dificulty of the Kakuro (It will go from 0 to 2).
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param numFilledCells It indicates the number of WhiteCells with an assigned value that the Kakuro will have.
     * @param numBlackCells It indicates the number of BlackCells that the Kakuro will have.
     * @param numWhiteCells It indicates the number of WhiteCells that the Kakuro will have.
     */
    public void generateKakuro(String dificulty, int numRows, int numColumns, int numFilledCells, int numBlackCells, int numWhiteCells){

    }

    /**
     * This method checks if the Kakuro proposed by the user is valid and it is created.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param field It has the information of every individual Cell in the Kakuro.
     */
    public void proposeKakuro(int numRows, int numColumns, String[][] field){
        kakuro.proposeKakuro(numRows,numColumns,field);
    }

}
