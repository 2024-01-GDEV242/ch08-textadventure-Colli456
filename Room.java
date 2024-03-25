import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Collie Clarke
 * @version 2024.03.11
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<String, Items> items;       // stores items of this room.
    private HashMap<String, NPC> npcs;
    private HashMap<String, Door> doors;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashMap<String, Items>();
        npcs = new HashMap<String, NPC>();
        doors = new HashMap<String, Door>();
    }
    
    /**
     * Adds an NPC described by a name/desc.
     */
    public void addNPC(String name, String description)
    {     
        NPC newNPC = new NPC(name, description);
        npcs.put(name, newNPC);
    }
    
     /**
     * Adds an item to the items list, if it's there already just don't add it.
     * @param iname - The name of the item.
     * @param description - The description of the item.
     */
    public void setItem(String iname, String description)
    {
        Items newItem = new Items(description, iname);
        items.put(iname, newItem);
    }
    
    public Items delItem(String name)
    {
        Set<String> keys = items.keySet();
        for(String item : keys) {
            if (item.equals(name))
            {
                Items temp = items.get(name);
                items.remove(name);
                return temp;
            }
        }
        System.out.println("That isn't here");
        return null;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Adds an item to the room when it is created
     */
    public void addItem(Items item)
    {
        addItem(item); 
    }
    
    /**
     * Defines a lockable exit, or door, for the room.
     */
    public void setDoor(String direction, Room neighbor, boolean locked)
    {
        Door temp = new Door(direction, neighbor, locked);
        doors.put(direction, temp);
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public Room getDoor(String direction)
    {
        Door tempDoor = doors.get(direction);
        if (tempDoor != null)
        {
            return tempDoor.getNeighbor();
        }
        
        return null;
    }
    
    public boolean getLocked(String direction)
    {
        return doors.get(direction).getLocked();
    }
    
    public Door getActualDoor(String direction)
    {
        return doors.get(direction);
    }
    
    public Items getItem(String name)
    {
        return items.get(name);
    }
    
    public NPC getNPC()
    {
        return npcs.entrySet().iterator().next().getValue();
    }
}

