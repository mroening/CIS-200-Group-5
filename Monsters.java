public class Monsters {
    protected int health;
    protected int damage;
    protected String name;
    private final static double dodgeChance = 0.3;

    public Monsters(){
        health = 10;
        damage = 3;
        name = "Unnamed Abomination";
    }

    public Monsters(int h, int d, String n){
        health = h;
        damage = d;
        name = n;
    }

    public void setHealth(int h){
        health = h;
    }

    public int getHealth(){
        return health;
    }

    public void setDamage(int change){
        damage = damage-change;
    }

    public int getDamage(){
        return damage;
    }

    public String toString(){
        return ("Monsters name: " + name + "\nHealth: " + health + "\nDamage" + damage);
    }
}
