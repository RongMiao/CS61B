package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class GameMap {
    private Random random;
    private int width;
    private int height;
    private TETile[][] map;
    private ArrayList<Room> rooms;
    private ArrayList<Hallway> hallways;
    private TERenderer teRenderer;
    public Pointer door;
    private Pointer playerPosition;
    private int flowers;
    private GameMap(long seed, int width, int height) {
        random = new Random(seed);
        this.width = width;
        this.height = height;
        map = new TETile[width][height];
        rooms = new ArrayList<>();
        hallways = new ArrayList<>();
        teRenderer = new TERenderer();
    }

    private int getRandom(int max) {
        return RandomUtils.uniform(random, max);
    }
    private int getRandom(int min, int max) {
        return RandomUtils.uniform(random, min, max);
    }

    private void init() {
        teRenderer.initialize(width, height);
        // init map
        for (int x = 0; x < width; x++) {
            for (int y= 0; y < height; y++) {
                map[x][y] = Tileset.NOTHING;
            }
        }
        // generate room
        int roomNum = getRandom(Utils.MINROOM_NUM, Utils.MAXROOM_NUM);
        generateRooms(roomNum);
        // generate hallway
        generateHallways();
        // must render room outer first
        Room.renderOuter(rooms, map);
        // then render hallway
        Hallway.render(hallways, map);
        // then render room inner
        Room.renderInnder(rooms, map);
        // then door render
        renderDoor();
        // then door flowers
        renderFlowers();
        render();
    }

    private void render() {
        teRenderer.renderFrame(map);
    }

    private void generateRooms(int n) {
        for (int i = 0; i < n; i++) {
            int roomWidth = getRandom(Utils.MINROOM_WIDTH, Utils.MAXROOM_WIDTH);
            int roomHeight = getRandom(Utils.MINROOM_HEIGHT, Utils.MAXROOM_HEIGHT);
            int x = getRandom(1, width - roomWidth);
            int y = getRandom(1, height - roomHeight);
            Room room = new Room(x, y, roomWidth, roomHeight);
            rooms.add(room);
        }
        Room.sort(rooms);
    }

    private void generateHallways() {
        for (int i = 0; i < rooms.size() - 1; i++) {
            Pointer p1 = rooms.get(i).getRandomInnerPointer(random);
            Pointer p2 = rooms.get(i + 1).getRandomInnerPointer(random);
            Pointer corner;
            int direction = getRandom(2);
            switch (direction) {
                case 0: {   // horizontal first
                    corner = new Pointer(p2.x, p1.y);
                    int hLength = corner.x - p1.x;
                    int vLength = corner.y - p2.y;
                    Hallway hallway = new Hallway(corner, hLength, vLength);
                    /*
                    System.out.println("horizontal, x = " + corner.x +
                            " y = " + corner.y +
                            " hlength = " + hLength +
                            " vlength = " + vLength);
                     */
                    hallways.add(hallway);
                    break;
                }
                case 1: {   // vertical first
                    corner = new Pointer(p1.x, p2.y);
                    int hLength = corner.x - p2.x;
                    int vLength = corner.y - p1.y;
                    Hallway hallway = new Hallway(corner, hLength, vLength);
                    /*
                    System.out.println("vertical, x = " + corner.x +
                            " y = " + corner.y +
                            " hlength = " + hLength +
                            " vlength = " + vLength);
                     */
                    hallways.add(hallway);
                    break;
                }
                default:
                    break;
            }
        }
    }

    private void renderDoor() {
        // door always appear at left wall of first room
        Room room = rooms.get(0);
        Pointer pointer = room.getPointer();
        int x = pointer.x - 1;
        int y = getRandom(room.getRoomHeight()) + pointer.y;
        door = new Pointer(x, y);
        map[x][y] = Tileset.LOCKED_DOOR;
    }

    private void renderFlowers() {
        // generate n flowers, only save counts
        int n = getRandom(Utils.MINROOM_NUM, rooms.size());
        HashMap<Integer, Pointer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int index = getRandom(rooms.size());
            while (hashMap.containsKey(index)) {
                index = getRandom(rooms.size());
            }
            Pointer pointer = rooms.get(index).getPointer();
            hashMap.put(index, pointer);
            map[pointer.x][pointer.y] = Tileset.FLOWER;
        }
        flowers = hashMap.size();
    }

    public Pointer getCurrentPosition() {
        if (playerPosition == null) {
            // init player postion, always at top right corner of last room
            Room room = rooms.get(rooms.size() - 1);
            Pointer pointer = room.getPointer();
            int x = pointer.x + room.getRoomWidth() - 1;
            int y = pointer.y + room.getRoomHeight() - 1;
            map[x][y] = Tileset.PLAYER;
            playerPosition = new Pointer(x, y);
            render();
            return playerPosition;
        }
        return playerPosition;
    }
    public boolean update(Pointer nextPosition) {
        if (map[nextPosition.x][nextPosition.y] == Tileset.WALL) {
            return false;
        } else if (map[nextPosition.x][nextPosition.y] == Tileset.FLOOR) {
            map[playerPosition.x][playerPosition.y] = Tileset.FLOOR;
            map[nextPosition.x][nextPosition.y] = Tileset.PLAYER;
            playerPosition = nextPosition;
        } else if (map[nextPosition.x][nextPosition.y] == Tileset.FLOWER) {
            // get flower
            flowers--;
            map[playerPosition.x][playerPosition.y] = Tileset.FLOOR;
            map[nextPosition.x][nextPosition.y] = Tileset.PLAYER;
            playerPosition = nextPosition;
        } else if(map[nextPosition.x][nextPosition.y] == Tileset.LOCKED_DOOR && flowers == 0) {
            // get all flowsers the reach the door
            map[playerPosition.x][playerPosition.y] = Tileset.FLOOR;
            map[nextPosition.x][nextPosition.y] = Tileset.PLAYER;
            playerPosition = nextPosition;
        }
        render();
        // check if arrived at door
        if (playerPosition.x == door.x && playerPosition.y == door.y) {
            return true;
        } else {
            return false;
        }
    }
    public static GameMap create(long seed, int width, int height) {
        GameMap map = new GameMap(seed, width, height);
        map.init();
        return map;
    }
}
