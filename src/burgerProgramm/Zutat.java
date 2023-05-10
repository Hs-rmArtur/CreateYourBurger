package burgerProgramm;

/**
 * Abstrakte Zutaten-Oberklasse.
 * Legt die Grundlagen für die Zutaten fest.
 * @author Artur Konkel, Michael Karenko
 *
 */
public abstract class Zutat {
	public static final String VEGETARISCH = "vegetarisch";
	public static final String VEGAN = "vegan";
	public static final String FLEISCHHALTIG = "";

	protected int nummer;
	protected String name;
	protected double preis;
	protected boolean vegetarisch;
	protected boolean vegan;
	protected boolean klassisch;

	/**
	 * Konstruktor dem alle Eigenschaften mitgegeben werden, um die Zutat zu definieren
	 * @param name Name der Zutat
	 * @param nummer (Bestell-)Nummer der Zutat
	 * @param preis Preis der einzelnen Zutat
	 * @param klassisch Ob die Zutat klassisch (true) ist oder nicht (false)
	 * @param typ "vegetarisch" falls vegetarisch | "vegan" falls vegan | 'leer' falls nichts von beiden  ->da nur eins von beiden gleichzeitig möglich
	 */
	public Zutat(String name, int nummer, double preis, boolean klassisch, String typ) {
		this.name = name;
		this.nummer = nummer;
		this.preis = preis;
		this.klassisch = klassisch;

		if (typ.equalsIgnoreCase(VEGETARISCH)) {
			this.vegetarisch = true;
		} else if (typ.equalsIgnoreCase(VEGAN)) {
			this.vegetarisch = true;
			this.vegan = true;
		}

	}

	/**
	 * Getter-Methode für die (Bestell-)Nummer
	 * @return Nummer der Zutat
	 */
	public int getNummer() {
		return this.nummer;
	}
	
	/**
	 * Abstrakte Methode für die Zubereitung, da jede Zutat zubereitet werden soll.
	 * @return String in der die Zubereitungsschritte gespeichert werden.
	 */
	public abstract String zubereiten();

	/**
	 * Berechnet die Zubereitungszeit der Zutat, da jede Zutat zubereitet wird.
	 * @return Zubereitungszeit der Zutat in Sekunden
	 */
	public abstract int berechneZubereitungsZeit();

	/**
	 * Soll die Höhe der Zutat in mm berechnen
	 * @return Höhe der Zutat nach Zubereitung in mm
	 */
	public abstract double berechneHoehe();

	/**
	 * To-String-Methode für die Zutat, in der die wichtigesten Eigenschaften ausgegeben werden.
	 * Folgendes wird ausgegeben: Nr: 'Nummer', "Name der Zutat", Preis: 'Preis' Euro, klassisch, vegan/vegetarisch
	 * @return Formatierter String mit den wichtigsten Eigenschaften der Zutat
	 */
	public String toString() {
		String klassisch;
		String typ = "";
		String strPreis = "";

		strPreis = String.format("%.2f", this.preis);

		if (this.klassisch) {
			klassisch = ", klassisch";
		} else {
			klassisch = "";
		}

		if (this.vegan) {
			typ = ", vegan";
		} else if (this.vegetarisch) {
			typ = ", vegetarisch";
		}

		return "Nr: " + this.nummer + ", " + this.name + ", Preis: " + strPreis +" Euro"+ klassisch + typ;
	}

}
