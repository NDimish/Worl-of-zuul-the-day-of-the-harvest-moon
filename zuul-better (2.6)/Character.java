import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class Character here.
 *
 * @author (Nathan)
 * @version (3.2)
 */
public class Character
{
    // instance variables - replace the example below with your own
    private String Name;
    private String Dialouge;
    private ArrayList<Room> Rooms; // what rooms it can walk in
    private String Description;
    private String Defence;

     /**
     * |Return:: constructor
     * |Parameters: null
     * |Procedure:
     * Constructor for objects of class Character
     */
    public Character(String m_Name,String m_Description, String m_Dialouge,String m_Defence, ArrayList<Room> m_Rooms )
        {
        Name = m_Name;
        Dialouge = m_Dialouge;
        Rooms = m_Rooms;
        Description = m_Description;
        Defence = m_Defence;
    }
    
     /**
     * |Return: null
     * |Parameters: room: the current room the character is in
     * |Procedure:
     * rests characters by moving them if they move
     * The room they go to is random
     * they can stay in the same room
     */
    public void Reset(Room current )
    {
        // THE CHARACTER DOES NOT MOVE ROOMS
        if (Rooms==null){
            return;
        };
        
        current.minusRoomCharacter(Name);
        Room New = null;
        int rnd;
        rnd = new Random().nextInt(Rooms.size()-1);
        //new room its in (can be in same if wish be)
        New = Rooms.get(rnd);
            
        New.addRoomCharacter(this);
    
    }
    
    /**
     * |Return: String: Dialouge
     * |Parameters: null
     * |Function:
     * return Dialouge
     */
    public String getDialouge(){
        
        return Dialouge;
    
    }
    
    /**
     * |Return: String: name
     * |Parameters: null
     * |Function:
     * return name
     */
    public String getName(){
    
        return Name;
        
    }
    
    /**
     * |Return: String: Description
     * |Parameters: null
     * |Function:
     * return character discription
     */
    public String getDescription(){
    
        return Description;
        
    }
    
    /**
     * |Return: String: defence
     * |Parameters: null
     * |Function:
     * return Defence with bit of nice formating. ie the name of character then defence
     */
    public String getDefence(){
        
        return getName()+": "+ Defence;
        
    }
    
    //for beast
    /**
     * |Return: String: Action if you use bait on a character
     * |Parameters: String: name of bait item used
     * |Function:
     * return you cant use bait on a character(Will be overided for beast class)
     */
    public String Bait(String name){
    
        return "You cant use a bait on a character. /nHow rude";
        
    }
    
    /**
     * |Return: String: Action if you use weapon on a character
     * |Parameters: Weapon: weaopon used
     * |Function:
     * Returns Attack person wit weapon
     * (will be overided for beast class)
     */
    public String Attack(Weapons weapon){
    
        return "You attack person with"+ weapon.GetName(); 
        
    }
    
    /**
     * |Return: String: what happens if you enchant somthing
     * |Parameters: null
     * |Function:
     * Output a string to say what happens if you enchant a character
     * Some subclass use this
     */
    public String Encantation(){
    
        return "You mumble some random nonsense and are pushed aside";
        
    }
    
    /**
     * |Return: String: what happens if you use a berry on somthing
     * |Parameters: null
     * |Function:
     * Output a string to say what happens if you use a berry on a character a character
     * Some subclass use this
     */
    public String Berry(){
    
        return "You use berries on a person. Do you have no decency";
        
    }


    
}
