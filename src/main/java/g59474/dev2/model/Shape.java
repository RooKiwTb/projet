package g59474.dev2.model;
/**
 * Shape represent shape of tile
 * @author g59474
 * */
public enum Shape {
    CROSS, SQUARE, ROUND, STAR, PLUS, DIAMOND;

    public static String symbol(int index){
        if (index > Color.values().length || index < 0){
            throw new QwirkleException("Index for symbol out of bounds");
        }
        String[] shapeSymbols = {"✖", "□", "●", "*", "+", "◆"};
        return shapeSymbols[index];
    }

    public static String symbol(Shape shape){
        return symbol(shape.ordinal());
    }
}
