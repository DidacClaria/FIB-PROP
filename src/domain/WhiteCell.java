package domain;

/**
 * Subclass of Cell used to created WhiteCell.
 */
public class WhiteCell extends Cell {
    //ATTRIBUTES
    /**
     * Value of a white cell.
     */
    private int value;

    //CONSTRUCTORS

    /**
     * Default empty WhiteCell constructor.
     * @param value It indicates the value of the WhiteCell from 1-9. If the value is 0 it means it doesn't have any value.
     */
    public WhiteCell(int value) {
        this.value = value;
    }

    //GETTERS & SETTERS
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getColumnValue() {
        return 0;
    }

    @Override
    public void setColumnValue(int columnValue) {

    }

    @Override
    public int getRowValue() {
        return 0;
    }

    @Override
    public void setRowValue(int rowValue) {

    }
}
