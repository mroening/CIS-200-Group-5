public class Boss extends Monster
{
    private String description;
	private String location;

    public Boss(){
        super();
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
		location = "wasteland";
    }

    public Boss(String l, int h, int d, String n, int dif){
        super(h, d, n, dif);
		location = l;
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
    }

    public Boss(String l, int h, int d, String n, int dif, String des){
        super(h, d, n, dif);
        description = des;
		location = l;
    }

    public Boss (Boss exist){
        super(exist.health, exist.damage, exist.name, exist.difficulty);
        description = exist.description;
		location = exist.location;
    }

    public @Override void setHealth(int h){
        super.setHealth(h);
    }

    public @Override int getHealth(){
        return (super.getHealth());
    }

    public @Override int getDamage(){
        return super.getDamage();
    }

    public @Override double getDodge(){
        return super.getDodge();
    }
	
	public void setDescription (String s) {
		description = s;
	}
	
	public String getLocation () {
		return location;
	}
	
    public String toString(){
        return (super.toString() + "\nDescription: " + description);
    }
}
