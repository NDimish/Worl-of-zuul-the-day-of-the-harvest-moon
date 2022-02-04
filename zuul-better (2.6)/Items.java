
/**
 * Write a description of class Objects here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Items
{
    // instance variables - replace the example below with your own
    private String Name;
    private String FalseAction;
    private String TrueAction;
    int weight;
    private boolean Active_status = false; // if the object does somthing is that thing active
    Class<?> classtouse; 

    /**
     * Constructor for objects of class Objects
     * 
     */
    
    
    public Items(String m_Name, String m_TrueAction, String m_FalseAction, int weight, Class<?> classtouse)
    {
        // initialise instance variables
        Name = m_Name;
        FalseAction = m_FalseAction;
        TrueAction = m_TrueAction;
        this.classtouse = classtouse;
        this.weight = weight;
        
        
    }
    
    /**
     *
     *using the item 2 commands
     *2 word command for use is usually to use in room or player for many subclasses
     */
    public String Use(Command command, Room room, Player player)
    {
        String output = "";
        if (command.hasThirdWord()){
            output += FalseAction;
        }
        else{
            output += TrueAction;
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
            
            output = TrueAction;
            // for items base it the active status for objects stationary
            Active_status = true;
                        
        }
                        
        //item class incorrect
        else{
            
            output +="You use "+ Name + " with a " + item.GetName();
            output += "\n" + FalseAction;
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
            
            output = TrueAction;
            // for items base it the active status for objects stationary
            Active_status = true;
                        
        }
                        
        //item class incorrect
        else{
            
            output +="You use "+ Name + " on " + person.getName();
            output+= "\n"+GetFalseAction();
            output += "\n" + person.getDefence();
        }
        
        return output;
        
    }
    
    /**
     * returns the false action
     */
    public String GetFalseAction(){
    return FalseAction;
    }
    
    /**
     * returns the true action
     */
    public String GetTrueAction(){
    return TrueAction;
    }

    /**
     *
     *Return the name of item
     */
    public String GetName()
    {
        return Name;
    }
    
    /**
     *
     *set the name of item
     */
    public void setName(String m_name)
    {
        Name = m_name;
    }
    
    /**
     *
     *Return the weight of item
     */
    public int GetWeight()
    {
        return weight;
    }
    
    /**
     *
     *Set the weight of item
     */
    public void SetWeight(int temp)
    {
        weight = temp;
    }
    
    /**
     *
     *set active status
     */
    public void setActive_status(boolean temp)
    {
        Active_status = temp;
    }
    
     /**
     *
     *Return Activestatus
     */
    public boolean getActive_status()
    {
        return Active_status;
    }
    
    /**
     *
     *Get the using class
     */
    public Class<?> GetUsingClass()
    {
        return classtouse;
    }
    
     /**
     *
     *Set the using class
     */
    public void SetUsingClass(Class<?>x)
    {
         classtouse = x;
    }
    
    /**
     * subclasses use this function
     */
    public String setGem(String gem, char  prefix){
        
        return "Haha no gem can go on this item";
    }
}
