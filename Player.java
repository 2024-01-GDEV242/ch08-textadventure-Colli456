import java.util.HashMap;

/**
 * Write a description of class Player here.
 *
 * @author  Collie Clarke
 * @version 2024.03.11
 */
public class Player
{
    // instance variables
    private Room currentRoom;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = new Room("starting room");
    }

    /**
     * returns the current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room newRoom)
    {
        currentRoom = newRoom; 
    }
}
