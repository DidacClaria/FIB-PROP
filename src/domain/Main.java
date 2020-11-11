package domain;

import java.util.Scanner;

/**
 * Main class. We will use it in order to simulate an user from the presentation layer sending their actions and data.
 */
public class Main {
    public static void main(String[] args) {
        CtrlDomain ctrlDomain = new CtrlDomain();
        Scanner sca = new Scanner(System.in);
        String s = sca.nextLine(); // Llegir quantes files i quantes columnes;

        String[] input = s.split (",");
        int numRows = Integer.parseInt(input[0]);
        int numColumns = Integer.parseInt(input[1]);
        String[][] field = null;
        ctrlDomain.proposeKakuro(numRows, numColumns, field);
    }
}
