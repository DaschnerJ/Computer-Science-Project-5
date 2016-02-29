

import java.util.ArrayList;

public class Path 
{
	
	private int totalWeight;
	private ArrayList<Node> nodes;
	
	public Path()
	{
		nodes = new ArrayList<Node>();
		totalWeight = 0;
	}
	
	public Path(ArrayList<Node> nodes)
	{
		nodes = new ArrayList<Node>();
		totalWeight = 0;
		for(Node b : nodes)
		{
			addNode(b);
		}
	}
	
	public void addWeight(int w)
	{
		totalWeight += w;
	}
	
	public void addWeight(Node w)
	{
		totalWeight += w.getValue();
	}
	
	public void removeWeight(int w)
	{
		totalWeight -= w;
	}
	
	public void removeWeight(Node w)
	{
		totalWeight -= w.getValue();
	}
	
	public void fixWeight()
	{
		totalWeight = 0;
		for(int i = 0; i < nodes.size(); i++)
		{
			if(i == 0)
			{
				
			}
			else
			{
				totalWeight += nodes.get(i-1).getWeightOfNode(nodes.get(i));
			}
			
		}
	}
	
	public void addNode(Node n)
	{
		if(n != null)
		if(!nodes.contains(n))
			nodes.add(n);
		fixWeight();
	}
	
	public void removeNode(Node n)
	{
		if(nodes.contains(n))
			nodes.remove(n);
		fixWeight();
	}
	
	public int getPathWeight()
	{
		return totalWeight;
	}
	
	public int getPathSize()
	{
		if(nodes != null)
			return nodes.size();
		else
			return 0;
	}
	
	public ArrayList<Node> getPath()
	{
		return nodes;
	}
	
	public void clearPath()
	{
		nodes.clear();
		fixWeight();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Path> createPaths()
	{
		ArrayList<Path> paths = new ArrayList<Path>();
		if(nodes == null)
			return null;
		if(nodes.isEmpty())
			return null;
		Node last = nodes.get(nodes.size()-1);
		for(Node b : last.getConnections())
		{
			if(!nodes.contains(b))
			{
				ArrayList<Node> tempNodes = new ArrayList<Node>();
				tempNodes = nodes;
				tempNodes.add(b);
				Path p = new Path(tempNodes);
				paths.add(p);
			}
		}
		
		return paths;
	}
	
	public Node getSmallestNode()
	{
		Node smallest = null;
		
		for(Node n : nodes)
		{
			ArrayList<Node> small = n.findSmallestNode();
			if(small != null)
			{
				for(Node b : small)
				{
					if(!nodes.contains(b) && smallest == null)
						smallest = b;
					else if(!nodes.contains(b) && n.getWeightOfNode(smallest) > n.getWeightOfNode(b))
						smallest = b;
				}
			}
		}
		
		return smallest;
	}

}
