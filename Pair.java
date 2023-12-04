// =========================================================================
public class Pair {
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public double x;
    public double y;
    // =====================================================================
    


    // =====================================================================
    public Pair(double initX, double initY){
    	x = initX;
    	y = initY;
    } // Pair()
    // =====================================================================
 


    // =====================================================================
    public Pair add(Pair toAdd){
    	return new Pair(x + toAdd.x, y + toAdd.y);
    } // add()
    // =====================================================================



    // =====================================================================
    public Pair addX(double x){
        return new Pair(this.x + x, y);
    } // addX()
    // =====================================================================



    // =====================================================================
    public Pair addY(double y){
        return new Pair(x, this.y + y);
    } // addY()
    // =====================================================================


 
    // =====================================================================
    public Pair divide(double denom){
    	return new Pair(x / denom, y / denom);
    } // divide()
    // =====================================================================



    // =====================================================================
    public Pair times(double val){
    	return new Pair(x * val, y * val);
    } // times()
    // =====================================================================



    // =====================================================================
    public void flipX(){
    	x = -x;
    } // flipX()
    // =====================================================================



    // ===================================================================== 
    public void flipY(){
    	y = -y;
    } // flipY()
    // =====================================================================



// =========================================================================
} // class Pair
// =========================================================================
