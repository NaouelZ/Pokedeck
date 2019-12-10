
public class PokemonCard extends Card{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int healthPoints;
	
	public PokemonCard(String name, String description, String hp) {
		super("Pokemon", name, description, hp);
	}
	
	public String getHealthPoints() {
		return String.valueOf(healthPoints);
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
}
