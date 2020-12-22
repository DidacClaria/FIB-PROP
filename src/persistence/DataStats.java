package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataStats {

    public DataStats() {
    }

    public void updateStats (String [][] r,  FileWriter f) {
        try {
            for (int i = 0; i < r.length; ++i) {
                f.write("Kakuro: " + r[i][0] + ",Username: " + r[i][1] + ",Time_used: " + r[i][2] + ",Hints_asked: " + r[i][3] + ",Scores: " + r[i][4]);
            }
            f.close();
        }catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
    }

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
            System.out.println("\nError occurred during file reading");
        }
        return null;
    }

}
