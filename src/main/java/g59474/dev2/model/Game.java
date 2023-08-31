package g59474.dev2.model;

import g59474.dev2.view.View;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Game implements Serializable {

    private Grid grid;
    private Player[] players;
    private int currentPlayer;
    private boolean isOver = false;


    public Game(String[] names) {
        players = new Player[names.length];
        for (int i = 0; i < names.length; i++){
            players[i] = new Player(names[i]);
        }
        grid = new Grid();
        this.currentPlayer = 0;
    }

    public void first(Direction d, int... is) {
        Tile[] tiles = new Tile[is.length];
        for (int i = 0; i < tiles.length; i++) {
            if (is[i] >= 6) {
                throw new QwirkleException("Index out of bounds!");
            }
            tiles[i] = getCurrentPlayerHand().get(is[i]);
        }
        int score = grid.firstAdd(d, tiles);;
        getCurrentPlayer().addScore(score);
        getCurrentPlayer().remove(tiles);
        endGameCheck();
    }

    public void play(int row, int col, int index){
        if (index >= 6){
            throw new QwirkleException("Index out of bounds!");
        }
        int score = grid.add(row, col, getCurrentPlayerHand().get(index));
        getCurrentPlayer().addScore(score);
        getCurrentPlayer().remove(getCurrentPlayerHand().get(index));
        endGameCheck();
    }
    public void play(int row, int col, Direction d, int... indexes){
        Tile[] tiles = new Tile[indexes.length];
        for (int i = 0; i < tiles.length; i++) {
            if (indexes[i] >= 6) {
                throw new QwirkleException("Index out of bounds!");
            }
            tiles[i] = getCurrentPlayerHand().get(indexes[i]);
        }
        int score = grid.add(row, col, d, tiles);
        getCurrentPlayer().addScore(score);
        getCurrentPlayer().remove(tiles);
        endGameCheck();
    }

    public void play(int... is){
        for (int i = 0; i < is.length; i = i + 3) {
            TileAtPosition tap = new TileAtPosition(i,i+1, getCurrentPlayerHand().get(i+2)) ;
        }
        int score = 0;
        players[currentPlayer].addScore(score);
        endGameCheck();
    }

    public String getCurrentPlayerName(){
        return players[currentPlayer].getName();
    }

    public List<Tile> getCurrentPlayerHand(){
        return players[currentPlayer].getHand();
    }

    public int getCurrentPlayerScore(){
        return players[currentPlayer].getScore();
    }

    public Player getCurrentPlayer(){
        return players[currentPlayer];
    }

    public Player[] getPlayers(){
        return players;
    }

    public void pass(){
        currentPlayer++;
        if (currentPlayer > players.length - 1){
            currentPlayer = 0;
        }
    }

    private void endGameCheck(){
        if (getCurrentPlayerHand().size() == 0){
            getCurrentPlayer().addScore(6);
            this.isOver = true;
        }
    }

    public boolean isGameOver(){
        return isOver;
    }

    public GridView getGrid(){
        return new GridView(this.grid);
    }

    public void write(String saveName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(saveName));
            outputStream.writeObject(this);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            throw new QwirkleException("Error saving game" + e.getMessage());
        }
    }

    public static Game getFromFile(String saveName) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(saveName));
            Game loadedGame = (Game) inputStream.readObject();
            System.out.println("Game loaded successfully.");
            return loadedGame;
        } catch (IOException | ClassNotFoundException e) {
            throw new QwirkleException("Error loading game" + e.getMessage());
        }
    }

}