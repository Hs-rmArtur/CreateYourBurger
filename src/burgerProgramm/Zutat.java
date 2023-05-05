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
		
		System.out.println(this.vegetarisch);
	}

	public Zutat(String name, int nummer, float preis, String typ) {
		this(name, nummer, preis, false, typ);
	}

	public Zutat(String name, int nummer, float preis) {
		this(name, nummer, preis, VEGETARISCH);
	}

	public abstract int zubereiten();
	
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
