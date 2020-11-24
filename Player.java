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
 private int gold;
 protected Scanner s;
 
 private final int DEFAULT_HEALTH = 10;
 private final int DEFAULT_DMG = 2;
 
 
 public Player() {
	name = "Player";
	health = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	maxHealth = DEFAULT_HEALTH;
	healthEquipment = new Equipment();
	dmgEquipment = new Equipment();
	s = new Scanner(System.in);
	
 }
 
 
 public Player(String n) {
	name = n;
	health = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	healthEquipment = new Equipment("Leather Patchwork","armor",0,0);
	dmgEquipment = new Equipment("Rusty Sword","weapon",0,0);
	s = new Scanner(System.in);
 }
 
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
		System.out.println("You equipped it!\n");
		setMaxHealth();
		heal();
	}
 }

 public void equipWeapon(Equipment e) {
	System.out.println("\nYour current equipment is: \n" + dmgEquipment);
	System.out.println("\nThe new equipment you found is: \n" + e);
	System.out.print("Would you like to switch? (Y)es or (N)o: ");
	String answer = s.nextLine();
	if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
		dmgEquipment = e;
		System.out.println("You equipped it!\n");
		setDamage();
	}
 }
 
 
 public int getHealth() {
	return health;
 }
 
 
 public int getMaxHealth() {
	return maxHealth;
 }
 
 
 public int getDmg() {
	return dmg;
 }
 
 public String getName() {
	return name;
 }
 
 public void heal() {
	 health = maxHealth;
 }
 public void takeDamage(int damage) {
	System.out.println("You took "+damage+" damage!");
	health -= damage;
 }
 
 public void setMaxHealth() {
	maxHealth = DEFAULT_HEALTH + healthEquipment.getValue();
 }
 
 
 public void setDamage() {
	dmg = DEFAULT_DMG + dmgEquipment.getValue();
 }
 
 public String toString(){
       return ("Player's name: " + getName() + "\nHealth: " + getHealth() + "\nDamage: " + getDmg());
 }
}