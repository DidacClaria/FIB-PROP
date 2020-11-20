package DOMINI;

public class Casella {
    int sumF = 0;
    int sumC = 0;
    int tipus = -1;

    public Casella() {
    }

    public void set_sumF(int var1) {
        this.sumF = var1;
    }

    public void set_sumC(int var1) {
        this.sumC = var1;
    }

    public void set_tipus(int var1) {
        this.tipus = var1;
    }

    public int get_sumF() {
        return this.sumF;
    }

    public int get_sumC() {
        return this.sumC;
    }

    public int get_tipus() {
        return this.tipus;
    }
}
