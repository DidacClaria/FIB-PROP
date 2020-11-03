import java.io.*;
import java.util.*;
import Casella.java;

public class CasellaBlanca extends Casella {
    int valor;

    //public functions

    //creadores
    public void CasellaBlanca(int v){
        this.valor = v;
    }

    //modificadores
    public void setValor(int v){
        this.valor = v;
    }

    //consultores
    public int getValor(){
        return valor;
    }


}