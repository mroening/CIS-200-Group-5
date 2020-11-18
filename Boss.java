public class Boss extends Monsters
{
    private String description;

    public Boss(){
        super();
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
    }

    public Boss(int h, int d, String n){
        super(h, d, n);
        description = "You see this enemy infront of you, and just can't describe it. You just know that this is gonna be a difficult fight.";
    }

    public Boss(int h, int d, String n, String des){
        super(h, d, n);
        description = des;
    }

    public @Override void setHealth(int h){
        super.setHealth(h);
    }

    public @Override int getHealth(){
        return (super.getHealth());
    }

    public @Override void setDamage(int change){
        super.setDamage(change);
    }

    public @Override int getDamage(){
        return damage;
    }

    public String toString(){
        return (super.toString() + "\nDescription: " + description);
    }
}
