/* Trent Powell- Group 5 Final Proj. (Equipment Class)
* CIS 200/ Scholars Section
* 
* This is the equipment class! The equipment class creates equipment objects
* that can be found by the player along his journey. There are three constructors,
* a default constructor with default values, a constructor with parameters
* matching up to each of the four variables, and a constructor that
* creates a new piece of equipment from another piece of equipment, this is for
* dev mode in which the player creates his own objects. Next,
* there are get methods for each of the four variables and 
* a toString method. This is a short and sweet class.
*/

import java.util.*;
import java.io.*;

public class Equipment {
 
 private String name;
 private String type;
 private int value;
 private int rarity;
 
 /** Equipment
 * (This is the default constructor, never to be used because its equipment type is N/A)
 * 
 */
 public Equipment() {
	name = "None";
	type = "N/A";
	value = 0;
	rarity = -1;
 }
 
 /** Equipment
 * (This is a constructor that takes in a string for the equipment name, a string for the equipment type: either 'armor' or 'weapon', an int for the
 * equipment's value bonus which is how much health or damage it gives respective to its type, and an int for rarity which represents how hard it is to find)
 * 
 * @param(A string to represent the equipment's name)
 * @param(A string that represents the equipment's type)
 * @param(An integer that represents the value of the bonus given by the equipment)
 * @param(An integer that represents the rarity of the piece of equipment)
 */
 public Equipment(String n, String t, int v, int r) {
	name = n;
	type = t;
	value = v;
	rarity = r;
 }
 
 /** Equipment
 * (This is a constructor that creates of copy of another piece of equipment by taking in an equipment object and creating an equipment object with its exact variables)
 * 
 * @param (The equipment object to be copied)
 */
 public Equipment(Equipment e) {
	name = e.getName();
	type = e.getType();
	value = e.getValue();
	rarity = e.getRarity();
 }
 
 
 /** getName
 * (Gets the name of the equipment and returns it)
 * 
 * @return (Returns the name of the object as a string)
 */
 public String getName() {
	return name;
 }
 
 /** getType
 * (Gets the type of the equipment and returns it)
 * 
 * @return (Returns type of equipment as a string)
 */
 public String getType() {
	return type;
 }
 
 /** getValue
 * (Gets the bonus value of the equipment and returns it)
 * 
 * @return (Returns the integer representing the bonus value provided by the equipment)
 */
 public int getValue() {
	return value;
 }
 
 /** getRarity
 * (Gets the rarity of the piece of equipment and returns it)
 * 
 * @return (Returns an integer representing the rarity of the equipement)
 */
 public int getRarity() {
	return rarity;
 }
 
 /** toString
 * (Returns a string representing how the object should be printed, it prints its name and value)
 * 
 * @return (Returns the string to be printed)
 */
 public String toString() {
	return("Name: " + name + "\nBonus: +" + value);
 }
 
}