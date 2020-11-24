/* Trent Powell- Group 5 Final Proj. (Story Class)
* CIS 200/ Scholars Section
* 
* _________________________________________________
*/

import java.util.*;
import java.io.*;

public class Story {
	
	private Scanner s = new Scanner(System.in);
	private ArrayList<String> story = new ArrayList<>(7);
	
	public Story(String name) {
		System.out.println("\n" + name + "'s Quest for Glory: " +
					"\nYou awaken on the rocky ground in a dark forest near the entrance of a cave." + 
					"\nYou feel a bump on your head and can't remember exactly what you were doing." +
					"\nYou see a sword laying next to you and remember that you were on a quest." +
					"\nA quest to defeat one of the many monsters of this world. Unfortunately, you" +
					"\ndon't remember what it is, or where to find it so that leaves you with an important decision: " +
					"\nDo you continue deeper into the dark forest or do you enter into the dark cave? ");
		System.out.print("\n'(F)orest' or '(C)ave': ");
		String answer = s.nextLine();
		if (answer.charAt(0) == 'C' || answer.charAt(0) == 'c') {
			System.out.println("\nYou enter the cave with your sword in hand expecting to encounter something ferocious.");
			story.add(0, "cave");
		}
		else if (answer.charAt(0) == 'F' || answer.charAt(0) == 'f') {
			System.out.println("\nYou are not sure of the importance of the cave to your quest so you continue into the forest.");
			story.add(0, "forest");
		}
		else {
			System.out.println("\nYou still seem to be in a state of confusion, maybe it is the concussion." +
								"You wonder deeper into the forest hoping to find something... ANYTHING.");
			story.add(0, "forest");
		}
	}
//**********************************************************************************************************************************************
	
	
	public void event1() {
		if (story.get(0).equals("cave")) {
			System.out.println("Far into the cave at this point, you see two intersecting paths." + 
								"\nOne path leads deeper into the cave and the other has a bright light at the end of the tunnel." + 
								"\nOnce again you are not sure exactly which way you are supposed to be headed so that leaves you" +
								"\nwith an important decision once again. Do you go deeper into the cave or towards the bright light?");
			System.out.print("\n'(D)eeper' or '(L)ight': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'D' || answer.charAt(0) == 'd') {
				System.out.println("\nYou decide to continue deeper into the cave persistent in the idea that" +
									"\nyour quest must have to do with the cave. Why else would you be here?");
				story.add(1, "cave");
			}
			else if (answer.charAt(0) == 'L' || answer.charAt(0) == 'l') {
				System.out.println("\nYou decide that the cave seems like a dead end and that it'd be nice to get some fresh air." +
									"\nSo you head towards the light, hoping that your quest for glory awaits you on the other side.");
				story.add(1, "wasteland");
			}
			else {
				System.out.println("\nYou can't seem to make up your mind. So, you decide that going towards the light" +
									"\ngenerally means death and that deeper into the cave is the 'safer' option.");
				story.add(1, "cave");
			}
		}
		else if (story.get(0).equals("forest")) {
			System.out.println("\nAfter a few strange encounters in the forest, you see a clearing with a village in the distance." + 
								"\nStill unable to remember anything except your desire to complete your quest, you are not sure if" + 
								"\nthe village has anything to do with your quest. However, you are certain that this village is not" +
								"\nyour home, something seems off about the village. Maybe they will be able to help you on your quest." +
								"\nOr maybe the village is not very welcoming to strangers such as yourself. The village " +
								"\nis probably unnecessary to complete your quest so that leaves you with an important decision once again." +
								"\nDo you go deeper into the forest or towards the village in the distance?");
			System.out.print("\n'(D)eeper' or '(V)illage': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'D' || answer.charAt(0) == 'd') {
				System.out.println("\nYou decide to continue deeper into the forest, thinking that entering a random village" +
									"\ncould be dangerous and it almost certainly isn't the right way to go to complete your quest."); 
				story.add(1, "forest");
			}
			else if (answer.charAt(0) == 'V' || answer.charAt(0) == 'v') {
				System.out.println("\nYou decide that the village is your best option. Chances are low that they will be hostile and" +
									"\nthey may be able to help you find a monster to kill and complete your quest. Still, something" +
									"\nSeems a little off about the village. Nonetheless your adventure towards it.");
				story.add(1, "village");
			}
			else {
				System.out.println("\nSince even now you seem to be struggling with simple decision-making. You start" +
									"\nto head towards to village searching for some assistance to check out the bump on your head.");
				story.add(1, "village");
			}
		}
		else {
			System.out.println("Something went wrong with this event! So you will continue through the forest.");
			story.add(1, "forest");
		}
	}
//**********************************************************************************************************************************************
	
	
	
	
	public void event2() {
		if (story.get(1).equals("cave")) {
			System.out.println("\nAs you wonder deeper into the cave you get the feeling that you are in the right place." +
								"\nYou start to see lots of skeletons, some animal, some human. You think you must be in the right " +
								"\nspot, and continue on");
		}
		else if (story.get(1).equals("wasteland")) {
			System.out.println("\nYou have reached the end of the tunnel and are once again outside, but you can't tell how much time" + 
								"\nhas passed because it is dark and cloudy all around you. You can only see dead ashen trees and no sign" +
								"\nof any lifeforms. You must be in the right place! Whatever has created this place is definitely what" +
								"\nyou have been tasked to stop.");
		}
		else if (story.get(1).equals("forest")) {
			System.out.println("The forest seems to go on forever, with nothing redeeming in sight you are not quite sure you are anywhere" + 
								"\nnear where you are supposed to be. As you look around, you notice a humongous egg that has been cracked open." +
								"\nJust as you were losing hope you know that whatever laid that egg must be what you are tasked to defeat." +
								"\nYou continue towards the egg and follow big strangely shaped animal tracks deeper into the forest.");
		}
		else if (story.get(1).equals("village")) {
			System.out.println("\nYou finally arrive at what seems to be a deserted village, overrun with forest animals." +
								"\nThere must be a reason this village is deserted, maybe the monster you are tasked to defeat" +
								"\nhas already reigned terror over this village. Whatever caused this, you have tasked yourself to" +
								"\nfind and defeat as your new quest.");
		}
		else {
			System.out.println("Something is wrong");
		}
	}
//**********************************************************************************************************************************************

	
	
	
	public void event3(Player p) {
		if (story.get(1).equals("cave")) {
			System.out.println("\nAs you continue through the cave you see something...wait someone that is stuck in a spider web." +
								"\nTo your surprise the person is alive and upon seeing you begs for your help. Being the hero that" +
								"\nthat you are, you should definitely save him, right? On the other hand if you save him, the spider" +
								"\nthat has been fattening him up will not be too happy. Do you want to save him? ");
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nYou cut the man from the webs and he explains to you that he too was on an adventure. He teaches you" +
									"\nhis fighting tricks and helps increase your ability to fight.");
				//gives 3 damage
				p.addDamage(3);
				story.add(2, "save");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nUnfortunately, you cannot see yourself risking your quest to save this man. If you can complete your quest," +
									"\nthen you will be able to save many more lives than just his. He cries for help as you continue walking further into the cave.");
				story.add(2, "pass");
			}
			else {
				System.out.println("\nIt is almost like you ignored him. You definitely heard him, but just kept walking.");
				story.add(2, "pass");
			}
		}
		
		else if (story.get(1).equals("wasteland")) {
			System.out.println("\nJust as you were thinking that the fog and the ashen forest seem to go on forever with nothing promising in" +
								"\nsight you hear something. It is a man laying against one of the many black trees begging for help! " +
								"\nAs you get closer you see that half of the man's face seems to be decaying away like a zombie!. Still, he asks " +
								"\nfor you to give him a hand and he'll be forever in yo.... his words start to trail off" +
								"\nyou can tell that the man is in desperate need of assistance! Do you help the man?");
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nRight as you touch the man's shoulder to help him up you wince in pain as if the man has stung you!" +
									"\nThe man is cursed and your body seems to be starting to become part undead! In your new half " +
									"\nman, half-undead body you feel more weaker, but your body will be able to withstand more blows." +
									"\nThe man is still limp and you decide that it is best you leave him to rest.");
				//gives 5 max health and subtracts 1 damage
				p.addMaxHealth(5);
				p.addDamage(-1);
				p.heal();
				story.add(2, "save");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nYou decide that the corrupted man is far beyond saving and that it is best that you move on.");
				story.add(2, "pass");
			}
			else {
				System.out.println("\nNot sure exactly how you would save him you decide to kill him quick so he can no longer suffer.");
				story.add(2, "pass");
			}
		}
		
		else if (story.get(1).equals("forest")) {
			System.out.println("\nAs you continue through a forest, there is a small hill with a small shed built into it. The shed " +
								"\nis blocked shut by a giant rock. Do you push the rock over and go inside the shed?");
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nYou move the rock over with all of your strength and open the shed and to your surprise," +
									"\nthe shed is completely empty except for a man that was sleeping inside. He explains that " +
									"\na rockslide left him trapped inside a few hours ago and that he has nothing to offer you" +
									"\nexcept his eternal gratitude. While you are flattered, you move on to your journey");
				story.add(2, "save");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("It is just a shed, best not waste time and energy trying to get inside.");
				story.add(2, "pass");
			}
			else {
				System.out.println("Your mind wonders else where and you continue on without making a proper decision about the shed");
				story.add(2, "pass");
			}
		}
		
		else if (story.get(1).equals("village")) {
			System.out.println("\nAs you continue through the deserted village, you hear a faint sound coming from a nearby house." +
								"\nYou get closer and hear a child crying inside. Do you enter the house?" );
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nYou enter the house and the child goes silent in fear. You look around until you find the child" +
									"\nhuddled into a corner. You assure the child you will not hurt him and he seems comforted to see" +
									"\nanother human. You say you are on a quest to kill a monster and he gets excited offering you food." +
									"\nThe young child doesn't talk, but you accept the delicious and filling food and he wishes you luck.");
				//Gives 4 max health
				p.addMaxHealth(4);
				p.heal();
				story.add(2, "save");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nYou decide to let the child be and focus on completeing your quest, you can't be going into random houses.");
				story.add(2, "pass");
			}
			else {
				System.out.println("\nYou sit to ponder this decision for a while and fall asleep. When you wake up you can't remember " +
									"\nwhat you were doing so you move on continuing your quest.");
				story.add(2, "pass");
			}
		}
		else {
			System.out.println("Something is wrong");
		}
	}
//**********************************************************************************************************************************************
	
	
	
	public void event4(Player p) {
		if (story.get(1).equals("cave")) {
			System.out.println("\nYou can tell that more awaits you in this cave and as you are continuing your adventure, a large bow" +
								"\nwith arrows catches your eye. The bow is hand-crafted and engraved with the phrase 'Beholder Begone'.");
			p.equipWeapon(new Equipment("Beholder Begone-r", "weapon", 8, 0));
		}
		else if (story.get(1).equals("wasteland")) {
			System.out.println("\nAfter some unexpected encounters, you see a big sharp axe stuck in tree labeled escribed on it" +
								"\nare the words 'Slay the Lich'.");
			p.equipWeapon(new Equipment("The Lich Slayer", "weapon", 8, 0));
		}
		else if (story.get(1).equals("forest")) {
			System.out.println("\nAs you continue through the forest, you see a sword stuck in a boulder, it seems easy enough to take out." +
								"\nYou lift with all your strength and are able to retrieve the sharp sword.");
			p.equipWeapon(new Equipment("Sword in the Stone", "weapon", 8, 0));
		}
		else if (story.get(1).equals("village")) {
			System.out.println("\nAs you are walking through the village you see a blacksmith totally abandoned like all of the other buildings." +
								"\nWhile all the weapons seem to be taken from the racks there is one weapon, a giant mace sitting untouched.");
			p.equipWeapon(new Equipment("Mace Wind-You", "weapon", 8, 0));
		}
		else {
			System.out.println("Something is wrong");
		}
	}
//**********************************************************************************************************************************************
	
	
	
	
	
	public void event5(Player p) {
		if (story.get(1).equals("cave")) {
			System.out.println("\nYou continue down the cave and to your surprise you see a wimpering coyote that has a leg stuck under a rock" +
								"\nWhile a little crazy, you are tempted to help the wild animal out from under the rock, do you help it?");
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nAs you lift the rubble, the coyote gets up and starts running in circles. You decide it is best to move on." +
									"\nYou continue down the cave and coyote trails you. It must be hungry if it is this deep in a cave so " +
									"\nas you turn around and draw your weapon the coyote jumps past you and crushes a giant spider. Surprised, " +
									"\nyou put your weapon away and feed the coyote a little of your stored food. You have made a new friend!");
				//gives 5 damage;
				p.addDamage(5);
				story.add(3, "tame");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nAs much as it pains you to move on, a coyote is a predator you'd prefer not to deal with and so you continue.");
				story.add(3, "leave");
			}
			else {
				System.out.println("\nYou can't make up your mind so you decide to end the coyote's suffering with your weapon");
				story.add(3, "leave");
			}
		}
		else if (story.get(1).equals("wasteland")) {
			System.out.println("\nYou continue your journey thinking you should turn around since you are running low on supplies." +
								"\nWhile you are pondering this decision, a wild boar dashes by. You are can't believe that the animal" +
								"\nlives in this wasteland, the boar runs by again stopping right in front of you. It almost seems... friendly?" +
								"\nAs odd as that sounds, you are curious if you should try to tame it. On the other hand you could kill it" +
								"\nand have a tasty meal to refill on food. Do you try to tame it?");
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nYou reach out your hand and boar rears back with its head down charging at you. Your assumption was way off" +
									"\nand you try to dodge out of the way as the boar cuts you on the leg as you jump away.");
				//lose 2 max health
				p.addMaxHealth(-2);
				p.heal();
				story.add(3, "tame");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nAs cool as it would be to have a boar as a pet, you decide that the boar will be more of a help on your quest" +
									"\nif it is in your belly. The meal is very filling and you feel reinvigorated");
				p.addMaxHealth(5);
				p.heal();
				story.add(3, "leave");
			}
			else {
				System.out.println("\nWhile failing to make a simple binary decision, you see the boar getting ready to charge you and you " +
									"\njump out of the way just in time as it runs off behind you.");
				story.add(3, "leave");
			}
		}
		else if (story.get(1).equals("forest")) {
			System.out.println("\nAs you continue through the forest you see a small little hut in a tree. As you look up towards the hut" +
								"\nyou see an elderly-like woman ask you what you are doing. You obviously respond saying that you are on a " +
								"\nquest to kill a monster. She asks if you would be interested in strength potion she brewed up. You are" +
								"\ncertain that this is a witch, but know that witches don't tell a lie. Do you take the strength potion?");
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nYou decide to take the potion and feel instant effects! The potion makes your arm muscles bulge, but..." +
									"\nunfortunately as your arms grow in strength, your legs feel weaker and more unstable. While you are " +
									"\ncertain that the potion will increase your fighting ability, you will be easier to defeat as well");
				//lose 5 max health and add 5 damage
				p.addMaxHealth(-5);
				p.addDamage(5);
				p.heal();
				story.add(3, "tame");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nAs you decline she says \"Your loss!\" and you continue on your journey following the animal tracks");
				story.add(3, "leave");
			}
			else {
				System.out.println("\nYou don't even respond, you just keep walking, just keep walking, just keep walking, walking, walking.");
				story.add(3, "leave");
			}
		}
		else if (story.get(1).equals("village")) {
			System.out.println("\nYou hear your stomach growl and look around the village for something to eat. You see a house with a feast" +
								"\nhalf-eaten unfortunately some raccoons are completely hogging the food and seem very aggressive about eating it." +
								"\nDo you share with the raccoons?");
			System.out.print("\n'(Y)es' or '(N)o': ");
			String answer = s.nextLine();
			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nAs you step into the house, the raccoons start grabbing all of the food and running it out the window." +
									"\nYou unfortunately were only able to save a little bit of it before the raccoon gave the rest up." +
									"\nYou barely fill your belly and continue moving into the village");
				story.add(3, "tame");
			}
			else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nWhy would you share with some raccoons? You decide to go into the house as loud as possible scaring the raccoons away." +
									"\nYou have a nice meal and feel more energized to fight");
				//add 6 max health
				p.addMaxHealth(6);
				story.add(3, "leave");
			}
			else {
				System.out.println("\nSince your head must still be hurting due to your inability to make a binary decision you decide a nap is better." +
									"\nWhen you wake up, all the food is eaten so you continue on your way");
				story.add(3, "leave");
			}
		}
		else {
			System.out.println("Something is wrong");
		}
	}
//**********************************************************************************************************************************************
	
	
	
	
	public void event6(Player p) {
		if (story.get(1).equals("cave")) {
			System.out.println("\nYou continue through the cave and see a full set of chainmail armor that a small salamander has made its home." +
								"\nYou know that chainmail is extremely tough.");
			p.equipWeapon(new Equipment("Classic Chainmail", "armor", 15, 0));
		}
		else if (story.get(1).equals("wasteland")) {
			System.out.println("\nWalking through this wasteland is the hardest task you ever remember completeing. The burnt forest is starting" +
								"\nto drive you a bit nuts anything you come across that doesn't want to kill is a miracle. You see a skeleton in" +
								"\nfront of your path wearing spiked metal armor that looks extremely solid.");
			p.equipWeapon(new Equipment("The Defender", "armor", 15, 0));
		}
		else if (story.get(1).equals("forest")) {
			System.out.println("\nThe strange animal tracks seem to go on forever, but while you are walking you come across a giant piece" +
								"\nof bark that is cut in the shape of a shield. The shield looks very protective, but you know you won't be" +
								"\nto carry the shield and carry the weight of your armor.");
			p.equipWeapon(new Equipment("The Wooden Wall", "armor", 15, 0));
		}
		else if (story.get(1).equals("village")) {
			System.out.println("\nContinuing through the village after some crazy encounters, you see a golden armor set through the window" +
								"\nof a house, the door is completely open, but the armor is untouched.");
			p.equipWeapon(new Equipment("The Golden Set", "armor", 15, 0));
		}
		else {
			System.out.println("Something is wrong");
		}
	}
//**********************************************************************************************************************************************
	
	
	
	public void event7() {
		if (story.get(1).equals("cave")) {
			System.out.println("\nYou have reached the end of the line, what lies in front of you is a monster worthy of your killing" +
								"\nthe gross and horrific looking creature known as a the Beholder looks like a giant floating ball" +
								"\nwith one giant eye and flesh-like skin. You are certain this is what you're quested to kill");
		}
		else if (story.get(1).equals("wasteland")) {
			System.out.println("\nAs you continue through the ashen forest you hear a booming voice saying \"Who goes there?\"" +
								"\nYou turn to see a throne sitting on top of a hill with a magical skeleton creature wearing " +
								"\nKing's clothing sits looking stright at you. You tell him that you are quested to kill him" +
								"\nhe says \"Prepare to fight!\" and you are certain this is what you're quested to kill");
		}
		else if (story.get(1).equals("forest")) {
			System.out.println("\nYou see ahead of you more of the giant eggs unhatched, inside of a giant nest. As you start " +
								"\nrunning towards it a green dragon lands in front of you! Seeing the dragon makes you certain" +
								"\nthis is what you're quested to kill");
		}
		else if (story.get(1).equals("village")) {
			System.out.println("\nYou reach the town hall where you can hear a lot of concerned chattering. You knew this village " +
								"\ncouldn't be compeltely abandoned. Right as you walk up to front a giant ogre smashes through the" +
								"\nfront door and let's out a roar! \"Fresh Meat!\" he says as you prepare your weapon. Seeing this, " +
								"makes you certain that this is what you're quested to kill");
		}
		else {
			System.out.println("Something is wrong");
		}
	}
//**********************************************************************************************************************************************
	
	public String getEventDecision(int n) {
		return(story.get(n));
	}
}