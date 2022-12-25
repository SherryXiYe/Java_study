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
	public void input(){               //��ȡ����λ����Ϣ
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String s;
		String w=new String("w");
		String color=Controller.getinstance().getColor();
		try {
			System.out.println(color+" please row");
			s=in.readLine();
			if(s.equals(w)){              //����û�����w�����ʾ��Ҫ����
				if(Model.getInstance().alreadyWithdraw())     //�ж����Ƿ�Ž��й�һ�λ���
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
					Controller.getinstance().userPlayChess(row, col);     //������λ�ô��ݸ�Controllerִ���������	
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void inputFirstColor(){        //��������������ɫ
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
	public void outPutInfo(String s){    //���û������Ϣ
		System.out.println(s);
	}
	public void outPutMap(){              //�����ǰ����
		int row,col;
		for(row=1;row<=Model.WIDTH;row++){
			for(col=1;col<=Model.WIDTH;col++){
				switch(Model.getInstance().getChessInfo(row, col)){
				case Model.WHITE:
					System.out.print(" ��");
					break;
				case Model.BLACK:
					System.out.print(" ��");
					break;
				case Model.SPACE:
					System.out.print(" ��");
					break;
				}
			}
			System.out.println();
		}
	}
}
