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
 
 private final int DEFAULT_HEALTH = 10;
 private final int DEFAULT_DMG = 2;
 
 
 public Player() {
	name = "Player";
	health = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
 }
 
 
 public Player(String n) {
	name = n;
	health = DEFAULT_HEALTH;
	dmg = DEFAULT_DMG;
 }
 
 
 public void equipArmor(Equipment e) {
	healthEquipment = e;
 }
 
 
 public void equipWeapon(Equipment e) {
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
 public String toString(){
        return ("Player's name: " + getName() + "\nHealth: " + getHealth() + "\nDamage: " + getDmg());
    }
 
}