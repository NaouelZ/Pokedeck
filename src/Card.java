import java.io.Serializable;
import java.util.UUID;

public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String description;
	protected String healthPoints;
	protected static String types[] = { "Pokemon", "Energy", "Trainer"};
	protected String type;
	
	public Card(String type, String name,String description, String healthPoints){
		this.type = type;
		this.name = name;
		this.description = description;
		this.healthPoints = healthPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getHealthPoints() {
		return null;};

	
}