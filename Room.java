// =========================================================================
// IMPORTS

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
// =========================================================================



// =========================================================================
public class Room{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public static int numRooms;
    /*
     * Based off the roomType given through the constructor, a different room will be generated
     * Maybe odds = rooms with 1 door, evens = rooms with 2 doors
     * Different number ranges = different difficulties (i.e. 1-5 is easy rooms, 6-10 is medium, 11-15 is hard, etc.)
     */
    public ArrayList<Platform> platforms; // all of the platforms in the room
    public ArrayList<Door> doors;
    public ArrayList<Projectile> projectiles;
    public ArrayList<Enemy> enemies;
    public ArrayList<Collectable> collectables;
    public Button button;
    public Gas gas;
    public Room prev;
    public Room next1;
    public Room next2;
    public Door door1;
    public Door door2;
    public Door backDoor;
    private BufferedImage wall;
    private BufferedImage line;
    public int difficulty;
    public boolean isRoomZero;
    // =====================================================================



    // =====================================================================
    public Room(Random RNG, int prevRooms){
        projectiles = new ArrayList<Projectile>();
        platforms = new ArrayList<Platform>();
        doors = new ArrayList<Door>();
        enemies = new ArrayList<Enemy>();
        collectables = new ArrayList<Collectable>();
        gas = new Gas();
        difficulty = 1 + ((prevRooms - 2) / 3);
        isRoomZero = false;
        

        try {                
                wall = ImageIO.read(new File("wall.png"));
                line = ImageIO.read(new File("line.png"));
            } 
            catch (IOException ex) {
                System.out.println("Failed to find image.");
            }

        platforms.add(new Platform(0, 675, 11));
        doors.add(new Door(25, 603, 0));

        int firstLength = RNG.nextInt(3) + 1;
        int firstPositionX = RNG.nextInt(1024 - (firstLength * 100));
        if (firstPositionX <= 92) platforms.add(new Platform(firstPositionX, 575, firstLength));
        else platforms.add(new Platform(firstPositionX, 630 - RNG.nextInt(56) , firstLength));

        Platform currentPlatform = platforms.get(1);
        while(!(currentPlatform.platformPosition.y <= 150)){
            int length = RNG.nextInt(3) + 1;
            if (currentPlatform.platformPosition.x >= 1024 - (currentPlatform.platformPosition.x + currentPlatform.platformDimensions.x)){ //more space on left or equal space
                int min = (int)currentPlatform.platformPosition.x - 195 - (length * 100);
                int max = (int)currentPlatform.platformPosition.x - (length * 100);
                if (min < 0) min = 0;
                platforms.add(new Platform(RNG.nextInt(max - min) + min, (int)currentPlatform.platformPosition.y - (RNG.nextInt(100 - 75) + 75), length));
            }
            else{ //more space on right
                int min = (int)currentPlatform.platformPosition.x + (int)currentPlatform.platformDimensions.x;
                int max = (int)currentPlatform.platformPosition.x + (int)currentPlatform.platformDimensions.x + 195;
                if(max + length * 100 > 1024) max = 1024 - length * 100;
                platforms.add(new Platform(RNG.nextInt(max - min) + min, (int)currentPlatform.platformPosition.y - (RNG.nextInt(100 - 75) + 75), length));
            }
            currentPlatform = platforms.get(platforms.size()-1);
        }


        ArrayList<Integer> platformIndexes = new ArrayList<>();
        for(int i = 1; i < platforms.size()-1; i ++){
            platformIndexes.add(i);
        }

        for(int i = 0; i < difficulty; i++){
            if(!platformIndexes.isEmpty()){
                int randomPlatform = platformIndexes.get(RNG.nextInt(platformIndexes.size()));
                if (randomPlatform == 1) {
                    enemies.add(new Enemy((int)(platforms.get(randomPlatform).platformPosition.x + platforms.get(randomPlatform).platformDimensions.x - 39), (int)platforms.get(randomPlatform).platformPosition.y - 35, "left"));
                }
                else if (platforms.get(randomPlatform).platformPosition.x >= 1024 - (platforms.get(randomPlatform).platformPosition.x + platforms.get(randomPlatform).platformDimensions.x)){
                    enemies.add(new Enemy((int)(platforms.get(randomPlatform).platformPosition.x + platforms.get(randomPlatform).platformDimensions.x - 39), (int)platforms.get(randomPlatform).platformPosition.y - 35, "left"));
                }
                else enemies.add(new Enemy((int)(platforms.get(randomPlatform).platformPosition.x), (int)platforms.get(randomPlatform).platformPosition.y - 35, "right"));
                platformIndexes.remove(platformIndexes.indexOf(randomPlatform));
            }
        }

        platformIndexes = new ArrayList<>();
        for(int i = 1; i < platforms.size()-2; i ++){
            platformIndexes.add(i);
        }
        
        int numHearts = RNG.nextInt(3);
        for(int i = 0; i < numHearts; i++){
            if(!platformIndexes.isEmpty()){
                int randomPlatform = platformIndexes.get(RNG.nextInt(platformIndexes.size()));
                collectables.add(new Collectable((int)(platforms.get(randomPlatform).platformPosition.x + platforms.get(randomPlatform).platformDimensions.x/2 - 15), (int)(platforms.get(randomPlatform).platformPosition.y - 30)));
            }
        }
        
        button = new Button(platforms.get(platforms.size()-2).platformPosition.x + platforms.get(platforms.size()-2).platformDimensions.x/2 - 15, platforms.get(platforms.size()-2).platformPosition.y - 15);
        doors.add(new Door (currentPlatform.platformPosition.x + currentPlatform.platformDimensions.x/2 - 33.5, currentPlatform.platformPosition.y - 72, 1));
        
    } // Room()
    // =====================================================================

    public Room(int roomType){
        projectiles = new ArrayList<Projectile>();
        platforms = new ArrayList<Platform>();
        doors = new ArrayList<Door>();
        enemies = new ArrayList<Enemy>();
        collectables = new ArrayList<Collectable>();
        if (roomType == 0){
            try {                
                wall = ImageIO.read(new File("wall-1.png"));
                line = ImageIO.read(new File("line.png"));
            } 
            catch (IOException ex) {
                System.out.println("Failed to find image.");
            }
            platforms.add(new Platform(0, 675, 11));
            platforms.add(new Platform(400, 575, 2));
            platforms.add(new Platform(775, 475, 3));
            platforms.add(new Platform(550, 375, 2));
            platforms.add(new Platform(400, 300, 2));
            platforms.add(new Platform(250, 225, 2));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            enemies.add(new Enemy(974, 445, "left"));
            
            isRoomZero = true;
            System.out.println("Room Type 0 created");
        }
        else if (roomType == 1){
            try {                
                wall = ImageIO.read(new File("wall-2.png"));
                line = ImageIO.read(new File("line.png"));
            } 
            catch (IOException ex) {
                System.out.println("Failed to find image.");
            }
            platforms.add(new Platform(0, 675, 11));
            enemies.add(new Enemy(985, 645, "left"));
            platforms.add(new Platform(400, 575, 2));
            enemies.add(new Enemy(561, 545, "left"));
            platforms.add(new Platform(775, 475, 3));
            enemies.add(new Enemy(985, 445, "left"));
            platforms.add(new Platform(550, 375, 2));
            enemies.add(new Enemy(550, 345, "right"));
            platforms.add(new Platform(400, 300, 2));
            enemies.add(new Enemy(400, 270, "right"));
            platforms.add(new Platform(250, 225, 2));
            enemies.add(new Enemy(250, 195, "right"));
            platforms.add(new Platform(0, 125, 2));
            button = new Button(130, 110);
            doors.add(new Door(25, 53, 1));
            doors.add(new Door (25, 603, 0));
            
            System.out.println("Room Type 1 created");
        }
    }


    // =====================================================================
    public void draw(World w, Graphics g){
        g.drawImage(wall, 0, 0, null);
        if(gas != null && (w.time % 1) > 0.5) {
            g.drawImage(line, 0, w.height - gas.lineHeight - line.getHeight()/2, null);
        }
        for (Platform p : platforms){
            p.draw(g);
        }
        if (button != null) button.draw(w, g);
        for (Enemy e : enemies){
            e.draw(g);
        } 
        for (Door d : doors){
            d.draw(g);
        } 
        for (Projectile p : projectiles){
            if (p != null) p.draw(g);
        }
        for (Collectable c : collectables){
            c.draw(g);
        }

        if((w.time % 1) <= (1.0/60)) {
            for(Enemy e : enemies){
                e.shoot(w);
            }
        }
        if (gas != null) gas.draw(w, g);
    } // draw()
    // =====================================================================



    // =====================================================================
    public void update(World w, double time){
        if (gas != null) gas.update(time);
        for(Projectile p: projectiles){
            if (p != null) p.update(w, time);
        }
        for(Enemy e: enemies){
            e.update(w, time);
        }
    } // update()
    // =====================================================================


    
// =========================================================================
} // class Room
// =========================================================================
