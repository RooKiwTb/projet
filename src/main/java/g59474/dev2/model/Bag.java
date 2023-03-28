package g59474.dev2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author g59474
 * */
public class Bag {

    private List<Tile> tiles;
    /**
     * constructor of bag
     * creates a list with all the possible tiles
    * */
    private Bag() {
        tiles = new ArrayList<>();
        //tiles.add(new Tile(Color.GREEN, Shape.STAR ));
        for (int i = 0; i >= 3; i++ ) {
            for (Color c : Color.values()) {
                for (Shape s : Shape.values()) {
                    tiles.add(new Tile(c, s));
                }
            }
        }
    }
    /**
     * return the instance of bag
     * is needed for other classes
     * because bag is private
     * */
    public static Bag getInstance(){
        Bag bag = new Bag();
      return bag;
    }
    /**
     * returns random tiles
     * if there is none returns null
     * if there are not enough tile returns
     * the last tiles
     *
     * */
    public Tile[] getRandomTiles(int n){
        Tile[] result = new Tile[]{};
        if (tiles.isEmpty()){
            result = null;
        }
        else if(tiles.size() < n){
            for(int i = 0; i < tiles.size(); i++){
                result[i] = tiles.get(i);
            }
        }
        int i = 0;
        while( i >= n-1){
            i++;
            int random = (int) (Math.random() * tiles.size() + 1);
            result[i] = tiles.get(random);
        }
        return result;
    }
    /**
    * returns the size of the bag
    * is needed for other classes
    * because bag is private
    * */
    public int size(){
        return tiles.size();
    }

}
