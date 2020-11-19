package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    /**
     * This attribute indicates the possible solutions of the Kakuro (1 unique and 2 multiple)
     */
    private int solutions;

    /**
     * This attribute indicates the name of the author of the Kakuro.
     */
    private String author;

    //CONSTRUCTORS

    /**
     * Default empty Kakuro constructor.
     */
    public Kakuro() {
    }

    /**
     * Kakuro constructor that generates a kakuro with random black cells and it has unique solution
     * @param x It indicates the number of rows that the Kakuro will have.
     * @param y It indicates the number of columns that the Kakuro will have.
     */
    public Kakuro(int x, int y) {
        this.numRows = x;
        this.numColumns = y;
        this.cells = new Cell[numRows][numColumns];
        this.solutions = 0;

        for(int i = 0; i < numRows; ++i) {
            for(int j = 0; j < numColumns; ++j) {
                if (i != 0 && j != 0) cells[i][j] = new WhiteCell(0);
                else cells[i][j] = new BlackCell(0,0);
            }
        }

        for(int k = 0; k < 100; ++k) generateRandomBlack(numRows-1, numColumns-1, 1, 1);
        correctFormat();


        boolean aux = generateWhiteNumbers();
        while (!aux) aux = generateWhiteNumbers();
        generateBlackNumbers();
        while (solveKakuroMultiple()) {
            clearWhiteCells();
            aux = generateWhiteNumbers();
            while (!aux) aux = generateWhiteNumbers();
            generateBlackNumbers();
            this.solutions = 0;
        }


    }

    //GETTERS & SETTERS

    /**
     * Consultant function
     * @return It returns the id of the kakuro
     */
    public int getIdKakuro() {
        return idKakuro;
    }

    /**
     * Modifier function
     * @param idKakuro It indicates the id of the kakuro
     */
    public void setIdKakuro(int idKakuro) {
        this.idKakuro = idKakuro;
    }

    //CLASS METHODS

    /**
     * This method generates randomly black cells in the kakuro
     * @param rangeX It indicates the range of number that can be generated horizontally (Row)
     * @param rangeY It indicates the range of number that can be generated vertically (Column)
     * @param minimumX It indicates the minimum of number that can be generated horizontally (Row)
     * @param minimumY It indicates the minimum of number that can be generated vertically (Column)
     */
    private void generateRandomBlack (int rangeX, int rangeY, int minimumX, int minimumY) {
        int randX = (int) (Math.random() * rangeX) + minimumX;
        int randY = (int) (Math.random() * rangeY) + minimumY;
        if (cells[randX][randY] instanceof WhiteCell && noAloneSym(randX, randY) && dfsSym(randX, randY)) {
            cells[randX][randY] = new BlackCell(0,0);
            cells[numRows - randX][numColumns - randY] = new BlackCell(0,0);
            System.out.print(randX);
            System.out.print(randY);
        }
    }

    /**
     * This method checks if the position indicated in the parameters is correct or not
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return TRUE if the position indicated in the parameters and his 180º symmetrical position comply the rule of the kakuro
     * FALSE if the position indicated in the parameters and his 180º symmetrical position comply the rule of the kakuro
     */
    private boolean noAloneSym(int x, int y) {
        return noAlone(x, y) && noAlone(numRows - x, numColumns - y);
    }

    /**
     * Immersion function of "noAloneSym"
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return TRUE if the position indicated in the parameters complies the rule of the kakuro
     * FALSE if the position indicated in the parameters complies the rule of the kakuro
     */
    private boolean noAlone(int x, int y) {
        if (cells[x - 1][y] instanceof WhiteCell && cells[x - 2][y] instanceof BlackCell) return false;
        if (x + 1 == numRows - 1 && cells[x + 1][y] instanceof WhiteCell) return false;
        if (x + 1 < numRows && cells[x + 1][y] instanceof WhiteCell && cells[x + 2][y] instanceof BlackCell) return false;
        if (x + 1 < numRows && cells[x + 1][y] instanceof WhiteCell && cells[x + 2][y] instanceof WhiteCell && x+2 == numRows-x) return false;

        if (cells[x][y - 1] instanceof WhiteCell && cells[x][y - 2] instanceof BlackCell) return false;
        if (y + 1 == numColumns - 1 && cells[x][y + 1] instanceof WhiteCell) return false;
        if (y + 1 < numColumns && cells[x][y + 1] instanceof WhiteCell && cells[x][y + 2] instanceof BlackCell) return false;
        if (y + 1 < numColumns && cells[x][y + 1] instanceof WhiteCell && cells[x][y + 2] instanceof WhiteCell && y+2 == numColumns-y) return false;


        return true;
    }

    /**
     * This method uses Depth-First-Search to check if all the white cells of the kakuro are connected
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return TRUE if all the white cells are connected
     * FALSE if all the white cells are not connected
     */
    private boolean dfsSym(int x, int y) {
        boolean[][] m = new boolean[numRows][numColumns];
        int visited = numRows * numColumns;
        int posX = 0;
        int posY = 0;
        boolean trobat = false;

        for(int i = 0; i < numRows; ++i) {
            for(int j = 0; j < numColumns; ++j) {
                if (cells[i][j] instanceof BlackCell) {
                    m[i][j] = true;
                    --visited;
                }

                if (!trobat && cells[i][j] instanceof WhiteCell && (i != x || j != y || i != numRows - x || j != numColumns - y)) {
                    posX = i;
                    posY = j;
                    trobat = true;
                }
            }
        }

        m[x][y] = true;
        m[numRows - x][numColumns - y] = true;
        visited -= 2;
        return visited == searchDFS(posX, posY, m);
    }

    /**
     * Depth-First-Search
     * @param i It indicates the position of the x-axis of the cell
     * @param j It indicates the position of the y-axis of the cell
     * @param m A matrix of the white cells visited
     * @return It returns the number of white cells visited
     */
    private int searchDFS(int i, int j, boolean[][] m) {
        if (i >= numRows || j >= numColumns) return 0;
        if (m[i][j]) return 0;
        if (!m[i][j]) m[i][j] = true;
        return 1 + searchDFS(i - 1, j, m) + searchDFS(i + 1, j, m) + searchDFS(i, j - 1, m) + searchDFS(i, j + 1, m);
    }

    /**
     * This method corrects those black cells which have more than 9 white cells
     */
    private void correctFormat () {
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; ++j) {
                if (cells[i][j] instanceof BlackCell) {
                    if (wrongCellH(i, j)) correctCellH(i, j);
                    if (wrongCellV(i, j)) correctCellV(i, j);
                }
            }
        }
    }

    /**
     * This method checks horizontally the white cells of the position indicated in the parameters
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return TRUE if there are more than 9 horizontal white cells
     * FALSE it there are less than 9 horizontal white cells
     */
    private boolean wrongCellH (int x, int y) {
        if (y+10 >= numColumns) return false;
        for (int i = y+1; i <= (y+10); ++i) {
            //System.out.println ("H" + " " + i);
            if (cells[x][i] instanceof BlackCell) return false;
        }
        return true;
    }

    /**
     * This method checks vertically the white cells of the position indicated in the parameters
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return TRUE if there are more than 9 vertical white cells
     * FALSE it there are less than 9 vertical white cells
     */
    private boolean wrongCellV (int x, int y) {
        if (x+10 >= numRows) return false;
        for (int i = x+1; i <= (x+10); ++i) {
            //System.out.println ("V" + " " + i);
            if (cells[i][y] instanceof BlackCell) return false;
        }
        return true;
    }

    /**
     * This method adds randomly in the horizontal position of the cell indicated in the parameters
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     */
    private void correctCellH (int x, int y) {
        ArrayList<Pair> safe = new ArrayList<Pair>();
        for (int i = y+1; i <= (y+10); ++i) {
            if (noAloneSym(x, i) && dfsSym(x, i)) safe.add(new Pair(x, i));
        }
        if (safe.isEmpty()) restart();
        else {
            int k = (int) (Math.random() * safe.size());
            Pair aux = safe.get(k);
            int posX = aux.first();
            int posY = aux.second();
            cells[posX][posY] = new BlackCell(0,0);
            cells[numRows - posX][numColumns - posY] = new BlackCell(0,0);
        }
    }

    /**
     * This method adds randomly in the vertical position of the cell indicated in the parameters
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     */
    private void correctCellV (int x, int y) {
        ArrayList<Pair> safe = new ArrayList<Pair>();
        for (int i = x+1; i <= (x+10); ++i) {
            if (noAloneSym(i, y) && dfsSym(i, y)) safe.add(new Pair(i, y));
        }
        if (safe.isEmpty()) restart();
        else {
            int k = (int) (Math.random() * safe.size());
            Pair aux = safe.get(k);
            int posX = aux.first();
            int posY = aux.second();
            cells[posX][posY] = new BlackCell(0,0);;
            cells[numRows - posX][numColumns - posY] = new BlackCell(0,0);;
        }
    }

    /**
     * This method restarts the kakuro format generator if it can not correct the wrong black cells
     */
    private void restart () {
        for(int i = 1; i < numRows; ++i) {
            for(int j = 1; j < numColumns; ++j) cells[i][j] = new WhiteCell(0);
        }

        for(int k = 0; k < 1000; ++k) generateRandomBlack(numRows-1, numColumns-1, 1, 1);
        correctFormat();
    }

    /**
     * This methods adds randomly a number between 1 and 9 in each white cell of the kakuro
     * @return TRUE if all the white cells are filled with a number
     * FALSE if all the white cells are not filled with a number
     */
    private boolean generateWhiteNumbers () {
        for (int i = 1; i < numRows; ++i) {
            for (int j = 1; j < numColumns; ++j) {
                if (cells[i][j] instanceof WhiteCell) {
                    ArrayList <Integer> possibleNumbers = listRCNumbers(i ,j);
                    if (possibleNumbers.size() == 0) {
                        clearWhiteCells();
                        return false;
                    }
                    int pos = (int)(Math.random() * possibleNumbers.size());
                    cells[i][j].setValue(possibleNumbers.get(pos));
                }
            }
        }
        return true;
    }

    /**
     * This methods calculates the vertical and horizontal sum of all black cells which have white cells
     */
    private void generateBlackNumbers () {
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; ++j) {
                if (cells[i][j] instanceof BlackCell) {
                    cells[i][j].setRowValue(calculateSumF(i, j));
                    cells[i][j].setColumnValue(calculateSumC(i, j));
                }
            }
        }
    }

    /**
     * This method checks if the possible safe numbers (not repeated) in a white cell
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return It returns an array of the possible safe numbers (not repeated) of a white cell
     */
    private ArrayList<Integer> listRCNumbers (int x, int y) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        int i;
        for (i = x-1; cells[i][y] instanceof WhiteCell; --i) list.remove(Integer.valueOf(cells[i][y].getValue()));
        for (i = x+1; i < numRows && cells[i][y] instanceof WhiteCell; ++i) list.remove(Integer.valueOf(cells[i][y].getValue()));
        for (i = y-1; cells[x][i] instanceof WhiteCell; --i) list.remove(Integer.valueOf(cells[x][i].getValue()));
        for (i = y+1; i < numColumns && cells[x][i] instanceof WhiteCell; ++i) list.remove(Integer.valueOf(cells[x][i].getValue()));

        return list;
    }

    /**
     * This method calculates the sum of all horizontal white cells in a black cell
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return It returns the sum of all horizontal white cells in a black cell
     */
    private int calculateSumF (int x, int y) {
        int sumF = 0;
        for (int i = y+1; i < numColumns && cells[x][i] instanceof WhiteCell; ++i) sumF += cells[x][i].getValue();
        return sumF;
    }

    /**
     * This method calculates the sum of all vertical white cells in a black cell
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return It returns the sum of all vertical white cells in a black cell
     */
    private int calculateSumC (int x, int y) {
        int sumC = 0;
        for (int i = x+1; i < numRows && cells[i][y] instanceof WhiteCell; ++i) sumC += cells[i][y].getValue();
        return sumC;
    }

    /**
     * This method clears the value of all white cells to 0
     */
    private void clearWhiteCells () {
        for(int i = 1; i < numRows; ++i) {
            for(int j = 1; j < numColumns; ++j) {
                if (cells[i][j] instanceof WhiteCell) cells[i][j].setValue(0);
            }
        }
    }

    /**
     * This method checks if the Kakuro proposed by the user is valid and it is created.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param field It has the information of every individual Cell in the Kakuro.
     * @return TRUE if there is a solution of the kakuro proposed
     * FALSE if there isn't a solution of the kakuro proposed
     */
    public Boolean proposeKakuro(int numRows, int numColumns, String[][] field){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cells = new Cell[numRows][numColumns];

        for (int i = 0; i<this.numRows; ++i) {
            for (int j=0; j<this.numColumns; ++j) {
                String aux = field[i][j];
                int l = aux.length();

                switch (l) {
                    case 1:
                        if (aux.charAt(0) == '?') cells[i][j] = new WhiteCell(0);
                        else if (aux.charAt(0) == '*') cells[i][j] = new BlackCell(0,0);
                        else cells[i][j]= new WhiteCell(aux.charAt(0)-'0');
                        break;
                    case 2:
                        cells[i][j]=new BlackCell(0,0);
                        if (aux.charAt(0) == 'F') cells[i][j].setRowValue(aux.charAt(1) - '0');
                        else cells[i][j].setColumnValue(aux.charAt(1) - '0');
                        break;
                    case 3:
                        cells[i][j]=new BlackCell(0,0);
                        int q = Integer.parseInt(aux.substring(1, 3));
                        if (aux.charAt(0) == 'F') cells[i][j].setRowValue(q);
                        else cells[i][j].setColumnValue(q);
                        break;
                    case 4:
                        cells[i][j]=new BlackCell(0,0);
                        cells[i][j].setColumnValue(aux.charAt(1) - '0');
                        cells[i][j].setRowValue(aux.charAt(3) - '0');
                        break;
                    case 5:
                        cells[i][j]=new BlackCell(0,0);
                        if (aux.charAt(2) == 'F') {
                            cells[i][j].setColumnValue(aux.charAt(1) - '0');
                            cells[i][j].setRowValue(Integer.parseInt(aux.substring(3, 5)));
                        } else {
                            cells[i][j].setColumnValue(Integer.parseInt(aux.substring(1, 3)));
                            cells[i][j].setRowValue(aux.charAt(4) - '0');
                        }
                        break;
                    case 6:
                        cells[i][j]=new BlackCell(0,0);
                        cells[i][j].setColumnValue(Integer.parseInt(aux.substring(1, 3)));
                        cells[i][j].setRowValue(Integer.parseInt(aux.substring(4, 6)));
                        break;
                }
            }
        }
        return solveKakuro();
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] listKakuro () {
        String [][] l = new String [numRows][numColumns];
        String aux;

        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j <numColumns; ++j){
                if (cells[i][j] instanceof BlackCell) {
                    if (cells[i][j].getColumnValue() > 0 && cells[i][j].getRowValue() > 0) {
                        aux = "C" + cells[i][j].getColumnValue() + "F" + cells[i][j].getRowValue();
                    }
                    else if (cells[i][j].getColumnValue() > 0) aux = "C" + cells[i][j].getColumnValue();
                    else if (cells[i][j].getRowValue() > 0) aux = "F" + cells[i][j].getRowValue();
                    else aux = "*";
                }
                else aux = String.valueOf(cells[i][j].getValue());
                l[i][j] = aux;
            }
        }

        return l;
    }

    /**
     * Kakuro solver
     * @return TRUE if there is a solution of the kakuro proposed
     * FALSE if there isn't a solution of the kakuro proposed
     */
    private boolean solveKakuro () {
        ArrayList<Pair> posWhites = searchWhites();
        return solve (posWhites, 0);
    }

    /**
     * Multiple kakuro solver
     * @return TRUE if there are multiple solution of the kakuro
     * FALSE if there is unique solution
     */
    public boolean solveKakuroMultiple() {
        ArrayList <Pair> posWhites = searchWhites();
        return solveMultiple (posWhites, 0);
    }

    /**
     * Auxiliar Function
     * @return it creates an ArrayList to save the positions of all existing white cells in the kakuro
     */
    private ArrayList <Pair> searchWhites () {
        ArrayList <Pair> p = new ArrayList <Pair> ();
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; ++j) {
                if (cells[i][j] instanceof WhiteCell) p.add (new Pair(i,j));
            }
        }
        return p;
    }

    /**
     * Backtracking recursive function
     * @param posWhites It indicates the positions of all existing white cells in the kakuro
     * @param k It indicated the iterator of the posWhites
     * @return TRUE if it exists a solution
     * FALSE if it doesn't exist a solution
     */
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

    /**
     * Backtracking recursive function
     * @param posWhites It indicates the positions of all existing white cells in the kakuro
     * @param k It indicated the iterator of the posWhites
     * @return TRUE if it exists multiple solution
     * FALSE if there is only one solution
     */
    private boolean solveMultiple (final ArrayList <Pair> posWhites, int k) {
        if (k == posWhites.size()) {
            ++this.solutions;
            return true;
        }

        Pair aux = posWhites.get(k);
        int posX = aux.first();
        int posY = aux.second();

        for (int i = 1; i <= 9; ++i) {
            if (checkH(posX, posY, i) && checkV(posX, posY, i)) {
                cells[posX][posY].setValue(i);
                if (solveMultiple(posWhites, k + 1) && solutions == 2) return true;
                else cells[posX][posY].setValue(0);
            }
        }
        return false;
    }

    /**
     * This method checks if the number to be added complies the rule of kakuro
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @param valor It indicates the number to be added in a white cell
     * @return TRUE if there is not any repeated number in all horizontal white cells and if they correspond with the sum of their black cell
     * FALSE if not
     */
    private boolean checkH (int x, int y, int valor) {
        int sum = valor;
        int totalF = 0;

        int aux = y-1;
        if (aux < 0) return true;

        while (cells[x][aux] instanceof WhiteCell) {
            if (cells[x][aux].getValue() == valor) return false;
            sum += cells[x][aux].getValue();
            --aux;
        }

        totalF = cells[x][aux].getRowValue();

        if (sum > totalF) return false;

        if (y + 1 == numColumns) {
            if (sum < totalF) return false;
        }
        else if (cells[x][y+1] instanceof BlackCell) {
            if (sum < totalF) return false;
        }
        return true;
    }

    /**
     * This method checks if the number to be added complies the rule of kakuro
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @param valor It indicates the number to be added in a white cell
     * @return TRUE if there is not any repeated number in all vertical white cells and if they correspond with the sum of their black cell
     * FALSE if not
     */
    private boolean checkV (int x, int y, int valor) {
        int sum = valor;
        int totalC = 0;

        int aux = x-1;
        if (aux < 0) return true;

        while (cells[aux][y] instanceof WhiteCell)    {
            if (cells[aux][y].getValue() == valor) return false;
            sum += cells[aux][y].getValue();
            --aux;
        }

        totalC = cells[aux][y].getColumnValue();

        if (sum > totalC) return false;

        if (x+1 == numRows) {
            if (sum < totalC) return false;
        }
        else if (cells[x+1][y] instanceof BlackCell) {
            if (sum < totalC) return false;
        }

        return true;
    }

}
