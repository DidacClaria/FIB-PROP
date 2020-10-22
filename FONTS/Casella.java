import java.io.*;
import java.util.*;

public class Casella {
    int posX;
    int posY;

    //public functions

    //creadores
    public Casella (int x, int y){
        this.posX = x;
        this.posY = y;
    }

    //modificadores
    public void setPosX (int x) {
        this.posX = x;
    }

    public void setPosY (int y) {
        this.posY = y;
    }

    //consultores
    public int getposX () { //consulta posX
        return posX;
    }

    public int getposY () { //consulta posY
        return posY;
    }




}