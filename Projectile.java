import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Projectile{
    public Hitbox projHitbox;
    public BufferedImage projSprite;
    public Pair projVelocity;
    public Pair projPosition;
    public Pair projDimensions;
    public int projRange;

    public Projectile(Pair pos, Pair vel, BufferedImage sprite, int range){
        projPosition = pos;
        projVelocity = vel;
        projSprite = sprite;
        projDimensions = new Pair(projSprite.getWidth(), projSprite.getHeight());
        projHitbox = new Hitbox(projDimensions, projPosition);
        projRange = range;
    }

    public void draw(World w, Graphics g){

    }

    public void update(){

    }
}
