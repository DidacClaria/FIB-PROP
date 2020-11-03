package domain;

public class Kakuro {
    //ATTRIBUTES
    private CtrlKakuro ctrlKakuro;

    private User author;

    private Game game;

    private Cell[] cells;

    private int idKakuro;

    private int numRows;

    private int numColumns;

    private int dificulty;

    //CONSTRUCTORS
    public Kakuro() {
    }

    public Kakuro(CtrlKakuro ctrlKakuro, User author, Game game, Cell[] cells, int idKakuro, int numRows, int numColumns, int dificulty) {
        this.ctrlKakuro = ctrlKakuro;
        this.author = author;
        this.game = game;
        this.cells = cells;
        this.idKakuro = idKakuro;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.dificulty = dificulty;
    }

    //GETTERS & SETTERS
    public CtrlKakuro getCtrlKakuro() {
        return ctrlKakuro;
    }

    public void setCtrlKakuro(CtrlKakuro ctrlKakuro) {
        this.ctrlKakuro = ctrlKakuro;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public int getIdKakuro() {
        return idKakuro;
    }

    public void setIdKakuro(int idKakuro) {
        this.idKakuro = idKakuro;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getDificulty() {
        return dificulty;
    }

    public void setDificulty(int dificulty) {
        this.dificulty = dificulty;
    }
}
