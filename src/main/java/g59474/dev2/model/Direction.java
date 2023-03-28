package g59474.dev2.model;

public enum Direction {
    UP(1,0),
    DOWN(-1,0),
    LEFT(0,-1),
    RIGHT(0,1);

    private int deltaRow;
    private int deltaColumn;

    private Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }
    public int getDeltaRow() {
        return this.deltaRow;
    }
    public int getDeltaColumn() {
        return this.deltaColumn;
    }
    public Direction opposite(){
        Direction res = LEFT;
        if(this == Direction.LEFT){
            res =RIGHT;
        }
        else if (this == Direction.UP){
            res= DOWN;
        }
        else if (this == Direction.DOWN) {
            res = UP;

        }
        return res;
        //return (this == Direction.UP ? Direction.DOWN : Direction.UP ) || (this == Direction.LEFT ? Direction.RIGHT :Direction.LEFT);
    }

}
