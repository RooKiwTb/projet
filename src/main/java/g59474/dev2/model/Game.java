package g59474.dev2.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Grid grid;
    private Player[] players;
    private int currentPlayer;


    public Game(Player[] players) {
        this.players = players;
        Grid grid = new Grid();
        this.currentPlayer = 0;
    }

    public void first(Direction d, int... is) {
        for (int i: is) {
            new Grid().firstAdd(d , getCurrentPlayerHand().get(i) );
        }
    }

    public void play(int row, int col, int index){
        new Grid().add(row, col, getCurrentPlayerHand().get(index));
    }
    public void play(int row, int col, Direction d, int... indexes){
        for (int i:indexes) {
            new Grid().add(row, col, d, getCurrentPlayerHand().get(i));
        }

    }
    public void play (int... is){
        for (int i = 0; i < is.length; i = i + 3) {
            TileAtPosition tap = new TileAtPosition(i,i+1, getCurrentPlayerHand().get(i+2)) ;
        }

    }

    public String getCurrentPlayerName(){
        return (String)Array.get(players, currentPlayer);
    }

    public List<Tile> getCurrentPlayerHand(){
        return new Player(getCurrentPlayerName()).getHand();
    }
    public void pass(){
        currentPlayer++;
    }

}