class Dog{
	String name;
	String says;
}
public class exercise305 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog1=new Dog();
		Dog dog2=new Dog();
		dog1.name="spot";
		dog2.name="scruffy";
		dog1.says="Ruff!";
		dog2.says="Wurf!";
		System.out.println("name:"+dog1.name+" says:"+dog1.says);
		System.out.println("name:"+dog2.name+" says:"+dog2.says);
	}

}
