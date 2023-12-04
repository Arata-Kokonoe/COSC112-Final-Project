// =========================================================================
// IMPORTS

import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class Button{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public Pair buttonPosition;
    public boolean pressed;
    public BufferedImage onButton;
    public BufferedImage offButton;
    public Hitbox buttonHitbox;
    // =====================================================================



    // =====================================================================
    public Button(double x, double y){
        buttonPosition = new Pair(x, y);
        try {                
            onButton = ImageIO.read(new File("buttonOn.png"));
            offButton = ImageIO.read(new File("buttonOff.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
        buttonHitbox = new Hitbox(new Pair(onButton.getWidth(), onButton.getHeight()), buttonPosition);
    } // Button()
    // =====================================================================



    // =====================================================================
    public void draw(World w, Graphics g){
        if (pressed) g.drawImage(onButton, (int)buttonPosition.x, (int)buttonPosition.y + 10, null);
        else g.drawImage(offButton, (int)buttonPosition.x, (int)buttonPosition.y, null);
    } // draw()
    // =====================================================================



// =========================================================================
} // class Button
// =========================================================================
