import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.function.IntConsumer;

        
    
    
    


/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.
    private HashMap<String, Items> RoomItems;
    private HashMap<String, Character> RoomCharacters;
    IntConsumer Ability;
    boolean Active_ability = false;
    int visable = 1;
    // see if room locked from inside
    boolean lock = false;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        RoomItems = new HashMap<>();
        RoomCharacters = new HashMap<>();
        
    }
    
    /**
     * Output items in the room
     */
    public String seeRoomItems() 
    {
        if(RoomItems.isEmpty()){
        return ("No items in the room");
        
        }
        String returnString = "Items:";
        Set<String> keys = RoomItems.keySet();
        for(String Item : keys) {
            returnString += "     " + Item;
        }
        return returnString;
    }
    
    /**
     * Output items in the room
     */
    public String seeRoomCharacters() 
    {
        if(RoomCharacters.isEmpty()){
        return ("Nobody for miles to see");
        
        }
        String returnString = "Characters:\n";
        Set<String> keys = RoomCharacters.keySet();
        for(String person : keys) {
            returnString += RoomCharacters.get(person).getDescription() + "\n";
        }
        return returnString;
    }
    
   /**
     * gives item from room if possible
     */
    public Items useItem(String item){
        
        if(RoomItems.containsKey(item)){
            return RoomItems.get(item);
        }
        else{
            return null;
        }
    
    }
    
    /**
     * gives character from room if possible
     */
    public Character findCharacters(String person){
        
        if(RoomCharacters.containsKey(person)){
            return RoomCharacters.get(person);
        }
        else{
            return null;
        }
    
    }
    
    /**
     *Puts many items in the room
     * @param description The room's description.
     */
    public void addRoomItems(ArrayList<Items> m_roomItems) 
    {
        
        for(int x = 0; x<m_roomItems.size(); x++){
            Items temp = 
            RoomItems.put(m_roomItems.get(x).GetName(),m_roomItems.get(x));
        
        }
        
    }
    
    /**
     * Add single item to the room
     */
    public void addRoomItems(Items item){
        RoomItems.put(item.GetName(),item);
    
    }
    
     /**
     * Add single character to the room
     */
    // only to be used by the character class
    public void addRoomCharacter(Character person){
        RoomCharacters.put(person.getName(),person);
    
    }
    
    /**
     * Takes away item from room
     */
    public Items minusRoomItems(String item){
    
        if (RoomItems.containsKey(item)){
            Items temp = RoomItems.get(item);
            RoomItems.remove(item);
            return temp;
        
        }
        else{
            return null;
        }
    
    }
    
     /**
     * takes away single character to the room
     */
    public void minusRoomCharacter(String person){
        RoomCharacters.remove(person);
    
    }
    
     /**
     * Resets staus of items and characters in room
     */
    public void Resetroom() 
    {
            if(RoomItems.isEmpty()){
            }
            else{
                Set<String> keys = RoomItems.keySet();
                for(String Item : keys) {
                    RoomItems.get(Item).setActive_status(false);
                }
            }
            
            if(RoomCharacters.isEmpty()){
            }
            else{
                Set<String> keys = RoomCharacters.keySet();
                ArrayList<Character> temp = new ArrayList<>();
                for(String person : keys) {
                    temp.add(RoomCharacters.get(person));
                }
                for(int x =0;x<temp.size();x++){
                    temp.get(x).Reset(this);
                }
                
            }
        
    }
    

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        
        if(visable < 1){
            
            return "---------------------------------------------------\nYou are " + description + "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \n\nyou can't see use a light crystal or somthing";
        
        }
        return "---------------------------------------------------\nYou are " + description + "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \n\n" + seeRoomItems() + "\n\n" +seeRoomCharacters()+ "\n\n"+getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Sets if the room has an active ability
     */
    public void setAbility(boolean change) 
    {
        Active_ability = change;
    }
    
    /**
     * See if the room has an active ability
     */
    public boolean getAbility() 
    {
        return Active_ability;
    }
    
    /**
     * Sets if the room has an active ability
     */
    public void addVisable(int change) 
    {
        visable += change;
    }
    
    /**
     * Sets if the room has an active ability
     */
    public int getVisable() 
    {
        return visable;
    }
    
    /**
     * Update. Incase the item names change we update them after every use command
     */
    public void update(){
        Items temp;
        ArrayList<String> tempList = new ArrayList<>();
        if(RoomItems.isEmpty()){
            }
            else{
                Set<String> keys = RoomItems.keySet();
                for(String Item : keys) {
                    if (RoomItems.get(Item).GetName() == Item){
                    }
                    else{
                        tempList.add(Item);
                    }
                }
                for(String Item2 : tempList) {
                    temp = RoomItems.get(Item2);
                    RoomItems.remove(Item2);
                    RoomItems.put(temp.GetName(),temp);
                }
            }
    
    }
    
    /**
     * set lock status
     */
    public void setLock(boolean x){
        lock = x;
    }
    
    /**
     * Ger lock status
     */
    public boolean getLock(){
        return lock;
    }

}

