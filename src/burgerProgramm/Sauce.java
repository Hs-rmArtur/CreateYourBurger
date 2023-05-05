package burgerProgramm;

public class Sauce extends Zutat {
	public final String SUESS = "sueß";
	public final String SCHARF = "scharf";
	public final String SALZIG = "salzig";
	
	protected int menge;
	protected String geschmack;

	public Sauce(String name, int nummer, float preis, boolean klassisch, String typ, int menge, String geschmack) {
		super(name, nummer, preis, klassisch, typ);
		this.menge = menge;
		this.geschmack = geschmack;
	}

	public Sauce(String name, int nummer, float preis, int menge, String geschmack) {
		this(name, nummer, preis, false, "vegetarisch", menge, geschmack);
	}

	public String zubereiten() {
		return this.name + " wird geschuettelt.";
	}
	
	public int berechneZubereitungsZeit() {
		return 0;
	}
	
	public double berechneHoehe() {
		return 0;
	}

	public String toString() {
		return super.toString() + "\nMenge: " + this.menge + "  |  Geschmack: " + this.geschmack;
	}

}
