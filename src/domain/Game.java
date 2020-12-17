package domain;

/**
 * Generic class used to created Games.
 */
public class Game {
    //ATTRIBUTES
    /**
     * The scenario of a Game it is a Kakuro field.
     */
    private Kakuro gameScenario;

    /**
     * This attribute indicates the User player of the Game.
     */
    private User player;

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


    public Game (String user, int idKakuro) {
        this.player = new User(user);
        this.gameScenario = new Kakuro();
        gameScenario.setIdKakuro(idKakuro);
    }


}
