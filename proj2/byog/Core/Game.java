package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;


public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */

    private Player player;
    Menu menu;

    public Game() {
        menu = new Menu();
    }
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        menu.showMenu();
        char option = Utils.getOption();
        if (option == 'n') {
            Long seed = menu.getSeed();
            start(seed);
        } else if (option == 'l') {
            System.out.println("load game");
        } else if (option == 'q') {
            System.out.println("quit");
        }
    }

    private void init(long seed) {
        StdDraw.enableDoubleBuffering();
        // create map
        GameMap gameMap =  GameMap.create(seed, Utils.MAP_WIDTH, Utils.MAP_HEIGHT);
        // create player
        player = new Player(gameMap);
    }

    private void start(long seed) {
        init(seed);
        boolean isWin = false;
        while (true) {
            char c = Utils.getInput();
            if (c == 'w' || c == 's' || c == 'a' || c == 'd') {
                isWin = player.move(c);
                if (isWin) {
                    break;
                }
            } else if (c == 'q') {
                break;
            }
        }
        if (isWin) {
            menu.showWin();
        } else {
            menu.showBreak();
        }
        // wait for quit
        char c = Utils.getInput();
        System.exit(0);
    }


    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
