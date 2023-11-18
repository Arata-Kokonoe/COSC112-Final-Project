import java.util.ArrayList;
import java.awt.Graphics;

public class Room{

    public int roomType;
    /*
     * Based off the roomType given through the constructor, a different room will be generated
     * Maybe odds = rooms with 1 door, evens = rooms with 2 doors
     * Different number ranges = different difficulties (i.e. 1-5 is easy rooms, 6-10 is medium, 11-15 is hard, etc.)
     */
    public ArrayList<Platform> platforms; // all of the platforms in the room
    public Button button;
    public Gas gas;
    public Room prev;
    public Room next1;
    public Room next2;

    public Room(int roomType){
        this.roomType = roomType;
        platforms = new ArrayList<Platform>();
        gas = new Gas();
        if (roomType == 0){
            platforms.add(new Platform(0, 650, 250));
            platforms.add(new Platform(387, 550, 250));
            platforms.add(new Platform(774, 475, 250));
            platforms.add(new Platform(600, 375, 70));
            platforms.add(new Platform(450, 300, 70));
            platforms.add(new Platform(300, 225, 70));
            platforms.add(new Platform(0, 125, 250));
            button = new Button(175, 100);
            System.out.println("Room 0 created");
        }
        else if (roomType == 1){

        }
        else if (roomType == 2){

        }
        else if (roomType == 3){

        }
        else if (roomType == 4){

        }
    }

    public void draw(World w, Graphics g){
        for (Platform p : platforms){
            p.draw(g);
        }
        button.draw(w, g);
        /* for (Enemy e : enemies){
            e.draw(g);
        }
        for (Door d : doors){
            d.draw(g);
        } */
        gas.draw(w, g);
    }

    public void update(World w, double time){
        gas.update(time);
    }
}
