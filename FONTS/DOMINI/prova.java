package DOMINI;

import java.util.*;

public class prova {
    int f;  //dimensió de fila del kakuro
    int c;  //dimensió de columna del kakuro
    Casella kakuro [][];

    public prova () {};

/*
    public DOMINI.prova (int x, int y) {
        this.f = x;
        this.c = y;
        this.kakuro = new DOMINI.Casella[f][c];
    }
*/

    public void read_kakuro () {
        Scanner sca = new Scanner(System.in);
        String s = sca.nextLine(); // Llegir quantes files i quantes columnes;

        String[] input = s.split (",");
        this.f = Integer.parseInt(input[0]);
        this.c = Integer.parseInt(input[1]);
        this.kakuro = new Casella[f][c];

        for (int i = 0; i<f; ++i) {
            s = sca.nextLine();
            String[] text = s.split (",");
            for (int j=0; j<c; ++j) {
                kakuro[i][j] = new Casella();

                String aux = text[j];
                int l = aux.length();

                switch (l) {
                    case 1:
                        if (aux.charAt(0) == '?') kakuro[i][j].set_tipus(0);
                        else kakuro[i][j].set_tipus(-2);
                        break;
                    case 2:
                        if (aux.charAt(0) == 'F') kakuro[i][j].set_sumF(aux.charAt(1) - '0');
                        else kakuro[i][j].set_sumC(aux.charAt(1) - '0');
                        break;
                    case 3:
                        int q = Integer.parseInt(aux.substring(1, 3));
                        if (aux.charAt(0) == 'F') kakuro[i][j].set_sumF(q);
                        else kakuro[i][j].set_sumC(q);
                        break;
                    case 4:
                        kakuro[i][j].set_sumC(aux.charAt(1) - '0');
                        kakuro[i][j].set_sumF(aux.charAt(3) - '0');
                        break;
                    case 5:
                        if (aux.charAt(2) == 'F') {
                            kakuro[i][j].set_sumC(aux.charAt(1) - '0');
                            kakuro[i][j].set_sumF(Integer.parseInt(aux.substring(3, 5)));
                        } else {
                            kakuro[i][j].set_sumC(Integer.parseInt(aux.substring(1, 3)));
                            kakuro[i][j].set_sumF(aux.charAt(4) - '0');
                        }
                        break;
                    case 6:
                        kakuro[i][j].set_sumC(Integer.parseInt(aux.substring(1, 3)));
                        kakuro[i][j].set_sumF(Integer.parseInt(aux.substring(4, 6)));
                        break;
                }
            }
        }

    }

    public void print_kakuro () {
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j <c; ++j){
                if (kakuro[i][j].get_tipus() == -1) {
                    int count = 0;
                    if (kakuro[i][j].get_sumC() > 0) {
                        System.out.print("C" + kakuro[i][j].get_sumC());
                        if (kakuro[i][j].get_sumC() >= 10) count += 3;
                        else count += 2;
                    }
                    if (kakuro[i][j].get_sumF() > 0) {
                        System.out.print("F" + kakuro[i][j].get_sumF());
                        if (kakuro[i][j].get_sumF() >= 10) count += 3;
                        else count += 2;
                    }

                    for (int k = 0; k < (10 - count); ++k) System.out.print(" ");
                }
                else if (kakuro[i][j].get_tipus() == -2) System.out.print("X         ");
                else System.out.print(kakuro[i][j].get_tipus() + "         ");

            }
            System.out.println();
        }
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