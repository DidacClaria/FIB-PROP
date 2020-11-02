package domain;

import persistence.CtrlPersistence;
import presentation.CtrlPresentation;

public class CtrlDomain {
    //ATTRIBUTES
    private CtrlPersistence ctrlPersistence;

    private CtrlPresentation ctrlPresentation;

    private CtrlUser ctrlUser;

    private CtrlGame ctrlGame;

    private CtrlKakuro ctrlKakuro;

    //CONSTRUCTOR
    public CtrlDomain() {
    }

    public CtrlDomain(CtrlPersistence ctrlPersistence, CtrlPresentation ctrlPresentation, CtrlUser ctrlUser, CtrlGame ctrlGame, CtrlKakuro ctrlKakuro) {
        this.ctrlPersistence = ctrlPersistence;
        this.ctrlPresentation = ctrlPresentation;
        this.ctrlUser = ctrlUser;
        this.ctrlGame = ctrlGame;
        this.ctrlKakuro = ctrlKakuro;
    }

    //GETTERS & SETTERS
    public CtrlPersistence getCtrlPersistence() {
        return ctrlPersistence;
    }

    public void setCtrlPersistence(CtrlPersistence ctrlPersistence) {
        this.ctrlPersistence = ctrlPersistence;
    }

    public CtrlPresentation getCtrlPresentation() {
        return ctrlPresentation;
    }

    public void setCtrlPresentation(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
    }

    public CtrlUser getCtrlUser() {
        return ctrlUser;
    }

    public void setCtrlUser(CtrlUser ctrlUser) {
        this.ctrlUser = ctrlUser;
    }

    public CtrlGame getCtrlGame() {
        return ctrlGame;
    }

    public void setCtrlGame(CtrlGame ctrlGame) {
        this.ctrlGame = ctrlGame;
    }

    public CtrlKakuro getCtrlKakuro() {
        return ctrlKakuro;
    }

    public void setCtrlKakuro(CtrlKakuro ctrlKakuro) {
        this.ctrlKakuro = ctrlKakuro;
    }

    //CLASS METHODS
    public void createUser(String name, String password){

    }

    public void loadUser(String name){

    }

    public void deleteUser(String name){

    }

    public void existUser(String name){

    }

    public void playKakuro(int idGame){

    }

    public void saveGame(int idGame){

    }

    public void deleteGame(int idGame){

    }

    public void generateKakuro(String dificulty, int numRows, int numColumns, int numFilledCells, int numBlackCells, int numWhiteCells){

    }

    public void proposeKakuro(int numRows, int numColumns, String field){

    }

    public boolean solveKakuro(int numRows, int numColumns, String field){
        return false;
    }

    public void consultRanking(){

    }

    public void consultPersonalStats(){

    }
}
