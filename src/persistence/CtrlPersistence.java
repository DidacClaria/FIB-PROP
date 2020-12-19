package persistence;

import domain.CtrlDomain;
import domain.Pair;

import java.io.*;
import java.util.ArrayList;

/**
 * Persistence Controller Class.
 */
public class CtrlPersistence {
    //ATTRIBUTES

    /**
     * Domain Controller.
     */
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
     *
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


    public ArrayList<String[][]> loadKakuros(){
        ArrayList<String[][]> kakuros = new ArrayList<>();
        File f = new File(routek);
        for( String k : f.list()){
           File kakuro = new File(routek + "/" + k);
           kakuros.add(dataKakuro.showKakuro(kakuro));
        }
        return kakuros;
    }

    public ArrayList<String> loadUsers(){
        ArrayList<String> users = new ArrayList<>();
        File f = new File(route);
        for (String u : f.list()){
            if (u != "kakuros" && u != "global_ranking.txt"){
                users.add(u);
            }
        }
        return users;
    }

    public String[] startedKakuros(String user){
        File f = new File(route + "/" + user);
        return f.list();
    }

    /**
     *
     * @param idKakuro
     * @param kakuro
     * @return
     */
    public boolean newKakuro(int idKakuro, String [][] kakuro){
        try{
            FileWriter wr = new FileWriter(routek + "/" + "model_" + idKakuro + ".txt");
            FileWriter wrSol = new FileWriter(routek + "/" + "model_" + idKakuro + "_sol.txt");
            File GlobalRanking = new File (route + "/" + "globalranking.txt");
            return dataKakuro.newKakuro(wr, wrSol, kakuro, GlobalRanking);
        } catch (IOException e){
            return false;
        }
    }

    /**
     *
     * @param idKakuro
     * @param solution
     * @return
     */
    public String[][] showKakuro(int idKakuro, boolean solution){
        File k;
        if (solution) k = new File(routek + "/" + "model_" + idKakuro + "_sol.txt");
        else k = new  File(routek + "/" + "model_" + idKakuro + ".txt");
        return dataKakuro.showKakuro(k);
    }

    /**
     *
     * @param user
     * @param idKakuro
     * @return
     */
    public boolean newGame (String user, int idKakuro){
        try{
            File pathOri = new File(routek + "/" + "model_" + idKakuro + ".txt");
            File pathUser = new File(route + "/" + user);
            File kakuro = new File(route + "/" + user + "/" + "kakuro_" + idKakuro);

            int idGame = 0;
            if (!kakuro.exists()) kakuro.mkdir();
            String[] quantity = kakuro.list();
            idGame = quantity.length / 2 + 1;

            File pathDes = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + ".txt");
            FileWriter wr = new FileWriter(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" +"game_" + idGame + "_stats.txt");
            return dataKakuro.newGame(pathOri, pathUser, pathDes, wr);
        } catch (IOException e){
            return false;
        }

    }

    /**
     *
     * @param user
     * @param idKakuro
     * @param idGame
     * @param time
     * @param hints
     * @param kakuro
     * @return
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

    /**
     *
     * @param user
     * @param idKakuro
     * @param idGame
     * @param kakuro
     * @return
     */
    public boolean validateCorrectnessGame (String user, int idKakuro, int idGame, String [][] kakuro){
        String [][] solution = showKakuro(idKakuro, true);
        File f = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + ".txt");
        File fStats = new File(route + "/" + user + "/" + "kakuro_" + idKakuro + "/" + "game_" + idGame + "_stats.txt");
        return dataKakuro.validateCorrectnessGame(solution, f, fStats, kakuro);
    }

    /**
     * @param user
     * @param idKakuro
     * @param idGame
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
     *
     * @param name
     * @return
     */
    public boolean createUser (String name){
        File user = new File(route + "/" + name);
        File f = new File(route + "/" + name + "/" + "personal_stats.txt");
        return dataUser.createUser(user, f);
    }

    /**
     *
     * @param user
     */
    public void deleteUser (String user) {
        File f = new File(route + "/" + user);
        dataUser.deleteUser(f);
    }

    /**
     *
     * @param user
     * @param idKakuro
     * @param time
     * @param hints
     * @param scores
     * @param global
     */
    public void updateStats (String user, int idKakuro, int time, int hints, int scores, boolean global) {
        try{
            File f;
            FileWriter wr;
            if (global){
                f = new File(route + "/" + "global_ranking.txt");
                wr = new FileWriter(route + "/" + "global_ranking.txt");
            }
            else{
                f = new File(route + "/" + user + "/" + "personal_stats.txt");
                wr = new FileWriter(route + "/" + user + "/" + "personal_stats.txt");
            }
            dataStats.updateStats(user, idKakuro, time, hints, scores, f, wr);
        } catch (IOException e){

        }
    }

    /**
     *
     * @param user
     * @param global
     * @return
     */
    public String listRankingOrStats(String user, boolean global){
        File s;
        String r;
        if (global) {
            s = new File(route + "/" + "global_ranking.txt");
            r = "NOBODY HAS PLAYED YET!";
        }
        else {
            s = new File(route + "/" + user + "/" + "personal_stats.txt");
            r = "YOU HAVE NOT DONE ANY KAKUROS!";
        }
        return dataStats.listRankingOrStats(s, r);
    }

    public String[] getKakurosGlobals () {
        File f = new File(routek);
        return f.list();
    }

    public String[] getGames (String user, int idKakuro) {
        File f = new File(route + "/" + user + "/" + "kakuro_" + idKakuro);
        if (!f.exists()) return null;
        return f.list();
    }

    public String[][] loadGame(String user, int id_kakuro, int id_game){
        return null;
    }

    public String loadStats(String u, int idk, int idg){
        return null;
    }

}
