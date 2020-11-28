 /**
 * Input.java
 * Micah Roening
 * CIS 200 Scholar's Section 
 *
 * This class allows a user to input Equipment, Monsters, Choices, and Traps.
 * These things will then be put into the main game!
 * There is a tutorial to teach a user the details of the process.
 */ 

import java.util.*;
import java.io.*;

public class Input {

	private String type;
	private Scanner s = new Scanner(System.in);
	
	/** Input
	* This no-arg constructor allows the user to enter the tutorial and
	* allows them to choose what kind of object they would like to create.
	*
	* @return void
	*/ 
	
	public Input () {
		boolean validClass = false;
		String choice = "";
		System.out.println("Welcome to Developer mode, where all your wildest dreams come true! Would you like a quick rundown on how to create stuff? (Y)es/(N)o: ");
		String tutorialString = s.nextLine().toLowerCase();
		if (tutorialString.equals(""))
			tutorialString = "Zoinks!";
		char tutorialChoice = tutorialString.charAt(0);
		
		if (tutorialChoice == 'y') {
			devTutorial();
		}
			
		while (validClass == false) {
			System.out.print("Would you like to input a piece of 'Equipment', a 'Monster', a 'Choice', or a 'Trap': ");
			choice = s.nextLine();
			
			if (choice.equalsIgnoreCase("Equipment") || choice.equalsIgnoreCase("Monster") ||
				choice.equalsIgnoreCase("Choice") || choice.equalsIgnoreCase("Trap"))
				validClass = true;
			else
				System.out.println("Please choose a valid object!");
		}
		
		type = choice;
	}
	
	/** createNewThing
	* This method calls the methods to create individual objects
	*
	* @return void
	*/ 
	
	public void createNewThing () throws IOException {
		if (type.equalsIgnoreCase("Equipment")) {
			createEquipment();
		} else if (type.equalsIgnoreCase("Monster")) {
			createMonster();
		} else if (type.equalsIgnoreCase("Choice")) {
			createChoice();
		} else if (type.equalsIgnoreCase("Trap")) {
			createTrap();
		} 
	}
	
	/** createEquipment
	* This method allows a user to create equipment
	*
	* @return void
	*/ 
	
	public void createEquipment () throws IOException {
		String name = "";
		String type = "";
		int value = 0;
		int rarity = 0;
		
		File equipmentFile = new File("Relics.txt");
		Scanner readFile = new Scanner(equipmentFile);
		
		System.out.println("Welcome to Equipment creation!");
		
		// getting a valid type for the equipment
		do {
			System.out.print("Choose if this is a (W)eapon or a piece of (A)rmor: ");
			type = s.nextLine().toLowerCase();
			if (type.charAt(0) != 'w' && type.charAt(0) != 'a')
				System.out.println("Please choose a valid type!");
				
				
		} while (type.charAt(0) != 'w' && type.charAt(0) != 'a');
				 
		if (type.charAt(0) == 'w')
			type = "weapon";
		else if (type.charAt(0) == 'a')
			type = "armor";
			
		// getting a valid (non-duplicate) name for the monster
		boolean openName = false;
		ArrayList <String> currentRelics = new ArrayList <> ();
		while (readFile.hasNext()) {
				String line = readFile.nextLine();
				currentRelics.add(line);
		}
		readFile.close();
		
		while (openName == false) {
			System.out.print("Enter a name for this " + type + ": ");
			name = s.nextLine();
			openName = true;
			for (int i = 0; i < currentRelics.size(); i++) {
				String[] pieces = currentRelics.get(i).split(":");
				if (name.equalsIgnoreCase(pieces[0])) {
					System.out.println(type + " already exists! Enter a different name.");
					openName = false;
				}
			}
		}
		
		// getting valid value
		boolean validValue = false;
		while (validValue == false) {
			try {
				if (type.equals("weapon"))
					System.out.print("Enter this " + name + "'s damage: ");
				else
					System.out.print("Enter this " + name + "'s defense: ");
				value = Integer.parseInt(s.nextLine());
				validValue = true;
			} catch (NumberFormatException x) {
				System.out.println("Please input only integers!");
				validValue = false;
			}
		}
		
		// getting valid rarity
		boolean validRarity = false;
		while (validRarity == false) {
			try {
				System.out.print("Enter this " + name + "'s rarity: ");
				rarity = Integer.parseInt(s.nextLine());
				validRarity = true;
				
				if (rarity < 0 || rarity > 10) {
					System.out.println("Value must be between 1-10!");
					validRarity = false;
				}
			} catch (NumberFormatException x) {
				System.out.println("Please input only integers!");
				validRarity = false;
			}
		}
		
		
		// writing to a file
		System.out.println("Creating equipment...");
		String outputString = name + ":" + type + ":" + value + ":" + rarity;
		FileWriter writeFile = new FileWriter(equipmentFile, true);
		writeFile.write("\n" + outputString);
		writeFile.close();
		System.out.println("Equipment created! May it serve you well on your quest!");
		
		
	}
	
	/** createMonster
	* This method allows a user to create monsters
	*
	* @return void
	*/ 
	
	public void createMonster () throws IOException {
		int health = 0;
		int damage = 0;
		int difficulty = 0;
		String name = "";
		String location = "";
	
		File monsterFile = new File("Monsters.txt");
		Scanner readFile = new Scanner(monsterFile);
		
		System.out.println("Welcome to Monster creation!");
		
		// getting a valid location for the monster
		do {
			System.out.print("Choose if this is a (C)ave, (F)orest, (W)asteland, or (V)illage monster: ");
			location = s.nextLine().toLowerCase();
			if (location.charAt(0) != 'c' && location.charAt(0) != 'f' && location.charAt(0) != 'w' && location.charAt(0) != 'v')
				System.out.println("Please choose a valid location!");
				
				
		} while (location.charAt(0) != 'c' && location.charAt(0) != 'f' &&
				 location.charAt(0) != 'w' && location.charAt(0) != 'v');
				 
		if (location.charAt(0) == 'c')
			location = "cave";
		else if (location.charAt(0) == 'f')
			location = "forest";
		else if (location.charAt(0) == 'w')
			location = "wasteland";
		else if (location.charAt(0) == 'v')
			location = "village";
			
		// getting a valid (non-duplicate) name for the monster
		boolean openName = false;
		ArrayList <String> currentMonsters = new ArrayList <> ();
		while (readFile.hasNext()) {
				String line = readFile.nextLine();
				currentMonsters.add(line);
		}
		readFile.close();
		
		while (openName == false) {
			System.out.print("Enter a name for this " + location + " monster: ");
			name = s.nextLine();
			openName = true;
			for (int i = 0; i < currentMonsters.size(); i++) {
				String[] pieces = currentMonsters.get(i).split(":");
				if (name.equalsIgnoreCase(pieces[3])) {
					System.out.println("Monster already exists! Enter a different name.");
					openName = false;
				}
			}
		}
		
		// getting valid monster health
		boolean validHealth = false;
		while (validHealth == false) {
			try {
				System.out.print("Enter this " + name + "'s health: ");
				health = Integer.parseInt(s.nextLine());
				validHealth = true;
			} catch (NumberFormatException x) {
				System.out.println("Please input only integers!");
				validHealth = false;
			}
		}
		
		// getting valid monster damage
		boolean validDamage = false;
		while (validDamage == false) {
			try {
				System.out.print("Enter this " + name + "'s damage: ");
				damage = Integer.parseInt(s.nextLine());
				validDamage = true;
			} catch (NumberFormatException x) {
				System.out.println("Please input only integers!");
				validDamage = false;
			}
		}
		
		// getting valid monster difficulty
		boolean validDifficulty = false;
		while (validDifficulty == false) {
			try {
				System.out.print("Enter this " + name + "'s difficulty: ");
				difficulty = Integer.parseInt(s.nextLine());
				validDifficulty = true;
				
				if (difficulty < 0 || difficulty > 10) {
					System.out.println("Value must be between 1-10!");
					validDifficulty = false;
				}
			} catch (NumberFormatException x) {
				System.out.println("Please input only integers!");
				validDifficulty = false;
			}
		}
		
		
		// writing to a file
		System.out.println("Creating monster...");
		String outputString = location + ":" + health + ":" + damage + ":" + name + ":" + difficulty;
		FileWriter writeFile = new FileWriter(monsterFile, true);
		writeFile.write("\n" + outputString);
		writeFile.close();
		System.out.println("Monster created! May God have mercy on us all.");
		
	}
	
	/** createChoice
	* This method allows a user to create choices
	*
	* @return void
	*/ 
	
	public void createChoice() throws IOException {
		String description = "";
		String optionA = "";
		String optionB = "";
		String rewardMessage = "";
		String resolutionA = "";
		String resolutionB = "";
		String location = "";
		
		File choiceFile = new File("Choices.txt");
		
		System.out.println("Welcome to Choice creation!");
		
		// getting a valid location for the monster
		do {
			System.out.print("Choose if this is a (C)ave, (F)orest, (W)asteland, or (V)illage choice: ");
			location = s.nextLine().toLowerCase();
			if (location.charAt(0) != 'c' && location.charAt(0) != 'f' && location.charAt(0) != 'w' && location.charAt(0) != 'v')
				System.out.println("Please choose a valid location!");
		} while (location.charAt(0) != 'c' && location.charAt(0) != 'f' &&
				 location.charAt(0) != 'w' && location.charAt(0) != 'v');
				 
		if (location.charAt(0) == 'c')
			location = "cave";
		else if (location.charAt(0) == 'f')
			location = "forest";
		else if (location.charAt(0) == 'w')
			location = "wasteland";
		else if (location.charAt(0) == 'v')
			location = "village";
		
		// entering the choice description
		do {
			System.out.print("Enter a description for this " + location + " choice: ");
			description = s.nextLine();
		} while (description.equals(""));
		
		// entering the first option
		do {
			System.out.print("Enter the rewarded option for this " + location + " choice: ");
			optionA = s.nextLine();
		} while (optionA.equals(""));
		
		// entering the first resolution
		do {
			System.out.print("Enter the rewarded resolution for this " + location + " choice: ");
			resolutionA = s.nextLine();
		} while (resolutionA.equals(""));
		
		// entering the second option
		do {
			System.out.print("Enter the non-rewarded option for this " + location + " choice: ");
			optionB = s.nextLine();
		} while (optionB.equals(""));
		
		// entering the second resolution
		do {
			System.out.print("Enter the non-rewarded resolution for this " + location + " choice: ");
			resolutionB = s.nextLine();
		} while (resolutionB.equals(""));
		
		// entering the reward message
		do {
			System.out.print("Enter the reward message for this " + location + " choice: ");
			rewardMessage = s.nextLine();
		} while (rewardMessage.equals(""));
		
		// writing to a file
		System.out.println("Creating choice...");
		String outputString = "(Player):" + description + ":" + optionA + ":" + optionB + ":" + rewardMessage + ":(Reward):" + resolutionA + ":" + resolutionB + ":" + location;
		FileWriter writeFile = new FileWriter(choiceFile, true);
		writeFile.write("\n" + outputString);
		writeFile.close();
		System.out.println("Choice created! Choose wisely!");
		
		
	}
	
	/** createTrap
	* This method allows a user to create Traps
	*
	* @return void
	*/ 
	
	public void createTrap () throws IOException {
		String description = "";
		int damage = 0;
		String failMessage = "";
		String successMessage = "";
		String rewardMessage = "";
		String location = "";
		
		File trapFile = new File("Traps.txt");
		
		System.out.println("Welcome to Trap creation!");
		
		// getting a valid location for the monster
		do {
			System.out.print("Choose if this is a (C)ave, (F)orest, (W)asteland, or (V)illage trap: ");
			location = s.nextLine().toLowerCase();
			if (location.charAt(0) != 'c' && location.charAt(0) != 'f' && location.charAt(0) != 'w' && location.charAt(0) != 'v')
				System.out.println("Please choose a valid location!");
		} while (location.charAt(0) != 'c' && location.charAt(0) != 'f' &&
				 location.charAt(0) != 'w' && location.charAt(0) != 'v');
				 
		if (location.charAt(0) == 'c')
			location = "cave";
		else if (location.charAt(0) == 'f')
			location = "forest";
		else if (location.charAt(0) == 'w')
			location = "wasteland";
		else if (location.charAt(0) == 'v')
			location = "village";
		
		// entering the trap description
		do {
			System.out.print("Enter a description for this " + location + " trap: ");
			description = s.nextLine();
		} while (description.equals(""));
		
		// getting valid trap damage
		boolean validDamage = false;
		while (validDamage == false) {
			try {
				System.out.print("Enter this trap's damage: ");
				damage = Integer.parseInt(s.nextLine());
				validDamage = true;
			} catch (NumberFormatException x) {
				System.out.println("Please input only integers!");
				validDamage = false;
			}
		}
		
		// entering the fail message
		do {
			System.out.print("Enter the fail message for this " + location + " trap: ");
			failMessage = s.nextLine();
		} while (failMessage.equals(""));
		
		// entering the success message
		do {
			System.out.print("Enter the success message for this " + location + " trap: ");
			successMessage = s.nextLine();
		} while (successMessage.equals(""));
		
		// entering the reward message
		do {
			System.out.print("Enter the reward message for this " + location + " trap: ");
			rewardMessage = s.nextLine();
		} while (rewardMessage.equals(""));
		
		// writing to a file
		System.out.println("Creating trap...");
		String outputString = "(Player):" + description + ":" + damage + ":" + failMessage + ":" + successMessage + ":(Randomly Generated Reward):" + rewardMessage + ":" + location;
		FileWriter writeFile = new FileWriter(trapFile, true);
		writeFile.write("\n" + outputString);
		writeFile.close();
		System.out.println("Trap created! I hope it gets the hero! (you maniac)");
		
	}
	
	/** devTutorial
	* This method tells the user how to use Dev Mode.
	*
	* @return void
	*/ 
	
	public void devTutorial () {
		System.out.println("Ah! A newcomer! Welcome to DEVELOPER MODE!!!! Imagine some epic music just played. I'd have done it, but we're kind of on a budget.");
		System.out.println("In fact, we're on so much of a budget, we couldn't fill the game with enough content! Oops.");
		System.out.println("But fear not, you! We've spent precious development time making it so YOU can do the work instead! (Genius, right?)");
		System.out.println("So why don't you come with me! I'll explain the basics. Just keep pressing buttons to advance to tutorial! (Try it now!)");
		String wait = s.nextLine();
		System.out.println("See, you're a natural. Alright, onto the good stuff...");
		wait = s.nextLine();
		
		System.out.println("There are four different things you can create! Equipment, monsters, choices, and traps!");
		wait = s.nextLine();
		
		System.out.println("\nEquipment is the stuff the hero picks up in order to get stronger! There are two types: 'Armor' and 'Weapon'.");
		wait = s.nextLine();
		System.out.println("Armor is for defense, and Weapons are for offense. Each piece of equipment has a 'rarity'! This is a rough indication of how powerful it is.");
		wait = s.nextLine();
		System.out.println("Rarity goes from levels 1 to 5. A level one weapon might have anywhere from 1 to 3 attack, while a level five weapon might have anywhere from 11 to 13!");
		wait = s.nextLine();
		System.out.println("Here's a rough chart that corresponds rarity to value for Weapons:");
		System.out.println("**********\n 1: 1-3\n 2: 4-6\n 3: 7-8\n 4: 9-10\n 5: 11-13\n**********");
		wait = s.nextLine();
		System.out.println("And here's another one for Armor:");
		System.out.println("**********\n 1: 5-14\n 2: 15-24\n 3: 25-29\n 4: 30-34\n 5: 35-40\n**********");
		wait = s.nextLine();
		System.out.println("You may have noticed the Armor has a lot higher values than the weapons! Don't worry, this is intentional game design.");
		System.out.println("Each piece of equipment must also have a name! This can be whatever you want, as long as the name isn't '' or already taken, so go ham!");
		wait = s.nextLine();
		
		System.out.println("\nNext up are Monsters! Though I question WHY you would want to make any more of these, you can!");
		wait = s.nextLine();
		System.out.println("Monsters show up in the locations of the game. You'll be prompted to choose between one of them before making the rest of the monster.");
		System.out.println("(Just as a reminder, the locations are 'cave', 'forest', 'wasteland', and 'village')");
		wait = s.nextLine();
		System.out.println("You'll then need to choose a name for your beasty. Once again, this can be anything except a blank string OR a monster that already exists.");
		wait = s.nextLine();
		System.out.println("Finally, Monsters are kind of like Equipment in the fact they have values that roughly correspond to a rarity, or for Monsters, difficulty.");
		wait = s.nextLine();
		System.out.println("Unlike Equipment, Monsters have both a health AND damage value. The difficulty also goes from 1 - 10. Here's a handy table so you don't break the game while creating your abominations:");
		System.out.println(" Difficulty | Health | Damage");
		System.out.println("***************************");
		System.out.println("      1     |   3    |    1\n      2     |   6    |    2\n      3     |   9    |    3\n      4     |   12    |    4\n      5     |   15    |    5\n      6     |   18    |    6\n      7     |   21    |    7\n      8     |   24    |    8\n      9     |   27    |    9\n      10     |   30    |    10");
		System.out.println("***************************");
		wait = s.nextLine();
		System.out.println("Q: Do all my monsters have to follow this table?");
		wait = s.nextLine();
		System.out.println("A: No! You can have higher values, lower values, mix up the numbers, do whatever! These are just the basic stats for a level X monster. Try not to stray TOO far from these values, and you should be good!");
		wait = s.nextLine();
		
		System.out.println("\nAfter Monsters, we have Choices! Choices are fun because you have a chance to win a prize!");
		wait = s.nextLine();
		System.out.println("Choices are entirely text-based! That's right! No more tables! Instead, choices require logic (or basic pattern recognition).");
		wait = s.nextLine();
		System.out.println("Choices also have locations attached to them! That means you can make a location appropriate choices (pretty cool, I know)!");
		wait = s.nextLine();
		System.out.println("When creating a choice, you will first (well, after the location) be asked to provide a description. This explains the circumstances surrounding the choice.");
		System.out.println("For the sake of demonstration, let's make one together (or, you just watch me make one)! We'll make the location 'cave', and keep the description simple: 'You see a box'.");
		wait = s.nextLine();
		System.out.println("You will then be asked to give the option that MIGHT lead to a reward. For example: 'Open the Box'.");
		wait = s.nextLine();
		System.out.println("After that, you'll need to provide the resolution for that choice. For example: 'You open the box! It is empty. Lame.'.");
		wait = s.nextLine();
		System.out.println("'But wait,' you may be asking, 'where's the reward? This is LAME!' First off, uncalled for! Second, keep that in mind. We'll come back to it.");
		wait = s.nextLine();
		System.out.println("Next is the UNrewarded option. This option will NEVER provide a reward, no matter what. For example: 'Leave the box alone! I have a TERRIBLE box allergy!'");
		wait = s.nextLine();
		System.out.println("Poor man, can't open a box without breaking into hives. Anyways, we now need to provide a resolution to THIS choice.");
		System.out.println("Let's go with this: 'You keep walking. Boxes are BAD news.'");
		wait = s.nextLine();
		System.out.println("So, in the actual game, if you choose option 1, you'll open the box, and if you choose option 2, you won't. Binary choices! Pretty cool, right?");
		wait = s.nextLine();
		System.out.println("No? Fine, we'll add a reward message.");
		wait = s.nextLine();
		System.out.println("The reward message is outputted if the random number generator blesses the player. So, for this box, let's go with this:");
		System.out.println("'Just as you start to break out in hives, you notice a small lever in the box. You flip it, and, to your glee, a hidden compartment opens! As you grab the treasure, you only wish it also had some allergy medicine too.'");
		wait = s.nextLine();
		System.out.println("See what we did there? Continuity, spontaneity, AND a funny joke! Maybe try to make harder choices when you're actually making choices, though.");
		wait = s.nextLine();
		
		System.out.println("\nLastly, we have Traps. These are meant to bully the player! Do you like bullying? What are you, some kind of sadist? Weird. Anyways...");
		wait = s.nextLine();
		System.out.println("Traps are kind of like gambling. You're probably going to lose, and that will probably end up causing some sort of physical pain.");
		wait = s.nextLine();
		System.out.println("When making a trap, you will first be asked to enter a location. You know these! Cave, forest, wasteland, village, you get the idea.");
		wait = s.nextLine();
		System.out.println("Anyways, back to the more important gambling metaphor. When dealing with a trap, the player has two options:");
		System.out.println("   A: Play it safe! (This is kind of like folding halfway through a round of poker)");
		System.out.println("or B: Go for it! (This is kind of like going all in on a pair of twos. Either way, you're probably gonna lose)");
		wait = s.nextLine();
		System.out.println("When you 'Play it safe', you are guarenteed to get hit, but for a reduced amount of damage!");
		System.out.println("When you 'Go for it', you MIGHT not get hit. And if you don't get hit, you MIGHT even get a reward! But you're probably just going to get hit for the full amount of damage.");
		wait = s.nextLine();
		System.out.println("With all this in mind, we can finally start making our own trap! Huzzah!");
		wait = s.nextLine();
		System.out.println("The first thing we'll need is a location. How about the forest this time? The description describes how you have just gotten yourself into trouble.");
		System.out.println("For our example, let's go with something classic, and dangerous! Ooh! How about this: 'You step into a bear trap'!");
		wait = s.nextLine();
		System.out.println("Classic, painful, and to the point. Next, we need a 'fail message'. This will display if the player hunkers down OR fails when trying to disarm the trap.");
		System.out.println("You may be asking, 'Hey console I/O guy, isn't that a lot of ground to cover with one message?' I'm so glad you asked! Anyways, onto the fail message...");
		wait = s.nextLine();
		System.out.println("Let's try and get something good for this one: 'The trap swings closed around your leg with a *CRUNCH*. You spend the next few, painful, minutes prying it off your leg. Maybe watch your step next time?'");
		wait = s.nextLine();
		System.out.println("Well, we really emphasized the pain of the trap, AND got in a barb at the player for losing the random number generator! It'll do.");
		wait = s.nextLine();
		System.out.println("Oh, I almost forgot! The main purpose of a Trap is to whittle down the player's health BEFORE the next monster fight. To do that, we need damage!");
		wait = s.nextLine();
		System.out.println("It'll prompt you for damage BEFORE the fail message. Just input a number and you'll be good! What was that? How much damage should my trap do?");
		System.out.println("Well, traps don't have a level attached to them, so try to keep them pretty tame. We wouldn't want to punish a low-level player for running into the wrong trap!");
		System.out.println("Then again, the player can't DIE from a trap, so...");
		System.out.println("You know what, I'll leave that up for you to decide.");
		wait = s.nextLine();
		System.out.println("Anyways, you'll also need a success message! If the player throws themselves at the mercy of RNG, they may, in fact, win! Pretty lame, I know, but still a possibility.");
		wait = s.nextLine();
		System.out.println("We'll make our message something like: 'You quickly dart your foot out of the trap, just before it snaps shut beneath you. A bead of sweat trickes down your brow as you put your foot back down, and continue on your way.'");
		wait = s.nextLine();
		System.out.println("...emphasizes the closeness of the danger, and the speed of the reaction, and the subsequent carefulness... seems good to me!");
		wait = s.nextLine();
		System.out.println("Finally, we'll need to add a reward message. That's right! Kind of like a choice, you MAY get a reward for dodging the trap.");
		System.out.println("For the bear trap, this will do: 'As you pick through the forest, wary of more traps, a metal glit catches your eye. Carefully approaching, you realize this is not another trap, but someone's gear! You pick it up, satisfied it isn't trying to kill you.'");
		wait = s.nextLine();
		System.out.println("It kind of relates to the trap! Good enough for me. With that, the trap should be finished!");
		wait = s.nextLine();
		
		System.out.println("\nAnd that's it! You are officaily a junior GLORYQUEST developer! Congrats. No, you don't get any money.");
		wait = s.nextLine();
		System.out.println("A few quick notes before you go play god:");
		wait = s.nextLine();
		System.out.println("1. You can't delete things from Developer mode! If you break the balance of the game, you have to go into the files to delete it.");
		wait = s.nextLine();
		System.out.println("2. The location locking means you can sort of theme the game as you'd like! Want to forest to become a magical fairy forest? With enough you-sir generated content, it can be just that! Might not be able to change the bosses though.");
		wait = s.nextLine();
		System.out.println("3. If you get halfway through making something and decide it's a wash, you should probably force-exit the program. The file is only written to once ALL the values have been entered, so don't feel bad about this.");
		wait = s.nextLine();
		System.out.println("4. The program will close after you finish making something. After all, it has to read the new files on startup!");
		wait = s.nextLine();
		System.out.println("\nAny other questions, comments, or concerns can be forwarded to our PR department. (Spolier alert: We don't have one!)");
		wait = s.nextLine();
		System.out.println("Welp, that's it from me. Have fun breaking the game!");
		wait = s.nextLine();
	}
	
}