package domain;

import java.util.ArrayList;

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

    //GETTERS & SETTERS
    public int getIdKakuro() {
        return idKakuro;
    }

    public void setIdKakuro(int idKakuro) {
        this.idKakuro = idKakuro;
    }


    //CLASS METHODS

    /**
     * This method checks if the Kakuro proposed by the user is valid and it is created.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param field It has the information of every individual Cell in the Kakuro.
     */
    public boolean proposeKakuro(int numRows, int numColumns, String[][] field){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cells = new Cell[numRows][numColumns];

        for (int i = 0; i<this.numRows; ++i) {
            for (int j=0; j<this.numColumns; ++j) {
                String aux = field[i][j];
                int l = aux.length();
                cells[i][j]=new BlackCell(0,0);

                switch (l) {
                    case 1:
                        if (aux.charAt(0) == '?') cells[i][j] = new WhiteCell(0);
                        else if (aux.charAt(0) == '*') cells[i][j] = new BlackCell(0,0);
                        else cells[i][j]= new WhiteCell(aux.charAt(0)-'0');
                        break;
                    case 2:
                        if (aux.charAt(0) == 'F') cells[i][j].setRowValue(aux.charAt(1) - '0');
                        else cells[i][j].setColumnValue(aux.charAt(1) - '0');
                        break;
                    case 3:
                        int q = Integer.parseInt(aux.substring(1, 3));
                        if (aux.charAt(0) == 'F') cells[i][j].setRowValue(q);
                        else cells[i][j].setColumnValue(q);
                        break;
                    case 4:
                        cells[i][j].setColumnValue(aux.charAt(1) - '0');
                        cells[i][j].setRowValue(aux.charAt(3) - '0');
                        break;
                    case 5:
                        if (aux.charAt(2) == 'F') {
                            cells[i][j].setColumnValue(aux.charAt(1) - '0');
                            cells[i][j].setRowValue(Integer.parseInt(aux.substring(3, 5)));
                        } else {
                            cells[i][j].setColumnValue(Integer.parseInt(aux.substring(1, 3)));
                            cells[i][j].setRowValue(aux.charAt(4) - '0');
                        }
                        break;
                    case 6:
                        cells[i][j].setColumnValue(Integer.parseInt(aux.substring(1, 3)));
                        cells[i][j].setRowValue(Integer.parseInt(aux.substring(4, 6)));
                        break;
                }
            }
        }
        return solveKakuro();
    }

    private boolean solveKakuro () {
        ArrayList<Pair> posWhites = searchWhites();
        return solve (posWhites, 0);
    }

    //Crear un ArrayList per guardar les posicions de totes les caselles blanques existents al kakuro [][]
    private ArrayList <Pair> searchWhites () {
        ArrayList <Pair> p = new ArrayList <Pair> ();
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; ++j) {
                if (cells[i][j] instanceof WhiteCell) p.add (new Pair(i,j));
            }
        }
        return p;
    }

    //Una funció recursiva que fa un backtracking
    //És a dir, mira valors possibles (1...9) i les seves combinacions a cada posició agafada del ArrayList pos_whites
    private boolean solve (final ArrayList <Pair> posWhites, int k) {
        if (k == posWhites.size()) return true; //El moment quan hagi vist totes les caselles blanques

        // Consultar la posició de la casella blanca
        Pair aux = posWhites.get(k);
        int posX = aux.first();
        int posY = aux.second();
        if(cells[posX][posY].getValue()>0){
            if (checkH(posX,posY,cells[posX][posY].getValue()) && checkV(posX,posY,cells[posX][posY].getValue())){
                if (solve(posWhites,k+1)) return true;
            }
        }
        else {
            // Backtracking
            for (int i = 1; i <= 9; ++i) {
                //Comprova la fila i la columna del de cada número (checkH i checkV)
                if (checkH(posX, posY, i) && checkV(posX, posY, i)) {
                    cells[posX][posY].setValue(i); // Posar el número a l'atribut tipus (Recordar els 4 tipus: -2, -1, 0 i >0)

                    if (solve(posWhites, k + 1)) return true; //Mira les combinacions possibles amb el número i
                        //Retorna cert si existeix solució amb el número i

                    else cells[posX][posY].setValue(0); //Fals si no existeix solució amb el número i
                    //Per tant, esborrar el número que hem posat
                }
            }
        }
        return false; //Quan hagi comprovat tots els números possibles 1...9 i no troba cap solució
    }

    private boolean checkH (int x, int y, int value) {
        int sum = value;
        int totalF = 0;

        int aux = y-1;
        if (aux < 0) return true;

        while (cells[x][aux].getValue() > 0) {
            if (cells[x][aux].getValue() == value) return false;
            sum += cells[x][aux].getValue();
            --aux;
        }

        totalF = cells[x][aux].getRowValue();

        if (sum > totalF) return false;

        if (y + 1 == numColumns) {
            if (sum < totalF) return false;
        }
        else if (cells[x][y+1].getValue() < 0) {
            if (sum < totalF) return false;
        }
        return true;
    }


    private boolean checkV (int x, int y, int value) {
        int sum = value;
        int totalC = 0;

        int aux = x-1;
        if (aux < 0) return true;

        while (cells[aux][y].getValue() > 0)    {
            if (cells[aux][y].getValue() == value) return false;
            sum += cells[aux][y].getValue();
            --aux;
        }

        totalC = cells[aux][y].getColumnValue();

        if (sum > totalC) return false;

        if (x+1 == numRows) {
            if (sum < totalC) return false;
        }
        else if (cells[x+1][y].getValue() < 0) {
            if (sum < totalC) return false;
        }

        return true;
    }

}
