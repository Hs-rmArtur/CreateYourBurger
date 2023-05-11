package burgerProgramm;

import de.hsrm.mi.prog.util.StaticScanner;
/**
 * Ein Programm welches dem User ermöglicht eigene Kreationen von Burgern zu erstellen.
 * Am Ende erhält er die Burger zubereitet und außerdem das Rezept dazu.
 * @author artur konkel, michael karenko
 *
 */
public class Main {
	private final static String BEFEHL_OK = "ok";
	private final static String BEFEHL_MENU = "menu";
	private final static String BEFEHL_NEUER_BURGER = "neuer";
	private final static String BEFEHL_MEINE_BURGER = "meine";
	private final static String BEFEHL_BESTELLEN = "bestellen";
	private final static String BEFEHL_ZEIGE_BEFEHLE = "befehle";
	private final static String BEFEHL_ABBRECHEN = "abbrechen";

	private final static int INDEX_HAUPTBEFEHL = 0;
	private final static int INDEX_BURGERNAME = 2;
	private final static int MAX_ANZ_BURGER = 10;
	private final static Zutat[][] ZUTATEN = generiereZutaten();

	private static Burger[] burgerBestellungen = new Burger[MAX_ANZ_BURGER];

	public static void main(String[] args) {
		String eingabe = "";
		String[] befehle;
		Burger aktuellerBurger = null;

		druckeWillkommensText();
		druckeAnleitung(false);
		
		do {
			eingabe = StaticScanner.nextString();
			befehle = bearbeiteBefehle(eingabe);
			System.out.println();

			if (fuehreAllgemeineBefehleAus(befehle, false) == false) {
				switch (befehle[INDEX_HAUPTBEFEHL]) {
				case BEFEHL_NEUER_BURGER:
					
					//Pruefe, ob dritte Stelle im Befehl vorhanden ist
					if (befehle.length > 2) {
						aktuellerBurger = erstelleBurger(befehle[INDEX_BURGERNAME]);
						
						if(aktuellerBurger != null) {
							belegeBurger(aktuellerBurger);							
						}
						
						System.out.println("Bitte deine Eingabe:");
					} else {
						System.out.println("Du musst deinem Burger einen Namen geben!");
					}
					
					break;
				case BEFEHL_BESTELLEN:
					bestellungAbgeben();
					
					break;
				case BEFEHL_ABBRECHEN:
					System.out.println("Dein Bestellvorgang wurde abgebrochen.");
					
					break;
				default:
					System.out.println("Deine Eingabe war nicht korrekt. Versuche es gerne erneut.");
				}
			}

		} while (!befehle[INDEX_HAUPTBEFEHL].equals(BEFEHL_BESTELLEN)
				&& !befehle[INDEX_HAUPTBEFEHL].equals(BEFEHL_ABBRECHEN));
	}

	/**
	 * Druckt die Bestellungen, dir bereits erstellt wurden.
	 */
	public static void zeigeAktBestellungen() {
		System.out.println("Deine aktuellen Bestellungen: ");
		for (int i = 0; i < burgerBestellungen.length; i++) {
			if (burgerBestellungen[i] != null) {
				System.out.println(burgerBestellungen[i].toString());
				System.out.println(burgerBestellungen[i].gebeZutatenAus());
			}
		}
		System.out.println();
		System.out.println("Bitte deine Eingabe: ");
	}

	/**
	 * Berechnet die Zubereitungszeit und den Gesamtpreis aller Bestellungen und druckt diese aus.
	 */
	public static void bestellungAbgeben() {
		int zubereitungsZeit = 0;
		int tempZeit = 0;
		double gesamtPreis = 0;
		String strGesamtpreis;

		for (int i = 0; i < burgerBestellungen.length; i++) {
			if (burgerBestellungen[i] != null) {
				burgerBestellungen[i].zeigeRezept();
				tempZeit = burgerBestellungen[i].berechneZubereitungszeit();

				//ermittle die höchste Zubereitungszeit der Burger
				if (tempZeit > zubereitungsZeit) {
					zubereitungsZeit = tempZeit;
				}

				gesamtPreis += burgerBestellungen[i].berechnePreis();
				System.out.println();
			}
		}
		strGesamtpreis = String.format("%.2f", gesamtPreis);
		
		System.out.println();
		System.out.println("Gesamtzubereitungszeit: " + (zubereitungsZeit / 60) + " Minuten und "
				+ (zubereitungsZeit % 60) + " Sekunden");
		System.out.println("Gesamtpreis: " + strGesamtpreis + " Euro\n");
	}
	
	/**
	 * Bearbeitet die Eingabe des Users zur weiteren Verarbeitung vor.
	 * @param befehle Zeichenkette der Usereingabe
	 * @return Array mit einzelnen Worten
	 */
	public static String[] bearbeiteBefehle(String befehle) {
		befehle = befehle.toLowerCase();
		return befehle.split(" ");
	}

	/**
	 * Prueft, ob die maximale Anzahl an erlaubten gleichzeitigen Bestellungen erlaubt ist.
	 * @return boolean, ob max Anzahl erreicht oder nicht.
	 */
	public static boolean pruefeObBestellungenMaxErreicht() {
		int zaehler = 0;
		for (int i = 0; i < burgerBestellungen.length; i++) {
			if (burgerBestellungen[i] != null) {
				zaehler++;
			}
		}

		if (zaehler == burgerBestellungen.length) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Überprüfung, ob der eingegebene Befehl vom Typ OK ist.
	 * @param befehl Befehl, der überprüft werden soll.
	 * @return boolean
	 */
	public static boolean pruefeObBefehl_BEFEHL_OK(String befehl) {
		if(befehl.equals(BEFEHL_OK)){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Hauptlogik zum Abfragen der Zutaten, die auf den Burger gelegt werden sollen.
	 * @param burger, der belegt werden soll.
	 */
	public static void belegeBurger(Burger burger) {
		String eingabe = "";
		String befehle[];
		Zutat aktuelleZutat = null;
		int zutatenNr;
		int zaehlerZutaten = 1;
		boolean zutatHinzugefuegt = true;
		boolean istAllgemeinerBefehl = false;
		boolean istBefehlOK = false;
		
		System.out.println();
		System.out.println("Mit was moechtest du deinen Burger belegen? ");
		System.out.println("Mit '" + BEFEHL_OK + "' kannst du deine Zusammenstellung abschließen.");

		do {
			// Prozedur zur Abfragung der Zutaten
			do {
				System.out.println("Bitte gib die " + zaehlerZutaten + ". Zutat an:");
				eingabe = StaticScanner.nextString();
				befehle = bearbeiteBefehle(eingabe);
				istBefehlOK = pruefeObBefehl_BEFEHL_OK(befehle[INDEX_HAUPTBEFEHL]);
				
				istAllgemeinerBefehl = fuehreAllgemeineBefehleAus(befehle, true);
				
				if (!istBefehlOK && istAllgemeinerBefehl == false) {
					zutatenNr = pruefeObIntInBefehl(befehle);
					aktuelleZutat = findeZutat(zutatenNr);
					if (aktuelleZutat == null) {
						System.out.println("Die Zutat haben wir leider nicht. Versuch es doch mit einer anderen!");
						System.out.println(
								"Mit dem Befehl '" + BEFEHL_MENU + "' kannst du dir die Zutaten anzeigen lassen.");
					} else if (aktuelleZutat instanceof Broetchen) {
						System.out.println(
								"Dein Burger kann nur aus einem Broetchen bestehen! Belege es mit etwas anderem.");
					}
				}

			} while ((aktuelleZutat == null || aktuelleZutat instanceof Broetchen || istAllgemeinerBefehl == true) && !istBefehlOK);

			if (!istBefehlOK) {
				zutatHinzugefuegt = burger.fuegeZutatHinzu(aktuelleZutat);
			}

			if (!zutatHinzugefuegt) {
				System.out.println("Maximale Anzahl an Zutaten erreicht!");
			} else if (!istBefehlOK) {
				zaehlerZutaten++;
				druckeZutatNachBelegen(aktuelleZutat);
				
			}
		} while (!istBefehlOK && zutatHinzugefuegt);

		System.out.println("Dein Burger '" + burger.getName() + "' wird der Bestellung hinzugefuegt.");

	}
	
	/**
	 * Drucken der Zutatnummer und dessen Preis nach dem Belegen.
	 * @param zutat Die Zutat die hunzugefügt wird
	 */
	public static void druckeZutatNachBelegen(Zutat zutat) {
		String strZutatPreis = "";
		
		strZutatPreis = String.format("%.2f", zutat.preis);

		System.out.println("> Zutat " + zutat.nummer);
		System.out.println(zutat.name + " - " + strZutatPreis + " Euro");
	}

	/**
	 * Erstellt einen neuen leeren Burger und prüft ob die maximale Anzahl erreicht ist. Initialisiert den Burger mit der entsprechenden Methode
	 * @param name Name des zu erstellenden Burgers
	 * @return Der neu erstellte Burger wird zurückgegeben
	 */
	public static Burger erstelleBurger(String name) {
		Burger burger = null;

		if (!pruefeObBestellungenMaxErreicht()) {
			for (int i = 0; i < burgerBestellungen.length; i++) {
				if (burgerBestellungen[i] == null) {
					burger = initialisiereBurgerMitBroetchen(name);
					burgerBestellungen[i] = burger;
					break;
				}
			}

			return burger;
		} else {
			System.out.println("Du hast deine maximale Anzahl an Burgern erstellt. Zeit diese zu bestellen!");
			return null;
		}

	}

	/**
	 * Erstellung des Burgers mit Userabfrage, mit welchem Broetchen dieser initialisiert werden soll.
	 * @param name, den der Burger haben soll.
	 */
	public static Burger initialisiereBurgerMitBroetchen(String name) {
		Zutat zutat = null;
		int zutatenNr = 0;
		String eingabe = "";
		String[] befehle;
		boolean allgemeinerBefehlAusgefuehrt = false;

		do {
			System.out.println("Aus welchem Broetchen soll dein Burger bestehen? ");
			eingabe = StaticScanner.nextString();
			befehle = bearbeiteBefehle(eingabe);
			allgemeinerBefehlAusgefuehrt = fuehreAllgemeineBefehleAus(befehle, true);

			if (allgemeinerBefehlAusgefuehrt == false) {
				zutatenNr = pruefeObIntInBefehl(befehle);
				zutat = findeZutat(zutatenNr);
				if (!(zutat instanceof Broetchen)) {
					System.out.println("Du musst zunächst ein Broetchen waehlen, um deinen Burger belegen zu koennen.");
				}
			}
		} while (!(zutat instanceof Broetchen) || allgemeinerBefehlAusgefuehrt);

		System.out.println(zutat.toString());
		System.out.println("Dein Burger ist bereit belegt zu werden!");
		
		return new Burger(name, (Broetchen) zutat);
	}
	
		
	/**
	 * Ausgabe der Zutat, die anhand der Nummer ermittelt wird.
	 * @param zutatenNummer der Zutat
	 * @return gefundene Zutat oder null
	 */
	public static Zutat findeZutat(int zutatenNummer) {
		int[] index = splitZahl(zutatenNummer);
		
		if(index != null) {
			if(index[0] >= 0 && index[0] < ZUTATEN.length && index[1] < ZUTATEN[index[0]].length) {
				return ZUTATEN[index[0]][index[1]];
			}
		}
			return  null;
	}
	
	public static int[] splitZahl(int zutatenNummer) {
		int[] split = new int[2];
		
		if(zutatenNummer < 100) {
			 for(int i = split.length - 1; i >= 0; i--) {
				 split[i] = zutatenNummer % 10;
				 
				 zutatenNummer = zutatenNummer / 10;
			 }
		 split[0] -= 1;
		return split;
		}
			return null;
	}
	
	/**
	 * Druckt den Willkommenstext beim starten des Programms
	 */
	public static void druckeWillkommensText() {
		System.out.println("Bock auf deine eigene Burgerkreation?");
		System.out.println("Dann bist du bei uns genau richtig!");
		System.out.println(
				"Stelle deinen individuellen eigenen Burger zusammen und du erhälst dein ganz persoenliches Rezept, sowie den Burger direkt von uns zubereitet!");
		System.out.println();
	}

	/**
	 * druckt die Befehle, die eingegeben werden können.
	 * @param imBelegProzess fragt ab, ob die Anleitung im Belegprozess aufgerufen wird oder nicht.
	 */
	public static void druckeAnleitung(boolean imBelegProzess) {
		System.out.println("Wie es funktioniert:");
		System.out.println(
				"Mit '" + BEFEHL_MENU + "' kannst du dir alle zur Verfügung stehenden Zutaten anzeigen lassen.");
		
		if (!imBelegProzess) {
			System.out.println(
					"Mit '" + BEFEHL_NEUER_BURGER + " Burger <Name>' beginnst du die Kreation deines Burgers.");
			
		}
		
		if (imBelegProzess) {
			System.out.println(
					"Mit '<Zutatennummer>' fuegst du im Kreationsprozess deinem Burger die Zutat mit der entspraechenden Nummer hinzu.");			
		}

		System.out.println(
				"Mit '" + BEFEHL_MEINE_BURGER + " Burger' gibst du dir alle bereits erstellten Kreationen aus.");

		if(!imBelegProzess) {
			System.out.println("Mit '" + BEFEHL_BESTELLEN
					+ "' schließt du deine Kreationen ab und wir machen uns an die Arbeit, um deine Burger fertig zu stellen.");
			System.out.println("Mit '" + BEFEHL_ABBRECHEN + "' brichst du deine gesamte Bestellung ab.");
		}
	
		System.out.println("Mit '" + BEFEHL_ZEIGE_BEFEHLE + "' lässt du dir diese Befehlsliste erneut ausgeben.");

		System.out.println();
		System.out.println("Bitte deine Eingabe:");
	}

	/**
	 * Suche eines Integers im eingegeben Befehl. Gleichzeitig wird so vermieden, dass es sich 
	 * nicht um einen falschen Eingabetyp handelt.
	 * @param befehle Array mit Befehlen
	 * @return geparste nummer, die im Befehl gefunden wurde.
	 */
	public static int pruefeObIntInBefehl(String[] befehle) {
		int nummer = 0;

		//Da wir noch kein "try catch" gelertn haben, wird so der Error abgefangen, falls der String nicht in ein Int
		// geparst werde kann.
		for (int i = 0; i < befehle.length; i++) {
			if ((befehle[i].contains("1") || befehle[i].contains("2") || befehle[i].contains("3")
					|| befehle[i].contains("4") || befehle[i].contains("5") || befehle[i].contains("6")
					|| befehle[i].contains("7") || befehle[i].contains("8") || befehle[i].contains("9")
					|| befehle[i].contains("0")) 
					&& (!(befehle[i].contains("a")) && !(befehle[i].contains("b")) && !(befehle[i].contains("c")) 
					&& !(befehle[i].contains("d")) && !(befehle[i].contains("e")) && !(befehle[i].contains("f")) 
					&& !(befehle[i].contains("g")) && !(befehle[i].contains("h")) && !(befehle[i].contains("i")) 
					&& !(befehle[i].contains("j")) && !(befehle[i].contains("k")) && !(befehle[i].contains("l")) 
					&& !(befehle[i].contains("m")) && !(befehle[i].contains("n")) && !(befehle[i].contains("o")) 
					&& !(befehle[i].contains("p")) && !(befehle[i].contains("q")) && !(befehle[i].contains("r")) 
					&& !(befehle[i].contains("s")) && !(befehle[i].contains("t")) && !(befehle[i].contains("u")) 
					&& !(befehle[i].contains("v")) && !(befehle[i].contains("w")) && !(befehle[i].contains("x")) 
					&& !(befehle[i].contains("y")) && !(befehle[i].contains("z")) && !(befehle[i].contains("µ"))
					&& !(befehle[i].contains("!")) && !(befehle[i].contains("§")) && !(befehle[i].contains("$"))
					&& !(befehle[i].contains("%")) && !(befehle[i].contains("&")) && !(befehle[i].contains("/"))
					&& !(befehle[i].contains("(")) && !(befehle[i].contains(")")) && !(befehle[i].contains("="))
					&& !(befehle[i].contains("?")) && !(befehle[i].contains("`")) && !(befehle[i].contains("+"))
					&& !(befehle[i].contains("*")) && !(befehle[i].contains("#")) && !(befehle[i].contains("'"))
					&& !(befehle[i].contains("_")) && !(befehle[i].contains("-")) && !(befehle[i].contains("."))
					&& !(befehle[i].contains(":")) && !(befehle[i].contains(";")) && !(befehle[i].contains(","))
					&& !(befehle[i].contains("<")) && !(befehle[i].contains(">")) && !(befehle[i].contains("^"))
					&& !(befehle[i].contains("°")) && !(befehle[i].contains("²")) && !(befehle[i].contains("³"))
					&& !(befehle[i].contains("{")) && !(befehle[i].contains("[")) && !(befehle[i].contains("]"))
					&& !(befehle[i].contains("}")) && !(befehle[i].contains("ß")) && !(befehle[i].contains("\\"))
					&& !(befehle[i].contains("\"")) && !(befehle[i].contains("~")) && !(befehle[i].contains("@"))
					&& !(befehle[i].contains("€")) && !(befehle[i].contains("´")) && !(befehle[i].contains("|")))) {
				nummer = Integer.parseInt(befehle[i]);
			}
		}

		return nummer;
	}

	/**
	 * Allgemeine Befehle, die beim Belegen, sowie als auch im Startmenü ausgeführt werden können.
	 * @param befehle Der geteilte String der Eingabe
	 * @param imBelegProzess, prueft ob methode beim Belegen ausgeführt wird oder nicht.
	 * @return boolean, ob einer der allgemeinen Befehle ausgeführt wurde.
	 */
	public static boolean fuehreAllgemeineBefehleAus(String[] befehle, boolean imBelegProzess) {
		switch (befehle[INDEX_HAUPTBEFEHL]) {
		case BEFEHL_MENU:
			druckeMenu();
			return true;
		case BEFEHL_MEINE_BURGER:
			zeigeAktBestellungen();
			return true;
		case BEFEHL_ZEIGE_BEFEHLE:
			druckeAnleitung(imBelegProzess);
			return true;
		default:
			return false;
		}
	}

	/**
	 * druckt das Menü, welches die möglichen Zutaten enthält.
	 */
	public static void druckeMenu() {
		/*
		 * Wurde so implementiert, um Text zwischen den Zutatenkategorien zu
		 * ermöglichen. Vorteil ist ebenfalls, dass die Reihenfolge der Zutaten in der
		 * Liste nicht relevant ist. Perfomance technisch nicht schoen, aber da die
		 * Anzahl von Zutaten nicht so groß ist, noch in Ordnung.
		 */

		System.out.println("Broetchen:");
		for (int i = 0; i < ZUTATEN[0].length; i++) {
			if (ZUTATEN[0][i] instanceof Broetchen) {
				System.out.println(ZUTATEN[0][i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Bratlinge:");
		for (int i = 0; i < ZUTATEN[1].length; i++) {
			if (ZUTATEN[1][i] instanceof Bratling) {
				System.out.println(ZUTATEN[1][i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Salate:");
		for (int i = 0; i < ZUTATEN[2].length; i++) {
			if (ZUTATEN[2][i] instanceof Salat) {
				System.out.println(ZUTATEN[2][i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Gemuese:");
		for (int i = 0; i < ZUTATEN[3].length; i++) {
			if (ZUTATEN[3][i] instanceof Gemuese) {
				System.out.println(ZUTATEN[3][i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Saucen:");
		for (int i = 0; i < ZUTATEN[4].length; i++) {
			if (ZUTATEN[4][i] instanceof Sauce) {
				System.out.println(ZUTATEN[4][i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

	}

	/**
	 * Erstellung eines Arrays mit den möglichen Zutaten.
	 * @return Array  mit Zutaten.
	 */
	public static Zutat[][] generiereZutaten() {
		return new Zutat[][] { {
				new Broetchen("Hamburger", 10, 0.85, true, Zutat.VEGETARISCH, 90, 27),
				new Broetchen("Hamburger Sesam", 11, 0.95, true, Zutat.VEGETARISCH, 90, 28),
				new Broetchen("Vegan-Broetchen", 12, 0.55, false, Zutat.VEGAN, 240, 34),
				new Broetchen("Ciabatta", 13, 0.45, false, Zutat.VEGETARISCH, 330, 41),
				}, {
				new Bratling("Rindfleisch (Original)", 20, 1.85, true, Zutat.FLEISCHHALTIG, 270, 25),
				new Bratling("Haehnchenfleisch gegrillt", 21, 1.15, false, Zutat.FLEISCHHALTIG, 180, 11),
				new Bratling("Falafel-Bratling", 22, 1.45, false, Zutat.VEGAN, 210, 21),
				new Bratling("Gemuese-Bratling", 23, 1.75, false, Zutat.VEGETARISCH, 240, 25),
				}, {
				new Salat("Eisbergsalat", 30, 0.18, true),
				new Salat("Rucolasalat", 31, 0.25, false),
				}, {
				new Gemuese("Tomate", 40, 0.25, true, 3, 3),
				new Gemuese("Salzgurke", 41, 0.15, true, 4, 2),
				new Gemuese("Rote Zwiebelringe", 42, 0.08, false, 5, 2),
				new Gemuese("Jalapeno-Ringe", 43, 0.08, false, 5, 2),
				}, {
				new Sauce("Ketchup", 50, 0.10, true, Zutat.VEGAN, 5, Sauce.NORMAL),
				new Sauce("Sandwich-Sauce", 51, 0.15, true, Zutat.VEGETARISCH, 10, Sauce.NORMAL),
				new Sauce("Chili-Sauce", 52, 0.25, false, Zutat.VEGAN, 8, Sauce.SCHARF),
				new Sauce("Honig-Senf-Sauce", 53, 0.18, false, Zutat.VEGETARISCH, 8, Sauce.SUESS)
				}};

	}

}
