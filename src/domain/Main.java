package domain;

import presentation.CtrlPresentation;

import java.util.Scanner;

/**
 * Main class. We will use it in order to simulate an user from the presentation layer sending their actions and data.
 */
public class Main {

    private static int f, c;
    private static String[][] kakuro;
    private static CtrlDomain a;

    public static void main(String[] args) {
        CtrlPresentation ctrlPresentation = new CtrlPresentation();
        a = new CtrlDomain(ctrlPresentation);
        String user;
        int idKakuro;
        int idGame;
        int time;
        int hints;

        while (true){
            System.out.println("\n1 - Propose Kakuro");
            System.out.println("2 - Generate Kakuro");
            System.out.println("3 - Create User");
            System.out.println("4 - Start new game");
            System.out.println("5 - Save game");
            System.out.println("6 - Validate game");
            System.out.println("7 - Eliminate User");
            System.out.println("8 - Eliminate Kakuro");

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
                    a.generateKakuro(f,c, 0, 0);
                    printKakuro();
                    break;
                case 3:
                    System.out.println("\nEnter your user name:");
                    sca = new Scanner(System.in);
                    a.logInUser(sca.nextLine());
                    break;
                case 4:
                    System.out.println("This version is for testing not for a real game :)");
                    System.out.println("REMEMBER CREATE A USER AND A KAKURO BEFORE!! :)");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Enter the user name:");
                    sca = new Scanner(System.in);
                    user = sca.nextLine();
                    System.out.println("Enter the id of kakuro:");
                    idKakuro = sca.nextInt();
                    a.createNewGame(idKakuro);
                    break;
                case 5:
                    System.out.println("REMEMBER START A GAME BEFORE!! :)");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Enter the user name:");
                    sca = new Scanner(System.in);
                    user = sca.nextLine();
                    System.out.println("Enter the id of kakuro:");
                    idKakuro = sca.nextInt();
                    System.out.println("Enter the id of game:");
                    idGame = sca.nextInt();
                    System.out.println("Enter the execution time:");
                    time = sca.nextInt();
                    System.out.println("Enter the number of hints:");
                    hints = sca.nextInt();
                    System.out.println("Enter the new state of the game (THIS IS ONLY FOR TESTING):");
                    readKakuro();
                    a.saveGame(user, idKakuro, idGame, time, hints, kakuro);
                    break;
                case 6:
                    System.out.println("Enter the user name:");
                    sca = new Scanner(System.in);
                    user = sca.nextLine();
                    System.out.println("Enter the id of kakuro:");
                    idKakuro = sca.nextInt();
                    System.out.println("Enter the id of game:");
                    idGame = sca.nextInt();
                    System.out.println("Enter the execution time:");
                    time = sca.nextInt();
                    System.out.println("Enter the number of hints:");
                    hints = sca.nextInt();
                    System.out.println("Enter the new state of the game (THIS IS ONLY FOR TESTING):");
                    readKakuro();
                    a.validateGame(idKakuro, idGame, time, hints, kakuro);
                    break;
                case 7:
                    System.out.println("Enter the user name you want to remove:");
                    sca = new Scanner(System.in);
                    user = sca.nextLine();
                    a.deleteUser();
                    break;
                case 8:
                    System.out.println("Enter the user name you want to remove:");
                    sca = new Scanner(System.in);
                    user = sca.nextLine();
                    System.out.println("Enter the id of kakuro you want to remove:");
                    idKakuro = sca.nextInt();
                    System.out.println("Enter the id of game you want to remove:");
                    idGame = sca.nextInt();
                    a.deleteGame(idGame);
                    break;
            }
        }
    }

    /**
     * Auxiliar method used to read the kakuro format
     */
    public static String[][] readKakuro () {
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
        return kakuro;
    }

    /**
     * Auxiliar method used to print or represent the kakuro in console
     */
    public static void printKakuro () {
//        String[][] aux = a.listKakuro();
//        System.out.println(f + "," + c);
//
//        for (int i = 0; i < aux.length; ++i) {
//            for (int j = 0; j <aux[0].length; ++j){
//                System.out.print(aux[i][j]);
//                if (j != aux[0].length - 1) System.out.print(",");
//            }
//            System.out.println();
//        }
    }

}
