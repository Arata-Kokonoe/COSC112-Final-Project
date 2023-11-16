import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Cat implements canShoot{
    public double lives;
    public boolean state;
    public int experience;
    public double positionX;
    public double positionY;
    public double velocityX;
    public double velocityY;
    public double accelerationX;
    public double accelerationY;
    public double stateCD;
    public int width;
    public int height;
    private BufferedImage sprite;

    public Cat(){
        positionX = 500;
        positionY = 500;
        lives = 9;
        width = 60;
        height = 35;

        try {                
            sprite = ImageIO.read(new File("SmallNyanCat.png"));
        } 
        catch (IOException ex) {
        }

        accelerationY = 200.0;
    }

    public void draw(Graphics g){
        g.drawImage(sprite, (int)positionX, (int)positionY, null);
    }

    public void update(World w, double time){
        positionX = (positionX + (velocityX * time));
        positionY = (positionY + (velocityY * time));
        velocityX = velocityX + (accelerationX * time);
        velocityY = velocityY + (accelerationY * time);
        this.check(w);
    }

    public void check(World w){
        if (positionY + height > w.height) positionY = w.height - height; //checks if bottom of cat has touched bottom of world
        else if (positionY < 0) positionY = 0; //checks if top of cat has touched top of world
        if (positionX + width > w.width) positionX = w.width - width; //checks if right side of cat has touched the right side of world
        else if (positionX < 0) positionX = 0; //checks if left side of cat has touched the left side of world
        for (Platform p : w.currentRoom.current.platforms){
            //write code to check if the cat is touching the platforms
            //i think we should create a hitbox class that holds the x and y values of each side of an entity
            //then make it have a touch method that checks if two hitboxes are "touching"
        }
    }

    public void shoot(){

    }
}
