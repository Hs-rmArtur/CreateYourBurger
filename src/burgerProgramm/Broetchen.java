package burgerProgramm;

public class Broetchen extends Zutat{
	protected int backzeit;
	protected int hoehe;
	
	public Broetchen(String name, int nummer, float preis, boolean klassisch, String typ, int backzeit, int hoehe) {
		super(name, nummer, preis, klassisch, typ);
		this.backzeit = backzeit;
		this.hoehe = hoehe;
		
	}
	
	public Broetchen(String name, int nummer, float preis, int backzeit, int hoehe) {
		this(name, nummer, preis, false, "vegetarisch", backzeit, hoehe);
	}
	
	
	
	public int zubereiten() {
		System.out.println(this.name + " " + (backzeit / 60) + " Minuten rösten und aufschneiden.");
		return this.backzeit + 1;
	}
	
	public float berechneHoehe() {
		
		float hoeheMinute;
		float temp = this.hoehe;
		
		for(int i = 0; i < (backzeit / 60); i++) {
			hoeheMinute = (float)((temp / 100) * 2.5);
			temp += hoeheMinute;
		}
		return temp;
	}
	
	public String toString() {
		return super.toString() + "\nBackzeit: " + this.backzeit +
				" Sekunden  |  Hoehe: " + this.berechneHoehe() + " mm";
	}
	
}
