package g59474.dev2.view;

import g59474.dev2.model.*;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class View {

    public static void display(GridView grid){
        if (grid.isEmpty()){
            System.out.println("Grid is empty!");
            return;
        }

        int minI = 91;
        int maxI = 0;
        int minJ = 91;
        int maxJ = 0;

        for (int i = 0; i < 91; i++){
            for (int j = 0; j < 91; j++){
                if (grid.get(i, j) != null) {
                    if (minI > i) minI = i;
                    if (maxI < i) maxI = i;
                    if (minJ > j) minJ = j;
                    if (maxJ < j) maxJ = j;
                }
            }
        }

        String str = "";
        for (int i = minI; i <= maxI; i++){
            str = String.format("%02d", i);
            str += "| ";

            for (int j = minJ; j <= maxJ; j++){
                Tile t = grid.get(i, j);
                if (t == null){
                    str += ".. ";
                    continue;
                }
                str += Color.symbol(t.color()) + Shape.symbol(t.shape()) + " ";
            }
            System.out.println(str);
        }
        str = "    ";
        for (int j = minJ; j <= maxJ; j++){
            str += String.format("%02d", j) + " ";
        }
        System.out.println(str);
    }
    public static void display(Player player){
        System.out.println(player.getName());
        System.out.println("SCORE: " + player.getScore());
        System.out.println("HAND:");

        List<Tile> playerTiles = player.getHand();
        String str = "";
        for (Tile t : playerTiles){
            str += Color.symbol(t.color()) + " " + Shape.symbol(t.shape());
            str += " | ";
        }
        System.out.println(str);

        str = "";
        for (int i = 0; i < playerTiles.size(); i++){
            str += i + "     ";
        }
        System.out.println(str);
    }
    public static void displayHelp(){
        System.out.println(
                "Q W I R K L E\n" +
                        "Qwirkle command:\n" +
                        "- play 1 tile : o <row> <col> <i>\n" +
                        "- play line: l <row> <col> <direction> <i1> [<i2>]\n" +
                        "- play plic-ploc : m <row1> <col1> <i1> [<row2> <col2> <i2>]\n" +
                        "- play first : f <i1> [<i2>]\n" +
                        "- pass : p\n" +
                        "- quit : q\n" +
                        "- show scores : s\n" +
                        "- load savefile : load\n" +
                        "- save game: save\n" +
                        "i : index in list of tiles\n" +
                        "d : direction in l (left), r (right), u (up), d(down)\n");

    }
    public static void displayError(String message){

    }

    public static void displayEnd(Game game){
        System.out.println("GAME IS OVER!");
        View.displayScores(game.getPlayers());
    }

    public static void displayAddScore(Player p, int value){
        System.out.println("==========");
        System.out.println("+" + value + " points for " + p.getName() + " ("+p.getScore()+")");
        System.out.println("==========");
        System.out.println("");
    }


    public static void displayScores(Player[] players){
        Arrays.sort(players, Comparator.comparingInt(Player::getScore).reversed());
        for (int i = 0; i < players.length; i++){
            System.out.println(i + 1 + ". " + players[i].getName() + " (" + players[i].getScore() + ")");
        }
        System.out.printf("");
    }
}

