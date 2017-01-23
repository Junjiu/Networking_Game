/* MachinePlayer.java */
/*
 * those move ,has to be check before put it into Game Date
 */
package player;

import java.util.ArrayList;
import java.util.Random;

import Main.Check;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {
  private static final int deepth=3;
  private static final int width=8;
	private static final int height=8;
	private static final int empty=0;
	private static final int white=1;
	private static final int black=2;
	private ArrayList<Integer> Wexist=new ArrayList<Integer>();// To store those point which are already exist for move operation.
	private ArrayList<Integer> Bexist=new ArrayList<Integer>();
  private GameData data=new GameData();
  private short color;
  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
	  this.color=(short) color;
  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
	  this.color=(short) color;
  }

  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  // also change the date in player,and check it !!!
  public Move chooseMove() {
	 deep=0;
	  finalNode=new TreeNode();
	  faildeep=1000;
	 TreeNode root=Buildtree(new TreeNode(new GameData(data),null));
	 
    
	 
	 Move move= search_move(root);
	 for(int i=0;i<root.child.size();i++){
		 System.out.println(root.child.get(i).faildeep);
	 }
	 if(move==null){
		move= search_despressing(root);
	 }
	 if(move==null){
		 System.out.println("OK,YOU WIN!");
		 Random rand=new Random();
			move=root.child.get(rand.nextInt(root.child.size())).move;
		 }
	 data.move(move);
	 return move;
	
	
  }
  private Move search_despressing(TreeNode root) {
	for(int i=0;i<root.child.size();i++){
		if(root.child.get(i).faildeep!=2){
			return root.child.get(i).move;
		}
	}
	return null;
}
private TreeNode finalNode;
  private int faildeep;
  private Move search_move(TreeNode root) {
	  deep=root.deep;
	 
		  if(root.winner==color&&finalNode.deep>root.deep){	
			  finalNode=root;
		  }
		  if(root.winner==(3-color)){	
			  System.out.println(deep+"  deep");
			  faildeep=deep;
				}
	  
	for(int i=0;i<root.child.size();i++){	
		if(root.pre==null){
			  faildeep=1000;
		  }
		search_move(root.child.get(i));
		deep=root.deep;
	}
		if(deep==1){
			root.faildeep=faildeep;
		}
		if(faildeep<finalNode.deep&&deep==1){
			System.out.println("it works"+ faildeep+"  "+deep+"  "+finalNode.deep);
			finalNode=new TreeNode();
			  faildeep=1000;
		}

	return finalNode.move;
}

//search those point which are already exist and put it in the exist array.
  private void searchexist(GameData board) {
	for(int i=0;i<width;i++){
		for(int j=0;j<height;j++){
			if(board.Board[i][j]==white){
				Wexist.add(i);
				Wexist.add(j);
			}
			if(board.Board[i][j]==black){
				Bexist.add(i);
				Bexist.add(j);
			}
		}
	}
	
}
private int deep=0;

  private TreeNode Buildtree(TreeNode root){
	
	  if(Check.checkwin(root.Board.Board, white)){
		  root.winner=white;
		 
		  deep--;
		  return root;
	  }
	  if(Check.checkwin(root.Board.Board, black)){
		  root.winner=black;
		
		
		  deep--;
		  return root;
	  }
	 if((getcolor()==white&&root.Board.WhiteNum>0)||(getcolor()==black&&root.Board.BlackNum>0)){
		  for(int i=0;i<width;i++){
			  for(int j=0;j<height;j++){
				
					Move move=new Move(i,j,getcolor());
					if(Check.checkmove(move, root.Board)){
						if(deep<deepth){
							
							deep++;
							GameData childBoard=new GameData(root.Board);
							childBoard.move(move);
							
							TreeNode childNode=new TreeNode(childBoard,root);
							childNode.move=move;
							childNode.deep=deep;
							root.child.add(childNode);
							Buildtree(childNode);
						}else{
							deep--;
							return root;
						}
						
					}
				
			  }
		  }
	 }else {
		 Wexist.clear();
		 Bexist.clear();
		 searchexist(root.Board);
		 ArrayList<Integer> exist;
		 if(getcolor()==white){
				 exist=Wexist;
			 }else{
				 exist=Bexist;
			 }
	
		for(int i=0;i<exist.size();i+=2){
			for(int j=0;j<width;j++){
				for(int k=0;k<height;k++){
					 Wexist.clear();
					 Bexist.clear();
					 searchexist(root.Board);  
					
					Move move=new Move(exist.get(i),exist.get(i+1),j,k,getcolor());	
					
					if(Check.checkmove(move, root.Board)){
						if(deep<deepth){
							
							deep++;
						    
							GameData childBoard=new GameData(root.Board);
							childBoard.move(move);

							TreeNode childNode=new TreeNode(childBoard,root);
							childNode.move=move;
							childNode.deep=deep;
							root.child.add(childNode);
							Buildtree(childNode);
						}else{
							deep--;
							return root;
						}
						
					}
				}
			}
		}
		
		
		 
	 }
	
	deep--;
	return root;
	
}

private short getcolor() {
	if((deep)%2==1){
		return (short) (3-color);
	}else{
		return color ;
	}
	
}

// If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
	data.move(m);
    return false;
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    return false;
  }

}
