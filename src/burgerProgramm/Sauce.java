package burgerProgramm;

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

	public String zubereiten() {
		return this.name + " wird geschuettelt.";
	}
	
	public int berechneZubereitungsZeit() {
		return 0;
	}
	
	public double berechneHoehe() {
		return 0;
	}

}
