// =========================================================================
// IMPORTS

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
// =========================================================================



// =========================================================================
public class World{
// =========================================================================



    // =====================================================================
    // DATA MEMBERS

    public int width;
    public int height;
    public double time;
    public Room firstRoom;
    public Room currentRoom;
    public Cat player;
    public Map map;
    public ArrayList<Integer> roomTypes;
    public final Random RNG = new Random(12345); //find a way to change 12345 into a random seed (AKA key)
    // =====================================================================



    // =====================================================================
    public World(int width, int height){
        this.width = width;
        this.height = height;
        player = new Cat();

        //firstRoom = new Node(new Room(RNG.nextInt(5))); replace whats inside .nextInt() with however many rooms we design
        firstRoom = new Room(0); //temporary to test just one room
        currentRoom = firstRoom;

        roomTypes = new ArrayList<Integer>();
        for (int i = 1; i <= 15; i++){
            roomTypes.add(i);
        }

    } // World()
    // =====================================================================



    // =====================================================================
    public void drawWorld(Graphics g){
        //map.draw(g);
        currentRoom.draw(this, g);
        player.draw(this, g);
    } // drawWorld()
    // =====================================================================



    // =====================================================================
    public void updateWorld(double time){
        currentRoom.update(this, time);
        player.update(this, time);
        this.time += time;
    } // updateWorld()
    // =====================================================================

    public void addRoom(int doorNum){
        if (doorNum == 1){
            if (countRooms() < 4) {
                boolean isUsed = true;
                int newRoomType;
                do{
                    newRoomType = RNG.nextInt(5) + 1;
                    for (Integer n : roomTypes){
                        if ((int)n == newRoomType) isUsed = false;
                    }
                } while(isUsed);

                currentRoom.next1 = new Room(newRoomType);
                currentRoom.next1.prev = currentRoom;
                roomTypes.remove(roomTypes.indexOf(newRoomType));
                currentRoom = currentRoom.next1;
            }
            else if (countRooms() < 7) {
                boolean isUsed = true;
                int newRoomType;
                do{
                    newRoomType = RNG.nextInt(5) + 6;
                    for (Integer n : roomTypes){
                        if ((int)n == newRoomType) isUsed = false;
                    }
                } while(isUsed);

                currentRoom.next1 = new Room(newRoomType);
                currentRoom.next1.prev = currentRoom;
                roomTypes.remove(roomTypes.indexOf(newRoomType));
                currentRoom = currentRoom.next1;
            }
            else if (countRooms() < 10) {
                boolean isUsed = true;
                int newRoomType;
                do{
                    newRoomType = RNG.nextInt(5) + 11;
                    for (Integer n : roomTypes){
                        if ((int)n == newRoomType) isUsed = false;
                    }
                } while(isUsed);

                currentRoom.next1 = new Room(newRoomType);
                currentRoom.next1.prev = currentRoom;
                roomTypes.remove(roomTypes.indexOf(newRoomType));
                currentRoom = currentRoom.next1;
            }
        }
        else if (doorNum == 2){
            if (countRooms() < 4) {
                boolean isUsed = true;
                int newRoomType;
                do{
                    newRoomType = RNG.nextInt(5) + 1;
                    for (Integer n : roomTypes){
                        if ((int)n == newRoomType) isUsed = false;
                    }
                } while(isUsed);

                currentRoom.next2 = new Room(newRoomType);
                currentRoom.next2.prev = currentRoom;
                roomTypes.remove(roomTypes.indexOf(newRoomType));
                currentRoom = currentRoom.next2;
            }
            else if (countRooms() < 7) {
                boolean isUsed = true;
                int newRoomType;
                do{
                    newRoomType = RNG.nextInt(5) + 6;
                    for (Integer n : roomTypes){
                        if ((int)n == newRoomType) isUsed = false;
                    }
                } while(isUsed);

                currentRoom.next2 = new Room(newRoomType);
                currentRoom.next2.prev = currentRoom;
                roomTypes.remove(roomTypes.indexOf(newRoomType));
                currentRoom = currentRoom.next2;
            }
            else if (countRooms() < 10) {
                boolean isUsed = true;
                int newRoomType;
                do{
                    newRoomType = RNG.nextInt(5) + 11;
                    for (Integer n : roomTypes){
                        if ((int)n == newRoomType) isUsed = false;
                    }
                } while(isUsed);

                currentRoom.next2 = new Room(newRoomType);
                currentRoom.next2.prev = currentRoom;
                roomTypes.remove(roomTypes.indexOf(newRoomType));
                currentRoom = currentRoom.next2;
            }
        }
    }

    // =====================================================================
    public int countRooms(){
        int count = 1;
        ArrayList<Room> toConsiderList = new ArrayList<Room>();
        boolean isCurrentRoom = false;
        if (firstRoom == currentRoom) isCurrentRoom = true;
        toConsiderList.add(currentRoom);
        while(!isCurrentRoom){
            Room popped = toConsiderList.remove(toConsiderList.size()-1);
            count++;
            if (popped.next1 != null) toConsiderList.add(popped.next1);
            if (popped.next2 != null) toConsiderList.add(popped.next2);
            if (popped == currentRoom) isCurrentRoom = true;
        }
        return count;
    }


// =========================================================================
} // class World
// =========================================================================
