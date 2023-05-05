package burgerProgramm;
import de.hsrm.mi.prog.util.StaticScanner;

public class Main {
	
	final static Zutat[] zutaten = { 	
			//Broetchen(String name, int nummer, double preis, boolean klassisch, String typ, int backzeit, int hoehe) 
			new Broetchen("Hamburger", 10, 0.85, true, Zutat.VEGETARISCH, 90, 27),
			new Broetchen("Hamburger Sesam", 11, 0.95, true, Zutat.VEGETARISCH, 90, 28),
			new Broetchen("Vegan-Broetchen", 12, 0.55, false, Zutat.VEGAN, 240, 34),
			new Broetchen("Ciabatta", 13, 0.45, false, Zutat.VEGETARISCH, 330, 41),
		
			//Bratling(String name, int nummer, double preis, boolean klassisch, String typ, int backzeit, int hoehe)
			new Bratling("Rindfleisch (Original)", 20, 1.85, true, Zutat.FLEISCHHALTIG,270, 25),
			new Bratling("Haehnchenfleisch gegrillt", 21, 1.15, false, Zutat.FLEISCHHALTIG, 180, 11),
			new Bratling("Falafel-Bratling", 22, 1.45, false, Zutat.VEGAN, 210, 21),
			new Bratling("Gemuese-Bratling", 23, 1.75, false, Zutat.VEGETARISCH, 240, 25),

			//Salat(String name, int nummer, float preis, boolean klassisch) 
			new Salat("Eisbergsalat", 30, 0.18, true),
			new Salat("Rucolasalat", 31, 0.25, false),
			
			//Gemuese(String name, int nummer, float preis, boolean klassisch, String typ, int scheibenAnzahl,
			//int scheibenDicke
			new Gemuese("Tomate", 40, 0.25, true, 3, 3),
			new Gemuese("Salzgurke", 41, 0.15, true, 4, 2),
			new Gemuese("Rote Zwiebelringe", 42, 0.08, false, 5, 2),
			new Gemuese("Jalapeno-Ringe", 43, 0.08, false, 5, 2),
			
			//Sauce(String name, int nummer, float preis, boolean klassisch, String typ, int menge, String geschmack) {
			new Sauce("Ketchup", 50, 0.1, true, Zutat.VEGAN, 5, Sauce.NORMAL),
			new Sauce("Sandwich-Sauce", 51, 0.15, true, Zutat.VEGETARISCH, 10, Sauce.NORMAL),
			new Sauce("Chili-Sauce", 52, 0.25, false, Zutat.VEGAN, 8, Sauce.SCHARF),
			new Sauce("Honig-Senf-Sauce", 53, 0.18, false, Zutat.VEGETARISCH, 8, Sauce.SUESS)
	};
	
	public static boolean ok(String[] befehl) {
		for(int i = 0; i < befehl.length; i++) {
			if(befehl[i].equalsIgnoreCase("ok")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean quit(String[] befehl) {
		for(int i = 0; i < befehl.length; i++) {
			if(befehl[i].equalsIgnoreCase("quit")) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void doCommand(String[] befehl, Burger[] burger) {
		String tempName = "";
		int currentBurger = -1;

		for(int i = 0; i < befehl.length; i++) {
			if(befehl[i].equalsIgnoreCase("Burger")) {
				if((i+1 < befehl.length)) {
					tempName = befehl[i+1];
				}
			}
		}
		
		for(int i = 0; i < befehl.length; i++) {
			
			if(befehl[i].equalsIgnoreCase("neuer") || befehl[i].equalsIgnoreCase("neu")) {
				for(int j = 0; j < burger.length; j++) {
					if(burger[j] == null) {
						burger[j] = new Burger(tempName);
						currentBurger++; 
						break;
					}
				}
				
				String eingabe;
				String[] split;
				do {
					eingabe = StaticScanner.nextString();
					split = eingabe.split(" ");
					
					for(int x = 0; x < split.length; x++) {
						if(split[x].equalsIgnoreCase("zutat")) {
							for(int j = 0; j < zutaten.length; j++) {
								if(zutaten[j].getNummer() == Integer.parseInt(split[x+1])) {
									burger[currentBurger].fuegeZutatHinzu(zutaten[j]);
								}
							}
						}
					}
				}while(!(ok(split)));
				
				
			}
			
			if(befehl[i].equalsIgnoreCase("menu")) {
				for(int j = 0; j < zutaten.length; j++) {
					zutaten[j].toString();
				}
			}
			
			/*
			if(befehl[i].equalsIgnoreCase("ok")) {
				
			}
			
			if(befehl[i].equalsIgnoreCase("zutat")) {
				for(int j = 0; j < zutaten.length; j++) {
					if(zutaten[j].getNummer() == Integer.parseInt(befehl[i+1])) {
						burger[currentBurger].fuegeZutatHinzu(zutaten[j]);
					}
				}
			}
			*/
			
			if(befehl[i].equalsIgnoreCase("bestellen") || befehl[i].equalsIgnoreCase("bestelle")) {
				for(int j = 0; j < burger.length; j++) {
					if(burger[j] != null) {
						burger[j].zeigeRezept();
					}
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		
		String eingabe;
		String[] befehl;
		
		Burger[] burger = new Burger[10];

		
		do {
		eingabe = StaticScanner.nextString();
		befehl = eingabe.split(" ");
		
		doCommand(befehl, burger);
		}while(!(quit(befehl)));
		


	}
}
