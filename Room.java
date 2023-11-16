import java.util.ArrayList;

public class Room{

    int roomType;
    /*
     * Based off the roomType given through the constructor, a different room will be generated
     * Maybe odds = rooms with 1 door, evens = rooms with 2 doors
     * Different number ranges = different difficulties (i.e. 1-5 is easy rooms, 6-10 is medium, 11-15 is hard, etc.)
     */
    ArrayList<Platform> platforms; // all of the platforms in the room

    public Room(int roomType){
        this.roomType = roomType;
    }
}
