import java.util.*;


public class BreadthFirstSearch
{
	
	
	public Node[][] setBoard(int[][] Board)
	{
		Node[][] grid = new Node[50][50];
		for (int i = 0; i < 50; i++)
		{
			for (int j = 0; j < 50; j++)
			{
				grid[i][j] = new Node(j, i);
				if (Board[i][j] == 1 || Board[i][j] == 2)
				{
					grid[i][j].isObstacle = true;
				}
			}
		}	
		
		for (int i = 0; i < 50; i++)
		{
			for (int j = 0; j < 50; j++)
			{
				grid[i][j].addNeighbors(grid);
				
			}
		}
		
		return grid;
	}
	
	
	public Node searchBoard(int[][] Board, Node Start, Node Goal)
	{
		
		Queue<Node> queue = new LinkedList<Node>();
		ArrayList<Node> explored = new ArrayList<Node>();
		Node[][] grid = setBoard(Board);

		

		
		//mark root as explored
		Start = grid[Start.row][Start.col];
		queue.add(Start);
		
		while (!queue.isEmpty())
		{
			Node current = queue.poll();
		
			if (current.row == Goal.row && current.col == Goal.col) // Goal check
			{
				return current;
			}
			
		
	
			
			for (Node n : grid[current.row][current.col].neighbors)
			{
				if (!explored.contains(n) && !n.isObstacle)
				{
					explored.add(n);
					n.parent = current;
					queue.add(n);
					
					
				}
			}
		}
		
		return null;
		
	}
	


}