package burgerProgramm;

public abstract class Zutat {
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

		if (typ.equalsIgnoreCase("vegetarisch")) {
			this.vegetarisch = true;
		} else if (typ.equalsIgnoreCase("vegan")) {
			this.vegan = true;
		}
	}

	public Zutat(String name, int nummer, float preis, String typ) {
		this(name, nummer, preis, false, typ);
	}

	public Zutat(String name, int nummer, float preis) {
		this(name, nummer, preis, "vegetarisch");
	}

	public abstract String zubereiten();
	
	public abstract int berechneZubereitungsZeit();
	
	public abstract double berechneHoehe();

	public String toString() {
		String klassisch;
		String typ = "";

		if (this.klassisch) {
			klassisch = "Ja";
		} else {
			klassisch = "Nein";
		}

		if (this.vegan) {
			typ = "vegan";
		} else if (this.vegetarisch) {
			typ = "vegetarisch";
		}

		return "Name: " + this.name + "  |  Nummer: " + this.nummer + "  |  Preis: " + this.preis + "\nKlassisch: "
				+ klassisch + "   " + typ;
	}

}
