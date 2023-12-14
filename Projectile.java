// =========================================================================
// IMPORTS

import java.awt.image.BufferedImage;
import java.awt.Graphics;
// =========================================================================



// =========================================================================
public class Projectile {
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public Hitbox projHitbox;
    public BufferedImage projSprite;
    public Pair projVelocity;
    public Pair projPosition;
    public Pair projDimensions;
    public int projRange;
    public Pair initProjPosition;
    public boolean hitSomething;
    // =====================================================================



    // =====================================================================
    public Projectile(Pair pos, Pair vel, BufferedImage sprite, int range) {
        initProjPosition = new Pair(pos.x, pos.y);
        hitSomething = false;
        projPosition = pos;
        projVelocity = vel;
        projSprite = sprite;
        projDimensions = new Pair(projSprite.getWidth(), projSprite.getHeight());
        projHitbox = new Hitbox(projDimensions, projPosition);
        projRange = range;
    } // Projectile()
    // =====================================================================



    // =====================================================================
    public void draw(Graphics g) {
        g.drawImage(projSprite, (int)projPosition.x, (int)projPosition.y, null);
    } // draw()
    // =====================================================================



    // =====================================================================
    public void update(World w, double time) {
        if (hitSomething || !(projPosition.x >= initProjPosition.x - projRange && projPosition.x <= initProjPosition.x + projRange)) {
            w.currentRoom.projectiles.set(w.currentRoom.projectiles.indexOf(this), null);
        }
        projPosition = projPosition.add(projVelocity.times(time));
        projHitbox.update(projPosition);
    } // update()
    // =====================================================================



// =========================================================================
} // class Projectile
// =========================================================================
