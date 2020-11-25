/* Trent Powell- Group 5 Final Proj. (Equipment Class)
* CIS 200/ Scholars Section
* 
* _________________________________________________
*/

import java.util.*;
import java.io.*;

public class Equipment {
 
 private String name;
 private String type;
 private int value;
 private int rarity;
 
 
 public Equipment() {
	name = "None";
	type = "N/A";
	value = 0;
	rarity = -1;
 }
 
 
 public Equipment(String n, String t, int v, int r) {
	name = n;
	type = t;
	value = v;
	rarity = r;
 }
 
 public Equipment(Equipment e) {
	name = e.getName();
	type = e.getType();
	value = e.getValue();
	rarity = e.getRarity();
 }
 
 
 public String getName() {
	return name;
 }
 
 public String getType() {
	return type;
 }
 
 public int getValue() {
	return value;
 }
 
 public int getRarity() {
	return rarity;
 }
 
 public String toString() {
	return("Name: " + name + "\nBonus: +" + value);
 }
 
}