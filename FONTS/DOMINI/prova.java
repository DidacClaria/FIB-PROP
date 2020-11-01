package DOMINI;

import java.util.*;

public class prova {
    int f;
    int c;
    Casella kakuro [][];

    public prova () {};

/*
    public DOMINI.prova (int x, int y) {
        this.f = x;
        this.c = y;
        this.kakuro = new DOMINI.Casella[f][c];
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
                if (kakuro[i][j].getvalor() == -1) {
                    if (kakuro[i][j].getnumC() > 0) System.out.print("C" + kakuro[i][j].getnumC());
                    if (kakuro[i][j].getnumF() > 0) System.out.print("F" + kakuro[i][j].getnumF());
                    System.out.print("   ");
                }
                else if (kakuro[i][j].getvalor() == -2) System.out.print("X   ");
                else System.out.print(kakuro[i][j].getvalor() + "    ");

            }
            System.out.println();
        }
    }


    public int read_cell (String s, int k, int x, int y) {
        char c = s.charAt(k); // llegim la primera lletra
        boolean negra_double = false;

        if (c == 'F' || c == 'C') { // si és una casella negra amb números
            String aux;
            aux = String.valueOf(s.charAt(++k)); // llegim una posició més, no cal comprovar res perquè segur és un número

            if (k+1 < s.length()) { // tornem a llegir una posició més, aqui cal comprovar si ja és al final
                ++k;
                if (s.charAt(k) == 'F') negra_double = true;
                else if (s.charAt(k) != ',') { // Si torna a ser un número, fem una suma de string
                    aux += s.charAt(k);
                    if (k+1 < s.length () && s.charAt(++k) == 'F') negra_double = true; // A mirar si la casella té dos números o no
                }
            }

            if (c == 'C') { // Si la primera lletra és C
                            // Només el cas de que la primera lletra comenci amb C pot tenir dos números
                            // és a dir, (existeix C12F... i no existex F12C...)

                kakuro[x][y].setnumC(Integer.valueOf(aux));

                if (negra_double) {
                    aux = String.valueOf(s.charAt(++k)); // Canviar el valor de aux per a F
                    if (k+1 < s.length()) { // tornem a llegir una posició més, aqui cal comprovar si ja és al final
                        if (s.charAt(++k) != ',') { // Si torna a ser un número, fem una suma de string
                            aux += s.charAt(k);
                            ++k;
                        }
                    }
                    kakuro[x][y].setnumF (Integer.valueOf(aux));
                }
            }
            else kakuro[x][y].setnumF (Integer.valueOf(aux)); // Si la primera lletra és F
        }

        else if (c == '?') { // casella blanca
            kakuro[x][y].setvalor(0); // les caselles que tenen l'atribut valor = 0 són blanques
            ++k;
        }

        else { // casella negra buida
            kakuro[x][y].setvalor(-2); // les caselles que tenen l'atribut valor = -2 són negres buides
            ++k;
        }

        return ++k; // Sempre quan arribem aqui, l'element de la posició k sempre és una coma, per tant, cal incrementar
                    // una posició més per la següent cerca.
    }

}