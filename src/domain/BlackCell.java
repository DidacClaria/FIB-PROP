package domain;

/**
 * Subclass of Cell used to created BlackCell.
 */
public class BlackCell extends Cell {
    //ATTRIBUTES
    /**
     * Column value of a black cell.
     */
    private int columnValue;

    /**
     * Row value of a black cell.
     */
    private int rowValue;

    //CONSTRUCTORS

    /**
     * Default empty BlackCell constructor.
     */
    public BlackCell(int columnValue, int rowValue) {
        this.columnValue=columnValue;
        this.rowValue=rowValue;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void setValue(int value) {

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
