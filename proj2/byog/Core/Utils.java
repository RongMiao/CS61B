package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

public class Utils {
    public static final int MAP_WIDTH = 80;
    public static final int MAP_HEIGHT = 30;
    public static final int MENU_WIDTH = 40;
    public static final int MENU_HEIGHT = 40;
    public static final int MAXROOM_NUM = 30;
    public static final int MINROOM_NUM = 10;
    public static final int MAXROOM_WIDTH = 8;
    public static final int MINROOM_WIDTH = 4;
    public static final int MAXROOM_HEIGHT = 6;
    public static final int MINROOM_HEIGHT = 3;
    public static char getInput() {
        char c;
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            c = Character.toLowerCase(StdDraw.nextKeyTyped());
            break;
        }
        return c;
    }

    public static char getOption() {
        char option;
        while (true) {
            option = Utils.getInput();
            if (option == 'n' || option == 'l' || option == 'q') {
                break;
            }
        }
        return option;
    }
}
