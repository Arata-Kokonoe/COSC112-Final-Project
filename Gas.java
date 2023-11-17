import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Gas{
    public int height;
    public BufferedImage sprite;
    public int counter;

    public Gas(){
        height = 0;

        try {                
            sprite = ImageIO.read(new File("Gas.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
    }

    public void draw(World w, Graphics g){
        g.drawImage(sprite, 0, w.height - height, null);
    }

    public void update(double time){
        counter += (int)(60 * time);
        if (counter == 180) {
            height += 50;
            counter = 0;
        }
    }
}
