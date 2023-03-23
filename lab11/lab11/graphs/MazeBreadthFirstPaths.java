package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private ArrayDeque<Integer> queue;
    private int s;
    private int t;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        queue = new ArrayDeque<Integer>();
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        while (!queue.isEmpty()) {
            int w = queue.remove();
            for (int u : maze.adj(w)) {
                if (!marked[u]) {
                    queue.add(u);
                    marked[u] = true;
                    edgeTo[u] = w;
                    distTo[u] = distTo[w] + 1;
                    announce();
                    if (t == u) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        queue.add(s);
        marked[s] = true;
        bfs();
    }
}

