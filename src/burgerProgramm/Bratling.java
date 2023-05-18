package burgerProgramm;


/**
 * Bratling - Unterklasse von Zutat
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Bratling extends Zutat {
	private int bratzeit;
	private int hoehe;

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
		if(bratzeit % 60 == 0) {
			return this.name + " von jeder Seite " + ((this.bratzeit / 2) / 60) + " Minuten und "
					+ ((this.bratzeit / 2) % 60) + " Sekunden grillen.";			
		} else {
			return this.name + " von jeder Seite " + ((this.bratzeit / 2) / 60) + " Minuten grillen.";	
		}
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
		double hoeheProEinheit;
		double temp = this.hoehe;

		for (int i = 0; i < (bratzeit / 60); i++) {
			hoeheProEinheit = (temp / 100) * 3.5;
			temp -= hoeheProEinheit;
		}
		
			for(int i = 0; i < (bratzeit % 60); i++) {
				hoeheProEinheit = ((temp / 100) * 3.5) / 60;
				temp -= hoeheProEinheit;
			}
		
		return (Math.round(temp * 10) / 10.0);
	}

}
