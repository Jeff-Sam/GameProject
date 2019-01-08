import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Location{
	public static void main(String args[]) {
		readJson("resources/locations.json");
	}

	public static void readJson(String file) {
		FileReader fileReader = new FileReader(file);
		JSONObject json = (JSONObject) parser.parse(fileReader);
		String coordinate = (String) json.get("coordinate");
		String description = (String) json.get("description");
	}
//compare to see if coordinates created from user input equals any object's coordinates in the json file.
	public static void compareJson(){
		if (Direction.javacoordinate.equals(coordinate)) {
			System.out.println(description);
		}
	}
}