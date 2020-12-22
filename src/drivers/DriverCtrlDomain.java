package drivers;

import domain.CtrlDomain;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverCtrlDomain {

    private static int f, c;
    private static String[][] kakuro;
    public static CtrlDomain ctrlDomain;
    public static void main(String[] args) {
        ctrlDomain = new CtrlDomain(null);
        iniExecution();
    }

    private static void iniExecution() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - create or enter user");
            System.out.println("2 - exit system");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\nEnter your user name:");
                    sca = new Scanner(System.in);
                    ctrlDomain.logInUser(sca.nextLine());
                    contExecution();
                    break;

                case 2:
                    exit = true;
                    break;
            }

        }
    }

    private static void contExecution(){
        boolean exit = false;
        while(!exit){
            System.out.println("\n1 - Create Kakuro");
            System.out.println("2 - Play Game");
            System.out.println("3 - Delete User");
            System.out.println("4 - Exit");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();

            switch (option) {
                case 1:
                    createKakuro();
                    break;
                case 2:
                    playGames();
                    break;
                case 3:
                    ctrlDomain.deleteUser();
                    exit = true;
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }


    private static void createKakuro(){
        boolean exit = false;
        while(!exit) {


            System.out.println("\n1 - Automatic Generation");
            System.out.println("2 - Propose Kakuro");
            System.out.println("3 - Exit");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();
            int col, fil;
            switch (option) {
                case 1:
                    System.out.println("\nGive the following info about the kakuro: width height diff(EASY, INTERMEDIATE or HARD) filledCells");
                    sca = new Scanner(System.in);
                    col = sca.nextInt();
                    fil = sca.nextInt();
                    String diff = sca.next();
                    int fc = sca.nextInt();
                    ctrlDomain.generateKakuro(col,fil,diff,fc);
                    System.out.println("Kakuro added to the collection");
                    exit = true;
                    break;
                case 2:
                    System.out.println("\nGive the following info about the kakuro: width height");
                    sca = new Scanner(System.in);
                    col = sca.nextInt();
                    fil = sca.nextInt();
                    String[][] kakuro = readKakuro();
                    if(ctrlDomain.proposeKakuro(fil, col, kakuro) != -1) { //falta asignar una dificultat
                        System.out.println("Kakuro added to the collection");
                        exit = true;
                    } else{
                        System.out.println("Error: Kakuro not valid");
                    }
                    break;
                case 3:
                    exit = true;
                    break;
            }
        }
    }

    private static void playGames(){
        boolean exit = false;
        while(!exit) {

            System.out.println("1 - Play");
            System.out.println("2 - Exit");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();

            switch (option) {
                case 1:
                    System.out.print("\nSelect the kakuro by its ID (from 1 to " + ctrlDomain.getKakurosGlobals()[ctrlDomain.getKakurosGlobals().length-1] + ")");
                    int k = sca.nextInt();
                    System.out.println("1 - Play new game");
                    System.out.println("2 - See games");
                    System.out.print("\nCHOOSE ONE OPTION: ");
                    option = sca.nextInt();

                    switch (option){
                        case 1:
                            ctrlDomain.createNewGame(k);
                            gameExecution(k);
                            break;
                        case 2:
                            ArrayList<Integer> games = ctrlDomain.getGames(k);
                            if (!games.isEmpty()){
                                for (int i=0; i<games.size(); ++i) games.get(i);

                                System.out.print("\nCHOOSE ONE GAME: ");
                                int idGame;
                                sca = new Scanner(System.in);
                                idGame = sca.nextInt();

                                System.out.println("1 - Continue game");
                                System.out.println("2 - Delete Game");

                                System.out.print("\nCHOOSE ONE OPTION: ");
                                sca = new Scanner(System.in);
                                option = sca.nextInt();


                                switch (option) {
                                    case 1:
                                        ctrlDomain.resumeGame(k,idGame);
                                        gameExecution(k);
                                        break;
                                    case 2:
                                        ctrlDomain.deleteGame(k,idGame,true);
                                        break;
                                }
                            }
                            break;
                    }
                    break;
                case 2:
                    exit = true;
                    break;
            }
        }
    }


    private static void gameExecution(int k) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - Fill cell");
            System.out.println("2 - Ask hint");
            System.out.println("3 - Save and exit");
            System.out.println("4 - Exit");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();
            switch (option) {
                case 1:
                    System.out.print("\nPLEASE INSERT THE SOLUTION TO CHECK: ");
                    String[][] solution = readKakuro();
                    ctrlDomain.validateGame(k,0,0,solution);
                    break;
                case 2:
                    System.out.print("\nPLEASE INSERT A FIELD: ");
                    String[][] field = readKakuro();
                    String returnValue = ctrlDomain.askHint(k,field);
                    System.out.println(returnValue);
                    break;
                case 3:
                    System.out.print("\nPLEASE INSERT THE NEW STATE: ");
                    String[][] newState = readKakuro();
                    ctrlDomain.saveGame(0, 0, newState);
                    exit = true;
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }

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
}
