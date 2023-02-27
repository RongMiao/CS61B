package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.ArrayList;

public class Hallway {
    private int hLength;
    private int vLength;
    private Pointer corner;

    /*
                        |  (x1, y1)
                        |
                        |   vlength
                        |
    (x, y)------------ cor  (x1, y)
            hlength
     */
    public Hallway(Pointer corner, int hLength, int vLength) {
        this.corner = corner;
        this.hLength = hLength;
        this.vLength = vLength;
    }

    public static void render(ArrayList<Hallway> hallways, TETile[][] map) {
        // render outer first
        renderOuter(hallways, map);
        // then render inner
        renderInner(hallways, map);
    }
    private static void renderInner(ArrayList<Hallway> hallways, TETile[][] map) {
        for (int i = 0; i < hallways.size(); i++) {
            Hallway hallway = hallways.get(i);
            int start, end;
            // horizon
            if (hallway.hLength < 0) {
                start = hallway.corner.x;
                end = start - hallway.hLength;
            } else {
                end = hallway.corner.x;
                start = end - hallway.hLength;
            }
            for (int j = start; j <= end; j++) {
                map[j][hallway.corner.y] = Tileset.FLOOR;
            }
            // vertical
            if (hallway.vLength < 0) {
                start = hallway.corner.y;
                end = start - hallway.vLength;
            } else {
                end = hallway.corner.y;
                start = end - hallway.vLength;
            }
            for (int j = start; j <= end; j++) {
                map[hallway.corner.x][j] = Tileset.FLOOR;
            }
        }
    }

    private static void renderOuter(ArrayList<Hallway> hallways, TETile[][] map) {
        for (int i = 0; i < hallways.size(); i++) {
            Hallway hallway = hallways.get(i);
            int start, end;
            // horizon
            if (hallway.hLength < 0) {
                start = hallway.corner.x;
                end = start - hallway.hLength;
            } else {
                end = hallway.corner.x;
                start = end - hallway.hLength;
            }
            for (int j = start; j <= end; j++) {
                map[j][hallway.corner.y - 1] = Tileset.WALL;
                map[j][hallway.corner.y + 1] = Tileset.WALL;
            }
            // vertical
            if (hallway.vLength < 0) {
                start = hallway.corner.y;
                end = start - hallway.vLength;
            } else {
                end = hallway.corner.y;
                start = end - hallway.vLength;
            }
            for (int j = start; j <= end; j++) {
                map[hallway.corner.x + 1][j] = Tileset.WALL;
                map[hallway.corner.x - 1][j] = Tileset.WALL;
            }
        }
    }

}
