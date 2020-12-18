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

    public ArrayList<String> show_stats (File s){
        try {
            if (s.exists()) {
                ArrayList<String> stats = new ArrayList<>();

                Scanner mr = new Scanner(s);
                while (mr.hasNextLine()) stats.add(mr.nextLine());
                mr.close();
                return stats;
            }
            else {
                return null;
            }
        } catch (IOException e){
            System.out.println("\nError occurred during file reading");
            return null;
        }
    }

}
