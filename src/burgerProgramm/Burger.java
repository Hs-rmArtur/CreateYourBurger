package burgerProgramm;

/**
 * Burger-Klasse für den in der Main erstellten Burger. 
 * Die Maximale Anzahl der Zutaten und alle Eigenschaften des Burgers werden hier basierend auf den Zutaten ermittelt.
 * 
 * @author Artur Konkel, Michael Karenko
 * @version 1.0
 * 
 */
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

	/**
	 * Konstruktor des Burgers.
	 * Zunächst wird ein leerer Burger erzeugt und die Eigenschaften werden je nach hinzugefügten Zutaten ermittelt.
	 * Zutaten werden nach und nach hinzugefügt.
	 * 
	 * @param name Der Name des Burgers
	 */
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
	
	/**
	 * Konstruktor, der den Burger mit einem broetchen initialisiert.
	 * @param name
	 * @param broetchen, aus dem der Burger bestehen soll.
	 */
	public Burger(String name, Broetchen broetchen) {
		this(name);
		fuegeZutatHinzu(broetchen);
		
	}

	/**
	 * Geht das Zutaten-Array durch unnd prüft, ob die Zutaten vegan sind. Sobald eine Zutat nicht vegan ist, ist der Burger nicht vegan.
	 * Dennoch Sinnvoll, da dies einfacher zu benutzen ist, falls Zutaten entfernt werden sollen.
	 * @return gibt aus ob der Burger vegan (true) ist oder nicht (false).
	 */
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

	/**
	 * Geht das Zutaten-Array durch unnd prüft, ob die Zutaten vegetarisch sind. Sobald eine Zutat nicht vegetarisch ist, ist der Burger nicht vegetarisch.
	 * Wird nicht verwendet, da die Zutat, die hinzugefügt wird, direkt geprüft wird.
	 * Dennoch Sinnvoll, da dies einfacher zu benutzen ist, falls Zutaten entfernt werden sollen.
	 * @return gibt aus ob der Burger vegetarisch (true) ist oder nicht (false).
	 */
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
	
	/**
	 * Geht das Zutaten-Array durch unnd prüft, ob die Zutaten klassisch sind. Sobald eine Zutat nicht klassisch ist, ist der Burger nicht klassisch.
	 * Dennoch Sinnvoll, da dies einfacher zu benutzen ist, falls Zutaten entfernt werden sollen.
	 * @return gibt aus ob der Burger klassisch (true) ist oder nicht (false).
	 */
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

	/**
	 * Geht das Zutaten-Array durch und prüft, ob die Zutat ein Saucen-Objekt ist.
	 * Speichert im Fall einer Sauce den Geschmack der Sauce als String und listet alle weiteren Geschmäcke auf, falls sie noch nicht enthalten sind.
	 * @return gibt die Kombination der Geschmäcke als String aus.
	 */
	public String ermittleGeschmack() {
		this.geschmack = "";
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] instanceof Sauce) {
				if(!(this.geschmack.contains(((Sauce)zutaten[i]).geschmack))) {
					this.geschmack += ((Sauce) zutaten[i]).geschmack + ", ";					
				}
			}
		}
		
		if(!(this.geschmack.equals(""))) {
			this.geschmack = geschmack.substring(0, geschmack.length() - 2);			
		}
		
		return this.geschmack;
	}

	/**
	 * Berechnet die Gesamthöhe des Burgers, indem es die Höhe der einzelnen Zutaten addiert
	 * @return Gibt die Gesamthöhe in cm (auf zwei Nachkommastellen gerundet) wieder.
	 */
	public double berechneHoehe() {
		this.hoehe = 0.0;
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				this.hoehe += zutaten[i].berechneHoehe();
			}
		}
		return rundeAufZweiNachkomma(this.hoehe / 10);
	}

	/**
	 * Rundet den mitgegebenen Double auf zwei Nachkommastellen.
	 * @param number Beliebiger Double, der auf zwei Nachkommastellen gerundet werden soll.
	 * @return Gibt die gerundete Zahl zurück.
	 */
	public double rundeAufZweiNachkomma(double number) {
		return Math.round(number * 100.0) / 100.0;
	}
	
	/**
	 * Berechnet den Gesamtpreis, indem der Preis der einzelnen Zutaten aufaddiert werden.
	 * @return Gibt den Gesamtpreis mit zwei Nachkommastellen in Euro zurück.
	 */
	public double berechnePreis() {
		this.preis = 0.0;
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				this.preis += zutaten[i].preis;
			}
		}
		return rundeAufZweiNachkomma(this.preis);
	}

	/**
	 * Bestimmt die längste Zubereitungszeit der erstellten Burger, da alle Burger parallel zubereitet werden.
	 * @return Gibt die längste Zubereitungszeit der einzelnen Burger in Sekunden wieder.
	 */
	public int berechneZubereitungszeit() {
		this.zubereitungsZeit = 0;
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				this.zubereitungsZeit += this.zutaten[i].berechneZubereitungsZeit();
			}
		}
		return this.zubereitungsZeit;
	}

	/**
	 * Prüft, ob die Zutat klassisch ist. Falls nciht, ist der Burger nicht mehr klassisch.
	 * @param zutat Die Zutat, die dem Burger hinzugefügt werden soll.
	 */
	private void pruefeObZutatKlassisch(Zutat zutat) {
		if (!zutat.klassisch) {
			this.klassisch = false;
		}
	}

	/**
	 * Prüft, ob die Zutat vegan ist. Falls nicht wird geprüft ob sie vegetarisch ist. 
	 * Setzt entsprechend den Burger als Vegan, Vegetarisch oder nichts.
	 * @param zutat Die Zutat, die dem Burger hinzugefügt werden soll.
	 */
	private void pruefeTypVonZutat(Zutat zutat) {
		if (!zutat.vegan) {
			this.vegan = false;
		} else if (!zutat.vegetarisch) {
			this.vegetarisch = false;
		}
	}
	
	/**
	 * Prüft ob die Zutat Vegan/Vegetarisch und ob sie Klassisch ist und fügt sie an die nächste freie Stelle im Burger-Zutaten-Array zu.
	 * @param zutat Die Zutat, die dem Burger hinzugefügt werden soll.
	 * @return Gibt zurück, ob die Zutat hinzugefügt wurde oder nicht (Voll).
	 */
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

	/**
	 * Methode um Zutaten aus dem Burger-Zutaten-Array zu entfernen.
	 * Wird momentan nicht verwendet.
	 * @param zutat Die Zutat, die aus dem Burger entfernt werden soll.
	 * @return Gibt zurück, ob die Zutat erfolgreich entfernt wurde.
	 */
	public boolean entferneZutat(Zutat zutat) {
		// Zutat wird entfernt
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] == zutat) {
				zutaten[i] = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * Listet die einzelnen Zutaten aus dem Array auf, damit Sie später geprintet werden können.
	 * @return Gibt einen String aus, der die Zutaten des Burgers auflistet.
	 */
	public String gebeZutatenAus() {
		String output = "";
		for (int i = 0; i < zutaten.length; i++) {
			if (zutaten[i] != null) {
				output += "    " + zutaten[i].toString() + "\n";
			}
		}
		return output;
	}

	/**
	 * Printet das Rezept eines einzelnen Burgers und die damit verbundenen Zubereitungsschritte.
	 */
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
	
	/**
	 * Getter-Methode für den Namen des Burgers
	 * @return Name des Burgers
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * To-String-Methode für die wichtigsten Eigenschaften des Burgers.
	 * Es wird folgendes Ausgegeben: "Name des Burgers" ('Gesamthöhe' cm, klassisch, "Geschmack", vegan/vegetatisch) - 'Gesamtpreis' Euro
	 * Klassisch und vegan/vegetarisch werden weggelassen falls es nicht zutrifft.
	 * Geschmack wird hintereinander aufgelistet oder weggelassen, je nachdem, welche Saucen gewählt wurden.
	 */
	public String toString() {
		String temp;
		String strString = "";
		
		strString = String.format("%.2f", this.berechnePreis());
		
		temp = this.name + " (" + this.berechneHoehe() + " cm";

		if (this.klassisch) {
			temp += ", klassisch";
		}
		
		if(ermittleGeschmack().length() > 0) {
			temp+= ", " + this.geschmack;
		}
		
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
