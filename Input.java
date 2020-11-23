import java.util.*;
import java.io.*;

public class Input {

	private String type;
	private Scanner s = new Scanner(System.in);
	
	public Input () {
		boolean validClass = false;
		String choice = "";
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
	
	public void createEquipment () {
		// take in input, check for validity, add it to a file
		
	}
	
	public void createMonster () throws IOException {
		int health = 0;
		int damage = 0;
		int difficulty = 0;
		String name = "";
		String location = "";
	
		File monsterFile = new File("Monsters.txt");
		Scanner readFile = new Scanner(monsterFile);
		
		// add tutorial/point to README
		System.out.println("Welcome to Monster creation!");
		
		// getting a valid location for the monster
		do {
			System.out.print("Choose if this is a (C)ave, (F)orest, (W)asteland, or (V)illage monster: ");
			location = s.nextLine().toLowerCase();
			System.out.println(location + "<-- Location");
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
	
	public void createChoice() {
		// take in input, check for validity, add it to a file
		
	}
	
	public void createTrap () {
		// take in input, check for validity, add it to a file
		
	}

}