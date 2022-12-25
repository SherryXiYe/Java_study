package exercise910;
enum Note{
	MIDDLE_C,C_SHARP,B_FLAT;
}
interface Instrument{ 
	void adjust(); 
} 
interface Playable{ 
	void play(Note n); 
} 
class Wind implements Instrument, Playable { 
	public void play(Note n){ 
		System.out.println(this + ".play() " + n); 
	} 
	public String toString(){
		return "Wind";
	}
	public void adjust(){
		System.out.println(this + ".adjust()"); 
	} 
} 
class Percussion implements Instrument, Playable { 
	public void play(Note n){ 
		 System.out.println(this + ".play() " + n); 
	} 
	public String toString(){
		 return "Percussion";
	} 
	public void adjust(){
		 System.out.println(this + ".adjust()");
	} 
} 
class Stringed implements Instrument, Playable { 
	public void play(Note n){ 
		 System.out.println(this + ".play() " + n); 
	 } 
	public String toString(){
		 return "Stringed";
	} 
	public void adjust(){
		 System.out.println(this + ".adjust()");
	} 
} 
class Brass extends Wind{ 
	public String toString(){
		return "Brass";
	} 
} 
class Woodwind extends Wind{ 
	public String toString(){
		return "Woodwind";
	} 
}
public class Music5 {
	static void tune(Playable i){
		i.play(Note.MIDDLE_C);
	}
	static void tuneAll(Playable[] e){
		for(Playable i:e)
			tune(i);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Playable[] orchestra = {
				new Wind(), 
				new Percussion(), 
				new Stringed(), 
				new Brass(), 
				new Woodwind() 
		}; 
		
		tuneAll(orchestra); 
	}

}
