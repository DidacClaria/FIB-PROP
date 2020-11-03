import java.io.*;
import java.util.*;
import Casella.java;

public class Kakuro {
    int f;
    int c;
    Casella kakuro [][];

    public void prova ();

    /*
    public prova (int x, int y) {
        this.f = x;
        this.c = y;
        this.kakuro = new Casella[x][y];
    }
    */

    public void set_dim_kakuro (String s) {
        int i = s.indexOf (',');
        this.f  = Integer.parseInt (s.substring(0, i));
        this.c  = Integer.parseInt (s.substring(i+1, s.length()));
    }

    public void read_kakuro () {
        Scanner sca = new Scanner(System.in);
        String s = sca.nextLine)(); // Llegir quantes files i quantes columnes;

        set_dim_kakuro (s);

        for (int i = 0; i<f; ++i) {
            s = sca.nextLine();
            int k = 0;

            for (int j=0; j<m; ++j) {
                kakuro[i][j] = new Casella();
                read_cell(s, k, i, j);

            }
        }

    }

    public void print_kakuro () {
        for (int i = 0; i < n; ++i) {
            for (int j=0 , j < m; ++j) {
                if (kakuro[i][j].getvalor() == -1)

            }


        }


    }

    public static void read_cell (String s, int k, int x, int y) {
        char c = s.charAt(k);
        if (c == 'F') {
            String aux;
            aux = s.charAt(++k);

            ++k;
            if (k < s.length()) {
                if (s.charAt(k) != ',' && s.charAt(k) !?

            }
            grid[x][y].set
        }

        else if (c == 'C') {


        }
    }
}