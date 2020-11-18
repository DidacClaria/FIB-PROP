package drivers;

import domain.CtrlKakuro;

import java.util.Scanner;

public class DriverCtrlKakuro {

    static CtrlKakuro ctrlKakuro;
    static Scanner scanner;

    public DriverCtrlKakuro(){
        //this.ctrlKakuro = new CtrlKakuro();
    }

    public void testGenerateKakuro(){

    }

    public void testProposeKakuro(){

    }

    public static void main (String[] args){
        DriverCtrlKakuro driverCtrlKakuro = new DriverCtrlKakuro();
        driverCtrlKakuro.showUsage();
        String s;
        try {
            while (!(s = driverCtrlKakuro.scanner.nextLine()).equals("0")) {
                switch (s){
                    case "1":

                        break;
                    case "2":

                        break;
                    case "3":

                        break;
                    case "4":

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

    private static void showUsage() {
        System.out.println("###############################################");
        System.out.println("Driver of the CtrlKakuro class:");
        System.out.println("1. ");
        System.out.println("2. ");
        System.out.println("3. ");
        System.out.println("4. ");
        System.out.println("5. ");
        System.out.println("6. Exit");
        System.out.println("###############################################");
    }
}
