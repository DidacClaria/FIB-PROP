package domain;

public class Kakuro {
    //ATTRIBUTES
    private int idKakuro;

    private int numRows;

    private int numColumns;

    private Cell[][] cells;

    private int dificulty;

    //CONSTRUCTORS
    public Kakuro() {
    }

    //GETTERS & SETTERS


    //CLASS METHODS
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
