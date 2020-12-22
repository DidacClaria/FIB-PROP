package domain;

/**
 * This class Ranking is used to represent a entry of the ranking in the domain.
 */
public class Ranking implements Comparable<Ranking> {

    private int kakuroId;
    private String username;
    private int time;
    private int hints;
    private int scores;

    /**
     * Default class creator.
     * @param k Identifier of the kakuro.
     * @param u Username of the user in the ranking.
     * @param t Time to complete the game.
     * @param h Hints needed to complete the game.
     * @param s Final score of the game.
     */
    public Ranking (int k, String u, int t, int h, int s) {
        this.kakuroId = k;
        this.username = u;
        this.time = t;
        this.hints = h;
        this.scores = s;
    }

    /**
     * Getter of kakuroId
     * @return kakuroId
     */
    public int getKakuroId () {
        return kakuroId;
    }

    /**
     * Getter of username
     * @return username
     */
    public String getUser () {
        return username;
    }

    /**
     * Getter of time
     * @return time
     */
    public int getTime () {
        return time;
    }

    /**
     * Getter of hints
     * @return hints
     */
    public int getHints () {
        return hints;
    }

    /**
     * Getter of scores
     * @return scores
     */
    public int getScores() {
        return scores;
    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Ranking ranking) {
        int id = ranking.getKakuroId();
        int sc = ranking.getScores();
        if (this.kakuroId == id) return sc - this.scores;
        else return this.kakuroId - id;

    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "kakuroId:" + kakuroId + ", USERNAME: " + username + ", TIME:" + time + ", HINTS: " + hints + ", SCORES: " + scores;
    }
}
