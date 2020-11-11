package domain;

/**
 * Generic class used to created Cells.
 */
public abstract class Cell {
    //CONSTRUCTORS
    /**
     * Default empty Cell constructor.
     */
    public Cell() {
    }

    public abstract int getValue();

    public abstract void setValue(int value);

    public abstract int getColumnValue();

    public abstract void setColumnValue(int columnValue);

    public abstract int getRowValue();

    public abstract void setRowValue(int rowValue);

}
