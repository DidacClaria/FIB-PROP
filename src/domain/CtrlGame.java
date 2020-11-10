package domain;

public class CtrlGame {
    //ATTRIBUTES
    private CtrlDomain ctrlDomain;

    private Game game;

    //CONSTRUCTOR
    public CtrlGame() {
    }

    public CtrlGame(CtrlDomain ctrlDomain, Game game) {
        this.ctrlDomain = ctrlDomain;
        this.game = game;
    }

    //GETTERS & SETTERS
    public CtrlDomain getCtrlDomain() {
        return ctrlDomain;
    }

    public void setCtrlDomain(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //CLASS METHODS
    public void consultRanking(){

    }

    public void consultPersonalStats(){

    }

    public void playKakuro(int idGame){

    }

    public void saveGame(int idGame){

    }

    public void askHint(int idGame){

    }

}
