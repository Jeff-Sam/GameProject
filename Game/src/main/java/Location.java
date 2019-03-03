public class Location {
    private String description;
    private boolean alreadyVisited = false;
//this is essentially helping build the map by setting things as they are.
    public Location(String description) {
		this.description = description;
	}
//pullng the description from the now readable format
	public String getDescription() {
		return description;
	}
//checks to see if you've already visited a location. if you have it will call the method alreadyVisited which will inform the player whether or not they've already been somewhere so they can keep track of where they've been.
	public boolean isAlreadyVisited() {
		return alreadyVisited;
	}
//setting for alreadyVisited because you will need to let the method know what to output. Once a location is visited this is essentially adding the spot to it's memory.
	public void setAlreadyVisited(boolean alreadyVisited) {
		this.alreadyVisited = alreadyVisited;
	}
}