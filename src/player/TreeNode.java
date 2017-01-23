package player;
import java.util.ArrayList;


public class TreeNode {
  public GameData Board;
  public int faildeep;
  public ArrayList<TreeNode> child;
  public TreeNode pre;
  public Move move;// This is the move to this board.
  public int winner;
  public int deep;
  public TreeNode(GameData Board,TreeNode pre){
	  this.Board=Board;
	  child=new ArrayList<TreeNode>();
	  this.pre=pre;
  }
  public TreeNode(){
	  deep=200;
  }
  public String toString(){
	  return Board.toString();
  }
}
