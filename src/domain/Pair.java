package domain;

public class Pair {
    public int p1;
    public int p2;

    public Pair(int a, int b) {
        this.p1 = a;
        this.p2 = b;
    }

    public int first() {
        return this.p1;
    }

    public int second() {
        return this.p2;
    }
}
