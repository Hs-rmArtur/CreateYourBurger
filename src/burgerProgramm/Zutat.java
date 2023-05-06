package burgerProgramm;

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

	public Zutat(String name, int nummer, double preis, boolean klassisch, String typ) {
		this.name = name;
		this.nummer = nummer;
		this.preis = preis;
		this.klassisch = klassisch;

		if (typ.equalsIgnoreCase(VEGETARISCH)) {
			this.vegetarisch = true;
		} else if (typ.equalsIgnoreCase(VEGAN)) {
			this.vegan = true;
		}

	}

	public Zutat(String name, int nummer, float preis, String typ) {
		this(name, nummer, preis, false, typ);
	}

	public Zutat(String name, int nummer, float preis) {
		this(name, nummer, preis, VEGETARISCH);
	}

	public int getNummer() {
		return this.nummer;
	}

	public abstract String zubereiten();

	public abstract int berechneZubereitungsZeit();

	public abstract double berechneHoehe();

	public String toString() {
		String klassisch;
		String typ = "";

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

		return "Nr: " + this.nummer + ", " + this.name + ", Preis: " + this.preis + klassisch + typ;
	}

}
