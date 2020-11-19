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

    //GETTERS & SETTERS

    /**
     * Getter of the column value of a black cell
     * @return column value of the black cell
     */
    public int getColumnValue() {
        return columnValue;
    }

    /**
     * Setter of the column value of a black cell
     * @param columnValue column value to set in the black cell
     */
    public void setColumnValue(int columnValue) {
        this.columnValue = columnValue;
    }

    /**
     * Getter of the row value of a black cell
     * @return row value of the black cell
     */
    public int getRowValue() {
        return rowValue;
    }

    /**
     * Setter of the row value of a black cell
     * @param rowValue row value to set in the black cell
     */
    public void setRowValue(int rowValue) {
        this.rowValue = rowValue;
    }

    /**
     * Neutral behaviour if the operation getValue in the abstract class is called by the wrong subclass.
     */
    @Override
    public int getValue() {
        return 0;
    }

    /**
     * Neutral behaviour if the operation setValue in the abstract class is called by the wrong subclass.
     */
    @Override
    public void setValue(int value) {

    }
}
