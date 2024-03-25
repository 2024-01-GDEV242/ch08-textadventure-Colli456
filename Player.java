import java.util.Set;
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
    private HashMap<String, Items> inventory;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = new Room("starting room");
        inventory = new HashMap<String, Items>();
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
    
    /**
     * Iterates through the inventory, adding all items
     * to a String, then returns it.
     */
    public String getInventoryString()
    {
        String returnString = "Inventory:";
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            returnString += " " + item;
        return returnString;
    }
    
    /**
     * Checks the player's inventory to see if we have a key.
     */
    public boolean checkKey()
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys)
            if (item.equals("key"))
                return true;
        return false;
    }
    
    /**
     * Adds the item of name and description to the inventory.
     */
    public void addInventory(String name, String description)
    {
        Set<String> keys = inventory.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                System.out.println("We've already got one!");
                return;
            }
        }
        Items newItems = new Items(description, name);
        inventory.put(name, newItems);
    }
    
    public void addInventory(Items item)
    {
        inventory.put(item.getName(), item);
    }
}
