package burgerProgramm;

/**
 * Sauce - Unterklasse von Zutat
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Sauce extends Zutat {
	public static final String SUESS = "suess";
	public static final String SCHARF = "scharf";
	public static final String NORMAL = "normal";
	
	private int menge;
	private String geschmack;

	/**
	 * Konstruktor für Zutaten der Kategorie Sauce
	 * @param name Name der Zutat
	 * @param nummer (Bestell-)Nummer
	 * @param preis Einzelpreis der Zutat
	 * @param klassisch Ob die Zutat klassisch (true) ist oder nicht (false)
	 * @param typ Ob die Zutat vegan / vegetarisch / nichts ist
	 * @param menge Die Vorgegebene Menge der Sauce
	 * @param geschmack Der Vorgegebene Geschmack der Sauce
	 */
	public Sauce(String name, int nummer, double preis, boolean klassisch, String typ, int menge, String geschmack) {
		super(name, nummer, preis, klassisch, typ);
		this.menge = menge;
		this.geschmack = geschmack;
	}
	
	public int getMenge() {
		return this.menge;
	}
	
	public String getGeschmack() {
		return this.geschmack;
	}

	/**
	 * Das formatieren des Strings für die Zubereitung der Sauce.
	 * @return Formatierter String der Zubereitungsschritte
	 */
	public String zubereiten() {
		return this.name + " wird geschuettelt.";
	}
	
	/**
	 * Berechnet die Zubereitungszeit der Zutat. Saucen haben keine Zubereitungszeit, weshalb sie 0 ist.
	 * @return Gibt die Zubereitungszeit zurück
	 */
	public int berechneZubereitungsZeit() {
		return 0;
	}
	
	/**
	 * Berechnet die Höhe von Sauce. Sauce hat keine Höhe.
	 * @return Gibt die Höhe von Sauce zurück
	 */
	public double berechneHoehe() {
		return 0;
	}

}
