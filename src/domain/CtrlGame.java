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
        this.games = new ArrayList<Game>();
    }

    //WIP CLASS METHODS

    /**
     * The execution of a Game is on.
     * @param idKakuro is the identification of the current game.
     */
    public void createNewGame(String user, int idKakuro){
        ++this.numGames;
        activeGame = new Game(user, idKakuro, numGames);
        this.games.add(activeGame);
    }

    /*
     * The execution of a Game it stops and the current state is saved.
     * @param idGame is the identification of the current game.
     */
    public void saveGame(int time, int hints, String[][] state){
        Game g = getGame(activeGame.get_game_id());
        g.
    }

    public ArrayList<Integer> getGames(int id_kakuro){
        ArrayList<Integer> list = new ArrayList<>();
        for(Game g : games){
            if (g.get_kakuro_id() == id_kakuro) list.add(g.get_game_id());
        }
        return list;
    }

    public void setActiveGame(int id_game) {
        boolean found = false;
        for (Game g : games) {
            if (!found && (g.get_game_id() == id_game)) {
                this.activeGame = g;
                found = true;
            }
        }
    }

    public Game getGame(int id_game){
        for (Game g : games) {
            if ((g.get_game_id() == id_game)) {
                return g;
            }
        }
        return null;
    }

    public void delete_game(int id_game){
        for (Game g : games) {
            if ((g.get_game_id() == id_game)) {
                games.remove(g);
            }
        }
    }

    public int getActiveGame(){
        return activeGame.get_game_id();
    }

}
