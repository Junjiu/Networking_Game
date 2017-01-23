package player;

public class GameData {
	public int WhiteNum=10;
	public int BlackNum=10;
	public short[][] Board=new short[width][height];
	private static final int width=8;
	private static final int height=8;
	private static final int empty=0;
	private static final int white=1;
	private static final int black=2;
	 public final static int QUIT = 0;
	  public final static int ADD = 1;
	  public final static int STEP = 2;
	
	
	public GameData(){
		
	}
	public GameData(GameData data){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				Board[i][j]=data.Board[i][j];
			}
		}
		this.WhiteNum=data.WhiteNum;
		this.BlackNum=data.BlackNum;
	}
	public String toString(){
		String str=new String("  ----------------------------------------\n");
		for(int i=0;i<height;i++){
			str+=i;
			for(int j=0;j<width;j++){
				
				str+="|";
				switch(Board[j][i]){
					case empty:
						str+="    ";
						break;
					case white:
						str+=" WW ";
						break;
					case black:
						str+=" BB ";
					}
				
				
			}
			str+="|\n  ---------------------------------------\n";
			
		}
		str+="   0    1    2    3    4    5    6     7";
		return str;
		
	}
	//input the change to date,and change the chip
	public void move(Move move){
		
		
		if(move.moveKind==ADD){
			
			Board[move.x1][move.y1]=move.color;
			if(move.color==white){
				WhiteNum--;	
				if(WhiteNum==0){
			System.out.println("White chips run out");
		}
			}else{
				BlackNum--;
				if(BlackNum==0){
			System.out.println("Black chips run out");
		}
			}
		}
		if(move.moveKind==STEP){
				Board[move.x1][move.y1]=empty;
				Board[move.x2][move.y2]=move.color;
			
		}
	}
	public int returnNum(int color){
		if(color==white){
			return WhiteNum;
		}
		if(color==black){
			return BlackNum;
		}
		return 0;
	}
}
