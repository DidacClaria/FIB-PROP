package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * This KakuroGrid class is a custom component that extends all the propierties of a JPanel and it is used to represent a Kakuro in the GUI. It contains KakuroCells.
 */
public class KakuroGrid extends JPanel {

    int numRows, numCols;
    String[][] field;

    /**
     * Default constructor of a KakuroGrid.
     * @param numRows Indicates the width of the field.
     * @param numCols Indicates the height of the field.
     * @param field It contains the information of all single cells.
     * @param enabled It indicates if the kakuro grid is editable or not.
     */
    public KakuroGrid (int numRows, int numCols, String[][] field, boolean enabled){
        super(new GridLayout(numRows,numCols));
        this.numRows=numRows;
        this.numCols=numCols;
        this.field = field;
        setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));
        for (int i= 0; i<numRows; ++i){
            for(int j=0; j<numCols; ++j){
                if (field == null) add(new KakuroCell(-1,i,j,enabled,this));
                else  {
                    String[] parts = field[i][j].split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
                    if (field[i][j].equals("?")) add(new KakuroCell(-1,i,j,enabled,this));
                    else if (field[i][j].equals("*")) add(new KakuroCell(-1,-1));
                    else if (field[i][j].startsWith("F")) add (new KakuroCell(-1,Integer.parseInt(parts[1])));
                    else if (parts[0].equals("C") && field[i][j].contains("F")) add (new KakuroCell(Integer.parseInt(parts[1]),Integer.parseInt(parts[3])));
                    else if (parts[0].equals("C")) add (new KakuroCell(Integer.parseInt(parts[1]),-1));
                    else add(new KakuroCell(Integer.parseInt(field[i][j]),i,j,enabled,this));
                }
            }
        }
    }

    /**
     * It returns the current state of the field from the GUI.
     * @return String matrix with all the information of the field.
     */
    public String[][] getFieldStatus (){
        return field;
    }

    /**
     * Setter operation used to keep updated the current state of the field attribute.
     * @param posX Indicates the position in the X-axis from the field.
     * @param posY Indicates the position in the Y-axis from the field.
     * @param value Indicates the value added by the user in the GUI.
     */
    public void setValueField(int posX, int posY, int value){
        try {
            field[posX][posY] = String.valueOf(value);
        }
        catch (Exception e){
//            System.out.println("Value: "+value+" not added at :"+posX+posY);
        }
    }
}
