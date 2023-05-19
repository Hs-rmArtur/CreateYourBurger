package burgerProgramm;

public class Kaese extends Zutat {

	public static final String TYP_KUHMILCH = "Kuhmilch";
	public static final String TYP_SCHAFSMILCH = "Schafsmilch";
	public static final String TYP_ZIEGENMILCH = "Ziegenmilch";

	private int zubereitungsZeit;
	private String milchTyp;

	public Kaese(String name, int nummer, double preis, boolean klassisch, String typ, int zubereitungsZeit,
			String milchTyp) {
		super(name, nummer, preis, klassisch, typ);
		this.zubereitungsZeit = zubereitungsZeit;
		this.milchTyp = milchTyp;
	}

	@Override
	public double berechneHoehe() {
		return 0;
	}

	@Override
	public int berechneZubereitungsZeit() {
		return zubereitungsZeit;
	}

	@Override
	public String zubereiten() {

		return name + " auf Patty legen und " + zubereitungsZeit + " Sekunden schmelzen.";
	}

	@Override
	public String toString() {
		return super.toString() + ", " + milchTyp;
	}
}
