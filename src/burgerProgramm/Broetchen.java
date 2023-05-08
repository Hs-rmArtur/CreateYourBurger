package burgerProgramm;

public class Broetchen extends Zutat {
	protected int backzeit; //in Sekunden
	protected int hoehe;

	public Broetchen(String name, int nummer, double preis, boolean klassisch, String typ, int backzeit, int hoehe) {
		super(name, nummer, preis, klassisch, typ);
		this.backzeit = backzeit;
		this.hoehe = hoehe;

	}

	public String zubereiten() {
		return this.name + " " + (backzeit / 60.0) + " Minuten rösten und aufschneiden.";
	}
	
	public int berechneZubereitungsZeit() {
		//+1, da Aufschneiden eine Sekunde dauert.
				return this.backzeit + 1;
	}

	public double berechneHoehe() { 
		double hoeheMinute;
		double temp = this.hoehe;

		for (int i = 0; i < (backzeit / 60); i++) {
			hoeheMinute = (temp / 100) * 2.5;
			temp += hoeheMinute;
		}
		return (Math.round(temp*10)/10.0);
	}

}
