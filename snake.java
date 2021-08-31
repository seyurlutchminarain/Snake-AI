import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class MyAgent extends za.ac.wits.snake.MyAgent {

    public static void main(String args[]) throws IOException {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }
    ArrayList<Integer> obscords = new ArrayList<Integer>();
    int[][]Board = new int[50][50];
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
                    	
                    	
                    	String head = split1[3];
                    	String neck = split1[4];
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
                    	for(int k = 3;k<s.length;k++) {
                    		String str1 = s[k];
                    		String[] cor = str1.split(",");
                    		int x = Integer.parseInt(cor[0]);
                    		int y = Integer.parseInt(cor[1]);
                    		Board[y][x]=1;
                    		
                    	}
                    }
                }
                //moving left
                if(headx<neckx) {
                	//apple on the left
                	if(ax<headx) {
                		if(isobs(headx-1,heady)) {
                			Print(str);
                		}
                		else if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                	
                		else if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                	}
                	//apple on the right
                	else if(ax>headx) {
                		
                		if(isobs(headx,heady-1)) {
                    			Print(up);
                    		}
                    		else if(isobs(headx,heady+1)) {
                    			Print(down);
                    		}
                    		else if(isobs(headx-1,heady)) {
                    			Print(str);
                    		}
                		
                		
                	}
                	//apple on top
                	else if(ay<heady) {
                		if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                		else if(isobs(headx-1,heady)) {
                			Print(str);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                	}
                	//apple below
                	else if(ay>heady) {
                		if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                		else if(isobs(headx-1,heady)) {
                			Print(str);
                		}
                		else if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                	}
                }
                //moving right
                else if(headx>neckx) {
                	//apple on the left
                	if(ax<headx) {
                		if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(str);
                		}
                	}
                	//apple on the right
                	else if(ax>headx) {
                		if(isobs(headx+1,heady)) {
                			Print(str);
                		}
                		else if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                	}
                	//apple on top
                	else if(ay<heady) {
                		if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(str);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                	}
                	//apple below
                	else if(ay>heady) {
                		if(isobs(headx,heady-1)) {
                			Print(down);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(str);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(up);
                		}
                	}
                }
                //moving down
                else if(heady>necky) {
                	//apple on the left
                	if(ax<headx) {
                		if(isobs(headx-1,heady)) {
                			Print(rh);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(str);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(lh);
                		}
                	}
                	//apple on the right
                	else if(ax>headx) {
                		if(isobs(headx+1,heady)) {
                			Print(lh);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(str);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(rh);
                		}
                	}
                	//apple on top
                	else if(ay<heady) {
                		if(isobs(headx-1,heady)) {
                			Print(rh);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(lh);
                		}
                		else if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                	}
                	//apple below
                	else if(ay>heady) {
                		if(isobs(headx,heady+1)) {
                			Print(down);
                		}
                		else if(isobs(headx-1,heady)) {
                			Print(rh);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(lh);
                		}
                	}
                }
                //moving up
                else if(heady<necky) {
                	//apple on the left
                	if(ax<headx) {
                		if(isobs(headx-1,heady)) {
                			Print(lh);
                		}
                		else if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(rh);
                		}
                	}
                	//apple on the right
                	else if(ax>headx) {
                		if(isobs(headx+1,heady)) {
                			Print(rh);
                		}
                		else if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                		else if(isobs(headx-1,heady)) {
                			Print(lh);
                		}
                	}
                	//apple on top
                	else if(ay<heady) {
                		if(isobs(headx,heady-1)) {
                			Print(up);
                		}
                		else if(isobs(headx-1,heady)) {
                			Print(lh);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(rh);
                		}
                	}
                	//apple below
                	else if(ay>heady) {
                		if(isobs(headx-1,heady)) {
                			Print(lh);
                		}
                		else if(isobs(headx+1,heady)) {
                			Print(rh);
                		}
                		else if(isobs(headx,heady-1)){
                			Print(up);
                		}
                	}
                	obscords.clear();
                	clear();
                }
  
             obscords.clear();
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
     	   for(int j=0 ; j<Board[i].length-1;j++) {
     		   Board[j][i]=0;
     	   }
        }
    }
    public boolean isobs(int x,int y) {
    	boolean truth = true;
    	
    	if(x<0||x>49) {
    		truth=false;
    	
    	}
    	else if(y<0||y>49) {
    		truth=false;
    		
    	}
    	else {
    		if(Board[y][x]==1) {
    			truth=false;		
    		}
    	}
    	
    	return truth;
    	
    	
    }
    public boolean isedge(int x,int y,int[][]Board) {
    	if(Board[x][y]==10) {
    		return true;
    	}
    	return false;
    }
    
}
