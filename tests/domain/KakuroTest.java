package domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class KakuroTest {




    /**
     * Test of getIdKakuro method of class Kakuro
     */
    @Test
    public void Kakuro_test(){
        System.out.println("Kakuro");
        Kakuro k = new Kakuro(5,5);
        int id = k.getIdKakuro();
        assertNotNull(k);
    }

    /**
     * Test of getIdKakuro method of class Kakuro
     */
    @Test
    public void getIdKakuro_test(){
        System.out.println("getIdKakuro");
        Kakuro k = new Kakuro(2,2);
        k.setIdKakuro(23);

        int id = k.getIdKakuro();
        assertEquals(23,id);
    }
    /**
     * Test of setIdKakuro method of class Kakuro
     */
    @Test
    public void setIdKakuro_test(){
        System.out.println("setIdKakuro");
        Kakuro k = new Kakuro(2,2);
        k.setIdKakuro(5);

        int id = k.getIdKakuro();
        assertEquals(5,id);
    }

    /**
     * Test of listKakuro method of class Kakuro
     */
    @Test
    public void listKakuro_test(){
        System.out.println("listKakuro");
        Kakuro k = new Kakuro();
        String field [][] = { {"*","C16","C6","*","*"},
                            {"F9","2","0","C13","*"},
                            {"F16","0","0","0","C16"},
                            {"*","F12","0","0","0"},
                            {"*","*","F14","0","0"} };

        k.proposeKakuro(5,5,field);
        String l [][]  = k.listKakuro();
        for (int n = 0; n < 5; n++){
                assertEquals(Arrays.asList(field[n]),Arrays.asList(l[n]));
        }

    }

    /**
     * Test of solve method of class Kakuro
     */
    /*
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
    */
    /**
     * Test of proposeKakuro method of class Kakuro
     */
    @Test
    public void proposeKakuro_test(){
        System.out.println("proposeKakuro");
        int numR = 5, numC = 5;
        Kakuro k = new Kakuro(5,5);
        String [][] field = {{"*","C16","C6","*","*"}, {"F9","0","0","C13","*"},{"F16","0","0","0","C16"},{"*","F12","0","0","0"},
                {"*","*","F14","0","0"}};
        boolean prop = k.proposeKakuro(numR,numC,field);
        assertTrue(prop);

    }

    /**
     * Test of DFS_sym method of class Kakuro
     */
    /*
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
        assertTrue(connect);
    }
    */

}