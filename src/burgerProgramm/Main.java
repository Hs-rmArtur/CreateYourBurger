package burgerProgramm;

import de.hsrm.mi.prog.util.StaticScanner;

public class Main {
	final static String BURGER_OK = "ok";

	final static int MAX_ANZ_BURGER = 10;
	final static Zutat[] ZUTATEN = generiereZutaten();

	static Burger[] burgerBestellungen = new Burger[MAX_ANZ_BURGER];

	public static void main(String[] args) {
		String eingabe = "";
		String[] commands;
		Burger aktuellerBurger = null;

		druckeWillkommensText();
		druckeAnleitung();

		do {
			eingabe = StaticScanner.nextString();
			commands = bearbeiteBefehle(eingabe);

			switch (commands[0]) {
			case "menu":
				druckeMenu();
				break;
			case "neuer":
				if (commands.length > 2) {
					aktuellerBurger = erstelleBurger(commands[2]);
					belegeBurger(aktuellerBurger);
					System.out.println("Bitte deine Eingabe:");
				} else {
					System.out.println("Du musst deinem Burger einen Namen geben!");
				}

				break;
			case "meine":
				zeigeAktBestellungen();
				break;
			case "bestellen":
				bestellungAbgeben();

				break;
			case "befehle":
				druckeAnleitung();

				break;
			case "abbruch":
				System.out.println("Dein Bestellvorgang wurde abgebrochen.");
				break;
			default:
				System.out.println("Deine Eingabe war nicht korrekt. Versuche es gerne erneut.");
			}

		} while (!commands[0].equalsIgnoreCase("bestellen") && !commands[0].equalsIgnoreCase("abbruch"));
	}

	public static void zeigeAktBestellungen() {
		for (int i = 0; i < burgerBestellungen.length; i++) {
			if (burgerBestellungen[i] != null) {
				System.out.println(burgerBestellungen[i].toString());
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
			}
		}

		System.out.println();
		System.out.println("Gesamtzubereitungszeit: " + (zubereitungsZeit / 60) + " Minuten und "
				+ (zubereitungsZeit % 60) + " Seekunden");
		System.out.println("Gesamtpreis: " + gesamtPreis + " Euro");
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
		String commands[];
		Zutat aktuelleZutat = null;
		int zutatenNr;
		int zaehlerZutaten = 1;
		boolean zutatHinzugefuegt = false;

		System.out.println();
		System.out.println("Mit was moechtest du deinen Burger belegen? ");
		System.out.println("Mit 'ok' kannst du deine Zusammenstellung abschließen.");
		do {
			// Prozedur zur Abfragung der Zutaten
			do {
				System.out.println("Bitte gib die " + zaehlerZutaten + ". Zutat an:");
				eingabe = StaticScanner.nextString();
				commands = bearbeiteBefehle(eingabe);

				if (!commands[0].equalsIgnoreCase(BURGER_OK)) {
					zutatenNr = Integer.parseInt(commands[0]);
					aktuelleZutat = findeZutat(zutatenNr);

					if (aktuelleZutat == null) {
						System.out.println("Die Zutat haben wir leider nicht. Versuch es doch mit einer anderen!");
					} else if (aktuelleZutat instanceof Broetchen) {
						System.out.println(
								"Dein Burger kann nur aus einem Broetchen bestehen! Belege es mit etwas anderem.");
					}
				}

			} while (aktuelleZutat == null || aktuelleZutat instanceof Broetchen);

			zutatHinzugefuegt = burger.fuegeZutatHinzu(aktuelleZutat);

			if (!zutatHinzugefuegt) {
				System.out.println("Maximale Anzahl an Zutaten erreicht!");
			} else {
				zaehlerZutaten++;
				System.out.println("> Zutat " + aktuelleZutat.nummer);
				System.out.println(aktuelleZutat.name + " - " + aktuelleZutat.preis + " Euro");
			}

		} while (!(commands[0].equalsIgnoreCase(BURGER_OK)));

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

		} else {
			System.out.println("Du hast deine maximale Anzahl an Burgern erstellt. Zeit diese zu bestellen!");
		}

		return aktuellerBurger;
	}

	public static void initialisiereBurgerMitBroetchen(Burger burger) {
		Zutat aktuelleZutat = null;
		int zutatenNr = 0;

		do {
			System.out.println("Aus welchem Broetchen soll dein Burger bestehen? ");
			zutatenNr = StaticScanner.nextInt();
			aktuelleZutat = findeZutat(zutatenNr);
			if (!(aktuelleZutat instanceof Broetchen)) {
				System.out.println("Du musst zunächst ein Broetchen waehlen, um deinen Burger belegen zu koennen.");
			}
		} while (!(aktuelleZutat instanceof Broetchen));

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

	public static void druckeAnleitung() {
		System.out.println("Wie es funktioniert:");
		System.out.println("Mit 'Menu' kannst du dir alle zur Verfügung stehenden Zutaten anzeigen lassen.");
		System.out.println("Mit 'neuer Burger <Name>' beginnst du die Kreation deines Burgers.");
		System.out.println("Mit 'meine Burger' gibst du dir alle bereits erstellten Kreationen aus.");
		System.out.println(
				"Mit 'bestellen' schließt du deine Kreationen ab und wir machen uns an die Arbeit, um deine Burger fertig zu stellen.");
		System.out.println("Mit 'Befehle' lässt du dir diese Befehlsliste erneut ausgeben.");
		System.out.println("Mit 'abbruch' brichst du deine gesamte Bestellung ab.");
		System.out.println();
		System.out.println("Bitte deine Eingabe:");
		System.out.println();
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
		return new Zutat[] {
				// Broetchen(String name, int nummer, double preis, boolean klassisch, String
				// typ, int backzeit, int hoehe)
				new Broetchen("Hamburger", 10, 0.85, true, Zutat.VEGETARISCH, 90, 27),
				new Broetchen("Hamburger Sesam", 11, 0.95, true, Zutat.VEGETARISCH, 90, 28),
				new Broetchen("Vegan-Broetchen", 12, 0.55, false, Zutat.VEGAN, 240, 34),
				new Broetchen("Ciabatta", 13, 0.45, false, Zutat.VEGETARISCH, 330, 41),

				// Bratling(String name, int nummer, double preis, boolean klassisch, String
				// typ, int backzeit, int hoehe)
				new Bratling("Rindfleisch (Original)", 20, 1.85, true, Zutat.FLEISCHHALTIG, 270, 25),
				new Bratling("Haehnchenfleisch gegrillt", 21, 1.15, false, Zutat.FLEISCHHALTIG, 180, 11),
				new Bratling("Falafel-Bratling", 22, 1.45, false, Zutat.VEGAN, 210, 21),
				new Bratling("Gemuese-Bratling", 23, 1.75, false, Zutat.VEGETARISCH, 240, 25),

				// Salat(String name, int nummer, float preis, boolean klassisch)
				new Salat("Eisbergsalat", 30, 0.18, true), new Salat("Rucolasalat", 31, 0.25, false),

				// Gemuese(String name, int nummer, float preis, boolean klassisch, String typ,
				// int scheibenAnzahl,
				// int scheibenDicke
				new Gemuese("Tomate", 40, 0.25, true, 3, 3),
				new Gemuese("Salzgurke", 41, 0.15, true, 4, 2),
				new Gemuese("Rote Zwiebelringe", 42, 0.08, false, 5, 2),
				new Gemuese("Jalapeno-Ringe", 43, 0.08, false, 5, 2),

				// Sauce(String name, int nummer, float preis, boolean klassisch, String typ,
				// int menge, String geschmack) {
				new Sauce("Ketchup", 50, 0.10, true, Zutat.VEGAN, 5, Sauce.NORMAL),
				new Sauce("Sandwich-Sauce", 51, 0.15, true, Zutat.VEGETARISCH, 10, Sauce.NORMAL),
				new Sauce("Chili-Sauce", 52, 0.25, false, Zutat.VEGAN, 8, Sauce.SCHARF),
				new Sauce("Honig-Senf-Sauce", 53, 0.18, false, Zutat.VEGETARISCH, 8, Sauce.SUESS)

		};

	}

}
