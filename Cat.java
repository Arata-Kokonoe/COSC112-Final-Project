import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Cat implements canShoot{
    public double lives;
    public boolean state;
    public int experience;
    public Pair catPosition;
    public Pair catVelocity;
    public Pair catAcceleration;
    public double stateCD;
    public Pair catDimensions;
    private BufferedImage sprite;
    public Hitbox catHitbox;

    public Cat(){
        catPosition = new Pair(50.0, 50.0);
        catVelocity = new Pair(0.0, 0.01);
        catAcceleration = new Pair(0.0, 200.0);
        catDimensions = new Pair(65.0, 40.0);
        catHitbox = new Hitbox(catDimensions, catPosition);

        try {                
            sprite = ImageIO.read(new File("NyanCat.png"));
        } 
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        }
    }

    public void draw(Graphics g){
        g.drawImage(sprite, (int)catPosition.x, (int)catPosition.y, null);
    }

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
        catHitbox.update(catPosition);
    }

    public boolean checkCollisions(World w){
        boolean collision = false;
        if (catPosition.y + catDimensions.y >= w.height){
            catPosition.y = w.height - catDimensions.y; //checks if bottom of cat has touched bottom of world
            catVelocity.y = 0.0;
            collision = true;
        }
        else if (catPosition.y <= 0.0){
            catPosition.y = 0.0; //checks if top of cat has touched top of world
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
                catPosition.x = p.platformPosition.x - catDimensions.x;
                System.out.println("Collided with left of platform");
                collision = true;
            }
            else if (catHitbox.rightCollision(p.platformHitbox)){
                catPosition.x = p.platformPosition.x + p.platformDimensions.x;
                System.out.println("Collided with right of platform");
                collision = true;
            }
            else if (catHitbox.topCollision(p.platformHitbox)){
                catPosition.y = p.platformPosition.y - catDimensions.y;
                catVelocity.y = 0.0;
                collision = true;
                //System.out.println("Collided with top of platform");
            }
            else if (catHitbox.botCollision(p.platformHitbox)){
                System.out.println("Cat Position: " + catPosition.x + ", " + catPosition.y);
                catPosition.y = p.platformPosition.y + p.platformDimensions.y + 1;
                catVelocity.y = 0.0;
                collision = true;
            }
            /*
            if (((int)(positionX + width) >= (int)p.x - 3 && (int)(positionX + width) <= (int)p.x + 3) && (((int)positionY >= p.y && (int)positionY <= p.y + Platform.HEIGHT) || ((int)(positionY + height) >= p.y && (int)(positionY + height) <= p.y + Platform.HEIGHT))){
                positionX = p.x - width - 3;
                touching = true;
                //System.out.println("Cat hit the left side of a platform");
            }
            else if (((int)positionX <= (int)(p.x + p.width) + 3 && (int)positionX >= (int)(p.x + p.width) - 3) && ((positionY >= p.y && positionY <= p.y + Platform.HEIGHT) || (positionY + height >= p.y && positionY + height <= p.y + Platform.HEIGHT))){
                positionX = p.x + p.width + 3;
                touching = true;
                //System.out.println("Cat hit the right side of a platform");
            }
            /*d
             * if ((catRight == platformLeft) && ((catTop >= platformTop && catTop <= platformBot) || (ballBot >= platformTop && catBot <= platformBot)))
            else if (positionY + height >= p.y && positionX + width >= p.x && positionX <= p.x + p.width && !(positionY >= p.y + Platform.HEIGHT)){
                if (!(positionY > p.y)){
                    System.out.println(positionY + " " + (positionY + height) + " " + positionX + " " + (positionX + width) + " " + p.y + " " + (p.y + Platform.HEIGHT) + " " + p.x + " " + (p.x + p.width));
                    positionY = p.y - height;
                    velocityY = 0.0;
                    touching = true;
                    //System.out.println("Cat hit the top of a platform");
                }
                else if (positionY <= p.y + Platform.HEIGHT){
                    System.out.println(positionY + " " + (positionY + height) + " " + positionX + " " + (positionX + width) + " " + p.y + " " + (p.y + Platform.HEIGHT) + " " + p.x + " " + (p.x + p.width));
                    positionY = p.y + Platform.HEIGHT;
                    velocityY = 0.01;
                    touching = true;
                    //System.out.println("Cat hit the bottom of a platform");
                }
            } */
            //write code to check if the cat is touching the platforms
            //i think we should create a hitbox class that holds the x and y values of each side of an entity
            //then make it have a touch method that checks if two hitboxes are "touching" 
        }
        /*if (positionY + height >= w.height - w.currentRoom.current.gas.height) {
            lives--;
            System.out.println("Lives: " + lives);
            positionX = 50;
            positionY = 615;
            w.currentRoom.current.gas
        }*/
        return collision;
    }

    public void shoot(){

    }
}
