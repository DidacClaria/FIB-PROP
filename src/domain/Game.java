package domain;

public class Game {
    //ATTRIBUTES
    private CtrlGame ctrlGame;

    private Kakuro gameScenario;

    private User player;

    private Stat stat;

    private int idGame;

    //CONSTRUCTORS
    public Game() {
    }

    public Game(CtrlGame ctrlGame, Kakuro gameScenario, User player, Stat stat, int idGame) {
        this.ctrlGame = ctrlGame;
        this.gameScenario = gameScenario;
        this.player = player;
        this.stat = stat;
        this.idGame = idGame;
    }

    //GETTERS & SETTERS
    public CtrlGame getCtrlGame() {
        return ctrlGame;
    }

    public void setCtrlGame(CtrlGame ctrlGame) {
        this.ctrlGame = ctrlGame;
    }

    public Kakuro getGameScenario() {
        return gameScenario;
    }

    public void setGameScenario(Kakuro gameScenario) {
        this.gameScenario = gameScenario;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }
}
