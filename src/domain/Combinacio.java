package domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Combinacio Class. Cretes a HashMap with the combinations of numbers for each concurrent-white-cell lenghts
 */
public class Combinacio {

    /**
     * This attribute is the HashMap of combinations.
     */
    private HashMap<Integer, ArrayList<ArrayList<Integer>> > comb;


    /**
     * Combinacio constructor generates the HashMap with the combinations
     */
    public Combinacio(){
        int x1, x2, x3, x4, x5, x6, x7, x8, x9, suma;
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<ArrayList<Integer>> aux2 = new ArrayList<>();

        // 2 caselles (3-17)
        for(int i = 3; i <= 17; i++){
            for(x1 = 1; x1 < 10; x1++){
                for(x2 = x1+1; x2 < 10; x2++){
                    if (i == x1 + x2){
                        aux = new ArrayList<>();
                        aux.add(x1);
                        aux.add(x2);

                        aux2.add(aux);
                    }
                }
            }
            suma = 200 + i;
            comb.put(suma, aux2);
            aux2 = new ArrayList<>();
        }


            // 3 caselles (6-24)
        for(int i = 6; i <= 24; i++){
            for(x1 = 1; x1 < 10; x1++){
                for(x2 = x1+1; x2 < 10; x2++){
                    for(x3 = x2+1; x3 < 10; x3++) {
                        if (i == x1 + x2 + x3) {

                            aux = new ArrayList<>();
                            aux.add(x1);
                            aux.add(x2);
                            aux.add(x3);

                            aux2.add(aux);
                        }
                    }
                }
            }
            suma = 300 + i;
            comb.put(suma, aux2);
            aux2 = new ArrayList<>();
        }
        // 4 caselles (10-30)

        for(int i = 10; i <= 30; i++){
            for(x1 = 1; x1 < 10; x1++){
                for(x2 = x1+1; x2 < 10; x2++){
                    for(x3 = x2+1; x3 < 10; x3++) {
                        for(x4 = x3+1; x4 < 10; x4++) {
                            if (i == x1 + x2 + x3 + x4) {
                                aux = new ArrayList<>();
                                aux.add(x1);
                                aux.add(x2);
                                aux.add(x3);
                                aux.add(x4);

                                aux2.add(aux);
                            }
                        }
                    }
                }
            }
            suma = 400 + i;
            comb.put(suma, aux2);
            aux2 = new ArrayList<>();
        }
        // 5 caselles (15-35)
        for(int i = 15; i <= 35; i++){
            for(x1 = 1; x1 < 10; x1++){
                for(x2 = x1+1; x2 < 10; x2++){
                    for(x3 = x2+1; x3 < 10; x3++) {
                        for(x4 = x3+1; x4 < 10; x4++) {
                            for(x5 = x4+1; x5 < 10; x5++) {
                                if (i == x1 + x2 + x3 + x4 + x5) {
                                    aux = new ArrayList<>();
                                    aux.add(x1);
                                    aux.add(x2);
                                    aux.add(x3);
                                    aux.add(x4);
                                    aux.add(x5);

                                    aux2.add(aux);
                                }
                            }
                        }
                    }
                }
            }
            suma = 500 + i;
            comb.put(suma, aux2);
            aux2 = new ArrayList<>();
        }
        // 6 caselles (21-39)
        for(int i = 21; i <= 39; i++){
            for(x1 = 1; x1 < 10; x1++){
                for(x2 = x1+1; x2 < 10; x2++){
                    for(x3 = x2+1; x3 < 10; x3++) {
                        for(x4 = x3+1; x4 < 10; x4++) {
                            for(x5 = x4+1; x5 < 10; x5++) {
                                for(x6 = x5+1; x6 < 10; x6++) {
                                    if (i == x1 + x2 + x3 + x4 + x5 + x6) {

                                        aux = new ArrayList<>();
                                        aux.add(x1);
                                        aux.add(x2);
                                        aux.add(x3);
                                        aux.add(x4);
                                        aux.add(x5);
                                        aux.add(x6);

                                        aux2.add(aux);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            suma = 600 + i;
            comb.put(suma, aux2);
            aux2 = new ArrayList<>();
        }
        // 7 caselles (28-42)
        for(int i = 28; i <= 42; i++){
            for(x1 = 1; x1 < 10; x1++){
                for(x2 = x1+1; x2 < 10; x2++){
                    for(x3 = x2+1; x3 < 10; x3++) {
                        for(x4 = x3+1; x4 < 10; x4++) {
                            for(x5 = x4+1; x5 < 10; x5++) {
                                for(x6 = x5+1; x6 < 10; x6++) {
                                    for(x7 = x6+1; x7 < 10; x7++) {
                                        if (i == x1 + x2 + x3 + x4 + x5 + x6 + x7) {
                                            aux = new ArrayList<>();
                                            aux.add(x1);
                                            aux.add(x2);
                                            aux.add(x3);
                                            aux.add(x4);
                                            aux.add(x5);
                                            aux.add(x6);
                                            aux.add(x7);

                                            aux2.add(aux);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            suma = 700 + i;
            comb.put(suma, aux2);
            aux2 = new ArrayList<>();
        }
        // 8 caselles (36-44)
        for(int i = 36; i <= 44; i++){
            for(x1 = 1; x1 < 10; x1++){
                for(x2 = x1+1; x2 < 10; x2++){
                    for(x3 = x2+1; x3 < 10; x3++) {
                        for(x4 = x3+1; x4 < 10; x4++) {
                            for(x5 = x4+1; x5 < 10; x5++) {
                                for(x6 = x5+1; x6 < 10; x6++) {
                                    for(x7 = x6+1; x7 < 10; x7++) {
                                        for(x8 = x7+1; x8 < 10; x8++) {
                                            if (i == x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8) {

                                                aux = new ArrayList<>();
                                                aux.add(x1);
                                                aux.add(x2);
                                                aux.add(x3);
                                                aux.add(x4);
                                                aux.add(x5);
                                                aux.add(x6);
                                                aux.add(x7);
                                                aux.add(x8);

                                                aux2.add(aux);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            suma = 800 + i;
            comb.put(suma, aux2);
            aux2 = new ArrayList<>();
        }
        // 9 caselles (45)
        aux = new ArrayList<>();
        aux.add(45);
        aux2.add(aux);
        comb.put(945, aux2);
    }

    /**
     * Getter function of full HashMap
     * @return HashMap comb
     */
    public HashMap<Integer, ArrayList<ArrayList<Integer>> > getCombinacio(){
        return comb;
    }
}
