package burgerProgramm;

public class Gemuese extends Zutat {
	protected int scheibenAnzahl;
	protected int scheibenDicke;

	public Gemuese(String name, int nummer, float preis, boolean klassisch, String typ, int scheibenAnzahl,
			int scheibenDicke) {
		super(name, nummer, preis, klassisch, typ);
		this.scheibenAnzahl = scheibenAnzahl;
		this.scheibenDicke = scheibenDicke;
	}

	public Gemuese(String name, int nummer, float preis, int scheibenAnzahl, int scheibenDicke) {
		this(name, nummer, preis, false, "vegetarisch", scheibenAnzahl, scheibenDicke);
	}

	public double berechneHoehe() {
		return (scheibenDicke * scheibenAnzahl);
	}

	public String zubereiten() {
		String temp = this.name + " wird gewaschen.\n";

		for (int i = 1; i <= this.scheibenAnzahl; i++) {
			temp += i + ". Scheibe mit " + this.scheibenDicke + " mm schneiden.";
		}
		return temp;
	}

	public int berechneZubereitungsZeit() {
		return this.scheibenAnzahl;
	}
	
	public String toString() {
		return super.toString() + "\nScheiben: " + this.scheibenAnzahl + "  |  Dicke der Scheiben: "
				+ this.scheibenDicke;
	}

}
