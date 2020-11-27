/**Boss.java
 * This class is used for creating the Boss objects, and holds methods for getting/setting specific values. 
 * It extends off of the monster class.
 * 
 * @author Gibson Williams
 * Scholars
 */
public class Boss extends Monster
{
    private String description;
	private String location;

    /**Boss
     * A default, no-arg constructor that sets the description to a default, and location to wasteland.
     * 
     */
    public Boss(){
        super();
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
		location = "wasteland";
    }

    /**Boss
     * A boss object constructor that calls the super class to create the boss, and set's location to the parameter.
     * 
     * @param l Boss's location
     * @param h Boss's health
     * @param d Boss's damage
     * @param n Boss's name
     * @param dif Boss's difficulty
     */
    public Boss(String l, int h, int d, String n, int dif){
        super(h, d, n, dif);
		location = l;
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
    }

    /**Boss
     * A boss object constructor that uses the super class to create the object, and sets the other variables using the parameters.
     * 
     * @param l Boss's location
     * @param h Boss's health
     * @param d Boss's damage
     * @param n Boss's name
     * @param dif Boss's difficulty
     * @param des Boss's description
     */
    public Boss(String l, int h, int d, String n, int dif, String des){
        super(h, d, n, dif);
        description = des;
		location = l;
    }

    /**Boss
     * A boss object constructor that uses a pre-existing boss to create a copy.
     * 
     * @param exist The boss object that already exists.
     */
    public Boss (Boss exist){
        super(exist.health, exist.damage, exist.name, exist.difficulty);
        description = exist.description;
		location = exist.location;
    }

    /**sethealth
     * Calls super method to set Boss health.
     * 
     * @param h The value that the health is set to.
     * @return Nothing
     */
    public @Override void setHealth(int h){
        super.setHealth(h);
    }

    /**getHealth
     * A method that calls the super method to get the health.
     * 
     * @return Returns the current health value.
     */
    public @Override int getHealth(){
        return (super.getHealth());
    }

    /**getDamage
     * A method that uses the super method to get the damage value.
     * 
     * @return The damage value.
     */
    public @Override int getDamage(){
        return super.getDamage();
    }

    /**getDodge
     * Calls the super method to get the dodge value.
     * 
     * @return The dodge value.
     */
    public @Override double getDodge(){
        return super.getDodge();
    }
    
    /**setDescription
     * A method for setting the bosses description.
     * 
     * @param s The string that the description is set to.
     * @return Nothing
     */
	public void setDescription (String s) {
		description = s;
	}
    
    /**getLocation
     * This method gets the boss's location.
     * 
     * @return The location of the boss.
     */
	public String getLocation () {
		return location;
	}
    
    /**toString
     * Uses the super method for displaying the boss values, with the description added on.
     * 
     * @return Returns the string of the boss values and description.
     */
    public String toString(){
        return (super.toString() + "\nDescription: " + description);
    }
}
