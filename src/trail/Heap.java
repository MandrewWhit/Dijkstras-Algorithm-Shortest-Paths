/*
 * Name: Michael Whitaker
 * EID: maw5299	
 */

// Implement your heap here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission
package trail;
import java.util.ArrayList;

public class Heap {
    private ArrayList<RestStop> minHeap;
    private int[] heapLocation;

    public Heap() {
        minHeap = new ArrayList<RestStop>();    
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
    public void buildHeap(ArrayList<RestStop> stops) {
        // TODO: implement this method

    	heapLocation = new int[stops.size()];
    	for(int i=0;i<stops.size();i++) {
    		heapLocation[i] = -1;
    	}
    	for(int i=0;i<stops.size();i++) {
    		insertNode(stops.get(i));
    	}
    }

    /**
     * insertNode(RestStop in)
     * Insert a RestStop into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the RestStop to insert.
     */
    public void insertNode(RestStop in) {
        // TODO: implement this method
    	minHeap.add(in);
    	heapLocation[in.getName()] = minHeap.size()-1;
    	int currentNodeIndex = minHeap.size()-1;
    	int parent = (currentNodeIndex-1)/2;
    	//heapify up
    	
    	boolean childLessThanParent = false;
    	int secondChildIndex = (parent*2)+1;
		if(secondChildIndex==currentNodeIndex) {
			secondChildIndex++;
		}
		if(currentNodeIndex==1 || currentNodeIndex==2) {
			if(currentNodeIndex==1) {
				parent = 0;
				secondChildIndex = 2;
			}else {
				parent = 0;
				secondChildIndex = 1;
			}
		}
		if(currentNodeIndex!=0) {
			if(currentNodeIndex<minHeap.size() && secondChildIndex<minHeap.size()) {
				if(minHeap.get(parent).getMinDist()>minHeap.get(currentNodeIndex).getMinDist()) {
					childLessThanParent = true;
				}
				if(minHeap.get(parent).getMinDist()>minHeap.get(secondChildIndex).getMinDist()) {
					childLessThanParent = true;
				}
				if(minHeap.get(parent).getMinDist()==minHeap.get(currentNodeIndex).getMinDist() || minHeap.get(parent).getMinDist()==minHeap.get(secondChildIndex).getMinDist()) {
					if(minHeap.get(parent).getName()>minHeap.get(currentNodeIndex).getName()) {
						childLessThanParent = true;
					}
					if(minHeap.get(parent).getName()>minHeap.get(secondChildIndex).getName()) {
						childLessThanParent = true;
					}
				}
			}else if(currentNodeIndex<minHeap.size()) {
				if(minHeap.get(parent).getMinDist()>minHeap.get(currentNodeIndex).getMinDist()) {
					childLessThanParent = true;
				}
				if(minHeap.get(parent).getMinDist()==minHeap.get(currentNodeIndex).getMinDist()) {
					if(minHeap.get(parent).getName()>minHeap.get(currentNodeIndex).getName()) {
						childLessThanParent = true;
					}
				}
			}else{
				if(minHeap.get(parent).getMinDist()>minHeap.get(secondChildIndex).getMinDist()) {
					childLessThanParent = true;
				}
				if(minHeap.get(parent).getMinDist()==minHeap.get(secondChildIndex).getMinDist()) {
					if(minHeap.get(parent).getName()>minHeap.get(secondChildIndex).getName()) {
						childLessThanParent = true;
					}
				}
			}
			boolean notEnd = true;
	    	while(childLessThanParent && notEnd) {
	    		if(parent==0) {
	    			notEnd = false;
	    		}
	    		if(currentNodeIndex<minHeap.size() && secondChildIndex<minHeap.size()) {
		    		if(minHeap.get(currentNodeIndex).getMinDist()<=minHeap.get(secondChildIndex).getMinDist()) {
		    			if(minHeap.get(currentNodeIndex).getMinDist()!=minHeap.get(secondChildIndex).getMinDist()) {
			    			RestStop p = minHeap.get(parent);
			    			minHeap.add(parent, minHeap.get(currentNodeIndex));
			    			minHeap.remove(parent+1);
			    			minHeap.add(currentNodeIndex, p);
			    			minHeap.remove(currentNodeIndex+1);
			    			heapLocation[minHeap.get(currentNodeIndex).getName()] = currentNodeIndex;
			    			heapLocation[minHeap.get(parent).getName()] = parent;
			    			if(parent==1 || parent==2) {
			    				currentNodeIndex = 1;
			    				secondChildIndex = 2;
			    				parent = 0;
			    			}else {
			    				currentNodeIndex = parent;
				    			parent = (currentNodeIndex-1)/2;
			    			}
		    			}else {
		    				if(minHeap.get(currentNodeIndex).getName()<minHeap.get(secondChildIndex).getName()) {
		    					RestStop p = minHeap.get(parent);
		    	    			minHeap.add(parent, minHeap.get(currentNodeIndex));
		    	    			minHeap.remove(parent+1);
		    	    			minHeap.add(currentNodeIndex, p);
		    	    			minHeap.remove(currentNodeIndex+1);
		    	    			heapLocation[minHeap.get(currentNodeIndex).getName()] = currentNodeIndex;
				    			heapLocation[minHeap.get(parent).getName()] = parent;
		    	    			if(parent==1 || parent==2) {
				    				currentNodeIndex = 1;
				    				secondChildIndex = 2;
				    				parent = 0;
				    			}else {
				    				currentNodeIndex = parent;
					    			parent = (currentNodeIndex-1)/2;
				    			}
		    				}else {
		    					RestStop p = minHeap.get(parent);
		    	    			minHeap.add(parent, minHeap.get(secondChildIndex));
		    	    			minHeap.remove(parent+1);
		    	    			minHeap.add(secondChildIndex, p);
		    	    			minHeap.remove(secondChildIndex+1);
		    	    			heapLocation[minHeap.get(secondChildIndex).getName()] = secondChildIndex;
				    			heapLocation[minHeap.get(parent).getName()] = parent;
		    	    			if(parent==1 || parent==2) {
				    				currentNodeIndex = 1;
				    				secondChildIndex = 2;
				    				parent = 0;
				    			}else {
				    				currentNodeIndex = parent;
					    			parent = (currentNodeIndex-1)/2;
				    			}
		    				}
		    			}
		    		}else {
		    			RestStop p = minHeap.get(parent);
		    			minHeap.add(parent, minHeap.get(secondChildIndex));
		    			minHeap.remove(parent+1);
		    			minHeap.add(secondChildIndex, p);
		    			minHeap.remove(secondChildIndex+1);
		    			heapLocation[minHeap.get(secondChildIndex).getName()] = secondChildIndex;
		    			heapLocation[minHeap.get(parent).getName()] = parent;
		    			if(parent==1 || parent==2) {
		    				currentNodeIndex = 1;
		    				secondChildIndex = 2;
		    				parent = 0;
		    			}else {
		    				currentNodeIndex = parent;
			    			parent = (currentNodeIndex-1)/2;
		    			}
		    		}
	    		}else if(currentNodeIndex<minHeap.size()) {
	    			if(minHeap.get(parent).getMinDist()>minHeap.get(currentNodeIndex).getMinDist()) {
	    				//swap parent and child
	    				RestStop p = minHeap.get(parent);
		    			minHeap.add(parent, minHeap.get(currentNodeIndex));
		    			minHeap.remove(parent+1);
		    			minHeap.add(currentNodeIndex, p);
		    			minHeap.remove(currentNodeIndex+1);
		    			heapLocation[minHeap.get(currentNodeIndex).getName()] = currentNodeIndex;
		    			heapLocation[minHeap.get(parent).getName()] = parent;
		    			if(parent==1 || parent==2) {
		    				currentNodeIndex = 1;
		    				secondChildIndex = 2;
		    				parent = 0;
		    			}else {
		    				currentNodeIndex = parent;
			    			parent = (currentNodeIndex-1)/2;
		    			}
	    			}else if(minHeap.get(parent).getName()>minHeap.get(currentNodeIndex).getName()) {
	    				//swap parent and child
	    				RestStop p = minHeap.get(parent);
		    			minHeap.add(parent, minHeap.get(currentNodeIndex));
		    			minHeap.remove(parent+1);
		    			minHeap.add(currentNodeIndex, p);
		    			minHeap.remove(currentNodeIndex+1);
		    			heapLocation[minHeap.get(currentNodeIndex).getName()] = currentNodeIndex;
		    			heapLocation[minHeap.get(parent).getName()] = parent;
		    			if(parent==1 || parent==2) {
		    				currentNodeIndex = 1;
		    				secondChildIndex = 2;
		    				parent = 0;
		    			}else {
		    				currentNodeIndex = parent;
			    			parent = (currentNodeIndex-1)/2;
		    			}
	    			}
	    		}else {
	    			if(minHeap.get(parent).getMinDist()>minHeap.get(secondChildIndex).getMinDist()) {
	    				//swap parent and child
	    				RestStop p = minHeap.get(parent);
		    			minHeap.add(parent, minHeap.get(secondChildIndex));
		    			minHeap.remove(parent+1);
		    			minHeap.add(secondChildIndex, p);
		    			minHeap.remove(secondChildIndex+1);
		    			heapLocation[minHeap.get(secondChildIndex).getName()] = secondChildIndex;
		    			heapLocation[minHeap.get(parent).getName()] = parent;
		    			if(parent==1 || parent==2) {
		    				currentNodeIndex = 1;
		    				secondChildIndex = 2;
		    				parent = 0;
		    			}else {
		    				currentNodeIndex = parent;
			    			parent = (currentNodeIndex-1)/2;
		    			}
	    			}else if(minHeap.get(parent).getName()>minHeap.get(secondChildIndex).getName()) {
	    				//swap parent and child
	    				RestStop p = minHeap.get(parent);
		    			minHeap.add(parent, minHeap.get(secondChildIndex));
		    			minHeap.remove(parent+1);
		    			minHeap.add(secondChildIndex, p);
		    			minHeap.remove(secondChildIndex+1);
		    			heapLocation[minHeap.get(secondChildIndex).getName()] = secondChildIndex;
		    			heapLocation[minHeap.get(parent).getName()] = parent;
		    			if(parent==1 || parent==2) {
		    				currentNodeIndex = 1;
		    				secondChildIndex = 2;
		    				parent = 0;
		    			}else {
		    				currentNodeIndex = parent;
			    			parent = (currentNodeIndex-1)/2;
		    			}
	    			}
	    			
	    		}
	    		childLessThanParent = false;
	    		if(currentNodeIndex<minHeap.size() && secondChildIndex<minHeap.size()) {
					if(minHeap.get(parent).getMinDist()>minHeap.get(currentNodeIndex).getMinDist()) {
						childLessThanParent = true;
					}
					if(minHeap.get(parent).getMinDist()>minHeap.get(secondChildIndex).getMinDist()) {
						childLessThanParent = true;
					}
					if(minHeap.get(parent).getMinDist()==minHeap.get(currentNodeIndex).getMinDist() || minHeap.get(parent).getMinDist()==minHeap.get(secondChildIndex).getMinDist()) {
						if(minHeap.get(parent).getName()>minHeap.get(currentNodeIndex).getName()) {
							childLessThanParent = true;
						}
						if(minHeap.get(parent).getName()>minHeap.get(secondChildIndex).getName()) {
							childLessThanParent = true;
						}
					}
				}else if(currentNodeIndex<minHeap.size()) {
					if(minHeap.get(parent).getMinDist()>minHeap.get(currentNodeIndex).getMinDist()) {
						childLessThanParent = true;
					}
					if(minHeap.get(parent).getMinDist()==minHeap.get(currentNodeIndex).getMinDist()) {
						if(minHeap.get(parent).getName()>minHeap.get(currentNodeIndex).getName()) {
							childLessThanParent = true;
						}
					}
				}else{
					if(minHeap.get(parent).getMinDist()>minHeap.get(secondChildIndex).getMinDist()) {
						childLessThanParent = true;
					}
					if(minHeap.get(parent).getMinDist()==minHeap.get(secondChildIndex).getMinDist()) {
						if(minHeap.get(parent).getName()>minHeap.get(secondChildIndex).getName()) {
							childLessThanParent = true;
						}
					}
				}
	    	}
		}
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public RestStop findMin() {
        return minHeap.get(0);
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public RestStop extractMin() {
        // TODO: implement this method
        // pop min element off the heap
    	RestStop min = minHeap.get(0);
    	heapLocation[min.getName()] = -1;
    	RestStop swap = minHeap.get(minHeap.size()-1);
    	minHeap.set(0, swap);
    	heapLocation[swap.getName()] = 0;
    	minHeap.remove(minHeap.size()-1);
    	int currentIndex = 0;
    	int childIndex1 = 1;
    	int childIndex2 = 2;
    	//heapify down
    	heapifyDown(currentIndex, childIndex1, childIndex2);
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
    	RestStop swap = minHeap.get(minHeap.size()-1);
    	RestStop deletedStop = minHeap.get(index);
    	heapLocation[deletedStop.getName()] = -1;
    	minHeap.add(index, swap);
    	minHeap.remove(index+1);
    	minHeap.remove(minHeap.size()-1);
    	heapLocation[swap.getName()] = index;
    	int currentIndex = index;
    	int childIndex1 = (index*2)+1;
    	int childIndex2 = (index*2)+2;
    	//heapify down
    	heapifyDown(currentIndex, childIndex1, childIndex2);
    	
    }

    /**
     * changeKey(RestStop r, int newDist)
     * Changes minDist of RestStop s to newDist and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the RestStop in the heap that needs to be updated.
     * @param newDist - the new cost of RestStop r in the heap (note that the heap is keyed on the values of minDist)
     */
    public void changeKey(RestStop r, int newDist) {
    	
    	
    	
    	int index = heapLocation[r.getName()];
    	delete(index);
    	r.setMinDist(newDist);
    	insertNode(r);
    	
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getName() + " ";
        }
        return output;
    }
    
    public void heapifyDown(int currentIndex, int childIndex1, int childIndex2){
    	boolean childrenExist = true;
    	if(childIndex1>minHeap.size() && childIndex2>minHeap.size()) {
    		childrenExist = false;
    	}
    	while(childrenExist) {
    		boolean updateChildIndex1 = false;
    		boolean updateChildIndex2 = false;
    		if(childIndex1<minHeap.size() && childIndex2<minHeap.size()) {
	    		if(minHeap.get(currentIndex).getMinDist()>minHeap.get(childIndex1).getMinDist() && minHeap.get(childIndex1).getMinDist()<minHeap.get(childIndex2).getMinDist()) {
	    			
		    			RestStop p = minHeap.get(currentIndex);
		    			RestStop c = minHeap.get(childIndex1);
		    			minHeap.add(currentIndex, c);
		    			minHeap.remove(currentIndex+1);
		    			minHeap.add(childIndex1, p);
		    			minHeap.remove(childIndex1+1);
		    			updateChildIndex1 = true;
	    			
	    		}else if(minHeap.get(currentIndex).getMinDist()>minHeap.get(childIndex2).getMinDist()){
	    			
		    			RestStop p = minHeap.get(currentIndex);
		    			RestStop c = minHeap.get(childIndex2);
		    			minHeap.add(currentIndex, c);
		    			minHeap.remove(currentIndex+1);
		    			minHeap.add(childIndex2, p);
		    			minHeap.remove(childIndex2+1);
		    			updateChildIndex2 = true;
	    			
	    		}else if((minHeap.get(currentIndex).getMinDist()==minHeap.get(childIndex1).getMinDist()) || (minHeap.get(currentIndex).getMinDist()==minHeap.get(childIndex2).getMinDist())) {
	    			if(minHeap.get(currentIndex).getMinDist()==minHeap.get(childIndex1).getMinDist() && minHeap.get(childIndex1).getName()<minHeap.get(childIndex2).getName()) {
	    				if(minHeap.get(currentIndex).getName()>minHeap.get(childIndex1).getName()) {
	    					
		    					RestStop p = minHeap.get(currentIndex);
		    	    			RestStop c = minHeap.get(childIndex1);
		    	    			minHeap.add(currentIndex, c);
		    	    			minHeap.remove(currentIndex+1);
		    	    			minHeap.add(childIndex1, p);
		    	    			minHeap.remove(childIndex1+1);
		    	    			updateChildIndex1 = true;
	    					
	    				}
	    			}else if(minHeap.get(currentIndex).getMinDist()==minHeap.get(childIndex2).getMinDist() && minHeap.get(childIndex2).getName()<=minHeap.get(childIndex1).getName()) {
	    				if(minHeap.get(currentIndex).getName()>minHeap.get(childIndex2).getName()) {
	    					
		    					RestStop p = minHeap.get(currentIndex);
		    	    			RestStop c = minHeap.get(childIndex2);
		    	    			minHeap.add(currentIndex, c);
		    	    			minHeap.remove(currentIndex+1);
		    	    			minHeap.add(childIndex2, p);
		    	    			minHeap.remove(childIndex2+1);
		    	    			updateChildIndex2 = true;
	    					
	    				}
	    			}
	    		}else {
	    			childrenExist = false;
	    		}
    		}else if(childIndex1<minHeap.size()) {
    			if(minHeap.get(currentIndex).getMinDist()>minHeap.get(childIndex1).getMinDist()) {
	    			RestStop p = minHeap.get(currentIndex);
	    			RestStop c = minHeap.get(childIndex1);
	    			minHeap.add(currentIndex, c);
	    			minHeap.remove(currentIndex+1);
	    			minHeap.add(childIndex1, p);
	    			minHeap.remove(childIndex1+1);
	    			updateChildIndex1 = true;
	    		}else if(minHeap.get(currentIndex).getMinDist()==minHeap.get(childIndex1).getMinDist()) {
	    			if(minHeap.get(currentIndex).getName()>minHeap.get(childIndex1).getName()) {
	    				RestStop p = minHeap.get(currentIndex);
		    			RestStop c = minHeap.get(childIndex1);
		    			minHeap.add(currentIndex, c);
    	    			minHeap.remove(currentIndex+1);
    	    			minHeap.add(childIndex1, p);
    	    			minHeap.remove(childIndex1+1);
		    			updateChildIndex1 = true;
	    			}
    			}else {
	    			childrenExist = false;
	    		}
    		}else if(childIndex2<minHeap.size()) {
    			if(minHeap.get(currentIndex).getMinDist()>minHeap.get(childIndex2).getMinDist()) {
	    			RestStop p = minHeap.get(currentIndex);
	    			RestStop c = minHeap.get(childIndex2);
	    			minHeap.add(currentIndex, c);
	    			minHeap.remove(currentIndex+1);
	    			minHeap.add(childIndex2, p);
	    			minHeap.remove(childIndex2+1);
	    			updateChildIndex2 = true;
    			}else if(minHeap.get(currentIndex).getMinDist()==minHeap.get(childIndex2).getMinDist()) {
	    			if(minHeap.get(currentIndex).getName()>minHeap.get(childIndex2).getName()) {
	    				RestStop p = minHeap.get(currentIndex);
		    			RestStop c = minHeap.get(childIndex2);
		    			minHeap.add(currentIndex, c);
    	    			minHeap.remove(currentIndex+1);
    	    			minHeap.add(childIndex2, p);
    	    			minHeap.remove(childIndex2+1);
		    			updateChildIndex2 = true;
	    			}
    			}else {
	    			childrenExist = false;
	    		}
    		}else {
    			childrenExist = false;
    		}
    		if(updateChildIndex1) {
    			heapLocation[minHeap.get(currentIndex).getName()] = currentIndex;
    			heapLocation[minHeap.get(childIndex1).getName()] = childIndex1;
    			currentIndex = childIndex1;
    			childIndex1 = (currentIndex*2) + 1;
    			childIndex2 = (currentIndex*2) + 2;
    		}
    		if(updateChildIndex2) {
    			heapLocation[minHeap.get(currentIndex).getName()] = currentIndex;
    			heapLocation[minHeap.get(childIndex2).getName()] = childIndex2;
    			currentIndex = childIndex2;
    			childIndex1 = (currentIndex*2) + 1;
    			childIndex2 = (currentIndex*2) + 2;
    		}
    		if(!updateChildIndex1 && !updateChildIndex2) {
    			childrenExist = false;
    		}
    	}
    	
    	
    }
    

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<RestStop> toArrayList() {
        return minHeap;
    }
}
