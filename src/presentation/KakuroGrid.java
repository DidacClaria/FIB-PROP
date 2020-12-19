package presentation;

import javax.swing.*;
import java.awt.*;

public class KakuroGrid extends JPanel {

    int numRows, numCols;
    String[][] field;

    public KakuroGrid (int numRows, int numCols, String[][] field, boolean enabled){
        super(new GridLayout(numRows,numCols));
        this.numRows=numRows;
        this.numCols=numCols;
        this.field=field;
        setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));
        for (int i= 0; i<numRows; ++i){
            for(int j=0; j<numCols; ++j){
                if (field == null) add(new KakuroCell(-1,enabled));
                else  {
                    String[] parts = field[i][j].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
                    if (field[i][j].equals("?")) add(new KakuroCell(-1,enabled));
                    else if (field[i][j].equals("*")) add(new KakuroCell(-1,-1));
                    else if (field[i][j].startsWith("F")) add (new KakuroCell(-1,Integer.parseInt(parts[1])));
                    else if (parts[0].equals("C") && field[i][j].contains("F")) add (new KakuroCell(Integer.parseInt(parts[1]),Integer.parseInt(parts[3])));
                    else if (parts[0].equals("C")) add (new KakuroCell(Integer.parseInt(parts[1]),-1));
                    else add(new KakuroCell(Integer.parseInt(field[i][j]),enabled));
                }
            }
        }
    }

    public String[][] getField() {
        return field;
    }
}
