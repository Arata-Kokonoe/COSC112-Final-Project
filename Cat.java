import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Cat implements canShoot{
    public double lives;
    public boolean state;
    public int experience;
    public int positionX;
    public int positionY;
    public double velocityX;
    public double velocityY;
    public double accelerationX;
    public double accelerationY;
    public double stateCD;
    public int width;
    public int height;
    public Image sprite = ImageIO.read(new File("SmallNyanCat.png"));

    public Cat(){
        positionX = 500;
        positionY = 500;
        lives = 9;
        width = 60;
        height = 35;
    }

    public void draw(Graphics g){
        g.drawImage(sprite, positionX, positionY, null);
    }

    public void update(double time){
        positionX = (int)(positionX + (velocityX * time));
        positionY = (int)(positionX + (velocityY * time));
        velocityX = velocityX + (accelerationX * time);
        velocityY = velocityY + (accelerationY * time);
    }

    public void shoot(){

    }
}
