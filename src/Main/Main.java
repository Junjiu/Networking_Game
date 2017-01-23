package Main;

import java.util.Scanner;
import player.*;
public class Main {
	  public final static int QUIT = 0;
	  public final static int ADD = 1;
	  public final static int STEP = 2;
		private static final int white=1;
		private static final int black=2;

	static GameData serive;
	static Player MPlayer;
	static HumanPlayer HPlayer;
   public static void main(String[] args ){
	   serive=new GameData();
	   
	   System.out.println("Select your color:2(black)/1(white)");
	   System.out.println(serive);
	   Scanner s=new Scanner(System.in);
	   int color=s.nextInt();
	   
	    MPlayer=new MachinePlayer(3-color);
	   HPlayer=new HumanPlayer(color);
	  
	   while(true){
		   		if( HPlayer.getColor()==white){
		   			if(HumanTurn()){
		   				break;
		   			}
		   			if(Check.checkwinForWhite(serive)){
		   				System.out.println(serive);
		   				break;
		   			}
		   			MachineTurn();
		   			System.out.println(serive);
		   			if(Check.checkwinForBlack(serive)){
		   				break;
		   			}
		   				
		   		}
		   		if( HPlayer.getColor()==black){
		   			MachineTurn();
		   			System.out.println(serive);
		   			if(Check.checkwinForWhite(serive)){
		   				break;
		   			}
		   			if(HumanTurn()){
		   				break;
		   			}
		   			System.out.println(serive);
		   			if(Check.checkwinForBlack(serive)){
		   				break;
		   			}
		   				
		   		}
	   
	   }
	   
   }

private static void MachineTurn() {
	Move move=MPlayer.chooseMove();
	System.out.println(move);
	serive.move(move);
	HPlayer.opponentMove(move);
	
	
}
//return ture when human player input QUIT;
private static boolean HumanTurn() {
	Move move=HPlayer.chooseMove();
	if(move.moveKind==QUIT){
		System.out.println("QUIT");
		return true;
	}
	serive.move(move);
	MPlayer.opponentMove(move);
	
	return false;
}
}
