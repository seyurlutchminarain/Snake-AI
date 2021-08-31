import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
	 Node[][]b=new Node[50][50];
     ArrayList<Node>path=new ArrayList<Node>();
	 ArrayList<Node>visited=new ArrayList<Node>();
	public static void main(String args[]) {
		int[][]Board = new int[50][50];

		Board[48][49]=1;
		Board[1][0]=1;
		Board[2][0]=1;
		Board[3][0]=1;
		Board[4][0]=1;
		Board[6][0]=1;
		Board[6][0]=1;
		Board[6][1]=1;
		Board[6][2]=1;
		Board[6][3]=1;
		Board[6][4]=1;
		Board[10][4]=1;
		Board[10][5]=1;
		Board[10][6]=1;
		Board[10][7]=1;
		Board[10][8]=1;
		Board[10][9]=1;
		Board[9][9]=1;
		Board[8][9]=1;
		
	
		
     	
	
		Node Start = new Node(1,0);
		Node End = new Node(49,49);
		BFS test = new BFS();
		test.search(Board, Start, End);
		
		for(int i=0 ; i<Board.length; i++) {
	     	   for(int j=0 ; j<Board[i].length;j++) {
	     		   System.out.print(Board[i][j]);
	     	   }
	     	   System.out.println();
	        }
	}
	
	public String search(int[][]Board,Node Start,Node End) {
		Queue<Node> Q = new LinkedList<Node>();
		for(int i=0 ; i<Board.length; i++) {
			
	      	   for(int j=0 ; j<Board[i].length;j++) {
	      			   b[i][j]=new Node(i,j);
	      			  
	      	   }
	        }
	    	for(int i=0 ; i<b.length; i++) {
	      	   for(int j=0 ; j<b[i].length;j++) {
	      		   Node temp=b[i][j];
	      		   temp.addneig(Board);
	      	   }
	         }
	    	Start.isWall=true;
	    	Q.add(Start);
	    	
	    	while(!Q.isEmpty()) {
	    		Node v = Q.poll();
	    		
	    		if(v.x==End.x && v.y==End.y) {
	    			visited.add(v);
	    			return "Path Found";
	    		}
	    		for(Node n:b[v.x][v.y].neigbours) {
	    			if(n.isWall!=true) {
	    				n.isWall=true;
	    				n.parent=v;
	    				Q.add(n);
	    			}
	    		}
	    	}
	    	return "Path NotFound" ;
	 
		
	}
	
	public void retracepath(Node cur) {
		while(cur.parent!=null) {
			path.add(cur);
			cur=cur.parent;
		}
	}

}


