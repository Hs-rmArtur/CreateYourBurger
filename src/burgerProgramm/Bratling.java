package burgerProgramm;
/**
 * Beschreibt ein Burgerbratling.
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Bratling extends Zutat {
	protected int bratzeit;
	protected int hoehe;

	/**
	 * Standardkonstruktor zum erstellen eines Bratlings.
	 * @param name
	 * @param nummer
	 * @param preis
	 * @param klassisch
	 * @param typ
	 * @param backzeit
	 * @param hoehe
	 */
	public Bratling(String name, int nummer, double preis, boolean klassisch, String typ, int backzeit, int hoehe) {
		super(name, nummer, preis, klassisch, typ);
		this.bratzeit = backzeit;
		this.hoehe = hoehe;

	}

	/**
	 * Ausgabe, wie das Broetchen zubereitet werden soll.
	 */
	public String zubereiten() {
		return this.name + " von jeder Seite " + ((this.bratzeit / 2) / 60) + " Minuten und "
				+ ((this.bratzeit / 2) % 60) + " Sekunden grillen.";
	}

	/**
	 * Berechnung der Zubereitungszeit.
	 */
	public int berechneZubereitungsZeit() {
		return this.bratzeit;
	}
	
	/**
	 * Berechnung der Hoehe des Bratlings, das sich bei der zubereitung ändert.
	 */
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
