package domain;

import java.util.ArrayList;

/**
 * Game controller class. Used to collect and manage the use cases of a Game.
 */
public class CtrlGame {
    //ATTRIBUTES
    /**
     * Domain controller. Used to communicate back the result of the class methods.
     */
    private final CtrlDomain ctrlDomain;

    /**
     * Collection of games in the system.
     */
    private final ArrayList<Game> games;

    /**
     * Game class. Is the instance of the game currently running in the system.
     */
    private Game activeGame;

    /**
     * Number of games in the system.
     */
    private int numGames;

    //CONSTRUCTOR

    /**
     * Default empty Game Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlGame(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
        this.games = new ArrayList<>();
        this.numGames = 0;
    }

    public void loadGame(String u, int idk, int idg, String[][] game, String stats){
        ++numGames;
        Game g = new Game(u, idk, idg, game, stats);
        this.games.add(g);
    }


    /**
     * This method initializes a game and its stats.
     * @param user It references the creator of the game
     * @param idKakuro It references the id of the Kakuro to play
     * @return It returns the idGame of the game created.
     */
    public int createNewGame(String user, int idKakuro,  String[][] kakuro){
        ++this.numGames;
        activeGame = new Game(user, idKakuro, numGames, kakuro);
        this.games.add(activeGame);
        return numGames;
    }

    /*
     * The execution of a Game it stops and the current state is saved.
     * @param idGame is the identification of the current game.
     */
    public void saveGame(int time, int hints, String[][] state){
        Game g = getGame(activeGame.getGameId());
    }

    public ArrayList<Integer> getGames(int id_kakuro){
        ArrayList<Integer> list = new ArrayList<>();
        for(Game g : games){
            if (g.getKakuroId() == id_kakuro) list.add(g.getGameId());
        }
        return list;
    }

    public void setActiveGame(int id_game) {
        boolean found = false;
        for (Game g : games) {
            if (!found && (g.getGameId() == id_game)) {
                this.activeGame = g;
                found = true;
            }
        }
    }

    public Game getGame(int id_game){
        for (Game g : games) {
            if ((g.getGameId() == id_game)) {
                return g;
            }
        }
        return null;
    }

    public void deleteGame(int id_game){
        for (Game g : games) {
            if ((g.getGameId() == id_game)) {
                games.remove(g);
            }
        }
    }

    public Game getActiveGame(){
        return activeGame;
    }

    /**
     * It returns the global ranking of punctuations from the system.
     */
    public void listRanking() {
        throw new ArithmeticException("Not implemented yet");
    }

    /**
     * It returns all the punctuations of the current User in all their Games.
     */
    public void listPersonalStats() {
        throw new ArithmeticException("Not implemented yet");
    }

}
