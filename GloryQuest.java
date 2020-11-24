import java.util.*;
import java.io.*;

public class GloryQuest {
	
	Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.print("Welcome to GLORYQUEST! Type (P)lay to play GLORYQUEST, or type (D)ev to enter Developer mode: ");
		char titleChoice = s.nextLine().toLowerCase().charAt(0);
		
		if (titleChoice == 'd') {
			try {
				Input devMode = new Input();
				devMode.createNewThing();
			} catch (IOException x) {
				System.out.println("There was an error with the file! Please check your installation and try again.");
			}
		} else if (titleChoice == 'p') {
			playGame(s);
		} else {
			System.out.println("You think you're a funny man, dont'cha? A real razzle-dazzler! Why don't you go try to impress a Commadore, you fish-lipped clown college drop-out.");
		}
		
	}
	
	public static void playGame () {
		
	}

}