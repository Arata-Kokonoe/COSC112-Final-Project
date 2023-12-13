// =========================================================================
// IMPORTS

import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class Enemy{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public int x;
    public int y;
    private BufferedImage rightSprite;
    private BufferedImage leftSprite;
    private BufferedImage leftAttackSprite;
    // =====================================================================



    // =====================================================================
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        try{ 
          rightSprite = ImageIO.read(new File("enemy-right.png"));
          leftSprite = ImageIO.read(new File("enemy-left.png"));
          leftAttackSprite = ImageIO.read(new File("projectile.png"));
        }
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
    } // Enemy()
    // =====================================================================



    // =====================================================================
    public void draw(World w, Graphics g){
        g.drawImage(leftSprite, x, y, null);
    } // draw()
    // =====================================================================



    // =====================================================================
    public void shoot(World w){
      w.currentRoom.projectiles.add(new Projectile(new Pair(x, (y + 15)), new Pair(-300, 0), leftAttackSprite, 1024));
    } // shoot()
    // =====================================================================



// =========================================================================
} // class Enemy
// =========================================================================
