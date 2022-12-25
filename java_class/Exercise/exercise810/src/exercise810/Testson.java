package exercise810;
class Father{
	private int order;
	private static int count=0;
	Father(){
		count++;
		order=count;
		System.out.println(order+" father() is called");
	}
	public String toString(){
		return "father"+order;
	}
	public void firstmethod(){
		System.out.println(this+" firstmethod() is called");
		secondmethod();
	}
	public void secondmethod(){
		System.out.println(this+" secondmethod() is called");
	}
}
class Son extends Father{
	private int order;
	private static int count=0;
	Son(){
		count++;
		order=count;
		System.out.println(order+" son() is called");
	}
	public String toString(){
		return "son"+order;
	}
	public void secondmethod(){
		System.out.println(this+" new secondmethod() is called");
	}
}
public class Testson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Son newson1=new Son();
		Son newson2=new Son();
		Father newfather=new Father();
		newson1.firstmethod();
		newfather.firstmethod();
		Father fa=newson2;
		fa.firstmethod();
	}

}
