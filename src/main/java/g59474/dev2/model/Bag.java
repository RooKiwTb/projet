package g59474.dev2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 *
 * @author g59474
 * */
public class Bag {

    private List<Tile> tiles;
    public static Bag bagInstance;
    /**
     * constructor of bag
     * creates a list with all the possible tiles
    * */
    private Bag() {
        bagInstance = this;
        boolean isTestBag = false;

        tiles = new ArrayList<>();

        if (isTestBag) {
            for (int i = 0; i < 18; i++){
                tiles.add(new Tile(Color.RED, Shape.DIAMOND));
            }
        } else{
            for (int i = 0; i < 3; i++ ) {
                for (Color c : Color.values()) {
                    for (Shape s : Shape.values()) {
                        tiles.add(new Tile(c, s));
                    }
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
        if (bagInstance == null){
            new Bag();
        }
        return bagInstance;
    }
    /**
     * returns random tiles
     * if there is none returns null
     * if there are not enough tile returns
     * the last tiles
     *
     * */
    public Tile[] getRandomTiles(int n){
        if (tiles.isEmpty()) {
            return null;
        }
        Collections.shuffle(tiles);

        int arraySize = n;
        if (tiles.size() < n) {
            arraySize = tiles.size();
        }

        Tile[] result = new Tile[arraySize];
        for (int i = 0; i < arraySize; i++){
            result[i] = tiles.remove(0);
        }
        System.out.println("LEFT IN BAG:");
        System.out.println(tiles.size());
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
