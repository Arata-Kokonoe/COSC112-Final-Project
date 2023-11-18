import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Door {
    public Pair doorPosition;
    private BufferedImage openDoor;
    private BufferedImage closedDoor;
    public Pair doorDimensions;
    public boolean unlocked;

    public Door(double x, double y){
        doorPosition = new Pair(x, y);
        unlocked = false;
        try {                
            openDoor = ImageIO.read(new File("openDoor.png"));
            closedDoor = ImageIO.read(new File("closedDoor.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
        doorDimensions = new Pair(openDoor.getWidth(), openDoor.getHeight());
    }

    public void draw(World w, Graphics g){
        if (unlocked) g.drawImage(openDoor, (int)doorPosition.x, (int)doorPosition.y, null);
        else g.drawImage(closedDoor, (int)doorPosition.x, (int)doorPosition.y, null);
    }

}
