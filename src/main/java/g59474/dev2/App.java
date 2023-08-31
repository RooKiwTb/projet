package g59474.dev2;

import g59474.dev2.view.View;
import g59474.dev2.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static String userInput = "";
    private static Game gameInstance;

    public static void main(String[] args) {

        String[] names = {"Sophie", "Albert", "Sebastien"};
        gameInstance = new Game(names);
        View.displayHelp();


        while (!gameInstance.isGameOver()){
            loop();
        }

        View.displayEnd(gameInstance);
    }

    private static void loop(){

        while (userInput == ""){
            showText();
            userInput = scanner.nextLine();
        }

        try {
            if (interpretCommand(userInput) == false){
                System.out.println("Unknown or invalid command!");
            }
        } catch (QwirkleException e){
            System.out.println(e);
        }

        userInput = "";
    }

    private static void showText(){
        View.display(gameInstance.getGrid());
        System.out.println("");
        View.display(gameInstance.getCurrentPlayer());
    }


    private static boolean interpretCommand(String s){
        String[] args = s.toLowerCase().split(" ");

        if (args[0].equals("q")){
            System.exit(0);
        }


        String command = args[0];
        String[] temp_args;
        int[] int_args;

        switch (command) {
            case "o":
                temp_args = Arrays.copyOfRange(args, 1, 4);
                int_args = getNumbersFromArgs(temp_args);
                if (int_args == null){
                    return false;
                }
                gameInstance.play(int_args[0], int_args[1], int_args[2]);
                gameInstance.pass();
                return true;

            case "m":
                temp_args = Arrays.copyOfRange(args, 1, args.length);
                int_args = getNumbersFromArgs(temp_args);
                if (int_args == null){
                    return false;
                }

                if (int_args.length % 3 != 0){
                    return false;
                }

                for (int i = 0; i < (int_args.length / 3); i++){
                    int j = i * 3;
                    gameInstance.play(int_args[0 + j], int_args[1 + j], int_args[2 + j]);
                    gameInstance.pass();
                }
                return true;


            case "l":
                if (args.length < 5){
                    return false;
                }

                temp_args = Arrays.copyOfRange(args, 1, 3);
                int_args = getNumbersFromArgs(temp_args);
                if (int_args == null){
                    return false;
                }

                int row = int_args[0];
                int col = int_args[1];
                Direction dir = Direction.getDirectionFromLetter(args[3]);

                if (dir == null){
                    return false;
                }

                if (row > 90 || row < 0 || col > 90 || col < 0){
                    return false;
                }

                temp_args = Arrays.copyOfRange(args,4, args.length);
                int[] tile_indexes = getNumbersFromArgs(temp_args);

                if (tile_indexes == null){
                    return false;
                }
                gameInstance.play(row, col, dir, tile_indexes);
                gameInstance.pass();
                return true;

            case "f":
                if (args.length >= 2) {
                    temp_args = Arrays.copyOfRange(args, 1, args.length);
                    int_args = getNumbersFromArgs(temp_args);
                    if (int_args == null){
                        return false;
                    }
                    gameInstance.first(Direction.RIGHT, int_args);
                    gameInstance.pass();
                }
                return true;

            case "p":
                gameInstance.pass();
                return true;

            case "q":
                System.exit(0);

            case "s":
                View.displayScores(gameInstance.getPlayers());
                return true;

            case "h":
                View.displayHelp();
                return true;

            case "save":
                gameInstance.write("savefile");
                return true;

            case "load":
                gameInstance = Game.getFromFile("savefile");
                return true;
        }

        return false;
    }

    private static int[] getNumbersFromArgs(String... args){
        try {
            int[] numbers = new int[args.length];
            for (int i = 0; i < args.length; i++){
                int n = Integer.parseInt(args[i]);
                numbers[i] = n;
            }
            return numbers;
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments");
            return null;
        }
    }

    private static ArrayList<Tile> getTilesFromIndexes(ArrayList<Integer> indexes){
        try {
            ArrayList<Tile> tiles = new ArrayList<>();
            for (int i = 1; i < indexes.size(); i++){
                Tile t = gameInstance.getCurrentPlayerHand().get(indexes.get(i));
                tiles.add(t);
            }
            return tiles;
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments");
            return null;
        }
    }

}
