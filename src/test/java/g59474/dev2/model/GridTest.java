package g59474.dev2.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GridTest {

    @Test
    void firstAdd() {
        System.out.println("Case A");
        Grid grid = new Grid();
        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        grid.firstAdd(Direction.UP, t1, t2, t3);

        assertEquals(t1, grid.get(45,45));
        assertEquals(t2, grid.get(44,45));
        assertEquals(t3, grid.get(43,45));
    }

    @Test
    void add() {
        System.out.println("Case C");
        System.out.println("Case B");
        Grid grid = new Grid();
        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        grid.firstAdd(Direction.UP, t1, t2, t3);

        Tile t4 = new Tile(Color.RED, Shape.SQUARE);
        Tile t5 = new Tile(Color.BLUE, Shape.SQUARE);
        Tile t6 = new Tile(Color.PURPLE, Shape.SQUARE);
        grid.add(42, 45, Direction.RIGHT, t4, t5, t6);

        Tile t7 = new Tile(Color.BLUE, Shape.ROUND);
        grid.add(45, 46, t7);

        assertEquals(t7, grid.get(45, 46));
    }

    @Test
    void testAdd() {
        System.out.println("Case B");
        Grid grid = new Grid();
        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        grid.firstAdd(Direction.UP, t1, t2, t3);

        Tile t4 = new Tile(Color.RED, Shape.SQUARE);
        Tile t5 = new Tile(Color.BLUE, Shape.SQUARE);
        Tile t6 = new Tile(Color.PURPLE, Shape.SQUARE);
        grid.add(42, 45, Direction.RIGHT, t4, t5, t6);

        assertEquals(t4, grid.get(42,45));
        assertEquals(t4, grid.get(42,46));
        assertEquals(t4, grid.get(42,47));
    }

    @Test
    void testAdd1() {
    }
}