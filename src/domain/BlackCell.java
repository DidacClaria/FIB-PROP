package domain;

public class BlackCell extends Cell {
    //ATTRIBUTES
    private int columnValue;

    private int rowValue;

    //CONSTRUCTORS
    public BlackCell() {
    }

    public BlackCell(Kakuro kakuro, int posX, int posY, int columnValue, int rowValue) {
        super(kakuro, posX, posY);
        this.columnValue = columnValue;
        this.rowValue = rowValue;
    }

    //GETTERS & SETTERS
    public int getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(int columnValue) {
        this.columnValue = columnValue;
    }

    public int getRowValue() {
        return rowValue;
    }

    public void setRowValue(int rowValue) {
        this.rowValue = rowValue;
    }
}
