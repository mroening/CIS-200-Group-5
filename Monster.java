/**Monster
 * This class is used for creating the Monster objects, and holds methods for getting/setting specific values.
 * 
 */
public class Monster {
    protected int health;
    protected int damage;
    protected String name;
    protected int difficulty;
    protected double dodge;

    private final static double dodgeChance = 0.4;

    private final static int defaultDamage = 1;
    private final static int defaultHealth = 3;
    private final static int defaultDifficulty = 1;

    /**Monster
     * A default, no-arg constructor that assigns the values to the default values.
     * 
     */
    public Monster(){
        health = defaultHealth;
        damage = defaultDamage;
        name = "Unnamed Abomination";
        difficulty = defaultDifficulty;
        dodge = dodgeChance;
    }

    /**Monster
     * A Monster object constructor that initiallizes values to the given values.
     * 
     * @param h Monster's health
     * @param d Monster's damage
     * @param n Monster's name
     * @param dif Monster's difficulty
     */
    public Monster(int h, int d, String n, int dif){
        health = h;
        damage = d;
        name = n;
        difficulty = dif;
        dodge = dodgeChance;
    }

    /**Monster
     * A Monster object constructor that uses an already existing monster to create a copy.
     * 
     * @param mon The monster object that is used for creating the copy.
     */
    public Monster(Monster mon){
        health = mon.health;
        damage = mon.damage;
        name = mon.name;
        difficulty = mon.difficulty;
        dodge = dodgeChance;
    }

    /**setHealth
     * Method for setting the monster's health.
     * 
     * @param h The health that the monster's health should be set to.
     * @return Nothing
     */
    public void setHealth(int h){
        health = h;
    }

    /**getHealth
     * Gets the monster's health.
     * 
     * @return Returns the monster's current health.
     */
    public int getHealth(){
        return health;
    }

    /**getDamage
     * Get's the monster's current damage.
     * 
     * @return Monster damage value
     */
    public int getDamage(){
        return damage;
    }

    /**getDodge
     * Gets the monster dodge value.
     * 
     * @return Monster dodge value.
     */
    public double getDodge(){
        return dodgeChance;
    }
    
    /**getDifficulty
     * Gets the monster's difficulty rating.
     * 
     * @return Returns difficulty value
     */
	public int getDifficulty () {
		return difficulty;
	}
    
    /**getName
     * Gets the monster's name.
     * 
     * @return Returns name string.
     */
	public String getName(){
        return name;
    }

    /**toString
     * A method for displaying the monster's name/difficulty/health/damage.
     * 
     * @return Returns a string containing the monster's name/difficulty/health/damage.
     */
    public String toString(){
        return ("Monsters name: " + name + "\nDifficulty: " + difficulty + "\nHealth: " + health + "\nDamage: " + damage);
    }
}
