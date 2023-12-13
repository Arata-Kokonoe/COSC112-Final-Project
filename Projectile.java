import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Projectile{
    public Hitbox projHitbox;
    public BufferedImage projSprite;
    public Pair projVelocity;
    public Pair projPosition;
    public Pair projDimensions;
    public int projRange;
    public Pair initProjPosition;
    public boolean hitSomething;

    public Projectile(Pair pos, Pair vel, BufferedImage sprite, int range){
        initProjPosition = new Pair(pos.x, pos.y);
        hitSomething = false;
        projPosition = pos;
        projVelocity = vel;
        projSprite = sprite;
        projDimensions = new Pair(projSprite.getWidth(), projSprite.getHeight());
        projHitbox = new Hitbox(projDimensions, projPosition);
        projRange = range;
    }

    public void draw(Graphics g){
        if (projPosition.x >= initProjPosition.x - projRange && projPosition.x <= initProjPosition.x + projRange && !hitSomething) g.drawImage(projSprite, (int)projPosition.x, (int)projPosition.y, null);
    }

    public void update(World w, double time){
        if (hitSomething){
            projPosition = new Pair (0,0);
        }
        projPosition = projPosition.add(projVelocity.times(time));
        projHitbox.update(projPosition);
    }
}
