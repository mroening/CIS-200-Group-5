import java.util.*;
import java.io.*;

public class GloryQuest {
	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Welcome to GLORYQUEST! Type (P)lay to play GLORYQUEST, or type (D)ev to enter Developer mode: ");
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
		} else {
			System.out.println("You think you're a funny man, dont'cha? A real razzle-dazzler! Why don't you go impress some goblins in GloryQuest, you clown.");
			playGame();
		}
		
	}
	
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
		
		int time = 0;
		// introduction
		System.out.println("\nIt's dark. It hurts. Your head swims, and your vision comes in and out of focus. You try to focus on something... your name! You must have a name...");
		System.out.print("But what was it? (Enter your name here): ");
		String name = s.nextLine();
		
		if (name.equals("")) {
			name = nullName();
		} else if (name.equalsIgnoreCase("dennis")) {
			dennisQuest();
			name = nullName();
		}
		
		System.out.println("Of course! " + name + "! Now sit back, relax, and get comfy! You're about to embark on...");
		
		Player hero = new Player(name);
		Story story = new Story(name); // The Quest Begins!
		chill();
		
		// first half of the game
		if (story.getEventDecision(0).equals("cave")) {
			while (time < 3) {
				time = theCave(time, hero, relics, caveMonsters, caveChoices, caveTraps);
			}
			story.event1();
			chill();
			while (time < 6) {
				time = theCave(time, hero, relics, caveMonsters, caveChoices, caveTraps);
			}
			story.event2();
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
		
		// Last half of the game 
		if (story.getEventDecision(1).equals("cave")) {
			while (time < 11) {
				int isStory = r.nextInt(4);
				if (isStory == 0) {
					storyBook(story, hero);
					time++;
				} else {
					time = theCave(time, hero, relics, caveMonsters, caveChoices, caveTraps);
				}
			}
			story.event7();
			chill();
			bossBattle(getBoss(story.getEventDecision(1), bosses), hero);
			
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
	
	public static int theCave (int time, Player hero, ArrayList <Equipment> relics, ArrayList <Monster> caveMonsters,  ArrayList <Choice> caveChoices,  ArrayList <Trap> caveTraps) {
		Random r = new Random();
		int thisEvent = r.nextInt(12);
		
		if (thisEvent < 4)
			battle(time, hero, caveMonsters);
		else if (thisEvent < 8) 
			trap(time, hero, caveTraps, relics);
		else
			choice(time, hero, caveChoices, relics);
			
		time++;
		
		return time;
	}
	
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
	
	public static void battle (int time, Player hero, ArrayList <Monster> monsters) {
		Random r = new Random();
		ArrayList <Monster> tempMonsters = new ArrayList <> ();
		
		for (int i = 0; i < monsters.size(); i++) {
			if ((monsters.get(i).getDifficulty() > time - 1) && (monsters.get(i).getDifficulty() < time + 3)) {
				Monster deepMonsterCopy = new Monster(monsters.get(i));
				tempMonsters.add(deepMonsterCopy);
			}
		}
		
		
		int thisMonster = r.nextInt(tempMonsters.size());
		System.out.println("\nYou quickly raise your weapon: A " + tempMonsters.get(thisMonster).getName() + " stands in your path!");
		Battles fight = new Battles();
		int isDead = fight.battleMonster(tempMonsters.get(thisMonster), hero);
		
		if (isDead <= 0) { // replace later
			System.out.println("You collapse to the ground. Your body, broken. Your mind, fading. As the " + tempMonsters.get(thisMonster).getName() + " looms over you, you feel yourself slipping away...");
			System.out.println("Away...");
			System.exit(0);
		}
		else
			System.out.println("The " + tempMonsters.get(thisMonster).getName() + " defeated, you take a moment to rest and heal before continuing on your way.");
			hero.heal();
			chill();
	}
	
	public static void bossBattle (Boss boss, Player hero) {
		System.out.println("The air is still. You each stand your ground, daring the other to make the first move. In a flash, you both charge. This is it! The ultimate battle! The final challenge! The fight... for GLORY!");
		Battles fight = new Battles();
		
		int isDead = fight.battleBoss(boss, hero);
		
		if (isDead <= 0) { // maybe add some more death dialouge?
			System.out.print("You were so close! As the " + boss.getName() + " lunges for the finishing blow, you wonder what possessed you to go on a Quest for Glory.\nI mean, you wake up outside a cave, and your first thought is to find a horrific beast to fight to the death? I mean, really, what kind of person does that? These thoughts do little to soften the incoming coup de grace, though. \nYour final thought before going to the great beyond is:");
			System.out.println(" I wonder what it will sound like?\n\n...crunch\n\nAfterwards, you are too busy being dead to wonder what part of your body being eviscerated made that noise.");
			System.exit(0);
		}
		else
			System.out.println("The " + boss.getName() + " lets out a final, agonized groan as you deliver the killing blow. You stand above your vanquished foe, and bask in your victory. This kill will surely bring you fame, fortune, and, most importantly...");
			System.out.println("G L O R Y !");
	}
	
	public static Boss getBoss (String location, ArrayList <Boss> bosses) {
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
	
	public static Reward generateReward (int time, ArrayList <Equipment> relics) {
		Random r = new Random();
		ArrayList <Equipment> tempRelics = new ArrayList <> ();
			
		for (int i = 0; i < relics.size(); i++) {
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
		Reward tempReward = new Reward(0, tempRelics.get(thisReward));
		
		return tempReward;
	}
	
	public static void chill () {
		Scanner s = new Scanner(System.in);
		System.out.println("\tPress any key to continue...\n");
		String breakTime = s.nextLine();
	}
	
	public static String nullName () {
		Random r = new Random();
		int funnyName = r.nextInt(10);
		
		System.out.println("Ooh, you must really not be feeling so well. It's alright! If you don't remember your name, I'll come up with one for you. Now, what should your name be...");
		if (funnyName == 0)
			return "Dumpus";
		else if (funnyName == 1)
			return "Rumblebumbus";
		else if (funnyName == 2)
			return "Dinkywinkle";
		else if (funnyName == 3)
			return "Billy the Fool, Lord of the Pigsty";
		else if (funnyName == 4)
			return "A Rat. You are just a rat.";
		else if (funnyName == 5)
			return "John Smith";
		else if (funnyName == 6)
			return "uh... sorry, I forgot";
		else if (funnyName == 7)
			return "A Cardboard Cutout of the Kool-Aid Man";
		else if (funnyName == 8)
			return "Gumby";
		else
			return "you";
		
	}
	
	public static void dennisQuest () {
		Scanner s = new Scanner(System.in);
		Player dennis = new Player("Dennis");
		dennis.addMaxHealth(100);
		dennis.addDamage(100);
		dennis.heal();
		
		System.out.println("\nYou stand in the middle of an ocean of conquest. Foes lie defeated all around." +
						   "\nYou have done it. You have glory! Glory! GLORY! YES! AHAHAHAHHAAAHAHAHAHHAHAHAahaa..." +
						   "\nWait. What is that? From the ground, a lone man rises. He raises his sword in silent challenge." +
						   "\nOne. More. One more conquest, and then you will have all the GLORY you could ever want. Glory. Glory! GLORY!!!!!!");
		chill();
		
		Boss dennisBoss = new Boss("Dennis", 1000, 25, "Joshua Weese", 11);
		dennisBoss.setDescription("GLORY!! GLORY! GLORY!!! GLORY! GLORY!!!!!");
		
		Battles dennisFight = new Battles();
		int isDead = dennisFight.battleBoss(dennisBoss, dennis);
		
		if (isDead <= 0) {
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
			while (i < 100000) {
				System.out.print("GLORY");
				i++;
			}
		
			System.exit(0);
		}
	}

}