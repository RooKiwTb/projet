package g59474.dev2.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g59474
 * */
public class Bag {

    private List<Tile> tiles;

    private Bag() {
        tiles = new ArrayList<>();
        tiles.add(Tile(GREEN,STAR ));
    }


    public static Bag getInstance(){

      return ;
    }

    public Tile getRandomTiles(int n){


    }

    public int size(){

    }

}
