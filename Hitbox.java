public class Hitbox {
    public int hitboxWidth;
    public int hitboxHeight;
    public int hitboxTop;
    public int hitboxLeft;
    public int hitboxRight;
    public int hitboxBot;
    public int buffer = 3;

    public Hitbox(Pair dimensions, Pair position){
        hitboxWidth = (int)dimensions.x;
        hitboxHeight = (int)dimensions.y;
        hitboxLeft = (int)position.x;
        hitboxRight = (int)(position.x + dimensions.x);
        hitboxTop = (int)position.y;
        hitboxBot = (int)(position.y + dimensions.y);
    }

    public boolean topCollision(Hitbox other){
        if ((hitboxBot > other.hitboxTop) && (hitboxBot < other.hitboxBot) && ((hitboxRight > other.hitboxLeft && hitboxRight < other.hitboxRight) || (hitboxLeft > other.hitboxLeft && hitboxLeft < other.hitboxRight))){
            return true;
        }
        else return false;
    } 

    public boolean leftCollision(Hitbox other){
        if ((hitboxRight >= other.hitboxLeft) && (hitboxRight <= other.hitboxRight) && (!this.isOnTopOf(other)) && (!this.isOnBotOf(other)) && ((hitboxTop > other.hitboxTop && hitboxTop < other.hitboxBot) || (hitboxBot > other.hitboxTop && hitboxBot < other.hitboxBot))){
            return true;
        }
        else return false;
    }

    public boolean rightCollision(Hitbox other){
        if ((hitboxLeft <= other.hitboxRight) && (hitboxLeft >= other.hitboxLeft) && (!this.isOnTopOf(other)) && (!this.isOnBotOf(other)) && ((hitboxTop > other.hitboxTop && hitboxTop < other.hitboxBot) || (hitboxBot > other.hitboxTop && hitboxBot < other.hitboxBot))){
            System.out.println("Cat left: \t" + hitboxLeft + "\nCat right: \t" + hitboxRight + "\nCat top: \t" + hitboxTop + "\nCat bot: \t" + hitboxBot);
            System.out.println("Platform left: \t" + other.hitboxLeft + "\nPlatform right:\t" + other.hitboxRight + "\nPlatform top: \t" + other.hitboxTop + "\nPlatform bot: \t" + other.hitboxBot);
            return true;
        }
        else return false;
        
    }

    public boolean botCollision(Hitbox other){
        if ((hitboxTop < other.hitboxBot) && (hitboxTop > other.hitboxTop) && ((hitboxRight > other.hitboxLeft && hitboxRight < other.hitboxRight) || (hitboxLeft > other.hitboxLeft && hitboxLeft < other.hitboxRight))){
            System.out.println("Cat left: \t" + hitboxLeft + "\nCat right: \t" + hitboxRight + "\nCat top: \t" + hitboxTop + "\nCat bot: \t" + hitboxBot);
            System.out.println("Platform left: \t" + other.hitboxLeft + "\nPlatform right:\t" + other.hitboxRight + "\nPlatform top: \t" + other.hitboxTop + "\nPlatform bot: \t" + other.hitboxBot);
            return true;
        }
        else return false;
    }
    
    private boolean isOnTopOf(Hitbox other){
        if ((hitboxBot >= other.hitboxTop - buffer) && (hitboxBot < other.hitboxBot) && ((hitboxRight > other.hitboxLeft && hitboxRight < other.hitboxRight) && (hitboxLeft > other.hitboxLeft && hitboxLeft < other.hitboxRight))){
            return true;
        }
        else return false;
    }

    public boolean isOnTopOfForgiving(Hitbox other){
        if ((hitboxBot >= other.hitboxTop - buffer) && (hitboxBot < other.hitboxBot) && ((hitboxRight > other.hitboxLeft && hitboxRight < other.hitboxRight) || (hitboxLeft > other.hitboxLeft && hitboxLeft < other.hitboxRight))){
            return true;
        }
        else return false;
    }

    private boolean isOnBotOf(Hitbox other){
        if ((hitboxTop <= other.hitboxBot + buffer) && (hitboxTop > other.hitboxTop) && ((hitboxRight > other.hitboxLeft && hitboxRight < other.hitboxRight) && (hitboxLeft > other.hitboxLeft && hitboxLeft < other.hitboxRight))){
            return true;
        }
        else return false;
    }

    public void update(Pair position){
        hitboxLeft = (int)position.x;
        hitboxRight = (int)position.x + hitboxWidth;
        hitboxTop = (int)position.y;
        hitboxBot = (int)position.y + hitboxHeight;
    }
}

/*
 * System.out.println("Cat left: \t" + hitboxLeft + "\nCat right: \t" + hitboxRight + "\nCat top: \t" + hitboxTop + "\nCat bot: \t" + hitboxBot);
 * System.out.println("Platform left: \t" + other.hitboxLeft + "\nPlatform right:\t" + other.hitboxRight + "\nPlatform top: \t" + other.hitboxTop + "\nPlatform bot: \t" + other.hitboxBot);
 */