package drivers;

import domain.WhiteCell;
import java.util.Scanner;

public class DriverWhiteCell {
    static WhiteCell whitecell;
    static Scanner scanner;

    public static void main(String[] args){
        whitecell= new WhiteCell(0);
        scanner = new Scanner(System.in);
        showUsage();
        String s;
        try {
            while (!(s = scanner.nextLine()).equals("0")) {
                switch (s){
                    case "1":
                        createWhiteCell();
                        break;
                    case "2":
                        setWhiteCellValue();
                        break;
                    case "3":
                        getWhileCellValue();
                        break;
                    case "4":
                        doBlackCellMethods();
                        break;
                    case "5":
                        showUsage();
                        break;
                    case "6":
                        System.exit(0);
                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }

    private static void doBlackCellMethods() {
        System.out.println("First we will try to set 2 as the column value of a Black Cell");
        whitecell.setColumnValue(2);
        System.out.println("The setter was called and now with the getter we will see it's actual value: " + whitecell.getColumnValue());
        System.out.println("This happens because Cell is an abstract class and the behaviour of the used methods are overridden.");
    }

    private static void setWhiteCellValue() {
        int originalValue = whitecell.getValue();
        System.out.println("Insert new WhiteCell value: ");
        String newValue = scanner.nextLine();
        whitecell.setValue(Integer.parseInt(newValue));
        System.out.println("The WhiteCell with value: "+ originalValue + " now has value "+ newValue);
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
        System.out.println("New WhiteCell created with value: " + whitecell.getValue());
    }

    private static void showUsage() {
        System.out.println("###############################################");
        System.out.println("Driver of the WhiteCell class:");
        System.out.println("1. Create a new WhiteCell");
        System.out.println("2. Set new value to the WhiteCell");
        System.out.println("3. Get value of the WhiteCell");
        System.out.println("4. Try the BlackCell overridden methods");
        System.out.println("5. Show Usage");
        System.out.println("6. Exit");
        System.out.println("###############################################");
    }

}
