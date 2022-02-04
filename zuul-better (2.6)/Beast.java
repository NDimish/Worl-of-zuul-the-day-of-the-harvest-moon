
/**
 * Write a description of class Beast here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Beast extends Character
{
    // instance variables - replace the example below with your own
    private Player player;
    private Room Lair;
    private int Health;
    private int Max_Health;
    private String Baittouse;
    private String Weakness; // elemnt it weak to
    private Game game;
    
    // for hypnotising ending 
    private boolean sleeping = true;
    private boolean bait;
    private boolean berry;
    

    /**
     * Constructor for objects of class Beast
     */
    public Beast(String m_Name,String m_Description, String m_Dialouge,String m_Defence, int m_Health,Player m_Player, Room m_lair, String m_Baittouse, String m_Weakness, Game m_game)
    {
        super(m_Name,m_Description,m_Dialouge,m_Defence,null);
        player = m_Player;
        
        game = m_game;
        Lair = m_lair;
        Health = m_Health;
        Max_Health =m_Health;
        Baittouse = m_Baittouse;
        Weakness = m_Weakness;
        
    }

    /**
     * what to do when bait used on beast
     */
    public String Bait(String name)
    {
        sleeping = false;
        Lair.setLock(true);
        if(name.equals(Baittouse)){
            bait = true;
            return "The beast awakes and pounces on the "+name +"\n The woken beast seals the way out";
            
        
        }
        else{
            player.minus_Health(5);
            String output;
            output = "#######\nStatus:\nPlayer: " +player.getHealth()+"\nBeast: "+ Health +"\n########\n";
            return "Wrong bait the Beast Attacks you !!!!\nYou take damage -5\nYour health is now " + player.getHealth() +"\nThe woken beast seals the way out\n"+output; 
        
        }
    }
    
    /**
     * When you attack the beast
     */
    public String Attack(Weapons weapon){
        
        String output = "";
        if(bait != true){
            sleeping = false;
            bait = true;
            Lair.setLock(true);
            output += "The beast wakes\nIt locks all paths out and attacks first. \nYou can use your bait but it won't help you now";
            player.minus_Health(5);
            output += "\nYou get damaged -5 points.\nYou have "+player.getHealth()+" health points left\n";
            
            
        }
    
        if(weapon.getGem().equals(Weakness)){
            
            output += "You use a "+  weapon.getGem() + " " +weapon.GetName()+ " to slice the beast\nThe beast takes "+weapon.getDamage()+4+"  damage\n";
            Health -=  weapon.getDamage()+4;
            output += "Beast Health points are "+ Health+ "points now\n";
                    
        
        }
        
        else{
            
            output += "You use a "+  weapon.getGem() + " " +weapon.GetName()+ " to slice the beast\nThe beast takes "+weapon.getDamage()+ " damage\n";
            Health -=  weapon.getDamage();
            output += "Beast Health points are "+ Health+ "points now\n";
        
        
        }
        
        if(sleeping){
        
            output += "The beast is now awake from your slash\n";
            sleeping = false;
        }
        
        if(Health <= 0){
            game.endgame(0);
        }
        output += "The beast attacks. ";
        player.minus_Health(5);
        output += "\nYou get damaged -5 points.\nYou have "+player.getHealth()+" health points left\n";
        
        output += "#######\nStatus:\nPlayer: " +player.getHealth()+"\nBeast: "+ Health +"\n########\n";
        sleeping = false;
        return output;
    
    }
    
    /**
     * Berry used on beast
     */
    public String Berry(){
        
        berry = true;
        String output ="";
        if(sleeping != true){
            output += "You put the beast into a hypnotic state, but it will still attack you";
            output += "The beast attacks. ";
            player.minus_Health(5);
            output += "\nYou get damaged -5 points. You have "+player.getHealth()+" health points left\n";
            output += "#######\n Status:\nPlayer: " +player.getHealth()+"\nBeast: "+ Health +"\n########\n";
            return output;
        }
    
        return "You use a Dark Berry to put the beast in a hypnotic state. It still sleeps";
    
    }
    
    /**
     * Useing encantation
     */
    public String Encantation(){
        String output ="";
        
        if(!berry){
         output += "The Beast is not in a hypnotic state\nMaybe use berries\n";
        }
        else if(Lair.getVisable()<2){
            output += "The Light Level is too low\n Increase by using a light crystal\n";
        }
        else if(Lair.useItem("noisestone")==null ||Lair.useItem("noisestone").getActive_status() != true){
            output += "You need some noise to start the encantation\n";
        }
        else{
            //end game 
            output+= "You have succsefully Hypnotised the beast wel done";
            System.out.println("yaaaay");
            game.endgame(2);
            return output;
        }
        
        if(sleeping != true){
            output += "You Failed to Hypnotise while the beast awake\n";
            output += "The beast attacks. ";
            player.minus_Health(5);
            output += "\nYou get damaged -5 points. You have "+player.getHealth()+" health points left\n";
            output += "#######\n Status:\nPlayer: " +player.getHealth()+"\nBeast"+ Health +"\n########\n";
        }
        else{
           output += "You failed to hypnotise the beast. Lucky for you is still sleeping\n";
        }
    
        return output;
    
    }
    
    /**
     * this method is called when you reset the room. name move as its same as superclass
     */
    public void Reset(Room current ){
        Health = Max_Health;
        berry = false;
        bait = false;
        sleeping = true;
        Lair.setLock(false);
    
    }
    
    
}
