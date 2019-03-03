import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private static Map<String, Location> map = MapLoader.loadMap();
	private static int time = 12;
	private static Random random = new Random();

	public static void main(String[] args) {
		while (true) {
			System.out.println(getCurrentLocation().getDescription());
			if (getCurrentLocation().isAlreadyVisited()) {
				System.out.println("(you've already been here)");
			}
			System.out.println("The current time is " + formatCurrentTime() + ".");
			getCurrentLocation().setAlreadyVisited(true);
			playerInput();
		}
	}

	// This is where the java version of the coordinate will be established. It will be within java so to be compare
	// with json to find a corresponding coordinate and then read out the description of the object that coordinate is in.
	public static int x;
	public static int y;
	public static int hunger;
	public static boolean knife;
	public static boolean isFood;

	public static String getInput() {
		System.out.print("-> ");
		return new Scanner(System.in).nextLine().toLowerCase();
	}
	public static void playerInput() {
		var input = getInput();
		if (input.equals("north")) {
			//making you move up 1 on the y axis of the coordinate
			attemptMove(0, 1);
		}
		if (input.equals("south")) {
			attemptMove(0, -1);
		}
		if (input.equals("east")) {
			attemptMove(1, 0);
		}
		if (input.equals("west")) {
			attemptMove(-1, 0);
		}
		if (input.equals("attempt climb")){
			attemptMove(0, 1);
		}
		if (input.equals("climb down")){
			attemptMove(0, -1);
		}
		if (input.equals("drink")){
			System.out.println("Cupping the water in your hands, you pull it to your lips and take a swig. The taste of salt overwhelms your tongue. You quickly spit the water out. What did manage to make it down your throat leaves a trail of amplified sting. (cool off / east)");
		}
		if (input.equals("cool off")){
			System.out.println("You cup the water in your hands, splashing it on your face. Aside from a slight agitation to your sunburns, it feels refreshing and you cool off a little. (drink / east)");
		}
		if (input.equals("attempt entry")){
			System.out.println("Entering the shaft, into the cockpit find not only blood everywhere, but also broken parts and components everywhere. If you try what appears to be a radio it’ll flicker on for a second with traffic from pilots and air traffic control but it quickly dies off before you can do anything. You can find flight maps, including a map of the island you're on. You also find a knife in the cargo area on your way out. (west)");
			knife = true;
		}
		if (input.equals("shout")){
			System.out.println("You shout at the top of your lungs. A flock of exotic looking birds, dashed with color race out of the trees and into the sky.");
		}
		if (input.equals("stay quiet")){
			System.out.println("You hold your breath as you carefully approach underneath the suspicious tree. Looking up, you see a colorful bird tending to it's children. Another bird arrives with food for the birds, and must've been responsible for the ruckus.");
		}
		if (input.equals("construct help sign")){
			System.out.println("You gather as many rocks as you can find and construct the message SOS.");
			hunger = hunger + 9;
			timeDanger();
			System.out.println("After a few days, a plane flies over and spots you. 7 Hours later help arrives.");
			gameEndWin();
		}
	}

	private static void gameEndWin(){
		System.out.println("You've been rescued and brought back to safety. After reuniting with your family, you learn of a plane that crashed above the pacific ocean. They had suspected that everyone had died... that is until you showed up of course. (Close game)");
		var input = getInput();
		getInput();
		if (input.equals("Close game")){
			System.exit(0);
		}
	}

	public static Location getLocation(int x, int y) {
		String coordinate = String.format("%d,%d", x, y);
		return map.get(coordinate);
	}

	private static Location getCurrentLocation() {
		return getLocation(x, y);
	}
//attributes are being made and moved. Every time you try to move, time wil pass, your hunger will increase- which cna lead to death, then it will check to see if you should die from starvation or if you are fine. It will also determine whether or not to randomly generate the opportunity to acquire food.
	private static void attemptMove(int deltaX, int deltaY) {
		if (getLocation(x + deltaX, y + deltaY) != null) {
			x += deltaX;
			y += deltaY;
			time++;
			hunger++;
			timeDanger();
			getFood();
		} else {
			System.err.println("You can't go that way.");
		}
	}

	private static void getFood(){
		int n = random.nextInt(125) + 1;
		//randomness calculations are being made
		if ((n % 8) == 0){
			isFood = true;
			System.out.println("You see a rabbit scurry by. (attempt catch / do nothing)");
			String userCatch = getInput();
			//Right here is a system of my own design where catching food, which is vital for survival in this game is random. The odds of catching the rabbit for food however, will be based on whether or not you visited the crashed plane, which is where you would have found the knife. This knife increases your odds of catching rabbits significantly.
			if (userCatch.equals("attempt catch")) {
				if (knife = true) {
					int attemptCatch = random.nextInt(2) + 1;
					if (attemptCatch == 2) {
						hunger = hunger - 3;
						System.out.println("You've caught the animal. You eat it and you feel slightly more nourished.");
						System.out.println("Hunger: " + hunger);
					}
					else{
						hunger++;
						System.out.println("You've just missed him. Your stomach still growls.");
						System.out.println("Hunger: " +hunger);
					}
				}
				//if you don't have a knife, this is where it is determining that your odds of catching the food are far lower
				if (knife = false) {
					int attemptCatch = random.nextInt(150) + 1;
					if (attemptCatch % 3 == 0) {
						hunger = hunger - 10;
						System.out.println("You've caught the animal. You eat it and you feel slightly more nourished.");
						System.out.println("Hunger: " + hunger);
					}
					else {
						hunger = hunger + 5;
						System.out.println("You've just missed him. Your stomach still growls.");
						System.out.println("Hunger: " + hunger);
					}
				}
			}
		}
	}

	private static void timeDanger(){
		//you will get warnings when your hunger is high
		if ((time > 12 * 4) && (hunger > 10)){
			System.out.println("You're starving, you need to get food as soon as you can.");
		}
		if ((time > 24 * 4) && (hunger > 15)){
			System.out.println("Your muscles begin to spasm, you feel nauseated, the world is spinning, and your balance begins to give in.");
		}
		if ((time > 30 * 4) && (hunger > 20)){
			System.out.println("You need to find food now.");
		}
		//you will die if become starving
		if ((time > 32 * 4) && (hunger > 22)){
			System.out.println("Your muscles spasm and a severe loss of energy has stopped your ability to move. You lay down, waiting for the end.");
			gameEndDeath();
		}
	}
//the game ends here
	private static void gameEndDeath(){
		System.out.println("You have died, your journey is over. (Close game)");
		var input = getInput();
		if(input.equals("Close game")){
			System.exit(0);
		}
	}

//just formatting the time so that when it displays, it will look like an actual readable time.
	private static String formatCurrentTime() {
		return String.format("%d:%d", time / 4, time % 4 * 15);
	}
}