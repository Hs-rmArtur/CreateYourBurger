package burgerProgramm;

public class Salat extends Zutat{
	
	public Salat(String name, int nummer, float preis, boolean klassisch, String typ) {
		super(name, nummer, preis, klassisch, typ);
	}
	
	public Salat(String name, int nummer, float preis) {
		this(name, nummer, preis, false, "vegetarisch");
	}
	
	
	
	public int zubereiten() {
		System.out.println(this.name + " wird gewaschen und geschleudert.");
		return 0;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
