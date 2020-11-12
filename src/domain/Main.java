package domain;

import java.util.Scanner;

/**
 * Main class. We will use it in order to simulate an user from the presentation layer sending their actions and data.
 */
public class Main {
    public static int f, c;
    public static String[][] kakuro;

    public static void main(String[] args) {
        CtrlDomain a = new CtrlDomain();
        read_kakuro();
        a.proposeKakuro(f,c,kakuro);
        print_kakuro();
    }

    public static void read_kakuro () {
        Scanner sca = new Scanner(System.in);
        String s = sca.nextLine(); // Llegir quantes files i quantes columnes;

        String[] input = s.split (",");
        f = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        kakuro = new String[f][c];

        for (int i = 0; i<f; ++i) {
            s = sca.nextLine();
            String[] text = s.split (",");
            for (int j=0; j<c; ++j) {
                kakuro[i][j] = text[j];
            }
        }
    }

    public static void print_kakuro () {
        System.out.print(f);
        System.out.print(c);
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j <c; ++j){
                System.out.print(kakuro[i][j]);
            }
        }
    }
}
