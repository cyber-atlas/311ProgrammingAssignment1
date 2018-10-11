package Default;
import java.util.ArrayList;

public class BFS {
    public Graph t; //The graph t that it intakes
    public Fifo Q; //The first in first out queue
    public ArrayList<String> Discovered; //The Discovered Strings
    public String root; //I'm guessing the Seed
    BFS(Graph G, String seed){ //Taking in a graph and a seed or root
        this.t = G; //initializing the graph
        this.Q = new Fifo(); //initializing the fifo queue
        this.Discovered = new ArrayList<String>(); //initializing the list
        this.root = seed; //initializing the seed
    }

    public ArrayList<String> traverse(Graph t){
        Q.Enque(root); //Adding root to Fifo Q
        Discovered.add(root); //adding root to Discovered list
        while(Q.getSize() != 0) { //While Fifo Q isn't empty
            Q.Deque(); //Taking out the first element
            for (Link s : t.e){ //For every edge in the graph edges
                if (!Check(s.after, Discovered)) { //if v' isn't in discovered
                    Q.Enque(s.after); //Adding v' to fifo
                    Discovered.add(s.after); //adding v' to Discovered
                }
            }
        }
        return Discovered; //Returning an ArrayList of discovered strings
    }

    public boolean Check(String s, ArrayList<String> Discovered){ //terrible runtime please forgive me.
        for(int i = 0; i < Discovered.size(); i++){ //Checking to see if after page is in discovered list
           if(s.equals(Discovered.get(i))){ //checking to see if s equals spot in Discovered
               return true;
           }
        }
        return false;
    }



    private class Graph{ //This is the constructed graph
        ArrayList<String> v = new ArrayList<String>(); //Vertex ArrayList of strings. each string is a web page
        ArrayList<Link> e = new ArrayList<Link>(); // Link object has two pages of strings. Each link is linked to
        // each other
        Graph(ArrayList<String> vertex, ArrayList<Link> edge){
            this.v = vertex;
            this.e = edge;
        }
    }

    private class Link{
        String prev; //The first page
        String after;//The second page linked from the the first page
        Link(String a, String b){
            this.prev = a;
            this.after = b;
        }
    }
}
