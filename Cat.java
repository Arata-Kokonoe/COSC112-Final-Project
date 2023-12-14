// =========================================================================
// IMPORTS

import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class Cat {
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public double lives;
    public boolean transformState;
    public boolean isTransformed;
    public int timer;
    public boolean attackState;
    public int score;
    public Pair catPosition;
    public Pair catVelocity;
    public Pair catAcceleration;
    public double transformStateCD;
    public double attackStateCD;
    public Pair catDimensions;
    public int attackRange;
    private BufferedImage rightSprite;
    private BufferedImage leftSprite;
    private BufferedImage rightRunSprite1;
    private BufferedImage rightRunSprite2;
    private BufferedImage leftRunSprite1;
    private BufferedImage leftRunSprite2;
    private BufferedImage rightJumpSprite;
    private BufferedImage leftJumpSprite;
    private BufferedImage rightLandSprite;
    private BufferedImage leftLandSprite;
    private BufferedImage rightHalfHeartSprite;
    private BufferedImage leftHalfHeartSprite;
    private BufferedImage leftAttackSprite;
    private BufferedImage rightAttackSprite;
    private BufferedImage state;
    public Hitbox catHitbox;
    public String orientation;
    // =====================================================================



    // =====================================================================
    public Cat() {
        catPosition = new Pair(50.0, 615.0);
        catVelocity = new Pair(0.0, 0.01);
        catAcceleration = new Pair(0.0, 300.0);
        catDimensions = new Pair(48.0, 40.0);
        catHitbox = new Hitbox(catDimensions, catPosition);
        lives = 3;
        attackRange = 100;
        orientation = "right";
        transformStateCD = 0.0;
        timer = 0;
        transformState = true;
        isTransformed = false;
        attackState = true;

        try { 
            rightAttackSprite = ImageIO.read(new File("Graphics/attack-right.png"));
            leftAttackSprite = ImageIO.read(new File("Graphics/attack-left.png"));
        }
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        } 
    } // Cat()
    // =====================================================================



    // =====================================================================
    public void draw(World w, Graphics g) {



        // =================================================================
        // Read Images

        if((transformStateCD != 0) && ((w.time - transformStateCD) < 10)) {
            try { 
                rightSprite = ImageIO.read(new File("Graphics/dead-still-right.png"));
                leftSprite = ImageIO.read(new File("Graphics/dead-still-left.png"));
                rightRunSprite1 = ImageIO.read(new File("Graphics/dead-run-right-1.png"));
                rightRunSprite2 = ImageIO.read(new File("Graphics/dead-run-right-2.png"));
                leftRunSprite1 = ImageIO.read(new File("Graphics/dead-run-left-1.png"));
                leftRunSprite2 = ImageIO.read(new File("Graphics/dead-run-left-2.png"));
                rightJumpSprite = ImageIO.read(new File("Graphics/dead-jump-right.png"));
                leftJumpSprite = ImageIO.read(new File("Graphics/dead-jump-left.png"));
                rightLandSprite = ImageIO.read(new File("Graphics/dead-land-right.png"));
                leftLandSprite = ImageIO.read(new File("Graphics/dead-land-left.png"));
            }
            catch (IOException ex) {
                System.out.println("Failed to find image.");
            } 
        }
        else {
            try {                
                rightSprite = ImageIO.read(new File("Graphics/cat-still-right.png"));
                leftSprite = ImageIO.read(new File("Graphics/cat-still-left.png"));
                rightRunSprite1 = ImageIO.read(new File("Graphics/cat-run-right-1.png"));
                rightRunSprite2 = ImageIO.read(new File("Graphics/cat-run-right-2.png"));
                leftRunSprite1 = ImageIO.read(new File("Graphics/cat-run-left-1.png"));
                leftRunSprite2 = ImageIO.read(new File("Graphics/cat-run-left-2.png"));
                rightJumpSprite = ImageIO.read(new File("Graphics/cat-jump-right.png"));
                leftJumpSprite = ImageIO.read(new File("Graphics/cat-jump-left.png"));
                rightLandSprite = ImageIO.read(new File("Graphics/cat-land-right.png"));
                leftLandSprite = ImageIO.read(new File("Graphics/cat-land-left.png"));
                rightHalfHeartSprite = ImageIO.read(new File("Graphics/HalfHeartRight.png"));
                leftHalfHeartSprite = ImageIO.read(new File("Graphics/HalfHeartLeft.png"));
                rightAttackSprite = ImageIO.read(new File("Graphics/attack-right.png"));
                leftAttackSprite = ImageIO.read(new File("Graphics/attack-left.png"));
                state = ImageIO.read(new File("Graphics/changeState.png"));
            } 
            catch (IOException ex) {
                System.out.println("Failed to find image.");
            }
        }
        // =================================================================



        if (orientation.equals("right")) {
            if((catVelocity.x > 0) && ((((w.time % 1) > (0.125)) && ((w.time % 1) < (0.25))) || (((w.time % 1) > (0.375)) && ((w.time % 1) < (0.5))) || (((w.time % 1) > (0.625)) && ((w.time % 1) < (0.75))) || ((w.time % 1) > (0.875))) && (catVelocity.y == 0)) {
                g.drawImage(rightRunSprite1, (int)catPosition.x, (int)catPosition.y, null);
            }
            else if((catVelocity.x > 0) && (((w.time % 1) < (0.125)) || (((w.time % 1) > (0.25)) && ((w.time % 1) < (0.375)))  || (((w.time % 1) > (0.5)) && ((w.time % 1) < (0.625))) || (((w.time % 1) > (0.75)) && ((w.time % 1) < (0.875)))) && (catVelocity.y == 0)) {
                g.drawImage(rightRunSprite2, (int)catPosition.x, (int)catPosition.y, null);
            }
            else if(catVelocity.y < 0) {
                g.drawImage(rightJumpSprite, (int)catPosition.x, (int)catPosition.y, null);
            }
            else if(catVelocity.y > 0) {
                g.drawImage(rightLandSprite, (int)catPosition.x, (int)catPosition.y, null);
            }
            else {
                g.drawImage(rightSprite, (int)catPosition.x, (int)catPosition.y, null);
            }
        }
        if (orientation.equals("left")) {
            if((catVelocity.x < 0) && ((((w.time % 1) > (0.125)) && ((w.time % 1) < (0.25))) || (((w.time % 1) > (0.375)) && ((w.time % 1) < (0.5))) || (((w.time % 1) > (0.625)) && ((w.time % 1) < (0.75))) || ((w.time % 1) > (0.875))) && (catVelocity.y == 0)) {
                g.drawImage(leftRunSprite1, (int)catPosition.x, (int)catPosition.y, null);
            }
            else if((catVelocity.x < 0) && (((w.time % 1) < (0.125)) || (((w.time % 1) > (0.25)) && ((w.time % 1) < (0.375)))  || (((w.time % 1) > (0.5)) && ((w.time % 1) < (0.625))) || (((w.time % 1) > (0.75)) && ((w.time % 1) < (0.875)))) && (catVelocity.y == 0)) {
                g.drawImage(leftRunSprite2, (int)catPosition.x, (int)catPosition.y, null);
            }
            else if(catVelocity.y < 0) {
                g.drawImage(leftJumpSprite, (int)catPosition.x, (int)catPosition.y, null);
            }
            else if(catVelocity.y > 0) {
                g.drawImage(leftLandSprite, (int)catPosition.x, (int)catPosition.y, null);
            }
            else {
                g.drawImage(leftSprite, (int)catPosition.x, (int)catPosition.y, null);
            }
        }
        for (double i = 0; i < lives; i += 0.5) {
            if(i % 1 != 0) g.drawImage(rightHalfHeartSprite, (int)(w.width - (90 - (30 * (i - 0.5)))), 0, null);
            else if (i % 1 == 0) g.drawImage(leftHalfHeartSprite, (int)(w.width - (90 - (30 * i))), 0, null);
        }

        if(isTransformed) {
            g.fillRect((int)catPosition.x, ((int)catPosition.y - 10), 50 - timer, 5);
        }

        if(transformState) {
            g.drawImage(state, (w.width - 60), 40, null);
        }

    } // draw()
    // =====================================================================



    // =====================================================================
    public void update(World w, double time) {
        if (catVelocity.y != 0.0) {
            catVelocity.y = catVelocity.y + (catAcceleration.y * time);
        }

        catPosition.y += catVelocity.y * time;
        for (Platform p : w.currentRoom.platforms) {
            if (catHitbox.topCollision(p.platformHitbox)) {
                catPosition.y = p.platformPosition.y - catDimensions.y;
                catVelocity.y = 0.0;
            }
            if (catHitbox.botCollision(p.platformHitbox)) {
                catPosition.y = p.platformPosition.y + p.platformDimensions.y;
                catVelocity.y = 0.0;
            }
        }

        catPosition.x += catVelocity.x * time;
        for (Platform p : w.currentRoom.platforms) {
            if (catHitbox.leftCollision(p.platformHitbox)) {
                catPosition.x = p.platformPosition.x - catDimensions.x; 
            }
            if (catHitbox.rightCollision(p.platformHitbox)) {
                catPosition.x = p.platformPosition.x + p.platformDimensions.x;
            }
        }

        if (catVelocity.y == 0.0) {
            boolean check = false;
            for (Platform p : w.currentRoom.platforms) {
                if (catHitbox.isOnTopOf(p.platformHitbox)) check = true;
            }
            if (!check) catVelocity.y = 0.01;
        }

        checkOtherCollisions(w, time);
        
        if (catVelocity.x < 0.0) orientation = "left";
        else if (catVelocity.x > 0.0) orientation = "right";

        if(isTransformed) {
            if((w.time % 1) >= (59.0/60)) {
                timer += 5;
            }
        }

        if(w.time > (transformStateCD + 25)) {
            transformState = true;
        }

        if(w.time > (transformStateCD + 10)) {
            isTransformed = false;
        }

        if(w.time > (attackStateCD + 3)) {
            attackState = true;
        }
        
        catHitbox.update(catPosition);
    } // update()
    // =====================================================================



    // =====================================================================
    public void checkOtherCollisions(World w, double time) {
        if (catPosition.y + catDimensions.y >= w.height) {
            catPosition.y = w.height - catDimensions.y; //checks if bottom of cat has touched bottom of world
            catVelocity.y = 0.0;
        }
        else if (catPosition.y <= 0.0) {
            catPosition.y = 0.0; //checks if top of cat has touched top of world
            catVelocity.y = 0.1;
        }
        if (catPosition.x + catDimensions.x >= w.width) {
            catPosition.x = w.width - catDimensions.x; //checks if right side of cat has touched the right side of world
            catVelocity.y = 0.0;
        }
        else if (catPosition.x <= 0.0) {
            catPosition.x = 0.0; //checks if left side of cat has touched the left side of world
        }

        if (w.currentRoom.gas != null && catPosition.y + catDimensions.y >= w.height - w.currentRoom.gas.height && !isTransformed) {
            lives -= 0.5;
            System.out.println("Lives: " + lives);
            catPosition = new Pair(50.0, 615.0);
            w.currentRoom.gas.reset();
        }

        if (w.currentRoom.button != null && catHitbox.anyCollision(w.currentRoom.button.buttonHitbox)) {
            w.currentRoom.button.pressed = true;
            for (Door d : w.currentRoom.doors) {
                d.unlocked = true;
            }
            if (w.currentRoom.gas != null) w.currentRoom.gas.vanish();
        }

        for(Projectile p : w.currentRoom.projectiles) {
            if (p != null && catHitbox.anyCollision(p.projHitbox) && !isTransformed) {
                lives -= 0.5;
                p.hitSomething = true;
            }
        }
        
        for(Collectable c : w.currentRoom.collectables) {
            if (c != null && catHitbox.anyCollision(c.collHitbox) && !c.used) {
                if(lives != 3){
                    lives += 0.5;
                    c.used = true;
                }
            }
        }

        if(w.currentRoom.button != null && w.currentRoom.button.pressed) {
            for (Door d : w.currentRoom.doors) {
                if(catHitbox.anyCollision(d.doorHitbox)) {
                    if(d.doorType == 0){
                        if(w.currentRoom.prev.isRoomZero) {
                            catPosition = new Pair(73, 53);
                            for(Door d2 : w.currentRoom.prev.doors){
                                d2.unlocked = false;
                            }
                            w.currentRoom.prev.button.pressed = false;
                        }
                        else{
                            if(w.currentRoom == w.currentRoom.prev.next1) {
                                catPosition = new Pair(w.currentRoom.prev.doors.get(1).doorPosition.x - catDimensions.x, w.currentRoom.prev.doors.get(1).doorPosition.y);
                                for(Door d2 : w.currentRoom.prev.doors) {
                                    d2.unlocked = false;
                                }
                                w.currentRoom.prev.button.pressed = false;
                            }
                            else if (w.currentRoom == w.currentRoom.prev.next2) {
                                catPosition = new Pair(w.currentRoom.prev.doors.get(2).doorPosition.x - catDimensions.x, w.currentRoom.prev.doors.get(2).doorPosition.y);
                                for(Door d2 : w.currentRoom.prev.doors) {
                                    d2.unlocked = false;
                                }
                                w.currentRoom.prev.button.pressed = false;
                            }
                        }
                        w.currentRoom = w.currentRoom.prev;
                    }
                    else if (d.doorType == 1) {
                        if (w.currentRoom.next1 == null) {
                            w.addRoom(1);
                            catPosition = new Pair(100.0, 620.0);
                            score += 5;
                        }
                        else {
                            w.currentRoom = w.currentRoom.next1;
                            catPosition = new Pair(100.0, 620.0);
                        }
                    }
                    else if (d.doorType == 2) {
                        if (w.currentRoom.next2 == null) {
                            w.addRoom(2);
                            catPosition = new Pair(100.0, 620.0);
                            score += 5;
                        }
                        else {
                            w.currentRoom = w.currentRoom.next2;
                            catPosition = new Pair(100.0, 620.0);
                        }
                    }
                }
            }
        }

    } // checkCollisions()
    // =====================================================================



    // =====================================================================
    public void transformState(double time) {
        transformStateCD = time;
        transformState = false;
    } // transformState()
    // =====================================================================




    public void attackState(double time) {
        attackStateCD = time;
        attackState = false;   
    }


    
    // =====================================================================
    public void shoot(World w) {
        if (orientation == "left" && !isTransformed) {
            w.currentRoom.projectiles.add(new Projectile(new Pair(catPosition.x - leftAttackSprite.getWidth(), catPosition.y), new Pair(-300, 0), leftAttackSprite, attackRange));
        }
        else if (orientation == "right" && !isTransformed) 
        {
            w.currentRoom.projectiles.add(new Projectile(new Pair(catPosition.x + catDimensions.x, catPosition.y), new Pair(300, 0), rightAttackSprite, attackRange));
        }
    } // shoot()
    // =====================================================================



// =========================================================================
} // class Cat
// =========================================================================
