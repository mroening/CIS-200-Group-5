import java.util.*;
import java.io.*;

public class GloryQuest {
	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Welcome to GLORYQUEST! Type (P)lay to play GLORYQUEST, or type (D)ev to enter Developer mode: ");
		String titleString = s.nextLine().toLowerCase();
		char titleChoice = titleString.charAt(0);
		
		if (titleString.equals("dennis")) {
			System.out.println("Dennis");
		} else if (titleChoice == 'd') {
			try {
				Input devMode = new Input();
				devMode.createNewThing();
			} catch (IOException x) {
				System.out.println("There was an error with the file! Please check your installation and try again.");
			}
		} else if (titleChoice == 'p') {
			playGame();
		} else {
			System.out.println("You think you're a funny man, dont'cha? A real razzle-dazzler! Why don't you go try to impress a Commadore, you fish-lipped clown college drop-out.");
		}
		
	}
	
	public static void playGame () throws IOException {
		
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
		
		File equipmentFile = new File("Relics.txt");
		File monsterFile = new File("Monsters.txt");
		File choiceFile = new File("Choices.txt");
		File trapFile = new File("Traps.txt");
		
		Scanner readEquipmentFile = new Scanner(equipmentFile);
		Scanner readMonsterFile = new Scanner(monsterFile);
		Scanner readChoiceFile = new Scanner(choiceFile);
		Scanner readTrapFile = new Scanner(trapFile);
		
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
		
		Scanner s = new Scanner(System.in);
		
		int time = 0;
		// introduction
		System.out.println("\nIt's dark. It hurts. Your head swims, and your vision comes in and out of focus. You try to focus on something... your name! You must have a name...");
		System.out.print("But what was it? (Enter your name here): ");
		String name = s.nextLine();
		System.out.println("Of course! " + name + "! Now sit back, relax, and get comfy! You're about to embark on...");
		
		Player hero = new Player(name);
		Story story = new Story(name);
		
		if (story.getEventDecision(0).equals("cave"))
			time = theCave(time);
		else 
			time = theForest(time, hero, relics, forestMonsters, forestChoices, forestTraps);
		
	}
	
	public static int theCave (int time) {
		while (time < 5) {
			
		}
		return time;
	}
	
	public static int theForest (int time, Player hero, ArrayList <Equipment> relics, ArrayList <Monster> forestMonsters,  ArrayList <Choice> forestChoices,  ArrayList <Trap> forestTraps) {
		// choice(time, hero, forestChoices, relics);
		// trap(time, hero, forestTraps, relics);
		return time;
	}
	
	public static void choice (int time, Player hero, ArrayList <Choice> choices, ArrayList <Equipment> relics) {
		Random r = new Random();
		int thisChoice = r.nextInt(choices.size());
		
		int isReward = r.nextInt(4); // 1/4 chance to get reward from an event
		if (isReward == 0) { 
			Reward tempReward = generateReward(time, relics);
			choices.get(thisChoice).setReward(tempReward);
		}
		
		choices.get(thisChoice).setPlayer(hero);
		boolean temp = choices.get(thisChoice).userChoice();

	}
	
	public static void trap (int time, Player hero, ArrayList <Trap> traps, ArrayList <Equipment> relics) {
		Random r = new Random();
		int thisTrap = r.nextInt(traps.size());
		
		int isReward = r.nextInt(3); // 1/3 chance to get reward from an trap
		if (isReward == 0) { 
			Reward tempReward = generateReward(time, relics);
			traps.get(thisTrap).setReward(tempReward);
		}
		
		int disarmChance = r.nextInt(60 - ((time + 1) * 2)); // traps get harder to disarm as the game progresses!
		
		traps.get(thisTrap).setPlayer(hero);
		traps.get(thisTrap).disarm(disarmChance);
	}
	
	public static Reward generateReward (int time, ArrayList <Equipment> relics) {
		Random r = new Random();
		ArrayList <Equipment> tempRelics = new ArrayList <> ();
			
		for (int i = 0; i < relics.size(); i++) {
			tempRelics.add(relics.get(i));
		}
			
		Iterator <Equipment> it = tempRelics.iterator();
		while (it.hasNext()) {
			Equipment tempEquipment = it.next();
			if ((tempEquipment.getRarity() < time - 1) || (tempEquipment.getRarity() > time + 1))
				it.remove();
		}
			
		int thisReward = r.nextInt(tempRelics.size());
		Reward tempReward = new Reward(0, tempRelics.get(thisReward));
		
		return tempReward;
	}

}