import java.io.*;
import java.util.*;

public class test {

    public static void main (String[] args) {
        prova p = new prova ();
        p.read_kakuro();
        boolean solució = p.solve_kakuro();
        p.print_kakuro();
    }
}