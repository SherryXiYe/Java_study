
public class Exercise {
public enum Money{
	ONE,FIVE,TEN,TWENTY,FIFTY,ONE_HUNDRED
}
static void describe(Money m){
	System.out.println("The describe of "+m+":");
	switch(m){
	case ONE:	System.out.println("һԪ");
				break;
	case FIVE:	System.out.println("��Ԫ");
				break;
	case TEN:	System.out.println("ʮԪ");
				break;
	case TWENTY:	System.out.println("��ʮԪ");
				break;
	case FIFTY:	System.out.println("��ʮԪ");
				break;
	case ONE_HUNDRED:	System.out.println("һ��Ԫ");
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
