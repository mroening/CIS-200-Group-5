/* Trent Powell- Group 5 Final Proj. (Player Class)
* CIS 200/ Scholars Section
* 
* This is the player class! The player class creates the user as 
* an object. Starting with a name, and default values for health and
* damage, the player is created. The player is given default equipment
* to start the game with default values of 0 bonus health and damage.
* Next, the class is filled with functions on equipping new armor, buffing health
* and damage, get and set functions for almost all of the variables, a to string method.
* All simple, self-explanatory methods. 
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
 //Gold is a variable that currently does nothing we had plans if we got to it, but didn't
 private int gold;
 protected Scanner s;
 private int healthBuff = 0;
 private int damageBuff = 0;
 
 private final int DEFAULT_HEALTH = 10;
 private final int DEFAULT_DMG = 2;
 
 /** Player
 * (This is a default constructor that assigns the player's health and damage values to default and gives default equipment. The class also gives the character the name "Player")
 * 
 */
 public Player() {
	name = "Player";
	health = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	maxHealth = DEFAULT_HEALTH;
	healthEquipment = new Equipment();
	dmgEquipment = new Equipment();
	s = new Scanner(System.in);
	
 }
 
 /** Player
 * (This is the normal constructor for the player object. The player is assigned default values for health and damage, and is given default equipment with special names.)
 * 
 * @param (The inputted player name that is assigned to name)
 */
 public Player(String n) {
	name = n;
	health = DEFAULT_HEALTH;
	maxHealth = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	healthEquipment = new Equipment("Leather Patchwork","armor",0,0);
	dmgEquipment = new Equipment("Rusty Sword","weapon",0,0);
	s = new Scanner(System.in);
 }
 
 
 /** addReward
 * (Unfortunately I do not think we got to using this class, it was mostly for if we added and used gold)
 * 
 * @param (Takes in a reward object)
 */
 public void addReward(Reward r) {
	if(r.getType().equalsIgnoreCase("armor")) {
		equipArmor(r.getEquipment());
	} else if(r.getType().equalsIgnoreCase("weapon")) {
		equipWeapon(r.getEquipment()); {	
		}
	}
	gold += r.getGold();
 }
 
 /** getGold
 * (This functions asks and returns the value of the gold variable which we did not end up using.)
 * 
 * @return (Returns values of gold)
 */
 public int getGold() {
	 return gold;
 }
 
 
 /** getEquipment
 * (This function returns a string of the player's equipment)
 * 
 * @return (Returns string of the player's equipment)
 */
 public String getEquipment() {
	 return "\n\tArmor: "+ healthEquipment.getName() + "\n\tWeapon: "+ dmgEquipment.getName();
 }
 
 /** equipArmor
 * (This function is passed an equipment object of type armor. It shows the player his current armor and the proposed new piece of armor and he can decide to leave it or equip it.)
 * 
 * @param (The new found piece of equipment)
 */
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

 /** equipWeapon
 * (This works just like equip armor except the equipment type is weapon, it shows the player its current weapon and asks if it would like to switch.)
 * 
 * @param (The new found weapon equipment)
 */
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
 
 /** getHealth
 * (Returns the value of the player's health)
 * 
 * @return (Returns the player's health as an int)
 */
 public int getHealth() {
	return health;
 }
 
 /** getMaxHealth
 * (Returns the player's max health)
 * 
 * @return (Returns the max health variable as an int)
 */
 public int getMaxHealth() {
	return maxHealth;
 }
 
 /** getDmg
 * (Returns the amount of damage the player can do)
 * 
 * @return (Returns the player's damage as an int)
 */
 public int getDmg() {
	return dmg;
 }
 
 /** getName
 * (Returns the player's name)
 * 
 * @return (The string of the player's name)
 */
 public String getName() {
	return name;
 }
 
 /** heal
 * (Sets health to max health, healing the player)
 * 
 */
 public void heal() {
	 health = maxHealth;
 }
 
 /** takeDamage
 * (This method prints the damage the player took and subtracts the damage taken from his health if it is less than one the player is left with one health)
 * 
 * @param (The amount of damage to be taken as an int)
 */
 public void takeDamage(int damage) { // for traps
	System.out.println("You took "+damage+" damage!");
	if (health - damage < 1)
		health = 1;
	else
		health -= damage;
	
 }
 
 /** setHealth
 * (Sets the player's health variable to the input integer)
 * 
 * @param (The amount of health that the player's health will be set to as an int)
 */
 public void setHealth(int h){ // for fights
        health = h;
    }
 
 /** setMaxHealth
 * (This adds the default health to any healthbuffs and adds the value of his armor to get the player's max health)
 *
 */
 public void setMaxHealth() {
	maxHealth = DEFAULT_HEALTH + healthBuff + healthEquipment.getValue();
 }
 
 /** addMaxHealth
 * (If the player's max health is buffed, it adds the buff and resets maxHealth)
 * 
 * @param (The amount of the buff as an int)
 */
 public void addMaxHealth(int buff) {
	healthBuff += buff;
	setMaxHealth();
 }
 
 /** addDamage
 * (Adds a damage buff to the player's  damage and calls the set damage method to re-set the damage that is being done with the buff)
 * 
 * @param (The size of the damage buff as an int)
 */
 public void addDamage(int buff) {
	damageBuff += buff;
	setDamage();
 }
 
 /** setDamage
 * (Sets how much damage the player should be doing by adding default damage to any buffs and adding the value of the weapon the player has)
 * 
 */
 public void setDamage() {
	dmg = DEFAULT_DMG + damageBuff + dmgEquipment.getValue();
 }
 
 /** toString
 * (This is the to string method for the player, it prints the player's name, current health, and damage)
 * 
 * @return (Returns what is to be printed as a string)
 */
 public String toString(){
       return ("Player's name: " + getName() + "\nHealth: " + getHealth() + "\nDamage: " + getDmg());
 }
}