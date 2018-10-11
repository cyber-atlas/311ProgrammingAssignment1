package Default;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




public class BFS {
    HashMap<String, Integer> map = new HashMap<>();
    private Graph t;
    Fifo Q;
    ArrayList<String> Discovered;
    String root = "root";
    BFS(Graph G){
        this.t = G;
        this.Q = new Fifo();
        Discovered = new ArrayList<String>();
    }

    public Graph traverse(Graph t){
        Q.Enque(root);
        Discovered.add(root);
        String extract = Q.Deque();

        return null;

    }



    private class Graph{
        ArrayList<String> v = new ArrayList<String>();
        ArrayList<String> e = new ArrayList<String>();
        Graph(ArrayList<String> vertex, ArrayList<String> edge){
            this.v = vertex;
            this.e = edge;
        }
    }
}
