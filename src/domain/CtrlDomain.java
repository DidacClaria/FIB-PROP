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

        String[][] r = ctrlPersistence.listRankingOrStats("", true);
        if (r != null) {
            for (int i = 0; i < r.length; ++i) {
                ctrlGame.loadRanking(Integer.parseInt(r[i][0]), r[i][1], Integer.parseInt(r[i][2]), Integer.parseInt(r[i][3]), Integer.parseInt(r[i][4]));
                ctrlUser.setGSuser(r[i][1], 1);
            }
        }
    }


        //welcome to the system
//        iniExecution();



    private ArrayList<String[][]> dataKakuros(){
        return ctrlPersistence.loadKakuros();
    }

    private ArrayList<String> dataUsers(){
        return ctrlPersistence.loadUsers();
    }

    private void dataGame(ArrayList<String> users){
        for (String u: users){
            String[] kakuros = ctrlPersistence.startedKakuros(u);
            if (kakuros != null) {
                for (String k : kakuros) {
                    if (k.contains("kakuro_")) {
                        k = k.replace("kakuro_", "");
                        int idk = Integer.parseInt(k);
                        String[] games = ctrlPersistence.getGames(u, idk);
                        for (String g : games) {
                            if (!g.contains("_stats")) {
                                g = g.replace("game_", "");
                                g = g.replace(".txt", "");
                                int idg = Integer.parseInt(g);
                                String[][] game = ctrlPersistence.loadGame(u, idk, idg);
                                String stats = ctrlPersistence.loadStats(u, idk, idg);
                                stats = stats.replace("Execution Time: ", "");
                                stats = stats.replace("Hints asked: ", "");
                                ctrlGame.loadGame(u, idk, idg, game, stats);
                                ctrlUser.setGSuser(u, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    //CLASS METHODS

    //primer panell
    private void iniExecution() {
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
                    //contExecution();
                    break;

                case 2:
                    exit = true;
                    break;
            }

        }
    }
/*
    //segon panell
    private void contExecution(){
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
                    createKakuro();
                    break;
                case 2:
                    playGames();
                    break;
                case 3:
                    personalStats();
                    break;
                case 4:
                    globalRanking();
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
*//*
    // tercer panell
    private void createKakuro(){
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
    */
/*
    // cuart panell
    private void playGames(){
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
                            createNewGame(k);
                            gameExecution();
                            break;
                        case 2:
                            getGames(k);

                            System.out.println("1 - Continue game");
                            System.out.println("2 - Delete Game");

                            System.out.print("\nCHOOSE ONE OPTION: ");
                            sca = new Scanner(System.in);
                            option = sca.nextInt();

                            int idGame;
                            sca = new Scanner(System.in);
                            idGame = sca.nextInt();

                            switch (option) {
                                case 1:
                                    getGameScenario(idGame);
                                    gameExecution();
                                    break;
                                case 2:
                                    deleteGame(idGame);
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
*/
    // cinquè panell
    private void gameExecution() {
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
//                    fillCell();
                    //donada una posició (x,y) i un valor (v), s'omple la casella x,y amb v, retorna true si el joc esta completat
                    //es guarda el joc
                    break;
                case 2:
//                    askHint();
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


    /**
     * Consultant function of the ranking of punctuations that all the different users made in their games.
     */
    public String[][] listGlobalRanking(){
        return ctrlGame.listRankingOrStats("", true);
    }

    /**
     * Consultant function of the personal ranking of punctuations for one user from all his games.
     */
    public String[][] listPersonalStats(){
        return ctrlGame.listRankingOrStats(getActiveUser(), false);
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
            ctrlPersistence.newKakuro(ctrlKakuro.listIdKakuro(), ctrlKakuro.listKakuro(idKakuro).listKakuro());
            return idKakuro;
        }
        return -1;
    }

    /**
     * This method uses a generation algorithm of a Kakuro, according to the following parameters.
     * @param numRows It indicates the number of rows that the Kakuro will have.
     * @param numColumns It indicates the number of columns that the Kakuro will have.
     * @param diff It indicates the difficulty of the game.
     * @param fc It indicates the number of filled cells.
     * @return It returns the kakuro representated in String [][] with some white cells filled indicated by fc.
     */
    public String [][] generateKakuro(int numRows, int numColumns, String diff, int fc){
        String [][] result = ctrlKakuro.generateKakuro(numRows, numColumns, diff, fc);
        ctrlPersistence.newKakuro(ctrlKakuro.listIdKakuro(), ctrlKakuro.listKakuro(ctrlKakuro.listIdKakuro()).listKakuro());
        return result;
    }

//    /**
//     * Consultant function
//     * @return It returns a matrix of the information of all the cells of the kakuro
//     */
//    public String [][] listKakuro () {
//        return ctrlKakuro.listKakuro();
//    }

    /**
     * Function used to log in, if the user is not existed, the system will create by itself a new user with name introduced
     * @param name indicates the name of the new user
     */
    public void logInUser(String name){
        if (ctrlPersistence.createUser(name)) ctrlUser.createUser(name);
        else ctrlUser.setActiveUser(name);
    }

    /**
     * This method is a getter of the active user in the system.
     * @return username of the active user.
     */
    public String getActiveUser() {
        return ctrlUser.getActiveUser();
    }

    /**
     * This method is a getter of all the kakuros in the system.
     * @return A list of the kakuros existing.
     */
    public int[] getKakurosGlobals() {

        return ctrlKakuro.listIdKakuros();
    }

    /**
     * This method is a getter of all the existing games of a specific Kakuro from the active user.
     * @param idKakuro Indicates the kakuro that is the gameScenario of all the games.
     * @return A list of game identifiers.
     */
    public ArrayList<Integer> getGames(int idKakuro) {
        return ctrlGame.getGames(getActiveUser(), idKakuro);
    }

    /**
     * Method used to create a new Game in the persistence layer and in the domain layer.
     * @param idKakuro Indicates the id of the Kakuro gameScenario.
     * @return It returns either the id of the kakuro or -1 if it failed.
     */
    public String createNewGame(int idKakuro) {
        String username = getActiveUser();
        int idGame = ctrlUser.getGSuser() + 1;

        if(ctrlPersistence.newGame(username, idKakuro, idGame)) {

            String[][] kakuro = ctrlKakuro.listKakuro(idKakuro).listKakuro();

            ctrlUser.setGSuser(username, 1);
            ctrlGame.loadGame(username, idKakuro, idGame, kakuro, "0:0");
            ctrlGame.setActiveGame(username, idKakuro, idGame);

            int numR = ctrlKakuro.listKakuro(idKakuro).getNumRows();
            int numC = ctrlKakuro.listKakuro(idKakuro).getNumColumns();
            String newGame = "0:0"  + ":" + numR + "," + numC + ":";
            for(int i=0; i<numR; i++){
                for(int j=0; j<numC; j++){
                    newGame += kakuro[i][j];
                    if (i<numR-1 || j<numC-1){
                        newGame += ",";
                    }
                }
            }
            return newGame;
        }
        return null;

    }

    /**
     * This method is used to continue playing a started game.
     * @param idGame Identifies the game to resume.
     * @return It returns the field in a formatted String.
     */
    public String resumeGame(int idKakuro, int idGame) {
        ctrlGame.setActiveGame(getActiveUser(), idKakuro, idGame);
        return ctrlGame.getGameScenario(getActiveUser(), idKakuro ,idGame);
    }

    /**
     * This method will recieve the current status of the game being played and it will return a hint to solve it.
     * @param field Contains all the information of the game Scenario being played.
     * @return It returns the value of the Hint and the position X and Y as well as the number of current hints
     * in a formatted string like: "value:posX:posY:numHints". If it fails it will return the error message.
     */
    public String askHint(int idKakuro, String[][] game) {

        ArrayList<Pair> g = new ArrayList<>();
        for (int i = 0; i < game.length; ++i) {
            for (int j = 0; j < game[0].length; ++j) {
                if (game[i][j].equals("0") || game[i][j].equals("?")) g.add(new Pair(i, j));
            }
        }
        if (g.size() == 0) return null;

        int random = (int)(Math.random()*g.size());
        String[][] solution = ctrlKakuro.listKakuro(idKakuro).getSolution();
        return solution[g.get(random).first()][g.get(random).second()]+":"+g.get(random).first()+":"+g.get(random).second();
    }
    
    /**
     * This method updates the current state of the gameScenario being played.
     * @param time 
     * @param hints
     * @param newState
     */
    public void saveGame(int time, int hints, String [][] newState){
        ctrlPersistence.saveGame(getActiveUser(), ctrlGame.getActiveGame().getKakuroId(), ctrlGame.getActiveGame().getGameId(), time, hints, newState);
        ctrlGame.saveGame(time, hints, newState);
    }

    /**
     * This method checks if a specific game solution is correct. If it is the score is calculated.
     * @param idKakuro Identifies the kakuro that is the gameScenario to check.
     * @param idGame Identifies the game that is being checked.
     * @param time Indicates the value of the time passed playing the game.
     * @param hints Indicates the number of hints asked while playing.
     * @param kakuro It contains the solution provided by the user.
     */

    public boolean validateGame (int idKakuro, int time, int hints, String [][] game) {

        String [][] solution = ctrlKakuro.listKakuro(idKakuro).getSolution();
        for (int i = 0; i < game.length; ++i) {
            for (int j = 0; j < game[0].length; ++j) {
                if (!game[i][j].equals(solution[i][j])) return false;
            }
        }

        String user = getActiveUser();
        ctrlGame.updateGameInfo(time, hints);

        ctrlPersistence.updateStats(user, ctrlGame.listRankingOrStats(user, true), true);
        ctrlPersistence.updateStats(user, ctrlGame.listRankingOrStats(user, false), false);

        int idGame = ctrlGame.getActiveGame().getGameId();
        deleteGame(idKakuro, idGame, false);

        return true;
    }

    /**
     * This method deletes the user from the system.
     */
    public void deleteUser () {
        // FALTA ESBORRAR DEL CONJUNT DE USERS QUE TE CTRL USER I ESBORRAR TOTS ELS GAMES QUE TINGUI A CTRL GAME
        String user = getActiveUser();
        ctrlPersistence.deleteUser(user);
        ctrlUser.deleteUser(user);
        ctrlGame.deleteGames(user);
    }

    /**
     * This method deletes a specified game from the active user.
     * @param idGame Identifies the game to delete.
     */
    public void deleteGame (int idKakuro, int idGame, boolean actDomini) {
        String user = getActiveUser();
        ctrlPersistence.deleteGame(user, idKakuro, idGame);
        if (actDomini) ctrlGame.deleteGame(user, idKakuro, idGame);
    }

}
