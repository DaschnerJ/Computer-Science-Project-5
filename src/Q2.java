

import java.util.ArrayList;

public class Q2 
{
	
	public static ArrayList<Node> nodes = new ArrayList<Node>();

	public static void main(String[] args) 
	{
	
		Node a = new Node(1);
		Node b = new Node(1);
		Node c = new Node(1);
		Node d = new Node(1);
		Node e = new Node(1);
		Node f = new Node(1);
		
		
		nodes.add(a);
		nodes.add(b);
		nodes.add(c);
		nodes.add(d);
		nodes.add(d);
		nodes.add(f);
		
		Node previous = null;
		
		for(Node n : nodes)
		{
			if(previous != null);
			{
				n.addConnection(previous);
				n.addWeight(6);
			}
			previous = n;
		}
		
		MyGraph m = new MyGraph(nodes);
		
		m.dijkstra(nodes.get(0).getValue());
		
		m.prim(nodes.get(0).getValue());
		
		
	}
	
	
	
}
