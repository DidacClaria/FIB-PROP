package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStats {

    public DataStats() {
    }

    public void update_stats (String user, int id_kakuro, int time, int hints, int scores, File f, FileWriter wr) {
        try {
            Scanner sca = new Scanner(f);
            StringBuilder result = new StringBuilder();
            boolean found = false;
            if (!sca.hasNextLine())
                result = new StringBuilder("Kakuro " + id_kakuro + " --> " + user + " ,Time used: " + time + ", Hints asked: " + hints + ", Scores: " + scores);
            else {
                while (sca.hasNextLine()) {
                    String aux = sca.nextLine();
                    if (!found && aux.contains("Kakuro " + id_kakuro)) {
                        found = true;
                        int oldScore = Integer.parseInt(aux.substring(aux.indexOf("Scores: ") + 8));
                        if (scores > oldScore) {
                            result.append("Kakuro ").append(id_kakuro).append(" --> ").append(user).append(" ,Time used: ").append(time).append(", Hints asked: ").append(hints).append(", Scores: ").append(scores).append("\n");
                        }
                    } else result.append(aux).append("\n");
                }
                if (!found) {
                    result.append("Kakuro ").append(id_kakuro).append(" --> ").append(user).append(" ,Time used: ").append(time).append(", Hints asked: ").append(hints).append(", Scores: ").append(scores).append("\n");
                }
            }
            wr.write(result.toString());
            wr.close();

        }catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
    }

    public String list_Ranking_or_Stats (File s, String r){
        try {
            Scanner sca = new Scanner(s);
            boolean first = true;
            while (sca.hasNextLine()) {
                if (first) {
                    first = false;
                    r = sca.nextLine();
                } else r += ("\n" + sca.nextLine());
            }
            return r;
        } catch (IOException e){
            System.out.println("\nError occurred during file reading");
            return null;
        }
    }

}
