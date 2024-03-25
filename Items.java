
/**
 * Item.java. Holds information about each item in the game.
 * 
 * @author  Collie Clarke
 * @version 2024.03.11
 */
public class Items
{
    // instance variables - replace the example below with your own
    private String iDescription;
    private String iName;
    //private int weight;

    /**
     * Constructor for objects of class Items
     */
    public Items(String iDescription, String iName)
    {
        this.iDescription = iDescription;
        this.iName = iName;      
        //this.weight = weight;
    }
    
    /**
     * Return the description of the item
     */
    public String getShortDescription()
    {
        return iDescription;
    }
    
    /**
     * Return the name of the item
     */
    public String getName()
    {
        return iName; 
    }
    //**
    //* Return the weight of the item 
    //*/
    //public int weight()
    //{
    //    return weight;
    //}
    
    public void addItem(String description,int weight)
    {
        //items.put(new Items (description, weight));
    }
}
