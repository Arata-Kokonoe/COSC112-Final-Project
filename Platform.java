import java.awt.Graphics;
public class Platform{
    public static final int HEIGHT = 25;
    public int width;
    public double x;
    public double y;

    public Platform(double x, double y, int width){
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void draw(Graphics g){
        g.fillRect((int)x, (int)y, HEIGHT, width);
    }
}
