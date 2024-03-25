
/**
 * Write a description of class NPC here.
 *
 * @author  Collie Clarke
 * @version 2024.03.11
 */
public class NPC extends Player
{
    // instance variables - replace the example below with your own
    private String nName;
    private String nDescription;

    /**
     * Constructor for objects of class NPC
     */
    public NPC(String name, String description)
    {
        this.nDescription = description;
        this.nName = name;
    }

    /**
     * @return NPC description
     */
    public String getDescription()
    {
        return nDescription;
    }
    
    /**
     * @return NPC Name
     */
    public String getName()
    {
        return nName;
    }
}
