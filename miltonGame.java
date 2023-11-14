class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Physics!!!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    KeyboardSpheres mainInstance = new KeyboardSpheres();
    frame.setContentPane(mainInstance);
    frame.pack();
    frame.setVisible(true);
    }
}
