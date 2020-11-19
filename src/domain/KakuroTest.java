package domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class KakuroTEST {

    /**
     * Test of generate_random_black method of class Kakuro
     */
    @Test
    public void generate_random_black_test(){
        System.out.println("generate_random_black");
    }




    /**
     * Test of solve method of class Kakuro
     */
    @Test
    public void solve_test(){
        System.out.println("solve");
        Kakuro = new Kakuro(5,5);
        ArrayList <Pair> pos_w = new ArrayList<>();
        pos_w.add(new Pair(2,2));
        pos_w.add(new Pair(2,3));
        pos_w.add(new Pair(3,2));
        pos_w.add(new Pair(3,3));
        pos_w.add(new Pair(3,4));
        pos_w.add(new Pair(4,3));
        pos_w.add(new Pair(4,4));
        pos_w.add(new Pair(4,5));
        pos_w.add(new Pair(5,4));
        pos_w.add(new Pair(5,5));
        boolean resol = Kakuro.solve(pos_w,0);
        assertTrue(resol);

    }
    /**
     * Test of proposeKakuro method of class Kakuro
     */
    @Test
    public void proposeKakuro_test(){
        System.out.println("proposeKakuro");
        int numR = 5, numC = 5;
        String [][] field = {{"*","C16","C6","*","*"}, {"F9","0","0","C13","*"},{"F16","0","0","0","C16"},{"*","F12","0","0","0"}
                {"*","*","F14","0","0"}};
        boolean prop = proposeKakuro(numR,numC,field);
        assertTrue(prop);

    }

    /**
     * Test of DFS_sym method of class Kakuro
     */

    @Test
    public void DFS_sym(){
        System.out.println("solve");
        Kakuro = new Kakuro(5,5);
        ArrayList <Pair> pos_w = new ArrayList<>();
        pos_w.add(new Pair(2,2));
        pos_w.add(new Pair(2,3));
        pos_w.add(new Pair(3,2));
        pos_w.add(new Pair(3,3));
        pos_w.add(new Pair(3,4));
        pos_w.add(new Pair(4,3));
        pos_w.add(new Pair(4,4));
        pos_w.add(new Pair(4,5));
        pos_w.add(new Pair(5,4));
        pos_w.add(new Pair(5,5));
        boolean connect = Kakuro.DFS_sym(2,2);
        assertTrue(resol);
    }

}