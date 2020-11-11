package domain;

/**
 * Generic class used to created Kakuros.
 */
public class Kakuro {
    //ATTRIBUTES
    /**
     * This attribute is an identifier of the Kakuro class.
     */
    private int idKakuro;

    /**
     * This attribute indicates the number of rows from the Kakuro.
     */
    private int numRows;

    /**
     * This attribute indicates the number of columns from the Kakuro.
     */
    private int numColumns;

    /**
     * This attribute indicates the information of the individual Cells from the Kakuro.
     */
    private Cell[][] cells;

    /**
     * This attribute indicates the difficulty of rows from the Kakuro.
     */
    private int difficulty;

    //CONSTRUCTORS

    /**
     * Default empty Kakuro constructor.
     */
    public Kakuro() {
    }

    //CLASS METHODS

    /**
     * This method checks if the Kakuro proposed by the user is valid and it is created.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param field It has the information of every individual Cell in the Kakuro.
     */
    public void proposeKakuro(int numRows, int numColumns, String[][] field){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cells = new Cell[numRows][numColumns];

        for (int i = 0; i<this.numRows; ++i) {
            for (int j=0; j<this.numColumns; ++j) {
                cells[i][j] = new Cell();

                String aux = text[j];
                int l = aux.length();

                switch (l) {
                    case 1:
                        if (aux.charAt(0) == '?') cells[i][j].set_tipus(0);
                        else cells[i][j].set_tipus(-2);
                        break;
                    case 2:
                        if (aux.charAt(0) == 'F') cells[i][j].set_sumF(aux.charAt(1) - '0');
                        else cells[i][j].set_sumC(aux.charAt(1) - '0');
                        break;
                    case 3:
                        int q = Integer.parseInt(aux.substring(1, 3));
                        if (aux.charAt(0) == 'F') cells[i][j].set_sumF(q);
                        else cells[i][j].set_sumC(q);
                        break;
                    case 4:
                        cells[i][j].set_sumC(aux.charAt(1) - '0');
                        cells[i][j].set_sumF(aux.charAt(3) - '0');
                        break;
                    case 5:
                        if (aux.charAt(2) == 'F') {
                            cells[i][j].set_sumC(aux.charAt(1) - '0');
                            cells[i][j].set_sumF(Integer.parseInt(aux.substring(3, 5)));
                        } else {
                            cells[i][j].set_sumC(Integer.parseInt(aux.substring(1, 3)));
                            cells[i][j].set_sumF(aux.charAt(4) - '0');
                        }
                        break;
                    case 6:
                        cells[i][j].set_sumC(Integer.parseInt(aux.substring(1, 3)));
                        cells[i][j].set_sumF(Integer.parseInt(aux.substring(4, 6)));
                        break;
                }
            }
        }
    }
}
