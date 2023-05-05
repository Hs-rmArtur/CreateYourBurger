package burgerProgramm;

public class Main {
	public static void main(String[] args) {
		/*
		 * Zutat[][] zutaten = { {new Broetchen("Hamburger", 10, 0.85, true,
		 * "vegetarisch", 90, 27), new Broetchen("Hamburger", 10, 0.85, true,
		 * "vegetarisch", 90, 27), new Broetchen("Hamburger", 10, 0.85, true,
		 * "vegetarisch", 90, 27), new Broetchen("Hamburger", 10, 0.85, true,
		 * "vegetarisch", 90, 27)},
		 * 
		 * {new Bratling("Rindfleisch", 20, 1.85, true, " ", 270, 25), new
		 * Bratling("Rindfleisch", 20, 1.85, true, " ", 270, 25), new
		 * Bratling("Rindfleisch", 20, 1.85, true, " ", 270, 25), new
		 * Bratling("Rindfleisch", 20, 1.85, true, " ", 270, 25)},
		 * 
		 * {new Broetchen("Hamburger", 10, 0.85, true, "vegetarisch", 90, 27)} };
		 */

		Zutat[] zutaten = { new Broetchen("Hamburger", 10, 0.85, true, Zutat.VEGETARISCH, 90, 27),
				new Broetchen("Hamburger Sesam", 11, 0.95, true, Zutat.VEGETARISCH, 90, 28),
				new Broetchen("Vegan-Broetchen", 12, 0.55, true, Zutat.VEGAN, 240, 34),
				new Broetchen("Ciabatta", 13, 0.45, true, Zutat.VEGETARISCH, 330, 41),

				new Bratling("Rindfleisch (Original)", 20, 1.85, true, "",270, 25),
				new Bratling("Haehnchenfleisch gegrillt", 21, 1.15, true, "", 180, 25),
				new Bratling("Falafel-Bratling", 22, 1.45, false, Zutat.VEGAN, 210, 25),
				new Bratling("Gemuese-Bratling", 23, 1.75, false, Zutat.VEGETARISCH, 240, 25),

				new Broetchen("Hamburger", 10, 0.85, true, "vegetarisch", 90, 27) };

		Burger burger = new Burger("Arur");

	}
}
