package g59474.dev2.model;

import java.lang.reflect.Array;
import java.util.List;


/**
 * @author g59474
 * */
public class Player {

    private String name;
    private static List <Tile> tiles;

    public Player(String n){
    }
    public List<Tile> getHand(){return tiles;
    }
    public String getName(){
        return this.name;
    }
    public void refill(){
        Tile[] l = Bag.getInstance().getRandomTiles(6 - tiles.size());
        for (int i = 0; i< l.length - 1; i++){
            tiles.add((Tile) Array.get(l,i));
        }

    }
    public void remove(Tile... ts){
        for (Tile t : ts) {
            tiles.remove(t);
        }
    }

}
