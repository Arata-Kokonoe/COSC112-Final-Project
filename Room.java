// =========================================================================
// IMPORTS

import java.util.ArrayList;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
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
    public ArrayList<Door> doors;
    public Button button;
    public Gas gas;
    public Room prev;
    public Room next1;
    public Room next2;
    public Door door1;
    public Door door2;
    public Door backDoor;
    private BufferedImage wall;
    private BufferedImage line;
    // =====================================================================



    // =====================================================================
    public Room(int roomType){
        try {                
                wall = ImageIO.read(new File("wall-1.png"));
                line = ImageIO.read(new File("line.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
        this.roomType = roomType;
        platforms = new ArrayList<Platform>();
        doors = new ArrayList<Door>();
        gas = new Gas();
        if (roomType == 0){
            platforms.add(new Platform(0, 675, 11));
            platforms.add(new Platform(400, 575, 2));
            platforms.add(new Platform(775, 475, 3));
            platforms.add(new Platform(550, 375, 2));
            platforms.add(new Platform(400, 300, 2));
            platforms.add(new Platform(250, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            
            System.out.println("Room Type 0 created");
        }
        else if (roomType == 1){
            try {                
                wall = ImageIO.read(new File("wall-2.png"));
            } 
            catch (IOException ex) {
                System.out.println("Failed to find image.");
            }
            platforms.add(new Platform(0, 650, 2));
            platforms.add(new Platform(375, 550, 2));
            platforms.add(new Platform(775, 475, 2));
            platforms.add(new Platform(600, 375, 2));
            platforms.add(new Platform(450, 300, 2));
            platforms.add(new Platform(300, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            doors.add(new Door (25, 578, 0));
            
            System.out.println("Room Type 1 created");
        }
        else if (roomType == 2){
            platforms.add(new Platform(0, 650, 2));
            platforms.add(new Platform(375, 550, 2));
            platforms.add(new Platform(775, 475, 2));
            platforms.add(new Platform(600, 375, 2));
            platforms.add(new Platform(450, 300, 2));
            platforms.add(new Platform(300, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            doors.add(new Door (25, 578, 0));

            System.out.println("Room Type 2 created");
        }
        else if (roomType == 3){
            platforms.add(new Platform(0, 650, 2));
            platforms.add(new Platform(375, 550, 2));
            platforms.add(new Platform(775, 475, 2));
            platforms.add(new Platform(600, 375, 2));
            platforms.add(new Platform(450, 300, 2));
            platforms.add(new Platform(300, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            doors.add(new Door (25, 578, 0));
            System.out.println("Room Type 3 created");
        }
        else if (roomType == 4){
            platforms.add(new Platform(0, 650, 2));
            platforms.add(new Platform(375, 550, 2));
            platforms.add(new Platform(775, 475, 2));
            platforms.add(new Platform(600, 375, 2));
            platforms.add(new Platform(450, 300, 2));
            platforms.add(new Platform(300, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            doors.add(new Door (25, 578, 0));

            System.out.println("Room Type 4 created");
        }
        else if (roomType == 5){
            platforms.add(new Platform(0, 650, 2));
            platforms.add(new Platform(375, 550, 2));
            platforms.add(new Platform(775, 475, 2));
            platforms.add(new Platform(600, 375, 2));
            platforms.add(new Platform(450, 300, 2));
            platforms.add(new Platform(300, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            doors.add(new Door (25, 578, 0));

            System.out.println("Room Type 5 created");
        }

    } // Room()
    // =====================================================================



    // =====================================================================
    public void draw(World w, Graphics g){
        g.drawImage(wall, 0, 0, null);
        if((w.time % 1) > 0.5) {
            g.drawImage(line, 0, w.height - gas.lineHeight - line.getHeight()/2, null);
        }
        for (Platform p : platforms){
            p.draw(g);
        }
        button.draw(w, g);
        /* for (Enemy e : enemies){
            e.draw(g);
        } */
        for (Door d : doors){
            if (d != null) d.draw(w, g);
        } 
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
