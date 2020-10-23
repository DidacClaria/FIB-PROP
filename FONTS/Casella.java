import java.io.*;
import java.util.*;

public class Casella {
    private int valor;
    private int numF;
    private int numC;

    public Casella () {
        this.valor = -1; //casella negra amb números
        this.numF = 0;
        this.numC = 0;
    };

    public int getvalor () {
        return valor;
    }

    public int getnumF () {
        return numF;
    }

    public int getnumC () {
        return numC;
    }

    public void setvalor (int x) {
        this.valor = x;
    }

    public void setnumF (int x) {
        this.numF = x;
    }

    public void setnumC (int x) {
        this.numC = x;
    }
}