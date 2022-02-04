
import java.util.Random;
/**
 * Write a description of class Bait here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bait extends Items
{
    // instance variables - replace the example below with your own
    // in this class we use actu=ive status to indicate if dead or not
    private int weight_Dead;
    private int alive_weight;
    private Class<?> SwapClass;
    

    /**
     * Constructor for objects of class Bait
     */
    public Bait(String m_Name, String m_TrueAction, String m_FalseAction, int weight1, int weight2, Class<?> classtouse, Class<?> m_afterDead)
    {
        super( "A"+m_Name,  m_TrueAction, m_FalseAction, weight1, classtouse);
        weight_Dead = weight2;
        alive_weight = weight1;
        SwapClass = m_afterDead;
    
    }
    
    
    /**
     *
     *using the item 3 commands
     */
    public String Use(Items item)
    {
        
        String output = "" ;
        //is item class right
        if(GetUsingClass() == item.getClass() ){
            
            //make rabbbit dead
            output = GetTrueAction();
            setActive_status(true);
            SetWeight(weight_Dead);
            output += "\nThe dead bait is indicated with a D";
            
            // change to name to dead
            String temp2;
            temp2 = GetName().replace('A','D');
            setName(temp2);
            
            // swap class to use dead animal
            Class<?> temp = GetUsingClass();
            SetUsingClass(SwapClass);
            SwapClass = temp;
                         
        }
                        
        //item class incorrect
        else{
            
            output +="You use "+ GetName() + " in a " + item.GetName();
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
            
            output = "You use bait " +GetName() +" on the Beast\n";
            output = person.Bait(GetName());
                        
        }
                        
        //item class incorrect
        else{
            output +="You use "+ GetName() + " to attack " + person.getName();
            output += "\n" + GetFalseAction();
            output += "\n" + person.getDefence();
        }
        return output;
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return 3;
    }
    
    /**
     *
     *set active status as well as reset bait
     */
    public void setActive_status(boolean temp)
    {
        if(temp == getActive_status()){
            return;
        
        }
        else if(temp == false){
            super.setActive_status(false);
            SetWeight(alive_weight);
            
            // change to name to Alive
            String temp2;
            temp2 = GetName().replace('D','A');
            setName(temp2);
            
            Class<?> temporary = GetUsingClass();
            SetUsingClass(SwapClass);
            SwapClass = temporary ;
        
        }
        else{
            super.setActive_status(true);
        }
    }
}
