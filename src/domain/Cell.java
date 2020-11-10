package domain;

public class Cell {
    //ATTRIBUTES
    private Kakuro kakuro;

    private int posX;

    private int posY;

    //CONSTRUCTORS
    public Cell() {
    }

    public Cell(Kakuro kakuro, int posX, int posY) {
        this.kakuro = kakuro;
        this.posX = posX;
        this.posY = posY;
    }

    //GETTERS & SETTERS
    public Kakuro getKakuro() {
        return kakuro;
    }

    public void setKakuro(Kakuro kakuro) {
        this.kakuro = kakuro;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
