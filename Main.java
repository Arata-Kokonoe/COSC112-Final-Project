// =========================================================================
// IMPORTS

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent; 
// =========================================================================



// =========================================================================
public class Main extends JPanel implements KeyListener, MouseListener{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public boolean play;
    private BufferedImage startScreen;
    World world;
    // =====================================================================



    // =====================================================================
    class Runner implements Runnable{
    // =====================================================================



        // =================================================================
        public void run() {
            while(true){
                if((world.player.lives > 0) && (play == true)) {
                    world.updateWorld(1.0/FPS);
                }
                else if(world.player.lives == 0.0) {
                    world.status = true;
                }
                repaint();
                try{
                    Thread.sleep(1000/FPS);
                    }
                catch(InterruptedException e){}
            }
     
        } // run()
        // =================================================================



    // =====================================================================
    } // class Runner 
    // =====================================================================
    


    // =====================================================================
    boolean jump = true; 
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);
        if (c == 'a'){
            world.player.catVelocity.x = -150;
        }; // moves left
        if (c == 'd'){
            world.player.catVelocity.x = 150;
        }; // moves right
        if (c == ' '){
            System.out.println(world.player.catVelocity.y);
            if ((world.player.catVelocity.y == 0.0) && (jump == true)){
                jump = false;
                world.player.catVelocity.y = -250.0;
            }
        }; //jumps
        if (c == 'j'){
            world.player.shoot(world);
        }; // attacks
        if (c == 'k'){
            if(world.player.state == true) {
                world.player.state(world.time);
            }
        }; // switches between dead and alive
        if (c == 'm'){}; // opens map
        if (c == 'p'){}; // opens the menus; change later to Esc if possible
    } // keyPressed()
    // =====================================================================



    // =====================================================================
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'a' && world.player.catVelocity.x == -150){
            world.player.catVelocity.x = 0.0;
        }; // stops movement
        if (c == 'd' && world.player.catVelocity.x == 150){
            world.player.catVelocity.x = 0.0;
        }; // stops movement
        if(c == ' ') {
            jump = true;
        }
    } // keyReleased()
    // =====================================================================
 


    // =====================================================================
    public void keyTyped(KeyEvent e) {
    	char c = e.getKeyChar();
    } // keyTyped()
    // =====================================================================



    // =====================================================================
    public void mousePressed(MouseEvent e) { 
        if(((e.getX() > 370) && (e.getX() < 647)) && ((e.getY() > 432) && (e.getY() < 535))) {
            play = true;
        }
    }
    // mousePressed()
    // =====================================================================



    // =====================================================================
    public void mouseReleased(MouseEvent e) {}
    // mouseReleased()
    // =====================================================================



    // =====================================================================
    public void mouseExited(MouseEvent e) {}
    // mouseExcited()
    // =====================================================================



    // =====================================================================
    public void mouseEntered(MouseEvent e) {}
    // mouseEntered()
    // =====================================================================



    // =====================================================================
    public void mouseClicked(MouseEvent e) {}
    // mouseClicked()
    // =====================================================================
    


    // =====================================================================
    public void addNotify() {
        super.addNotify();
        requestFocus();
    } // addNotify()
    // =====================================================================



    // =====================================================================
    /**
     * The specialized constructor.
     **/
    public Main(){
        world = new World(WIDTH, HEIGHT);
        addKeyListener(this);
        addMouseListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    } // Main()
    // =====================================================================



    // =====================================================================
    public static void main(String[] args) {
        JFrame frame = new JFrame("Milton Escapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);

        frame.pack();
        frame.setVisible(true);
    } // main()
    // =====================================================================



    // =====================================================================
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
     
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        try {
            startScreen = ImageIO.read(new File("start.png"));
        }
        catch (IOException ex) {
            System.out.println("Failed to find image.");
        } 
        
        g.drawImage(startScreen, 0, 0, null);

        if(play) {
            world.drawWorld(g); 
        }
    } // paintCompnent()
    // =====================================================================



// =========================================================================
} // class MiltonGame
// =========================================================================
