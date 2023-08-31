package g59474.dev2.model;
/**
 * Color represent color of tile
 * @author g59474
 * */
public enum Color {
    BLUE, RED, GREEN, ORANGE, YELLOW, PURPLE;

    public static String symbol(int index){
        if (index > Color.values().length || index < 0){
            throw new QwirkleException("Index for color out of bounds");
        }
        String[] symbols = {"B", "R", "G", "O", "Y", "P"};
        return symbols[index];
    }

    public static String symbol(Color color){
        return symbol(color.ordinal());
    }
}

