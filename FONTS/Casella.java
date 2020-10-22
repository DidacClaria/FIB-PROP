import java.io.*;
import java.util.*;

public class Casella {
    int valor;
    int numF;
    int numC;

    public Casella ();

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