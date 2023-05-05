package burgerProgramm;

public class Salat extends Zutat {

	public Salat(String name, int nummer, float preis, boolean klassisch) {
		super(name, nummer, preis, klassisch, VEGAN);
	}
	
	@Override
	public double berechneHoehe() {
		return 0;
	}

	public int zubereiten() {
		System.out.println(this.name + " wird gewaschen und geschleudert.");
		return 0;
	}

	public String toString() {
		return super.toString();
	}

}
