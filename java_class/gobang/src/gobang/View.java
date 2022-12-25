package gobang;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
	private View(){}
	private static View instance=null;
	public static View getinstance(){
		if(instance==null)
			instance=new View();
		return instance;
	}
	public void input(){               //获取下棋位置信息
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String s;
		String w=new String("w");
		String color=Controller.getinstance().getColor();
		try {
			System.out.println(color+" please row");
			s=in.readLine();
			if(s.equals(w)){              //如果用户输入w，则表示他要悔棋
				if(Model.getInstance().alreadyWithdraw())     //判断他是否才进行过一次悔棋
					outPutInfo("Sorry.You have retracted a false move in the chess game."); 
				else
					Controller.getinstance().userWithdraw();
			}
			else{
				int row=Integer.parseInt(s);
				System.out.println(color+" please col");
				s=in.readLine();
				if(s.equals(w)){
					if(Model.getInstance().alreadyWithdraw())
						outPutInfo("Sorry.You have retracted a false move in the chess game.");
					else
						Controller.getinstance().userWithdraw();
				}
				else{
					int col=Integer.parseInt(s);
					Controller.getinstance().userPlayChess(row, col);     //将下棋位置传递给Controller执行下棋操作	
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void inputFirstColor(){        //输入先手棋子颜色
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String s;
		try {
			System.out.println("Please choose the first color.Input 1 for black or 2 for white.");
			while(true){
				s=in.readLine();
				int color=Integer.parseInt(s);
				if(color==1){
					Controller.getinstance().modifyColor(Model.BLACK);
					break;
				}
				else if(color==2){
					Controller.getinstance().modifyColor(Model.WHITE);
					break;
				}
				else{
					System.out.println("Input error, please re-enter it correctly.");
				}
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void outPutInfo(String s){    //向用户输出信息
		System.out.println(s);
	}
	public void outPutMap(){              //输出当前棋盘
		int row,col;
		for(row=1;row<=Model.WIDTH;row++){
			for(col=1;col<=Model.WIDTH;col++){
				switch(Model.getInstance().getChessInfo(row, col)){
				case Model.WHITE:
					System.out.print(" ○");
					break;
				case Model.BLACK:
					System.out.print(" ●");
					break;
				case Model.SPACE:
					System.out.print(" ┼");
					break;
				}
			}
			System.out.println();
		}
	}
}
