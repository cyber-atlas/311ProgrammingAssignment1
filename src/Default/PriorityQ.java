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
        Up(A, A.size() - 1);
    }
    public String returnMax(){ //returns string with highest priority
        Node t = A.get(0);
        return t.y;
    }
    public String extractMax(){ //returnMax but it removes the node as well
        String temp = A.get(0).y;
        A.set(0, A.get(A.size() - 1));
        A.remove(A.size() - 1);
        heapify(A,A.size(),0);
        return temp;
    }
    public void remove(int i){ //removes the element from the priority queue whose array index is i.
        i = i - 1;
        A.set(i, A.get(A.size()-1)); //swap last element with given spot
        A.remove(A.size()-1); //remove last element
        heapify(A,A.size(),i);
    }
    public void decrementPriority(int i, int k){ //Decrements the priority of the ith element by k.
        i = i - 1;
        A.get(i).p = (A.get(i).p-k);
        heapify(A,A.size(),i);
    }
    public int[] priorityArray(){
        int[] B = new int[A.size()];
        for(int i = 0; i < A.size(); i++){
            B[i] = A.get(i).p;
        }
        return B; //returns an array B with the following property: B[i] = key(A[i]) for
        //all i in the array A used to implement the priority queue
    }
    public int getKey(int i){
        i = i - 1;
        return A.get(i).p; //Returns key(A[i]), where A is the array used to represent the priority queue
    }
    public String getValue(int i){
        i = i - 1;
        return A.get(i).y; //Returns value(A[i]), where A is the array used to represent the priority queue
    }
    public Boolean isEmpty(){
        if(A.size() == 0){
            return true;//Return true if and only if the queue is empty.
        }
        return false;
    }
    public void heapify(ArrayList<Node> A, int size, int index) {
        int largest = index;
        int l = 2*index + 1;
        int r = 2*index + 2;

        //left
        if (l < size && A.get(l).p > A.get(largest).p)
            largest = l;

        // right
        if (r < size && A.get(r).p > A.get(largest).p)
            largest = r;

        // If largest is not root
        if (largest != index)
        {
            Node swap = A.get(index);
            A.set(index, A.get(largest));
            A.set(largest, swap);

            // recursion
            heapify(A, size, largest);
        }
    }
    public void Up(ArrayList<Node> A, int index){ //going up tree
        int parent = 0;
        if(index == 0){ //if root
            return;
        }
        else { //else work up
            if (index % 2 == 0) { //right child
                if ((index - 2) >= 0) {
                    parent = (index - 2) / 2;
                }
            }
            if (index % 2 == 1) { //left child
                if ((index - 1) >= 0) {
                    parent = (index - 1) / 2;
                }
            }
            if(A.get(index).p > A.get(parent).p){
                Node temp = A.get(parent);
                A.set(parent, A.get(index));
                A.set(index, temp);
                Up(A,parent);
            }
        }
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
