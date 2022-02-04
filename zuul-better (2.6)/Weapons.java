
/**
 * Write a description of class Wepons here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Weapons extends Items
{
     private int BaseDamage;
     private String gem_attachment = "normal";
     private char Prefix = 'N';// for when battling with types does more damage
    
    
    

    /**
     * Constructor for objects of class Wepons
     */
    public Weapons(String m_Name, String m_TrueAction, String m_FalseAction, int weight, Class<?> classtouse, int Damage)
    {
        super( "N"+m_Name,  m_TrueAction, m_FalseAction, weight, classtouse);
        BaseDamage = Damage;
    }

    /**
     * Set the gem attachment
     * being, fire,electric,water,wind
     */
    public String setGem(String gem, char prefix){
        String temp;
        temp = GetName().replace(Prefix,prefix);
        setName(temp);
        gem_attachment = gem;
        Prefix = prefix;
        return "Your "+GetName()+ " is now " +getGem()+" as denoted by the " + Prefix;
    }
    
    public String getGem(){
    
        return gem_attachment;
    }
    
    public int getDamage(){
    
        return BaseDamage;
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
            
            output = GetTrueAction() +"\n" ;
            // for items base it the active status for objects stationary
            output +=person.Attack(this);
            setActive_status(true);
                        
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
