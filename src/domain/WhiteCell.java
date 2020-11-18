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
     * WhiteCell with value constructor.
     * @param value It indicates the value of the WhiteCell from 1-9. If the value is 0 it means it doesn't have any value.
     */
    public WhiteCell(int value) {
        this.value = value;
    }

    //GETTERS & SETTERS
    /**
     * Getter of the value of a white cell
     * @return value of a white cell
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter of the value of a white cell
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Neutral behaviour if the operation getColumnValue in the abstract class is called by the wrong subclass.
     */
    @Override
    public int getColumnValue() {
        return 0;
    }

    /**
     * Neutral behaviour if the operation setColumnValue in the abstract class is called by the wrong subclass.
     */
    @Override
    public void setColumnValue(int columnValue) {

    }

    /**
     * Neutral behaviour if the operation getRowValue in the abstract class is called by the wrong subclass.
     */
    @Override
    public int getRowValue() {
        return 0;
    }

    /**
     * Neutral behaviour if the operation setRowValue in the abstract class is called by the wrong subclass.
     */
    @Override
    public void setRowValue(int rowValue) {

    }
}
