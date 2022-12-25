
public class Exercise {
public enum Money{
	ONE,FIVE,TEN,TWENTY,FIFTY,ONE_HUNDRED
}
static void describe(Money m){
	System.out.println("The describe of "+m+":");
	switch(m){
	case ONE:	System.out.println("一元");
				break;
	case FIVE:	System.out.println("五元");
				break;
	case TEN:	System.out.println("十元");
				break;
	case TWENTY:	System.out.println("二十元");
				break;
	case FIFTY:	System.out.println("五十元");
				break;
	case ONE_HUNDRED:	System.out.println("一百元");
				break;
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(Money m:Money.values()){
			System.out.println(m+" ordinal:"+m.ordinal());
		}
		Money m=Money.FIFTY;
		describe(m);
	}

}
