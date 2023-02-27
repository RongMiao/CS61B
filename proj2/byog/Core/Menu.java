package byog.Core;


import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Menu {
    public void showMenu() {
        StdDraw.setCanvasSize(Utils.MENU_WIDTH * 16, Utils.MENU_HEIGHT * 16);
        StdDraw.setXscale(0, Utils.MENU_WIDTH);
        StdDraw.setYscale(0, Utils.MENU_HEIGHT);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Monaco", Font.PLAIN, 60);
        StdDraw.setFont(font);
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT * 3 / 4, "CS61B: GAME");
        Font font2 = new Font("Monaco", Font.PLAIN, 30);
        StdDraw.setFont(font2);
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT / 4 + 3, "New Game (N)");
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT / 4, "Load Game (L)");
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT / 4 - 3, "Quit (Q)");
        StdDraw.show();
    }

    public void showWin() {
        StdDraw.setCanvasSize(Utils.MENU_WIDTH * 16, Utils.MENU_HEIGHT * 16);
        StdDraw.setXscale(0, Utils.MENU_WIDTH);
        StdDraw.setYscale(0, Utils.MENU_HEIGHT);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Monaco", Font.PLAIN, 50);
        StdDraw.setFont(font);
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT / 2, "Congratulations, you win !");
        Font font2 = new Font("Monaco", Font.PLAIN, 30);
        StdDraw.setFont(font2);
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT / 4 + 3, "press any key to quit");
        StdDraw.show();
    }

    public void showBreak() {
        StdDraw.setCanvasSize(Utils.MENU_WIDTH * 16, Utils.MENU_HEIGHT * 16);
        StdDraw.setXscale(0, Utils.MENU_WIDTH);
        StdDraw.setYscale(0, Utils.MENU_HEIGHT);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Monaco", Font.PLAIN, 60);
        StdDraw.setFont(font);
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT / 2, "break !");
        Font font2 = new Font("Monaco", Font.PLAIN, 30);
        StdDraw.setFont(font2);
        StdDraw.text(Utils.MENU_WIDTH/2, Utils.MENU_HEIGHT / 4 + 3, "press any key to quit");
        StdDraw.show();
    }

    public long getSeed() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setFont(new Font("Monaco", Font.PLAIN, 20));
        StdDraw.text(Utils.MENU_WIDTH / 2, 3 * Utils.MENU_HEIGHT / 4, "Please enter a random seed:");
        StdDraw.show();
        StringBuilder seed = new StringBuilder();
        char c;
        while (true) {
            c = Utils.getInput();
            if (c == '\n') {
                if (seed.length() == 0) {
                    continue;
                } else {
                    break;
                }
            }
            if (c > 57 || c < 48) {
                continue;
            }
            seed.append(c);
            StdDraw.clear(Color.BLACK);
            StdDraw.text(Utils.MENU_WIDTH / 2, 3 * Utils.MENU_HEIGHT / 4, "Please enter a random seed:");
            StdDraw.text(Utils.MENU_WIDTH / 2, Utils.MENU_HEIGHT / 2, seed.toString());
            StdDraw.show();
        }
        return Long.parseLong(seed.toString());
    }
}
