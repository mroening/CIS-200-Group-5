public class Reward {
	private int gold;
	private String type;
	private Equipment equip;
	
	public Reward() {
		gold = 0;
		type = "NONE";
		equip = null;
	}
	public Reward(int g) {
		gold = g;
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
}