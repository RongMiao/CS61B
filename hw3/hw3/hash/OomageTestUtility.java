package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */

        int size = oomages.size();
        int[] arr = new int[M];
        int low = size / 50;
        int up = (int)(size / 2.5);
        for (int i = 0; i < size; ++i) {
            int bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            arr[bucketNum]++;
        }
        for (int i = 0; i < M; ++i) {
            if (arr[i] > up || arr[i] < low) {
                return false;
            }
        }
        return true;
    }
}
