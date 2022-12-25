package exercise206;

public class Exercise206 {
	String s="Hello,World!";
	void print(){
		System.out.println(storage(s));
	}
	int storage(String s){
		return s.length()*2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exercise206 a=new Exercise206();
		a.print();
	}

}
