package exercise211;

public class AllTheColorsOfTheRainbow {
	int anIntegerRepresentingColors;
	void changeTheHueOfTheColor(int newHue){
		anIntegerRepresentingColors=newHue;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AllTheColorsOfTheRainbow col1=new AllTheColorsOfTheRainbow();
		AllTheColorsOfTheRainbow col2=new AllTheColorsOfTheRainbow();
		col1.anIntegerRepresentingColors=15;
		col2.anIntegerRepresentingColors=20;
		//����col1��col2�ĳ�ʼֵ
		System.out.println("col1="+col1.anIntegerRepresentingColors);
		System.out.println("col2="+col2.anIntegerRepresentingColors);
		//���col1��col2��ֵ
		col1.changeTheHueOfTheColor(17);
		col2.changeTheHueOfTheColor(26);        
		//����col1��col2��ֵ
		System.out.println("���ĺ�");
		System.out.println("col1="+col1.anIntegerRepresentingColors);
		System.out.println("col2="+col2.anIntegerRepresentingColors);
		//������ĺ��ֵ
	}

}
