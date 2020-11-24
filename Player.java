/* Trent Powell- Group 5 Final Proj. (Player Class)
* CIS 200/ Scholars Section
* 
* 
*/

import java.util.*;
import java.io.*;

public class Player {
 
 private String name;
 private int health;
 private int maxHealth;
 private int dmg;
 private Equipment healthEquipment;
 private Equipment dmgEquipment;
 private Scanner s = new Scanner(System.in);
 //private int gold;

 private final int DEFAULT_HEALTH = 10;
 private final int DEFAULT_DMG = 2;
 
 
 public Player() {
	name = "Player";
	health = DEFAULT_HEALTH;
	maxHealth = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	healthEquipment = new Equipment();
	dmgEquipment = new Equipment();

 }


 public Player(String n) {
	name = n;
	health = DEFAULT_HEALTH;
	maxHealth = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	healthEquipment = new Equipment("Rusty Sword", "armor", 0, 0);
	dmgEquipment = new Equipment("Leather Patchwork", "weapon", 0, 0);
 }

/*
 public void addReward(Reward r) {
	if(r.getType().equalsIgnoreCase("armor")) {
		equipArmor(r.getEquipment());
	} else if(r.getType().equalsIgnoreCase("weapon")) {
		equipWeapon(r.getEquipment()); {

		}
	}
	gold += r.getGold();
 }
 public int getGold() {
	 return gold;
 }
 */
 
 public String getEquipment() {
	 return "\n\tArmor: "+ healthEquipment.getName() + "\n\tWeapon: "+ dmgEquipment.getName();
 }

 public void equipArmor(Equipment e) {
	System.out.println("\nYour current equipment is: \n" + healthEquipment);
	System.out.println("\nThe new equipment you found is: \n" + e);
	System.out.print("Would you like to switch? (Y)es or (N)o: ");
	String answer = s.nextLine();
	if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
		healthEquipment = e;
		System.out.println("You equipped it!");
	}
 }

 public void equipWeapon(Equipment e) {
	System.out.println("\nYour current equipment is: \n" + dmgEquipment);
	System.out.println("\nThe new equipment you found is: \n" + e);
	System.out.print("Would you like to switch? (Y)es or (N)o: ");
	String answer = s.nextLine();
	if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
		dmgEquipment = e;
		System.out.println("You equipped it!");
	}
 }
 
 public int getHealth() {
	return (health + healthEquipment.getValue());
 }
 
 
 public int getMaxHealth() {
	return (maxHealth + healthEquipment.getValue());
 }
 
 
 public int getDmg() {
	return (dmg + dmgEquipment.getValue());
 }
 
 public String getName() {
	return name;
 }
 
 public void heal() {
	 health = maxHealth;
 }
 
 public void addMaxHealth(int h) {
	maxHealth = maxHealth + h;
 }
 
 
 public void addDamage(int d) {
	dmg = dmg + d;
 }
 
 public String toString(){
       return ("Player's name: " + getName() + "\nHealth: " + getHealth() + "\nDamage: " + getDmg());
 }
 
}