package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState {
    private static int BLANK = 0;
    private int[][] tiles;
    static int[][] goal;
    private int N;
    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = Arrays.copyOf(tiles[i], tiles[i].length);
        }

        if (goal == null) {
            goal = new int[N][N];
            int cnt = 1;
            for (int i = 0; i < N; i += 1) {
                for (int j = 0; j < N; j += 1) {
                    goal[i][j] = cnt;
                    cnt += 1;
                }
            }
            goal[N - 1][N - 1] = 0;
        }
    }
    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N)
            throw new IndexOutOfBoundsException("i = " + i + " j = " + j);
        return tiles[i][j];
    }
    public int size() {
        return N;
    }
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming() {
        int distance = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (goal[i][j] == BLANK)
                    continue;
                if (goal[i][j] != tiles[i][j])
                    distance++;
            }
        return distance;
    }

    private int getDiff(int s, int i, int j) {
        int x = s / N;
        int y = s % N - 1;
        int distance = Math.abs(x - i) + Math.abs(y - j);
        return distance;
    }
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == BLANK) {
                    continue;
                }
                if (tiles[i][j] != goal[i][j]) {
                    distance += getDiff(tiles[i][j], i, j);
                }
            }
        }
        return distance;
    }
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board board = (Board) y;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board.tiles[i][j] != tiles[i][j])
                    return false;
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
