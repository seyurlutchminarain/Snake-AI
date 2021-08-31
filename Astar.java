import java.util.ArrayList;
import java.util.Collections;

public class Astar {
	ArrayList<Node> openset = new ArrayList<Node>();
	ArrayList<Node>closedset = new ArrayList<Node>();
	ArrayList<Node>path = new ArrayList<Node>();
	Node[][]b = new Node[50][50];
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
		Astar test = new Astar();
		String Result=test.astar(Start,End,Board);
		if(Result.equals("Path Found")) {
			for(Node n:test.path) {
				Board[n.x][n.y]=2;
			}
			Board[1][0]=8;
				for(int i=0 ; i<Board.length; i++) {
			     	   for(int j=0 ; j<Board[i].length;j++) {
			     		   System.out.print(Board[i][j]);
			     	   }
			     	   System.out.println();
			        }
		}
		else {
			System.out.print("Path NotFound");
		}
	
	}
	

	
	public String astar(Node Start,Node End,int[][]Board) {
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
		Node s = b[Start.x][Start.y];
		Node e = b[End.x][End.y];
		openset.add(s);
		while(!openset.isEmpty()) {
			Node Current = getlowestF();
			int x = Current.x;
			int y = Current.y;
			
			if(Current.x==e.x && Current.y==e.y) {
				retracepath(Current,s);
				return "Path Found";
			}
			openset.remove(Current);
			for(Node n:Current.neigbours) {
			if(!n.isWall) {
				double tentG = heuristic(Current,Start)+ heuristic(n,Current);
				
				if(tentG>n.g) {
					n.parent=Current;
					n.g=tentG;
					n.f=n.g+heuristic(n,e);
					
					if(!openset.contains(n) && n.isWall==false) {
						openset.add(n);
					}
				}
			}
				
		}
			
	}
		return "Path NotFound";
		
}
	public void retracepath(Node c,Node s) {
		path.add(c);
		while(c.parent!=null) {
			c=c.parent;
			path.add(c);
		}
	}
	public double heuristic(Node n,Node n1){
		double dis =Math.sqrt(Math.pow(n.x-n1.x,2)+Math.pow(n.y-n1.y,2));
		return dis;
	}
	public Node getlowestF() {
		Node temp = openset.get(0);
		for(Node n : openset) {
			if(n.f<temp.f) {
				temp=n;
			}
		}
		return temp;
		
	}
	
}
