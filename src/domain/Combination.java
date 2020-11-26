package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Combinacio Class. Cretes a HashMap with the combinations of numbers for each concurrent-white-cell lenghts
 */
public class Combination {

    /**
     * This attribute is the HashMap of combinations.
     */
    private HashMap<Integer, Integer[]> comb;

    /**
     * Combinacio constructor generates the HashMap with the combinations
     */
    public Combination(){
        this.comb = new HashMap<Integer, Integer[]> ();
        comb.put(203, new Integer[10]{1,0,0,0,0,0,0,0,0,0});


    /**
     * Getter function of full HashMap
     * @return HashMap comb
     */
    public HashMap<Integer, ArrayList<ArrayList<Integer>> > getCombinacio(){
        return comb;
    }
}
