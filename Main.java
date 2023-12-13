// =========================================================================
// IMPORTS

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent; 
// =========================================================================



// =========================================================================
public class Main extends JPanel implements KeyListener{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;
    // =====================================================================



    // =====================================================================
    class Runner implements Runnable{
    // =====================================================================



        // =================================================================
        public void run() {
            while(true){
                world.updateWorld(1.0/FPS);
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
    boolean attack = true;
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
            if (world.player.attackState == true && attack == true) {
                attack = false;
                world.player.attackState(world.time);
                world.player.shoot(world);
            }
        }; // attacks
        if (c == 'k'){
            if(world.player.transformState == true) {
                world.player.transformState(world.time);
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
        if(c == 'j'){
            attack = true;
        }
    } // keyReleased()
    // =====================================================================
 


    // =====================================================================
    public void keyTyped(KeyEvent e) {
    	char c = e.getKeyChar();
    } // keyTyped()
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
     
        world.drawWorld(g); 
    } // paintCompnent()
    // =====================================================================



// =========================================================================
} // class MiltonGame
// =========================================================================
