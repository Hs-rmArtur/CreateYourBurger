package burgerProgramm;


/**
 * Bratling - Unterklasse von Zutat
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Bratling extends Zutat {
	protected int bratzeit;
	protected int hoehe;

	/**
	 * Konstruktor für Zutaten der Kategorie Bratling
	 * @param name Name der Zutat
	 * @param nummer (Bestell-)Nummer der Zutat
	 * @param preis Einzelpreis der Zutat
	 * @param klassisch Ob die Zutat klassisch (true) ist oder nicht (false)
	 * @param typ Ob die Zutat vegetarisch / vegan / nichts von beiden ist
	 * @param backzeit Bratzeit des Bratlings in Sekunden
	 * @param hoehe Höhe des Bratlings
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
