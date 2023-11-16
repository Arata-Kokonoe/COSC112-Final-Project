// =========================================================================
// IMPORTS

import java.util.Random;
import java.awt.Graphics;
// =========================================================================



// =========================================================================
public class World{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public int width;
    public int height;
    public Node firstRoom;
    public Node currentRoom;
    public Cat player;
    public Map map;
    public final Random RNG = new Random(12345); //find a way to change 12345 into a random seed (AKA key)
    // =====================================================================



    // =====================================================================
    public World(int width, int height){

        this.width = width;
        this.height = height;
        player = new Cat();

        //firstRoom = new Node(new Room(RNG.nextInt(5))); replace whats inside .nextInt() with however many rooms we design
        firstRoom = new Node(new Room(0)); //temporary to test just one room

    } // World ()
    // =====================================================================



    // =====================================================================
    public void drawWorld(Graphics g){
        //map.draw(g);
        //currentRoom.draw(g);
        player.draw(g);
    } // drawWorld ()
    // =====================================================================



    // =====================================================================
    public void updateWorld(double time){
        //currentRoom.update(time);
        player.update(this, time);
    } // updateWorld ()
    // =====================================================================



// =========================================================================
} // class World
// =========================================================================
