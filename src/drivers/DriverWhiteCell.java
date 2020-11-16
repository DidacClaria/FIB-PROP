package drivers;

import domain.WhiteCell;
import java.util.Scanner;

public class DriverWhiteCell {
    static WhiteCell whitecell;
    static Scanner scanner;

    public DriverWhiteCell(){
        this.whitecell= new WhiteCell(0);
        this.scanner = new Scanner(System.in);
    }

    public static void main(String args[]){
        DriverWhiteCell driverWhiteCell= new DriverWhiteCell();
        driverWhiteCell.showUsage();
        String s;
        try {
            while (!(s = driverWhiteCell.scanner.nextLine()).equals("0")) {
                switch (s){
                    case "1":
                        createWhiteCell();
                        break;
                    case "2":
                        getWhileCellValue();
                        break;
                    case "3":
                        setWhiteCellValue();
                        break;
                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }

    private static void setWhiteCellValue() {
        System.out.println("Insert new WhiteCell value: ");
        String newValue = scanner.nextLine();
        whitecell.setValue(Integer.parseInt(newValue));
    }

    private static void getWhileCellValue() {
        if(whitecell.getValue()!=0){
            System.out.println("Value of the Whitcell: " + whitecell.getValue());
        }
        else {System.out.println("Empty WhiteCell");}
    }

    private static void createWhiteCell() {
        System.out.println("Insert value for the new WhiteCell: ");
        String str = scanner.nextLine();
        whitecell = new WhiteCell(Integer.parseInt(str));
    }

    private void showUsage() {
        System.out.println("###############################################");
        System.out.println("Driver of the WhiteCell class:");
        System.out.println("1. Create a new WhiteCell");
        System.out.println("2. Set new value to the WhiteCell");
        System.out.println("3. Get value of the WhiteCell");
        System.out.println("4. Exit");
        System.out.println("###############################################");
    }

}
