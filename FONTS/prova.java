import java.io.*;
import java.util.*;

public class prova {
    int f;  //dimensió de fila del kakuro
    int c;  //dimensió de columna del kakuro
    Casella kakuro [][];

    public prova () {};

/*
    public prova (int x, int y) {
        this.f = x;
        this.c = y;
        this.kakuro = new Casella[f][c];
    }
*/

    public void set_dim_kakuro (String s) {
        int i = s.indexOf (',');
        this.f  = Integer.parseInt (s.substring(0, i));
        this.c  = Integer.parseInt (s.substring(i+1, s.length()));
        this.kakuro = new Casella[f][c];
    }

    public void read_kakuro () {
        Scanner sca = new Scanner(System.in);
        String s = sca.nextLine(); // Llegir quantes files i quantes columnes;

        set_dim_kakuro (s);

        for (int i = 0; i<f; ++i) {
            s = sca.nextLine();
            int k = 0;

            for (int j=0; j<c; ++j) {
                kakuro[i][j] = new Casella();
                k = read_cell(s, k, i, j);
            }
        }

    }


    public void print_kakuro () {
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j <c; ++j){
                if (kakuro[i][j].get_tipus() == -1) {
                    if (kakuro[i][j].get_sumC() > 0) System.out.print("C" + kakuro[i][j].get_sumC());
                    if (kakuro[i][j].get_sumF() > 0) System.out.print("F" + kakuro[i][j].get_sumF());
                    System.out.print("   ");
                }
                else if (kakuro[i][j].get_tipus() == -2) System.out.print("X   ");
                else System.out.print(kakuro[i][j].get_tipus() + "    ");

            }
            System.out.println();
        }
    }


    private int read_cell (String s, int k, int x, int y) {
        char c = s.charAt(k); // llegim la primera lletra
        boolean negra_double = false;

        if (c == 'F' || c == 'C') { // si és una casella negra amb números
            String aux;
            aux = String.valueOf(s.charAt(++k)); // llegim una posició més, no cal comprovar res perquè segur és un número

            if (k+1 < s.length()) { // tornem a llegir una posició més, aqui cal comprovar si ja és al final
                ++k;
                if (s.charAt(k) == 'F') negra_double = true; // negra_double per indicar si és una casella negra amb dos valors (C i F)
                else if (s.charAt(k) != ',') { // Si torna a ser un número, fem una suma de string
                    aux += s.charAt(k);
                    if (k+1 < s.length () && s.charAt(++k) == 'F') negra_double = true;
                }
            }

            if (c == 'C') { // Si la primera lletra és C
                            // Només el cas de que la primera lletra comenci amb C pot tenir dos números
                            // és a dir, (existeix C12F... i no existex F12C...)

                kakuro[x][y].set_sumC(Integer.valueOf(aux));

                if (negra_double) {
                    aux = String.valueOf(s.charAt(++k)); // Canviar el valor de aux per al cas de F (repetir el procés anterior)
                    if (k+1 < s.length()) { // Tornem a llegir una posició més, aqui cal comprovar si ja és al final
                        if (s.charAt(++k) != ',') { // Si torna a ser un número, fem una suma de string
                            aux += s.charAt(k);
                            ++k;
                        }
                    }
                    kakuro[x][y].set_sumF (Integer.valueOf(aux));
                }
            }
            else kakuro[x][y].set_sumF (Integer.valueOf(aux)); // Si la primera lletra és F
        }

        else if (c == '?') { // casella blanca
            kakuro[x][y].set_tipus(0); // les caselles que tenen l'atribut tipus = 0 són blanques
            ++k;
        }

        else { // casella negra buida
            kakuro[x][y].set_tipus(-2); // les caselles que tenen l'atribut tipus = -2 són negres buides
            ++k;
        }

        return ++k; // Sempre quan arribem aqui, l'element de la posició k sempre és una coma, per tant, cal incrementar
                    // una posició més per la següent cerca.
    }

    public boolean solve_kakuro () {
        ArrayList <Pair> pos_whites = search_whites();
        return solve (pos_whites, 0);
    }

    //Crear un ArrayList per guardar les posicions de totes les caselles blanques existents al kakuro [][]
    private ArrayList <Pair> search_whites () {
        ArrayList <Pair> p = new ArrayList <Pair> ();
        int count = 0;
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (kakuro[i][j].get_tipus() == 0) p.add (new Pair(i,j));
            }
        }
        return p;
    }

    //Una funció recursiva que fa un backtracking
    //És a dir, mira valors possibles (1...9) i les seves combinacions a cada posició agafada del ArrayList pos_whites
    private boolean solve (final ArrayList <Pair> pos_whites, int k) {
        if (k == pos_whites.size()) return true; //El moment quan hagi vist totes les caselles blanques

        // Consultar la posició de la casella blanca
        Pair aux = pos_whites.get(k);
        int posX = aux.first();
        int posY = aux.second();

        // Backtracking
        for (int i = 1; i <= 9; ++i) {
            //Comprova la fila i la columna del de cada número (checkH i checkV)
            if (checkH(posX, posY, i) && checkV(posX, posY, i)) {
                kakuro[posX][posY].set_tipus(i); // Posar el número a l'atribut tipus (Recordar els 4 tipus: -2, -1, 0 i >0)

                if (solve(pos_whites, k + 1)) return true; //Mira les combinacions possibles amb el número i
                                                              //Retorna cert si existeix solució amb el número i

                else kakuro[posX][posY].set_tipus(0); //Fals si no existeix solució amb el número i
                                                      //Per tant, esborrar el número que hem posat
            }
        }
        return false; //Quan hagi comprovat tots els números possibles 1...9 i no troba cap solució
    }

    private boolean checkH (int x, int y, int valor) {
        int sum = valor;
        int totalF = 0;

        int aux = y-1;
        if (aux < 0) return true;

        while (kakuro[x][aux].get_tipus() > 0) {
            if (kakuro[x][aux].get_tipus() == valor) return false;
            sum += kakuro[x][aux].get_tipus();
            --aux;
        }

        totalF = kakuro[x][aux].get_sumF();

        if (sum > totalF) return false;

        if (y + 1 == c) {
            if (sum < totalF) return false;
        }
        else if (kakuro[x][y+1].get_tipus() < 0) {
            if (sum < totalF) return false;
        }
        return true;
    }


    private boolean checkV (int x, int y, int valor) {
        int sum = valor;
        int totalC = 0;

        int aux = x-1;
        if (aux < 0) return true;

        while (kakuro[aux][y].get_tipus() > 0)    {
            if (kakuro[aux][y].get_tipus() == valor) return false;
            sum += kakuro[aux][y].get_tipus();
            --aux;
        }

        totalC = kakuro[aux][y].get_sumC();

        if (sum > totalC) return false;

        if (x+1 == f) {
            if (sum < totalC) return false;
        }
        else if (kakuro[x+1][y].get_tipus() < 0) {
            if (sum < totalC) return false;
        }

        return true;
    }

}