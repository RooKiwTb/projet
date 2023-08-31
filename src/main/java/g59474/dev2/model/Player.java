package g59474.dev2.model;

import g59474.dev2.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author g59474
 * */
public class Player implements Serializable {

    private String name;
    private int score;
    private ArrayList<Tile> tiles;

    public Player(String n){
        this.name = n;
        this.score = 0;
        tiles = new ArrayList<>();
        refill();
    }
    public List<Tile> getHand(){
        return tiles;
    }
    public String getName(){
        return this.name;
    }

    public void refill(){
        Tile[] l = Bag.getInstance().getRandomTiles(6 - tiles.size());

        if (l == null){
            return;
        }

        for (int i = 0; i < l.length; i++){
            tiles.add(l[i]);
        }

    }
    public void remove(Tile... ts){
        for (Tile t : ts) {
            tiles.remove(t);
        }
        refill();
    }

    public int getScore(){
        return this.score;
    }

    public void addScore(int value){
        this.score += value;
        View.displayAddScore(this, value);
    }

}

