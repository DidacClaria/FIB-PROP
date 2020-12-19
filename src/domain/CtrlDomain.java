package domain;

import persistence.CtrlPersistence;
import presentation.CtrlPresentation;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Domain Controller Class. It is in charge of communicating with the layers of Presentation and Persistence, through their correspondent Controllers (CtrlPresentation and CtrlPersistence respectively) and to delegate use cases to their responsible.
 */
public class CtrlDomain {
    //ATTRIBUTES

    /**
     * Persistence Controller.
     */
    private final CtrlPersistence ctrlPersistence;

    /**
     * Presentation Controller.
     */
    private final CtrlPresentation ctrlPresentation;

    /**
     * User Controller. Used to manage and collect all the User use cases.
     */
    private final CtrlUser ctrlUser;

    /**
     * Game Controller. Used to manage and collect all the Game use cases.
     */
    private final CtrlGame ctrlGame;

    /**
     * Kakuro Controller. Used to manage and collect all the Kakuro use cases.
     */
    private final CtrlKakuro ctrlKakuro;

    //CONSTRUCTOR

    /**
     * Default Domain Controller constructor.
     */
    public CtrlDomain(CtrlPresentation ctrlPresentation) {
        //initialization
        this.ctrlPresentation = ctrlPresentation;
        ctrlPersistence = new CtrlPersistence(this);
        ctrlKakuro = new CtrlKakuro(this, dataKakuros());
        ArrayList<String> users = dataUsers();
        ctrlUser = new CtrlUser(this, users);
        ctrlGame = new CtrlGame(this);
        dataGame(users);

        //welcome to the system
        ini_execution();
    }


    private ArrayList<String[][]> dataKakuros(){
        return ctrlPersistence.loadKakuros();
    }

    private ArrayList<String> dataUsers(){
        return ctrlPersistence.loadUsers();
    }

    private void dataGame(ArrayList<String> users){
        for (String u: users){
            String[] kakuros = ctrlPersistence.startedKakuros(u);
            for (String k : kakuros){
                if (k != "personal_stats.txt"){
                    k = k.replace("kakuro_", "");
                    int idk = Integer.parseInt(k);
                    String[] games = ctrlPersistence.getGames(u, idk);
                    for (String g : games) {
                        String[][] game = ctrlPersistence.loadGame(u, idk, );
                    }
                }
            }

        }
    }

    //CLASS METHODS

    //primer panell
    private void ini_execution() {
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
                    logInUser(sca.nextLine());
                    cont_execution();
                    break;

                case 2:
                    exit = true;
                    break;
            }

        }
    }

    //segon panell
    private void cont_execution(){
        boolean exit = false;
        while(!exit){
            System.out.println("\n1 - Create Kakuro");
            System.out.println("2 - Play Game");
            System.out.println("3 - Personal Stats");
            System.out.println("4 - Global Ranking");
            System.out.println("5 - Delete User");
            System.out.println("6 - Exit");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();

            switch (option) {
                case 1:
                    create_kakuro();
                    break;
                case 2:
                    play_games();
                    break;
                case 3:
                    personal_stats();
                    break;
                case 4:
                    global_ranking();
                    break;
                case 5:
                    deleteUser(); //sense implementar del tot
                    exit = true;
                    break;
                case 6:
                    exit = true;
                    break;
            }
        }
    }

    // tercer panell
    private void create_kakuro(){
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
                    System.out.println("\nGive the following info about the kakuro: width height diff(1, 2 or 3) filled_cells");
                    sca = new Scanner(System.in);
                    col = sca.nextInt();
                    fil = sca.nextInt();
                    int diff = sca.nextInt();
                    int fc = sca.nextInt();
                    generateKakuro(fil, col, diff, fc); //falta la diff i les fc
                    System.out.println("Kakuro added to the collection");
                    exit = true;
                    break;
                case 2:
                    System.out.println("\nGive the following info about the kakuro: width height");
                    sca = new Scanner(System.in);
                    col = sca.nextInt();
                    fil = sca.nextInt();
                    String[][] kakuro = Main.readKakuro();
                    if(proposeKakuro(fil, col, kakuro) != -1) { //falta asignar una dificultat
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

    // cuart panell
    private void play_games(){
        boolean exit = false;
        while(!exit) {

            System.out.println("1 - Play");
            System.out.println("2 - Exit");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();

            switch (option) {
                case 1:
                    System.out.print("\nSelect the kakuro by its ID (from 1 to " + ctrlKakuro.listIdKakuro() + ")");
                    int k = sca.nextInt();
                    System.out.println("1 - Play new game");
                    System.out.println("2 - See games");
                    System.out.print("\nCHOOSE ONE OPTION: ");
                    option = sca.nextInt();

                    switch (option){
                        case 1:
                            playKakuro(ctrlUser.getActiveUser(), k);
                            game_execution();
                            break;
                        case 2:
                            seeGames(k);

                            System.out.println("1 - Continue game");
                            System.out.println("2 - Delete Game");

                            System.out.print("\nCHOOSE ONE OPTION: ");
                            sca = new Scanner(System.in);
                            option = sca.nextInt();

                            int id_game;
                            sca = new Scanner(System.in);
                            id_game = sca.nextInt();

                            switch (option) {
                                case 1:
                                    continueGame(id_game);
                                    game_execution();
                                    break;
                                case 2:
                                    deleteGame(id_game);
                                    break;
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

    // cinquè panell
    private void game_execution() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - Fill cell");
            System.out.println("2 - Ask hint");
            System.out.println("3 - Save and exit");
            System.out.println("2 - Exit");

            System.out.print("\nCHOOSE ONE OPTION: ");
            Scanner sca = new Scanner(System.in);
            int option = sca.nextInt();

            switch (option) {
                case 1:
                    fillCell();
                    //donada una posició (x,y) i un valor (v), s'omple la casella x,y amb v, retorna true si el joc esta completat
                    //es guarda el joc
                    break;
                case 2:
                    askHint();
                    //demanar pista random
                    break;
                case 3:
                    //guardar joc
                    saveGame(0, 0, null);
                    exit = true;
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }

    //sisè panell
    private void personal_stats(){
        listPersonalStats(); // no implementada
    }

    //sisè panell
    private void global_ranking(){
        listGlobalRanking(); // no implementada
    }

    /**
     * If the proposed Kakuro by the User at the Presentation Layer is valid, it is saved at the Persistence Layer and it's added to the collection of Kakuros that a User has.
     * @param numRows indicates the number of rows that the field has
     * @param numColumns indicates the number of columns that the field has
     * @param field it has the value of each cell divided by ",".
     * @return It returns either the id of the new proposed Kakuro or -1 if it is has no solution.
     */
    public int proposeKakuro(int numRows, int numColumns, String[][] field){
        int idKakuro = ctrlKakuro.proposeKakuro(numRows,numColumns,field);
        if (idKakuro!=-1) {
            ctrlPersistence.newKakuro(ctrlKakuro.listIdKakuro(), ctrlKakuro.listKakuro());
            return idKakuro;
        }
        return -1;
    }

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     */
    public int generateKakuro(int numRows, int numColumns, int diff, int fc){
        ctrlKakuro.generateKakuro(numRows, numColumns, diff, fc);
        ctrlPersistence.newKakuro(ctrlKakuro.listIdKakuro(), ctrlKakuro.listKakuro());
        return ctrlKakuro.listIdKakuro();
    }

    /**
     * Consultant function
     * @return It returns a matrix of the information of all the cells of the kakuro
     */
    public String [][] listKakuro () {
        return ctrlKakuro.listKakuro();
    }

    //WIP USE CASES

    /**
     * Function used to log in, if the user is not existed, the system will create by itself a new user with name introduced
     * @param name indicates the name of the new user
     */
    public void logInUser(String name){
        if (ctrlPersistence.createUser(name)) ctrlUser.createUser(name);
        else ctrlUser.setActiveUser(name);
    }

    public String getActiveUser() {
        return ctrlUser.getActiveUser();
    }

    public String[] getKakurosGlobals() {
        return ctrlPersistence.getKakurosGlobals();
    }

    public String[] getGames(String user, int id_game) {
        return ctrlPersistence.getGames(user, id_game);
    }



    /**
     *
     * @param user It indicates the user who is playing the game
     * @param idKakuro It indicates the game scenario
     */
    public void playKakuro (String user, int idKakuro) {
        if (ctrlPersistence.newGame(user, idKakuro)) ctrlGame.startKakuro(user, idKakuro);
    }

    public ArrayList<Integer> seeGames(int id_kakuro){
        return ctrlGame.getGames(id_kakuro);
    }

    public void continueGame(int id_game){
        ctrlGame.setActiveGame(id_game);
    }

    public void deleteGame (int id_game) {
        ctrlPersistence.deleteGame(ctrlUser.getActiveUser(), ctrlGame.getGame(id_game).get_kakuro_id(), id_game);
        ctrlGame.deleteGame(id_game);

    }

    public void fillCell(){

    }

    public void askHint(){

    }

    /**
     * Function used to save the current state of a game.
     */
    public void saveGame(int time, int hints, String [][] newState){
        ctrlPersistence.saveGame(ctrlUser.getActiveUser(), ctrlGame.getActiveGame().get_kakuro_id(), ctrlGame.getActiveGame().get_game_id(), time, hints, newState);
    }

    public void validateGame (int time, int hints, String [][] kakuro) {
        if (ctrlPersistence.validateCorrectnessGame(ctrlUser.getActiveUser(), ctrlGame.getActiveGame().get_kakuro_id(), ctrlGame.getActiveGame().get_game_id(), kakuro)) {
            int scores = (72000 - time);
            if (scores - (7200 * hints) > 0) scores -= (7200 * hints);
            else scores = 0;
            ctrlPersistence.updateStats(ctrlUser.getActiveUser(), ctrlGame.getActiveGame().get_kakuro_id(), time, hints, scores, true);
            ctrlPersistence.updateStats(ctrlUser.getActiveUser(), ctrlGame.getActiveGame().get_kakuro_id(), time, hints, scores, false);
        }
    }

    public void deleteUser () {
        ctrlPersistence.deleteUser(ctrlUser.getActiveUser());
    }



    /**
     * Consultant function of the ranking of punctuations that all the different users made in their games.
     */
    public String listGlobalRanking(){
        return ctrlPersistence.listRankingOrStats(null, true);
    }

    /**
     * Consultant function of the personal ranking of punctuations for one user from all his games.
     */
    public String listPersonalStats(){
        return ctrlPersistence.listRankingOrStats(ctrlUser.getActiveUser(), false);
    }
}
