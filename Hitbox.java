// =========================================================================
public class Hitbox {
// =========================================================================


    // =====================================================================
    // DATA MEMBERS

    public int hitboxWidth;
    public int hitboxHeight;
    public int hitboxTop;
    public int hitboxLeft;
    public int hitboxRight;
    public int hitboxBot;
    public int buffer = 3;
    // =====================================================================



    // =====================================================================
    public Hitbox(Pair dimensions, Pair position){
        hitboxWidth = (int)dimensions.x;
        hitboxHeight = (int)dimensions.y;
        hitboxLeft = (int)position.x;
        hitboxRight = (int)(position.x + dimensions.x);
        hitboxTop = (int)position.y;
        hitboxBot = (int)(position.y + dimensions.y);
    } // Hitbox()
    // =====================================================================



    // =====================================================================
    public boolean topCollision(Hitbox other){
        double b_collision = other.hitboxBot - hitboxTop;
        double t_collision = hitboxBot - other.hitboxTop;
        double l_collision = hitboxRight - other.hitboxLeft;
        double r_collision = other.hitboxRight - hitboxLeft;
        if (anyCollision(other) && (t_collision < b_collision && t_collision < l_collision && t_collision < r_collision)){
            return true;
        }

        else return false;
    } // topCollision()
    // taken from https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision
    // =====================================================================



    // =====================================================================
    public boolean leftCollision(Hitbox other){
        double b_collision = other.hitboxBot - hitboxTop;
        double t_collision = hitboxBot - other.hitboxTop;
        double l_collision = hitboxRight - other.hitboxLeft;
        double r_collision = other.hitboxRight - hitboxLeft;
        if (anyCollision(other) && (l_collision < t_collision && l_collision < b_collision && l_collision < r_collision)){
            return true;
        }

        else return false;
    } // leftCollision()
    // taken from https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision
    // =====================================================================



    // =====================================================================
    public boolean rightCollision(Hitbox other){
        double b_collision = other.hitboxBot - hitboxTop;
        double t_collision = hitboxBot - other.hitboxTop;
        double l_collision = hitboxRight - other.hitboxLeft;
        double r_collision = other.hitboxRight - hitboxLeft;
        if (anyCollision(other) && (r_collision < t_collision && r_collision < l_collision && r_collision < b_collision)){
            return true;
        }

        else return false;
    } // rightCollision()
    // taken from https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision
    // =====================================================================



    // =====================================================================
    public boolean botCollision(Hitbox other){
        double b_collision = other.hitboxBot - hitboxTop;
        double t_collision = hitboxBot - other.hitboxTop;
        double l_collision = hitboxRight - other.hitboxLeft;
        double r_collision = other.hitboxRight - hitboxLeft;
        if (anyCollision(other) && (b_collision < t_collision && b_collision < l_collision && b_collision < r_collision)){
            return true;
        }
        
        else return false;
    } // botCollision()
    // taken form https://stackoverflow.com/questions/5062833/detecting-the-direction-of-a-collision
    // =====================================================================



    // =====================================================================
    public boolean isOnTopOf(Hitbox other){
        if ((hitboxBot >= other.hitboxTop - buffer) && (hitboxBot < other.hitboxBot) && ((hitboxRight > other.hitboxLeft && hitboxRight < other.hitboxRight) || (hitboxLeft > other.hitboxLeft && hitboxLeft < other.hitboxRight))){
            return true;
        }
        else return false;
    } // isOnTopOfForgiving
    // =====================================================================



    // =====================================================================
    public boolean anyCollision(Hitbox other){
        if (hitboxLeft < other.hitboxRight && hitboxRight > other.hitboxLeft && hitboxTop < other.hitboxBot && hitboxBot > other.hitboxTop){
            return true;
        }
        else return false;
    } // anyCollision()
    // =====================================================================



    // =====================================================================
    public void update(Pair position){
        hitboxLeft = (int)position.x;
        hitboxRight = (int)position.x + hitboxWidth;
        hitboxTop = (int)position.y;
        hitboxBot = (int)position.y + hitboxHeight;
    } // update()
    // =====================================================================



// =========================================================================
} // class Hitbox
// =========================================================================

/*
 * System.out.println("Cat left: \t" + hitboxLeft + "\nCat right: \t" + hitboxRight + "\nCat top: \t" + hitboxTop + "\nCat bot: \t" + hitboxBot);
 * System.out.println("Platform left: \t" + other.hitboxLeft + "\nPlatform right:\t" + other.hitboxRight + "\nPlatform top: \t" + other.hitboxTop + "\nPlatform bot: \t" + other.hitboxBot);
 */