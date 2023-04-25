package g59474.dev2.model;

/**
 *
 * @author g59474
 * */
public class Grid {

    private Tile[][] tiles;
    private boolean isEmpty;

    /**
     * Creates a board of 91 by 91 tiles
     * */
    public Grid() {
        this.tiles = new Tile[91][91];
    }
    /**
     * Getter of a given position
     * */
    public Tile get(int row, int col){
        return tiles [row][col];
    }
    /**
     * This method is used only once, at the beginning of the game
     * Adds the first tile at the center and the others on the given direction
     * */
    public void firstAdd(Direction d, Tile... line){
        if(!isEmpty){
            throw new QwirkleException();
        }
        for (Tile t : line){
            tiles[45+ d.getDeltaRow()][45+ d.getDeltaColumn()]= t;
        }
    }
    /**
     * This method is used when a single given tile is added to the board
     * */
    public void add(int row, int col, Tile tile){
        if(tiles[row][col-1]==null && tiles[row][col+1]==null && tiles[row-1][col]==null && tiles[row+1][col]==null){
            throw new QwirkleException();
        }
        else if (tiles[row][col-1].color() != tile.color() && tiles[row][col-1].shape() != tile.shape()
                    && tiles[row][col+1].color() != tile.color() && tiles[row][col+1].shape() != tile.shape()
                    && tiles[row-1][col].color() != tile.color() && tiles[row-1][col].shape() != tile.shape()
                    && tiles[row+1][col].color() != tile.color() && tiles[row+1][col].shape() != tile.shape()){
            throw new QwirkleException();
        }
             // \\
            // | \\
           //  °  \\
        // OPTIMISER

        /*for (Direction dir: Direction.values() ) {

        }*/
        else if (tiles[row][col-1].color() == tile.color() || tiles[row][col-1].shape() == tile.shape()
                    || tiles[row][col+1].color() == tile.color() || tiles[row][col+1].shape() == tile.shape()) {
            int i = 0;
            while (tiles[row][col - 1 - i] != null) {
                if (tiles[row][col - 1 - i].color() == tile.color() && tiles[row][col - 1 - i].shape() == tile.shape()) {
                    throw new QwirkleException();
                }
                i++;
            }
            while (tiles[row][col + 1 + i] != null) {
                if (tiles[row][col + 1 + i].color() == tile.color() && tiles[row][col + 1 + i].shape() == tile.shape()) {
                    throw new QwirkleException();
                }
                i++;
            }
        }
        else if (tiles[row-1][col].color() == tile.color() || tiles[row-1][col].shape() == tile.shape()
                    || tiles[row+1][col].color() == tile.color() || tiles[row+1][col].shape() == tile.shape()){
            int i = 0;
            while(tiles[row-1-i][col] != null){
                if(tiles[row-1-i][col].color() == tile.color() && tiles[row-1-i][col].shape() == tile.shape()){
                    throw new QwirkleException();
                }
                i++;
            }
            while(tiles[row+1+i][col] != null){
                if(tiles[row+1+i][col].color() == tile.color() && tiles[row+1+i][col].shape() == tile.shape()){
                    throw new QwirkleException();
                }
                i++;
            }
        }
        tiles [row][col]= tile;
    }
    /**
     * This method is used when more than one tile is added to the board
     * */
    public void add(int row, int col, Direction d, Tile...line){
        //if (Condition)
        if(tiles[row][col-1]==null && tiles[row][col+1]==null && tiles[row-1][col]==null && tiles[row+1][col]==null){
            throw new QwirkleException();
        }
        for (Tile t :line) {

        }
        for (Tile t : line){
            tiles[row+d.getDeltaRow()][col+d.getDeltaColumn()]=t;
        }
    }
    /**
     * This method is used when more than one tile is added,
     * but they aren't next to each other
     * */
    public void add(TileAtPosition... line){
        // if (Conditon)
        for (TileAtPosition t :line) {
            tiles[t.row()][t.col()]= t.tile();
        }
    }
    /**
     * This method checks if the board is empty
     * and returns a boolean
     * */
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


