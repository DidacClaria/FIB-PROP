package domain;

import java.util.Scanner;

/**
 * Main class. We will use it in order to simulate an user from the presentation layer sending their actions and data.
 */
public class Main {

    public static int f, c;
    public static String[][] kakuro;
    public static CtrlDomain a;

    public static void main(String[] args) {
        a = new CtrlDomain();
        while (true){
            System.out.println("\n1 - Propose Kakuro");
            System.out.println("2 - Generate Kakuro");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\nEnter your kakuro:");
                    readKakuro();
                    a.proposeKakuro(f,c,kakuro);
                    printKakuro();
                    break;
                case 2:
                    System.out.println("\nEnter the size of kakuro you want to generate:");
                    f = sca.nextInt();
                    c = sca.nextInt();
                    a.generateKakuro(f,c);
                    printKakuro();
                    break;
            }
        }
    }

    public static void readKakuro () {
        Scanner sca = new Scanner(System.in);
        String s = sca.nextLine(); // Llegir quantes files i quantes columnes;

        String[] input = s.split (",");
        f = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        kakuro = new String[f][c];

        for (int i = 0; i<f; ++i) {
            s = sca.nextLine();
            String[] text = s.split (",");
            for (int j=0; j<c; ++j) {
                kakuro[i][j] = text[j];
            }
        }
    }

    public static void printKakuro () {
        String aux [][] = a.listKakuro();
        System.out.println(f + "," + c);

        for (int i = 0; i < aux.length; ++i) {
            for (int j = 0; j <aux[0].length; ++j){
                System.out.print(aux[i][j]);
                if (j != aux[0].length - 1) System.out.print(",");
            }
            System.out.println();
        }
    }

}
