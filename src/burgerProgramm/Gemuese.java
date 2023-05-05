package burgerProgramm;

public class Gemuese extends Zutat {
	protected int scheibenAnzahl;
	protected int scheibenDicke;

	public Gemuese(String name, int nummer, double preis, boolean klassisch, int scheibenAnzahl,
			int scheibenDicke) {
		super(name, nummer, preis, klassisch, VEGAN);
		this.scheibenAnzahl = scheibenAnzahl;
		this.scheibenDicke = scheibenDicke;
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
