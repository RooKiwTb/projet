package g59474.dev2.model;
/**
 * @author g59474
 * */
public record TileAtPosition(int row, int col, Tile tile) {

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Tile getTile() {
        return tile;
    }
}
