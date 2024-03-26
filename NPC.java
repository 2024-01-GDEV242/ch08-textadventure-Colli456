
/**
 * Write a description of class NPC here.
 *
 * @author  Collie Clarke
 * @version 2024.03.11
 */
public class NPC extends Player
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;

    /**
     * Constructor for objects of class NPC
     */
    public NPC(String name, String description, String itemname, String itemdesc)
    {
        this.description = description;
        this.name = name;
        addInventory(itemname, itemdesc);
    }

    /**
     * @return NPC description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return NPC Name
     */
    public String getName()
    {
        return name;
    }
}
