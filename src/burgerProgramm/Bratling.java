package burgerProgramm;

public class Bratling extends Zutat {
	protected int bratzeit;
	protected int hoehe;

	public Bratling(String name, int nummer, float preis, boolean klassisch, String typ, int backzeit, int hoehe) {
		super(name, nummer, preis, klassisch, typ);
		this.bratzeit = backzeit;
		this.hoehe = hoehe;

	}

	public Bratling(String name, int nummer, float preis, int backzeit, int hoehe) {
		this(name, nummer, preis, false, "vegetarisch", backzeit, hoehe);
	}

	public int zubereiten() {
		int minutenProSeite = (bratzeit / 2) / 60;
		int sekundenProSeite = (bratzeit / 2) % 60;

		System.out.println(this.name + " von jeder Seite " + minutenProSeite + " Minuten und " + sekundenProSeite
				+ " Sekunden grillen.");
		return this.bratzeit;
	}

	public double berechneHoehe() {
		double hoeheMinute;
		double temp = this.hoehe;

		for (int i = 0; i < bratzeit; i++) {
			hoeheMinute = (temp / 100) * 3.5;
			temp -= hoeheMinute;
		}
		return temp;
	}

	public String toString() {
		return super.toString() + "\nBackzeit: " + this.bratzeit + " Sekunden  |  Hoehe: " + this.berechneHoehe()
				+ " mm";
	}

}
