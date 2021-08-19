package trail;

import java.util.ArrayList;

public class EdgeHeap {
	private ArrayList<Edge> minHeap;
	private int[] heapLocation;

    public EdgeHeap() {
        minHeap = new ArrayList<Edge>();
    }
    
    public boolean isEmpty() {
    	if(minHeap.size()==0) {
    		return true;
    	}else {
    		return false;
    	}
    }

    /**
     * buildHeap(ArrayList<RestStop> stops)
     * Given an ArrayList of RestStops, build a min-heap keyed on each RestStop's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     *
     * @param stops
     */
    public void buildHeap(ArrayList<Edge> edges) {
        // TODO: implement this method
    	/*
    	for(int i=0;i<edges.size();i++) {
    		insertNode(edges.get(i));
    	}
    	*/
    	heapLocation = new int[edges.size()];
    	for(int i=0;i<edges.size();i++) {
    		heapLocation[i] = -1;
    	}
    	for(int i=0;i<edges.size();i++) {
    		insertNode(edges.get(i));
    	}
    }

    /**
     * insertNode(RestStop in)
     * Insert a RestStop into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the RestStop to insert.
     */
    public void insertNode(Edge in) {
        // TODO: implement this method
    	minHeap.add(in);
    	int currentNodeIndex = minHeap.size()-1;
    	int parent = (currentNodeIndex-1)/2;
    	boolean notEnd = true;
    	//heapify up
    	while(minHeap.get(parent).getLength()>in.getLength() && notEnd) {
    		if(parent==0) {
    			notEnd = false;
    		}
    		Edge p = minHeap.get(parent);
    		minHeap.set(parent, in);
    		minHeap.set(currentNodeIndex, p);
    		currentNodeIndex = parent;
    		if(currentNodeIndex==1 || currentNodeIndex==2) {
    			parent = 0;
    		}else {
    			parent = (currentNodeIndex-1)/2;
    		}
    	}
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public Edge findMin() {
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public Edge extractMin() {
        // TODO: implement this method
        // pop min element off the heap
    	Edge min = minHeap.get(0);
    	Edge swap = minHeap.get(minHeap.size()-1);
    	minHeap.set(0, swap);
    	int currentIndex = 0;
    	int childIndex1 = 1;
    	int childIndex2 = 2;
    	//heapify down
    	boolean childrenExist = true;
    	if(childIndex1>minHeap.size() && childIndex2>minHeap.size()) {
    		childrenExist = false;
    	}
    	while(childrenExist) {
    		if(childIndex1<minHeap.size() && childIndex2<minHeap.size()) {
	    		if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex1).getLength()) {
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex1);
	    			minHeap.add(currentIndex, c);
	    			minHeap.remove(currentIndex+1);
	    			minHeap.add(childIndex1, p);
	    			minHeap.remove(childIndex1+1);
	    			currentIndex = childIndex1;
	    			childIndex1 = (currentIndex*2)+1;
	    			childIndex2 = (currentIndex*2) + 2;
	    		}else if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex2).getLength()){
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex2);
	    			minHeap.add(currentIndex, c);
	    			minHeap.remove(currentIndex+1);
	    			minHeap.add(childIndex2, p);
	    			minHeap.remove(childIndex2+1);
	    			currentIndex = childIndex2;
	    			childIndex1 = (currentIndex*2)+1;
	    			childIndex2 = (currentIndex*2) + 2;
	    		}else {
	    			childrenExist = false;
	    		}
    		}else if(childIndex1<minHeap.size()) {
    			if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex1).getLength()) {
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex1);
	    			minHeap.add(currentIndex, c);
	    			minHeap.remove(currentIndex+1);
	    			minHeap.add(childIndex1, p);
	    			minHeap.remove(childIndex1+1);
	    			currentIndex = childIndex1;
	    			childIndex1 = (currentIndex*2)+1;
	    			childIndex2 = (currentIndex*2) + 2;
	    		}else {
	    			childrenExist = false;
	    		}
    		}else if(childIndex2<minHeap.size()) {
    			if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex2).getLength()) {
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex2);
	    			minHeap.add(currentIndex, c);
	    			minHeap.remove(currentIndex+1);
	    			minHeap.add(childIndex2, p);
	    			minHeap.remove(childIndex2+1);
	    			currentIndex = childIndex2;
	    			childIndex1 = (currentIndex*2)+1;
	    			childIndex2 = (currentIndex*2) + 2;
	    		}else {
	    			childrenExist = false;
	    		}
    		}else {
    			childrenExist = false;
    		}
    	}
    	return min;
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
        // TODO: implement this method
    	Edge swap = minHeap.get(minHeap.size()-1);
    	minHeap.set(index, swap);
    	int currentIndex = index;
    	int childIndex1 = index*2;
    	int childIndex2 = (index*2)+1;
    	//heapify down
    	boolean childrenExist = true;
    	if(childIndex1>minHeap.size() && childIndex2>minHeap.size()) {
    		childrenExist = false;
    	}
    	while(childrenExist) {
    		if(childIndex1<minHeap.size() && childIndex2<minHeap.size()) {
	    		if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex1).getLength()) {
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex1);
	    			minHeap.set(currentIndex, c);
	    			minHeap.set(childIndex1, p);
	    			currentIndex = childIndex1;
	    			childIndex1 = currentIndex*2;
	    			childIndex2 = (currentIndex*2) + 1;
	    		}else if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex2).getLength()){
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex2);
	    			minHeap.set(currentIndex, c);
	    			minHeap.set(childIndex2, p);
	    			currentIndex = childIndex2;
	    			childIndex1 = currentIndex*2;
	    			childIndex2 = (currentIndex*2) + 1;
	    		}else {
	    			childrenExist = false;
	    		}
    		}else if(childIndex1<minHeap.size()) {
    			if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex1).getLength()) {
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex1);
	    			minHeap.set(currentIndex, c);
	    			minHeap.set(childIndex1, p);
	    			currentIndex = childIndex1;
	    			childIndex1 = currentIndex*2;
	    			childIndex2 = (currentIndex*2) + 1;
	    		}else {
	    			childrenExist = false;
	    		}
    		}else if(childIndex2<minHeap.size()) {
    			if(minHeap.get(currentIndex).getLength()>minHeap.get(childIndex2).getLength()) {
	    			Edge p = minHeap.get(currentIndex);
	    			Edge c = minHeap.get(childIndex2);
	    			minHeap.set(currentIndex, c);
	    			minHeap.set(childIndex2, p);
	    			currentIndex = childIndex2;
	    			childIndex1 = currentIndex*2;
	    			childIndex2 = (currentIndex*2) + 1;
	    		}else {
	    			childrenExist = false;
	    		}
    		}else {
    			childrenExist = false;
    		}
    	}
    	
    }
}
