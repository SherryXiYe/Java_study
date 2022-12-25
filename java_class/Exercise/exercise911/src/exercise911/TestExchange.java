package exercise911;

interface Processor{
	String name();
	Object process(Object input);
}
class Apply{
	public static void process(Processor p,Object s){
		System.out.println("Using Processor "+p.name());
		System.out.println(p.process(s));
	}
}
class ExchangeCharacter{
	public static String exchangChar(String s){
		StringBuilder news = new StringBuilder(s); 
		for(int i = 0; i < news.length() - 1; i += 2) { 
			char c1 = news.charAt(i); 
			char c2 = news.charAt(i + 1); 
			news.setCharAt(i, c2); 
			news.setCharAt(i + 1, c1); 
		} 
		return news.toString();
	}
}
class ExchangeAdapter implements Processor{
	ExchangeCharacter exChar;
	public ExchangeAdapter(ExchangeCharacter exChar){
		this.exChar=exChar;
	}
	public String name() { 
		return ExchangeCharacter.class.getSimpleName(); 
	} 
	public String process(Object input) { 
		return ExchangeCharacter.exchangChar((String)input); 
	} 
}
public class TestExchange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="xyxyxy";
		String s2="hello";
		System.out.println(s1);
		System.out.println(s2);
		Apply.process(new ExchangeAdapter(new ExchangeCharacter()), s1); 
		Apply.process(new ExchangeAdapter(new ExchangeCharacter()), s2); 
	}

}
