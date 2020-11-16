package drivers;

import domain.CtrlKakuro;

import java.util.Scanner;

public class DriverCtrlKakuro {

    static CtrlKakuro ctrlKakuro;
    static Scanner scanner;


    //TEST CONSTRUCTOR
    public void testConstructor(){
        this.ctrlKakuro = new CtrlKakuro();
    }

    //TEST GETTERS & SETTERS


    //TEST CLASS METHODS
    public void testGenerateKakuro(){

    }

    public void testProposeKakuro(){

    }

    //MAIN
    public static void main (String[] args){
        DriverCtrlKakuro driverCtrlKakuro = new DriverCtrlKakuro();
        driverCtrlKakuro.printUsage();
        String s = new String();
        try {
            while (!(s = driverCtrlKakuro.scanner.nextLine()).equals("0")){
                switch (s){
                    case "1":
                        driverCtrlKakuro.testConstructor();
                        break;
                    case "2":
                        driverCtrlKakuro.testGenerateKakuro();
                        break;
                    case "3":
                        driverCtrlKakuro.testProposeKakuro();
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printUsage(){
        System.out.println("Tests from the Driver of the CtrlKakuro class:");
        System.out.println("1. Create an empty CtrlKakuro.");
    }
}
