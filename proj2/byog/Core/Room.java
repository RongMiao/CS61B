package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Room {
    private int x;
    private int y;
    private int roomWidth;
    private int roomHeight;

    public Room(int x, int y, int roomWidth, int roomHeight) {
        this.x = x;
        this.y = y;
        this.roomWidth = roomWidth;
        this.roomHeight = roomHeight;
    }

    public int getRoomHeight() {
        return roomHeight;
    }

    public int getRoomWidth() {
        return roomWidth;
    }

    public Pointer getPointer() {
        return new Pointer(x, y);
    }
    public static ArrayList<Room> sort(ArrayList<Room> rooms) {
        rooms.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                //return (o1.x + o1.y) - (o2.x + o2.y);
                return o1.x - o2.x;
            }
        });
        /*
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            System.out.println(i + ": " +
                    " x = " + room.x +
                    " y = "+ room.y +
                    " width = " + room.roomWidth +
                    " height = " + room.roomHeight);
        }
        */
        return rooms;
    }

    public static void renderInnder(ArrayList<Room> rooms, TETile[][] map) {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            // render inner
            for (int x = room.x; x < room.x + room.roomWidth; x++) {
                for (int y = room.y; y < room.y + room.roomHeight; y++) {
                    map[x][y] = Tileset.FLOOR;
                }
            }
        }
    }

    public static void renderOuter(ArrayList<Room> rooms, TETile[][] map) {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            // render outer
            for (int x = room.x - 1; x <= room.x + room.roomWidth; x++) {
                map[x][room.y - 1] = Tileset.WALL;
                map[x][room.y + room.roomHeight] = Tileset.WALL;
            }
            for (int y = room.y - 1; y <= room.y + room.roomHeight; y++) {
                map[room.x - 1][y] = Tileset.WALL;
                map[room.x + room.roomWidth][y] = Tileset.WALL;
            }
        }
    }

    public Pointer getRandomInnerPointer(Random random) {
        int px = RandomUtils.uniform(random, x + 1, x + roomWidth - 1);
        int py = RandomUtils.uniform(random, y + 1, y + roomHeight - 1);
        return new Pointer(px, py);
    }

}
