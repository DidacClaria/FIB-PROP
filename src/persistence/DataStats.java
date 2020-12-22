package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class has the responsibility of scrapping through the stats data stored in the persistance layer. It communicates with the Persistance Controller.
 */
public class DataStats {

    /**
     * Default empty class constructor
     */
    public DataStats() {
    }

    /**
     * This method is in charge of writing in the file where are stored the stats of a game.
     */
    public void updateStats (String user, int idKakuro, int time, int hints, int scores, File f, FileWriter wr) {
        try {
            Scanner sca = new Scanner(f);
            StringBuilder result = new StringBuilder();
            boolean found = false;
            if (!sca.hasNextLine())
                result = new StringBuilder("Kakuro: " + idKakuro + ",Username: " + user + ",Time_used: " + time + ",Hints_asked: " + hints + ",Scores: " + scores);
            else {
                while (sca.hasNextLine()) {
                    String aux = sca.nextLine();
                    if (!found && aux.contains("Kakuro: " + idKakuro)) {
                        found = true;
                        int oldScore = Integer.parseInt(aux.substring(aux.indexOf("Scores: ") + 8));
                        if (scores > oldScore) {
                            result.append("Kakuro: ").append(idKakuro).append(",Username: ").append(user).append(",Time_used: ").append(time).append(",Hints_asked: ").append(hints).append(",Scores: ").append(scores).append("\n");
                        }
                    } else result.append(aux).append("\n");
                }
                if (!found) {
                    result.append("Kakuro: ").append(idKakuro).append(",Username: ").append(user).append(",Time_used: ").append(time).append(",Hints_asked: ").append(hints).append(",Scores: ").append(scores).append("\n");
                }
            }
            wr.write(result.toString());
            wr.close();

        }catch (IOException e){
//            System.out.println("\nError occurred during file writing");
        }
    }

    /**
     * This method is in charge of listing the ranking or stats.
     */
    public String[][] listRankingOrStats (File s){
        try {
            Path path = Paths.get(s.getPath());
            long count = Files.lines(path).count();
            if (count != 0) {
                String[][] result = new String[(int) count][5];
                Scanner sca = new Scanner(s);
                int i = 0;
                while (sca.hasNextLine()) {
                    String aux = sca.nextLine();
                    String [] aux2 = aux.split(",");
                    result[i][0] = aux2[0].substring(aux2[0].indexOf(" ")+1);
                    result[i][1] = aux2[1].substring(aux2[1].indexOf(" ")+1);
                    result[i][2] = aux2[2].substring(aux2[2].indexOf(" ")+1);
                    result[i][3] = aux2[3].substring(aux2[3].indexOf(" ")+1);
                    result[i][4] = aux2[4].substring(aux2[4].indexOf(" ")+1);
                    ++i;
                }
                return result;
            }
        } catch (IOException e){
//            System.out.println("\nError occurred during file reading");
        }
        return null;
    }

}
