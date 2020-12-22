package persistence;

import domain.CtrlDomain;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Persistence Controller Class. It communicates with the Domain Controller and with the specific classes of the data in this layer.
 */
public class CtrlPersistence {
    //ATTRIBUTES

    private final CtrlDomain ctrlDomain;
    private final DataKakuro dataKakuro;
    private final DataStats dataStats;
    private final DataUser dataUser;
    public String route;
    public String routek;

    //CONSTRUCTOR

    /**
     * Default empty Persistence Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlPersistence(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
        dataKakuro = new DataKakuro();
        dataStats = new DataStats();
        dataUser = new DataUser();
        route = "./src/persistence/data";
        routek = route + "/kakuros";
        InitializePersistance();
    }

    /**
     * This method initializes the layer creating new folders in the specified route if they don't exist.
     */
    private void InitializePersistance(){
        File data = new File(route);
        if (!data.exists()){
            if (data.mkdir()) {
                data = new File(routek);
                if (!data.exists()){
                    if (data.mkdir()) {
                    }
                }
            }
        }
    }


    /**
     * This method provides all the information saved in persistence about the kakuros created.
     * @return A list with all the kakuros existing in the system.
     */
    public ArrayList<String[][]> loadKakuros(){
        ArrayList<String[][]> kakuros = new ArrayList<>();
        File f = new File(routek);
        for( String k : Objects.requireNonNull(f.list())){
           File kakuro = new File(routek + "/" + k);
           kakuros.add(dataKakuro.showKakuro(kakuro));
        }

        return kakuros;
    }

    /**
     * This method provides all the information about the users registered in the system.
     * @return A list with all the usernames.
     */
    public ArrayList<String> loadUsers(){
        ArrayList<String> users = new ArrayList<>();
        File f = new File(route);
        for (String u : Objects.requireNonNull(f.list())){
            if (!u.equals("kakuros") && !u.equals("global_ranking.txt")){
                users.add(u);
            }
        }
        return users;
    }

    /**
     * This method provides a list with all the started games of a User.
     * @param user Indicates the username that requires this information.
     * @return A list with all the information of the games and stats
     */
    public String[] startedKakuros(String user){
        File f = new File(route + "/" + user);
        return f.list();
    }

    /**
     * This method creates a new Kakuro in persistence layer.
     * @param idKakuro Indicates the id to be saved with.
     * @param kakuro It contains all the information of the kakuro created.
     * @return If the return value is true the new Kakuro was saved correctly, if not it will return false.
     */
    public boolean newKakuro(int idKakuro, String [][] kakuro){
        try{
            FileWriter wr = new FileWriter(routek + "/" + "model_" + idKakuro + ".txt");
            FileWriter wrSol = new FileWriter(routek + "/" + "model_" + idKakuro + "_sol.txt");
            File GlobalRanking = new File (route + "/" + "global_ranking.txt");
            return dataKakuro.newKakuro(wr, wrSol, kakuro, GlobalRanking);
        } catch (IOException e){
            return false;
        }
    }

    /**
     * This method is used to show either the solution or the field saved in persistence of a single game.
     * @param idKakuro It indicates the identifier of the kakuro.
     * @param solution If true means that is asking for the solution, if false for the actual status.
     * @return It returns the information of each cell in a String matrix.
     */
    public String[][] showKakuro(int idKakuro, boolean solution){
        File k;
        if (solution) k = new File(routek + "/" + "model_" + idKakuro + "_sol.txt");
        else k = new  File(routek + "/" + "model_" + idKakuro + ".txt");
        return dataKakuro.showKakuro(k);
    }

    /**
     * This method is used to save a new Game in persistence.
     * @param user Indicates the user that wants to create a new Game.
     * @param idKakuro Indicates the identifier of the game scenario.
     * @return It returns the id of the Game created.
     */
    public boolean newGame (String user, int idKakuro, int idGame){
        try{
            File pathOri = new File(routek + "/" + "model_" + idKakuro + ".txt");
            File pathUser = new File(route + "/" + user);
            File kakuro = new File(route + "/" + user + "/" + "kakuro_" + idKakuro);

            File pathDes = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + ".txt");
            FileWriter wr = new FileWriter(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" +"game_" + idGame + "_stats.txt");
            return dataKakuro.newGame(pathOri, pathUser, pathDes, wr);
        } catch (IOException e){
        }
        return false;
    }

    /**
     * This method is used to overwrite a game in persistence layer.
     * @param user Indicates the player.
     * @param idKakuro Identifies the gameScenario.
     * @param idGame Identifies the game to overwrite.
     * @param time Indicates the time passed.
     * @param hints Indicates the number of hints asked.
     * @param kakuro It contains all the information of the field.
     * @return It returns if it was possible to save or not.
     */
    public boolean saveGame (String user, int idKakuro, int idGame, int time, int hints, String [][] kakuro){
        try{
            FileWriter gameSaved = new FileWriter(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + ".txt");
            FileWriter gameSavedStats = new FileWriter(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" +"game_" + idGame + "_stats.txt");
            return dataKakuro.saveGame(gameSaved, gameSavedStats, time, hints, kakuro);
        } catch (IOException e){
            return false;
        }

    }

//    /**
//     *
//     * @param user
//     * @param idKakuro
//     * @param idGame
//     * @param kakuro
//     * @return
//     */
//    public boolean validateCorrectnessGame (String user, int idKakuro, int idGame, String [][] kakuro){
//        String [][] solution = showKakuro(idKakuro, true);
//        File f = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + ".txt");
//        File fStats = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + "_stats.txt");
//        return dataKakuro.validateCorrectnessGame(solution, f, fStats, kakuro);
//    }

    /**
     * This operation is used to delete an existing game.
     * @param user Indicates the user that wants to delete a game.
     * @param idKakuro Indicates the game scenario of the game to delete.
     * @param idGame Identifies the game to delete.
     */
    public void deleteGame (String user, int idKakuro, int idGame) {
        File f = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + ".txt");
        if (!f.exists()) {
            File dir = new File(route + "/" + user + "/" + "kakuro_" + idKakuro);
            String [] size = dir.list();
            if (size.length == 0) dir.delete();
        }
        else {
            f.delete();
            File stats = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + "_stats.txt");
            stats.delete();
        }
    }

    /**
     * This method is used to register a new User in the system.
     * @param name Indicates the username that the user specified.
     * @return It indicates whether the user was possible to create or not.
     */
    public boolean createUser (String name){
        File user = new File(route + "/" + name);
        File f = new File(route + "/" + name + "/" + "personal_stats.txt");
        return dataUser.createUser(user, f);
    }

    /**
     * This method is in charge to delete a specified user.
     * @param user Indicates the username of the user to delete.
     */
    public void deleteUser (String user) {
        File f = new File(route + "/" + user);
        dataUser.deleteUser(f);
    }

    /**
     * This method overwrites the stats of a game.
     * @param user Indicates the player.
     * @param global Indicates if this stats are updated in the globalRanking or the personalStats list.
     */
    public void updateStats (String user, String [][] r, boolean global) {
        try{
            FileWriter f;
            if (global) f = new FileWriter(route + "/" + "global_ranking.txt");
            else f = new FileWriter(route + "/" + user + "/" + "personal_stats.txt");
            dataStats.updateStats(r, f);
        } catch (IOException e){

        }
    }

    /**
     * This method lists all the entries on the personal stats list or global ranking.
     * @param user Indicates the active user.
     * @param global Indicates whether is requested the global ranking list or the personal stats list.
     * @return A list with all the entries in the selected ranking.
     */
    public String[][] listRankingOrStats(String user, boolean global){
        File s;
        String r;
        if (global) s = new File(route + "/" + "global_ranking.txt");
        else s = new File(route + "/" + user + "/" + "personal_stats.txt");
        return dataStats.listRankingOrStats(s);
    }

    /**
     * This method is used to get the information of all the games of a User.
     * @param user Indicates the user to search for.
     * @param idKakuro Indicates the game scenario for the games to search.
     * @return A list of all the identifiers of games created.
     */
    public String[] getGames (String user, int idKakuro) {
        File f = new File(route + "/" + user + "/" + "kakuro_" + idKakuro);
        if (!f.exists()) return null;
        return f.list();
    }

    /**
     * This method asks for the information of a single game.
     * @param user Indicates the creator.
     * @param idKakuro Indicates the game scenario of the game.
     * @param idGame Indicates the id of the game to get.
     * @return The information of the field of the specified game.
     */
    public String[][] loadGame(String user, int idKakuro, int idGame){
        File f = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + ".txt");
        return dataKakuro.showKakuro(f);
    }

    /**
     * This method asks for the stats of a single game.
     * @param user Indicates the creator.
     * @param idKakuro Indicates the game scenario of the game.
     * @param idGame Indicates the identifier of the game to search for.
     * @return The stats of that specified game.
     */
    public String loadStats(String user, int idKakuro, int idGame) {
        File fStats = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + "_stats.txt");
        return dataKakuro.showGameStats(fStats);
    }

}
