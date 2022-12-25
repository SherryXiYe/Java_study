package exercise608;

class Connection{
	private Connection(){}
	private static int count=0;
	private int index;
	public int getindex(){
		return index;
	}
	public static Connection makeConnection(){
		Connection newcon=new Connection();
		count++;
		newcon.index=count;      //Ϊ�����ɵĶ��������˳��ֵ
		return newcon;           //���������ɵĶ���
	}
	public String toString(){    //����toString����
		return "Connection "+index+" exist";
	}
}
class ConnectionManager{
	private static int n=5;
	public static int getn(){
		return n;
	}
	private static Connection[] con=new Connection[n];
	static{
		for(int i=0;i<n;i++)
			con[i]=Connection.makeConnection();
	}
	public static Connection getinstance(){    //ȡ��һ��Connection����
		for(int i=0;i<n;i++)
			if(con[i]!=null){
				Connection sCon=con[i];
				System.out.println("Connection"+con[i].getindex()+"��ȡ��");
				con[i]=null;
				return sCon;
			}
		return null;            //��ConnectionMannager֮�в����ж����򷵻�null����
	}
	public static void deleteAllCon(){       //ɾ��ConnectionManager�����ж���
		for(int i=0;i<n;i++)
			con[i]=null;
	}
	public static void replaceCon(Connection r){   //�Ż�һ��Connection����
		for(int i=0;i<n;i++){
			if(con[i]==null){
				con[i]=r;
				r=null;
				System.out.println("Connection"+con[i].getindex()+"�ѷŻ�");
				return;
			}
		}
	}
	public static void outexist(){     //������ڵ�Connection����
		for(int i=0;i<n;i++){
			if(con[i]!=null)
				System.out.println(con[i]);
		}
	}
}
public class TestConnectionManager {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection[] testCon=new Connection[ConnectionManager.getn()];
		for(int i=0;i<ConnectionManager.getn();i++){ //ȡ��ConnectionManager��Connection����
			testCon[i]=ConnectionManager.getinstance();
		}
		Connection c=ConnectionManager.getinstance();
		System.out.println(c);     //ȡ�����ȥConnectionManager��ȡ���۲���
		for(int i=0;i<ConnectionManager.getn();i++) //�Ż�ConnectionManager��Connection����
		{
			ConnectionManager.replaceCon(testCon[i]);
		}
		ConnectionManager.outexist();
		ConnectionManager.deleteAllCon();    //ɾ��ConnectionManager������Connection����
		Connection d=ConnectionManager.getinstance();
		System.out.println(d);         //����Ƿ�ɾ��
	}

}
