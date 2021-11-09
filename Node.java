import java.util.*;

public class Node
{
	int row; // row which node is found
	int col; // col "
	boolean isObstacle= false; // check if node is available or not
	double distance;  // used for distance calcs
	double f = 10000000;
	double g = 10000000;
	double h = 10000000;
	
	Node parent; // nodes parent node
	
	ArrayList<Node> neighbors = new ArrayList<Node>(); // list of neighbors
	
	public Node(int i, int j)
	{
		this.col = i;
		this.row = j;
		this.parent = null;
	}
	
	public void addNeighbors(Node[][] Board)
	{
		double distance = this.distance;
		int row = this.row;
		int col = this.col;
		
		//add left
		if (col -  1 >= 0)
		{
			this.neighbors.add(Board[row][col - 1]);
		}
		
		// add right
		if (col + 1 <= 49)
		{
			this.neighbors.add(Board[row][col + 1]);
		}
		
		// add up
		if (row - 1 >= 0)
		{
			this.neighbors.add(Board[row - 1][col]);
		}
		
		// add below
		if (row + 1 <= 49)
		{
			this.neighbors.add(Board[row + 1][col]);
		}
		
	}
}