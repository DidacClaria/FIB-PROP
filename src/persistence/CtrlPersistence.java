package persistence;

import domain.CtrlDomain;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

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
        route = ".\\src\\persistence\\data";
        routek = route + "\\kakuros";
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

    /**
     *
     * @param id_kakuro
     * @param kakuro
     * @return
     */
    public boolean new_kakuro(int id_kakuro, String [][] kakuro){
        try{
            FileWriter wr = new FileWriter(routek + "\\" + "model_" + id_kakuro + ".txt");
            FileWriter wr_sol = new FileWriter(routek + "\\" + "model_" + id_kakuro + "_sol.txt");
            File GlobalRanking = new File (route + "\\" + "globalranking.txt");
            return dataKakuro.new_kakuro(wr, wr_sol, kakuro, GlobalRanking);
        } catch (IOException e){
            return false;
        }
    }

    /**
     *
     * @param id_kakuro
     * @param solution
     * @return
     */
    public String[][] show_kakuro(int id_kakuro, boolean solution){
        File k;
        if (solution) k = new File(routek + "\\" + "model_" + id_kakuro + "_sol.txt");
        else k = new  File(routek + "\\" + "model_" + id_kakuro + ".txt");
        return dataKakuro.show_kakuro(k);
    }

    /**
     *
     * @param user
     * @param id_kakuro
     * @return
     */
    public boolean new_game (String user, int id_kakuro){
        try{
            File pathOri = new File(routek + "\\" + "model_" + id_kakuro + ".txt");
            File pathUser = new File(route + "\\" + user);
            File kakuro = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro);

            int id_game = 0;
            if (!kakuro.exists()) kakuro.mkdir();
            String[] quantity = kakuro.list();
            id_game = quantity.length / 2 + 1;

            File pathDes = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + ".txt");
            FileWriter wr = new FileWriter(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" +"game_" + id_game + "_stats.txt");
            return dataKakuro.new_game(pathOri, pathUser, pathDes, wr);
        } catch (IOException e){
            return false;
        }

    }

    /**
     *
     * @param user
     * @param id_kakuro
     * @param id_game
     * @param time
     * @param hints
     * @param kakuro
     * @return
     */
    public boolean save_game (String user, int id_kakuro, int id_game, int time, int hints, String [][] kakuro){
        try{
            FileWriter game_saved = new FileWriter(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + ".txt");
            FileWriter game_saved_stats = new FileWriter(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" +"game_" + id_game + "_stats.txt");
            return dataKakuro.save_game(game_saved, game_saved_stats, time, hints, kakuro);
        } catch (IOException e){
            return false;
        }

    }

    /**
     *
     * @param user
     * @param id_kakuro
     * @param id_game
     * @param kakuro
     * @return
     */
    public boolean validate_correctness_game (String user, int id_kakuro, int id_game, String [][] kakuro){
        String [][] solution = show_kakuro(id_kakuro, true);
        File f = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + ".txt");
        File f_stats = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + "_stats.txt");
        return dataKakuro.validate_correctness_game(solution, f, f_stats, kakuro);
    }

    /**
     * @param user
     * @param id_kakuro
     * @param id_game
     */
    public void delete_game (String user, int id_kakuro, int id_game) {
        File f = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + ".txt");
        if (!f.exists()) {
            File dir = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro);
            String [] size = dir.list();
            if (size.length == 0) dir.delete();
        }
        else {
            f.delete();
            File stats = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + "_stats.txt");
            stats.delete();
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean create_user (String name){
        File user = new File(route + "\\" + name);
        File f = new File(route + "\\" + name + "\\" + "personal_stats.txt");
        return dataUser.create_user(user, f);
    }

    /**
     *
     * @param user
     */
    public void delete_user (String user) {
        File f = new File(route + "\\" + user);
        dataUser.delete_user(f);
    }

    /**
     *
     * @param user
     * @param id_kakuro
     * @param time
     * @param hints
     * @param scores
     * @param global
     */
    public void update_stats (String user, int id_kakuro, int time, int hints, int scores, boolean global) {
        try{
            File f;
            FileWriter wr;
            if (global){
                f = new File(route + "\\" + "global_ranking.txt");
                wr = new FileWriter(route + "\\" + "global_ranking.txt");
            }
            else{
                f = new File(route + "\\" + user + "\\" + "personal_stats.txt");
                wr = new FileWriter(route + "\\" + user + "\\" + "personal_stats.txt");
            }
            dataStats.update_stats(user, id_kakuro, time, hints, scores, f, wr);
        } catch (IOException e){

        }
    }

    /**
     *
     * @param user
     * @param global
     * @return
     */
    public String list_Ranking_or_Stats(String user, boolean global){
        File s;
        String r;
        if (global) {
            s = new File(route + "\\" + "global_ranking.txt");
            r = "NOBODY HAS PLAYED YET!";
        }
        else {
            s = new File(route + "\\" + user + "\\" + "personal_stats.txt");
            r = "YOU HAVE NOT DONE ANY KAKUROS!";
        }
        return dataStats.list_Ranking_or_Stats(s, r);
    }

    public String[] get_kakuros_globals () {
        File f = new File(routek);
        return f.list();
    }

    public String[] get_games (String user, int id_kakuro) {
        File f = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro);
        if (!f.exists()) return null;
        return f.list();
    }
}
