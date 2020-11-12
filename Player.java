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
 
 private final int DEFAULT_HEALTH = 10;
 private final int DEFAULT_DMG = 2;
 
 
 public Player() {
	name = "Player";
	health = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	healthEquipment = new Equipment();
	dmgEquipment = new Equipment();
	
 }
 
 
 public Player(String n) {
	name = n;
	health = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
	healthEquipment = new Equipment();
	dmgEquipment = new Equipment();
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
 
 private void equipArmor(Equipment e) {
	healthEquipment = e;
 }
 
 
 private void equipWeapon(Equipment e) {
	dmgEquipment = e;
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
 
 
 public void setHealth(int h) {
	health = h;
 }
 
 
 public void setMaxHealth(int h) {
	maxHealth = h;
 }
 
 
 public void getName(int d) {
	dmg = d;
 }
 
}