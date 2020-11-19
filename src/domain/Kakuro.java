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
     * This attribute indicates the possible solutions of the kakuro (1 unique and 2 multiple)
     */
    private int solutions;

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

        for(int k = 0; k < 100; ++k) generate_random_black(numRows-1, numColumns-1, 1, 1);
        correct_format();


        boolean aux = generate_white_numbers();
        while (!aux) aux = generate_white_numbers();
        generate_black_numbers();
        while (solve_kakuro_multiple()) {
            clear_white_cells();
            aux = generate_white_numbers();
            while (!aux) aux = generate_white_numbers();
            generate_black_numbers();
            this.solutions = 0;
        }


    }

    /**
     * This method generates randomly black cells in the kakuro
     * @param rangeX It indicates the range of number that can be generated horizontally (Row)
     * @param rangeY It indicates the range of number that can be generated vertically (Column)
     * @param minimumX It indicates the minimum of number that can be generated horizontally (Row)
     * @param minimumY It indicates the minimum of number that can be generated vertically (Column)
     */
    private void generate_random_black (int rangeX, int rangeY, int minimumX, int minimumY) {
        int randX = (int) (Math.random() * rangeX) + minimumX;
        int randY = (int) (Math.random() * rangeY) + minimumY;
        if (cells[randX][randY] instanceof WhiteCell && no_alone_sym(randX, randY) && DFS_sym(randX, randY)) {
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
    private boolean no_alone_sym(int x, int y) {
        return no_alone(x, y) && no_alone(numRows - x, numColumns - y);
    }

    /**
     * Immersion function of "no_alone_sym"
     * @param x It indicates the position of the x-axis of the cell
     * @param y It indicates the position of the y-axis of the cell
     * @return TRUE if the position indicated in the parameters complies the rule of the kakuro
     * FALSE if the position indicated in the parameters complies the rule of the kakuro
     */
    private boolean no_alone(int x, int y) {
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
    private boolean DFS_sym(int x, int y) {
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
        return visited == search_DFS(posX, posY, m);
    }

    /**
     * Depth-First-Search
     * @param i It indicates the position of the x-axis of the cell
     * @param j It indicates the position of the y-axis of the cell
     * @param m A matrix of the white cells visited
     * @return It returns the number of white cells visited
     */
    private int search_DFS(int i, int j, boolean[][] m) {
        if (i >= numRows || j >= numColumns) return 0;
        if (m[i][j]) return 0;
        if (!m[i][j]) m[i][j] = true;
        return 1 + search_DFS(i - 1, j, m) + search_DFS(i + 1, j, m) + search_DFS(i, j - 1, m) + search_DFS(i, j + 1, m);
    }

    /**
     * This method corrects those black cells which have more than 9 white cells
     */
    private void correct_format () {
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; ++j) {
                if (cells[i][j] instanceof BlackCell) {
                    if (wrong_cellH(i, j)) correct_cellH(i, j);
                    if (wrong_cellV(i, j)) correct_cellV(i, j);
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
    private boolean wrong_cellH (int x, int y) {
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
    private boolean wrong_cellV (int x, int y) {
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
    private void correct_cellH (int x, int y) {
        ArrayList<Pair> safe = new ArrayList<Pair>();
        for (int i = y+1; i <= (y+10); ++i) {
            if (no_alone_sym(x, i) && DFS_sym(x, i)) safe.add(new Pair(x, i));
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
    private void correct_cellV (int x, int y) {
        ArrayList<Pair> safe = new ArrayList<Pair>();
        for (int i = x+1; i <= (x+10); ++i) {
            if (no_alone_sym(i, y) && DFS_sym(i, y)) safe.add(new Pair(i, y));
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

        for(int k = 0; k < 1000; ++k) generate_random_black(numRows-1, numColumns-1, 1, 1);
        correct_format();
    }

    /**
     * This methods adds randomly a number between 1 and 9 in each white cell of the kakuro
     * @return TRUE if all the white cells are filled with a number
     * FALSE if all the white cells are not filled with a number
     */
    private boolean generate_white_numbers () {
        for (int i = 1; i < numRows; ++i) {
            for (int j = 1; j < numColumns; ++j) {
                if (cells[i][j] instanceof WhiteCell) {
                    ArrayList <Integer> possible_numbers = list_RC_numbers(i ,j);
                    if (possible_numbers.size() == 0) {
                        clear_white_cells();
                        return false;
                    }
                    int pos = (int)(Math.random() * possible_numbers.size());
                    cells[i][j].setValue(possible_numbers.get(pos));
                }
            }
        }
        return true;
    }

    /**
     * This methods calculates the vertical and horizontal sum of all black cells which have white cells
     */
    private void generate_black_numbers () {
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numColumns; ++j) {
                if (cells[i][j] instanceof BlackCell) {
                    cells[i][j].setRowValue(calculate_sumF(i, j));
                    cells[i][j].setColumnValue(calculate_sumC(i, j));
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
    private ArrayList<Integer> list_RC_numbers (int x, int y) {
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
    private int calculate_sumF (int x, int y) {
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
    private int calculate_sumC (int x, int y) {
        int sumC = 0;
        for (int i = x+1; i < numRows && cells[i][y] instanceof WhiteCell; ++i) sumC += cells[i][y].getValue();
        return sumC;
    }

    /**
     * This method clears the value of all white cells to 0
     */
    private void clear_white_cells () {
        for(int i = 1; i < numRows; ++i) {
            for(int j = 1; j < numColumns; ++j) {
                if (cells[i][j] instanceof WhiteCell) cells[i][j].setValue(0);
            }
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
        return solve_kakuro();
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] list_kakuro () {
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
     * Solver_kakuro
     * @return TRUE if there is a solution of the kakuro proposed
     * FALSE if there isn't a solution of the kakuro proposed
     */
    private boolean solve_kakuro () {
        ArrayList<Pair> pos_whites = search_whites();
        return solve (pos_whites, 0);
    }

    /**
     * Multiple_solver_kakuro
     * @return TRUE if there are multiple solution of the kakuro
     * FALSE if there is unique solution
     */
    public boolean solve_kakuro_multiple () {
        ArrayList <Pair> pos_whites = search_whites();
        return solve_multiple (pos_whites, 0);
    }

    /**
     * Auxiliar Function
     * @return it creates an ArrayList to save the positions of all existing white cells in the kakuro
     */
    //Crear un ArrayList per guardar les posicions de totes les caselles blanques existents al kakuro [][]
    private ArrayList <Pair> search_whites () {
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
     * @param pos_whites It indicates the positions of all existing white cells in the kakuro
     * @param k It indicated the iterator of the pos_whites
     * @return TRUE if it exists a solution
     * FALSE if it doesn't exist a solution
     */
    private boolean solve (final ArrayList <Pair> pos_whites, int k) {
        if (k == pos_whites.size()) return true; //El moment quan hagi vist totes les caselles blanques

        // Consultar la posició de la casella blanca
        Pair aux = pos_whites.get(k);
        int posX = aux.first();
        int posY = aux.second();
        if(cells[posX][posY].getValue()>0){
            if (checkH(posX,posY,cells[posX][posY].getValue()) && checkV(posX,posY,cells[posX][posY].getValue())){
                if (solve(pos_whites,k+1)) return true;
            }
        }
        else {
            // Backtracking
            for (int i = 1; i <= 9; ++i) {
                //Comprova la fila i la columna del de cada número (checkH i checkV)
                if (checkH(posX, posY, i) && checkV(posX, posY, i)) {
                    cells[posX][posY].setValue(i); // Posar el número a l'atribut tipus (Recordar els 4 tipus: -2, -1, 0 i >0)

                    if (solve(pos_whites, k + 1)) return true; //Mira les combinacions possibles amb el número i
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
     * @param pos_whites It indicates the positions of all existing white cells in the kakuro
     * @param k It indicated the iterator of the pos_whites
     * @return TRUE if it exists multiple solution
     * FALSE if there is only one solution
     */
    private boolean solve_multiple (final ArrayList <Pair> pos_whites, int k) {
        if (k == pos_whites.size()) {
            ++this.solutions;
            return true;
        }

        Pair aux = pos_whites.get(k);
        int posX = aux.first();
        int posY = aux.second();

        for (int i = 1; i <= 9; ++i) {
            if (checkH(posX, posY, i) && checkV(posX, posY, i)) {
                cells[posX][posY].setValue(i);
                if (solve_multiple(pos_whites, k + 1) && solutions == 2) return true;
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
