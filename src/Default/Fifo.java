package Default;
import java.util.ArrayList;

public class Fifo {
    //implementing a fifo queue using an arraylist
    private ArrayList<String> A; //List of strings
    int head; //setting up head
    Fifo(){
        this.A = new ArrayList<String>(); //Makes a new list of strings
        head = 0;
    }

    public void Enque(String input){ //Adds to queue
        A.add(input);
    }

    public int getSize(){ //Gets size of queue based of off starting head position to end of queue
        if(head == A.size()){
            return 0;
        }
        return (A.size() - head);
    }

    public String Deque(){ //Moves head position to the right.
        //Removes first position at head
        if(head == A.size()){
            return null;
        }
        String s = A.get(head);
        head = head + 1;
        return s;
    }



}
