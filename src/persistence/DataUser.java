package persistence;

import java.io.File;
import java.io.IOException;

public class DataUser {


    /**
     *
     */
    public DataUser() {
    }

    /**
     *
     * @param user
     * @param f
     * @return
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
     *
     * @param f
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

    /**
     * Returns true if a user exists

     public boolean exists_user(String name){
     File user = new File(route + "/" + name);
     if (user.exists()) return true;
     return false;
     }*/
}
