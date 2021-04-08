import java.util.*;
import java.time.*;

//Udit Kapoor
//19CSU327

class graph {

    static class edge{
        int src;
        int nbr;
        int wt;

        edge( int s , int n , int w){
            src=s;
            nbr=n;
            wt=w;
        }
    }
    public static void addEdge(ArrayList<ArrayList<edge>> graph , int a , int b , int w){

        graph.get(a).add(new edge(a, b, w));
        graph.get(b).add(new edge(b, a, w));

    }
    public static void dijkstra (ArrayList<ArrayList<edge>> graph , int s , int d){
        PriorityQueue<bfsHelper> pq = new PriorityQueue<>();
        boolean [] visited = new boolean[graph.size()];
        
        pq.add(new bfsHelper(s, ""+s, 0));
 
        while(pq.size() >0){
             //Get / Remove
             bfsHelper cp = pq.remove();
 
             //Mark
             if(visited[cp.vtx] ==true){
                 continue;
             }
             else
             {visited[cp.vtx]=true;}
 
             //Work->Print
            // if(cp.vtx==d){
                 System.out.println( cp.path + " @ " + cp.cost);
              //   break;
             //}
 
             //Add neighbours
             for(int e =0 ; e< graph.get(cp.vtx).size() ; e++){
                 edge ce = graph.get(cp.vtx).get(e);
                 if(visited[ce.nbr] == false){
                     pq.add(new bfsHelper(ce.nbr , cp.path + " -> " + ce.nbr, cp.cost + ce.wt));
                 }
             }
 
 
         }
    } 
    public static void main(String[]args){  

        //This creates an ArrayList jiske andr aur Arraylist ka address store hoga
        ArrayList<ArrayList<edge>> graph = new ArrayList<>();

        //This loop adds the vertexes which are basically Arraylist of Edge type inside outer AL
        for(int i = 0 ; i<6 ; i++){
            graph.add( new ArrayList<edge>() );
        }

        //Now we link all the vertices
        addEdge(graph, 0, 1 , 3);
        addEdge(graph, 0, 2 , 5);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 0, 3, 9);
        addEdge(graph, 1, 3, 4);
        addEdge(graph, 2, 4, 6);
        addEdge(graph, 1, 4, 7);
        addEdge(graph, 2, 3, 2);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 3, 5, 2);
        addEdge(graph, 4, 5, 5);
        addEdge(graph, 2, 5, 8);


        // boolean [] visited = new boolean[graph.size()];

        LocalTime t = LocalTime.now();
        dijkstra(graph, 0, 5);
        LocalTime t2 = LocalTime.now();

        System.out.println("Time taken in Program :");
		Duration d = Duration.between(t, t2);
		System.out.println(d.getNano() +" NanoSeconds");
     
    }

}


class bfsHelper implements Comparable<bfsHelper>{     // we need an interface to tell the priority queue on what basis are we gonna compare
    int vtx;
    String path;
    int cost;

    bfsHelper(int v , String path , int c){
        vtx=v;
        this.path=path;
        cost=c;
    }

    public int compareTo(bfsHelper other){      //Is called by priority queue when it need comparison
        return this.cost - other.cost ;         //Returns negative , 0  , positive val ! rest queue will see
    }
}