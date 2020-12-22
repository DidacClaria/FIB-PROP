package domain;

public class Ranking implements Comparable<Ranking> {
    private int kakuro_id;
    private String username;
    private int time;
    private int hints;
    private int scores;

    public Ranking (int k, String u, int t, int h, int s) {
        this.kakuro_id = k;
        this.username = u;
        this.time = t;
        this.hints = h;
        this.scores = s;
    }

    public int getKakuroId () {
        return kakuro_id;
    }

    public String getUser () {
        return username;
    }

    public int getTime () {
        return time;
    }

    public int getHints () {
        return hints;
    }
    public int getScores() {
        return scores;
    }


    @Override
    public int compareTo(Ranking ranking) {
        int id = ranking.getKakuroId();
        int sc = ranking.getScores();
        if (this.kakuro_id == id) return sc - this.scores;
        else return this.kakuro_id - id;
    }

    @Override
    public String toString() {
        return "KAKURO_ID:" + kakuro_id + ", USERNAME: " + username + ", TIME:" + time + ", HINTS: " + hints + ", SCORES: " + scores;
    }
}
