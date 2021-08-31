import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MyAgent extends za.ac.wits.snake.MyAgent {

    
	public static void main(String args[]) throws IOException {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }
  
	int[][]Board = new int[50][50];
	
	
	
	Node[][] nodes= new Node[50][50];
    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
             
             
           
            int up =0;
            int down =1;
            int left=2;
            int right=3;
            int str=5;
            int lh=4;
            int rh=6;
            
       
            
            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                String apple = line;
               String[] split = apple.split(" ");
               int ax = Integer.parseInt(split[0]);
               int ay = Integer.parseInt(split[1]);
               

                // read in obstacles and do something with them!
                int nObstacles = 3;
                
               
                
                for (int obstacle = 0; obstacle < nObstacles; obstacle++) {
                    String obs = br.readLine();
                    String[]splitobs = obs.split(" ");
                    
                    for(int i=0;i<splitobs.length;i++) {
                    	String cords = splitobs[i];
                    	String[] cord = cords.split(",");
                    	int x = Integer.parseInt(cord[0]);
                    	int y = Integer.parseInt(cord[1]);
                    	Board[y][x]=1;
                    	
                    }
                }
              
               
                int mySnakeNum = Integer.parseInt(br.readLine());
                int headx = 0,heady = 0;
                int neckx = 0,necky = 0;
               
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    
                    if (i == mySnakeNum) {
                        //hey! That's me :)
                    	String[] split1 = snakeLine.split(" ");
                    	
                    	for(int k=3;k<split1.length-1;k++) {
            				String body = split1[k];
            				String b = split1[k+1];
            				String[]s = body.split(",");
            				String[]b2 = b.split(",");
            				int x = Integer.parseInt(s[0]);
            				int y = Integer.parseInt(s[1]);
            				int x2 = Integer.parseInt(b2[0]);
                    		int y2 = Integer.parseInt(b2[1]);
                    		int minx = Math.min(x,x2);
                    		int maxx = Math.max(x,x2);
                    		int miny = Math.min(y, y2);
                    		int maxy = Math.max(y, y2);
                    		for(int a = minx;a<=maxx;a++) {
                    			for(int c = miny;c<=maxy;c++) {
                    				Board[a][c]=1;
                    			}
                    		}
            			
            		}
                    	String head = split1[3];
                    	String neck = split1[4];
                   
                    	String[]tails = tail.split(",");
                    	String[] necks = neck.split(",");
                    	String[] heads = head.split(",");
                    	
                    	neckx = Integer.parseInt(necks[0]);
                    	necky = Integer.parseInt(necks[1]);
                    	headx = Integer.parseInt(heads[0]);
                    	heady = Integer.parseInt(heads[1]);
                    	
                    	
                    	 
                    }
                    //do stuff with other snakes
                    String[] s = snakeLine.split(" ");
                    if(s[0].equals("alive")) {
                    	for(int k = 3;k<s.length-1;k++) {
                    		String str1 = s[k];
                    		String str2 = s[k+1];
                    		
                    		String[] cor = str1.split(",");
                    		String[] cor2 = str2.split(",");
                    		
                    		int x = Integer.parseInt(cor[0]);
                    		int y = Integer.parseInt(cor[1]);
                    		int x2 = Integer.parseInt(cor2[0]);
                    		int y2 = Integer.parseInt(cor2[1]);
                    		int minx = Math.min(x,x2);
                    		int maxx = Math.max(x,x2);
                    		int miny = Math.min(y, y2);
                    		int maxy = Math.max(y, y2);
                    		for(int z = minx;z<=maxx;z++) {
                    			for(int p = miny;p<=maxy;p++) {
                    				Board[p][z]=1;
                    			}
                    		}
                    		
                    	}
                    }
                }
       
                Node Start=new Node(headx,heady);
                Node End = new Node(ax,ay);
                
                double dis = Math.sqrt(Math.pow(ax-headx,2)+Math.pow(ay-heady,2));
                
                
                //calculate move
                //goal is far
                
                     BFS test = new BFS();
                     String result = test.search(Board, Start, End);
                     
                     if(result.equals("Path Found")) {
                    	 Node t = test.visited.get(0);
                    	 while(t.parent!=null) {
                    		 Board[t.y][t.x]=2;
                    		 t=t.parent;
                    	 }
                    	 if(ispath(headx-1,heady)) {
             				Print(left);
             		}
             		else if(ispath(headx+1,heady)) {
            			Print(right);
            		}
             		else if(ispath(headx,heady+1)) {
             			Print(down);
             		}
             		else if(ispath(headx,heady-1)) {
             			Print(up);
             		}
                     }
                     //no path found
                     else {
                    	 if(isfree(headx-1,heady)) {
                 			Print(left);
                 		}
                 		else if(isfree(headx+1,heady)) {
                 			Print(right);
                 		}
                 		else if(isfree(headx,heady+1)) {
                 			Print(down);
                 		}
                 		else if(isfree(headx,heady-1)) {
                 			Print(up);
                 		}
                     }
                     
 		clear();
            }
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Print(int x) {
    	System.out.println(x);
    }
    public void clear() {
    	
    	for(int i=0 ; i<Board.length; i++) {
     	   for(int j=0 ; j<Board[i].length;j++) {
     		   Board[j][i]=0;
     	   }
        }
    	
    }
    public double dis(int x,int x2,int y,int y2) {
    	
    	double d = Math.pow(x2-x,2);
    	double d2 = Math.pow(y2-y,2);
    	double distance = Math.sqrt(d+d2);
    	return distance;
    	
    }
    public void PrintBoard() {
    	for(int i=0 ; i<Board.length; i++) {
	     	   for(int j=0 ; j<Board[i].length;j++) {
	     		   System.err.print(Board[i][j]);
	     	   }
	     	   System.err.println();
	        }
    	System.err.println();
    	System.err.println();
    	System.err.println();
    	System.err.println();
    	System.err.println();
    }
    public boolean ispath(int x,int y) {
    	boolean truth = false;
    	
    	if(x<0||x>49) {
    		truth=false;
    	
    	}
    	else if(y<0||y>49) {
    		truth=false;
    		
    	}
    	else {
    		if(Board[y][x]==2) {
    			truth=true;		
    		}
    	}
    	
    	return truth;	
    }
   
    public boolean isfree(int x,int y) {
    	boolean truth = false;
    	if(x<0||x>49) {
    		truth=false;
    	
    	}
    	else if(y<0||y>49) {
    		truth=false;
    		
    	}
    	else {
    		if(Board[y][x]==0) {
    			truth=true;		
    		}
    	}
    	return truth;
    }
    
}
