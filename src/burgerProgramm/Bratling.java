package burgerProgramm;

public class Bratling extends Zutat {
	protected int bratzeit;
	protected int hoehe;

	public Bratling(String name, int nummer, double preis, boolean klassisch, String typ, int backzeit, int hoehe) {
		super(name, nummer, preis, klassisch, typ);
		this.bratzeit = backzeit;
		this.hoehe = hoehe;

	}

	public String zubereiten() {
		return this.name + " von jeder Seite " + ((this.bratzeit / 2) / 60) + " Minuten und "
				+ ((this.bratzeit / 2) % 60) + " Sekunden grillen.";
	}

	public int berechneZubereitungsZeit() {
		return this.bratzeit;
	}

	public double berechneHoehe() {
		double hoeheMinute;
		double temp = this.hoehe;

		for (int i = 0; i < (bratzeit / 60); i++) {
			hoeheMinute = (temp / 100) * 3.5;
			temp -= hoeheMinute;
		}
		return (Math.round(temp * 10) / 10.0);
	}

}
