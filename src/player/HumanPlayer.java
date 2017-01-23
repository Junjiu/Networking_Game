package player;

import java.util.Scanner;

import Main.Check;

public class HumanPlayer {
	private GameData date=new GameData();
	private short color;
	private int chip;
  public HumanPlayer(int color){
	  this.color=(short) color;
	  chip=10;
  }
  public int getColor(){
	  return color;
  }
  public Move chooseMove() {
	 
	
	 while(true){
		Move move;
		 Scanner s=new Scanner(System.in);
		  String str=s.nextLine(); 
	  	if(str.equals("QUIT")){
	  		return new Move();
	  	}
	  	
	  	
	  	s=new Scanner(str);
      	int x1=s.nextInt();
      	int y1=s.nextInt();
      	if(s.hasNext()){
    	int x2=s.nextInt();
      	int y2=s.nextInt();
       	move=new Move(x1,y1,x2,y2,color);
    	}else{
    		move=new Move(x1,y1,color);
    	}
      	
    	if(Check.checkmove(move,date)){
    		chip--;
    		date.move(move);
    		return move;
    	}else{
    		System.out.println("illegel input,please type again");
    	}
	}
	
  }
public void opponentMove(Move move) {
	date.move(move);
	
}
}
