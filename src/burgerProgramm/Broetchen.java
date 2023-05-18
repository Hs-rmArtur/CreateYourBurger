package burgerProgramm;

/**
 * Beschreibt ein Brötchen für Burger.
 * @author Artur Konkel, Michael Karenko
 *
 */
public class Broetchen extends Zutat {
	private int backzeit; //in Sekunden
	private int hoehe;

	/**
	 * Standard Konstruktor zur Erstellung eines Broetchens.
	 * @param name
	 * @param nummer
	 * @param preis
	 * @param klassisch
	 * @param typ
	 * @param backzeit
	 * @param hoehe
	 */
	public Broetchen(String name, int nummer, double preis, boolean klassisch, String typ, int backzeit, int hoehe) {
		super(name, nummer, preis, klassisch, typ);
		this.backzeit = backzeit;
		this.hoehe = hoehe;

	}

	/**
	 * Ausgabe, wie das Broetchen zubereitet werden soll.
	 */
	public String zubereiten() {
		if(backzeit % 60 == 0) {
			return this.name + " " + (backzeit / 60) + " Minuten und " + (backzeit % 60) + " Sekunden rösten und aufschneiden.";			
		} else {
			return this.name + " " + (backzeit / 60) + " Minuten rösten und aufschneiden.";
		}
	}
	
	
	/**
	 * Berechnung der Zubereitungszeit.
	 */
	public int berechneZubereitungsZeit() {
		//+1, da Aufschneiden eine Sekunde dauert.
				return this.backzeit + 1;
	}

	/**
	 * Berechnung der Hoehe des Brötchens, welches sich beim Backen ändert.
	 */
	public double berechneHoehe() { 
		double hoeheProEinheit;
		double temp = this.hoehe;

		for (int i = 0; i < (backzeit / 60); i++) {
			hoeheProEinheit = (temp / 100) * 2.5;
			temp += hoeheProEinheit;
		}
		
			for(int i = 0; i < (backzeit % 60); i++) {
				hoeheProEinheit = ((temp / 100) * 2.5) / 60;
				temp += hoeheProEinheit;
		}
		
		return (Math.round(temp*10)/10.0);
	}

}
