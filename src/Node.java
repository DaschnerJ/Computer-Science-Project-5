

import java.util.ArrayList;

public class Node 
{
	private int value;
	private ArrayList<Node> connections;
	private ArrayList<Integer> weights;
	
	public Node(int value)
	{
		this.value = value;
		connections = new ArrayList<Node>();
		weights = new ArrayList<Integer>();
	}
	
	public void newValue(int value)
	{
		this.value = value;
	}
	
	public ArrayList<Node> getConnections()
	{
		return connections;
	}
	
	public ArrayList<Integer> getWeights()
	{
		return weights;
	}
	
	public void addConnection(Node n)
	{
		connections.add(n);
	}
	
	public void removeWeight(Integer w)
	{
		if(weights.contains(w))
			weights.remove(w);
	}
	
	public void clearWeights()
	{
		weights.clear();
	}
	
	public void addWeight(Integer w)
	{
		weights.add(w);
	}
	
	public void removeConnection(Node n)
	{
		if(connections.contains(n))
			connections.remove(n);
	}
	
	public void clearConnections()
	{
		connections.clear();
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void clearValue()
	{
		value = 0;
	}
	
	public int getWeightOfNode(Node n)
	{
		if(!connections.contains(n))
			return 0;
		else
		{
			for(int i = 0; i < connections.size(); i++)
			{
				if(connections.get(i).equals(n))
					return weights.get(i);
			}
		}
		return 0;
	}
	
	@SuppressWarnings("null")
	public ArrayList<Node> findSmallestNode()
	{
		ArrayList<Node> n = new ArrayList<Node>();
		ArrayList<Integer> smallest = new ArrayList<Integer>();
		for(int i = 0; i < connections.size(); i++)
		{
			if(smallest == null)
				smallest.add(i);
			else if(i < weights.size() && weights.size() != 0 && !smallest.isEmpty() && smallest.size() != 0 && weights.get(smallest.get(0)) > weights.get(i))
			{
				smallest.clear();
				smallest.add(i);
			}
			else if(i < weights.size() && weights.size() != 0 && !smallest.isEmpty() && smallest.size() != 0 && weights.get(smallest.get(0)) == weights.get(i))
				smallest.add(i);
		}
		for(Integer i : smallest)
		{
			n.add(connections.get(i));
		}
		
		return n;
	}

}
