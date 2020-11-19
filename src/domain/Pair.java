package domain;

public class Pair {
    /**
     * First integer of the pair
     */
    public int p1;

    /**
     * Second integer of the pair
     */
    public int p2;

    /**
     * Default constructor
     * @param a first value of the pair
     * @param b second value of the pair
     */
    public Pair(int a, int b) {
        this.p1 = a;
        this.p2 = b;
    }

    /**
     * Getter of the first value of the pair
     * @return value of the first element in the pair
     */
    public int first() {
        return this.p1;
    }

    /**
     * Getter of the second value of the pair
     * @return value of the second element in the pair
     */
    public int second() {
        return this.p2;
    }
}
