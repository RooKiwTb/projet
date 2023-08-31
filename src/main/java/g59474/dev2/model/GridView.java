package g59474.dev2.model;

public class GridView {

    private Grid grid;
    GridView(Grid grid) {
        this.grid = grid;
    }

    public Tile get(int row, int col){
        return grid.get(row, col);
    }
    public boolean isEmpty(){
        return grid.isEmpty();
    }


}
