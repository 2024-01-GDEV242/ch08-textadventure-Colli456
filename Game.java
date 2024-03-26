/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Collie Clarke
 * @version 2024.03.11
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room finalRoom;
    private Player player;
    private NPC porter;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Adding the look command.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Adding the eat command.
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more.");
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room oneA, oneB, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifthteen;        
      
        // create the rooms
        oneA = new Room("Your at the entrance of the dungen");
        two = new Room("You go home");
        oneB = new Room("You are on the first level");
        three = new Room("You are east of the first level");
        four = new Room("You are west of the first level");
        five = new Room("You are westside of level two");
        six = new Room("You are eastside of level two");
        seven = new Room("You are in level two main hall");
        eight = new Room("You are on the third level");
        nine = new Room("You find a secret room on the west side");
        ten = new Room("You are on the fourth level");
        eleven = new Room("You are on the fifth level");
        twelve = new Room("You are westside the lowest level of the dungen");
        thirteen = new Room("You are eastside the lowest level of the dungen");
        fourteen = new Room("You found an empty room");
        fifthteen = new Room("You found a portal but it needs a key");
        
        finalRoom = two;
        
        // initialise room exits
        oneA.setDoor("secret", two, false);
        oneA.setExit("Forward", oneB);
        
        oneB.setExit("left", three);
        oneB.setExit("right", four);
        oneB.setExit("back", oneA);
        
        three.setExit("Forward", six);
        three.setExit("left", oneB);
        
        four.setExit("Forward", five);
        four.setExit("right", oneB);
        
        five.setExit("Forward", nine);
        five.setExit("right", seven);
        five.setExit("back", four);
        
        six.setExit("left", seven);
        six.setExit("back", three);
        
        seven.setExit("Forward", eight);
        seven.setExit("left", five);
        seven.setExit("right", six);
        
        eight.setExit("Forward", ten);
        eight.setExit("back", seven);
        
        nine.setExit("back", five);
        
        ten.setExit("Forward", eleven);
        ten.setExit("back", eight);
        
        eleven.setExit("left", twelve);
        eleven.setExit("right", thirteen);
        eleven.setExit("back", ten);
        
        twelve.setExit("down", fourteen);
        twelve.setExit("right", eleven);
        
        fourteen.setExit("up", twelve);
        
        thirteen.setExit("down", fifthteen);
        thirteen.setExit("left", eleven);
        
        fifthteen.setDoor("leave", two, true);
        fifthteen.setExit("back", thirteen);
        
        // initialise room items
        nine.setItem("Key", "Key");
        //Items key = new Items("Key", "Key");
        
        oneA.addNPC("Dwarf", "Guardian", "Clue", "A helpful clue");

        // start game outside
        currentRoom = oneA;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            if (currentRoom == finalRoom)
            {
                finished = true;
            }
            else
            {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;
                
            case LOCK:
                lockDoor(command);
                break;
                
            case UNLOCK:
                unlockDoor(command);
                break;
                
            case TAKE:
                takeItem(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    
    /** 
     * "Lock" was entered. Locks the specified door, if a key's in
     * the player's inventory, otherwise throws an error.
     */
    private void lockDoor(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Lock what");
            return;
        }
        
        String desiredDoor = command.getSecondWord();
        
        if (player.checkKey())
        {
            player.getCurrentRoom().getActualDoor(desiredDoor).lock();
            System.out.println("Door locked");
        }
        else
        {
            System.out.println("You don't have a key!");
        }
    }
    
     /** 
     * "Unlock" was entered. Unlocks the specified door, if a key's in
     * the player's inventory, otherwise throws an error.
     */
    private void unlockDoor(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Unlock what");
            return;
        }
        
        String desiredDoor = command.getSecondWord();
        
        if (player.checkKey())
        {
            player.getCurrentRoom().getActualDoor(desiredDoor).unlock();
            System.out.println("Door unlocked");
        }
        else
        {
            System.out.println("You don't have a key!");
        }
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        Room nextDoor = currentRoom.getDoor(direction);
        
        if (nextDoor != null) 
        {
            if (player.getCurrentRoom().getLocked(direction) == true)
            {
                System.out.println("The door is locked!");
                return;
            }
            else
            {
                currentRoom = nextDoor;
                System.out.println(currentRoom.getLongDescription());
                System.out.println(player.getInventoryString());
                return;
            }
        }
        
        if (nextRoom == null) 
            System.out.println("There is no door!");
        else
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(player.getInventoryString());
        }
    
    }
    
    /** 
     * "Take" was entered. Takes the specified item if it's in 
     * the room, otherwise throws an error.
     */
    private void takeItem(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("Take what?");
            return;
        }
        
        String desiredItem = command.getSecondWord();
        
        Items temp = player.getCurrentRoom().delItem(desiredItem);
        if (temp != null)
        {
            player.addInventory(temp.getName(), temp.getShortDescription());
            
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }
    }
    
    private void dropItem(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("Drop what?");
            return;
        }
        
        String droppedItem = command.getSecondWord();
        
        Items temp = player.dropInventory(droppedItem);
        if (temp != null)
        {
            player.getCurrentRoom().setItem(temp.getName(), temp.getShortDescription());
            
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.println(player.getInventoryString());
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
