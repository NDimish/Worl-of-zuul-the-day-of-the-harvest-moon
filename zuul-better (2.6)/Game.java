import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom = null;
    private Player Player1;
    private ArrayList<Room> Locations = new ArrayList();
    private boolean finished = false;
    private int ending = 6;
    
    /**
     * |Return:: constructor
     * |Parameters: null
     * |Procedure:
     * Create the game and initialise its internal map.
     * Also intialise the player and the input parser
     */
    public Game() 
    {
        Player1 = new Player(20,15,this);
        createRooms();
        parser = new Parser();
    }

    /**
     * |Return: null
     * |Parameters: null
     * |Procedure:
     * Create all the rooms and link their exits together.
     * Put the rooms in the locations arraylist.(For transporter ability)
     * As well as intializing items and charactes
     * Then putting them in their rooms
     */
    private void createRooms()
    {
        Room Home_village, Start, Path1, Path2, Path3, Path4, Men_village, Women_village, Mixed_village, Giant_village, Dwarf_village, Research_room, Cave, Boss_room;
      
        //temp array lists if batch inputs to cammands needed
        ArrayList<Items> temp = new ArrayList();
        ArrayList<Room> temproom = new ArrayList();
        
        // common items in rooms (cannot be moved so will not have effect on hashmap key idetification as there will be no duplicate)
        Items NoiseStone = new Items("noisestone","A loud noise echos throughout","U try but u fail to create even a slither of noise.",200,Weapons.class);
        Items Bed = new Items("bed","You sleep for a while.\nYou dont deserve it remeber the clocks ticking before the moon prayer ends","You somhow meesed up sleeping",150,null);
        Items Chair = new Items("chair","You sit down for a while.\nThen get up cause you got work to do","We at Ndimish.ltd don,t know how you failed to sit on a chair.\nBut well done",100,null);
        Items sidsitem = new Items("magical_printer","You sit down on the printer \na real life copy of your face is printed","We at Ndimish.ltd don,t know how you dont know how to use a printer...",1000,null);
    
        
        //weapons
        Weapons Katana = new Weapons("katana","You take pose\n You summon your inner chi and slice ","You point your katana\nYourform was wrong.\n Your attack failed",4,Beast.class,5);
        Weapons Sword  = new Weapons("sword","You brandish your sword.\nYou attack","You slice the air.\nYou missed the target",4,Beast.class,5);
        Weapons Crayon = new Weapons("crayon","You stick the crayon up their nose","You become sad and draw",4,Beast.class,2);
        Weapons Bow    = new Weapons("bow","You aim.\nYou shoot\nYou hit","You aim\nYou shoot\nYou hit your foot",4,Beast.class,5);
        
        //Bait
        Bait rabbit = new Bait("rabbit","You slain the rabbit","The rabbit runs free",200,4,Weapons.class, Beast.class);
        Bait puffin = new Bait("puffin","You slain the puffin","The puffin flies free",200,4,Weapons.class, Beast.class);
        Bait koala = new Bait("koala","You sad sad chaild.\nYou killed a poor fluffy koala","The koala is alive. he lives ",200,4,Weapons.class, Beast.class);
        Bait porcupine = new Bait("porcupine","You killed the spikey animal","The porcupine stabs u and ruffles away",200,4,Weapons.class, Beast.class);
        
        //crystals
        Crystal Electric_gem= new Crystal("electric_gem", "Purple Magical balls float around", "A spark occurs. The gem failed", 2, Weapons.class, 4,"electric", 'E');
        Crystal Fire_gem= new Crystal("fire_gem", "Red Magical balls float around", "A flame occurs. The gem failed", 2, Weapons.class, 4,"fire", 'F');
        Crystal Air_gem= new Crystal("Air_gem", "White Magical balls float around", "A breeze occurs. The gem failed", 2, Weapons.class, 4,"air", 'A');
        Crystal Soul_gem= new Crystal("soul_gem", "Black Magical balls float around", "A gohst noise occurs. The gem failed", 2, Weapons.class, 4,"soul", 'S');
        
        Crystal Light_crystal= new Crystal("light_crystal","A bright light occurs and all are blided for a while.","The light crystal cracks and fails",2,null,0,null,' ');
        Crystal Light_crystal2= new Crystal("light_crystal2","A bright light occurs and all are blided for a while.","The light crystal cracks and fails",2,null,0,null,' ');
        Crystal Light_crystal3= new Crystal("light_crystal3","A bright light occurs and all are blided for a while.","The light crystal cracks and fails",2,null,0,null,' ');
        Crystal Light_crystal4= new Crystal("light_crystal4","A bright light occurs and all are blided for a while.","The light crystal cracks and fails",2,null,0,null,' ');
        Crystal Light_crystal5= new Crystal("light_crystal5","A bright light occurs and all are blided for a while.","The light crystal cracks and fails",2,null,0,null,' ');
        
        Crystal Dispell_crystal = new Crystal("dispell_crystal", "The crystal glows to dechant all the magical barriers", "The crystal cracks and fails", 2, null, 2,null, ' ');
        Crystal Health_crystal = new Crystal("health_crystal", "The crystal glows red.\nYou are feeling rejuvinated", "The health crystal cracks you failed using it", 2, null, 1,null, ' ');
        Crystal enchantment_crystal = new Crystal("enchantment_gem", "The crystal glows and words fly in the room", "The health crystal cracks you failed using it", 2, Beast.class, 4,null, ' ');
        
        //berries
        Berry blackberry = new Berry("darkberry", "The sleep berry was consumed", "you try to drug an upstanding person shame on you",1,Beast.class);
        
        
        // create the rooms
        Home_village = new Room("in your home village.\nThe entire village is busy praying to the moon.\n The harvest is gone!! Find it!!");
        Start = new Room("at the enterance to the monlorp forest.\nThe square Footprints head east");
        Path1 = new Room("on the forest path in the Rabbit territory.\nThe square footprints head south\nYou hear a mens bar fight from the north");
        Path2 = new Room("on the forest path in the koala territory.\nThe square footprints head east\nA slurry of noises that seem to be from a mixed club come from the south"+
        "\nRoars of giants come from the west");
        Path3 = new Room("on the forest path in the porcupine territory.\nThe square footprints head north\nYou hear womans tap dance from the east");
        Path4 = new Room("on the forest path in the puffin territory.\nno square footprints here\nA slurry of noises that seem to be from a mixed club come from the west"+
        "\nYou hear the hobbit playing from the east\nYou hear lab noises from the south");
        Men_village = new Room("welcome to the village of men");
        Women_village = new Room("welcome to the village of women");
        Mixed_village = new Room("welcome to the mixed village");
        Giant_village = new Room("welcome the village of giants");
        Dwarf_village = new Room("welocme to the Dwarf village");
        Research_room = new Room("inside a sort of reasearch room");
        Cave = new Room("blinded in darkness. Where can u go");
        Boss_room = new Room("in front of the monster!!");
        
        //add to array:
        Locations.add(Home_village);
        Locations.add(Start);
        Locations.add(Path1);
        Locations.add(Path2);
        Locations.add(Path3);
        Locations.add(Path4);
        Locations.add(Men_village);
        Locations.add(Women_village);
        Locations.add(Mixed_village);
        Locations.add(Giant_village);
        Locations.add(Dwarf_village);
        Locations.add(Research_room);
        Locations.add(Cave);
        Locations.add(Boss_room);
        
        
        //characters
        temproom.add(Start);
        temproom.add(Path1);
        temproom.add(Path2);
        Character Glenn = new Character("glenn","glenn: A child dwarf twiddling his thumbs to understand what he just seen","umm, hi hello,"+
        "  i ii i\njust saw the Golndernhorn.\nit wass carrrrying uhh some harvest\nhuh u want to know more.\nI think my village elder knows more. "+
        "he is usualy in the dwarf village\nfrom the start of the forest go east,south,east,south and east","glenn cries on the floor wondering why u would attack him",temproom);
        
        temproom=new ArrayList();
        
        temproom.add(Mixed_village);
        temproom.add(Path1);
        temproom.add(Path2);
        temproom.add(Path3);
        temproom.add(Path4);
        
        Character Ndimishor = new Character("ndimishor","ndimishor: A man a woman a supirior being who resides in the mixed village ready to hunt",
        "Hi there buddy\nI have been told by the forest you suck at hunting\nLet me help you out hahahah\nTo kill bait like rabbit and koal"+
        "as you first look at the target then bradish your weapon of choice.\nTo my youglings i say do\nuse (bait) (weapon)\nTo hunt a beast you brandish"+
        " your weapon first\nuse (weapon) (beast)\nIf you use a bait on a beast you can attack first.\n "
        ,"Ndimishor dodges you and laughs.\nHe says: your far too young to beat me",temproom);
        
        temproom=new ArrayList();
        
        temproom.add(Path1);
        temproom.add(Path2);
        temproom.add(Path3);
        temproom.add(Path4);
        
        Character Researcher = new Character("researcher","researcher: A man investegating the area","I have followed these footprints.\nI"+
        " belive the Golnderhorn lives north of the porcupine grounds\nBut the enterance is locked by an ability that teleports me :(\nI think the dwarfs may have a"+
        " crystal to dispell it.\nSuch that you activate it in the room of porcupines.\n Then i can hypnotise it hahahahaha",
        "The resercher dodges and look at you like your and idiot",temproom);
        
        temproom = new ArrayList();
        
        temproom.add(Women_village);
        temproom.add(Path3);
        temproom.add(Path4);
        temproom.add(Mixed_village);
        
        Character woman = new Character("sergiomer","sergiomer: A beutiful woman who loves to hunt.","Looking at your sad figure I don't think "+
        "you do a lot of damage.\nHmph i think a weakling like you should learn fighting.\nWant me to teach a paesent like you\nWell be happy i am teac"+
        "hing you.\nWell for big beasts you should use swords\nFor small beasts you should use the all mighty kid crayon haha\nFor a flying beast you "+
        "should use a bow","UwU\nXD\nYou want to do somthing with me\nYou creep",temproom);
        
        
        temproom = new ArrayList();
        
        temproom.add(Dwarf_village);
        temproom.add(Path4);
        temproom.add(Path3);
        
        
        Character Glip = new Character("glip","glip:The elder Dwarf that knows all the knowledge of the dwarf libraries","Oh hello, Your wonder"+
        "ing about the monster that made those footprints glenn saw\nUhh the Golnderhorn\nYes the info in the libraries are\nThe Golnderhorn is a beast"+
        "that is weak to electric infused blades.\nIt is a big beast class\nThe bait to lure it is a puffin","This old dwarf can still out manouver you",
        temproom);
        
        temproom = new ArrayList();
        
        temproom.add(Giant_village);
        temproom.add(Path2);
        temproom.add(Mixed_village);
        
        Character Rigbitpaul = new Character("rigbitpaul","rigbitpaul: A big brained giant who will outsmart you in strength and brains",
        "hmmm i didn't see you down there.Let me teach you the way of the gems an crystals"+
        "\nSo a gem is used by saying use (gem). Gems have specific abilities usually indicated by their name\nCrystals however must be used with a weap"+
        "on\nThis will make that weapon have that element\nDo use (crystal) (weapon) to enchant weapon\nUseful for getting more damage if the monster has a weaknes to the element","The giant absolu"+
        "tly wrecks you and smashes you to the ground with his massive intelect",temproom);
        
        Character Holo = new Character("peppapig","peppapig: A hologram book titled how to hypnotise the Golnderhorn","To hypnotise the golnderhorn\nThe"+
        "room must be lit to a light level more than one\nThere should be a loud noise in the room\nThe Golnderhorn must eat the darkberry found in the cave"+
        "\nForce feed by use (berry) (beast)\nThen use the enchantment crystal like so : use enchantment_gem (Beast)\nNote: the dispell crystal must be used in the room"+
        "before the\nGolnderhorn Lair","Its a hologram Dumbass",null);
        
        temproom = new ArrayList();
        
        temproom.add(Men_village);
        temproom.add(Cave);
        
        
        Character Damna = new Character("damna","damna: A stunning young man with lucious pink hair and stick body.","Yoooo broo\nhehe there a cave"+
        "it connects me to a certain village ;)\nbut i need a light crystal to see.We have one in the men_village buttt its sticky i dont like it", "brooo"+
        " I thought we were tight",temproom);
        
        //Beast(final boss)
        Beast Golnderhorn = new Beast("beast","beast: named Golnderhorn \nshe sleep soundly surrounding by the hard earned crops of your village",
        "Grrrrrraaaaaaaaaaaaa", "The beast suprising nimble for its weight dodge with ease", 25, Player1, Boss_room , "Dpuffin","electric",this);  
      
        
        //set exits
        //set items
        // set chatacters
        //home village
        Home_village.setExit("east", Start);
        
        
        //start
        Start.setExit("east",Path1);
        Start.setExit("west", Home_village);
        Start.addRoomCharacter(Glenn);
        Start.addRoomItems(NoiseStone);
        
        
        //path1
        Path1.setExit("north", Men_village);
        Path1.setExit("south",Path2);
        Path1.setExit("west", Start);
        Path1.addRoomItems(rabbit);
        
        
        //path 2
        Path2.setExit("north", Path1);
        Path2.setExit("east" , Path3);
        Path2.setExit("south",Mixed_village);
        Path2.setExit("west", Giant_village);
        Path2.addRoomItems(koala);
        Path2.addRoomItems(Soul_gem);
        
        
        //path3
        Path3.setExit("north", Boss_room);
        Path3.setExit("east", Women_village);
        Path3.setExit("south",Path4);
        Path3.setExit("west", Path2);
        Path3.addRoomCharacter(Researcher);
        Path3.addRoomItems(porcupine);
        Path3.addRoomItems(Crayon);
        
        
        
        //path4
        Path4.setExit("north", Path3);
        Path4.setExit("east", Dwarf_village);
        Path4.setExit("south",Research_room);
        Path4.setExit("west", Mixed_village);
        Path4.addRoomItems(puffin);
        Path4.addRoomItems(Electric_gem);
        Path4.addRoomItems(NoiseStone);
        
        //Men_village
        Men_village.setExit("down", Cave);
        Men_village.setExit("south",Path1);
        Men_village.addRoomCharacter(Damna);
        Men_village.addRoomItems(Light_crystal);
        Men_village.addRoomItems(Health_crystal);
        Men_village.addRoomItems(Chair);
        Men_village.addRoomItems(NoiseStone);
        
        
        //Women_village
        Women_village.setExit("east", Cave);
        Women_village.setExit("west", Path3);
        Women_village.addRoomCharacter(woman);
        Women_village.addRoomItems(Light_crystal2);
        Women_village.addRoomItems(Bow);
        Women_village.addRoomItems(Bed);
        
        
        //Mixed_village
        Mixed_village.setExit("north", Path2);
        Mixed_village.setExit("east", Path3);
        Mixed_village.addRoomCharacter(Ndimishor);
        Mixed_village.addRoomItems(Light_crystal3);
        Mixed_village.addRoomItems(Katana);
        
        
        //Giant_village
        Giant_village.setExit("east", Path2);
        Giant_village.setExit("down",Research_room);
        Giant_village.addRoomCharacter(Rigbitpaul);
        Giant_village.addRoomItems(Light_crystal4);
        Giant_village.addRoomItems(Chair);
        Giant_village.addRoomItems(NoiseStone);
        Giant_village.addRoomItems(Fire_gem);
        
        //Dwarf_village
        Dwarf_village.setExit("up",Cave);
        Dwarf_village.setExit("west", Path4);
        Dwarf_village.addRoomCharacter(Glip);
        Dwarf_village.addRoomItems(Air_gem);
        Dwarf_village.addRoomItems(Bed);
        Dwarf_village.addRoomItems(Chair);
        Dwarf_village.addRoomItems(Dispell_crystal);
        
        
        //Research_room
        Research_room.setExit("up",Giant_village);
        Research_room.setExit("north", Path4);
        Research_room.addRoomCharacter(Holo);
        Research_room.addRoomItems(Light_crystal5);
        Research_room.addRoomItems(enchantment_crystal);
        
        //Cave
        Cave.setExit("up", Men_village);
        Cave.setExit("west", Women_village);
        Cave.setExit("down",Dwarf_village);
        Cave.addVisable(-1);
        Cave.addRoomItems(blackberry);
        Cave.addRoomItems(sidsitem);
        
        
        //Boss_room
        Boss_room.setExit("south",Path3);
        Boss_room.setAbility(true);     
        Boss_room.addRoomCharacter(Golnderhorn);
        Boss_room.addRoomItems(NoiseStone);

        currentRoom = Start;  // start game outside
        
    }

    /**
     * |Return: null
     * |Parameters: null
     * |Procedure:
     * This is the the loop that keep the game running
     */
    public void play() 
    {            
        
        // print the welcom screen
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        
        
        do{
            while (! finished) {
                Command command = parser.getCommand();
                processCommand(command);
            }
            if(ending == 0){
                System.out.println("You killed the beast!!\nYou take the harvest back home\nYou are a hero!!\nThanks for playing");
            }
            else if(ending == 1){
                System.out.println("You died to the hands of the beast\nLets hope your legacy holds out\nThe village now wont have a harvest for a year\nThanks for playing");
                System.out.println("\n\nDo you wish to return outside the boss room and try again?");
                System.out.println("\n\nPress anything to quit. Press Y to return to game");
                
                // as you died you can restart game from last point before interaction with beast
                System.out.print("> "); ;
                Scanner read = new Scanner(System.in);
                String input = read.nextLine();
                if (input.toLowerCase().equals("y")){
                    
                    //reset room
                    goBack();
                    //resetplayhealth
                    Player1.addHealth(100);
                    finished = false;
                
                    }
                //set ending type to arbratary number to escape room;
                else{
                    
                     ending = 8;
                     
                    }
            }
            
            else if(ending == 2){
                System.out.println("By hypnotising the Beast; it helped you carry the harvest back.\nYou are a hero\nYou also made a friend :)\nThank for playing");
            }
            // if ended game in a different way such as quiting
            else{
                System.out.println("You decided not to play anymore.\nYou died to a stick. Your progress is lost. Good bye");
            }
        }while(ending ==1);// Ending one is the only ending allowing you to restart from check point
        
    }

    /**
     * |Return: null
     * |Parameters: null
     * |Procedure:
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        
        System.out.println();
        System.out.println("              Welcome to the World of ZUUL!\n              ~The day of the Harvest Moon~\n\n");
        System.out.println("World of Zuul is a new, incredibly boring adventure game without IMAGINATION!!.\n\n-----------| Cutscene |---------------");
        System.out.println( "It was a night like any other. \nWhere the sky started to dimm allowing the full moon to perch over the horizon.\nThe festivities "+
        "to thank the moon for the crops and bountiful harvests collleced this year commenced.\nTwas a distraction.\nFor you notice  during the cermony, the harvests dissapeared!\n"+
        "All that was left were the 'square' like footprint leading to monlorp forest. \nYou were warned not to enter. \nYou know the entire country is praying "+
        "so you go to ensure the safety of the harvest. \nYou run into the forest. \nYour task find the harvest stolen from you. ");
        System.out.println("Type 'help' to explain how to play the game.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        
    }

        /**
         * |Return: null
         * |Parameters: Command: input command
         * |Procedure:
         * Given a command, process (that is: execute) the command.
         * @param command The command to be processed.
         * 
         */
        private void processCommand(Command command) 
        {
    
        if(command.isUnknown()) {
            System.out.println("You are physically inable to "+ command.getThirdWord() + ". Try somthing else");
            return;
        }
        
        String commandWord = command.getCommandWord();
        
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            quit(command);
        }
        
        else if (commandWord.equals("back")) {
            goBack();
        }
        
        else if (commandWord.equals("talk")) {
            Talk(command);
        }
        
        else if (commandWord.equals("use") || commandWord.equals("attack")) {
            UseObject(command);
        }
        
        else if (commandWord.equals("take")) {
            TakeObject(command);
        }
        
        else if (commandWord.equals("drop")) {
            DropObject(command);
        }
        
        else if (commandWord.equals("see")) {
            see(command);
        }
        
        
        // else command not recognised.
    }

    // implementations of user commands:

    /**
     * |Return: null
     * |Parameters: null
     * |Procedure:
     * Print out some help information.
     * Here we print stuff to explain how the games works 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("####################| Help screen |#########################");
        System.out.println("You are in Monlorp forest.\nThe village round here have people that know specific things");
        System.out.println("Including how to catch bait\nHow to fight\nHow to enchant\nAnd the info about the many monsters in the forest");
        System.out.println();
        System.out.println("Your command words are:\n");
        parser.showCommands();
        System.out.println("\nAll command words need a second item or character to do\nSome even need 3\n");
        System.out.println("For example use command is done like: 'use chair'\nOr for the see command: 'see room', 'see inventory'\nWhen typing commands "+
        "omit prepositions\nSo 'use noisestone with Nsword' turns to 'use noisestone Nsword'\n\nattack and use do the same thing\nTo learn how to do more"+
        " tasks talk to people in the game\n\nCapatalised letters on items indicate one of two things:\nThe element of the item(These are for weapons)\nOr the item status eg: Arabbit (Alive), "+
        "supposed to Drabbit (Dead)\n\nWord of advice. Listen carefully to characters.\nAs they are beings they may not always be in the same place");
        System.out.println("#######################################################");
    }

    /** 
     * |Return: null
     * |Parameters: Command: command
     * |Procedure:
     * Check if the room is locked from the inside
     * Check if there is a second word to indicate directiorn
     * Check if direction is valid
     * If the room in that direction has an ability change the room value to one without an ability
     * Change room to that room
     * Reset previous room
     */
    private void goRoom(Command command) 
    {
        // see if room locked from inside
        if(currentRoom.getLock()){
            System.out.println("You are stuck in this room");
            return;
        }
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord().toLowerCase();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return;
        }
        // If room has abiliy get a room without and ability to be next room
        while(nextRoom.getAbility()){
            System.out.println( "----------------------------------------------------------");
            System.out.println("||||||||||||||||||||||||||||||||||||\nThis room has an ability! You have been teleported!!\n||||||||||||||||||||||||||||||");
            Random r = new Random();
            nextRoom = Locations.get(r.nextInt(Locations.size()-1));
            
        }
        
        // change room and reset previous
        previousRoom = currentRoom;
        currentRoom = nextRoom;
        previousRoom.Resetroom();
        System.out.println(currentRoom.getLongDescription());
        
    }

    /**
     * |Return: null
     * |Parameters: null
     * |Procedure:
     * Check if there is a romm to go back to
     * If yes change room to previous rooom
     * reset the room you came from
     */
    private void goBack(){
    
        if (previousRoom == null){
        
            System.out.println("You have not been anywhere yet to go back");
            return;
        }
        
        Room temp = currentRoom;
        currentRoom = previousRoom;
        previousRoom = temp;
        previousRoom.Resetroom();
        System.out.println(currentRoom.getLongDescription());
        
    }
    
    /**
     * |Return: null
     * |Parameters: Command: command
     * |Procedure:
     * Check if there is a second word to indicate person to talk to
     * Check if that person is in the room
     * If yes output the player dialouge
     */
    private void Talk(Command command){
    
        if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Talk to who");
                return;
            }
         
        Character person;
        if ((person = currentRoom.findCharacters(command.getSecondWord()))!=null){
            // print dialouge
            System.out.println("*****************| Dialouge |****************************");
            System.out.println(person.getName() + " speaks:\n"+ person.getDialouge());
            System.out.println("*****************************************************");
        }
        else{
        
            System.out.println("There is nobody by the name "+command.getSecondWord()+" here!");
        } 
    
    }
    
    /**
     * |Return: null
     * |Parameters: Command: command
     * |Procedure:
     * First we check if there is a second word to indicate and ite to use
     * We then see if the item is in the room or in the player inventory
     * Then we see if the item choosen need a third command by seeing if there is a class that uses it
     * If no third word needed output the cutscene
     * If there is a third word see if there is a third item in command
     * Then see if the item is in the room or in the player inventory
     * Then see if the item is the correct class to use the first item
     * if so output use dialouge of first item
     * else print the failed dialouge of first item
     */
    private void UseObject(Command command){
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            System.out.println("Use what?");
            return;
        }
        
        // remebr to add u cant attck with a gem
        
        
        String usingItemName = command.getSecondWord();
        // the class must have super class item for this to work
        Items usingItem;
        Items SecondItem;
        //incase item use on character
        Character person;
        // check item in inventory
        if((usingItem = Player1.useItem(usingItemName))!=null){
            System.out.println("You use the " +usingItemName+ " From your inventory.");
                        // check if the item uses three word command
            if (usingItem.GetUsingClass() != null){
            
                // check if 3rd word is availabe
                if(command.hasThirdWord()){
                    
                    // check if 2nd item in inventory
                        if((SecondItem = Player1.useItem(command.getThirdWord()))!=null){
                        
                        System.out.println(usingItem.Use(SecondItem));
        
                    }
                        // check if 2nd item in room
                    else if((SecondItem = currentRoom.useItem(command.getThirdWord()))!=null){
                        
                        System.out.println(usingItem.Use(SecondItem));
                        
                        }
                    // if second word interact with character    
                    else if((person = currentRoom.findCharacters(command.getThirdWord())) !=null ){
                    
                        System.out.println(usingItem.Use(person));
                        
                    }
                        // where 2nd item?
                    else{
                                        
                        System.out.println("You can't use a "+usingItemName + " with a " + command.getThirdWord()+ " when its not in the room \nDumDum");
                    }
                
                    
                }
                //use with what
                else{
                    
                    System.out.println("Use "+usingItemName + " with what?");
                }
            }
            //two word command
            else{
                System.out.println(usingItem.Use(command,currentRoom, Player1));
            
            }
        
        }
        
        // if not in inventory the player can use the item if its in the room
        else if((usingItem = currentRoom.useItem(usingItemName))!=null){
            System.out.println("You use the " +usingItemName+ " in the room.");
        
            // check if the item uses three word command
            if (usingItem.GetUsingClass() != null){
                
                // check if 3rd word is availabe
                if(command.hasThirdWord()){
                    
                    // check if 2nd item in inventory
                        if((SecondItem = Player1.useItem(command.getThirdWord()))!=null){
                        
                        System.out.println(usingItem.Use(SecondItem));
                        
                    }
                    // check if 2nd item in room
                    else if((SecondItem = currentRoom.useItem(command.getThirdWord()))!=null){
                        
                        System.out.println(usingItem.Use(SecondItem));
                        
                        }
                        
                    // if second word interact with character    
                    else if((person = currentRoom.findCharacters(command.getThirdWord())) !=null ){
                    
                        System.out.println(usingItem.Use(person));
                        
                    }    
                     // where 2nd item?   
                    else{
                                        
                        System.out.println("You can't use a "+usingItemName + " with a " + command.getThirdWord()+ " when its not in the room \nDumDum");
                    }
                }
                // use with what
                else{
                    System.out.println("Use "+usingItemName + " with what?");
                }
            }
            //two word command
            else{
                System.out.println(usingItem.Use(command, currentRoom, Player1));
            
            }
        }
        
        else{
            // a little fun if you use a  person
                 if ((person = currentRoom.findCharacters(command.getSecondWord()))!=null){
                            
                   System.out.println(person.getDefence());
                
                            }
            else{
                System.out.println("That item is neither in the room or your Inventory");
                return;
            }
        }
        // incase any values change for items
        currentRoom.update();
        Player1.update();
    
    
    }
    
    /**
     * |Return: null
     * |Parameters: Command: command
     * |Procedure:
     * See if there a second word to indicate item to drop
     * See if item is in your inventory
     * Drop and object from inventory
     */
    private void DropObject(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        
        
        Items temp;
        // get from inventory
        temp = Player1.dropFromInventory(command.getSecondWord());
        if(temp == null){
            System.out.println("You dont have such an item in you inventory");
        }
        
        // drop from inventory
        else{
            currentRoom.addRoomItems(temp);
            System.out.println(temp.GetName()+" is put into the room");
        }
    
    }
    
    /**
     * |Return: null
     * |Parameters: Command: command
     * |Procedure:
     * see if there is a second word to idicate item
     * see if item in room
     * see if the item can be picked up (ie if the weight is too much or not)
     * Take object from room if possible
     */
    private void TakeObject(Command command){
        
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        
        Items temp;
        // take out room
        temp = currentRoom.minusRoomItems(command.getSecondWord());
        if(temp == null){
            System.out.println("There is no item of that name in the room");
        }
        
        else{
            //add to inventory
            if(Player1.addToInventory(temp)){
                System.out.println(temp.GetName()+" is added to your inventory: "+ Player1.getWeightStatus());
                return;
            }
            // not possible to add return to room
            if( temp.getClass()== Bait.class){
                System.out.println("It seems the animal ran away from you");
            }
            else{
            System.out.println("It seems you can't handle the weight of this item plus your inventory.\nThe weight of that iem is: "+temp.GetWeight());
            }
            currentRoom.addRoomItems(temp);
        }
    
    }
    
    /**
     * |Return: null
     * |Parameters: Command: command
     * |Procedure:
     * see if the second word indicates what to see
     * Show inventory or room items
     * check if room light level is 0 or less. if so say you cant see room
     */
    private void see(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to see...
            System.out.println("see what?");
            return;
        }
        
        String toSee = command.getSecondWord();
        
        if ( _INVENTORY.equals(toSee.toLowerCase())){
        System.out.println("In your inventory you have the following items :\n"+ Player1.seeInventory());
        }
        
        else if (_ROOM.equals(toSee.toLowerCase())){
            if(currentRoom.getVisable() > 0){
                System.out.println("In the room you see:\n"+ currentRoom.seeRoomItems() + "\n\n" +currentRoom.seeRoomCharacters()+ "\n\n"+currentRoom.getExitString());
            }
            else{
            
                System.out.println("It dark here. Use a light crystal or somthing to leave a lingering light");
            }
        }
        
        else{
            System.out.println("Your vision is not that good to see " + command.getSecondWord());
        }
        
    
    }
    
    /** 
     * |Return: null
     * |Parameters: Command: command
     * |Procedure:
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * IF quit game is true then endgame wih scenario 6 (an arbritary number above 3 as the default ending is the quit number)
     */
    private void quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
        }
        else {
            endgame(6);  // signal that we want to quit
        }
    }
    
    /**
     * |Return: null
     * |Parameters: int ending type;
     * |Procedure:
     * Set the game end to true
     * with the ending of the parameter
     */
    public void endgame(int m_ending){
        finished = true;
        ending = m_ending;
    }
    
    static String _INVENTORY = "inventory";
    static String _ROOM = "room";
}