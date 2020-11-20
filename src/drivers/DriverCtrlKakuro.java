package drivers;

import domain.CtrlKakuro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DriverCtrlKakuro {

    static CtrlKakuro ctrlKakuro;
    static Scanner scanner;
    static int f,c;
    static String[][] kakuro;

    public DriverCtrlKakuro() {
    }

    public static void main (String[] args){
        ctrlKakuro= new CtrlKakuro(null);
        scanner = new Scanner(System.in);
        f=c=0;
        kakuro = new String[f][c];
        showUsage();
        String s;
        try {
            while (!(s = scanner.nextLine()).equals("0")) {
                switch (s) {
                    case "1":
                        proposeValidKakuro();
                        break;
                    case "2":
                        proposeInvalidKakuro();
                        break;
                    case "3":
                        generateValidKakuro();
                        break;
                    case "4":
                        generateInvalidKakuro();
                        break;
                    case "5":
                        showUsage();
                        break;
                    case "6":
                        System.exit(0);
                        break;
                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }

    public static void readKakuro (String x) {
        File myObj = new File("../out/testFiles/kakuro"+x+".txt");
        Scanner sca = null;
        try {
            sca = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = sca.nextLine(); // Llegir quantes files i quantes columnes;

        String[] input = s.split (",");
        f = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        kakuro = new String[f][c];

        for (int i = 0; i<f; ++i) {
            s = sca.nextLine();
            String[] text = s.split (",");
            if (c >= 0) System.arraycopy(text, 0, kakuro[i], 0, c);
        }
    }

    public static void printKakuro () {
        String[][] aux = ctrlKakuro.listKakuro();
        System.out.println(f + "," + c);

        for (String[] strings : aux) {
            for (int j = 0; j < aux[0].length; ++j) {
                System.out.print(strings[j]);
                if (j != aux[0].length - 1) System.out.print(",");
            }
            System.out.println();
        }
    }

    private static void proposeValidKakuro() {
        System.out.println("In this method we will propose the kakuro from the statement:");
        readKakuro("Valid");
        ctrlKakuro.proposeKakuro(f,c,kakuro);
        printKakuro();
        System.out.println("We can see that the proposed Kakuro was valid.");
    }

    private static void proposeInvalidKakuro() {
        System.out.println("A Kakuro proposed may be invalid for different reasons, pick which one to test:");
        showInvalidPropositions();
        String s;
        s = scanner.nextLine();
        switch (s) {
            case "1":
                readKakuro("Small");
                try {
                    ctrlKakuro.proposeKakuro(f,c,kakuro);
                } catch (ArithmeticException e){
                    System.out.println("The method launched the following exception:");
                    e.printStackTrace();
                    System.out.println(e);
                }
                break;
        case "2":
                readKakuro("InvalidFormat");
                try {
                    ctrlKakuro.proposeKakuro(f,c,kakuro);
                } catch (ArithmeticException e){
                    System.out.println("The method launched the following exception:");
                    e.printStackTrace();
                    System.out.println(e);
                }
                break;
            case "3":
                readKakuro("NoSolution");
                try {
                    ctrlKakuro.proposeKakuro(f,c,kakuro);
                } catch (ArithmeticException e){
                    System.out.println("The method launched the following exception:");
                    e.printStackTrace();
                    System.out.println(e);
                }
                break;
        }
        System.out.println("We can see that the proposed Kakuro was invalid.");
    }

    private static void generateValidKakuro() {
        System.out.println("In this method we will generate a kakuro for different valid sizes, and we will evaluate the time it takes:");
        System.out.println("Press any button to continue...");
        scanner.nextLine();
        long startTime,endTime,timeElapsed;
        for (int i=3; i<10; ++i){
            startTime=System.currentTimeMillis();
            ctrlKakuro.generateKakuro(i,i);
            endTime=System.currentTimeMillis();
            timeElapsed = endTime - startTime;
            System.out.println("Execution "+i+" time in milliseconds: " + timeElapsed);
            System.out.println("Press any button to continue...");
            scanner.nextLine();
        }
    }

    private static void generateInvalidKakuro() {
        System.out.println("In this case we will try to generate a kakuro too small and another too big.");
        System.out.println("First we'll go with the small one (Press any button...)");
        scanner.nextLine();
        try {
            ctrlKakuro.generateKakuro(1,1);
        } catch (ArithmeticException e){
            System.out.println("The method launched the following exception:");
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("In second place we'll go with the small one (Press any button...)");
        scanner.nextLine();
        try {
            ctrlKakuro.generateKakuro(11,11);
        } catch (ArithmeticException e){
            System.out.println("The method launched the following exception:");
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private static void showInvalidPropositions(){
        System.out.println("###############################################");
        System.out.println("1. Kakuro with small size");
        System.out.println("2. Kakuro with invalid format");
        System.out.println("3. Kakuro with no solution");
        System.out.println("###############################################");
    }

    private static void showUsage() {
        System.out.println("###############################################");
        System.out.println("Driver of the CtrlKakuro class:");
        System.out.println("1. Propose a valid kakuro");
        System.out.println("2. Propose a invalid kakuro");
        System.out.println("3. Generate a valid kakuro");
        System.out.println("4. Generate an invalid kakuro");
        System.out.println("5. Show Usage");
        System.out.println("6. Exit");
        System.out.println("###############################################");
    }
}
