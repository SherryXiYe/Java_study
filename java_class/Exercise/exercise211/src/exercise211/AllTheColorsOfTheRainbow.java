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
		//设置col1、col2的初始值
		System.out.println("col1="+col1.anIntegerRepresentingColors);
		System.out.println("col2="+col2.anIntegerRepresentingColors);
		//输出col1、col2的值
		col1.changeTheHueOfTheColor(17);
		col2.changeTheHueOfTheColor(26);        
		//更改col1、col2的值
		System.out.println("更改后：");
		System.out.println("col1="+col1.anIntegerRepresentingColors);
		System.out.println("col2="+col2.anIntegerRepresentingColors);
		//输出更改后的值
	}

}
