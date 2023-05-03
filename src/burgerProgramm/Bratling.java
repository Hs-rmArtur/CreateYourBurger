package burgerProgramm;

public class Bratling extends Zutat{
	protected int backzeit;
	protected int hoehe;
	
	public Bratling(String name, int nummer, float preis, boolean klassisch, String typ, int backzeit, int hoehe) {
		super(name, nummer, preis, klassisch, typ);
		this.backzeit = backzeit;
		this.hoehe = hoehe;
		
	}
	
	public Bratling(String name, int nummer, float preis, int backzeit, int hoehe) {
		this(name, nummer, preis, false, "vegetarisch", backzeit, hoehe);
	}
	
	
	
	public int zubereiten() {
		int minutenProSeite = (backzeit / 2) / 60;
		int sekundenProSeite = (backzeit / 2) % 60;
		
		System.out.println(this.name + " von jeder Seite " + minutenProSeite + " Minuten und " + sekundenProSeite + " Sekunden grillen.");
		return this.backzeit;
	}
	
	public float berechneHoehe() {
		float hoeheMinute;
		float temp = this.hoehe;
		
		for(int i = 0; i < backzeit; i++) {
			hoeheMinute = (float)((temp / 100) * 3.5);
			temp -= hoeheMinute;
		}
		return temp;
	}
	
	public String toString() {
		return super.toString() + "\nBackzeit: " + this.backzeit +
				" Sekunden  |  Hoehe: " + this.berechneHoehe() + " mm";
	}
	
}
