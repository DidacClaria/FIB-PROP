package domain;

import java.util.ArrayList;

/**
 * Kakuro controller class. Used to collect and manage the use cases of a Kakuro.
 */
public class CtrlKakuro {

    //ATTRIBUTES

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
     */
    public CtrlKakuro(ArrayList<String[][]> kakuros) {
        this.kakuros = new ArrayList<>();
        for (int i = 0; i < kakuros.size(); i += 2) {
            ++numKakuros;
            String[][] kString = kakuros.get(i);
            String[][] sol = kakuros.get(i + 1);
            Kakuro k = new Kakuro();
            k.setSolution(sol);
            k.setCells(kString);
            k.setIdKakuro(numKakuros);
            this.kakuros.add(k);
        }
    }

    //GETTERS AND SETTERS

    /**
     * This method returns the number of Kakuros created in the system.
     * @return Number of kakuros created.
     */
    public int listIdKakuro () {
        return this.numKakuros;
    }

    //CLASS METHODS

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param diff It indicates the difficulty that the Kakuro will have
     * @param fc It indicates the number of white cells filled that the Kakuro will have.
     * @return It returns the kakuro representated in String [][] with some white cells filled indicated by fc.
     */
    public String [][] generateKakuro(int numRows, int numColumns, String diff, int fc){
        if (numColumns>= 3 && numRows>=3 && numColumns<=10 && numRows<=10){
            kakuroCreated = new Kakuro(numRows, numColumns, diff);
            kakuros.add(kakuroCreated);
            ++numKakuros;
            kakuroCreated.setIdKakuro(numKakuros);

            return kakuroCreated.kakuroFilledCell(fc);
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
//            System.out.println("\nProposed Successfully");
            return numKakuros;
        }
        return -1;
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public Kakuro listKakuro (int id) {
        for (Kakuro k: kakuros){
            if (k.getIdKakuro() == id) return k;
        }
        return null;
    }

    /**
     * This method returns a list of all the existing kakuro identifiers in the system.
     * @return A list with all the identifiers.
     */
    public int [] listIdKakuros () {
        if (numKakuros == 0) return null;

        int [] result = new int[numKakuros];
        for (int i = 1; i <= numKakuros; ++i) result[i-1] = i;
        return result;
    }

}
