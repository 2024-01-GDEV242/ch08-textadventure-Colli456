
/**
 * Exit.java. Stores information on exits
 *
 * @author  Collie Clarke
 * @version 2024.03.11
 */
public class Exit
{
    // instance variables - replace the example below with your own
    private String direction;
    private Room neighbor;

    /**
     * Constructor for objects of class Exit
     */
    public Exit()
    {
        
    }

    /**
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Exit(String direction, Room neighbor)
    {
        // initialise instance variables
        this.direction = direction;
        this.neighbor = neighbor;
    }
    
    public String getDirection()
    {
        return direction;
    }
    
    public Room getNeighbor()
    {
        return neighbor;
    }
    
    public void setDirection(String direction)
    {
        this.direction = direction;
    }
    
    public void setNeighbor(Room neighbor)
    {
        this.neighbor = neighbor;
    }
}
