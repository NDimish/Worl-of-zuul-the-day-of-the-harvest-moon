
/**
 * Write a description of class Crystal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Crystal extends Items
{
    // instance variables - replace the example below with your own
    
    // if the crystal is a gem else these are null. (gems use 3 word command)
    private String gem_type;
    // used for idetifying
    private char gem_prefix;
    
    //if crystal what type 0= light 1=health 2=dispell;
    private int type;

    /**
     * Constructor for objects of class Crystal
     */
    public Crystal(String m_Name, String m_TrueAction, String m_FalseAction, int weight, Class<?> classtouse, int m_type,String m_gem, char m_prefix)
    {
        super(m_Name, m_TrueAction,m_FalseAction,weight,classtouse);
        type = m_type;
        gem_type = m_gem;
        gem_prefix = m_prefix;
        
    }

    /**
     *
     *using the item 2 commands
     *used for crystals
     */
    public String Use(Command command, Room room, Player player)
    {
        String output = "";
        // increse light level of room
        if (type == 0){
            room.addVisable(1);
            output += "The room is now lit up one light level\n";
            output += "The room light level is: " + room.getVisable() + "\n";
        }
        // increse health of player
        else if(type ==1){
            output += player.addHealth(5);
        }
        // set all neighboting room abilities to false
        else if(type == 2){
            
            Room tempRoom;
            String Exits = room.getExitString();
            String[] Listexits = Exits.split(" ");
            for(int x = 1;x<Listexits.length;x++){
                tempRoom = room.getExit(Listexits[x]);
                tempRoom.setAbility(false);
                
            }
            output += "All paths leading from here block with magic\n";
            output += "Are now unblocked and free to enter!!";
        
        }
        else{
            output += "Haha look like u got a dud Crystal.\nIt no work lol";
        }
        return output;
    }
    
    
    /**
     *
     *using the item 3 commands with item class
     */
    public String Use(Items item)
    {
        
        String output = "" ;
        //is item class right
        if(GetUsingClass() == item.getClass() ){
            
            output = GetTrueAction() + "\n";
            // for items base it the active status for objects stationary
            setActive_status(true);
            output += item.setGem(gem_type,gem_prefix);
                        
        }
                        
        //item class incorrect
        else{
            
            output +="You use "+ GetName() + " with a " + item.GetName();
            output += "\n" + GetFalseAction();
        }
        
        return output;
        
    }
    
    /**
     *
     *using the item 3 commands with character class
     */
    public String Use(Character person)
    {
        
        String output = "" ;
        //is item class right
        if(GetUsingClass() == person.getClass() ){
            
            output += GetTrueAction() + "\n";
            // for items base it the active status for objects stationary
            setActive_status(true);
            // use enchantment of character class for interaction
            output +=person.Encantation();
                        
        }
                        
        //item class incorrect
        else{
            
            output +="You use "+ GetName() + " to attack " + person.getName();
            output+= "\n"+GetFalseAction();
            output += "\n" + person.getDefence();
        }
        
        return output;
        
    }
}
