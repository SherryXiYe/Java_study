package exercise207;

public class StaticTest {
	static int i=47;
	static void increment(){
		StaticTest.i++;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StaticTest st1=new StaticTest();
		StaticTest st2=new StaticTest();
		System.out.println("i="+i);
		st1.increment();
		System.out.println("i="+i);
		StaticTest.increment();
		System.out.println("i="+i);
	}

}
