// =========================================================================
// IMPORTS

import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================

// =========================================================================
public class Platform{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public Pair platformDimensions;
    public Pair platformPosition;
    public Hitbox platformHitbox;
    public int repeat;
    private BufferedImage platform;
    // =====================================================================



    // =====================================================================
    public Platform(double x, double y, int repeat){
        platformDimensions = new Pair(100*repeat, 25);
        platformPosition = new Pair(x, y);
        platformHitbox = new Hitbox(platformDimensions, platformPosition);
        this.repeat = repeat;
        try {                
            platform = ImageIO.read(new File("platform.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
    } // Platform()
    // =====================================================================



    // =====================================================================
    public void draw(Graphics g){
       for(int i = 0; i < repeat; i++) {
        g.drawImage(platform, (int)(platformPosition.x + (100 * i)), (int)platformPosition.y, null);
       }
    } // draw()
    // =====================================================================



// =========================================================================
} // class Platform
// =========================================================================
