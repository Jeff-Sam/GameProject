public class MapLoader {
    private String fileName;
    private Map<Coordinate, ILocation> locations;
    private static MapLoader instance;
    public mapLoader(String profileName) {
        locations = new HashMap<Coordinate, Ilocation>();
        fileName = "resources/locations.json";
        load();
    }
    //looks for name of .json file
    private String getFileName() {
        return fileName;
    }
    //loads the json file and checks if the player anywhere that is not the initial point
    private void load(){
        JsonParser parser = new JsonParser();
        File f = new File(fileName);
        if (!f.exists()){
            copyLocationsFile();
        }
        try {
            Reader reader = new FileReader(fileName);
            JsonObject json = parser.parse(reader).getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry: json.entrySet()){
                locations.put(new Coordinate(entry.getKey()), loadLocation(entry.getValue().getAsJsonObject()));
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(-1);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    //load information about location player has entered
    private ILocation loadLocation(JsonObject json) {
        Coordinate coordinate =
                new Coordinate(json.get("coordinate").getAsString());
        String description = json.get("description").getAsString();
        Ilocation location = new Location(coordinate, description);
    }
    //checks to see where the player is
    public ILocation getLocation(Coordinate coordinate) {
        if (coordinate == null) {
            getInitialLocation();
        }
        if (!locations.containsKey(coordinate)) {
            throw new RespositoryException("Argument 'coordinate' with value '" + coordinate.toString() + "' not found in repository");
        }
        return locations.get(coordinate);
    }
    //gets initial location (spawn point)
    public ILocation getInitialLocation(){
        Coordinate coordinate = new Coordinate(0, 0, 0);
        return getLocation(coordinate);
    }
}
