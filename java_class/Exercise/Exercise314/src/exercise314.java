public class exercise314 {
	static void CompareString(String s1,String s2){
		System.out.println(s1+"=="+s2+":"+(s1==s2));
		System.out.print(s1+"!="+s2+":");
		System.out.println(s1!=s2);
		System.out.println(s1+".equals("+s2+"):"+s1.equals(s2));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompareString("myname","myname");
		CompareString("myname","mylife");
		String s=new String("myname");
		CompareString("myname",s);
	}

}
