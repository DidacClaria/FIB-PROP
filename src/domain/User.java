package domain;

public class User {

    //ATTRIBUTES
    private Kakuro[] createdKakuros;

    private Game[] gamesPlayed;

    private CtrlUser ctrlUser;

    private String name;

    //CONSTRUCTORS
    public User() {
    }

    public User(Kakuro[] createdKakuros, Game[] gamesPlayed, CtrlUser ctrlUser, String name) {
        this.createdKakuros = createdKakuros;
        this.gamesPlayed = gamesPlayed;
        this.ctrlUser = ctrlUser;
        this.name = name;
    }

    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Kakuro[] getCreatedKakuros() {
        return createdKakuros;
    }

    public void setCreatedKakuros(Kakuro[] createdKakuros) {
        this.createdKakuros = createdKakuros;
    }

    public Game[] getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Game[] gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public CtrlUser getCtrlUser() {
        return ctrlUser;
    }

    public void setCtrlUser(CtrlUser ctrlUser) {
        this.ctrlUser = ctrlUser;
    }
}
