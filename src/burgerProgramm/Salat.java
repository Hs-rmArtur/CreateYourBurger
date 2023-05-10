package burgerProgramm;
/**
 * Beschreibt ein Salat.
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Salat extends Zutat {
	
	/**
	 * Konstruktor zur Erstellung eines Salats.
	 * @param name
	 * @param nummer
	 * @param preis
	 * @param klassisch
	 */
	public Salat(String name, int nummer, double preis, boolean klassisch) {
		super(name, nummer, preis, klassisch, VEGAN);
	}
	
	/**
	 * Berechnung der Hoehe.
	 */
	@Override
	public double berechneHoehe() {
		return 0;
	}
	
	/**
	 * Ausgabe, wie der Salat zubereitet werden soll.
	 */
	public String zubereiten() {
		return this.name + " wird gewaschen und geschleudert.";
	}
	
	/**
	 * Berechnung der Zubereitungszeit.
	 */
	public int berechneZubereitungsZeit() {
		return 0;
	}

}
