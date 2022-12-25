package exercise;
class E17{
	E17(String s){
		System.out.println("create:"+s);
	}
}
class E18{
	E18(String ss){
		s=ss;
		System.out.println("create:"+ss);
	}
	String s;
}
public class Exercise514 {
	static String s1="Hello";
	static String s2;
	static {
		s2="World";
		}					
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(s1);
		System.out.println(s2);
		String[] ss={"Hello","My","World"};
		for(int i=0;i<ss.length;i++){
			System.out.println(ss[i]);
		}
		E17[] e17=new E17[4];
		E18[] e18=new E18[4];
		e18[0]=new E18("I");
		e18[1]=new E18("forever");
		e18[2]=new E18("love");
		e18[3]=new E18("you");
		for(int i=0;i<e18.length;i++){
			System.out.print(e18[i].s);
		}
	}

}
