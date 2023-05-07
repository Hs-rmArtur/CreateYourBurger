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
		return (scheibenDicke * scheibenAnzahl);
	}

	public String zubereiten() {
		String temp = this.name + " wird gewaschen.\n";
	
		for (int i = 1; i <= this.scheibenAnzahl; i++) {
			temp += "\t" + i + ". Scheibe mit " + this.scheibenDicke + " mm schneiden.";
			if(i < this.scheibenAnzahl) {
				temp += "\n";
			}
		}
		return temp;
	}

	public int berechneZubereitungsZeit() {
		return this.scheibenAnzahl;
	}

}
