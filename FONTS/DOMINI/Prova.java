package DOMINI;
import com.sun.jdi.VMMismatchException;

import java.lang.reflect.Array;
import java.util.*;

public class Prova {
    int f;
    int c;
    Casella[][] kakuro;

    private int solutions;

    public Prova() {
    }

    public Prova(int x, int y) {
        this.f = x;
        this.c = y;
        this.kakuro = new Casella[f][c];
        this.solutions = 0;

        for(int i = 0; i < f; ++i) {
            for(int j = 0; j < c; ++j) {
                kakuro[i][j] = new Casella();
                if (i != 0 && j != 0) kakuro[i][j].set_tipus(0);
                else kakuro[i][j].set_tipus(-2);
            }
        }

        for(int k = 0; k < 1000; ++k) generate_random_black(f-1, c-1, 1, 1);
        correct_format();

        for(int i = 0; i < f; ++i) {
            for(int j = 0; j < c; ++j) {
                if (kakuro[i][j].get_tipus() == -2 && ((i+1 < f && kakuro[i+1][j].get_tipus() == 0) || (j+1 < c && kakuro[i][j+1].get_tipus() == 0)))
                    kakuro[i][j].set_tipus(-1);
            }
        }

        generate_white_numbers();
        generate_black_numbers();
        while (solve_kakuro_multiple()) {
            clear_white_cells();
            generate_white_numbers();
            generate_black_numbers();
            this.solutions = 0;
        }

    }

    private void generate_random_black (int rangeX, int rangeY, int miniumX, int miniumY) {
        int randX = (int) (Math.random() * rangeX) + miniumX;
        int randY = (int) (Math.random() * rangeY) + miniumY;
        //System.out.println ("rangeX: " + rangeX + "rangeY: " + rangeY + "miniumX: " + miniumX + "miniumY: " + miniumY);
        //System.out.println (randX + " " + randY);
        if (kakuro[randX][randY].get_tipus() == 0 && no_alone_sym(randX, randY) && DFS_sym(randX, randY)) {
            kakuro[randX][randY].set_tipus(-2);
            kakuro[f - randX][c - randY].set_tipus(-2);
        }
    }

    private boolean no_alone_sym(int x, int y) {
        return no_alone(x, y) && no_alone(f - x, c - y);
    }

    private boolean no_alone(int x, int y) {
        if (kakuro[x - 1][y].get_tipus() == 0 && kakuro[x - 2][y].get_tipus() == -2) return false;
        if (x + 1 == f - 1 && kakuro[x + 1][y].get_tipus() == 0) return false;
        if (x + 1 < f && kakuro[x + 1][y].get_tipus() == 0 && kakuro[x + 2][y].get_tipus() == -2) return false;

        if (kakuro[x][y - 1].get_tipus() == 0 && kakuro[x][y - 2].get_tipus() == -2) return false;
        if (y + 1 == c - 1 && kakuro[x][y + 1].get_tipus() == 0) return false;
        if (y + 1 < c && kakuro[x][y + 1].get_tipus() == 0 && kakuro[x][y + 2].get_tipus() == -2) return false;

        return true;
    }

    private boolean DFS_sym(int x, int y) {
        boolean[][] m = new boolean[f][c];
        int visited = f * c;
        int posX = 0;
        int posY = 0;
        boolean trobat = false;

        for(int i = 0; i < f; ++i) {
            for(int j = 0; j < c; ++j) {
                if (kakuro[i][j].get_tipus() == -2) {
                    m[i][j] = true;
                    --visited;
                }

                if (!trobat && kakuro[i][j].get_tipus() == 0 && (i != x || j != y || i != f - x || j != c - y)) {
                    posX = i;
                    posY = j;
                    trobat = true;
                }
            }
        }

        m[x][y] = true;
        m[f - x][c - y] = true;
        visited -= 2;
        return visited == search_DFS(posX, posY, m);
    }

    private int search_DFS(int i, int j, boolean[][] m) {
        if (i >= f || j >= c) return 0;
        if (m[i][j]) return 0;
        if (!m[i][j]) m[i][j] = true;
        return 1 + search_DFS(i - 1, j, m) + search_DFS(i + 1, j, m) + search_DFS(i, j - 1, m) + search_DFS(i, j + 1, m);
    }

    private void correct_format () {
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (kakuro[i][j].get_tipus() == -2) {
                    if (wrong_cellH(i, j)) correct_cellH(i, j);
                    if (wrong_cellV(i, j)) correct_cellV(i, j);
                }
            }
        }
    }

    private boolean wrong_cellH (int x, int y) {
        if (y+10 >= c) return false;
        for (int i = y+1; i <= (y+10); ++i) {
            //System.out.println ("H" + " " + i);
            if (kakuro[x][i].get_tipus() == -2) return false;
        }
        return true;
    }

    private boolean wrong_cellV (int x, int y) {
        if (x+10 >= f) return false;
        for (int i = x+1; i <= (x+10); ++i) {
            //System.out.println ("V" + " " + i);
            if (kakuro[i][y].get_tipus() == -2) return false;
        }
        return true;
    }

    private void correct_cellH (int x, int y) {
        ArrayList<Pair> safe = new ArrayList<Pair>();
        for (int i = y+1; i <= (y+10); ++i) {
            //System.out.println ("H" + " " + i);
            if (no_alone_sym(x, i) && DFS_sym(x, i)) safe.add(new Pair(x, i));
        }
        if (safe.isEmpty()) restart();
        else {
            int k = (int) (Math.random() * safe.size());
            Pair aux = safe.get(k);
            int posX = aux.first();
            int posY = aux.second();
            kakuro[posX][posY].set_tipus(-2);
            kakuro[f - posX][c - posY].set_tipus(-2);
        }
    }

    private void correct_cellV (int x, int y) {
        ArrayList<Pair> safe = new ArrayList<Pair>();
        for (int i = x+1; i <= (x+10); ++i) {
            //System.out.println ("H" + " " + i);
            if (no_alone_sym(i, y) && DFS_sym(i, y)) safe.add(new Pair(i, y));
        }
        if (safe.isEmpty()) restart();
        else {
            int k = (int) (Math.random() * safe.size());
            Pair aux = safe.get(k);
            int posX = aux.first();
            int posY = aux.second();
            kakuro[posX][posY].set_tipus(-2);
            kakuro[f - posX][c - posY].set_tipus(-2);
        }
    }

    private void restart () {
        for(int i = 1; i < f; ++i) {
            for(int j = 1; j < c; ++j) kakuro[i][j].set_tipus(0);
        }

        for(int k = 0; k < 1; ++k) generate_random_black(f-1, c-1, 1, 1);
        correct_format();
    }

    private void generate_white_numbers () {
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (kakuro[i][j].get_tipus() == 0) {
                    ArrayList <Integer> possible_numbers = list_RC_numbers(i ,j);
                    int pos = (int)(Math.random() * possible_numbers.size());
                    kakuro[i][j].set_tipus(possible_numbers.get(pos));
                }
            }
        }
    }

    private void generate_black_numbers () {
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (kakuro[i][j].get_tipus() == -1) {
                    kakuro[i][j].set_sumF(calculate_sumF(i, j));
                    kakuro[i][j].set_sumC(calculate_sumC(i, j));
                }
            }
        }
    }

    private ArrayList<Integer> list_RC_numbers (int x, int y) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        int i;
        for (i = x-1; kakuro[i][y].get_tipus() > 0; --i) list.remove(Integer.valueOf(kakuro[i][y].get_tipus()));
        for (i = x+1; i < f && kakuro[i][y].get_tipus() > 0; ++i) list.remove(Integer.valueOf(kakuro[i][y].get_tipus()));
        for (i = y-1; kakuro[x][i].get_tipus() > 0; --i) list.remove(Integer.valueOf(kakuro[x][i].get_tipus()));
        for (i = y+1; i < c && kakuro[x][i].get_tipus() > 0; ++i) list.remove(Integer.valueOf(kakuro[x][i].get_tipus()));

        return list;
    }

    private int calculate_sumF (int x, int y) {
        int sumF = 0;
        for (int i = y+1; i < c && kakuro[x][i].get_tipus() > 0; ++i) sumF += kakuro[x][i].get_tipus();
        return sumF;
    }

    private int calculate_sumC (int x, int y) {
        int sumC = 0;
        for (int i = x+1; i < f && kakuro[i][y].get_tipus() > 0; ++i) sumC += kakuro[i][y].get_tipus();
        return sumC;
    }

    private void clear_white_cells () {
        for(int i = 1; i < f; ++i) {
            for(int j = 1; j < c; ++j) {
                if (kakuro[i][j].get_tipus() > 0) kakuro[i][j].set_tipus(0);
            }
        }
    }

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
                        else if (aux.charAt(0) == '*') kakuro[i][j].set_tipus(-2);
                        else kakuro[i][j].set_tipus(aux.charAt(0) - '0');
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

                    for (int k = 0; k < (9 - count); ++k) System.out.print(" ");
                }
                else if (kakuro[i][j].get_tipus() == -2) System.out.print("*        ");
                else System.out.print(kakuro[i][j].get_tipus() + "        ");

            }
            System.out.println();
        }
    }

    public boolean solve_kakuro () {
        ArrayList <Pair> pos_whites = search_whites();
        return solve (pos_whites, 0);
    }

    public boolean solve_kakuro_multiple () {
        ArrayList <Pair> pos_whites = search_whites();
        return solve_multiple (pos_whites, 0);
    }

    //Crear un ArrayList per guardar les posicions de totes les caselles blanques existents al kakuro [][]
    private ArrayList <Pair> search_whites () {
        ArrayList <Pair> p = new ArrayList <Pair> ();
        int count = 0;
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (kakuro[i][j].get_tipus() >= 0) p.add (new Pair(i,j));
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

        if (kakuro[posX][posY].get_tipus() > 0) {
            if (checkH(posX, posY, kakuro[posX][posY].get_tipus()) && checkV(posX, posY, kakuro[posX][posY].get_tipus())) {
                {
                    if (solve(pos_whites, k + 1)) return true;
                }
            }
        }
        else {
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
        }
        return false; //Quan hagi comprovat tots els números possibles 1...9 i no troba cap solució
    }

    private boolean solve_multiple (final ArrayList <Pair> pos_whites, int k) {
        if (k == pos_whites.size()) {
            ++this.solutions;
            return true;
        }

        Pair aux = pos_whites.get(k);
        int posX = aux.first();
        int posY = aux.second();

        for (int i = 1; i <= 9; ++i) {
            if (checkH(posX, posY, i) && checkV(posX, posY, i)) {
                kakuro[posX][posY].set_tipus(i);
                if (solve_multiple(pos_whites, k + 1) && solutions == 2) return true;
                else kakuro[posX][posY].set_tipus(0);
            }
        }
        return false;
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


    private boolean checkV(int x, int y, int valor) {
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
