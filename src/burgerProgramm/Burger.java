package burgerProgramm;

public class Burger {
	private static final int MAX_ZUTATEN = 9;

	private String name;
	private Zutat[] zutaten;
	private boolean vegan;
	private boolean vegetarisch;
	private boolean klassisch;
	private String geschmack;
	private int zubereitungsZeit;
	private double hoehe;
	private double preis;

	// Es wird zuenächst ein leeres Objekt "Burger" erstellt. Die Zutaten kommen
	// nach und nach hinzu.
	public Burger(String name) {
		this.zutaten = new Zutat[MAX_ZUTATEN];
		this.name = name;
		this.vegan = true;
		this.vegetarisch = true;
		this.klassisch = true;
		this.hoehe = 0.0;
		this.preis = 0.0;
		this.zubereitungsZeit = 0;
		this.geschmack = "";
	}

	public boolean pruefObVegan() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].vegan) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean pruefObVegetarisch() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].vegetarisch) {
					return false;
				}
			}
		}
		return true;

	}

	public boolean pruefObKlassisch() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].klassisch) {
					return false;
				}
			}
		}
		return true;
	}

	public String ermittleGeschmack() {
		this.geschmack = "";
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] instanceof Sauce) {
				this.geschmack += ((Sauce) zutaten[i]).geschmack + ", ";
			}
		}
		this.geschmack = geschmack.substring(0, geschmack.length() - 2);
		
		return this.geschmack;
	}

	public double berechneHoehe() {
		this.hoehe = 0.0;
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				this.hoehe += zutaten[i].berechneHoehe();
			}
		}
		return rundeAufZweiNachkomma(this.hoehe / 10);
	}

	public double rundeAufZweiNachkomma(double number) {
		return Math.round(number * 100.0) / 100.0;
	}
	
	public double berechnePreis() {
		this.preis = 0.0;
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				this.preis += zutaten[i].preis;
			}
		}
		return rundeAufZweiNachkomma(this.preis);
	}

	public int berechneZubereitungszeit() {
		this.zubereitungsZeit = 0;
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				this.zubereitungsZeit += this.zutaten[i].berechneZubereitungsZeit();
			}
		}
		return this.zubereitungsZeit;
	}

	private void pruefeObZutatKlassisch(Zutat zutat) {
		if (!zutat.klassisch) {
			this.klassisch = false;
		}
	}

	private void pruefeTypVonZutat(Zutat zutat) {
		if (!zutat.vegan) {
			this.vegan = false;
		} else if (!zutat.vegetarisch) {
			this.vegetarisch = false;
		}
	}

	public boolean fuegeZutatHinzu(Zutat zutat) {
		pruefeTypVonZutat(zutat);
		pruefeObZutatKlassisch(zutat);
		
		// Zutat wird hinzugefügt
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] == null) {
				zutaten[i] = zutat;
				return true;
			}
		}
		return false;
	}

	public void entferneZutat(Zutat zutat) {
		// Zutat wird entfernt
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] == zutat) {
				zutaten[i] = null;
				break;
			}
		}

	}

	public String gebeZutatenAus() {
		String output = "";
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				output += "    " + zutaten[i].toString() + "\n";
			}
		}
		return output;
	}

	public void zeigeRezept() {
		System.out.println("Rezept - " + this.toString());

		System.out.print("Zutaten: ");
		for (int i = 0; i < zutaten.length - 1; i++) {
			if (zutaten[i] != null) {
				System.out.print(zutaten[i].name + ", ");
			}
		}
		System.out.println();

		System.out.println("Und so gehts:");
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				System.out.println(((char) ('a' + i)) + " - " + zutaten[i].zubereiten());
			}
		}

	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		String temp;
		String strString = "";
		
		strString = String.format("%.2f", this.berechnePreis());
		
		temp = this.name + " (" + this.berechneHoehe() + " cm";

		if (this.klassisch) {
			temp += ", klassisch";
		}
		
		temp+= ", " + ermittleGeschmack();
		
		if (this.vegan) {
			temp += ", vegan";
		}
		if (this.vegetarisch && this.vegan == false) {
			temp += ", vegetarisch";
		}

		temp += ") - " + strString + " Euro";

		return temp;
	}

}
