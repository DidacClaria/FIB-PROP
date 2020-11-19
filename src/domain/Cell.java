package domain;

/**
 * Generic abstract class used to created Cells.
 */
public abstract class Cell {

    /**
     * Abstract getter operation
     * @return value of a WhiteCell
     */
    public abstract int getValue();

    /**
     * Abstract setter operation
     * @param value indicates the new value of a WhiteCell
     */
    public abstract void setValue(int value);

    /**
     * Abstract getter operation
     * @return column value of a BlackCell
     */
    public abstract int getColumnValue();

    /**
     * Abstract setter operation
     * @param columnValue indicates the new columnValue of a BlackCell
     */
    public abstract void setColumnValue(int columnValue);

    /**
     * Abstract getter operation
     * @return row value of a BlackCell
     */
    public abstract int getRowValue();

    /**
     * Abstract setter operation
     * @param rowValue indicates the new rowValue of a BlackCell
     */
    public abstract void setRowValue(int rowValue);

}
