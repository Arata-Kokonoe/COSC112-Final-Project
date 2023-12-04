// =========================================================================
// IMPORTS

import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class Cat implements canShoot{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public double lives;
    public boolean state;
    public int experience;
    public Pair catPosition;
    public Pair catVelocity;
    public Pair catAcceleration;
    public double stateCD;
    public Pair catDimensions;
    private BufferedImage rightSprite;
    private BufferedImage leftSprite;
    private BufferedImage leftHalfHeartSprite;
    private BufferedImage rightHalfHeartSprite;
    public Hitbox catHitbox;
    public String orientation;
    // =====================================================================



    // =====================================================================
    public Cat(){
        catPosition = new Pair(50.0, 615.0);
        catVelocity = new Pair(0.0, 0.01);
        catAcceleration = new Pair(0.0, 200.0);
        catDimensions = new Pair(65.0, 40.0);
        catHitbox = new Hitbox(catDimensions, catPosition);
        lives = 3;
        orientation = "right";

        try {                
            rightSprite = ImageIO.read(new File("NyanCatRight.png"));
            leftSprite = ImageIO.read(new File("NyanCatLeft.png"));
            leftHalfHeartSprite = ImageIO.read(new File("HalfHeartLeft.png"));
            rightHalfHeartSprite = ImageIO.read(new File("HalfHeartRight.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
    } // Cat()
    // =====================================================================



    // =====================================================================
    public void draw(World w, Graphics g){
        if (orientation.equals("left")) g.drawImage(leftSprite, (int)catPosition.x, (int)catPosition.y, null);
        else if (orientation.equals("right")) g.drawImage(rightSprite, (int)catPosition.x, (int)catPosition.y, null);
        for (double i = 0; i < lives; i += 0.5){
            if(i % 1 != 0) g.drawImage(rightHalfHeartSprite, (int)(w.width - (90 - (30 * (i - 0.5)))), 0, null);
            else if (i % 1 == 0) g.drawImage(leftHalfHeartSprite, (int)(w.width - (90 - (30 * i))), 0, null);
        }
    } // draw()
    // =====================================================================



    // =====================================================================
    public void update(World w, double time){
        catPosition = catPosition.add(catVelocity.times(time));
        if (!this.checkCollisions(w) && catVelocity.y == 0.0){
            boolean check = false;
            for (Platform p : w.currentRoom.platforms) {
                if (catHitbox.isOnTopOfForgiving(p.platformHitbox)) check = true;
            }
            if (!check) catVelocity.y = 0.01;
        }
        if (catVelocity.y != 0.0) {
            catVelocity.y = catVelocity.y + (catAcceleration.y * time);
        }
        if (catVelocity.x < 0.0) orientation = "left";
        else if (catVelocity.x > 0.0) orientation = "right";
        catHitbox.update(catPosition);
    } // update()
    // =====================================================================



    // =====================================================================
    public boolean checkCollisions(World w){
        boolean collision = false;
        if (catPosition.y + catDimensions.y >= w.height){
            catPosition.y = w.height - catDimensions.y; //checks if bottom of cat has touched bottom of world
            catVelocity.y = 0.0;
            collision = true;
        }
        else if (catPosition.y <= 0.0){
            catPosition.y = 0.0; //checks if top of cat has touched top of world
            catVelocity.y = 0.1;
            collision = true;
        }
        if (catPosition.x + catDimensions.x >= w.width){
            catPosition.x = w.width - catDimensions.x; //checks if right side of cat has touched the right side of world
            catVelocity.y = 0.0;
            collision = true;
        }
        else if (catPosition.x <= 0.0){
            catPosition.x = 0.0; //checks if left side of cat has touched the left side of world
            collision = true;
        }

        for (Platform p : w.currentRoom.platforms){;
            catHitbox.update(catPosition);
            if (catHitbox.leftCollision(p.platformHitbox)){
                if ((catHitbox.hitboxBot <= p.platformHitbox.hitboxTop + 3) && ((catHitbox.hitboxLeft <= p.platformHitbox.hitboxLeft && catHitbox.hitboxRight >= p.platformHitbox.hitboxLeft) || (catHitbox.hitboxRight >= p.platformHitbox.hitboxRight && catHitbox.hitboxLeft <= p.platformHitbox.hitboxRight))){
                    catPosition.y = p.platformPosition.y - catDimensions.y;
                    catVelocity.y = 0.0;
                    collision = true;
                }
                else if ((catHitbox.hitboxTop >= p.platformHitbox.hitboxBot - 3) && ((catHitbox.hitboxLeft <= p.platformHitbox.hitboxLeft && catHitbox.hitboxRight >= p.platformHitbox.hitboxLeft) || (catHitbox.hitboxRight >= p.platformHitbox.hitboxRight && catHitbox.hitboxLeft <= p.platformHitbox.hitboxRight))){
                    catPosition.y = p.platformPosition.y + p.platformDimensions.y;
                    catVelocity.y = 0.01;
                    collision = true;
                }
                else{
                    catPosition.x = p.platformPosition.x - catDimensions.x;
                    collision = true;
                }
            }
            else if (catHitbox.rightCollision(p.platformHitbox)){
                if ((catHitbox.hitboxBot <= p.platformHitbox.hitboxTop + 3) && ((catHitbox.hitboxLeft <= p.platformHitbox.hitboxLeft && catHitbox.hitboxRight >= p.platformHitbox.hitboxLeft) || (catHitbox.hitboxRight >= p.platformHitbox.hitboxRight && catHitbox.hitboxLeft <= p.platformHitbox.hitboxRight))){
                    catPosition.y = p.platformPosition.y - catDimensions.y;
                    catVelocity.y = 0.0;
                    collision = true;
                }
                else if ((catHitbox.hitboxTop >= p.platformHitbox.hitboxBot - 3) && ((catHitbox.hitboxLeft <= p.platformHitbox.hitboxLeft && catHitbox.hitboxRight >= p.platformHitbox.hitboxLeft) || (catHitbox.hitboxRight >= p.platformHitbox.hitboxRight && catHitbox.hitboxLeft <= p.platformHitbox.hitboxRight))){
                    catPosition.y = p.platformPosition.y + p.platformDimensions.y;
                    catVelocity.y = 0.01;
                    collision = true;
                }
                else{
                    catPosition.x = p.platformPosition.x + p.platformDimensions.x;
                    collision = true;
                }
            }
            else if (catHitbox.topCollision(p.platformHitbox)){
                catPosition.y = p.platformPosition.y - catDimensions.y;
                catVelocity.y = 0.0;
                collision = true;
            }
            else if (catHitbox.botCollision(p.platformHitbox)){
                catPosition.y = p.platformPosition.y + p.platformDimensions.y + 1;
                catVelocity.y = 0.0;
                collision = true;
            }
        }

        if (catPosition.y + catDimensions.y >= w.height - w.currentRoom.gas.height) {
            lives -= 0.5;
            System.out.println("Lives: " + lives);
            catPosition = new Pair(50.0, 615.0);
            w.currentRoom.gas.reset();
        }

        if (catHitbox.anyCollision(w.currentRoom.button.buttonHitbox)){
            w.currentRoom.button.pressed = true;
            if(w.currentRoom.door1 != null) w.currentRoom.door1.unlocked = true;
            if(w.currentRoom.door2 != null) w.currentRoom.door2.unlocked = true;
            w.currentRoom.gas.vanish();
        }
        return collision;
    } // checkCollisions()
    // =====================================================================



    // =====================================================================
    public void shoot(){

    } // shoot()
    // =====================================================================



// =========================================================================
} // class Cat
// =========================================================================
