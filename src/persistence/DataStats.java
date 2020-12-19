package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataStats {

    public DataStats() {
    }

    public void updateStats (String user, int idKakuro, int time, int hints, int scores, File f, FileWriter wr) {
        try {
            Scanner sca = new Scanner(f);
            StringBuilder result = new StringBuilder();
            boolean found = false;
            if (!sca.hasNextLine())
                result = new StringBuilder("Kakuro " + idKakuro + " --> " + user + " ,Time used: " + time + ", Hints asked: " + hints + ", Scores: " + scores);
            else {
                while (sca.hasNextLine()) {
                    String aux = sca.nextLine();
                    if (!found && aux.contains("Kakuro " + idKakuro)) {
                        found = true;
                        int oldScore = Integer.parseInt(aux.substring(aux.indexOf("Scores: ") + 8));
                        if (scores > oldScore) {
                            result.append("Kakuro ").append(idKakuro).append(" --> ").append(user).append(" ,Time used: ").append(time).append(", Hints asked: ").append(hints).append(", Scores: ").append(scores).append("\n");
                        }
                    } else result.append(aux).append("\n");
                }
                if (!found) {
                    result.append("Kakuro ").append(idKakuro).append(" --> ").append(user).append(" ,Time used: ").append(time).append(", Hints asked: ").append(hints).append(", Scores: ").append(scores).append("\n");
                }
            }
            wr.write(result.toString());
            wr.close();

        }catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
    }

    public String listRankingOrStats (File s, String r){
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
