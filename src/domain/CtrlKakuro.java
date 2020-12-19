package domain;

import java.util.ArrayList;

/**
 * Kakuro controller class. Used to collect and manage the use cases of a Kakuro.
 */
public class CtrlKakuro {

    //ATTRIBUTES
    /**
     * Domain controller. Used to communicate back the result of the class methods.
     */
    private final CtrlDomain ctrlDomain;

    /**
     * Collection of the kakuros on the system.
     */
    private final ArrayList<Kakuro> kakuros;

    /**
     * Kakuro class. Is the instance of the kakuro currently running in the system.
     */
    private Kakuro kakuroCreated;

    /**
     * It contains the current global number of kakuros created and saved.
     */
    private int numKakuros;

    //CONSTRUCTORS
    /**
     * Default Kakuro Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlKakuro(CtrlDomain ctrlDomain, ArrayList<String[][]> kakuros) {
        this.ctrlDomain = ctrlDomain;
        this.kakuros = new ArrayList<>();
        for (int i=0; i<kakuros.size(); i+=2){
            ++numKakuros;
            String[][] k_string = kakuros.get(i);
            String[][] sol = kakuros.get(i+1);
            Kakuro k = new Kakuro();
            k.setSolution(sol);
            k.setCells(k_string);
            k.setIdKakuro(numKakuros);
            this.kakuros.add(k);
        }

    }

    //GETTERS AND SETTERS
    public int listIdKakuro () {
        return this.numKakuros;
    }

    //CLASS METHODS

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     */
    public void generateKakuro(int numRows, int numColumns, int diff, int fc){
        if (numColumns>= 3 && numRows>=3 && numColumns<=10 && numRows<=10){
            kakuroCreated = new Kakuro(numRows, numColumns, diff, fc);
            kakuros.add(kakuroCreated);
            ++numKakuros;
            kakuroCreated.setIdKakuro(numKakuros);
        }
        else if (numColumns<=10 && numRows<=10) {
            throw new ArithmeticException("The size of the Kakuro is too small");
        }
        else {
            throw new ArithmeticException("The size of the Kakuro is too big");
        }
    }

    /**
     * This method checks if the Kakuro proposed by the user is valid and it is created.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param field It has the information of every individual Cell in the Kakuro.
     * @return It will return true if the kakuro to be proposed has solution and otherwise false.
     */

    public int proposeKakuro(int numRows, int numColumns, String[][] field){
        kakuroCreated = new Kakuro();
        Boolean b = kakuroCreated.proposeKakuro(numRows,numColumns,field);
        if (b) {
            ++numKakuros;
            kakuroCreated.setIdKakuro(numKakuros);
            kakuros.add(kakuroCreated);
            System.out.println("\nProposed Successfully");
            return numKakuros;
        }
        return -1;
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] listKakuro (int id) {
        for (Kakuro k: kakuros){
            if (k.getIdKakuro() == id) return k.listKakuro();
        }
        return null;
    }

}
