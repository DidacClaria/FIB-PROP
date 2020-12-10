package persistence;

import domain.CtrlDomain;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * Persistence Controller Class.
 */
public class CtrlPersistence {
    //ATTRIBUTES

    /**
     * Domain Controller.
     */
    private final CtrlDomain ctrlDomain;
    private String route;
    private String routek;

    //CONSTRUCTOR

    /**
     * Default empty Persistence Controller constructor.
     * @param ctrlDomain Is the reference of the Domain Controller.
     */
    public CtrlPersistence(CtrlDomain ctrlDomain) {
        this.ctrlDomain = ctrlDomain;
        route = "D:\\PROP\\src\\persistence\\data";
        routek = route + "\\kakuros";
        InitializePersistance();

    }

    /**
     * Initializes the data folders
     */
    private void InitializePersistance(){
        File data = new File(route);
        if (!data.exists()){
            if (data.mkdir()) {
                data = new File(routek);
                if (!data.exists()){
                    if (data.mkdir()) {
                    }
                }
            }
        }
    }

    /**
     * Saves a new user
     */
    public boolean create_user(String name){
        File user = new File(route + "\\" + name);
        if (!user.exists()) {
            if (user.mkdir()) return true;
            else System.out.println("User directory not created due an error");
        }
        else System.out.println("\nUser existed!");
        return false;
    }

    /**
     * Returns true if a user exists
     */
    public boolean exists_user(String name){
        File user = new File(route + "\\" + name);
        if (user.exists()) return true;
        return false;
    }

    /**
     * Saves a new kakuro
     */
    public boolean new_kakuro(int id_kakuro, String [][] kakuro){
        try {
            FileWriter wr = new FileWriter(routek + "\\" + id_kakuro + ".txt");
            FileWriter wr_sol = new FileWriter(routek + "\\" + id_kakuro + "_sol.txt");
            for (int i = 0; i < kakuro.length; i++) {
                for (int j = 0; j < kakuro[0].length; j++) {
                    if (kakuro[i][j].length() == 1 && kakuro[i][j] != "*") wr.write("0");
                    else wr.write(kakuro[i][j]);
                    wr_sol.write(kakuro[i][j]);
                    if (j != kakuro[0].length - 1) {//separador d'elements
                        wr.write(",");
                        wr_sol.write(",");
                    }
                    else {
                        wr.write(System.getProperty("line.separator")); //separador de files
                        wr_sol.write(System.getProperty("line.separator"));
                    }
                }
            }
            wr.close();
            wr_sol.close();
            return true;
        } catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
        return false;
    }

    /**
     * returns the kakuro with ID = id
     */
    public String show_kakuro(int id){
        String kakuro;
        String aux = null;
        try {
            File k = new File(routek + "\\" + id + ".txt");
            if (k.exists()) {
                Scanner mr = new Scanner(k);
                kakuro = mr.nextLine();
                while (mr.hasNextLine()){
                    kakuro += mr.nextLine();
                }
                mr.close();
                aux = kakuro;
            }
        } catch (IOException e){
            System.out.println("\nError occurred during file reading");
        }
        return aux;
    }

    /**
     * Starts a new game for User = user
     */
    public boolean new_game (String user, int id_kakuro){
        try {
            File pathOri = new File(routek + "\\" + id_kakuro + ".txt");
            File pathDes = new File(route + "\\" + user + "\\" + id_kakuro + ".txt");
            pathDes.createNewFile();
            if (pathOri.exists() && pathDes.exists())
                Files.copy(Paths.get(pathOri.getAbsolutePath()), Paths.get(pathDes.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            else System.out.println("\nThe kakuro of the game or the user is not existed!!!");
        } catch (IOException e){
            System.out.println("\nError occurred during file writing");
        }
        return false;
    }

    /**
     * Saves the state of a game in current of the user
     */
    public boolean save_game (String user, int id_kakuro, String [][] kakuro){
        File old_game = new File(route + "\\" + user + "\\" + id_kakuro + ".txt");
        if (old_game.exists()) {
            old_game.delete();
            try {
                FileWriter new_game = new FileWriter(route + "\\" + user + "\\" + id_kakuro + ".txt");
                for (int i = 0; i < kakuro.length; i++) {
                    for (int j = 0; j < kakuro[0].length; j++) {
                        if (kakuro[i][j].equals("?")) new_game.write("0");
                        else new_game.write(kakuro[i][j]);
                        if (j != kakuro[0].length - 1) new_game.write(","); //separador d'elements
                        else new_game.write(System.getProperty("line.separator")); //separador de files
                    }
                }
                new_game.close();
                return true;
            } catch (IOException e){
                System.out.println("\nError occurred during file writing");
                return false;
            }
        }
        else {
            System.out.println("\nThe game is not existed!");
            return false;
        }
    }

    /**
     * returns the game of User = user with ID = id_game
     */
    /*
    public String[][] show_game(String user, int id_kakuro){
        int i = 0;
        while (m)

        String [][]
        try {
            File k = new File(route + "\\" + user + "\\" + id_kakuro + "_sol.txt");
            if (k.exists()) {
                Scanner mr = new Scanner(k);
                int i = 0;
                while (mr.hasNextLine()){
                    String aux = mr.nextLine();
                    kakuro[i] = aux.split(",");
                    ++i;
                }
                mr.close();
            }
        } catch (IOException e){
            System.out.println("\nError occurred during file reading");
        }
        return kakuro;
    }
    */


    public void ask_hint (String user, int id_kakuro, String [][] kakuro) {


    }

}
