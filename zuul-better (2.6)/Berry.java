
/**
 * Write a description of class berry here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Berry extends Items
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class Crystal
     */
    public Berry(String m_Name, String m_TrueAction, String m_FalseAction, int weight, Class<?> classtouse)
    {
        super(m_Name, m_TrueAction,m_FalseAction,weight,classtouse);
        
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
            // use Berryof character class for interaction
            output +=person.Berry();
                        
        }
                        
        //item class incorrect
        else{
            
            output +="You use "+ GetName() + " on " + person.getName();
            output+= "\n"+GetFalseAction();
            output += "\n" + person.getDefence();
        }
        
        return output;
        
    }
}
