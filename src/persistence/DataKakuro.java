package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataKakuro {




    public DataKakuro() {

    }



    /**
     * Saves a new kakuro
     */
    public boolean new_kakuro(FileWriter wr, FileWriter wr_sol, String [][] kakuro, File GlobalRanking){
        try {
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

            GlobalRanking.createNewFile();

            return true;

        } catch (IOException e){
            System.out.println("\nError occurred during file writing");
            return false;
        }
    }

    /**
     * returns the kakuro or the solution of the kakuro with ID = id
     */
    public String[][] show_kakuro(File k){
        try {
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
    public boolean new_game (File pathOri, File pathUser, File pathDes, FileWriter wr){
        try {
            if (pathOri.exists() && pathUser.exists()) {
                Files.copy(Paths.get(pathOri.getAbsolutePath()), Paths.get(pathDes.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
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
    public boolean save_game (FileWriter game_saved, FileWriter game_saved_stats, int time, int hints, String [][] kakuro){
        try {
            for (int i = 0; i < kakuro.length; i++) {
                for (int j = 0; j < kakuro[0].length; j++) {
                    if (kakuro[i][j].equals("?")) game_saved.write("0");
                    else game_saved.write(kakuro[i][j]);
                    if (j != kakuro[0].length - 1) game_saved.write(","); //separador d'elements
                    else game_saved.write(System.getProperty("line.separator")); //separador de files
                }
            }
            game_saved.close();

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
     *
     * @param solution
     * @param f
     * @param f_stats
     * @param kakuro
     * @return
     */
    public boolean validate_correctness_game (String [][] solution, File f, File f_stats, String [][] kakuro) {
        if (Arrays.deepEquals(solution, kakuro)) {
            f.delete();
            f_stats.delete();
            return true;
        }
        else return false;
    }

    /**
     *
     * @param f
     */
    public void delete_kakuro_dir (File f) {
        if (f.exists()) {
            String[] entries = f.list();
            for (String s : entries) {
                File currentFile = new File(f.getPath(), s);
                currentFile.delete();
            }
            f.delete();
        }
        else System.out.println("\nKakuro not existed");
    }

}
