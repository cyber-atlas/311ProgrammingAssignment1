package Default;
import java.util.ArrayList;
//hashmap

public class PriorityQ {
    private ArrayList<Node> A;
    private int n;


    public PriorityQ(){ //constructs an empty priority queue
        this.A = new ArrayList<Node>();
        n = 1;
    } //Initializing the queue

    public void add(String s, int p){ //Adds a string s with priority p to the priority queue
        Node t = new Node(s, p); //making a new node with given s and p
        A.add(t); //adding node
        Up(A, A.size() - 1);//finding nodes rightful spot
        n += 1;
    }
    public String returnMax(){ //returns string with highest priority
        if (isEmpty()) { //checks to make sure no empty
            return null;
        }
        Node t = A.get(0);//gets highest priority
        return t.y;
    }
    public String extractMax(){ //returnMax but it removes the node as well
        if (isEmpty()) {//checks empty bad
            return null;
        }
        String temp = A.get(0).y; //max string
        A.set(0, A.get(A.size() - 1));//set last element in starting
        A.remove(A.size() - 1);//removes last element
        heapify(A, A.size(), 0);//finds rightful spot for root
        n -= 1;
        return temp;
    }
    public void remove(int i){ //removes the element from the priority queue whose array index is i.
//        if(i < 1 || i > A.size() - 1){
        if (isEmpty()) { //empty bad
            return;
        }
        if (i < 0 || i > A.size() - 1) { //out of bounds bad
            return;
        }
        //i = i - 1;
        A.set(i, A.get(A.size() - 1)); //swap last element with given spot
        A.remove(A.size() - 1); //remove last element
        n -= 1;
        heapify(A, A.size(), i);
    }
    public void decrementPriority(int i, int k){ //Decrements the priority of the ith element by k.
       // if(i < 1 || i > A.size() - 1){
        if(isEmpty()){ //empty bad
            return;
        }
        if(i < 0 || i > A.size() - 1){ //out of bounds bad
            return;
        }
        //i = i - 1;
        A.get(i).p = (A.get(i).p-k); //decrements certain node's priority
        heapify(A,A.size(),i); //rightful spot
    }
    public int[] priorityArray(){
        if(isEmpty()) return null; //empty bad
        int[] B = new int[A.size()];
        for(int i = 0; i < A.size(); i++){
            B[i] = A.get(i).p;
        }
        return B; //returns an array B with the following property: B[i] = key(A[i]) for
        //all i in the array A used to implement the priority queue
    }
    public int getKey(int i){
        int pp = 0;
        try {
            //i = i - 1;
            pp = A.get(i).p;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("This index is out of bounds");
        }
        return pp; //Returns key(A[i]), where A is the array used to represent the priority queue
    }
    public String getValue(int i){
        String pp = "";
        try {
           // i = i - 1;
            pp = A.get(i).y;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("This index is out of bounds");
        }
        return pp; //Returns key(A[i]), where A is the array used to represent the priority queue
    }

    public Boolean isEmpty(){
        if(n == 0){
            return true;//Return true if and only if the queue is empty.
        }
        return false;
    }
    public void heapify(ArrayList<Node> A, int size, int index) {
        int largest = index; //finds largest
        int l = 2*index + 1; //finds left child
        int r = 2*index + 2; //finds right child

        //checks if left is bigger
        if (l < size && A.get(l).p > A.get(largest).p)
            largest = l;

        // checks if right is bigger
        if (r < size && A.get(r).p > A.get(largest).p)
            largest = r;

        // If largest is not root
        if (largest != index)
        {
            //swap
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
