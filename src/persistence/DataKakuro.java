package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class has the responsibility of scrapping through the kakuro data stored in the persistence layer. It communicates with the Persistence Controller.
 */
public class DataKakuro {


    /**
     * Default empty class constructor
     */
    public DataKakuro() {

    }



    /**
     * Saves a new kakuro
     */
    public boolean newKakuro(FileWriter wr, FileWriter wrSol, String [][] kakuro, File GlobalRanking){
        try {
            wr.write(kakuro.length + "," + kakuro[0].length + "\n");
            wrSol.write(kakuro.length + "," + kakuro[0].length + "\n");
            for (int i = 0; i < kakuro.length; i++) {
                for (int j = 0; j < kakuro[0].length; j++) {
                    if (kakuro[i][j].length() == 1 && kakuro[i][j] != "*") wr.write("0");
                    else wr.write(kakuro[i][j]);
                    wrSol.write(kakuro[i][j]);
                    if (j != kakuro[0].length - 1) {//separador d'elements
                        wr.write(",");
                        wrSol.write(",");
                    }
                    else {
                        wr.write(System.getProperty("line.separator")); //separador de files
                        wrSol.write(System.getProperty("line.separator"));
                    }
                }
            }
            wr.close();
            wrSol.close();

            GlobalRanking.createNewFile();

            return true;

        } catch (IOException ignored){
            return false;
        }
    }

    /**
     * Returns the kakuro or the solution of the kakuro with ID = id
     */
    public String[][] showKakuro(File k){
        try {
            if (k.exists()) {
                String [][] kakuro;
                String aux;
                ArrayList<String> text = new ArrayList<>();

                Scanner mr = new Scanner(k);
                if(mr.hasNextLine()) mr.nextLine();
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
                return null;
            }
        } catch (IOException ignored){
        }
        return null;
    }

    /**
     * Starts a new game for User = user
     */
    public boolean newGame (File pathOri, File pathUser, File pathDes, FileWriter wr){
        try {
            if (pathOri.exists() && pathUser.exists()) {
                Files.copy(Paths.get(pathOri.getAbsolutePath()), Paths.get(pathDes.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                wr.write("Execution Time: 0\n");
                wr.write("Hints asked: 0\n");
                wr.close();
                return true;
            }
        } catch (IOException ignored){
        }
        return false;
    }

    /**
     * Saves the state of a game in current of the user
     */
    public boolean saveGame (FileWriter gameSaved, FileWriter gameSavedStats, int time, int hints, String [][] kakuro){
        try {
            gameSaved.write(kakuro.length +","+kakuro[0].length);
            gameSaved.write(System.getProperty("line.separator"));
            for (int i = 0; i < kakuro.length; i++) {
                for (int j = 0; j < kakuro[0].length; j++) {
                    if (kakuro[i][j].equals("?")) gameSaved.write("0");
                    else gameSaved.write(kakuro[i][j]);
                    if (j != kakuro[0].length - 1) gameSaved.write(","); //separador d'elements
                    else gameSaved.write(System.getProperty("line.separator")); //separador de files
                }
            }
            gameSaved.close();

            gameSavedStats.write("Execution Time: " + time + "\n");
            gameSavedStats.write("Hints asked: " + hints + "\n");
            gameSavedStats.close();

            return true;
        } catch (IOException ignored){
        }
        return false;
    }

//    /**
//     *
//     * @param solution
//     * @param f
//     * @param fStats
//     * @param kakuro
//     * @return
//     */
//    public boolean validateCorrectnessGame (String [][] solution, File f, File fStats, String [][] kakuro) {
//        if (Arrays.deepEquals(solution, kakuro)) {
//            f.delete();
//            fStats.delete();
//            return true;
//        }
//        else return false;
//    }

    /**
     * This method returns all the game stats of a single game.
     */
    public String showGameStats(File fStats) {
        try {
            if (fStats.exists()) {
                Scanner mr = new Scanner(fStats);
                String time = "0";
                String hints = "0";
                if(mr.hasNextLine()) time = mr.nextLine();
                if(mr.hasNextLine()) hints = mr.nextLine();
                return time + ":" + hints;
            }
        } catch (IOException ignored) {

        }
        return null;
    }

}
