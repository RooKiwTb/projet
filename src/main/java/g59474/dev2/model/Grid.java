package g59474.dev2.model;

import java.io.Serializable;

/**
 *
 * @author g59474
 * */
public class Grid implements Serializable {

    private Tile[][] tiles;
    private boolean isEmpty;

    /**
     * Creates a board of 91 by 91 tiles
     * */
    public Grid() {
        this.tiles = new Tile[91][91];
        isEmpty = true;
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
    public int firstAdd(Direction d, Tile... line){
        if(!isEmpty){
            throw new QwirkleException("The board must be empty at the beginning");
        }

        for (int i = 0; i < line.length - 2; i++){
            Tile tile1 = line[i];
            Tile tile2 = line[i+1];
            if (!checkColor(tile1, tile2) && !checkShape(tile1, tile2)) {
                throw new QwirkleException("The tiles don't match!!");
            }
        }

        for (int i = 0; i < line.length; i++){
            int x = (tiles.length/2)+ d.getDeltaRow() * i;
            int y = (tiles[0].length/2)+ d.getDeltaColumn() * i;
            tiles[x][y] = line[i];
        }
        int score = line.length;
        return score;
    }
    /**
     * This method is used when a single given tile is added to the board
     * */
    public int add(int row, int col, Tile tile){
        addTileAt(row, col, tile);
        int score = checkTurnScore(row, col);
        return score;
    }
    /**
     * This method is used when more than one tile is added to the board
     * */
    public int add(int row, int col, Direction d, Tile...line){
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                if (!checkColor(line[i], line[j]) && !checkShape(line[i], line[j])) {
                    throw new QwirkleException("The tiles don't match");
                }
            }
        }
        for (int i = 0; i < line.length; i++){
            int y = row + d.getDeltaRow() * i;
            int x = col + d.getDeltaColumn() * i;
            addTileAt(y, x, line[i]);
        }
        int score = checkTurnScore(row, col);
        return score;
    }
    /**
     * This method is used when more than one tile is added,
     * but they aren't next to each other
     **/
    public void add(TileAtPosition... line){
        for (TileAtPosition t :line) {
            addTileAt(t.getRow(), t.getCol(), t.getTile());
        }
    }

    /**
     * Function that puts a given tile at a given position
     * Throws an exception when given invalid arguments
     **/
    public void addTileAt(int row, int col, Tile tile){
        if (!validPosition(row, col)){
            throw new QwirkleException("Index out of bounds!");
        }
        if (tile == null){
            throw new QwirkleException("Given tile is null!");
        }

        checkLine(row, col, tile);
        tiles[row][col] = tile;
    }

    /**
     * This method checks if the board is empty
     * and returns a boolean
     * */
    public boolean isEmpty(){
        isEmpty = true;
        for(int i = 0; i < tiles.length; i++){
            for(int j =0; j < tiles[0].length; j++){
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
    private boolean checkColor(Tile tile1, Tile tile2){
        return tile1.color() == tile2.color();

    }
    private boolean checkShape(Tile tile1, Tile tile2){
        return tile1.shape() == tile2.shape();

    }
    private void checkLine(int row, int col, Tile tile){
        if(!checkEmpty(row, col)){
            throw new QwirkleException("There is no tile near the given position");
        }
        for (Direction dir : Direction.values()){
            int nextRow = row + dir.getDeltaRow();
            int nextCol = col + dir.getDeltaColumn();

            while (validPosition(nextRow, nextCol)) {
                if (tiles[nextRow][nextCol] == null){
                    break;
                }
                else if (!checkColor(tiles[nextRow][nextCol], tile) && !checkShape(tiles[nextRow][nextCol], tile)) {
                    throw new QwirkleException("The near tile does not match with the given tile");
                }
                nextRow = nextRow + dir.getDeltaRow();
                nextCol = nextCol + dir.getDeltaColumn();
            }
        }
    }

    public int checkTurnScore(int row, int col){
        if (tiles[row][col] == null){
            throw new QwirkleException("No tile at given position");
        }

        int currentScore = 0;

        // Calculate the score with all surrounding lines
        for (Direction dir : Direction.values()){
            int currentLength = 1;
            for (int i = 1; i < 45; i++){
                int currentRow = row + dir.getDeltaRow() * i;
                int currentCol = col + dir.getDeltaColumn() * i;

                if (!tileExistsAt(currentRow, currentCol)){
                    // If i is null at 6, it means the line has a length of 6 tiles, thus we score 6 points
                    if (i == 6){
                        currentScore += 6;
                    }
                    break;
                }

                currentLength = 2;

                // Check if a certain tile has another line connected to it
                // Tiles connected to 2 lines give 2 points
                if (dir == Direction.RIGHT || dir == Direction.LEFT){
                    if (tileExistsAt(currentRow + 1, currentCol) || tileExistsAt(currentRow - 1, currentCol)){
                        currentScore++;
                    }
                }
                if (dir == Direction.UP || dir == Direction.DOWN){
                    if (tileExistsAt(currentRow, currentCol + 1) || tileExistsAt(currentRow, currentCol - 1)){
                        currentScore++;
                    }
                }
                currentScore++;
            }
        }

        // Calculate the score of the center tile
        // it can give 2 points if it's in 2 lines, instead of 1
        if ((tileExistsAt(row - 1, col) || tileExistsAt(row + 1, col)) &&
                (tileExistsAt(row, col + 1) || tileExistsAt(row, col - 1))){
            currentScore += 2;
        } else {
            currentScore += 1;
        }

        return currentScore;
    }

    private boolean validPosition(int row, int col){
        return row >= 0 && row <= 90 && col >=0 && col<=90 ;
    }

    private boolean tileExistsAt(int row, int col){
        if (!validPosition(row, col)){
            return false;
        }
        return tiles[row][col] != null;
    }

}


