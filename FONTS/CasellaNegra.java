import java.io.*;
import java.util.*;
import Casella.java;

public class CasellaNegra extends Casella {
    int valorCol;
    int valorFila;

    //public functions

    //creadores
    public CasellaNegra(int vc, int vf){
        this.valorCol = vc;
        this.valorFila = vf;
    }

    //modificadores
    public void setValorColumna(int c){
        this.valorCol = c;
    }

    public void setValorFila(int f){
        this.valorFila = f;
    }

    //consultores
    public int getValorColumna(){
        return valorCol;
    }

    public int getValorFila(){
        return valorFila;
    }


}