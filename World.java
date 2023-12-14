// =========================================================================
// IMPORTS

import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class World {
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public int width;
    public int height;
    public double time;
    public boolean status;
    private BufferedImage gameOver;
    public Room firstRoom;
    public Room currentRoom;
    public Cat player;
    public ArrayList<Integer> roomTypes;
    public final Random RNG = new Random((int)Math.floor(Math.random() * (10000-1000) + 1000)); //find a way to change 12345 into a random seed (AKA key)
    // =====================================================================



    // =====================================================================
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        player = new Cat();

        firstRoom = new Room(0);
        currentRoom = firstRoom;

        try {
            gameOver = ImageIO.read(new File("Graphics/game-over.png"));
        }
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }

    } // World()
    // =====================================================================



    // =====================================================================
    public void drawWorld(Graphics g) {
        //map.draw(g);
        if(status) {
            g.drawImage(gameOver, 0, 0, null);
            g.setFont(new Font("scoreFont", Font.PLAIN, 40));
            g.drawString(player.score + "", 575, 410);
        }

        else {
            currentRoom.draw(this, g);
            player.draw(this, g);
        }
    } // drawWorld()
    // =====================================================================



    // =====================================================================
    public void updateWorld(double time) {
        currentRoom.update(this, time);
        player.update(this, time);
        this.time += time;
    } // updateWorld()
    // =====================================================================



    // =====================================================================
    public void addRoom(int doorNum) {
        if (currentRoom.isRoomZero) {
            currentRoom.next1 = new Room(1);
            currentRoom.next1.prev = currentRoom;
            currentRoom = currentRoom.next1;
        }
        else if (doorNum == 1) {
            currentRoom.next1 = new Room(RNG, countRooms());
            currentRoom.next1.prev = currentRoom;
            currentRoom = currentRoom.next1;
        }
        else if (doorNum == 2) {
            currentRoom.next2 = new Room(RNG, countRooms());
            currentRoom.next2.prev = currentRoom;
            currentRoom = currentRoom.next2;
        }
    }

    // =====================================================================
    public int countRooms() {
        int count = 1;
        Room roomPointer = currentRoom;
        while(roomPointer != firstRoom) {
            count++;
            roomPointer = roomPointer.prev; 
        }
        return count;
    } // countRooms()
    // =====================================================================



// =========================================================================
} // class World
// =========================================================================
