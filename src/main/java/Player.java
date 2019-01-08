public class Player{
	private String name;
	private int health = 100;
	private Position position;

	public Player(String name) {
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return this.name;
	}

	public int getHealth() {
		return this.health;
	}

	public boolean isDead() {
		return this.health <= 0;
	}

	public Position getPosition() {
		return this.position;
	}

	public void move(Direction d) {
		this.position = this.position.getNeighbor(d);
	}
}