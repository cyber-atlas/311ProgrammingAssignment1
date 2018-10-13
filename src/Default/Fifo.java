package Default;
import java.util.ArrayList;

public class Fifo {
    //implementing a fifo queue using an arraylist
    private ArrayList<String> A; //List of strings
    int head; //setting up head
    //updated the size
    int nSize;

    Fifo(){
        this.A = new ArrayList<String>(); //Makes a new list of strings
        head = 0;
        nSize = 0;
    }

    public void Enqueue(String input){ //Adds to queue
        A.add(input);
        nSize++;
    }

    public int getSize(){ //Gets size of queue based of off starting head position to end of queue
        if(head == nSize){
            return 0;
        }
        return (A.size() - head);
    }

    public String Dequeue(){ //Moves head position to the right.
        //Removes first position at head
        if(head == A.size()){
            return null;
        }
        String s = A.get(head);
        A.set(head, null);
        head = head + 1;
        nSize--;
        return s;
    }



}
