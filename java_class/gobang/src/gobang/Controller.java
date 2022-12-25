package gobang;

public class Controller {

	private int color;
	private static boolean continueGame=true;
	private Controller(){}
	private static Controller instance=null;
	public static Controller getinstance(){
		if(instance==null){
			instance=new Controller();
		}
		return instance;
	}
	public void modifyColor(int color){
		this.color=color;
	}
	public String getColor(){      //�����ɫ��Ϣ�������ַ�����ʽ����
		String s=new String();
		switch(color){
		case Model.WHITE:
			s="WHITE";
			break;
		case Model.BLACK:
			s="BLACK";
			break;
		}
		return s;
	}
	public void userPlayChess(int row,int col){           //������ģ�������岢�ж��Ƿ���Ӯ��
		boolean success=Model.getInstance().playChess(row, col, color);
		if(success){
			color=-color;
			int winner=Model.getInstance().whoWin();
			switch(winner){
			case Model.WHITE:
				View.getinstance().outPutInfo("White chess wins!");
				View.getinstance().outPutMap();		//������������
				continueGame=false;
				break;
			case Model.BLACK:
				View.getinstance().outPutInfo("Black chess wins!");
				View.getinstance().outPutMap();		//������������
				continueGame=false;
				break;
			case Model.SPACE:
				View.getinstance().outPutMap();		//������������
				break;
				
			}
		}
	}
	public void userWithdraw(){
		color=-color;
		Model.getInstance().withdraw();
		View.getinstance().outPutMap();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			View.getinstance().outPutInfo("A Gobang game begins!"+"\n"
		+"Notice: You can input \"w\" to retract a false move in the chess game");
			View.getinstance().inputFirstColor();
			while(continueGame){
				View.getinstance().input();
			}
	}

}
