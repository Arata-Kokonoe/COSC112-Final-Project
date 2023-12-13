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

    public int xPos;
    public int yPos;
    private BufferedImage rightSprite;
    private BufferedImage leftSprite;
    private BufferedImage enemyAttackSprite;
    public Hitbox enemyHitbox;
    public int velocity;
    public String orientation;
    public int health;
    // =====================================================================



    // =====================================================================
    public Enemy(int x, int y, String orientation) {
        this.xPos = x;
        this.yPos = y;
        enemyHitbox = new Hitbox(new Pair(39, 35), new Pair(x, y));
        this.orientation = orientation;
        health = 2;
        velocity = 0;
        try{ 
          rightSprite = ImageIO.read(new File("enemy-right.png"));
          leftSprite = ImageIO.read(new File("enemy-left.png"));
          enemyAttackSprite = ImageIO.read(new File("projectile.png"));
        }
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
    } // Enemy()
    // =====================================================================



    // =====================================================================
    public void draw(Graphics g){
      if(health != 0){
        if (orientation == "left") g.drawImage(leftSprite, xPos, yPos, null);
        else if (orientation == "right") g.drawImage(rightSprite, xPos, yPos, null);
      }
    } // draw()
    // =====================================================================



    // =====================================================================
    public void shoot(World w){
      if(health != 0){
        if (orientation == "left") w.currentRoom.projectiles.add(new Projectile(new Pair(xPos - 10, (yPos + 15)), new Pair(-300, 0), enemyAttackSprite, 1024));
        else if (orientation == "right") w.currentRoom.projectiles.add(new Projectile(new Pair(xPos + 45, (yPos + 15)), new Pair(300, 0), enemyAttackSprite, 1024));
      }
    } // shoot()
    // =====================================================================


    public void update(World w, double time){
      xPos += velocity * time;
      enemyHitbox.update(new Pair(xPos, yPos));

      for(Projectile p: w.currentRoom.projectiles){
        if (p != null && enemyHitbox.anyCollision(p.projHitbox)){
          p.hitSomething = true;
          health --;
          if (health == 0) w.player.score ++;
        }
      }

    }

// =========================================================================
} // class Enemy
// =========================================================================
