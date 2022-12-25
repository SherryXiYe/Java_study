
class Dog{
		void bark(){
			System.out.println("The bark is():wangwang");
		}
		void bark(int i){
			System.out.println("The bark is(int):barking");
		}
		void bark(double d){
			System.out.println("The bark is(double):howling");
		}
	}

public class Exercise505 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog=new Dog();
		dog.bark();
		dog.bark(3);
		dog.bark(2.2);
	}

}
