package exercise519;

public class Exercise519 {
	static void varargsString(String...ss){
		for(String s:ss){
			System.out.print(s+" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] str=new String[]{
				"Hello",
				",",
				"World",
				"!",
		};
		varargsString("Hello",",","my","friend","!");
		varargsString(str);
	}

}
