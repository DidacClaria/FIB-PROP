package DOMINI;

public class Pair {
    int p1;
    int p2;

    public Pair(int var1, int var2) {
        this.p1 = var1;
        this.p2 = var2;
    }

    public int first() {
        return this.p1;
    }

    public int second() {
        return this.p2;
    }
}
