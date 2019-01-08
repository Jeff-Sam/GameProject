import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LocationRepository{
	private String fileName;
	private Map<Coordinate> locations;
	private static LocationRespoitory instance;

	public LocationRepository(String profileName) {
		locations = new HashMap<Coordinate>();
		fileName = "json/profiles/" + profileName + "/locations.json";
		load();
	}

	public static LocationRepository createRepo(String profileName){
		if ("".equals(profileName)) {
			return instance;
		}
		if (instance == null) {
			instance = new LocationRepository(profileName);
		}
		else if (!instance.getFileName().contains(profileName)) {
			instance = new LocationRepository(profileName);
		}
		return instance;
	}

	private String getFileName() {
		return fileName;
	}

	private void load() {
		JsonParser parser = new JsonParser();
		File f = new File(fileName);
		if (!f.exists()) {
			copyLocationsFile();
		}
        try {
            Reader reader = new FileReader(fileName);
            JsonObject json = parser.parse(reader).getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry: json.entrySet()) {
                locations.put(new Coordinate(entry.getKey()), loadLocation(entry.getValue().getAsJsonObject()));
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

 private ILocation loadLocation(JsonObject json) {
        Coordinate coordinate =
            new Coordinate(json.get("coordinate").getAsString());
        String title = json.get("title").getAsString();
        String description = json.get("description").getAsString();
        LocationType locationType =
            LocationType.valueOf(json.get("locationType").getAsString());
        ILocation location = new Location(coordinate, title, description,
                locationType);

	public void writeLocations() {
		try{
			JsonObject jsonObject = new JsonObject();
			for (Map.Entry<Coordinate> entry : locations.entrySet()) {
				JsonObject LocationJsonElement = new JsonObject();
				locationJsonElement.addProperty("title", location.getTitle());
				locationJsonElement.addProperty("coordinate",
					location.getCoordinate().toString());
				jsonObject.add(location.getCoordinate().toString(),
					locationsJsonElement);
			}
			Writer writer = new FileWriter(fileName);
			Gson gson = new Gson();
			gson.tojson(jsonObject, writer);
			writer.close();
			QueueProvider.offer("The game locations were saved.");
		} catch (IOException ex) {
			QueueProvider.offer("Unable to save to file " + fileName);
		}
	}
 public ILocation getInitialLocation() {
        String profileName = fileName.split("/")[2];
        instance = null;
        LocationRepository.createRepo(profileName);
        load();
        Coordinate coordinate = new Coordinate(0, 0, 0);
        return getLocation(coordinate);
    }

    public ILocation getLocation(Coordinate coordinate) {
        if (coordinate == null) {
            return null;
        }
        if (!locations.containsKey(coordinate)) {
            throw new RepositoryException("Argument 'coordinate' with value '" + coordinate.toString() + "' not found in repository");
        }
        return locations.get(coordinate);
    }

    private void copyLocationsFile() {
        File source = new File("json/original_data/locations.json");
        File dest = new File(fileName);
        dest.mkdirs();
        try {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLocation(ILocation location) {
        locations.put(location.getCoordinate(), location);
}