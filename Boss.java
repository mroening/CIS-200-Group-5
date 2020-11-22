public class Boss extends Monster
{
    private String description;

    public Boss(){
        super();
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
    }

    public Boss(int h, int d, String n, int dif){
        super(h, d, n, dif);
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
    }

    public Boss(int h, int d, String n, int dif, String des){
        super(h, d, n, dif);
        description = des;
    }

    public Boss (Boss exist){
        super(exist.health, exist.damage, exist.name, exist.difficulty);
        difficulty = exist.difficulty;
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

    public String toString(){
        return (super.toString() + "\nDescription: " + description);
    }
}
