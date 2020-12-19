package drivers;

public class DriverToString{

    public static void main(String []args){
        String s = "9,9:*,*,C19,C12,*,*,*,C7,C10,*,F14,?,?,C4,C11,C17F4,?,?,*,C7F36,?,?,?,?,?,?,?,F12,?,?,F10,?,?,?,C25,C14,F3,?,?,C20,C11F20,?,?,?,?,F17,?,?,?,?,C8,F6,?,?,*,C11,C7F13,?,?,?,C4F10,?,?,F28,?,?,?,?,?,?,?,*,F6,?,?,*,*,F8,?,?,*";
        stringToKakuroGrid(s);
    }

    public static void stringToKakuroGrid(String sizeAndField) {
        String[] parts = sizeAndField.split(":");
        String[] size = parts[0].split(",");
        int numRows = Integer.parseInt(size[0]);
        int numCols = Integer.parseInt(size[1]);
        String[][] kakuroField = new String[numRows][numCols];
        String[] field = parts[1].split(",");
        for (int i = 0; i<numRows; ++i) {
            for (int j=0; j<numCols; ++j){
                kakuroField[i][j] = field[i*numCols+j];
            }
        }
        for (int i=0; i<numRows; ++i){
            for (int j=0; j<numCols; ++j){
                System.out.println(kakuroField[i][j]);
            }
        }
    }
}