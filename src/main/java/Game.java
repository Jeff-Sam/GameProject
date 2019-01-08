import java.util.*;
public class Game{
public static String javacoordinate;
	public static void main(String[] args) {
		var player = new Player("Jeffrey");
		spawn();
		Location.compareJson(Direction.javacoordinate);
		Direction.playerInput();
	}

	private static void spawn(){
		Direction.javacoordinate = 0+","+0+","+0;
	}
}