package domain;

/**
 * Generic class used to created Games.
 */
public class Game {
    //ATTRIBUTES
    /**
     * The scenario of a Game it is a Kakuro field.
     */
    private String[][] gameScenario;

    private int kakuroId;

    /**
     * This attribute indicates the User player of the Game.
     */
    private String player;

    /**
     * This instance has all the individual stats of a Game.
     */
    private Stat stat;

    /**
     * This is an identifier of the Game class.
     */
    private int idGame;

    //CONSTRUCTORS

    /**
     * Default empty Game constructor.
     */
    public Game() {
    }



    public Game (String user, int idKakuro, int idGame, String[][] estat) {
        this.player = user;
        this.gameScenario = estat;
        this.kakuroId = idKakuro;
        this.idGame = idGame;
        this.stat = new Stat();
    }

    public Game (String user, int idKakuro, int idGame, String[][] estat, String stat){
        this.player =  user;
        this.idGame = idGame;
        this.kakuroId = idKakuro;
        this.gameScenario = estat;
        this.stat = new Stat();
    }

    public int get_kakuro_id(){
        return kakuroId;
    }

    public int get_game_id(){
        return idGame;
    }


}
