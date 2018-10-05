package Default;
import java.util.ArrayList;
//hashmap

public class PriorityQ {
    ArrayList<Node> A = new ArrayList<Node>();

    public PriorityQ(){ //constructs an empty priority queue
        for(int i = 0; i < A.size(); i++){
            A.get(i).y = " ";
            A.get(i).p = 0;
        }
    }
    public void add(String s, int p){ //Adds a string s with priority p to the priority queue
        Node t = new Node(s, p);
        A.add(t);
    }
    public String returnMax(){ //returns string with highest priority
        Node t = A.get(0);
        return t.y;
    }
    public String extractMax(){ //returnMax but it removes the node as well
        Node t = A.get(0);
        A.set(0, A.get(A.size() - 1));
        //heapify
        return t.y;
    }
    public void remove(int i){ //removes the element from the priority queue whose array index is i.
        //swap last element with given spot
        //remove last element
        //heapify
    }
    public void decrementPriority(int i, int k){ //Decrements the priority of the ith element by k.
        A.get(i).p = (A.get(i).p-k);
        //heapify
    }
    public String[] priorityArray(){
        return null; //returns an array B with the following property: B[i] = key(A[i]) for
        //all i in the array A used to implement the priority queue
    }
    public int getKey(int i){
        return A.get(i).p; //Returns key(A[i]), where A is the array used to represent the priority queue
    }
    public String getValue(int i){
        return A.get(i).y; //Returns value(A[i]), where A is the array used to represent the priority queue
    }
    public Boolean isEmpty(){
        if(A.size() == 0){
            return true;//Return true if and only if the queue is empty.
        }
        return false;
    }

    private class Node {
        String y;
        int p;
        Node(String value, int priority){
            this.y = value;
            this.p = priority;
        }
    }
    //The max heap property must be maintained after each of the above operation. Methods 3–7 have
    //a pre-condition that priority queue is non-empty
}
