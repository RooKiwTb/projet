package g59474.dev2.model;

/**
 *
 * @author g59474
 * */
public class Grid {

    private Tile[][] tiles;
    private boolean isEmpty;

    public Grid() {
        this.tiles = new Tile[91][91];
    }
    public Tile get(int row, int col){
        return tiles [row][col];
    }
    public void firstAdd(Direction d, Tile... line){
        if(isEmpty == false){
            throw new IllegalArgumentException("Nope");
        }
        for (Tile t : line){
            tiles[45+ d.getDeltaRow()][45+ d.getDeltaColumn()]= t;
        }
    }
    public void add(int row, int col, Tile tile){
        tiles [row][col]= tile;
    }
    public void add(int row, int col, Direction d, Tile...line){
        for (Tile t : line){
            tiles[row+ d.getDeltaRow()][col+ d.getDeltaColumn()]=t;
        }
    }
    public void add(TileAtPosition... line){

    }
    public boolean isEmpty(){
        isEmpty = true;
        for(int i = 0; i <= tiles.length; i++){
            for(int j =0; j<= tiles[0].length; j++){
                if(tiles[i][j] != null){
                    isEmpty = false;
                }
            }
        }
        return isEmpty;
    }
}


