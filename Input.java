import java.util.*;

public class Input {

	private String type;
	private Scanner s = new Scanner(System.in);
	
	public Input () {
		boolean validClass = false;
		String choice = "";
		while (validClass == false) {
			System.out.print("Would you like to input a piece of 'Equipment', a 'Monster', a 'Choice', or a 'Trap': ");
			choice = s.nextLine();
			
			if (!choice.equalsIgnoreCase("Equipment") || !choice.equalsIgnoreCase("Monster") ||
				!choice.equalsIgnoreCase("Choice") || !choice.equalsIgnoreCase("Trap"))
				validClass = true;
			else
				System.out.println("Please choose a valid object!");
		}
		
		type = choice;
		} // end while
	}
	
	public void createNewThing () {
		if (type.equalsIgnoreCase("Equipment") {
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
	
	public void createMonster () {
		// take in input, check for validity, add it to a file
		
	}
	
	public void createChoice() {
		// take in input, check for validity, add it to a file
		
	}
	
	public void createTrap () {
		// take in input, check for validity, add it to a file
		
	}

}