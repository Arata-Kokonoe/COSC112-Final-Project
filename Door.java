// =========================================================================
// IMPORTS

import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class Door {
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public Pair doorPosition;
    public int doorType; //0 = backdoor, 1 = left door, 2 = right door
    private BufferedImage openDoor;
    private BufferedImage closedDoor;
    public Pair doorDimensions;
    public boolean unlocked;
    public Hitbox doorHitbox;
    // =====================================================================



    // =====================================================================
    public Door(double x, double y, int type){
        doorPosition = new Pair(x, y);
        unlocked = false;
        try {                
            openDoor = ImageIO.read(new File("Graphics/openDoor.png"));
            closedDoor = ImageIO.read(new File("Graphics/closedDoor.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
        doorDimensions = new Pair(openDoor.getWidth(), openDoor.getHeight());
        this.doorType = type;
        doorHitbox = new Hitbox(doorDimensions, doorPosition);
    } // Door()
    // =====================================================================



    // =====================================================================
    public void draw(Graphics g){
        if (unlocked) g.drawImage(openDoor, (int)doorPosition.x, (int)doorPosition.y, null);
        else g.drawImage(closedDoor, (int)doorPosition.x, (int)doorPosition.y, null);
    } // draw()
    // =====================================================================



// =========================================================================
} // class Door
// =========================================================================
