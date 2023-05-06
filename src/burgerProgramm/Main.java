package burgerProgramm;

import de.hsrm.mi.prog.util.StaticScanner;

public class Main {
	final static Zutat[] ZUTATEN = generiereZutaten();

	public static void main(String[] args) {
		String command = "";

		druckeWillkommensText();
		druckeAnleitung();

		do {
			command = StaticScanner.nextString();
			command = command.toLowerCase();

			switch (command) {
			case "menu":
				druckeMenu();
				break;
			case "neuer burger":

				break;
			case "zutat":

				break;
			case "ok":

				break;
			case "meine burger":

				break;
			case "bestellen":

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

		} while (!command.equalsIgnoreCase("bestellen") && !command.equalsIgnoreCase("abbruch"));
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
		System.out.println("Mit 'Zutat <Bestellnummer>' fuegst du deinem Burger eine Zutat hinzu.");
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
				new Gemuese("Tomate", 40, 0.25, true, 3, 3), new Gemuese("Salzgurke", 41, 0.15, true, 4, 2),
				new Gemuese("Rote Zwiebelringe", 42, 0.08, false, 5, 2),
				new Gemuese("Jalapeno-Ringe", 43, 0.08, false, 5, 2),

				// Sauce(String name, int nummer, float preis, boolean klassisch, String typ,
				// int menge, String geschmack) {
				new Sauce("Ketchup", 50, 0.1, true, Zutat.VEGAN, 5, Sauce.NORMAL),
				new Sauce("Sandwich-Sauce", 51, 0.15, true, Zutat.VEGETARISCH, 10, Sauce.NORMAL),
				new Sauce("Chili-Sauce", 52, 0.25, false, Zutat.VEGAN, 8, Sauce.SCHARF),
				new Sauce("Honig-Senf-Sauce", 53, 0.18, false, Zutat.VEGETARISCH, 8, Sauce.SUESS)

		};

	}

}
