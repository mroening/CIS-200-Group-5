public class Reward {
	private int gold;
	private String type;
	private Equipment equip;
	
	public Reward() {
		gold = 0;
		type = "NONE";
		equip = new Equipment();
	}
	public Reward(int g) {
		gold = g;
		type = "NONE";
		equip = new Equipment();
	}
	public Reward(int g,Equipment e) {
		gold = g;
		type = e.getType();
		equip = e;
	}
	public int getGold() {
		return gold;
	}
	public String getType() {
		return type;
	}
	public Equipment getEquipment() {
		return equip;
	}
	public String toString() {
		String name = equip.getName();
		
		if(type.equalsIgnoreCase("weapon")) {
		return "a " +type+ " and " +gold+ " gold!";
		} else {
			return "a piece of " +type+ " and "+gold+" gold!";
		}
	}
}