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

	public boolean checkObVegan() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].vegan) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkObVegetarisch() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].vegetarisch) {
					return false;
				}
			}
		}
		return true;

	}

	public boolean checkObKlassisch() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				if (!zutaten[i].klassisch) {
					return false;
				}
			}
		}
		return true;
	}
	
	public String checkGeschmack() {
		for(int i = 0; i < zutaten.length; i++) {
			if(zutaten[i] instanceof Sauce) {
				this.geschmack = ((Sauce)zutaten[i]).geschmack;
			}
		}
		
		return this.geschmack;
	}
	
	public double berechneHoehe() {
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				this.hoehe += zutaten[i].berechneHoehe();
			}
		}
		return this.hoehe;
	}

	public double berechnePreis() {
		for (int i = 0; i < zutaten.length; i++) {
			if(zutaten[i] != null) {
				this.preis += zutaten[i].preis;
			}
		}
		return this.preis;
	}
	

	public int berechneZubereitungszeit() {
		for (int i = 0; i < zutaten.length; i++) {
			if(zutaten[i] != null) {
				this.zubereitungsZeit += zutaten[i].berechneZubereitungsZeit();
			}
		}
		return this.zubereitungsZeit;
	}


	public void fuegeZutatHinzu(Zutat zutat) {
		// Zutat wird hinzugefügt
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] == null) {
				zutaten[i] = zutat;
				break;
			}
		}
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
	
	public void zeigeRezept() {
		System.out.println("Rezept - " + this.toString());
		
		System.out.print("Zutaten: ");
		for(int i = 0; i < zutaten.length - 1; i++) {
			System.out.print(zutaten[i].name + ", ");
		}
		System.out.print(zutaten[zutaten.length - 1].name + "\n");
		System.out.println();
		
		System.out.println("Und so gehts:");
		for(int i = 0; i < zutaten.length; i++) {
			System.out.println(('a' + i) + " - " + zutaten[i].zubereiten());
		}
		
	}
	

	public String toString() {
		String temp;
	
		
		temp = this.name + " (" + (this.berechneHoehe()/100.0) + " cm";
		
		if(this.klassisch) {
			temp += ", klassisch";
		} 
		if(this.vegan) {
			temp += ", vegan";
		}
		if(this.vegetarisch && this.vegan == false) {
			temp += ", vegetarisch";
		}
		if(!this.geschmack.equalsIgnoreCase("")) {
			temp += ", " + this.geschmack;
		}
		
		temp += ") - " + this.berechnePreis() + " €";
		
		return temp;
	}

}
