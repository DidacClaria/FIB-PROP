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
    private String route;
    private String routek;

    //CONSTRUCTOR

    /**
     * Default empty Persistence Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlPersistence(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
        route = "D:\\PROP\\src\\persistence\\data";
        routek = route + "\\kakuros";
        InitializePersistance();

    }

    /**
     * Initializes the data folders
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
     * Saves a new user
     */
    public boolean create_user(String name) {
        try {
            File user = new File(route + "\\" + name);
            if (!user.exists()) {
                if (user.mkdir()) {
                    File f = new File(route + "\\" + name + "\\" + "personal_stats.txt");
                    f.createNewFile();
                    return true;
                } else System.out.println("User directory not created due an error");
            } else System.out.println("\nUser existed!");
        } catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
        return false;
    }

    /**
     * Returns true if a user exists
     */
    public boolean exists_user(String name){
        File user = new File(route + "\\" + name);
        if (user.exists()) return true;
        return false;
    }

    /**
     * Saves a new kakuro
     */
    public boolean new_kakuro(int id_kakuro, String [][] kakuro){
        try {
            FileWriter wr = new FileWriter(routek + "\\" + "model_" + id_kakuro + ".txt");
            FileWriter wr_sol = new FileWriter(routek + "\\" + "model_" + id_kakuro + "_sol.txt");
            for (int i = 0; i < kakuro.length; i++) {
                for (int j = 0; j < kakuro[0].length; j++) {
                    if (kakuro[i][j].length() == 1 && kakuro[i][j] != "*") wr.write("0");
                    else wr.write(kakuro[i][j]);
                    wr_sol.write(kakuro[i][j]);
                    if (j != kakuro[0].length - 1) {//separador d'elements
                        wr.write(",");
                        wr_sol.write(",");
                    }
                    else {
                        wr.write(System.getProperty("line.separator")); //separador de files
                        wr_sol.write(System.getProperty("line.separator"));
                    }
                }
            }
            wr.close();
            wr_sol.close();
            File GlobalRanking = new File (route + "\\" + "globalranking.txt");
            GlobalRanking.createNewFile();

            return true;

        } catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
        return false;
    }

    /**
     * returns the kakuro or the solution of the kakuro with ID = id
     */
    public String[][] show_kakuro(int id, boolean solution){
        try {
            File k;
            if (solution) k = new File(routek + "\\" + "model_" + id + "_sol.txt");
            else k = new  File(routek + "\\" + "model_" + id + ".txt");

            if (k.exists()) {
                String [][] kakuro;
                String aux;
                ArrayList<String> text = new ArrayList<>();

                Scanner mr = new Scanner(k);
                while (mr.hasNextLine()) text.add(mr.nextLine());
                mr.close();

                kakuro = new String[text.size()][];

                for (int i = 0; i<text.size(); ++i) {
                    aux = text.get(i);
                    kakuro[i] = aux.split(",");
                }
                return kakuro;
            }
            else {
                System.out.println("Kakuro not existed");
                return null;
            }
        } catch (IOException e){
            System.out.println("\nError occurred during file reading");
        }
        return null;
    }

    /**
     * Starts a new game for User = user
     */
    public boolean new_game (String user, int id_kakuro){
        try {
            File pathOri = new File(routek + "\\" + "model_" + id_kakuro + ".txt");
            if (pathOri.exists()) {
                File kakuro = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro);
                int id_game = 0;

                if (!kakuro.exists()) kakuro.mkdir();

                String[] quantity = kakuro.list();
                if (quantity.length == 0) id_game = 1;
                else id_game = (quantity.length + 2) / 2;

                File pathDes = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + ".txt");
                Files.copy(Paths.get(pathOri.getAbsolutePath()), Paths.get(pathDes.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                FileWriter wr = new FileWriter(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + "_stats.txt");
                wr.write("Execution Time: 0");
                wr.write(System.getProperty("line.separator"));
                wr.write("Hints asked: 0");
                wr.write(System.getProperty("line.separator"));
                wr.close();
            }
            else System.out.println("\nThe kakuro of the game or the user is not existed!!!");
        } catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
        return false;
    }

    /**
     * Saves the state of a game in current of the user
     */
    public boolean save_game (String user, int id_kakuro, int id_game, int time, int hints, String [][] kakuro){
        try {
            FileWriter game_saved = new FileWriter(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + ".txt");
            for (int i = 0; i < kakuro.length; i++) {
                for (int j = 0; j < kakuro[0].length; j++) {
                    if (kakuro[i][j].equals("?")) game_saved.write("0");
                    else game_saved.write(kakuro[i][j]);
                    if (j != kakuro[0].length - 1) game_saved.write(","); //separador d'elements
                    else game_saved.write(System.getProperty("line.separator")); //separador de files
                }
            }
            game_saved.close();

            FileWriter game_saved_stats = new FileWriter(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + "_stats.txt");
            game_saved_stats.write("Execution Time: " + time);
            game_saved_stats.write(System.getProperty("line.separator"));
            game_saved_stats.write("Hints asked: " + hints);
            game_saved_stats.write(System.getProperty("line.separator"));
            game_saved_stats.close();

            return true;
        } catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
        return false;
    }

    /**
     * returns the game of User = user with ID = id_game
     */
    /*
    public String[][] load_game (String user, int id_kakuro){
        int i = 0;
        while (m)

        String [][]
        try {
            File k = new File(route + "\\" + user + "\\" + id_kakuro + "_sol.txt");
            if (k.exists()) {
                Scanner mr = new Scanner(k);
                int i = 0;
                while (mr.hasNextLine()){
                    String aux = mr.nextLine();
                    kakuro[i] = aux.split(",");
                    ++i;
                }
                mr.close();
            }
        } catch (IOException e){
            System.out.println("\nError occurred during file reading");
        }
        return kakuro;
    }
*/

    public boolean validate_correctness_game (String user, int id_kakuro, int id_game, String [][] kakuro) {
        String [][] solution = show_kakuro(id_kakuro, true);
        if (Arrays.deepEquals(solution, kakuro)) {
            File f = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + ".txt");
            File f_stats = new File(route + "\\" + user + "\\" + "kakuro_" + id_kakuro + "\\" + "game_" + id_game + "_stats.txt");
            f.delete();
            f_stats.delete();
            return true;
        }
        else return false;
    }


    public void update_stats (String user, int id_kakuro, int time, int hints, int scores, boolean global) {
        try {
            File f;
            if (global) f = new File(route + "\\" + "globalranking.txt");
            else f = new File(route + "\\" + user + "\\" + "personal_stats.txt");
            Scanner sca = new Scanner(f);
            String result = "";
            String aux;
            boolean found = false;
            while (sca.hasNextLine()) {
                aux = sca.nextLine();
                if (!found && aux.contains("Kakuro " + id_kakuro)) {
                    found = true;
                    int old_score = Integer.parseInt(aux.substring(aux.indexOf("Scores: ") + 8));
                    if (scores > old_score) {
                        result += "#" + "Kakuro " + id_kakuro + " --> " + user + " ,Time used: " + time + ", Hints asked: " + hints + ", Scores: " + scores;
                    }
                } else result += "#" + aux;
            }
            if (!found)
                result += "#" + "Kakuro " + id_kakuro + " --> " + user + " ,Time used: " + time + ", Hints asked: " + hints + ", Scores: " + scores;
            FileWriter wr = new FileWriter(route + "\\" + "globalranking.txt");
            String[] text = result.split("#");
            for (String s : text) {
                if (!s.equals("")) {
                    wr.write(s);
                    wr.write(System.getProperty("line.separator"));
                }
            }
            wr.close();

        }catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
    }

}
