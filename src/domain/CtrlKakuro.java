package domain;

public class CtrlKakuro {

    //ATTRIBUTES
    private CtrlDomain ctrlDomain;

    private Kakuro kakuro;

    private int numKakuros;

    //CONSTRUCTORS
    public CtrlKakuro() {
    }

    public CtrlKakuro(CtrlDomain ctrlDomain, Kakuro kakuro, int numKakuros) {
        this.ctrlDomain = ctrlDomain;
        this.kakuro = kakuro;
        this.numKakuros = numKakuros;
    }
    //GETTERS & SETTERS
    public CtrlDomain getCtrlDomain() {
        return ctrlDomain;
    }

    public void setCtrlDomain(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
    }

    public Kakuro getKakuro() {
        return kakuro;
    }

    public void setKakuro(Kakuro kakuro) {
        this.kakuro = kakuro;
    }

    public int getNumKakuros() {
        return numKakuros;
    }

    public void setNumKakuros(int numKakuros) {
        this.numKakuros = numKakuros;
    }

    //CLASS METHODS
    public void generateKakuro(String dificulty, int numRows, int numColumns, int numFilledCells, int numBlackCells, int numWhiteCells){

    }

    public void proposeKakuro(int numRows, int numColumns, String field){

    }

    public boolean solveKakuro(){
        kakuro.read_kakuro();
        ArrayList <Pair> pos_whites = kakuro.search_whites();
        return kakuro.solve (pos_whites, 0);
    }




}
