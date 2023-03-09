package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUF2;
    private boolean sites[][];
    private int N;

    private int top;
    private int botton;

    private int numberOfOpen;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IndexOutOfBoundsException("invaild parameter, N = " + N);
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
        weightedQuickUnionUF2 = new WeightedQuickUnionUF(N * N + 1);
        sites = new boolean[N][N];
        this.N = N;
        numberOfOpen = 0;
        top = N * N;
        botton = top + 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sites[i][j] = false;
            }
        }
    }

    private void check(int row, int col) {
        if(col >= N || row >= N || col < 0 || row < 0) {
            throw new IndexOutOfBoundsException("invaild parameter, N = " + N +
                    " row = " + row + " col = " + col);
        }
    }

    private int xyToIndex(int row, int col) {
        return row * N + col;
    }

    private void union(int row, int col, int row2, int col2) {
        if(row2 < 0 || row2 >= N || col2 < 0 || col2 >= N || !sites[row2][col2]) {
            return;
        }
        weightedQuickUnionUF.union(xyToIndex(row, col), xyToIndex(row2, col2));
        weightedQuickUnionUF2.union(xyToIndex(row, col), xyToIndex(row2, col2));
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        if (!sites[row][col]) {
            sites[row][col] = true;
            union(row, col, row - 1, col);
            union(row, col, row + 1, col);
            union(row, col, row, col - 1);
            union(row, col, row, col + 1);
            ++numberOfOpen;
            if (row == 0) {     // if item in first line is open, connect to virtual top
                weightedQuickUnionUF.union(top, xyToIndex(row, col));
                weightedQuickUnionUF2.union(top, xyToIndex(row, col));
            }
            if (row == N - 1) {
                //if (isFull(row, col)) { // if item in last line is open and isfull, connect to virtual bottom
                    weightedQuickUnionUF.union(botton, xyToIndex(row, col));
                //}
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        /*
        for (int i = 0; i < N; ++i) {
            if (sites[0][i]) {
                return weightedQuickUnionUF.connected(xyTo1D(0, i), xyTo1D(row, col));
            }
        }
         */
        return weightedQuickUnionUF2.connected(top, xyToIndex(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        /*
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (sites[0][i] && sites[N-1][j]) {
                    if (weightedQuickUnionUF.connected(xyTo1D(0, i), xyTo1D(N-1, j)))
                        return true;
                }
            }
        }
         */
        return weightedQuickUnionUF.connected(top, botton);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {


    }
}
