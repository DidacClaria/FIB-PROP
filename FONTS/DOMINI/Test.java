package DOMINI;
import java.util.*;
import java.lang.*;

public class Test {
    public static void main (String[] args) {


        Prova p = new Prova ();
        p.read_kakuro();
        boolean s = p.solve_kakuro_multiple();
        if (s) System.out.println ("Multiple Solution");
        else System.out.println ("Unique");
        p.print_kakuro();


/*
        Prova p = new Prova (10, 10);
        p.print_kakuro();
*/

        /*
        Map <Integer, Map <Integer, ArrayList<ArrayList<Integer> > > > table = new HashMap <Integer, Map <Integer, ArrayList<ArrayList<Integer> > > > ();

        //new ArrayList<Integer>(Arrays.asList(0, 4, 8, 9, 12))

        Map <Integer, ArrayList<ArrayList<Integer> > > numbers = new HashMap <Integer, ArrayList<ArrayList<Integer>>>();

        for (int i = 0; i < 17; ++i) {

            ArrayList<ArrayList<Integer>> comb = new ArrayList<ArrayList<Integer>>(
                    Arrays.asList(
                            Arrays.asList(1,2),
                            Arrays.asList(2,3)
                    )
            );

            numbers.put(3, comb);

        }
        comb.add(new ArrayList<Integer> );
        numbers.put(3, comb);

        comb.add()
        comb.add(new ArrayList<Integer> (Arrays.asList(1,4)));
        */

    }

}
