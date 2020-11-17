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
     * Default Kakuro Controller constructor.
     */
    public CtrlKakuro() {
        kakuro = new Kakuro();
        numKakuros = 0;
    }

    //CLASS METHODS

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     */
    public void generateKakuro(int numRows, int numColumns){
        kakuro = new Kakuro(numRows, numColumns);
    }

    /**
     * This method checks if the Kakuro proposed by the user is valid and it is created.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param field It has the information of every individual Cell in the Kakuro.
     */
    public void proposeKakuro(int numRows, int numColumns, String[][] field){
        Boolean b = kakuro.proposeKakuro(numRows,numColumns,field);
        if (b) {
            kakuro.setIdKakuro(numKakuros);
            numKakuros++;
            System.out.println("\nProposed Successfully");
        }
        else System.out.println("No Solution Found Out");
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] listKakuro () {
        return kakuro.listKakuro();
    }

}
