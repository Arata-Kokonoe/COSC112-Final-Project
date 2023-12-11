// =========================================================================
// IMPORTS

import java.util.ArrayList;
import java.awt.Graphics;
// =========================================================================



// =========================================================================
public class Room{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

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
    public Door door1;
    public Door door2;
    // =====================================================================



    // =====================================================================
    public Room(int roomType){
        this.roomType = roomType;
        platforms = new ArrayList<Platform>();
        gas = new Gas();
        if (roomType == 0){
            platforms.add(new Platform(0, 650, 2));
            platforms.add(new Platform(375, 550, 2));
            platforms.add(new Platform(775, 475, 2));
            platforms.add(new Platform(600, 375, 2));
            platforms.add(new Platform(450, 300, 2));
            platforms.add(new Platform(300, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            door1 = new Door(25, 53);
            
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
    } // Room()
    // =====================================================================



    // =====================================================================
    public void draw(World w, Graphics g){
        for (Platform p : platforms){
            p.draw(g);
        }
        button.draw(w, g);
        if(door1 != null) door1.draw(w, g);
        if(door2 != null) door2.draw(w, g);
        /* for (Enemy e : enemies){
            e.draw(g);
        }
        for (Door d : doors){
            d.draw(g);
        } */
        gas.draw(w, g);
    } // draw()
    // =====================================================================



    // =====================================================================
    public void update(World w, double time){
        gas.update(time);
    } // update()
    // =====================================================================


    
// =========================================================================
} // class Room
// =========================================================================
