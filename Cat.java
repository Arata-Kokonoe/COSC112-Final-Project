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
    }

    public void draw(Graphics g){
        g.drawImage(sprite, (int)positionX, (int)positionY, null);
    }

    public void update(double time){
        positionX = (positionX + (velocityX * time));
        positionY = (positionY + (velocityY * time));
        velocityX = velocityX + (accelerationX * time);
        velocityY = velocityY + (accelerationY * time);
    }

    public void shoot(){

    }
}
