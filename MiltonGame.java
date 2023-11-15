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
public class MiltonGame extends JPanel implements KeyListener{
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
                world.updateWorld(FPS);
                repaint();
            try{
                Thread.sleep(1000/FPS);
                }
            catch(InterruptedException e){}
            }
     
        } // run ()
        // =================================================================



    // =====================================================================
    } // class Runner 
    // =====================================================================
    


    // =====================================================================
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);
        if (c == 'a'){}; // moves left
        if (c == 'd'){}; // moves right
        if (c == ' '){}; // jumps
        if (c == 'j'){}; // attacks
        if (c == 'k'){}; // switches between dead and alive
        if (c == 'm'){}; // opens map
        if (c == 'p'){}; // opens the menus; change later to Esc if possible
    } // keyPressed ()
    // =====================================================================



    // =====================================================================
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
    } // keyReleased ()
    // =====================================================================
 


    // =====================================================================
    public void keyTyped(KeyEvent e) {
    	char c = e.getKeyChar();
    } // keyTyped ()
    // =====================================================================
    


    // =====================================================================
    public void addNotify() {
        super.addNotify();
        requestFocus();
    } // addNotify ()
    // =====================================================================



    // =====================================================================
    /**
     * The specialized constructor.
     **/
    public MiltonGame(){
        world = new World(WIDTH, HEIGHT);
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    } // MiltonGame ()
    // =====================================================================



    // =====================================================================
    public static void main(String[] args) {
        JFrame frame = new JFrame("New Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miltonGame mainInstance = new miltonGame();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    } // main ()
    // =====================================================================



    // =====================================================================
    public void paintComponent(Graphics g) {
        super.paintComponent(g);        
     
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
     
        world.drawWorld(g); 
    } // paintCompnent ()
    // =====================================================================



// =========================================================================
} // class MiltonGame
// =========================================================================
