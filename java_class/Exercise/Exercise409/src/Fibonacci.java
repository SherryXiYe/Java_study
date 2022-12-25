
public class Fibonacci {
	static int F(int n){
		if(n==1||n==2){
			return 1;
		}
		else{
			return F(n-1)+F(n-2);
		}
	}
	static void PrintFibonacci(int n){
		System.out.print("n="+n+": ");
		for(;n>0;n--){
			System.out.print(F(n)+" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N1=5,N2=10;
		PrintFibonacci(N1);
		PrintFibonacci(N2);
	}

}
