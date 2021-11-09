import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent extends DevelopmentAgent {

    public static void main(String args[]) {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }
    
    int[][] Board = new int[50][50];
    BreadthFirstSearch test = new BreadthFirstSearch();
    
    // encoding moves according to game dynamics
    int up = 0;
    int down  = 1;
    int left = 2;
    int right = 3;
    int left_head = 4;
    int straight = 5;
    int right_head = 6;

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);

            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                // Handle apple states
                
                String apple1 = line; // invisible apple
                String apple2 = br.readLine(); // regular apple
                
                String[] inv_apple_cords = apple1.split(" ");
                int inv_apple_x = Integer.parseInt(inv_apple_cords[0]);
                int inv_apple_y = Integer.parseInt(inv_apple_cords[1]);
                
                String[] apple_cords = apple2.split(" ");
                int apple_x = Integer.parseInt(apple_cords[0]);
                int apple_y = Integer.parseInt(apple_cords[1]); // apples handled correctly
                
                
                int head_x = 0, head_y = 0;
                int tail_x = 0, tail_y = 0;
                
                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    if (i == mySnakeNum) {
                        //hey! That's me :)
                    	String[] snake_state = snakeLine.split(" ");
                    	String condition = snake_state[0];
                    	// ignore snake_state[1] & snake_state[2] -> Just describes length and kills
                    	
                    	int start = 3;
                    	
                    	if (snakeLine.contains("invisible"))
                    	{
                    		start = 4;
                    	}
                    	
                    	for (int k = start; k < snake_state.length - 1; k++) // Looping through the head, body and tail cords
                    	{
                    		String body = snake_state[k];
                    		String next_body = snake_state[k + 1];
                    		
                    		String[] body_split = body.split(",");
                    		String[] next_body_split = next_body.split(",");
                    		
                    		int body_x = Integer.parseInt(body_split[0]);
                    		int body_y = Integer.parseInt(body_split[1]);
                    		
                    		int next_body_x = Integer.parseInt(next_body_split[0]);
                    		int next_body_y = Integer.parseInt(next_body_split[1]);
                    		
                    		int min_x = Math.min(body_x, next_body_x);
                    		int max_x = Math.max(body_x, next_body_x);
                    		
                    		int min_y = Math.min(body_y, next_body_y);
                    		int max_y = Math.max(body_y, next_body_y);
                    		
                    		for (int r = min_x; r <= max_x; r++)
                    		{
                    			for (int c = min_y; c <= max_y; c++)
                    			{
                    				Board[c][r] = 2; // Marking our snake on the board
                    			}
                    		}
                    	}
                    	
                		String[] snake_head = snake_state[3].split(","); // head of snake
                		head_x = Integer.parseInt(snake_head[0]);
                		head_y = Integer.parseInt(snake_head[1]);
                		
                		String[] snake_tail = snake_state[snake_state.length - 1].split(",");
                		tail_x = Integer.parseInt(snake_tail[0]);
                		tail_y = Integer.parseInt(snake_tail[1]); // mark snakes tail

                		
                		
                    	
                    }
                    if (i != mySnakeNum) 
                    {
                        //do stuff with other snakes
                        String[] enemy_state = snakeLine.split(" ");
                        String enemy_condition = enemy_state[0];
                        int start = 3;
                        
                        if (snakeLine.contains("invisible"))
                        {
                        	start = 4;
                        }
                        
                        for (int k = start; k < enemy_state.length - 1; k++)
                        {
                        	String enemy_body = enemy_state[k];
                        	String enemy_next = enemy_state[k + 1];
                        	
                        	String[] body_state = enemy_body.split(",");
                        	String[] next_body_state = enemy_next.split(",");
                        	
                        	
                        	int body_x = Integer.parseInt(body_state[0]);
                        	int body_y = Integer.parseInt(body_state[1]);
                        	int next_body_x = Integer.parseInt(next_body_state[0]);
                        	int next_body_y = Integer.parseInt(next_body_state[1]);
     
                        	int min_x = Math.min(body_x, next_body_x);
                        	int min_y = Math.min(body_y, next_body_y);
                        	
                        	int max_x = Math.max(body_x, next_body_x);
                        	int max_y = Math.max(body_y, next_body_y);
                        	
                        	for (int r = min_x; r <= max_x; r++)
                        	{
                        		for (int c = min_y; c <= max_y; c++)
                        		{
                        			Board[c][r] = 1; // marking enemy states
                        		}
                        	}
                        }
                    }


                }
                //finished reading, calculate move:
                Node Start = new Node(head_x, head_y);
                Node Goal;
                
                
                Goal = new Node(apple_x, apple_y); // try and figure out what to do when invisible

                if (getDistance(Start, Goal) >= 35)
                {
                	Board[tail_y][tail_x] = 0;
                	Goal = new Node(tail_x, tail_y); // tail chase
                }
                else
                {
                	int checkTrap = 0;
                	
                	for (Node state : Goal.neighbors)
                	{
                		if (state.isObstacle == true)
                		{
                			checkTrap++;
                		}
                		if (checkTrap > 2)
                		{
                			Goal = new Node(tail_x, tail_y); // checking if trap is true -> tail chase
                		}
                	}
                }
                
                
                
                Node current = test.searchBoard(Board, Start, Goal);
                


                
                
                while (current != null)
                {
                	Board[current.row][current.col] = 3; // destination node
                	current = current.parent; // backtrack
                }

                
                // implement moves
                if (head_x > 0)
                {
                    if (Board[head_y][head_x - 1] == 3)
                    {
                    	System.out.println(2);
                    }

                }
                
                if (head_x < 49)
                {
                	if (Board[head_y][head_x + 1] == 3)
                    {
                    	System.out.println(3);
                    }
                }
                
                 if (head_y > 0)
                {
                	if (Board[head_y - 1][head_x] == 3)
                    {
                    	System.out.println(0);
                    }
                }
                
                 if (head_y < 49)
                {
                    if (Board[head_y + 1][head_x] == 3)
                    {
                    	System.out.println(1);
                    }
                }
           

                clearBoard();

                
                
                
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void printBoard(int[][] Board)
    {
    	String line = "";
    	
    	for (int i = 0; i < 50; i++)
    	{
    		for (int j = 0; j < 50; j++)
    		{
    			line = line + Integer.toString(Board[i][j]) + " ";
    		}
    		
    		System.err.println(line);
    		line = "";
    	}
    }
    
    public void clearBoard()
    {
    	for (int i = 0; i < 50; i++)
    	{
    		for (int j = 0; j < 50; j++)
    		{
    			Board[i][j] = 0;
    		}
    	}
    }
    
    public double getDistance(Node start, Node goal)
    {
    	int x1 = start.row;
    	int y1 = start.col;
    	
    	int x2 = goal.row;
    	int y2 = goal.col;
    	
    	double distance = Math.abs(x2 - x1) + Math.abs(y2 - y1);
    	return distance;
    }
    
    
}