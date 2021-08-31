import java.util.ArrayList;

public class Node{
	int x;
	int y;
	boolean isWall=false;
	Node parent;
	double h=10000000;
	double f=10000000;
	double g=10000000;
	double dis =0;
	
	ArrayList<Node>neigbours = new ArrayList<Node>();
	Node(int i,int j){
		this.x=i;
		this.y=j;
	}
	
	public void addneig(int[][]grid) {
	double dis = this.dis;
	int x = this.x;
	int y = this.y;
	 int rows = grid.length;
	 int col = grid[0].length;
		//add right
		if(x<col-1 && y>=0 && y<rows) {
			if(grid[y][x+1]==1) {
				Node n = new Node(x+1,y);
				n.dis=dis+1;
				n.isWall=true;
				this.neigbours.add(n);
			}
			else {
				Node n = new Node(x+1,y);
				n.dis=dis+1;
				n.isWall=false;
				this.neigbours.add(n);
			}
		}
		//add left
		if(x>0 && y>=0 && y<rows) {
			if(grid[y][x-1]==1) {
				Node n = new Node(x-1,y);
				n.dis=dis+1;
				n.isWall=true;
				this.neigbours.add(n);
			}
			else {
				Node n = new Node(x-1,y);
				n.dis=dis+1;
				n.isWall=false;
				this.neigbours.add(n);
			}
		}
		//add up
		if(y>0 && x>=0 && x<col ) {
			if(grid[y-1][x]==1) {
				Node n = new Node(x,y-1);
				n.dis=dis+1;
				n.isWall=true;
				this.neigbours.add(n);
			}
			else {
				Node n = new Node(x,y-1);
				n.dis=dis+1;
				n.isWall=false;
				this.neigbours.add(n);
			}
		}
		//add bottom
		if(y<rows-1 && x>=0 && x<col) {
			if(grid[y+1][x]==1) {
				Node n = new Node(x,y+1);
				n.dis=dis+1;
				n.isWall=true;
				this.neigbours.add(n);
			}
			else {
				Node n = new Node(x,y+1);
				n.dis=dis+1;
				n.isWall=false;
				this.neigbours.add(n);
			}
		}
		
	}
}


