package persistence;

import java.io.File;
import java.io.IOException;

/**
 * This class has the responsibility of scrapping through the user data stored in the persistence layer. It communicates with the Persistence Controller.
 */
public class DataUser {


    /**
     * Default empty class constructor
     */
    public DataUser() {
    }

    /**
     * This method creates a new user in persistence layer.
     */
    public boolean createUser(File user, File f) {
        try {

            if (!user.exists()) {
                if (user.mkdir()) {

                    f.createNewFile();
                    return true;
                }
            }
        } catch (IOException ignored){
        }
        return false;
    }

    /**
     * This method is responsible to delete a User and the files with all his information.
     */
    public void deleteUser (File f) {
        if (f.exists()) {
            String[] entries = f.list();
            for (String s : entries) {
                File currentFile = new File(f.getPath(), s);
                if (currentFile.isDirectory()){

                    if (currentFile.exists()) {
                        String[] entriesK = currentFile.list();
                        for (String sK : entriesK) {
                            File currentFileK = new File(currentFile.getPath(), sK);
                            currentFileK.delete();
                        }
                        currentFile.delete();
                    }

                }
                else currentFile.delete();
            }
            f.delete();
        }

    }

//    /**
//     * Returns true if a user exists
//
//     public boolean exists_user(String name){
//     File user = new File(route + "/" + name);
//     if (user.exists()) return true;
//     return false;
//     }*/
}
