import java.io.*;
import java.util.*;

public class Casella {
    int sumF;   // el valor total de fila
    int sumC;   // el valor total de columna

    int tipus;  // 0 si és una casella blanca
                // > 0 casella blanca amb valors assginats
                // -1 si és una casella negra amb valors
                // -2 si és una casella negra buida

    //public functions

    //creadores
    public Casella () {
        this.sumF = 0;
        this.sumC = 0;
        this.tipus = -1;
    }

    /*
    public Casella (int x, int y){
        this.numF = x;
        this.numC = y;
    }
    */

    //modificadores
    public void set_sumF (int x) {
        this.sumF = x;
    }

    public void set_sumC (int x) {
        this.sumC = x;
    }

    public void set_tipus (int x) {
        this.tipus = x;
    }

    //consultores
    public int get_sumF () { //consulta posX
        return sumF;
    }

    public int get_sumC () { //consulta posY
        return sumC;
    }

    public int get_tipus () {
        return this.tipus;
    }


}