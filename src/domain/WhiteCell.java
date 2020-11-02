package domain;

public class WhiteCell extends Cell {
    //ATTRIBUTES
    private int value;

    //CONSTRUCTORS
    public WhiteCell() {
    }

    public WhiteCell(Kakuro kakuro, int posX, int posY, int value) {
        super(kakuro, posX, posY);
        this.value = value;
    }

    //GETTERS & SETTERS
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
