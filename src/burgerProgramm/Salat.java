package burgerProgramm;

public class Salat extends Zutat {

	public Salat(String name, int nummer, float preis, boolean klassisch) {
		super(name, nummer, preis, klassisch, "vegan");
	}
	
	@Override
	public double berechneHoehe() {
		return 0;
	}

	public String zubereiten() {
		return this.name + " wird gewaschen und geschleudert.";
	}
	
	public int berechneZubereitungsZeit() {
		return 0;
	}

	public String toString() {
		return super.toString();
	}

}
