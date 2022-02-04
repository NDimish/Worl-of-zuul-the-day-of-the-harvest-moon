import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * This class is the player class
 * Where the players inventory and health is stored.
 *
 * @author (Nathan Mani)
 * @version (3.2)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int Inventory_weight = 0; 
    private int Max_weight;
    private HashMap<String, Items> Inventory;
    
    private Game game; // the game that intialises it.(This is so when player health goes to 0 end game)
    //  future update ensure that player and game are not codependent;
    private int Health;
    private int Max_Health;
    

    /**
     * |Return: void :: constructor
     * |Parameters: int : health,maximum inventory weight, Game: game intailaising player
     * |Procedure:
     * Constructor for objects of class Player
     */
    public Player(int m_health, int m_Max_weight, Game m_game)
    {
        // initialise instance variables
        Health = m_health;
        Max_Health = m_health;
        Max_weight = m_Max_weight;
        Inventory = new HashMap<>();
        game = m_game;
    
    }
    
    
    /**
     * |Return: boolean
     * |Parameters: null
     * |Function:
     * Puts item into inventory if the weight plus inventory weight does not cross max weight
     * Returns true or false depending if the weight is correct or not
     * 
     */
    
    public boolean addToInventory(Items item){
        
        if(Inventory_weight + item.GetWeight() > Max_weight){
            return false;
        }
        
        else{
            Inventory_weight += item.GetWeight();
            Inventory.put(item.GetName(),item);
            return true;
        }
    
    }
    
    /**
     * |Return: Items
     * |Parameters: String: item name
     * |Function:
     * Drops item from inventory if possible
     * Check if item in inventory if not return null
     */
    public Items dropFromInventory(String item){
        
        if(Inventory.containsKey(item)){
            Items temp = Inventory.get(item);
            Inventory.remove(item);
            Inventory_weight -= temp.GetWeight();
            return temp;
        }
        
        else{
            return null;
        }
    
    }
    
    /**
     * |Return: String
     * |Parameters: null
     * |Function:
     * Returns a string of what is in the inventory
     */
    public String seeInventory() 
    {
        
        if(Inventory.size() == 0){
            return ("No items in your Inventory remeber");
        }
        
        String returnString = "";
        Set<String> keys = Inventory.keySet();
        
        for(String Item : keys) {
            returnString += "   " + Item;
        }
        return returnString;
        
    }
    
    /**
     * |Return: Items
     * |Parameters: String: item name
     * |Function:
     * Sees if item in inventory
     * If it is return the pointer of the item so it can be used;
     */
    public Items useItem(String item){
        
        if(Inventory.containsKey(item)){
            return Inventory.get(item);
        }
        else{
            return null;
        }
    
    }
    
    /**
     * |Return: null
     * |Parameters: null
     * |Procedure:
     * Updates inventory.
     * Incase the item names change we update them after every use command
     */
    public void update(){
        
        Items temp;
        ArrayList<String> tempList = new ArrayList<>();
        
        if(Inventory.isEmpty()){
            // do nothing
        }
        
        else{
            Set<String> keys = Inventory.keySet();
            // get all items into a array list
            for(String Item : keys) {
                if (Inventory.get(Item).GetName() == Item){
                }
                else{
                        tempList.add(Item);
                 }
             }
            //remove item from hash map and add it correctly to hash map with updated name
            for(String Item2 : tempList) {
                temp = Inventory.get(Item2);
                Inventory.remove(Item2);
                Inventory.put(temp.GetName(),temp);
            }
        }
    
    }

    /**
     * |Return: int
     * |Parameters: null
     * |Function:
     *Return inventory weight
     */
    public int getInventoryWeight()
    {
        
        return Inventory_weight;
        
    }
    
    /**
     * |Return: String
     * |Parameters: null
     * |Function:
     *Return the inventoryweigt/max weight ration in string form
     */
    public String getWeightStatus()
    {
        
        return Inventory_weight+ "/"+Max_weight;
        
    }
    
    /**
     * |Return: void
     * |Parameters: int number health minus by
     * |Procedure:
     * minus value from health.
     * if health is 0 or lower end the game with outcome 1
     */
    public void minus_Health(int num){
    
        Health -= num;
        if(Health <= 0){
            game.endgame(1);
            }
            
    }
    
    /**
     * |Return: int
     * |Parameters: null
     * |Function:
     * Return player health
     */
    public int getHealth(){
    
        return Health;
        
    }
    
    /**
     * |Return: String
     * |Parameters: int: Health incrrase by
     * |Function:
     *Adds increse health to health. If passes max health only restore to max health
     *return what happened in String
     */
    public String addHealth(int x){
        
        String output = "";
    
        if(Health + x > Max_Health){
            Health = Max_Health;
            output += "You have been restored to full health\n";
        }
        else{
            Health += x;
            output += "You have been restored health by " +x+" points\n";
        
        }
        return output;
        
    }
}
