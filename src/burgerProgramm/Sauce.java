package burgerProgramm;

/**
 * Beschreibt eine Sauce.
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Sauce extends Zutat {
	public static final String SUESS = "suess";
	public static final String SCHARF = "scharf";
	public static final String NORMAL = "normal";
	
	protected int menge;
	protected String geschmack;

	public Sauce(String name, int nummer, double preis, boolean klassisch, String typ, int menge, String geschmack) {
		super(name, nummer, preis, klassisch, typ);
		this.menge = menge;
		this.geschmack = geschmack;
	}

	public Sauce(String name, int nummer, double preis, int menge, String geschmack) {
		this(name, nummer, preis, false, "", menge, geschmack);
	}
	
	
	/**
	 * Ausgabe, wie die Sauce zubereitet werden soll.
	 */
	public String zubereiten() {
		return this.name + " wird geschuettelt.";
	}
	
	/**
	 * Berechnung der Zubereitungszeit.
	 */
	public int berechneZubereitungsZeit() {
		return 0;
	}
	
	
	/**
	 * Berechnung der Hoehe der Sauce auf dem Burger.
	 */
	public double berechneHoehe() {
		return 0;
	}

}
