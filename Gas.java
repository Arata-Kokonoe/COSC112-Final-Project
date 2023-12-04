// =========================================================================
// IMPORTS

import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class Gas{
// =========================================================================


    // =====================================================================
    // DATA MEMBERS

    public int height;
    public int lineHeight;
    public BufferedImage gasSprite;
    public BufferedImage lineSprite;
    public boolean exists;
    public int counter;
    // =====================================================================



    // =====================================================================
    public Gas(){
        height = 0;
        lineHeight = 50;
        exists = true;

        try {                
            gasSprite = ImageIO.read(new File("Gas.png"));
            lineSprite = ImageIO.read(new File("redDottedLine.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
    } // Gas()
    // =====================================================================



    // =====================================================================
    public void draw(World w, Graphics g){
        g.drawImage(gasSprite, 0, w.height - height, null);
        g.drawImage(lineSprite, 0, w.height - lineHeight - lineSprite.getHeight()/2, null);
    } // draw()
    // =====================================================================



    // =====================================================================
    public void update(double time){
        if (exists){
            counter += (int)(60 * time);
            if (counter == 180) {
                height += 50;
                lineHeight += 50;
                counter = 0;
            }
        }
    } // update()
    // =====================================================================



    // =====================================================================
    public void reset(){
        height = 0;
        lineHeight = 50;
        counter = 0;
    } // reset()
    // =====================================================================



    // =====================================================================
    public void vanish(){
        exists = false;
        height = 0;
        lineHeight = 0;
        counter = 0;
    } // vanish()
    // =====================================================================



// =========================================================================
} // class Gas
// =========================================================================
