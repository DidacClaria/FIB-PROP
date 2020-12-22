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

    /**
     * Identifier of the kakuro played.
     */
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
        int aux = stat.indexOf(":");
        int time = Integer.parseInt(stat.substring(0, aux));
        int hints = Integer.parseInt(stat.substring(aux+1));
        this.stat = new Stat(time, hints);
    }

    public Game (int idKakuro, String user, int time, int hints, int scores) {
        this.kakuroId = idKakuro;
        this.player = user;
        this.stat = new Stat(time, hints, scores);
    }

    public int getKakuroId(){
        return kakuroId;
    }

    public int getGameId(){
        return idGame;
    }

    public String getPlayer() {
        return player;
    }

    public int getTime() {
        return stat.getTime();
    }

    public int getNumHints() {
        return stat.getNumHints();
    }

    public int getScores() {
        return stat.getScores();
    }

    public String getStat() {
        return stat.getTime() + ":" + stat.getNumHints();
    }

    public String[][] getGameScenario() {
        return gameScenario;
    }

    public String getAllInfo() {
        String result = getStat() + ":" + gameScenario.length + "," + gameScenario[0].length + ":";
        for (int i = 0; i < gameScenario.length; ++i) {
            for (int j = 0; j < gameScenario[0].length; ++j) {
                result += gameScenario[i][j];
                if (i<gameScenario.length-1 || j<gameScenario[0].length-1) result += ",";
            }
        }
        return result;
    }

    public void updateStats (int time, int hints, String [][] newStats) {
        stat.updateStats(time, hints);
        this.gameScenario = newStats;
    }
}
