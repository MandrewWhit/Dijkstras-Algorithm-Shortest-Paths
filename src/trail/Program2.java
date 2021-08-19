/*
 * Name: Michael Whitaker
 * EID: maw5299
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission
package trail;
import java.util.ArrayList;
import java.util.*;

public class Program2 {
    private ArrayList<RestStop> stops;  // this is a list of all RestStops, populated by Driver class
    private Heap minHeap;

    // additional constructor fields may be added, but don't delete or modify anything already here
    public Program2(int numStops) {
        minHeap = new Heap();
        stops = new ArrayList<RestStop>();
    }

    /**
     * findMinimumRouteDistance(RestStop start, RestStop dest)
     *
     * @param start - the starting RestStop.
     * @param dest  - the end (destination) RestStop.
     * @return the minimum distance possible to get from start to dest.
     * Assume the given graph is always connected.
     */
    public int findMinimumRouteDistance(RestStop start, RestStop dest) {
        // TODO: implement this function
    	// Set Vd to infinity or 0
    	int startIndex = 0;
    	for(int i=0;i<stops.size();i++) {
    		if(stops.get(i).getName()==start.getName()) {
    			stops.get(i).setMinDist(0);
    			startIndex = i;
    		}else {
    			stops.get(i).setMinDist(Integer.MAX_VALUE);
    		}
    	}
    	
    	
    	minHeap.buildHeap(stops);
    	int returnLength = 0;
    	
    	
    	while(!minHeap.isEmpty()) {
    		RestStop currentStop = minHeap.extractMin();
    		for(int i=0;i<currentStop.getNeighbors().size();i++) {
    			RestStop currentNeighbor = currentStop.getNeighbors().get(i);
    			int newPathLength = 0;
    			if(currentStop.getMinDist()==Integer.MAX_VALUE) {
    				newPathLength = currentStop.getWeights().get(i);
    			}else {
    				newPathLength = currentStop.getMinDist() + currentStop.getWeights().get(i);
    			}
    			if(newPathLength<currentNeighbor.getMinDist()) {
    				minHeap.changeKey(currentNeighbor, newPathLength);
    				//minHeap.insertNode(currentNeighbor);
    				if(currentNeighbor.getName()==dest.getName()) {
    					returnLength = currentNeighbor.getMinDist();
    				}
    			}
    		}
    	}
    	
    	
        return returnLength;
    }

    /**
     * findMinimumLength()
     *
     * @return The minimum total pipe length required to connect (span) each rest stop on the given mountain (graph).
     * Assume the given graph is always connected.
     */
    public int findMinimumLength() {
        // TODO: implement this function
    	// create set of edges
    	
    	int[] prevEdge = new int[stops.size()];
    	
    	for(int i=0;i<stops.size();i++) {
    		if(i==0) {
    			stops.get(i).setMinDist(0);
    		}else {
    			stops.get(i).setMinDist(Integer.MAX_VALUE);
    		}
    		prevEdge[i] = -1;
    	}
    	
    	Heap h = new Heap();
    	h.buildHeap(stops);
    	ArrayList<RestStop> mst = new ArrayList<RestStop>();
    	int result = 0;
    	
    	while(mst.size()<stops.size()) {
    		RestStop currentNode = h.extractMin();
    		mst.add(currentNode);
    		for(int i=0;i<currentNode.getNeighbors().size();i++) {
    			RestStop currentNeighbor = currentNode.getNeighbors().get(i);
    			if(currentNeighbor.getMinDist()>(currentNode.getMinDist()+currentNode.getWeights().get(i))) {
    				if(prevEdge[currentNeighbor.getName()]==-1) {
    					result += currentNode.getWeights().get(i);
    					prevEdge[currentNeighbor.getName()] = currentNode.getWeights().get(i);
    				}else {
    					result -= prevEdge[currentNeighbor.getName()];
    					result += currentNode.getWeights().get(i);
    					prevEdge[currentNeighbor.getName()] = currentNode.getWeights().get(i);
    				}
    				
    				h.changeKey(currentNeighbor, (currentNode.getMinDist()+currentNode.getWeights().get(i)));
    			}
    		}
    	}
    	return result;
    	
    	
    }

    //returns edges and weights in a string.
    public String toString() {
        String o = "";
        for (RestStop v : stops) {
            boolean first = true;
            o += "Rest stop ";
            o += v.getName();
            o += " has neighbors ";
            ArrayList<RestStop> ngbr = v.getNeighbors();
            for (RestStop n : ngbr) {
                o += first ? n.getName() : ", " + n.getName();
                first = false;
            }
            first = true;
            o += " with distances ";
            ArrayList<Integer> wght = v.getWeights();
            for (Integer i : wght) {
                o += first ? i : ", " + i;
                first = false;
            }
            o += System.getProperty("line.separator");

        }

        return o;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public Heap getHeap() {
        return minHeap;
    }

    public ArrayList<RestStop> getAllStops() {
        return stops;
    }

    // used by Driver class to populate each RestStop with correct neighbors and corresponding weights
    public void setEdge(RestStop curr, RestStop neighbor, Integer weight) {
        curr.setNeighborAndWeight(neighbor, weight);
    }

    // used by Driver.java and sets stops to reference an ArrayList of all RestStops
    public void setAllNodesArray(ArrayList<RestStop> x) {
        stops = x;
    }
}
