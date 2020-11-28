 /**
 * GloryQuest.java
 * Micah Roening
 * CIS 200 Scholar's Section 
 *
 * This is GloryQuest! It runs the game, GLORYQUEST. 
 * The game consists of semi-random encounters, player choices,
 * and culminates in a final boss fight. The program continues until
 * The play quits in the main menu.
 */ 

import java.util.*;
import java.io.*;

/** P
	* D
	*
	* @param p
	*
	* @return void
	*/ 
	
public class GloryQuest {
	
	/** main
	* Main calls mainMenu() to start the program
	*
	* @param String[] args Command-Line Arguments
	*
	* @return void
	*/ 
	
	public static void main(String[] args) throws IOException {
		mainMenu(0);
	}
	
	/** mainMenu
	* This method allows the player to play the game, go into
	* Dev mode, or quit the program.
	*
	* @param int dead This parameter allows the menu to react to the player's current status.
	*
	* @return void
	*/ 
	
	public static void mainMenu (int dead) throws IOException {
		Scanner s = new Scanner(System.in);
		
		// different responses for if the player just booted up the game, died, or won
		if (dead == 0) 
			System.out.print("\nWelcome to GLORYQUEST! Type (P)lay to play GLORYQUEST, type (D)ev to enter Developer mode, or (Q)uit to exit GloryQuest: ");
		else if (dead == 1)
			System.out.print("\nOoh, that looked pretty bad! If you'd like to try again, type (P)lay! If you'd like to inflict misery on others, type (D)ev!\nOr, if you need some time to recover from getting flattened, you can type (Q)uit to quit. So, what'll it be, champ? ");
		else if (dead == 2) {
			System.out.println("\nNice job, champ! You really showed him what for! A real show-stopper! A real biscut boxer! A real stumbler-rumbler! Yes sir (or madam), you really are the real deal!");
			System.out.println("As for what happened to our brave hero... hrmm...");
			chill();
			System.out.println("Well, I usually don't let people in on this one. But, you did give a heck of a performance back there...");
			chill();
			System.out.println("Eeh, why not? Here's the goods: next time you play the game, type in 'Dennis' for your name. Then you can see what our hero has been up to!");
			chill();
			System.out.print("So, you want to do that right now? If so, type (P)lay! If you DON'T want to follow up on my stupendous advice, type (Q)uit.\nOR, you could be thinking, now that you've beaten the game, it could be better! In that case, why not try out (D)ev mode? It's fun, I swear! Well, it's your choice. What's it gonna be? ");
			
		}
		
		// This section takes in the menu input, and, if the input was invalid, starts the game
		String titleString = s.nextLine().toLowerCase();
		if (titleString.equals(""))
			titleString = "Zoinks!";
		char titleChoice = titleString.charAt(0);
		
		if (titleChoice == 'd') {
			try {
				Input devMode = new Input();
				devMode.createNewThing();
			} catch (IOException x) {
				System.out.println("There was an error with the file! Please check your installation and try again.");
			}
		} else if (titleChoice == 'p') {
			playGame();
		} else if (titleChoice == 'q') {
			System.exit(0);
		} else {
			System.out.println("You think you're a funny man, dont'cha? A real razzle-dazzler! Why don't you go impress some goblins in GloryQuest, you clown.");
			playGame();
		}
	}
	
	/** playGame
	* This method reads in content from the game files, 
	* gets the name from the player, and then randomly chooses
	* what events the player will go through between the story events,
	* ending with the boss battle. The player's progress through the game
	* is tracked by the time varible.
	*
	* @return void
	*/ 
	
	public static void playGame () throws IOException {
		Random r = new Random();
		
		// reading in content from files and putting it into ArrayLists
		ArrayList <Equipment> relics = new ArrayList <> ();
		ArrayList <Monster> caveMonsters = new ArrayList <> ();
		ArrayList <Monster> forestMonsters = new ArrayList <> ();
		ArrayList <Monster> wastelandMonsters = new ArrayList <> ();
		ArrayList <Monster> villageMonsters = new ArrayList <> ();
		ArrayList <Choice> caveChoices = new ArrayList <> ();
		ArrayList <Choice> forestChoices = new ArrayList <> ();
		ArrayList <Choice> wastelandChoices = new ArrayList <> ();
		ArrayList <Choice> villageChoices = new ArrayList <> ();
		ArrayList <Trap> caveTraps = new ArrayList <> ();
		ArrayList <Trap> forestTraps = new ArrayList <> ();
		ArrayList <Trap> wastelandTraps = new ArrayList <> ();
		ArrayList <Trap> villageTraps = new ArrayList <> ();
		ArrayList <Boss> bosses = new ArrayList <> ();
		
		File equipmentFile = new File("Relics.txt");
		File monsterFile = new File("Monsters.txt");
		File choiceFile = new File("Choices.txt");
		File trapFile = new File("Traps.txt");
		File bossFile = new File("Bosses.txt");
		
		Scanner readEquipmentFile = new Scanner(equipmentFile);
		Scanner readMonsterFile = new Scanner(monsterFile);
		Scanner readChoiceFile = new Scanner(choiceFile);
		Scanner readTrapFile = new Scanner(trapFile);
		Scanner readBossFile = new Scanner(bossFile);
		
		// reading in equipment
		while (readEquipmentFile.hasNext()) {
				String line = readEquipmentFile.nextLine();
				String [] pieces = line.split(":");
				Equipment tempEquipment = new Equipment(pieces[0], pieces[1], Integer.parseInt(pieces[2]), Integer.parseInt(pieces[3]));
				relics.add(tempEquipment);
		}
		readEquipmentFile.close();
		
		// reading in monsters
		while (readMonsterFile.hasNext()) {
				String line = readMonsterFile.nextLine();
				String [] pieces = line.split(":");
				Monster tempMonster = new Monster(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]), pieces[3], Integer.parseInt(pieces[4]));
				if (pieces[0].equals("cave"))
					caveMonsters.add(tempMonster);
				else if (pieces[0].equals("forest"))
					forestMonsters.add(tempMonster);
				else if (pieces[0].equals("wasteland"))
					wastelandMonsters.add(tempMonster);
				else if (pieces[0].equals("village"))
					villageMonsters.add(tempMonster);
		}
		readMonsterFile.close();
		
		// reading in choices
		while (readChoiceFile.hasNext()) {
				String line = readChoiceFile.nextLine();
				String [] pieces = line.split(":");
				Player tempPlayer = new Player();
				Reward tempReward = new Reward();
				Choice tempChoice = new Choice(tempPlayer, pieces[1], pieces[2], pieces[3], pieces[4], tempReward);
				tempChoice.setResolution(pieces[6], pieces[7]);
				if (pieces[8].equals("cave"))
					caveChoices.add(tempChoice);
				else if (pieces[8].equals("forest"))
					forestChoices.add(tempChoice);
				else if (pieces[8].equals("wasteland"))
					wastelandChoices.add(tempChoice);
				else if (pieces[8].equals("village"))
					villageChoices.add(tempChoice);
		}
		readChoiceFile.close(); // change tempChoice & tempTrap
		
		// reading in traps
		while (readTrapFile.hasNext()) {
				String line = readTrapFile.nextLine();
				String [] pieces = line.split(":");
				Player tempPlayer = new Player();
				Reward tempReward = new Reward();
				Trap tempTrap = new Trap(tempPlayer, pieces[1], Integer.parseInt(pieces[2]), pieces[3], pieces[4], tempReward, pieces[6]);
				if (pieces[7].equals("cave"))
					caveTraps.add(tempTrap);
				else if (pieces[7].equals("forest"))
					forestTraps.add(tempTrap);
				else if (pieces[7].equals("wasteland"))
					wastelandTraps.add(tempTrap);
				else if (pieces[7].equals("village"))
					villageTraps.add(tempTrap);
		}
		readTrapFile.close();
		
		
		// reading in bosses
		while (readBossFile.hasNext()) {
				String line = readBossFile.nextLine();
				String [] pieces = line.split(":");
				Boss tempBoss = new Boss(pieces[0], Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]), pieces[3], Integer.parseInt(pieces[4]));
				bosses.add(tempBoss);
		}
		readBossFile.close();
		
		Scanner s = new Scanner(System.in);
		
		int time = 0; // this is time! It ticks up as the player goes through the game, increasing difficulty (and the strength of treasure)! Think of it like the "game clock"!
		boolean presentaionTime = false;
		// introduction
		System.out.println("\nIt's dark. It hurts. Your head swims, and your vision comes in and out of focus. You try to focus on something... your name! You must have a name...");
		System.out.print("But what was it? (Enter your name here): ");
		String name = s.nextLine();
		
		// getting the name from the player, checking for special cases
		if (name.equals("")) {
			name = nullName();
		} else if (name.equalsIgnoreCase("dennis") || name.equalsIgnoreCase("dennis lang")) {
			dennisQuest();
			name = nullName();
		} else if (name.equalsIgnoreCase("presentation time")) {
			presentaionTime = true;
		}
		
		System.out.println("Of course! " + name + "! Now sit back, relax, and get comfy! You're about to embark on...");
		chill();
		
		// creating the player object
		Player hero = new Player(name);
		
		// buffing the player if in presentation mode
		if (presentaionTime == true) {
			hero.addMaxHealth(90);
			hero.addDamage(98);
			hero.heal();
		}
		Story story = new Story(name); // The Quest Begins!
		chill();
		
		// first half of the game, this calls the cave while loop if the player chose cave, and the forest if the player chose forest
		if (story.getEventDecision(0).equals("cave")) {
			while (time < 3) {
				time = theCave(time, hero, relics, caveMonsters, caveChoices, caveTraps);
			}
			story.event1(); // this calls the second choice
			chill();
			while (time < 6) {
				time = theCave(time, hero, relics, caveMonsters, caveChoices, caveTraps);
			}
			story.event2(); // this resolves the second choice and changes the environment
			chill();
			
		} else {
			while (time < 3) {
				time = theForest(time, hero, relics, forestMonsters, forestChoices, forestTraps);
			}
			story.event1();
			chill();
			while (time < 6) {
				time = theForest(time, hero, relics, forestMonsters, forestChoices, forestTraps);
			}
			story.event2();
			chill();
		}
		
		// Last half of the game, has while loops for each of the four areas, depending on where the player is
		if (story.getEventDecision(1).equals("cave")) {
			while (time < 11) {
				int isStory = r.nextInt(4);
				if (isStory == 0) { // there is a 25% chance for the player to run into a story event at any given turn in these loops
					storyBook(story, hero);
					time++;
				} else {
					time = theCave(time, hero, relics, caveMonsters, caveChoices, caveTraps);
				}
			}
			story.event7(); // this calls the boss entrance dialog
			chill();
			bossBattle(getBoss(story.getEventDecision(1), bosses), hero); // this calls the final boss battle
			
		} else if (story.getEventDecision(1).equals("forest")) {
			while (time < 11) {
				int isStory = r.nextInt(4);
				if (isStory == 0) {
					storyBook(story, hero);
					time++;
				} else {
					time = theForest(time, hero, relics, forestMonsters, forestChoices, forestTraps);
				}
			}
			story.event7();
			chill();
			bossBattle(getBoss(story.getEventDecision(1), bosses), hero);
			
		} else if (story.getEventDecision(1).equals("wasteland")) {
			while (time < 11) {
				int isStory = r.nextInt(4);
				if (isStory == 0) {
					storyBook(story, hero);
					time++;
				} else {
					time = theWasteland(time, hero, relics, wastelandMonsters, wastelandChoices, wastelandTraps);
				}
			}
			story.event7();
			chill();
			bossBattle(getBoss(story.getEventDecision(1), bosses), hero);
			
		} else {
			while (time < 11) {
				int isStory = r.nextInt(4);
				if (isStory == 0) {
					storyBook(story, hero);
					time++;
				} else {
					time = theVillage(time, hero, relics, villageMonsters, villageChoices, villageTraps);
				}
			}
			story.event7();
			chill();
			bossBattle(getBoss(story.getEventDecision(1), bosses), hero);
			
		}
		
	}
	
	/** theCave
	* This method randomly chooses between a battle, choice, or trap for
	* the player to face. It then advances the turn timer, and returns that value.
	*
	* @param int time The turn the player is currently on
	* @param Player hero The player object
	* @param ArrayList <Equipment> relics The ArrayList of Equipment
	* @param ArrayList <Monster> caveMonsters The ArrayList of Cave-Specific Monsters
	* @param ArrayList <Choice> caveChoices The ArrayList of Cave-Specific Choices
	* @param ArrayList <Trap> caveTraps The ArrayList of Cave-Specific Traps
	*
	* @return time The game clock
	*/ 
	
	public static int theCave (int time, Player hero, ArrayList <Equipment> relics, ArrayList <Monster> caveMonsters,  ArrayList <Choice> caveChoices,  ArrayList <Trap> caveTraps) {
		Random r = new Random();
		int thisEvent = r.nextInt(12);
		
		if (thisEvent < 6) // there is a 42% chance a battle will occur
			battle(time, hero, caveMonsters);
		else if (thisEvent < 9) // a 25% chance for a trap to happen
			trap(time, hero, caveTraps, relics);
		else // and a 33% chance a choice will occur
			choice(time, hero, caveChoices, relics);
			
		time++;
		// looking back, these location specific methods became so similar, I probably only need one of them! Oh well. 
		// I could always adjust the battle/choice/trap chances to make the areas more unique in the future
		return time;
	}
	
	/** theForest
	* This method randomly chooses between a battle, choice, or trap for
	* the player to face. It then advances the turn timer, and returns that value.
	*
	* @param int time The turn the player is currently on
	* @param Player hero The player object
	* @param ArrayList <Equipment> relics The ArrayList of Equipment
	* @param ArrayList <Monster> forestMonsters The ArrayList of forest-Specific Monsters
	* @param ArrayList <Choice> forestChoices The ArrayList of forest-Specific Choices
	* @param ArrayList <Trap> forestTraps The ArrayList of forest-Specific Traps
	*
	* @return time The game clock
	*/ 
	
	public static int theForest (int time, Player hero, ArrayList <Equipment> relics, ArrayList <Monster> forestMonsters,  ArrayList <Choice> forestChoices,  ArrayList <Trap> forestTraps) {
		Random r = new Random();
		int thisEvent = r.nextInt(12);
		
		if (thisEvent < 6)
			battle(time, hero, forestMonsters);
		else if (thisEvent < 9) 
			trap(time, hero, forestTraps, relics);
		else
			choice(time, hero, forestChoices, relics);
			
		time++;
		
		return time;
	}
	
	/** theWasteland
	* This method randomly chooses between a battle, choice, or trap for
	* the player to face. It then advances the turn timer, and returns that value.
	*
	* @param int time The turn the player is currently on
	* @param Player hero The player object
	* @param ArrayList <Equipment> relics The ArrayList of Equipment
	* @param ArrayList <Monster> wastelandMonsters The ArrayList of wasteland-Specific Monsters
	* @param ArrayList <Choice> wastelandChoices The ArrayList of wasteland-Specific Choices
	* @param ArrayList <Trap> wastelandTraps The ArrayList of wasteland-Specific Traps
	*
	* @return time The game clock
	*/ 
	
	public static int theWasteland (int time, Player hero, ArrayList <Equipment> relics, ArrayList <Monster> wastelandMonsters,  ArrayList <Choice> wastelandChoices,  ArrayList <Trap> wastelandTraps) {
		Random r = new Random();
		int thisEvent = r.nextInt(12);
		
		if (thisEvent < 6)
			battle(time, hero, wastelandMonsters);
		else if (thisEvent < 9) 
			trap(time, hero, wastelandTraps, relics);
		else
			choice(time, hero, wastelandChoices, relics);
			
		time++;
		
		return time;
	}
	
	/** theVillage
	* This method randomly chooses between a battle, choice, or trap for
	* the player to face. It then advances the turn timer, and returns that value.
	*
	* @param int time The turn the player is currently on
	* @param Player hero The player object
	* @param ArrayList <Equipment> relics The ArrayList of Equipment
	* @param ArrayList <Monster> villageMonsters The ArrayList of village-Specific Monsters
	* @param ArrayList <Choice> villageChoices The ArrayList of village-Specific Choices
	* @param ArrayList <Trap> villageTraps The ArrayList of village-Specific Traps
	*
	* @return time The game clock
	*/ 
	
	public static int theVillage (int time, Player hero, ArrayList <Equipment> relics, ArrayList <Monster> villageMonsters,  ArrayList <Choice> villageChoices,  ArrayList <Trap> villageTraps) {
		Random r = new Random();
		int thisEvent = r.nextInt(12);
		
		if (thisEvent < 6)
			battle(time, hero, villageMonsters);
		else if (thisEvent < 9) 
			trap(time, hero, villageTraps, relics);
		else
			choice(time, hero, villageChoices, relics);
			
		time++;
		
		return time;
	}
	
	/** choice
	* This method randomly chooses a choice for the player, randomly
	* chooses a reward (and if there will be a reward), and then
	* outputs that choice for the player to interact with.
	*
	* @param int time The turn the player is currently on
	* @param Player hero The player object
	* @param ArrayList <Equipment> relics The ArrayList of Equipment
	* @param ArrayList <Choice> choices An ArrayList of choices
	*
	* @return void
	*/ 
	
	public static void choice (int time, Player hero, ArrayList <Choice> choices, ArrayList <Equipment> relics) {
		Random r = new Random();
		int thisChoice = r.nextInt(choices.size());
		
		int isReward = r.nextInt(3); // 1/3 chance to get NOT reward from an event
		if (isReward > 0) { 
			Reward tempReward = generateReward(time, relics);
			choices.get(thisChoice).setReward(tempReward);
		}
		
		choices.get(thisChoice).setPlayer(hero);
		boolean temp = choices.get(thisChoice).userChoice();
		chill();

	}
	
	/** trap
	* This method randomly chooses a trap for the player, randomly
	* chooses a reward (and if there will be a reward), and then
	* outputs that trap for the player to interact with.
	*
	* @param int time The turn the player is currently on
	* @param Player hero The player object
	* @param ArrayList <Equipment> relics The ArrayList of Equipment
	* @param ArrayList <Trap> traps An ArrayList of choices
	*
	* @return void
	*/ 
	
	public static void trap (int time, Player hero, ArrayList <Trap> traps, ArrayList <Equipment> relics) {
		Random r = new Random();
		int thisTrap = r.nextInt(traps.size());
		
		int isReward = r.nextInt(4); // 1/4 chance to get NOT reward from an trap
		if (isReward > 0) { 
			Reward tempReward = generateReward(time, relics);
			traps.get(thisTrap).setReward(tempReward);
		}
		
		int disarmChance = r.nextInt(75 - ((time + 1) * 2)); // traps get harder to disarm as the game progresses!
		
		traps.get(thisTrap).setPlayer(hero);
		traps.get(thisTrap).disarm(disarmChance);
		chill();
	}
	
	/** battle
	* This method randomly chooses a monster for the player to fight, and then
	* outputs a battle with that monster for the player to interact with.
	*
	* @param int time The turn the player is currently on
	* @param Player hero The player object
	* @param ArrayList <Monster> monsters An ArrayList of monsters
	*
	* @return void
	*/ 
	
	public static void battle (int time, Player hero, ArrayList <Monster> monsters) {
		Random r = new Random();
		ArrayList <Monster> tempMonsters = new ArrayList <> ();
		
		for (int i = 0; i < monsters.size(); i++) { // this loop makes sure the monster about the right difficulty for the player (and sometimes a bit harder!)
			if ((monsters.get(i).getDifficulty() > time - 1) && (monsters.get(i).getDifficulty() < time + 3)) {
				Monster deepMonsterCopy = new Monster(monsters.get(i));
				tempMonsters.add(deepMonsterCopy);
			}
		}
		
		
		int thisMonster = r.nextInt(tempMonsters.size());
		System.out.println("\nYou quickly raise your weapon: A " + tempMonsters.get(thisMonster).getName() + " stands in your path!");
		Battles fight = new Battles();
		int isDead = fight.battleMonster(tempMonsters.get(thisMonster), hero);
		
		if (isDead <= 0) { // this statement deals with player death / victory
			deathMessage(tempMonsters.get(thisMonster).getName());
			try {
				mainMenu(1);
			} catch (IOException x) {
				System.out.println("Wow! You died so hard, you broke the game! Congrats? You might want to check your installation, and then restart the game.");
				System.exit(1);
			}
		}
		else
			System.out.println("The " + tempMonsters.get(thisMonster).getName() + " defeated, you take a moment to rest and heal before continuing on your way.");
			hero.heal();
			chill();
	}
	
	/** bossBattle
	* This method puts the player up against the final boss!
	*
	* @param Boss boss The boss.
	* @param Player hero The player object
	*
	* @return void
	*/ 
	
	public static void bossBattle (Boss boss, Player hero) {
		System.out.println("The air is still. You each stand your ground, daring the other to make the first move. In a flash, you both charge. This is it! The ultimate battle! The final challenge! The fight... for GLORY!");
		Battles fight = new Battles();
		
		int isDead = fight.battleBoss(boss, hero); // calls the method in battle to fight the boss
		
		if (isDead <= 0) { // this statement deals with player death / victory
			deathMessage(boss.getName());
			try {
				mainMenu(1);
			} catch (IOException x) { // if, for some unforseen reason, a file breaks AFTER the game has been finished. This shouldn't happen this late, but here's the error checking anyways!
				System.out.println("Wow! You died so hard, you broke the game! Congrats? You might want to check your installation, and then restart the game.");
				System.exit(1);
			}
		}
		else
			System.out.println("The " + boss.getName() + " lets out a final, agonized groan as you deliver the killing blow. You stand above your vanquished foe, and bask in your victory. This kill will surely bring you fame, fortune, and, most importantly...");
			System.out.println("G L O R Y !");
			try {
				mainMenu(2);
			} catch (IOException x) {
				System.out.println("Wow! You won so hard, you broke the game! Congrats? You might want to check your installation, and then restart the game.");
				System.exit(1);
			}
	}
	
	/** getBoss
	* This method chooses a boss to fight based on the location of the player.
	*
	* @param String location The area of the game the player is currently in
	* @param ArrayList <Boss> bosses The ArrayList of Bosses
	*
	* @return returnBoss The boss the player is going to fight
	*/ 
	
	public static Boss getBoss (String location, ArrayList <Boss> bosses) {
		// with more development time (i.e., more bosses), this method could be retooled to choose from a multitude of bosses
		Boss returnBoss = new Boss();
		
		for (int i = 0; i < bosses.size(); i++) {
			if (location.equals(bosses.get(i).getLocation()))
				returnBoss = new Boss(bosses.get(i));
		}
		
		if (location.equals("cave"))
			returnBoss.setDescription("A massive, floating eye, covered in flesh, eyestalks, and a mouth full of wicked teeth.");
		else if (location.equals("forest"))
			returnBoss.setDescription("A massive, winged lizard, with scales of green and a breath that could melt a man in seconds.");
		else if (location.equals("wasteland"))
			returnBoss.setDescription("A powerful wizard, so skilled and arrogant they have cheated death... at the cost of their own humanity.");
		else if (location.equals("village"))
			returnBoss.setDescription("BEEG OGRE SMASHIN' PUUNEE HUMEES! WAAAAAAGH!");
		
		return returnBoss;
	}
	
	/** storyBook
	* This method randomly chooses between one of the generalized story events.
	* It then outputs it for the player to interact with.
	*
	* @param Story story The Story object running part of the game
	* @param Player hero The player object
	*
	* @return void
	*/ 
	
	public static void storyBook (Story story, Player hero) {
		Random r = new Random();
		int thisStory = r.nextInt(4);
		
		if (thisStory == 0)
			story.event3(hero);
		else if (thisStory == 1)
			story.event4(hero);
		else if (thisStory == 2)
			story.event5(hero);
		else if (thisStory == 3)
			story.event6(hero);
			
		chill();
			
	}
	
	/** generateReward
	* This method generates a reward about on level with the player.
	*
	* @param int time The game clock
	* @param ArrayList <Equipment> relics The ArrayList of Equipment
	*
	* @return tempReward A reward object
	*/ 
	
	public static Reward generateReward (int time, ArrayList <Equipment> relics) {
		Random r = new Random();
		ArrayList <Equipment> tempRelics = new ArrayList <> ();
			
		for (int i = 0; i < relics.size(); i++) { // this method makes a copy of the equipment BEFORE removing over/under leveled stuff
			Equipment deepEquipmentCopy = new Equipment(relics.get(i));
			tempRelics.add(deepEquipmentCopy);
		}
			
		Iterator <Equipment> it = tempRelics.iterator();
		while (it.hasNext()) {
			Equipment tempEquipment = it.next();
			if ((tempEquipment.getRarity() < (time/2)) || (tempEquipment.getRarity() > (time/2) + 1)) // getting the a valid piece of equipment for the player
				it.remove();
		}
			
		int thisReward = r.nextInt(tempRelics.size());
		Reward tempReward = new Reward(0, tempRelics.get(thisReward)); // rewards include something called Gold. This was an idea that came up in mid-development, 
		// and was ultimately scrapped. You can see a remnant of that in the '0' parameter for the Reward constructor!
		
		return tempReward;
	}
	
	/** chill
	* This method stops the game, allowing the player to take a breath,
	* and continues the game on a button press.
	*
	* @return void
	*/ 
	
	public static void chill () {
		Scanner s = new Scanner(System.in);
		System.out.println("\tPress any key to continue...\n");
		String breakTime = s.nextLine();
	}
	
	/** nullName
	* This method returns a random, somewhat insulting / funny name for the player to use.
	* It is called if the player fails to input a name.
	*
	* @return The supplied player name
	*/ 
	
	public static String nullName () {
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		int funnyName = r.nextInt(10);
		
		System.out.println("Ooh, you must really not be feeling so well. It's alright! If you don't remember your name, I'll come up with one for you. Now, what should your name be...");
		chill();
		if (funnyName == 0)
			return "Dumpus";
		else if (funnyName == 1)
			return "Rumblebumbus";
		else if (funnyName == 2)
			return "Dinkywinkle";
		else if (funnyName == 3)
			return "Billy the Fool, Lord of the Pigsty";
		else if (funnyName == 4)
			return "A Rat. You are just a rat";
		else if (funnyName == 5)
			return "Alexander the Not-So-Great";
		else if (funnyName == 6)
			return "uh... sorry, I forgot";
		else if (funnyName == 7)
			return "A Cardboard Cutout of the Kool-Aid Man";
		else if (funnyName == 8)
			return "One of the Teletubbies";
		else
			return "you";
		
	}
	
	/** dennisQuest
	* DennisQuest.
	*
	* @return void
	*/ 
	
	public static void dennisQuest () { // DennisQuest
		Scanner s = new Scanner(System.in);
		Player dennis = new Player("Dennis"); // DennisQuest
		dennis.addMaxHealth(91);
		dennis.addDamage(98);
		dennis.heal();
		
		System.out.println("\nYou stand in the middle of an ocean of conquest. Foes lie defeated all around." +
						   "\nYou have done it. You have glory! Glory! GLORY! YES! AHAHAHAHHAAAHAHAHAHHAHAHAahaa..." +
						   "\nWait. What is that? From the ground, a lone man rises. He raises his sword in silent challenge." +
						   "\nOne. More. One more conquest, and then you will have all the GLORY you could ever want. Glory. Glory! GLORY!!!!!!");
		chill();
		
		Boss dennisBoss = new Boss("Dennis", 1000, 25, "Joshua Weese", 11); // DennisQuest
		dennisBoss.setDescription("GLORY!! GLORY! GLORY!!! GLORY! GLORY!!!!!");
		
		Battles dennisFight = new Battles();
		int isDead = dennisFight.battleBoss(dennisBoss, dennis); // DennisQuest
		
		if (isDead <= 0) { // dennisquest
			System.out.print("No! Impossible! The... the glory! Glory! GLORY! The lone swordsman watches as the life drains from your eyes. He shakes his head," +
							 "\nand picks you up. Time passes. Mountains, deserts, oceans and plains pass by in a haze. Finally, you enter into a familiar forest." +
							 "\nYou finally stop, and your body falls to the ground. You see the man put a shoddy sword on the ground next to you. He takes a final look" +
							 "\nat you before walking back into the forest. As you lie there, you notice a cave in front of you. Then, a sudden pain fills your head, and everything goes black.");
							 chill();
		}
		else {
			System.out.println("The swordsman crumples in the face of your overwhelming might. He is defeated! Just like all the others!" +
							   " Yes! YES! You have done it! You are the most glorious! GLORIOUS! GLORY! GLORY! HAHAHAAAHAHAHAHAHAHAHAHAAAA!" +
							   "EVERYTHING IS GLORY! YOU ARE GLORY! GLORY!");
			String wait = s.nextLine();
			System.out.println("GLORY!");
			wait = s.nextLine();
			System.out.println("GLORY!");
			wait = s.nextLine();
			
			int i = 0;
			while (i < 100000) { // DENNISQUEST
				System.out.print("GLORY");
				i++;
			}
		
			System.exit(0);
		}
	}
	
	/** deathMessage
	* This method returns a random, somewhat insulting / funny death message whenever the player
	* dies.
	* 
	* @param String monsterName The name of the monster that killed the player
	* @return void
	*/ 
	
	public static void deathMessage(String monsterName) {
		Random r = new Random();
		int thisDeath = r.nextInt(31);
		
		chill();
		
		// we figured death was an important (or at least frequent) part of the game, so I made some more death messages! These should (hopefully) made death a little more enjoyable.
		if (thisDeath == 0) {
			System.out.println("You collapse to the ground. Your body, broken. Your mind, fading. As the " + monsterName + " looms over you, you feel yourself slipping away...\nAway...");
		} else if (thisDeath == 1) {
			System.out.print("You collapse onto the ground, defeated. As the " + monsterName + " lunges for the finishing blow, you wonder what possessed you to go on a Quest for Glory.\nI mean, you wake up outside a cave, and your first thought is to find a horrific beast to fight to the death? \nI mean, really, what kind of person does that? These thoughts do little to soften the incoming coup de grace, though. \nYour final thought before going to the great beyond is: ");
			System.out.println("I wonder what it will sound like?\n\n...crunch\n\nAfterwards, you are too busy being dead to wonder what part of your body being eviscerated made that noise.");
		} else if (thisDeath == 2) {
			System.out.print("You collapse onto the ground, defeated. As the " + monsterName + " lunges for the finishing blow, you wonder what possessed you to go on a Quest for Glory.\nI mean, you wake up outside a cave, and your first thought is to find a horrific beast to fight to the death? \nI mean, really, what kind of person does that? These thoughts do little to soften the incoming coup de grace, though. \nYour final thought before going to the great beyond is: ");
			System.out.println("Wait, didn't I come out here to collect some forest berries?\n\n...spurlch\n\nAfterwards, you are too busy being dead to wonder what possessed you to fight a " + monsterName +" instead of collecting berries.");
		} else if (thisDeath == 3) {
			System.out.print("You collapse onto the ground, defeated. As the " + monsterName + " lunges for the finishing blow, you wonder what possessed you to go on a Quest for Glory.\nI mean, you wake up outside a cave, and your first thought is to find a horrific beast to fight to the death? \nI mean, really, what kind of person does that? These thoughts do little to soften the incoming coup de grace, though. \nYour final thought before going to the great beyond is: ");
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA!\n\n...SMASH!\n\nAfterwards, you are too busy being dead to continue screaming.");
		} else if (thisDeath == 4) {
			System.out.println("You stand, breathing heavily. Though greviously wounded, you steel yourself. You can do this! For glory! You draw yourself up, and commit to a final, desperate charge against the " + monsterName + ".");
			System.out.println("Unfortunately, you slip on a banana peel and, like Charlie Brown playing football, fall comedically to the ground. The " + monsterName + " lets out a short chuckle, and you die. Partly from all the bleeding, and partly from the embaressment.");
		} else if (thisDeath == 5) {
			System.out.println("You stand, breathing heavily. Though greviously wounded, you steel yourself. You can do this! For glory! You draw yourself up, and commit to a final, desperate charge against the " + monsterName + ".");
			System.out.println("Unfortunately, the monster sidesteps, and you trip. And you fall. Right onto your own sword. \nOof. \nThe " + monsterName + " watches in stunned silence as the light fades from your eyes. Maybe you should try holding the blade AWAY from yourself next time?");
		} else if (thisDeath == 6) {
			System.out.println("You stand, breathing heavily. Though greviously wounded, you steel yourself. You can do this! For glory! You draw yourself up, and commit to a final, desperate charge against the " + monsterName + ".");
			System.out.println("Unfortunately for you, the " + monsterName + " tied your shoelaces together during that last attack. When you try to charge, you trip and fall on your face. Then you try again. The " + monsterName + " thinks this is HILARIOUS, and cannot stop laughing! \nAfter a few minutes of this, the monster gets bored, and, perhaps as an act of pity, ends your life. Maybe in the next life, instead of a monster killer, you could be a court jester!");
		} else if (thisDeath == 7) {
			System.out.println("Tim sighed as the " + monsterName + " ended another run of GloryQuest. There were few bright spots in his dull life, and now he couldn't even enjoy the fantasy of killing a monster in glorious combat? \nAs his boss came around the corner, Tim re-opened his spreadsheet. After stewing for a few minutes, he closed the spreadsheet, and re-opened GloryQuest. One more run couldn't hurt right?\nRight?");
			chill();
			System.out.println("Tim lost his job later that month. When asked what happened, Tim replied:");
			System.out.println("'It's my one escape, okay?! In the game, I can be what I could never hope to be in real life! I just... I just want to win glory. Is that so much to ask?'");
			System.out.println("We couldn't agree more, Tim. Speaking of glory, why don't we get back to the game...");
		} else if (thisDeath == 8) {
			System.out.println("Tim sighed as the " + monsterName + " ended another run of GloryQuest. There were few bright spots in his dull life, and now he couldn't even enjoy the fantasy of killing a monster in glorious combat? \nAs his boss came around the corner, Tim re-opened his spreadsheet. After stewing for a few minutes, he closed the spreadsheet, and re-opened GloryQuest. With a determined glare, he dragged the program into the recycling bin.\nIf he couldn't win GloryQuest, he would have to find his glory in real life!");
			chill();
			System.out.println("Tim is now the owner of a small, successful bookstore in Switzerland. He lives very happily with his wife and three daughters. When asked what finally caused him to turn his life around, Tim replied:");
			System.out.println("'I realized glory isn't something you can win by going through the motions or watching others acheive. You have to get up and slay the monsters in your own life, no matter how hard that may be.'");
			System.out.println("Well said, Tim. Well said indeed. Anyways, back to the game...");
		} else if (thisDeath == 9) {
			System.out.println("The " + monsterName + "'s relentless assault quickly ends any hope of surviving this encounter. It's all going dark! Wait, is that... a light?");
			chill();
			System.out.println("Hi! Welcome to the afterlife. I see you've just been killed by a... " + monsterName +". A " + monsterName + "? Really? I mean, I see people come through here all the time. All kinds of stuff.\nOld age, tragic farming accidents, general stupidity, but a " + monsterName + "? Come on man, have some self-respect!\nHere, why don't I cut you a deal. I'll ressurect you somewhere nice and not dangerous, and you go find a better way to die. Sound good? Great!\nNow sit back and relax, this might hurt a little...");
		} else if (thisDeath == 10) {
			System.out.println("The " + monsterName + "'s relentless assault quickly ends any hope of surviving this encounter. It's all going dark! Wait, is that... a light?");
			chill();
			System.out.println("Hi! Welcome to the afterlife. I see you've just been killed by a... " + monsterName +"?!? A " + monsterName + "? Really? Wow! That's incredible! \nI hear those are, like, super tough. What was it like? Ooh, I bet you were in there, like *blam* and *dodge*, and, and the " + monsterName + " was like\n*RAWR* and *SMASH*! That is so COOL! Here, here I've got an idea. I'll ressurect you, but only if you go do it again. So I can watch this time! \nSound good? Great! Just, get ready. This might be pretty painful...");
		} else if (thisDeath == 11) {
			System.out.println("Your name is Reginald. You're a " + monsterName + " from South Monstertown, and all you've ever wanted was to start a bakery, maybe sell some nice cakes.");
			System.out.println("But no! Mom always wanted you to be a menace! 'Go attack some villagers, maybe one day you'll even be like your brother and threaten a whole city!' Stupid Terry.");
			System.out.println("Always coming home and talking about how 'I just destroyed a whole block!', and, 'There's so much money in terrorizing the innocent', and,");
			System.out.println("'Come on Reggie, you could be so much more if you just APPLIED yourself!'. Well, you'll show them. You can be just a much of a menace as Terry!");
			System.out.println("As the puny human scrambles across the ground, begging you to spare them, you crush them flat. You don't feel any satisfaction from this, but oh well.");
			System.out.println("Maybe you'll at least get a little respect from Mom. Maybe even... some glory? Maybe they'll let you start your bakery! Wait, you could make a bakery, and put humans in the food!");
			System.out.println("That's genius! You turn down to the human, now more of a meaty pancake. 'Thanks, little dude! I couldn't have done it without you.' You then get back to your master plan.");
			System.out.println("A bakery isn't going to build itself, you know!");
		} else if (thisDeath == 11) {
			System.out.println("Your name is Terry. You're a " + monsterName + " from South Monstertown, and you LOVE this. The screaming, the doomed, but valiant attempts at stopping you,");
			System.out.println("the terror in their eyes as you come in for the final blow! You just wish your little brother felt the same. 'I don't like killing humans, I want to start a bakery!'");
			System.out.println("What rubbish. " + monsterName + "s should be plundering settlements, not baking cakes! And besides, what self-respecting monster eats... baked goods? You eat humans!");
			System.out.println("Oh, great. You got so caught up thinking about your brother you accidentally killed the human! You didn't even get to enjoy it. Oh well. Maybe you'll try to have another talk");
			System.out.println("with your brother. Maybe if he can't terrorize the innocent with his " + monsterName + "yness, he could at least terrorize them with his cooking.");
		} else if (thisDeath == 12) {
			System.out.println("You blink at the wrong time, and the " + monsterName + " takes the opprutunity to strike you down. Maybe try investing in some eyedrops?");
		} else if (thisDeath == 13) {
			System.out.println("Your foot hits a rock, and you lose your balance. The " + monsterName + " takes the opprutunity to strike you down. Shouldn't have quit those yoga classes!");
		} else if (thisDeath == 14) {
			System.out.println("As you go for another attack, your weapon breaks. The " + monsterName + " takes the opprutunity to strike you down. Should have gotten the warrenty, eh buddy? Buddy?");
		} else if (thisDeath == 15) {
			System.out.println("You just... stand there? The " + monsterName + " takes the opprutunity to strike you down. Its vision wasn't based on movement, bub.");
		} else if (thisDeath == 16) {
			System.out.println("You drop the the floor. Wait, what? Hey, get up, the " + monsterName + " is...\nOh well. Doesn't matter anymore. Maybe don't play dead next time?");
		} else if (thisDeath == 17) {
			System.out.println("The " + monsterName + " finds a hole in your defense, and opens you up like an expired can of tuna.");
		} else if (thisDeath == 18) {
			System.out.println("Turns out, your armor was not enough to stop a " + monsterName + ". That'll teach you to use stuff you just find lying around. I mean, seriously, who knows where that's been?");
		} else if (thisDeath == 19) {
			System.out.println("You suddenly remember you have a crippling fear of " + monsterName + "s. " + monsterName + "phobia, the Doc called it. Funny thing to remember now, really. More importantly,\nEEEEEEEEEEEEEEEEE! A " + monsterName + "! You faint, and the " + monsterName + " takes no time (and great pleasure) in finishing you off.");
		} else if (thisDeath == 20) {
			System.out.println("The " + monsterName + " is too fast for you to keep up with, and you finally collapse in the face of its furious blows.");
		} else if (thisDeath == 21) {
			System.out.println("The " + monsterName + " winds up for a MASSIVE attack! It kind of reminds you of that time you were playing baseball, and there were players on all the bases, and you were up to bat.");
			System.out.println("It was hot. A drip of sweat went down your face as the pitcher wound up for a fastball. Time slowed as you tensed, ready for the ball. Just as the ball reached you, you hit the ball with all your might!");
			System.out.println("The ball kept going and going, and you kept running and running, and the crowd was going wild, and the other team couldn't stop it, because it was a home run, a GRAND SLAM! YES! You won! You won...");
			chill();
			int flyball = r.nextInt(50);
			System.out.println("The " + monsterName + ", still winding up for its big attack, hits you like an ace batter. You fly " + flyball + " feet before hitting something and falling down in a bloody heap.\nMaybe keep your eye on the ball next time, sport.");
		} else if (thisDeath == 22) {
			System.out.println("The " + monsterName + " skewers you with a spear. 'Where did it even get one of those?', you wonder, as you collapse, defeated, onto the ground.");
		} else if (thisDeath == 23) {
			// this one is a personal favorite
			System.out.println("The " + monsterName + " pulls out a pineapple, and starts beating you to death with it. 'Where did it even get one of those?', you wonder, as you collapse, defeated, onto the ground.");
		} else if (thisDeath == 24) {
			System.out.println("The " + monsterName + " pulls out a 2013 Smith & Wesson M&P15-22, and fills you full of lead. 'Where did it even get one of those?', you wonder, as you collapse, defeated, onto the ground.");
		} else if (thisDeath == 25) {
			System.out.println("The " + monsterName + " whistles. Before you can blink, the " + monsterName + " is inside a 2021 Jeep Grand Cherokee. It procedes to commit vehicular manslaughter. To you. 'Where did it even get one of those?', you wonder, as you collapse, defeated, onto the ground.");
		} else if (thisDeath == 26) {
			System.out.println("The " + monsterName + " pulls out a striped circle, and proceeds to hypnotize you. With a dopey smile on your face, you barely register that you're walking into its open jaws.");
		} else if (thisDeath == 27) {
			System.out.println("The " + monsterName + " kills you. Wait, that can't be right. You were SUPPOSED to win! It's right here in the script! Ugh. This is what I get for 'grabbing unwilling people off the streets' for my main roles.\nGet back to the cave entrance, and we'll do it again. Did I stutter? Go on! You don't want me to get angry, do you? You want to see your family again?\nGreat, then we're on the same page. Once you're back up, we'll go again.");
			chill();
			System.out.println("...is he up there? Great. ACTION!");
		} else if (thisDeath == 28) {
			System.out.println("The" + monsterName + " kills you. Wow, that was anticlimatic. Can you die with a little more... pizzaz next time? Just think about it.");
		} else if (thisDeath == 29) {
			System.out.println("The battle is intense. Fierce. You've never encountered a foe like this before! It might be too much...");
			chill();
			System.out.println("Scratch that, this is DEFINITELY too much. The " + monsterName + " attacks again and again and again. You have to end this, and soon!");
			chill();
			System.out.println("'I yield!' You say, putting your hands up and hoping the beast will react to your plea.");
			chill();
			System.out.println("To your suprise, the " + monsterName + " stops! 'Oh thank goodness!' it says, with a immaculate British accent. 'I was so tired of fighting!'");
			chill();
			System.out.println("You learn the " + monsterName + "'s name is Reginald, and it wants to start a bakery. As it just so happens, you know how to bake!");
			System.out.println("You go with Reginald to his home, where you teach him the secrets of baking. You spend the rest of your days running a very successful bakery with Reginald.");
			System.out.println("You die peacefully in your sleep, satisfied with the way your life turned out. You may not have found glory on your quest, but perhaps you found something more important:\nCommunity, a constructive purpose, and acceptance.");
			chill();
			System.out.println("Really, what else could a person want?");
		} else if (thisDeath == 30) {
			System.out.println("The battle is intense. Fierce. You've never encountered a foe like this before! It might be too much...");
			chill();
			System.out.println("Scratch that, this is DEFINITELY too much. The " + monsterName + " attacks again and again and again. You have to end this, and soon!");
			chill();
			System.out.println("'I yield!' You say, putting your hands up and hoping the beast will react to your plea.");
			chill();
			System.out.println("To no one's surprise, the " + monsterName + " takes the opprutunity turn you into Sunday brunch. What did you think was going to happen? It probably doesn't even speak English!");
		}
		
		
		chill();
	}

}