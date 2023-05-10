package burgerProgramm;

/**
 * Salat - Unterklasse von Zutat
 * @author mkare
 *
 */
public class Salat extends Zutat {

	/**
	 * Konstruktor für Zutaten der Kategorie Salat
	 * Salat ist immer Vegan
	 * @param name Name der Zutat
	 * @param nummer (Bestell-)Nummer der Zutat
	 * @param preis Einzelpreis der Zutat
	 * @param klassisch Ob die Zutat klassisch (true) ist oder nicht (false)
	 */
	public Salat(String name, int nummer, double preis, boolean klassisch) {
		super(name, nummer, preis, klassisch, VEGAN);
	}
	
	/**
	 * Berechnet die Höhe von Salat. Salat hat keine Höhe.
	 * @return Gibt die Höhe von Salat zurück
	 */
	public double berechneHoehe() {
		return 0;
	}

	/**
	 * Das formatieren des Strings für die Zubereitung des Salats.
	 * @return Formatierter String der Zubereitungsschritte
	 */
	public String zubereiten() {
		return this.name + " wird gewaschen und geschleudert.";
	}
	
	/**
	 * Berechnet die Zubereitungszeit der Zutat. Salate haben keine Zubereitungszeit, weshalb sie 0 ist.
	 * @return Gibt die Zubereitungszeit zurück
	 */
	public int berechneZubereitungsZeit() {
		return 0;
	}

}
