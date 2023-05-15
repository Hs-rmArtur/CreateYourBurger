package burgerProgramm;

/**
 * Gemuese - Unterklasse von Zutat
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Gemuese extends Zutat {
	private int scheibenAnzahl;
	private int scheibenDicke;

	/**
	 * Konstruktor von Zutaten der Kategorie Gemuese
	 * Ist immer vegan
	 * @param name Name der Zutat
	 * @param nummer (Bestell-)Nummer der Zutat
	 * @param preis Einzelpreis der Zutat
	 * @param klassisch Ob die Zutat klassisch (true) ist oder nicht (false)
	 * @param scheibenAnzahl Vorgegebene Anzahl der Scheiben je Zutat
	 * @param scheibenDicke Dicke der einzelnen Scheiben
	 */
	public Gemuese(String name, int nummer, double preis, boolean klassisch, int scheibenAnzahl,int scheibenDicke) {
		super(name, nummer, preis, klassisch, VEGAN);
		this.scheibenAnzahl = scheibenAnzahl;
		this.scheibenDicke = scheibenDicke;
	}

	/**
	 * Berechnet die Gesamthöhe der Scheiben in Abhängigkeit der Dicke
	 * @return Gesamthöhe des einzelnen Gemueses
	 */
	public double berechneHoehe() {
		return (scheibenDicke * scheibenAnzahl);
	}

	/**
	 * Der String für das Zubereiten wird hier formatiert.
	 * ->Die einzelnen Schritte für das Schneiden der Scheiben.
	 * @return Formatierter String der Zubereitungsschritte
	 */
	public String zubereiten() {
		String temp = this.name + " wird gewaschen.\n";
	
		for (int i = 1; i <= this.scheibenAnzahl; i++) {
			temp += "    " + i + ". Scheibe mit " + this.scheibenDicke + " mm schneiden.";
			if(i < this.scheibenAnzahl) {
				temp += "\n";
			}
		}
		return temp;
	}

	/**
	 * Berechnet die Zubereitungszeit für Gemuese in Sekunden (Schneiden dauert 1 Sekunde)
	 * @return Zubereitungszeit in Sekunden
	 */
	public int berechneZubereitungsZeit() {
		return this.scheibenAnzahl;
	}

}
