

import java.util.ArrayList;
import java.util.Scanner;

public class A5_Q1 {

	public static void main(String[] args) 
	{
		int[][] matrix = getMatrix();
		BFSMatrix(matrix, matrix[0][1]);
	}
	
	/**
	 * Makes a matrix based on the user's input.
	 * @return Returns a complete matrix.
	 */
	public static int[][] getMatrix()
	{
		ArrayList<Integer> dataList = new ArrayList<Integer>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Continue? 'Y' for yes and 'N' for no:");
		
		while (sc.hasNext() && (sc.nextLine().equalsIgnoreCase("y"))) {
			
		    System.out.println("Enter the next number:");
		    
		    int number = 0;
		    
		    try {
		    	
		        number = Integer.parseInt(sc.nextLine());
		        if(!dataList.contains(number))
		        dataList.add(number);
		        
		    } catch (IllegalArgumentException e) {
		    	
		        e.printStackTrace();
		        
		    }
		    System.out.println("Continue? 'Y' for yes and 'N' for no:");
		}
		
		int[][] matrix = new int[dataList.size()+1][dataList.size()+1];
		for(int i = 0; i < dataList.size(); i++)
		{
			matrix[0][i+1] = dataList.get(i);
			matrix[i+1][0] = dataList.get(i);
		}
		
		for(int i = 0; i < dataList.size(); i++)
		{
			System.out.println("Does " + dataList.get(i) + " have any relations? 'Y' for yes and 'N' for no:");
			while (sc.hasNext() && (sc.nextLine().equalsIgnoreCase("y"))) {
				
			    System.out.println("Enter the next related number:");
			    
			    int number = 0;
			    
			    try {
			    	
			        number = Integer.parseInt(sc.nextLine());
			        if(dataList.contains(number))
			        {
			        	matrix[i+1][dataList.indexOf(number)+1] = 1;
			        }
			        
			    } catch (IllegalArgumentException e) {
			    	
			        e.printStackTrace();
			        
			    }
			    System.out.println("Does " + dataList.get(i) + " have any relations? 'Y' for yes and 'N' for no:");
			}
		}
		
		sc.close();
		
		return matrix;
	}
	
	/**
	 * Creates a breadth first traversal of the user's input from the starting source point.
	 * @param aMatrix The user's matrix to make a traversal of.
	 * @param source The starting number for the traversal.
	 */
	public static void BFSMatrix(int[][] aMatrix, int source)
	{
		ArrayList<Integer> dataList = new ArrayList<Integer>();
		
		for(int i = 1; i < aMatrix[0].length; i++)
		{
			dataList.add(aMatrix[0][i]);
		}
		
		ArrayList<Integer> printList = new ArrayList<Integer>();
		
		if(dataList.contains(source))
		{
			printList.add(source);
			boolean failSafe = true;
			int count;
			while(dataList.size() != printList.size() && failSafe)
			{
				count = printList.size()-1;
				int[] num = findAllChildren(aMatrix, printList.get(count), dataList);
				int sizeCheck = printList.size();
				for(int i = 0; i < num.length; i++)
				{
					if(!printList.contains(num[i]) && num[i] != 0)
					{
						printList.add(num[i]);
						count = printList.size()-1;
					}
				}
				if(sizeCheck == printList.size() && count != 0)
				{
					count--;
				}
				else if(count == 0)
				{
					failSafe = false;
				}
				else
				{
					count = printList.size()-1;
				}
			}
			
			System.out.println("The BFS traversal is:");
			for(int i = 0; i < printList.size(); i++)
			{
				System.out.print(printList.get(i)+" ");
			}
		}
		else
		{
			System.out.println("The starting number does not exist.");
			System.out.println(source);
		}
	}
	
	
	/**
	 * Finds all connected children for the traversal.
	 * @param aMatrix The matrix to find the children in.
	 * @param parent The parent number of the children.
	 * @param dataList
	 * @return
	 */
	public static int[] findAllChildren(int[][] aMatrix, int parent, ArrayList<Integer> dataList)
	{
		int[] num = aMatrix[dataList.indexOf(parent)+1];
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i = 1; i < num.length; i++)
		{
			if(num[i] == 1)
			{
				nums.add(dataList.get(i-1));
			}
		}
		
		int[] fNums = new int[nums.size()];
		for(int i = 0; i < fNums.length; i++)
		{
			fNums[i] = nums.get(i);
		}
		return fNums;
		
	}

	
}
