class S1{
	String s;
}
class S2{
	String s1="Hello";
	String s2;
	S2(){s2="World";}
}
class E3{
	E3(){
		System.out.println("you create an object");
	}
	E3(String s){
		System.out.println("you create an object:"+s);
	}
}
class Dog{
	void bark(char c,int i){
		System.out.println("The bark is:barking");
	}
	void bark(int i,char c){
		System.out.println("The bark is:howling");
	}
}
class E7{
	int i;
}
public class exercise {

	public static void main(String[]args){
		S1 ss=new S1();
		System.out.println(ss.s);
		S2 e2=new S2();
		System.out.println(e2.s1);
		System.out.println(e2.s2);
		new E3();
		new E3("hello");
		Dog dog=new Dog();
		dog.bark(3,'s');
		dog.bark('s',3);
		E7 e7=new E7();
	}
}
