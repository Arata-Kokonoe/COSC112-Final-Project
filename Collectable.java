import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Collectable{
    public Pair collPosition;
    public Pair collDimensions;
    public Hitbox collHitbox;
    public BufferedImage collSprite;
    public boolean used;

    public Collectable(int x, int y){
        collPosition = new Pair(x, y);
        try {                
                collSprite = ImageIO.read(new File("HalfHeartLeft.png"));
            } 
            catch (IOException ex) {
                System.out.println("Failed to find image.");
            }
        collDimensions = new Pair(collSprite.getWidth(), collSprite.getHeight());
        collHitbox = new Hitbox(collDimensions, collPosition);
        used = false;
    }

    public void draw(Graphics g){
        if (!used) g.drawImage(collSprite, (int)collPosition.x, (int)collPosition.y, null);
    }

}
