import java.util.Scanner;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
 
 public class DijkstraExample {
 //        B---9--E
 //       /|      |
 //      / |      |
 //     /  |      |
 //    14  2      6
 //   /    |      |
 //  /     |      |
 // A---9--C--11--F
 //  \     |     /
 //   \    |    /
 //    7  10   15
 //     \  |  /
 //      \ | /
 //       \|/
 //        D      G
  	public static Graph exampleGraph() {
		Graph g = new SingleGraph("example");
		
		g.addNode("Rio de Janeiro").setAttribute("xy", 0, 1);
		g.addNode("Sao Paulo").setAttribute("xy", 1, 2);
		g.addNode("Salvador").setAttribute("xy", 1, 1);
		g.addNode("Recife").setAttribute("xy", 1, 0);
		g.addNode("Vitoria").setAttribute("xy", 2, 2);
		g.addNode("Natal").setAttribute("xy", 2, 1);
		
		g.addEdge("RSP", "Rio de Janeiro", "Sao Paulo").setAttribute("length", 300);
		g.addEdge("RN", "Rio de Janeiro", "Natal").setAttribute("length", 70);
		g.addEdge("RV", "Rio de Janeiro", "Vitoria").setAttribute("length", 100);
		g.addEdge("SPSA", "Sao Paulo", "Salvador").setAttribute("length", 100);
		g.addEdge("SPRE", "Sao Paulo", "Recife").setAttribute("length", 400);
		g.addEdge("SAN", "Salvador", "Natal").setAttribute("length", 50);
		g.addEdge("REN", "Recife", "Natal").setAttribute("length", 150);
		g.addEdge("REV", "Recife", "Vitoria").setAttribute("length", 50);

		g.getNodeSet().forEach(n -> n.setAttribute("label", n.getId()));
		g.getEdgeSet().forEach(e -> e.setAttribute("label", "" + (int) e.getNumber("length")));
		
		return g;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String origem, destino;
		
		Graph g = exampleGraph();
		System.out.println("Locais disponiveis para Escolha: \n");
		for(Node node: g) {
			System.out.println(node);
		}
		System.out.println("\nDigitar o nome Igual ao disponivel, diferenciando maiscula e minuscula!");
		System.out.println("\nEscolha a Origem de Saida: ");
		origem = scan.nextLine();
		System.out.println("Digite O Destino de Chegada: ");
		destino = scan.nextLine();
		
		g.display(false);

		// Edge lengths are stored in an attribute called "length"
		// The length of a path is the sum of the lengths of its edges
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
		
		// Compute the shortest paths in g from A to all nodes
		dijkstra.init(g);
		dijkstra.setSource(g.getNode(origem));
		dijkstra.compute();

		// Print the lengths of all the shortest paths
		for (Node node : g)
			if(node == g.getNode(destino)) {
				System.out.printf("%s->%s | Custo do Caminho: %.2f%n", dijkstra.getSource(), node,
						dijkstra.getPathLength(node));		
			}

		// Color in blue all the nodes on the shortest path form A to B
		for (Node node : dijkstra.getPathNodes(g.getNode(destino)))
				node.setAttribute("ui.style", "fill-color: blue;");

		// Color in red all the edges in the shortest path tree
		for (Node node : g)
			if(node == g.getNode(destino)) {
				for (Edge edge : dijkstra.getPathEdges(node))
					edge.setAttribute("ui.style", "fill-color: red;");
		}

		// Print the shortest path from A to B
		System.out.println(dijkstra.getPath(g.getNode(destino)));
		
		// cleanup to save memory if solutions are no longer needed
		dijkstra.clear();

	}
 }