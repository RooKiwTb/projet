package g59474.dev2.model;

import java.util.ArrayList;
import java.util.List;

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
            throw new QwirkleException("The board must be empty at the beginning");
        }
        for (Tile t : line){
            tiles[45+ d.getDeltaRow()][45+ d.getDeltaColumn()]= t;
        }
    }
    /**
     * This method is used when a single given tile is added to the board
     * */
    public void add(int row, int col, Tile tile){

        checkLine(row, col, tile);
        tiles [row][col]= tile;
    }
    /**
     * This method is used when more than one tile is added to the board
     * */
    public void add(int row, int col, Direction d, Tile...line){
        //if (Condition)
        List<Tile> ti= new ArrayList<>();
        for (Tile t :line) {
            ti.add(t);
        }
        for (int i = 0; i <ti.size()-1; i++) {
            for (int j = i + 1; j < ti.size(); j++) {
                if (!checkColor(ti.get(i), ti.get(j)) && !checkShape(ti.get(i),ti.get(j))) {

                }
            }
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
    private boolean checkEmpty (int row, int col){
        for (Direction dir: Direction.values()) {
           if(tiles[row + dir.getDeltaRow()][col + dir.getDeltaColumn()] != null){
               return true;
           }
        }
        return  false;
    }
    private boolean checkColor(Tile tile, Tile tuile){
        return tile.color() == tuile.color() && tile.shape()!=tuile.shape();

    }
    private boolean checkShape(Tile tile1, Tile tile2){
        return tile1.shape() == tile2.shape() && tile1.color()!=tile2.color();

    }
    private void checkLine ( int row, int col, Tile tile){

        if(!checkEmpty(row, col)){
            throw new QwirkleException("There is no tile near the given position");
        }
        // ajouter un hashset list
        for( Direction dir : Direction.values()){
            int nextRow = row + dir.getDeltaRow();
            int nextCol = col+ dir.getDeltaColumn();

            while (validPostion(nextRow,nextCol)) {
                if(tiles[nextRow][nextCol]==null){break;}
                else if (!checkColor(tiles[nextRow][nextCol], tile) && !checkShape(tiles[nextRow][nextCol], tile)) {
                    throw new QwirkleException("The near tile does not match with the given tile");
                }
                nextRow = nextRow + dir.getDeltaRow();
                nextCol = nextCol + dir.getDeltaColumn();
            }
        }


        }

    }
    private boolean validPostion(int row, int col){
      return row >= 0 && row <= 90 && col >=0 && col<=90 ;

    }

}


