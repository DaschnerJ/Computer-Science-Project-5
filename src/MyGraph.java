

import java.util.ArrayList;

public class MyGraph 
{
	
	private ArrayList<Node> nodes;
	
	/** Construct a new graph. */
	public MyGraph()
	{
		nodes = new ArrayList<Node>();
	}
	
	public MyGraph(ArrayList<Node> nodes)
	{
		this.nodes = nodes;
	}
	/** Add a new vertex into the graph. If the new vertex already
	exists in the graph then return false.
	* @param int newNode: the vertex number of the new node.
	3
	* @return true if the vertex number >=0 and is not used; otherwise
	false.
	*/
	public boolean addVertex(int newNode) 
	{
		
		Node n = new Node(newNode);
		
		if(n.getValue() < 0)
			return false;
		
		for(Node b : nodes)
		{
			
			if(n.getValue() == b.getValue())
				return false;
			
		}
		
		nodes.add(n);
		return true;
		
	}
	/** Add a new edge into the graph.
	* @param int source: the vertex number of the source node.
	* @param int destination: the vertex number of the destination
	node.
	* @param int weight: the weight of the edge.
	* @return true if the edge satisfies the following criteria: 1.
	valid source node. 2. valid destination node. 3. weight > 0. 4.
	No existing edge sharing the same source and destination;
	otherwise false.
	*/
	public boolean addEdge(int source, int destination, int weight) 
	{
		if(weight < 1)
			return false;
		if(source == destination)
			return false;
		Node s = null;
		Node d = null;
		for(Node b : nodes)
		{
			if(source == b.getValue())
				s = b;
			if(destination == b.getValue())
				d = b;
		}
		if(s != null && d != null)
		{
			
			s.addConnection(d);
			s.addWeight(weight);
			
			d.addConnection(d);
			d.addWeight(weight);
			
			return true;
		}
		return false;
	}
	/** Print the shortest distances and shortest paths of the graph.
	* @param int source: the vertex number of the source node.
	* @return true if the source node is valid; otherwise false.
	*/
	public boolean dijkstra(int source)
	{
		ArrayList<Path> paths = new ArrayList<Path>();
		
		Node s = null;
		
		for(Node b : nodes)
		{
			if(b.getValue() == source)
				s = b;
		}
		
		if(s == null)
			return false;
		
		Path tP = new Path();
		tP.addNode(s);
		paths.add(tP);
		
		paths = getPaths(paths);
		
		Path p = findShortestPath(paths);
		if(p == null)
			System.out.println("Could not find shortest path.");
		else
		{
			System.out.println("Found the shortest path.");
			System.out.println("The shortest path is:");
			for(Node n : p.getPath())
			{
				System.out.print(n.getValue() + " ");
			}
			System.out.println();
			System.out.println("With a path value of " + p.getPathWeight() + ".");
		}
		
		
		return false;
	}
	
	/**
	 * Recursive method to obtain a full set of possible paths.
	 * @param p The current list of paths found.
	 * @return Returns the list of paths found.
	 */
	public ArrayList<Path> getPaths(ArrayList<Path> p)
	{
		
		@SuppressWarnings("unchecked")
		ArrayList<Path> newPaths = (ArrayList<Path>) p.clone();
		
		for(Path b : p)
		{
			ArrayList<Path> addPath = b.createPaths();
			if(addPath != null)
			{
				addPath = getPaths(addPath);
				for(Path c : addPath)
				{
					newPaths.add(c);
				}
			}
		}
		
		return newPaths;
		
	}
	
	/**
	 * Finds the shortest path from the list of paths.
	 * @param paths The found paths.
	 * @return Returns the shortest complete path.
	 */
	@SuppressWarnings("null")
	public Path findShortestPath(ArrayList<Path> paths)
	{
		Path path = null;
		
		if(paths != null)
		{
			for(Path b : paths)
			{
				if(b != null && path == null && b.getPathSize() == nodes.size())
					path = b;
				else if(b != null && path != null && b.getPathWeight() < path.getPathWeight() && b.getPathSize() == nodes.size())
					path = b;
			}
		}
		
		return path;
		
	}
	
	/**
	 * Finds the shortest spanning tree using prim's method.
	 * @param source The original starting node.
	 * @return Returns the shortest spanning tree.
	 */
	public boolean prim(int source)
	{
		Node s = null;
		
		for(Node b : nodes)
		{
			if(b.getValue() == source)
				s = b;
		}
		
		if(s == null)
			return false;
		
		Path tP = new Path();
		tP.addNode(s);
		while(tP.getPathSize() != nodes.size())
		{
			Node addNode = tP.getSmallestNode();
			tP.addNode(addNode);
		}
		
		System.out.println("Found the shortest spanning path.");
		System.out.println("The shortest spanning path is:");
		for(Node n : tP.getPath())
		{
			System.out.print(n.getValue() + " ");
		}
		System.out.println();
		System.out.println("With a path value of " + tP.getPathWeight() + ".");
		
		return false;
	}

}
