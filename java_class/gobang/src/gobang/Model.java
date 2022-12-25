package gobang;

public class Model {
	public static final int BLACK=1;
	public static final int WHITE=-1;
	public static final int SPACE=0;
	public static final int WIDTH=12;                //定义WIDTH=12，可修改
	private int[][] map=new int[WIDTH][WIDTH];
	private int lastRow,lastCol,lastColor;
	private Model(){}                     //采用单例模式
	private static Model instance=null;
	public static Model getInstance(){
		if(instance==null){
			instance=new Model();
		}
		return instance;
	}
	public boolean playChess(int row,int col,int color){    //在棋盘模型（二维数组）中保存下棋信息
		if(row>=1&&row<=WIDTH&&col>=1&&col<=WIDTH&&(color==WHITE||color==BLACK)){
			if(map[row-1][col-1]==SPACE){
				map[row-1][col-1]=color;
				lastCol=col;
				lastRow=row;
				lastColor=color;
				return true;
			}
			else{
				View.getinstance().outPutInfo("Sorry,there is already a piece in this position.");
			}
		}
		else{
			View.getinstance().outPutInfo("Out of bounds!please try again.");
		}
		return false;           
	}
	public int getChessInfo(int row,int col){                  //获取row行col列的棋子颜色
		if(row>=1&&row<=WIDTH&&col>=1&&col<=WIDTH){
			return map[row-1][col-1];
		}
		else{
			View.getinstance().outPutInfo("Out of bounds!");
			return 0; 
		}
	}
	public int whoWin(){               //判断是否有赢家产生，若有则返回赢家所执棋子颜色
		int row,col,sum=1;
		for(row=lastRow+1;row<lastRow+5;row++){
			if(row>=1&&row<=WIDTH){
				if(map[row-1][lastCol-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		for(row=lastRow-1;row>lastRow-5;row--){
			if(row>=1&&row<=WIDTH){
				if(map[row-1][lastCol-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		if(sum==5){
			return lastColor;
		}
		sum=1;
		for(col=lastCol+1;col<lastCol+5;col++){
			if(col>=1&&col<=WIDTH){
				if(map[lastRow-1][col-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		for(col=lastCol-1;col>lastCol-5;col--){
			if(col>=1&&col<=WIDTH){
				if(map[lastRow-1][col-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		if(sum==5){
			return lastColor;
		}
		sum=1;
		for(row=lastRow+1,col=lastCol+1;row<lastRow+5&&col<lastCol+5;row++,col++){
			if(row>=1&&row<=WIDTH&&col>=1&&col<=WIDTH){
				if(map[row-1][col-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		for(row=lastRow-1,col=lastCol-1;row>lastRow-5&&col>lastCol-5;row--,col--){
			if(row>=1&&row<=WIDTH&&col>=1&&col<=WIDTH){
				if(map[row-1][col-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		if(sum==5){
			return lastColor;
		}
		sum=1;
		for(row=lastRow-1,col=lastCol+1;row>lastRow-5&&col<lastCol+5;row--,col++){
			if(row>=1&&row<=WIDTH&&col>=1&&col<=WIDTH){
				if(map[row-1][col-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		for(row=lastRow+1,col=lastCol-1;row<lastRow+5&&col>lastCol-5;row++,col--){
			if(row>=1&&row<=WIDTH&&col>=1&&col<=WIDTH){
				if(map[row-1][col-1]==lastColor){
					sum++;
				}
				else{break;}
			}
			else{break;}
		}
		if(sum==5){
			return lastColor;
		}
		return SPACE;
	}
	public void withdraw(){                           //悔棋
		map[lastRow-1][lastCol-1]=SPACE;
	}
	public boolean alreadyWithdraw(){                //判断是否刚进行过一次悔棋
		return map[lastRow-1][lastCol-1]==SPACE;
	}
}
