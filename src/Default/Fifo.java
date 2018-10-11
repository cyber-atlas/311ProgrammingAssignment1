package Default;
import java.util.ArrayList;

public class Fifo {
    //implementing a fifo queue using an arraylist
    private ArrayList<String> A;
    int head;
    Fifo(){
        this.A = new ArrayList<String>();
        head = 0;
    }

    public void Enque(String input){
        A.add(input);
    }

    public String Deque(){
        if(head == A.size()){
            return null;
        }
        String s = A.get(head);
        A.remove(head);
        head = head + 1;
        return s;
    }



}
