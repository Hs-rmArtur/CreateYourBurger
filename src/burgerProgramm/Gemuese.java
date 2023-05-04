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
		return scheibenDicke;
	}

	public int zubereiten() {
		System.out.println(this.name + " wird gewaschen.");

		for (int i = 1; i <= this.scheibenAnzahl; i++) {
			System.out.println(i + ". Scheibe mit " + this.scheibenDicke + " mm schneiden.");
		}

		return this.scheibenAnzahl;
	}

	public String toString() {
		return super.toString() + "\nScheiben: " + this.scheibenAnzahl + "  |  Dicke der Scheiben: "
				+ this.scheibenDicke;
	}

}
