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
		newcon.index=count;      //为新生成的对象的生成顺序赋值
		return newcon;           //返回新生成的对象
	}
	public String toString(){    //重载toString函数
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
	public static Connection getinstance(){    //取走一个Connection对象
		for(int i=0;i<n;i++)
			if(con[i]!=null){
				Connection sCon=con[i];
				System.out.println("Connection"+con[i].getindex()+"已取出");
				con[i]=null;
				return sCon;
			}
		return null;            //若ConnectionMannager之中不再有对象，则返回null引用
	}
	public static void deleteAllCon(){       //删除ConnectionManager中所有对象
		for(int i=0;i<n;i++)
			con[i]=null;
	}
	public static void replaceCon(Connection r){   //放回一个Connection对象
		for(int i=0;i<n;i++){
			if(con[i]==null){
				con[i]=r;
				r=null;
				System.out.println("Connection"+con[i].getindex()+"已放回");
				return;
			}
		}
	}
	public static void outexist(){     //输出存在的Connection对象
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
		for(int i=0;i<ConnectionManager.getn();i++){ //取完ConnectionManager中Connection对象
			testCon[i]=ConnectionManager.getinstance();
		}
		Connection c=ConnectionManager.getinstance();
		System.out.println(c);     //取完后再去ConnectionManager中取，观察结果
		for(int i=0;i<ConnectionManager.getn();i++) //放回ConnectionManager中Connection对象
		{
			ConnectionManager.replaceCon(testCon[i]);
		}
		ConnectionManager.outexist();
		ConnectionManager.deleteAllCon();    //删除ConnectionManager中所有Connection对象
		Connection d=ConnectionManager.getinstance();
		System.out.println(d);         //检测是否删完
	}

}
