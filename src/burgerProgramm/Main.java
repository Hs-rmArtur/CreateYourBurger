package burgerProgramm;

import de.hsrm.mi.prog.util.StaticScanner;

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
	private final static Zutat[] ZUTATEN = generiereZutaten();

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

			if (fuehreAllgemeineBefehleAus(befehle) == false) {
				switch (befehle[INDEX_HAUPTBEFEHL]) {
				case BEFEHL_NEUER_BURGER:
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

	public static void zeigeAktBestellungen() {
		for (int i = 0; i < burgerBestellungen.length; i++) {
			if (burgerBestellungen[i] != null) {
				System.out.println(burgerBestellungen[i].toString());
				System.out.println(burgerBestellungen[i].gebeZutatenAus());
			}
		}
	}

	public static void bestellungAbgeben() {
		int zubereitungsZeit = 0;
		double gesamtPreis = 0;

		for (int j = 0; j < burgerBestellungen.length; j++) {
			if (burgerBestellungen[j] != null) {
				burgerBestellungen[j].zeigeRezept();

				if (burgerBestellungen[j].berechneZubereitungszeit() > zubereitungsZeit) {
					zubereitungsZeit = burgerBestellungen[j].berechneZubereitungszeit();
				}

				gesamtPreis += burgerBestellungen[j].berechnePreis();
				System.out.println();
			}
		}

		System.out.println();
		System.out.println("Gesamtzubereitungszeit: " + (zubereitungsZeit / 60) + " Minuten und "
				+ (zubereitungsZeit % 60) + " Sekunden");
		System.out.println("Gesamtpreis: " + gesamtPreis + " Euro\n");
	}

	public static String[] bearbeiteBefehle(String befehle) {
		befehle = befehle.toLowerCase();
		return befehle.split(" ");
	}

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

	public static void belegeBurger(Burger burger) {
		String eingabe = "";
		String befehle[];
		Zutat aktuelleZutat = null;
		int zutatenNr;
		int zaehlerZutaten = 1;
		boolean zutatHinzugefuegt = false;
		boolean istAllgemeinerBefehl = false;

		System.out.println();
		System.out.println("Mit was moechtest du deinen Burger belegen? ");
		System.out.println("Mit '" + BEFEHL_OK + "' kannst du deine Zusammenstellung abschließen.");

		do {
			// Prozedur zur Abfragung der Zutaten
			do {
				System.out.println("Bitte gib die " + zaehlerZutaten + ". Zutat an:");
				eingabe = StaticScanner.nextString();
				befehle = bearbeiteBefehle(eingabe);

				istAllgemeinerBefehl = fuehreAllgemeineBefehleAus(befehle);

				if (!befehle[INDEX_HAUPTBEFEHL].equals(BEFEHL_OK) && istAllgemeinerBefehl == false) {
					zutatenNr = sucheNummerImBefehl(befehle);
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

			} while (aktuelleZutat == null || aktuelleZutat instanceof Broetchen || istAllgemeinerBefehl == true);

			if (!befehle[INDEX_HAUPTBEFEHL].equals(BEFEHL_OK)) {
				zutatHinzugefuegt = burger.fuegeZutatHinzu(aktuelleZutat);
			}

			if (!zutatHinzugefuegt) {
				System.out.println("Maximale Anzahl an Zutaten erreicht!");
			} else if (!befehle[INDEX_HAUPTBEFEHL].equals(BEFEHL_OK)) {
				zaehlerZutaten++;
				System.out.println("> Zutat " + aktuelleZutat.nummer);
				System.out.println(aktuelleZutat.name + " - " + aktuelleZutat.preis + " Euro");
			}
		} while (!(befehle[INDEX_HAUPTBEFEHL].equals(BEFEHL_OK)));

		System.out.println("Dein Burger '" + burger.getName() + "' wird der Bestellung hinzugefuegt.");

	}

	public static Burger erstelleBurger(String name) {
		Burger aktuellerBurger = null;

		if (!pruefeObBestellungenMaxErreicht()) {
			for (int i = 0; i < burgerBestellungen.length; i++) {
				if (burgerBestellungen[i] == null) {
					aktuellerBurger = new Burger(name);
					burgerBestellungen[i] = aktuellerBurger;
					break;
				}
			}

			initialisiereBurgerMitBroetchen(aktuellerBurger);

			return aktuellerBurger;
		} else {
			System.out.println("Du hast deine maximale Anzahl an Burgern erstellt. Zeit diese zu bestellen!");
			return null;
		}

	}

	public static void initialisiereBurgerMitBroetchen(Burger burger) {
		Zutat aktuelleZutat = null;
		int zutatenNr = 0;
		String eingabe = "";
		String[] befehle;
		boolean istAllgemeinerBefehl = false;

		do {
			System.out.println("Aus welchem Broetchen soll dein Burger bestehen? ");
			eingabe = StaticScanner.nextString();
			befehle = bearbeiteBefehle(eingabe);
			istAllgemeinerBefehl = fuehreAllgemeineBefehleAus(befehle);

			if (istAllgemeinerBefehl == false) {
				zutatenNr = sucheNummerImBefehl(befehle);
				aktuelleZutat = findeZutat(zutatenNr);
				if (!(aktuelleZutat instanceof Broetchen)) {
					System.out.println("Du musst zunächst ein Broetchen waehlen, um deinen Burger belegen zu koennen.");
				}
			}
		} while (!(aktuelleZutat instanceof Broetchen) || istAllgemeinerBefehl);

		burger.fuegeZutatHinzu(aktuelleZutat);
		System.out.println(aktuelleZutat.toString());
		System.out.println("Dein Burger ist bereit belegt zu werden!");
	}

	public static Zutat findeZutat(int zutatenNummer) {
		for (int i = 0; i < ZUTATEN.length; i++) {
			if (ZUTATEN[i].getNummer() == zutatenNummer) {
				return ZUTATEN[i];
			}
		}
		return null;
	}

	public static void druckeWillkommensText() {
		System.out.println("Bock auf deine eigene Burgerkreation?");
		System.out.println("Dann bist du bei uns genau richtig!");
		System.out.println(
				"Stelle deinen individuellen eigenen Burger zusammen und du erhälst dein ganz persoenliches Rezept, sowie den Burger direkt von uns zubereitet!");
		System.out.println();
	}

	public static void druckeAnleitung(boolean imBelegProzess) {
		System.out.println("Wie es funktioniert:");
		System.out.println(
				"Mit '" + BEFEHL_MENU + "' kannst du dir alle zur Verfügung stehenden Zutaten anzeigen lassen.");
		
		if (!imBelegProzess) {
			System.out.println(
					"Mit '" + BEFEHL_NEUER_BURGER + " Burger <Name>' beginnst du die Kreation deines Burgers.");
			
			System.out.println("Mit '" + BEFEHL_BESTELLEN
					+ "' schließt du deine Kreationen ab und wir machen uns an die Arbeit, um deine Burger fertig zu stellen.");

			System.out.println("Mit '" + BEFEHL_ABBRECHEN + "' brichst du deine gesamte Bestellung ab.");
		}
		
		if (imBelegProzess) {
			System.out.println(
					"Mit '<Zutatennummer>' fuegst du im Kreationsprozess deinem Burger die Zutat mit der entspraechenden Nummer hinzu.");			
		}

		System.out.println(
				"Mit '" + BEFEHL_MEINE_BURGER + " Burger' gibst du dir alle bereits erstellten Kreationen aus.");

		System.out.println("Mit '" + BEFEHL_ZEIGE_BEFEHLE + "' lässt du dir diese Befehlsliste erneut ausgeben.");

		System.out.println();
		System.out.println("Bitte deine Eingabe:");
	}

	public static int sucheNummerImBefehl(String[] befehle) {
		int nummer = 0;

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

	public static boolean fuehreAllgemeineBefehleAus(String[] befehle) {
		switch (befehle[INDEX_HAUPTBEFEHL]) {
		case BEFEHL_MENU:
			druckeMenu();
			return true;
		case BEFEHL_MEINE_BURGER:
			zeigeAktBestellungen();
			return true;
		case BEFEHL_ZEIGE_BEFEHLE:
			druckeAnleitung(true);
			return true;
		default:
			return false;
		}
	}

	public static void druckeMenu() {
		/*
		 * Wurde so implementiert, um Text zwischen den Zutatenkategorien zu
		 * ermöglichen. Vorteil ist ebenfalls, dass die Reihenfolge der Zutaten in der
		 * Liste nicht relevant ist. Perfomance technisch nicht schoen, aber da die
		 * Anzahl von Zutaten nicht so groß ist, noch in Ordnung.
		 */

		System.out.println("Broetchen:");
		for (int i = 0; i < ZUTATEN.length; i++) {
			if (ZUTATEN[i] instanceof Broetchen) {
				System.out.println(ZUTATEN[i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Bratlinge:");
		for (int i = 0; i < ZUTATEN.length; i++) {
			if (ZUTATEN[i] instanceof Bratling) {
				System.out.println(ZUTATEN[i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Salate:");
		for (int i = 0; i < ZUTATEN.length; i++) {
			if (ZUTATEN[i] instanceof Salat) {
				System.out.println(ZUTATEN[i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Gemuese:");
		for (int i = 0; i < ZUTATEN.length; i++) {
			if (ZUTATEN[i] instanceof Gemuese) {
				System.out.println(ZUTATEN[i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

		System.out.println();
		System.out.println("Saucen:");
		for (int i = 0; i < ZUTATEN.length; i++) {
			if (ZUTATEN[i] instanceof Sauce) {
				System.out.println(ZUTATEN[i].toString());
				System.out.println("--------------------------------------------------------------");
			}
		}

	}

	public static Zutat[] generiereZutaten() {
		return new Zutat[] { new Broetchen("Hamburger", 10, 0.85, true, Zutat.VEGETARISCH, 90, 27),
				new Broetchen("Hamburger Sesam", 11, 0.95, true, Zutat.VEGETARISCH, 90, 28),
				new Broetchen("Vegan-Broetchen", 12, 0.55, false, Zutat.VEGAN, 240, 34),
				new Broetchen("Ciabatta", 13, 0.45, false, Zutat.VEGETARISCH, 330, 41),

				new Bratling("Rindfleisch (Original)", 20, 1.85, true, Zutat.FLEISCHHALTIG, 270, 25),
				new Bratling("Haehnchenfleisch gegrillt", 21, 1.15, false, Zutat.FLEISCHHALTIG, 180, 11),
				new Bratling("Falafel-Bratling", 22, 1.45, false, Zutat.VEGAN, 210, 21),
				new Bratling("Gemuese-Bratling", 23, 1.75, false, Zutat.VEGETARISCH, 240, 25),

				new Salat("Eisbergsalat", 30, 0.18, true),
				new Salat("Rucolasalat", 31, 0.25, false),

				new Gemuese("Tomate", 40, 0.25, true, 3, 3),
				new Gemuese("Salzgurke", 41, 0.15, true, 4, 2),
				new Gemuese("Rote Zwiebelringe", 42, 0.08, false, 5, 2),
				new Gemuese("Jalapeno-Ringe", 43, 0.08, false, 5, 2),

				new Sauce("Ketchup", 50, 0.10, true, Zutat.VEGAN, 5, Sauce.NORMAL),
				new Sauce("Sandwich-Sauce", 51, 0.15, true, Zutat.VEGETARISCH, 10, Sauce.NORMAL),
				new Sauce("Chili-Sauce", 52, 0.25, false, Zutat.VEGAN, 8, Sauce.SCHARF),
				new Sauce("Honig-Senf-Sauce", 53, 0.18, false, Zutat.VEGETARISCH, 8, Sauce.SUESS)

		};

	}

}
