/*
 * Name: Michael Whitaker
 * EID: maw5299
 */

// Methods may be added to this file, but don't remove anything
// Include this file in your final submission
package trail;
import java.util.*;

public class RestStop {
    private int minDist;
    private int name;
    private ArrayList<RestStop> neighbors;
    private ArrayList<Integer> weights;

    public RestStop(int x) {
        name = x;
        minDist = Integer.MAX_VALUE;
        neighbors = new ArrayList<RestStop>();
        weights = new ArrayList<Integer>();
    }

    public void setNeighborAndWeight(RestStop n, Integer w) {
        neighbors.add(n);
        weights.add(w);
    }

    public ArrayList<RestStop> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Integer> getWeights() {
        return weights;
    }

    public int getMinDist() { return minDist; }

    public void setMinDist(int x) {
        minDist = x;
    }

    public void resetMinDist() {
        minDist = Integer.MAX_VALUE;
    }

    public int getName() {
        return name;
    }
}
