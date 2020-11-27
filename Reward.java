/* Joel Hervey - Reward.java
*	Creates Reward objects which consist of a single piece of equipment and a certain amount of gold.
*	Used to handle giving the player equipment and adding their stats to the player.
*/
public class Reward {
	private int gold;
	private String type;
	private Equipment equip;
	/**No param constructor
	*
	*/
	public Reward() {
		gold = 0;
		type = "NONE";
		equip = new Equipment();
	}
	/**Creates a choice with only a gold value
	*
	*/
	public Reward(int g) {
		gold = g;
		type = "NONE";
		equip = new Equipment();
	}
	/**Creates a choice with a gold value and an equipment
	*
	*/
	public Reward(int g,Equipment e) {
		gold = g;
		type = e.getType();
		equip = e;
	}
	/** getGold returns the gold from the reward
	*@returns int
	*/
	public int getGold() {
		return gold;
	}
	/** getType returns the type of reward the reward is
	*@return String
	*/
	public String getType() {
		return type;
	}
	/** getEquipment returns the equipment object on the reward
	*@return Equipment
	*/
	public Equipment getEquipment() {
		return equip;
	}
	/** toString displays what the reward is as well as how much gold you have
	*@return String
	*/
	public String toString() {
		String name = equip.getName();
		
		if(type.equalsIgnoreCase("weapon")) {
		return "a " +type+ " and " +gold+ " gold!";
		} else {
			return "a piece of " +type+ " and "+gold+" gold!";
		}
	}
}