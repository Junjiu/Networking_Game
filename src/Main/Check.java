package Main;

import java.util.ArrayList;

import player.*;

public class Check {
	private static final int width=8;
	private static final int height=8;
	private static final int empty=0;
	private static final int white=1;
	private static final int black=2;
	 public final static int QUIT = 0;
	  public final static int ADD = 1;
	  public final static int STEP = 2;

  //print the winner and the board return true if game finised;
  public static boolean checkwinForWhite(GameData data){
	
	 if(checkwin(data.Board,white)){
		 System.out.println("White win!");
		 return true;
	 }
	 return false;
  }
  public static boolean checkwinForBlack(GameData data){
	  if(checkwin(data.Board,black)){
			 System.out.println("Black win!");
			 return true;
		 }

	  return false;
  }
  //=======need to be write,just return true or false
  public static boolean checkwin(short[][] data,int color){
	 ArrayList<Integer> way=new ArrayList<Integer>();
	  if(color==white){
		  for(int i=0;i<width-1;i++){
			  if(data[i][0]!=0){
				 //wait to write about how to find out the right answer!! 
				if( findway(data,color,way,i,0)){
					return true;
				}
			  }
		  }
	  }
	  if(color==black){
		  for(int i=0;i<height-1;i++){
			  if(data[0][i]!=0){
				 //wait to write about how to find out the right answer!! 
				 if( findway(data,color,way,0,i)){
					 return true;
				 }
			  }
		  }
	  }
	  return false;
  }
  private static boolean findway(short[][] data, int color, ArrayList<Integer> way, int x, int y) {
	way.add(x);
	way.add(y);
	//remember to solve about equaling problem,direction problem.
	for(int i=y-1 ;y-i<height;i--){
		if(way.size()>2){
			
			if(way.get(way.size()-4)==x&&way.get(way.size()-3)>y){
				if(y-i<y){
					continue;
				}
			}   
				if(way.get(way.size()-4)==x&&way.get(way.size()-3)<y){
					if(y-i>y){
						
						break;
					}
			}
		 
		}
		if(i==0){
			continue;
		}
		if(data[x][y-i]==3-color){
			if(i>0&&y+1<height){
				i=-1;
			}else{
			 break;
			}
		}
		if(data[x][y-i]==color&&norepeat(x,y-i,way)){
				if(y-i==height-1){
					return true;
				}
			
			if(findway(data,color,way,x,y-i)==true){
				return true;
				
			}
			break;
		}
	}
	for(int i=x-1;x-i<width;i--){
		if(way.size()>2){
			if(way.get(way.size()-4)>x&&way.get(way.size()-3)==y){
				if(x-i<x){
					continue;
				}
			}
				if(way.get(way.size()-4)<x&&way.get(way.size()-3)==y){
					if(x-i>x){
						break;
					}
			}
		 
		}
		if(i==0){
			continue;
		}
		if(data[x-i][y]==3-color){
			if(i>0&&x+1<width){
				i=-1;
			}else{
				break;
			}
			
		}
		if(data[x-i][y]==color&&norepeat(x-i,y,way)){
					if(x-i==width-1){
						return true;
					}
				
			
			if(findway(data,color,way,x-i,y)==true){
				return true;
			}
			break;
		}
		
	}
	for(int i=Math.min(x-1, y-1);x-i<width&&y-i<height;i--){
		if(way.size()>2){
			if(way.get(way.size()-4)-x==way.get(way.size()-3)-y&&way.get(way.size()-4)-x>0){
				if(y-i<y){
					continue;
				}
			}
				if(way.get(way.size()-4)-x==way.get(way.size()-3)-y&&way.get(way.size()-4)-x<0){
					if(y-i>y){
						break;
					}
			}
		 
		}
		if(i==0){
			continue;
		}
		if(data[x-i][y-i]==3-color){
			if(i>0&&x+1<width&&y+1<height){
				i=-1;
			}else{
			  break;
			}
		}
		if(data[x-i][y-i]==color&&norepeat(x-i,y-i,way)){
			if(color==white){
				if(y-i==height-1){
					return true;
				}
			}
				if(color==black){
					if(x-i==width-1){
						return true;
					}
				}
			
			if(findway(data,color,way,x-i,y-i)==true){
				return true;
			}
			break;
		}
		
	}
	for(int i=Math.min(width-1-x, y-1) ;x+i>0&&y-i<height;i--){
		if(way.size()>2){
			if(way.get(way.size()-4)-x==-way.get(way.size()-3)-y&&way.get(way.size()-4)-x>0){
				if(y-i<y){
					break;
				}
			}
				if(way.get(way.size()-4)-x==-way.get(way.size()-3)-y&&way.get(way.size()-4)-x<0){
					if(y-i>y){
						continue;
					}
			}
		 
		}
		if(i==0){
			continue;
		}
		if(data[x+i][y-i]==3-color){
			if(i>0&&x-1>0&&y+1<height){
				i=-1;
			}else{
			  break;
			}
		}
		if(data[x+i][y-i]==color&&norepeat(x+i,y-i,way)){
			
			if(color==white){
				if(y-i==height-1){
					return true;
				}
			}
			if(color==black){
				if(x==width-1){
					return true;
				}
			}
			
			if(findway(data,color,way,x+i,y-i)==true){
				return true;
			}
			break;
		}
	}
	way.remove(way.size()-1);
	way.remove(way.size()-1);
	return false;
}
private static boolean norepeat(int x, int y, ArrayList<Integer> way) {
	for(int i=0;i<way.size();i+=2){
		if(x==way.get(i)&&y==way.get(i+1)){
			return false;
		}
	}
	return true;
}
public static boolean checkmove(Move move,GameData data){
	 if(move.moveKind==ADD){
		 
		if(data.Board[move.x1][move.y1]!=empty||data.returnNum(move.color)<=0){
			return false;
		}
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				if(move.x1+i>=0&&move.x1+i<=width-1&&move.y1+j>=0&&move.y1+j<=height-1){
					
					if(findConnection(move.x1+i,move.y1+j,move.x1,move.y1,data.Board)&&!(i==0&&j==0)){
						return false;
					}
				}
			}
		}
		
	 }
	 if(move.moveKind==STEP){
		 if(data.Board[move.x2][move.y2]!=empty||data.returnNum(move.color)>0){
			 return false;
		 }
		 data.Board[move.x1][move.y1]=0;
		 for(int i=-1;i<2;i++){
				for(int j=-1;j<2;j++){
					if(move.x2+i>=0&&move.x2+i<=width-1&&move.y2+j>=0&&move.y2+j<=height-1){
						
			          if(findConnection(move.x2+i,move.y2+j,move.x2,move.y2,data.Board)&&!(i==0&&j==0)){
			        	 data.Board[move.x1][move.y1]=move.color;
			        	 return false;
			           }
					}
				}
			}
		 data.Board[move.x1][move.y1]=move.color;
		 
	 }
	if((move.x1==0&&move.y1==0)||(move.x2==0&&move.y2==0)){
		return false;
	}
	if(move.color==white){
		if(move.x1==0||move.x2==0||move.x1==width-1||move.x2==width-1){
			return false;
		}
	}
	if(move.color==black){
		if(move.y1==0||move.y2==0||move.y1==height-1||move.y2==height-1){
			return false;
		}
	
	}
	
	  
	return true;
	  
  }
  /*
   * return true if there is any connected chips 
   * x1 ,y1, for the one to search,x2,y2,for avoid
   */
  private static boolean findConnection(int x1,int y1,int x2,int y2,short[][] data){
	 
	  if(data[x1][y1]!=0){
		
	  if(x1-1>=0){
		  if(y1-1>=0){
			  if(data[x1-1][y1-1]!=0&&!((x1-1)==x2&&(y1-1)==y2)){
				  return true;
			  }
		  }
		  if(data[x1-1][y1]!=0&&!((x1-1)==x2&&(y1)==y2)){
			  return true;
		  }
		  if(y1+1<=height-1){
			  if(data[x1-1][y1+1]!=0&&!((x1-1)==x2&&(y1+1)==y2)){
				  return true;
			  }
		  }
	  }
	  {
		  if(y1-1>=0){
			  if(data[x1][y1-1]!=0&&!((x1-1)==x2&&(y1-1)==y2)){
				  return true;
			  }
		  }
		  
		  if(y1+1<=height-1){
			  if(data[x1][y1+1]!=0&&!((x1-1)==x2&&(y1+1)==y2)){
				  return true;
			  }
		  }	
	  }
	  if(x1+1<=width-1){
		  if(y1-1>=0){
			  if(data[x1+1][y1-1]!=0&&!((x1-1)==x2&&(y1-1)==y2)){
				  return true;
			  }
		  }
		  if(data[x1+1][y1]!=0&&!((x1-1)==x2&&(y1)==y2)){
			  return true;
		  }
		  if(y1+1<=height-1){
			  if(data[x1+1][y1+1]!=0&&!((x1-1)==x2&&(y1+1)==y2)){
				  return true;
			  }
		  }
	  }
   }
	return false;
  }
}
