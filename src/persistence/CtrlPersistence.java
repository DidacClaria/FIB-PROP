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
        else System.out.println("User existed!");
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
    public boolean new_kakuro(int id_int, String [][] kakuro){
        String id = String.valueOf(id_int);
        try {
            FileWriter wr = new FileWriter(routek + "\\" + id + ".txt");
            String kak;
            for (int i = 0; i < kakuro.length; i++) {
                for (int j = 0; j < kakuro[0].length; j++) {
                    wr.write(kakuro[i][j]);
                    if (j != kakuro[0].length - 1) wr.write(","); //separador d'elements
                    else wr.write("#"); //separador de files
                }
            }
            wr.close();
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
     * Start a new game for User = user
     */
    public boolean new_game (String user, int id_game){
        try {
            File pathOri = new File(routek + "\\" + id_game + ".txt");
            File pathDes = new File(route + "\\" + user + "\\" + id_game + ".txt");
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
     * returns the game of User = user with ID = id_game
     */
    public String show_game(String user, int id_game){
        String kakuro;
        String aux = null;
        try {
            File k = new File(routek + "\\" + user + "\\" + id_game + ".txt");
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

}
