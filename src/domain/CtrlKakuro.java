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
    private Kakuro kakuro;

    /**
     * It contains the current global number of kakuros created and saved.
     */
    private int numKakuros;

    //CONSTRUCTORS
    /**
     * Default Kakuro Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlKakuro(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
        this.kakuros = new ArrayList<Kakuro>();
        this.numKakuros = 0;
    }

    //GETTERS AND SETTERS
    public int consult_id_kakuro () {
        return this.numKakuros;
    }

    //CLASS METHODS

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     */
    public void generateKakuro(int numRows, int numColumns){
        if (numColumns>= 3 && numRows>=3 && numColumns<=10 && numRows<=10){
            kakuro = new Kakuro(numRows, numColumns);
            kakuros.add(kakuro);
            ++numKakuros;
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
     */
    public void proposeKakuro(int numRows, int numColumns, String[][] field){
        boolean validField = false;
        for (String[] row:field){
            for (String cell: row){
                if (!(cell.equals("*") || cell.equals("?"))){
                    validField=false;
                    String[] parts = cell.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
                    try{
                        if (parts[0].equals("F") && parts[1].length()<3 && parts[2]==null) validField =true;
                        else if (parts[0].equals("C") && parts[1].length()<3 && parts[2]==null) validField =true;
                        else if (parts[0].equals("C") && parts[2].equals("F") && (parts[1].length()<3 || parts[3].length()<3)) validField=true;

                    } catch (ArrayIndexOutOfBoundsException ignored){
                        if (parts[0].equals("F") && parts[1].length()<3) validField = true;
                        else validField = parts[0].equals("C") && parts[1].length() <= 3;
                    }
                }
                else validField=true;
                if (!validField) break;
            }
        }
        if (validField){
            if (numColumns>= 3 && numRows>=3){
                kakuro = new Kakuro();
                Boolean b = kakuro.proposeKakuro(numRows,numColumns,field);
                if (b) {
                    kakuro.setIdKakuro(numKakuros);
                    ++numKakuros;
                    kakuros.add(kakuro);
                    System.out.println("\nProposed Successfully");
                }
                else System.out.println("No Solution Found Out");
            }
            else throw new ArithmeticException("The size of the Kakuro is too small");
        }
        else throw new ArithmeticException("The proposed field is not valid");
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] listKakuro () {
        return kakuro.listKakuro();
    }

}
