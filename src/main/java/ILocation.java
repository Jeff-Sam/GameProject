public interface ILocation{
    Coordinate getCoordinate();
    String getTitle();
    String getDescription();
    LocationType getLocationType();
    Map<Direction, ILocation> getExits();
}