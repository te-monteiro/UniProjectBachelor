import searchalgorithm.Algorithms;
import searchalgorithm.Node;
import undirectedgraph.Graph;

public class Main {

	public static void main(String[] args) {
        Graph graph = new Graph();
        graph.defineGraphRomenia();
        Graph g;
        Object[] a;
        Node n;
        //a = graph.searchSolutionMiddle("Arad", "Bucharest", "Dobrogea", Algorithms.AStarSearch);
        String[] provinces = new String[2];
        provinces[0] = "Dobrogea";
        provinces[1] = "Banat";
        a = graph.searchSolutionMiddlePro("Arad", "Bucharest", provinces, Algorithms.AStarSearch);
        g = (Graph) a[0];
        n = (Node) a[1];
        g.showLinks();
        //graph.showSets();
        graph.showSolution(n);       
	}

}


import java.util.HashSet;
import java.util.Set;

import searchalgorithm.*;
import undirectedgraph.*;
import searchproblem.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph(); 
        graph.defineGraphRomenia(); 
        graph.showLinks(); 
        graph.showSets(); 
        Node n; 
        List<String> s= new ArrayList<String>();
        s.add("Moldova");
        s.add("Oltenia");
        s.add("Dobrogea");
        s.add("Transilvania");
        n = graph.searchSolution("Arad", "Timisoara", s, Algorithms.AStarSearch); 
        graph.showSolution(n); 
	}

}