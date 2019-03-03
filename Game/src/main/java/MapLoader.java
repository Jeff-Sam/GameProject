import com.google.gson.JsonParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
//this loads the map from the json and puts it into a readable format for java. It will do so by creating a map for each coordinate that has a corresponding description.
public class MapLoader {
    static Map<String, Location> loadMap() {
        var map = new HashMap<String, Location>();
        var file = MapLoader.class.getResourceAsStream("map.json");
        try (var reader = new BufferedReader(new InputStreamReader(file))) {
            var root = new JsonParser().parse(reader).getAsJsonObject();
            for (var entry : root.entrySet()) {
                var key = entry.getKey();
                var value = entry.getValue().getAsJsonObject();
                map.put(key, new Location(value.get("description").getAsString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
