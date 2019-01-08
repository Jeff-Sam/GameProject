import java.lang.IllegalStateException;

public class Location {
	private String description;

	public Location(String description) {
		this.description = description;
	}

	public Location() {
		this("There doesn't seem to be anything here.");
	}

	public String getDescription() {
		return this.description;
	}
}
	//compare coordinate with json file to look for corresponding coordinate and give description
