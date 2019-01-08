public class Position {
	public final int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position getNeighbor(Direction direction) {
		int x = 0, y = 0;
		switch (direction) {
			case NORTH:
				y++;
				break;
			case EAST:
				x++;
				break;
			case SOUTH:
				y--;
				break;
			case WEST:
				x--;
				break;
		}
		return new Position(this.x + x, this.y + y);
	}
}