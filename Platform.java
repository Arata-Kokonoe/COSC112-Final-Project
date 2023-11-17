import java.awt.Graphics;
import java.awt.Color;

public class Platform{
    public Pair platformDimensions;
    public Pair platformPosition;
    public Hitbox platformHitbox;
    public final Color COLOR = Color.WHITE;

    public Platform(double x, double y, int width){
        platformDimensions = new Pair(width, 40);
        platformPosition = new Pair(x, y);
        platformHitbox = new Hitbox(platformDimensions, platformPosition);
    }

    public void draw(Graphics g){
        g.setColor(COLOR);
        g.fillRect((int)platformPosition.x, (int)platformPosition.y, (int)platformDimensions.x, (int)platformDimensions.y);
    }
}
