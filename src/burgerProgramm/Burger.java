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
		this.klassisch = false;
		this.hoehe = 0.0;
		this.preis = 0.0;
		this.zubereitungsZeit = 0;
	}

	private boolean checkObVegan() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].vegan) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkObVegetarisch() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].vegetarisch) {
					return false;
				}
			}
		}
		return true;

	}

	/*
	private void berechneHoehe() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				hoehe += zutaten[i].berechneHoehe();
			}
		}
	}

	private void berechnePreis() {
		for (int i = 0; i < zutaten.length; i++) {
			if(zutaten[i] != null) {
				zutaten[]
			}
		}
	}
	*/

	private int berechneZubereitungszeit() {
		return 0;
	}

	private void checkObKlassisch() {

	}

	public void fuegeZutatHinzu(Zutat zutat) {
		// Zutat wird hinzugefügt
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] == null) {
				zutaten[i] = zutat;
				break;
			}
		}
		
		//dynamische Berechnung der Hoehe und Preises, sowie vegan oder nicht.
		this.hoehe += zutat.berechneHoehe();
		this.preis += zutat.preis;
		
		//erstmal nicht schön, da bereits geprüfte Zutaten erneut geprüft werden.
		this.vegan = checkObVegan();
		this.vegetarisch = checkObVegetarisch();
	}

	public void entferneZutat(Zutat zutat) {

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
