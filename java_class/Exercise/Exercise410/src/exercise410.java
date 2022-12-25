
public class exercise410 {
	static int i=1000,x,y;
	static boolean Isnum(int m1,int m2,int n1,int n2){	
		x=m1*10+m2;
		y=n1*10+n2;
		if(x*y==i){
			System.out.println(x+"*"+y+":"+i);
			return true;
		}
		else{
			return false;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int g,s,b,q;
		for(;i<10000;i++){
			g=i%10;
			s=i/10%10;
			b=i/100%10;
			q=i/1000;
			if(g==0&&s==0){
				continue;
			}
			if(Isnum(g,s,b,q)){continue;};if(Isnum(g,s,q,b)){continue;};
			if(Isnum(s,g,b,q)){continue;};if(Isnum(s,g,q,b)){continue;};
			if(Isnum(g,b,s,q)){continue;};if(Isnum(g,b,q,s)){continue;};
			if(Isnum(b,g,s,q)){continue;};if(Isnum(b,g,q,s)){continue;};
			if(Isnum(g,q,s,b)){continue;};if(Isnum(g,q,b,s)){continue;};
			if(Isnum(q,g,s,b)){continue;};if(Isnum(q,g,b,s)){continue;};
		}
	}

}
