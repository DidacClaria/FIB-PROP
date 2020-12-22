package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

    /**
     * This method is responsible of adding a new game into the repository.
     * @param u Username of the player.
     * @param idk Identifier of the kakuro scenario.
     * @param idg Identifier of the game.
     * @param game Information of the current state of the game.
     * @param stats Information of the stats of a game.
     */
    public void loadGame(String u, int idk, int idg, String[][] game, String stats){
        ++numGames;
        Game g = new Game(u, idk, idg, game, stats);
        this.games.add(g);
    }

    /**
     * This method is in charge of adding a new entry in the ranking repository.
     * @param idk Identifier of the kakuro.
     * @param u Username of the player.
     * @param time Time needed to complete the game.
     * @param hints Hints needed to complete the game.
     * @param scores Final score of the game.
     */
    public void loadRanking(int idk, String u, int time, int hints, int scores) {
        ++numGames;
        Game g = new Game(idk, u, time, hints, scores);
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

    /**
     * The execution of a Game it stops and the current state is saved.
     * @param time New time to save.
     * @param hints New number of hints to save.
     * @param state New field state to save.
     */
    public void saveGame(int time, int hints, String[][] state){
        String user = ctrlDomain.getActiveUser();
        Game g = getActiveGame();
        games.remove(g);
        String stats = time + ":" + hints;
        g = new Game(user, g.getKakuroId(), g.getGameId(), state, stats);
        games.add(g);
        activeGame.updateStats(time, hints, state);
    }

    /**
     * This method is in charge of getting all the games of an existing user for a kakuro.
     * @param username Player of the games.
     * @param idKakuro Scenario of the games.
     * @return A list with all the games id.
     */
    public ArrayList<Integer> getGames(String username, int idKakuro){
        ArrayList<Integer> list = new ArrayList<>();
        for(Game g : games){
            if (g.getKakuroId() == idKakuro && g.getPlayer().equals(username)) {
                list.add(g.getGameId());
            }
        }
        return list;
    }

    /**
     * This method sets the active game to the one specified in the parameters.
     * @param username Username of the player of the active game.
     * @param idKakuro Identifier of the scenario from the active game.
     * @param idGame Identifier of the active game.
     */
    public void setActiveGame(String username, int idKakuro, int idGame) {
        if (idGame == -1) this.activeGame = null;
        for (Game g : games) {
            if (g.getPlayer().equals(username) && g.getKakuroId() == idKakuro && g.getGameId() == idGame) {
                this.activeGame = g;
                break;
            }
        }
    }

    /**
     * Getter of activeGame
     * @return activeGame
     */
    public Game getActiveGame(){
        return activeGame;
    }

    /**
     * Getter of a single game
     * @param idGame Identifier of the game to get
     * @return If exists it returns a instance of game, if not it returns null.
     */
    public Game getGame(int idGame){
        for (Game g : games) {
            if ((g.getGameId() == idGame)) {
                return g;
            }
        }
        return null;
    }

    /**
     * It returns the global ranking or the personal stats depending on the parameter global
     * @param username Indicates the username of the player.
     * @param global Indicates whether the listing is from the personal ranking or the global ranking.
     * @return A list of all the entries of the personal or global ranking.
     */
    public String [][] listRankingOrStats(String username, boolean global) {
        ArrayList<Ranking> rankingList = new ArrayList<>();
        for (Game g : games) {
            if (global) rankingList.add(new Ranking(g.getKakuroId(), g.getPlayer(), g.getTime(), g.getNumHints(), g.getScores()));
            else if (g.getPlayer().equals(username))
                rankingList.add(new Ranking(g.getKakuroId(), username, g.getTime(), g.getNumHints(), g.getScores()));
        }

        Collections.sort(rankingList);

        int a = -1;
        int size = 0;
        for (Ranking r : rankingList) {
            if (r.getKakuroId() != a) {
                ++size;
                a = r.getKakuroId();
            }
        }

        if (rankingList.size() != 0) {
            String[][] result = new String[size][5];
            int i = 0;
            int aux = -1;
            for (Ranking r : rankingList) {
                if (r.getKakuroId() != aux) {
                    result[i][0] = String.valueOf(r.getKakuroId());
                    result[i][1] = r.getUser();
                    result[i][2] = String.valueOf(r.getTime());
                    result[i][3] = String.valueOf(r.getHints());
                    result[i][4] = String.valueOf(r.getScores());
                    ++i;
                    aux = r.getKakuroId();
                }
            }
            return result;
        }
        else return null;
    }

    /**
     * This method it returns all the information of a single game.
     * @param username Username of the player.
     * @param idKakuro Identifier of the scenario.
     * @param idGame Identifier of the game to obtain.
     * @return The information in a formatted string of the started game.
     */
    public String getGameScenario (String username, int idKakuro, int idGame) {
        for (Game g : games) {
            if (g.getPlayer().equals(username) && g.getKakuroId() == idKakuro && g.getGameId() == idGame)
                return g.getAllInfo();
        }
        return null;
    }

    /**
     * This method is in charge of deleting the entry of the specified Game.
     * @param user Username of the player.
     * @param idKakuro Identifier of the scenario.
     * @param idGame Identifier of the game to delete.
     */
    public void deleteGame(String user, int idKakuro, int idGame) {
        for (int i = 0; i < games.size(); ++i) {
            Game g = games.get(i);
            if (g.getPlayer().equals(user) && g.getKakuroId() == idKakuro && g.getGameId() == idGame) {
                games.remove(g);
            }
        }
    }

    /**
     * This method deletes all entries for an specific user.
     * @param user Username to delete.
     */
    public void deleteGames(String user) {
        for (Game g : games) {
            if (g.getPlayer().equals(user)) {
                games.remove(g);
                break;
            }
        }
    }


}
