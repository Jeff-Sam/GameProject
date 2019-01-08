import java.util.Scanner;

public static enum Direction {
	//This is where the java version of the coordinate will be established. It will be within java so to be compared with json to find a corresponding coordinate and then read out the description of the object that coordinate is in.
	public String javacoordinate;
	public static int x;
	public static int y;
	public static int z;
	public static String javacoordinate;

	public void playerInput() {
		Scanner scanner = new Scanner(System.in);
		String presponse = scanner.nextLine();
		if (presponse.equals("North")) {
			//making you move up 1 on the y axis of the coordinate
			y++;
			//updating the string
			javacoordinate = x + "," + y + "," + z;
		}
		if (presponse.equals("South")) {
			y--;
			javacoordinate = x + "," + y + "," + z;
		}
		if (presponse.equals("East")) {
			x++;
			javacoordinate = x + "," + y + "," + z;
		}
		if (presponse.equals("West")) {
			x--;
			javacoordinate = x + "," + y + "," + z;
		}
		if (presponse.equals("drink") || presponse.isEqual("attempt climb") || presponse.isEqual("attempt entry") || presponse.isEqual("shout")) {
			z++;
			javacoordinate = x + "," + y + "," + z;
		}
		if (presponse.equals("cool off") || presponse.isEqual("stay quiet")) {
			z += 2;
			javacoordinate = x + "," + y + "," + z;
		}
	}



	// public static Direction read() {
	// 	var scanner = new Scanner(System.in);
	// 	System.out.print("Enter a direction: ");
	// 	try {
	// 		return Direction.valueOf(scanner.next().toUpperCase());
	// 	} catch (IllegalArgumentException ignored) {
	// 		System.out.println("Invalid direction.");
	// 		return null;
	// 	}
	// }
}